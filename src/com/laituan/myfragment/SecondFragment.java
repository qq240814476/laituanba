package com.laituan.myfragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.laituanba.R;
import com.laituan.Activity.Set_TuanActivity;
import com.laituan.Activity.ShopDetailsActivity;
import com.laituan.Activity.ShopListActivity;
import com.laituan.Activity.TuanDetailsActivity;
import com.laituan.adapter.Set_tuanAdapter;
import com.laituan.adapter.ShopAdapter;
import com.laituan.info.Set_allorder_detail_info;
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
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager.OnActivityResultListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 第二个界面
 *
 */
public class SecondFragment extends Fragment  {
/*	public void run(){
		while(!Thread.currentThread().isInterrupted()){
			try{
				Thread.sleep(100);
			}
			catch(InterruptedException e){
				getActivity()
			}
		}
	}*/
	private List<Set_allorder_detail_info> list = new ArrayList<Set_allorder_detail_info>();
	LinearLayout class01;
	private ListView mListView;
	private JSONArray jsonArray=new JSONArray();
	private Integer id=new Integer(-1);
	JSONObject jsonObject = null;
	JSONObject jsonObj = null;
	String url="";
	private MyGet myGet = new MyGet();
	private MyJson myJson = new MyJson();
	private Set_tuanAdapter mAdapter = null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//OnActivityResultListener
		// TODO 自动生成的方法存根
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        mListView=(ListView) view.findViewById(R.id.TuanResultView);
        mAdapter = new Set_tuanAdapter(list, getActivity());
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new MainListOnItemClickListener());
        if(login_content_fragment.a==1){
        	 url = Model.HTTPURL+Model.SHOPURL + "/shopcart/index.html";
        	 Log.e("seconfd",url);
        	 SearchPro();
  //   		ThreadPoolUtils.execute(new HttpGetThread(hand, url));	
        }
       // class01=(LinearLayout)view.findViewById(R.id.layout_class);
        MyBtnOnclick mytouchlistener = new MyBtnOnclick();
		//class01.setOnClickListener(mytouchlistener);
       
		return view;
	}
	
	private boolean SearchPro()
	{
		
		try
		{
			jsonObj = query();
			Log.e("secondjson", jsonObj.toString());
			List<Set_allorder_detail_info> newList=new ArrayList<Set_allorder_detail_info>();
			try {
				Log.e("retyert","rtertrtesyrty");
				//Log.e("secondjsonarrayu",jsonObject.getString("cart"));
				jsonArray=jsonObj.getJSONArray("cart");
				Log.e("secondjsonarrayu",jsonObj.getJSONArray("cart").toString());
				newList = myJson.getSetTuanList(jsonObj.getJSONArray("cart"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("getlisterrrorsecond",e.toString());
			}				
			if (newList != null) {
				
				for (Set_allorder_detail_info info : newList) {	
					Log.e("secondsearpronewlist","ryrdyrtdtyr");
					list.add(info);
					Log.e("secondlisdtsiszse",new Integer(list.size()).toString());
				}
				mAdapter.notifyDataSetChanged();
				Log.e("secondlghfgjhgkjhukiohyuftrdsrytde",new Integer(list.size()).toString());
				newList=null;
			}
			
		}

		
		catch (Exception e)
		{
			DialogUtil.showDialog(getActivity(), "服务器响应异常，请稍后再试！", false);
			Log.e("eeeeeeeee",e.toString());
			e.printStackTrace();
		}
		return false;
	}

	// 定义发送请求的方法
				private JSONObject query() throws Exception
	//private void query(String word) throws Exception
				{
					// 使用Map封装请求参数
					id=login_content_fragment.ID;
					Map<String, String> map = new HashMap<String, String>();
					map.put("uid", id.toString());
					
				
		           return new JSONObject(HttpUtil.postRequest(url, map));
				}
	
	
/*	Handler hand = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 404) {
				Toast.makeText(getActivity(), "找不到地址", 1).show();
			} else if (msg.what == 100) {
				Toast.makeText(getActivity(), "传输失败", 1).show();
			} else if (msg.what == 200) {
				Toast.makeText(getActivity(), "传输成功", 1).show();				
				String result = (String) msg.obj;
				Log.e("resultsencondfjk",result);
				// 在activity当中获取网络交互的数据
				if (result != null) {
					// 1次网络请求返回的数据
					
					try {
						jsonObject = new JSONObject(result);
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						Log.e("shoploste1",e1.toString());
					}
					
					
				
					List<Set_allorder_detail_info> newList=new ArrayList<Set_allorder_detail_info>();
					try {
						newList = myJson.getSetTuanList(jsonObject.getJSONArray("cart"));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
					if (newList != null) {
						
						for (Set_allorder_detail_info info : newList) {	
							list.add(info);
						}
						mAdapter.notifyDataSetChanged();
						newList=null;
					}
				}

				mAdapter.notifyDataSetChanged();				
			}
		};
	};*/
	///商店链接传值
				private class MainListOnItemClickListener implements OnItemClickListener {
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
							long arg3) {
						Intent intent = new Intent(getActivity(), TuanDetailsActivity.class);
						Bundle bund = new Bundle();
						bund.putSerializable("DetailsInfo",list.get(arg2));
						try {
							JSONObject job = jsonArray.getJSONObject(arg2);
							Log.e("list",job.toString());
							intent.putExtra("activity",job.toString());
							intent.putExtra("value", bund);
							startActivity(intent);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
	private class MyBtnOnclick implements View.OnClickListener {
		@Override
		public void onClick(View arg0) {
			int mBtnid = arg0.getId();
			// ///////跳转的intent
			Intent intent = new Intent();
			switch (mBtnid) {
			/*case R.id.layout_class:
				 指定intent要启动的类 
				Bundle bund = new Bundle();
				intent.putExtra("activity","tuangouche");
				intent.setClass(getActivity(), ShopDetailsActivity.class);
				getActivity().startActivity(intent);
				break;*/
			}
		}
	}
}
