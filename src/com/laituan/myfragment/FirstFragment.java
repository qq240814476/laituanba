package com.laituan.myfragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.example.laituanba.R;
import com.laituan.Activity.*;
import com.laituan.adapter.ShopAdapter;
import com.laituan.info.ShopInfo;
import com.laituan.model.Model;
import com.laituan.net.MyGet;
import com.laituan.net.ThreadPoolUtils;
import com.laituan.thread.HttpGetThread;
import com.laituan.utils.MyJson;
import com.test.demo.client.util.DialogUtil;
import com.test.demo.client.util.HttpUtil;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SearchView.OnQueryTextListener;

/**
 * ��һ������
 *
 */
public class FirstFragment extends Fragment {
	Button button_siliuji, button_kaoyan, button_jiaxiao, button_buildbody,
			button_xiaoyuzhong, button_else;

	private boolean listBottemFlag = true;
	private Button ListBottem = null;
	private MyGet myGet = new MyGet();
	private MyJson myJson = new MyJson();
	private List<ShopInfo> list = new ArrayList<ShopInfo>();
	private ShopAdapter mAdapter = null;
	private int mStart = 1;
	private int mEnd = 5;
	private boolean flag = true;
	private ListView mListView;
	private String url = null;
	private int a=0;

	private SearchView sv;
	private View rootView;// ����view

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_first, container, false);
		sv = (SearchView) view.findViewById(R.id.search_kuang);
		
		
		
		if(android.os.Build.VERSION.SDK_INT>9){
			StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		
		// ���ø�SearchViewĬ���Ƿ��Զ���СΪͼ��
		sv.setFocusable(false);
		sv.setIconifiedByDefault(false);
		// Ϊ��SearchView��������¼�������
		 //sv.setOnQueryTextListener((OnQueryTextListener) getActivity());
		// ���ø�SearchView��ʾ������ť
		sv.setSubmitButtonEnabled(true);
		// ���ø�SearchView��Ĭ����ʾ����ʾ�ı�
		sv.setQueryHint("����");
		
			
		
		       
		 sv.setOnQueryTextListener(new OnQueryTextListener() {  
			 @Override      
			 public boolean onQueryTextChange(String str) {         
				// Toast.makeText(getActivity(), str, 1).show();
				 
				
				 return false;             }          
			 @Override         
			 public boolean onQueryTextSubmit(String str) { 
				 //Toast.makeText(getActivity(), str, 1).show();
				 SearchPro(str);
				 if (SearchPro(str))
					{
              	/*DialogUtil.showDialog(getActivity()
								, "�����ɹ���", false);*/
						// ����Main Activity
//						Intent intent = new Intent(getActivity(), Main.class);
//						startActivity(intent);
						// ������Activity
//						getActivity().finish();
					}
					else
					{
						/*DialogUtil.showDialog(getActivity()
							, "���������룡", false);*/
					}
				 return false;  
				 }      
			 });   
		 
		
			
			//new Thread(runnable).start();
		
		
		button_siliuji = (Button) view.findViewById(R.id.button_siliuji);
		button_kaoyan = (Button) view.findViewById(R.id.button_kaoyan);
		button_jiaxiao = (Button) view.findViewById(R.id.button_jiaxiao);
		button_buildbody = (Button) view.findViewById(R.id.button_buildbody);
		button_xiaoyuzhong = (Button) view
				.findViewById(R.id.button_xiaoyuzhong);
		button_else = (Button) view.findViewById(R.id.button_else);
		// ���ü�����
		MyBtnOnclick mytouchlistener = new MyBtnOnclick();
		button_siliuji.setOnClickListener(mytouchlistener);
		button_kaoyan.setOnClickListener(mytouchlistener);
		button_jiaxiao.setOnClickListener(mytouchlistener);
		button_buildbody.setOnClickListener(mytouchlistener);
		button_xiaoyuzhong.setOnClickListener(mytouchlistener);
		button_else.setOnClickListener(mytouchlistener);
		
		sv.setOnClickListener(mytouchlistener);
		mListView = (ListView) view.findViewById(R.id.ShopListView);
		initView();
		return view;
		
		
	}
	
	private boolean SearchPro(String word)
	{
		
		//TestApplication application = (TestApplication) getActivity().getApplicationContext(); 
		 
		//������� 
		//int b;
		//Sb=application.getCurIndex(); 
		JSONObject jsonObj;
		try
		{
			jsonObj = query(word);
			Log.e(word, "word="+word);
			//int a=jsonObj.getInt("list");
			//SsLog.e(word, "a="+a);
			String result=jsonObj.toString();
			System.out.println(result);
			// ���status ����1
			
				Bundle bundle=new Bundle();
				bundle.putString("result", result);
				Intent intent = new Intent(getActivity(), SearchActivity.class);
				intent.putExtras(bundle);
				startActivity(intent);
				
			
			//query(word);
		}
		catch (Exception e)
		{
			DialogUtil.showDialog(getActivity(), "��������Ӧ�쳣�����Ժ����ԣ�", false);
			Log.e("eeeeeeeee",e.toString());
			e.printStackTrace();
		}
		return false;
	}

	// ���巢������ķ���
				private JSONObject query(String word) throws Exception
	//private void query(String word) throws Exception
				{
					// ʹ��Map��װ�������
					Map<String, String> map = new HashMap<String, String>();
					map.put("words", word);
					// ���巢�������URL
					String url =HttpUtil.Sear_URL;
					//ThreadPoolUtils.execute(new HttpPostThread(hand, url,"words",word));
		           return new JSONObject(HttpUtil.postRequest(url, map));
				}
				/*Handler hand = new Handler() {
					public void handleMessage(android.os.Message msg) {
						super.handleMessage(msg);
						if (msg.what == 404) {
							Toast.makeText(getActivity(), "�Ҳ�����ַ", 1).show();
						} else if (msg.what == 100) {
							Toast.makeText(getActivity(), "����ʧ��", 1).show();
						} else if (msg.what == 200) {
							Toast.makeText(getActivity(), "����ɹ�", 1).show();				
							String result = (String) msg.obj;
							if (result != null) {
								Toast.makeText(getActivity(), "�ɹ�", 1).show();
								Bundle bundle=new Bundle();
								bundle.putString("result", result);
								Intent intent = new Intent(getActivity(), SearchActivity.class);
								intent.putExtras(bundle);
								startActivity(intent);
								
							}
							//Toast.makeText(getActivity(), "fail", 1).show();
							//mAdapter.notifyDataSetChanged();				
						}
					};
				};*/
	private void initView() {
		
		// ���ü�����
		MyBtnOnclick mytouchlistener = new MyBtnOnclick();
		button_siliuji.setOnClickListener(mytouchlistener);
		button_kaoyan.setOnClickListener(mytouchlistener);
		button_jiaxiao.setOnClickListener(mytouchlistener);
		button_buildbody.setOnClickListener(mytouchlistener);
		button_xiaoyuzhong.setOnClickListener(mytouchlistener);
		button_else.setOnClickListener(mytouchlistener);
		//
		// -----------------------------------------------------------------
		mAdapter = new ShopAdapter(list, getActivity());
		ListBottem = new Button(getActivity());
		ListBottem.setText("������ظ���");
		ListBottem.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (flag && listBottemFlag) {
					url = Model.SHOPURL + "start=" + mStart + "&end=" + mEnd;
					ThreadPoolUtils.execute(new HttpGetThread(hand, url));
					listBottemFlag = false;
				} else if (!listBottemFlag)
					Toast.makeText(getActivity(), "���������Ժ�", 1).show();
			}
		});
		mListView.addFooterView(ListBottem, null, false);
		ListBottem.setVisibility(View.GONE);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new MainListOnItemClickListener());
		// ƴ���ַ�������
		url = Model.SHOPURL+"/article/index/category/jiaxiao.html";
		ThreadPoolUtils.execute(new HttpGetThread(hand, url));
	}

	Handler hand = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 404) {
				Toast.makeText(getActivity(), "�Ҳ�����ַ", 1).show();
				listBottemFlag = true;
			} else if (msg.what == 100) {
				Toast.makeText(getActivity(), "����ʧ��", 1).show();
				listBottemFlag = true;
			} else if (msg.what == 200) {
				String result = (String) msg.obj;
				// ��activity���л�ȡ���罻��������
				if (result != null&&a==0) {
					// 1���������󷵻ص�����
					List<ShopInfo> newList = myJson.getShopList(result);
					if (newList != null) {
						if (newList.size() == 5) {
							ListBottem.setVisibility(View.VISIBLE);
							mStart += 5;
							mEnd += 5;
						} else {
							ListBottem.setVisibility(View.GONE);
						}
						for (ShopInfo info : newList) {
							list.add(info);
						}
						mAdapter.notifyDataSetChanged();
						listBottemFlag = true;
						mAdapter.notifyDataSetChanged();
					}
					a=1;
				}
				mAdapter.notifyDataSetChanged();
			}
		};
	};

	private class MainListOnItemClickListener implements OnItemClickListener {
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Intent intent = new Intent(getActivity(), ShopDetailsActivity.class);
			Bundle bund = new Bundle();
			bund.putSerializable("ShopInfo",list.get(arg2));
			intent.putExtra("activity","");
			intent.putExtra("value",bund);
			startActivity(intent);
		}
	}

	private class MyBtnOnclick implements View.OnClickListener {

		@Override
		public void onClick(View arg0) {
			int mBtnid = arg0.getId();
			// ///////��ת��intent
			Intent intent = new Intent();
			switch (mBtnid) {
			case R.id.button_siliuji:
				intent.putExtra("name", "������");
				/* ָ��intentҪ�������� */
				intent.setClass(getActivity(), ShopListActivity.class);
				getActivity().startActivity(intent);
				break;
			case R.id.button_kaoyan:
				intent.putExtra("name", "����");
				/* ָ��intentҪ�������� */
				intent.setClass(getActivity(), ShopListActivity.class);
				getActivity().startActivity(intent);
				break;
			case R.id.button_jiaxiao:
				intent.putExtra("name", "��У");
				/* ָ��intentҪ�������� */
				intent.setClass(getActivity(), ShopListActivity.class);
				getActivity().startActivity(intent);
				break;
			case R.id.button_buildbody:
				intent.putExtra("name", "����");
				/* ָ��intentҪ�������� */
				intent.setClass(getActivity(), ShopListActivity.class);
				getActivity().startActivity(intent);
				break;
			case R.id.button_xiaoyuzhong:
				intent.putExtra("name", "С����");
				/* ָ��intentҪ�������� */
				intent.setClass(getActivity(), ShopListActivity.class);
				getActivity().startActivity(intent);
				break;
			case R.id.button_else:
				intent.putExtra("name", "����");
				/* ָ��intentҪ�������� */
				intent.setClass(getActivity(), ShopListActivity.class);
				getActivity().startActivity(intent);
				break;

			}

		}
	}
}
