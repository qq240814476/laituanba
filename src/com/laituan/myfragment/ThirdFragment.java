package com.laituan.myfragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.laituanba.R;
import com.laituan.Activity.LoginActivity;
import com.laituan.Activity.RegistrationActivity;
import com.laituan.Activity.Set_TuanActivity;
import com.laituan.Activity.Set_allorderActivity;
import com.laituan.Activity.ShopListActivity;
import com.laituan.Activity.UserInfoSimpleActivity;
import com.laituan.info.ShopInfo;
import com.laituan.net.ThreadPoolUtils;
import com.laituan.post.Login;
import com.laituan.thread.HttpGetThread;
import com.laituan.utils.MyJson;
import com.test.demo.client.util.DialogUtil;
import com.test.demo.client.util.HttpUtil;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 第三个界面
 *
 */
public class ThirdFragment extends Fragment {
	private TextView mLogin,mName;
	private TextView mSimple_info;
	private TextView mMybuy, mSet,mAllorder,mLoginOut,mRegister;
	private MyJson myJson = new MyJson();
	JSONObject jsonObj=null;
	String[] stringArray;
	String Name="";
	Integer id=new Integer(-1);
	Login b=new Login();
	//Intent intent;
	//int b;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		
		//TestApplication application = (TestApplication) getActivity().getApplicationContext(); 
		 
		//获取变量 
		//b=application.getCurIndex();
		jsonObj=null;
		Name=b.getUserName();
		id=b.getId();
        View view = inflater.inflate(R.layout.activity_user_info, container, false);
        mRegister=(TextView) view.findViewById(R.id.user_register);
        mLoginOut = (TextView) view.findViewById(R.id.text_login_out);
        mMybuy = (TextView) view.findViewById(R.id.text_my_buy);
        mAllorder=(TextView) view.findViewById(R.id.text_my_allorder);
		mSet = (TextView) view.findViewById(R.id.text_set);
		
		/*if(Name.equals("")) mName.setText("未登录");
		else mName.setText(login_content_fragment.NAME);*/
		mLogin = (TextView) view.findViewById(R.id.user_login);
		mLogin.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				// TODO Auto-generated method stub

				 Intent intent = new Intent(getActivity(), LoginActivity.class);
				getActivity().startActivity(intent);
				// Toast.makeText(login_title_fragment.this.getActivity().this,
				// "注册按钮被单击", 1).show();

			}
		});
		mRegister.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				// TODO Auto-generated method stub

				 Intent intent = new Intent(getActivity(), RegistrationActivity.class);
				getActivity().startActivity(intent);
				// Toast.makeText(login_title_fragment.this.getActivity().this,
				// "注册按钮被单击", 1).show();

			}
		});
		mLoginOut.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				// TODO Auto-generated method stub
				if(login_content_fragment.a==1){
					
					 
						//	Logout();
					
				}
				else{
					//DialogUtil.showDialog(getActivity(), "请先登录！", false);
					 Intent intent=new Intent(getActivity(),LoginActivity.class);
					 getActivity().startActivity(intent);
				}
					
				

			}
		});
		mMybuy.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				// TODO Auto-generated method stub
				if(login_content_fragment.a==1){
					if (jsonObj==null) SearchPro();
					 Log.e("ddf","null");
					 String result=jsonObj.toString();
					  Bundle bundle=new Bundle();
						bundle.putString("buyresult", result);
					
					  Intent intent=new Intent(getActivity(),Set_TuanActivity.class);
					
					/*	List<Set_allorder_detail_info> tuan=new ArrayList<Set_allorder_detail_info> ();
						JSONArray jsonArray = null;
						try {
							
							jsonArray = jsonObj.getJSONArray("cartlist");
							Log.e("jsonArray", "hi");
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Log.e("jsonArray", jsonArray.toString());
						  tuan=myJson.getSetTuanList(jsonArray);
						  Log.e("tuan", tuan.toString());
							Bundle bundle=new Bundle();
							bundle.putSerializable("tuan", (Serializable) tuan);
							 
							Intent  intent = new Intent(getActivity(), Set_TuanActivity.class);*/
							intent.putExtras(bundle);
							Log.e("thidrdresult",result);
							 getActivity().startActivity(intent);
					
				}
				else{
					//DialogUtil.showDialog(getActivity(), "请先登录！", false);
					 Intent intent=new Intent(getActivity(),LoginActivity.class);
					 getActivity().startActivity(intent);
				}
					
				

			}
		});
		mAllorder.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				// TODO Auto-generated method stub
				if(login_content_fragment.a==1){
					if (jsonObj==null) SearchPro();

					/*List<Set_allorder_info> allorder=new ArrayList<Set_allorder_info> ();
	JSONArray jsonArray = null;
	
	try {
		Log.e("jsonArray", "hi");
		
		
		jsonArray = jsonObj.getJSONArray("allorder");
		
		List<String> list = new ArrayList<String>();
		for (int i=0; i<jsonArray.length(); i++) {
		    list.add( jsonArray.getString(i) );
		}
		stringArray = list.toArray(new String[list.size()]);

		
		Log.e("jsonArray", jsonArray.toString());
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	allorder=myJson.getSetAllorderList(jsonArray);
	Log.e("tuanytuyuytuytyugyutuy", allorder.toString());*/
	
		/*Bundle bundle=new Bundle();
		bundle.putSerializable("result", (Serializable) stringArray);
		
		 Intent intent = new Intent(getActivity(), Set_allorderActivity.class);
		intent.putExtras(bundle);
		 getActivity().startActivity(intent);*/
	  
	  String result=jsonObj.toString();
	  Bundle bundle=new Bundle();
		bundle.putString("result", result);
	
	  Intent intent=new Intent(getActivity(),Set_allorderActivity.class);
	  
	  
	 //intent.putExtra("list", (Serializable)allorder);  
	  //intent.putExtras(bundle);
	  intent.putExtras(bundle);
		 getActivity().startActivity(intent);				
					}
				
				else{
					//DialogUtil.showDialog(getActivity(), "请先登录！", false);
					Intent intent=new Intent(getActivity(),LoginActivity.class);
					 getActivity().startActivity(intent);
				}
				
				

			}
		});
		mSet.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				// TODO Auto-generated method stub
//				Intent intent = new Intent(getActivity(), SetActivity.class);
//				getActivity().startActivity(intent);
				// getActivity().finish();
				// Toast.makeText(login_title_fragment.this.getActivity().this,
				// "注册按钮被单击", 1).show();

			}
		});
		mSimple_info = (TextView) view.findViewById(R.id.text_simple_info);
		mSimple_info.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				// TODO Auto-generated method stub
				
				 
					//Toast.makeText(getActivity(),"个人基本信息按钮被单击", 1).show();
				Intent  intent;
					if(login_content_fragment.a==1){
						intent=new Intent(getActivity(),UserInfoSimpleActivity.class);
					}
					else{
						//DialogUtil.showDialog(getActivity(), "请先登录！", false);
						 intent=new Intent(getActivity(),LoginActivity.class);
					}
					
					getActivity().startActivity(intent);
					
					
					
					//getActivity().finish();
					//Toast.makeText(login_title_fragment.this.getActivity().this, "注册按钮被单击", 1).show();
				
			}
		});
        return view;
	}
	private void Logout()
	{
		
		/*String url =HttpUtil.Logout_URL;
		ThreadPoolUtils.execute(new HttpGetThread(hand, url));*/
	}
	Handler hand = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 404) {
				Toast.makeText(getActivity(), "找不到地址", 1).show();
			} else if (msg.what == 100) {
				Toast.makeText(getActivity(), "传输失败", 1).show();
			} else if (msg.what == 200) {
				Toast.makeText(getActivity(), "传输成功", 1).show();				
				String result = (String) msg.obj;
				// 在activity当中获取网络交互的数据
				if (result != null) {
					// 1次网络请求返回的数据
					
				}
				
			}
		};
	};
	private boolean SearchPro()
	{
		
		//TestApplication application = (TestApplication) getActivity().getApplicationContext(); 
		 
		//保存变量 
		//int b;
		//Sb=application.getCurIndex(); 
		
		try
		{
			jsonObj = query();
			Log.e("jsonArray", "qqqqqqq");
			//int a=jsonObj.getInt("list");
			//SsLog.e(word, "a="+a);
			//result=jsonObj.toString();
			//System.out.println(result);
			// 如果status 等于1
			
				
			
			//query(word);
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
					id=b.getId();
					Map<String, String> map = new HashMap<String, String>();
					map.put("uid", id.toString());
					// 定义发送请求的URL
					String url =HttpUtil.Per_URL;
					//ThreadPoolUtils.execute(new HttpPostThread(hand, url,"words",word));
		           return new JSONObject(HttpUtil.postRequest(url, map));
				}
				
				
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
