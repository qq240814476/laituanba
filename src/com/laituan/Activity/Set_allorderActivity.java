package com.laituan.Activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.laituanba.R;
import com.example.laituanba.R.id;
import com.example.laituanba.R.layout;
import com.example.laituanba.R.menu;
import com.laituan.adapter.Set_allorderAdapter;
import com.laituan.adapter.ShopAdapter;
import com.laituan.info.Set_allorder_detail_info;
import com.laituan.info.Set_allorder_info;
import com.laituan.info.ShopInfo;
import com.laituan.myfragment.login_content_fragment;
import com.laituan.net.MyGet;
import com.laituan.utils.MyJson;
import com.test.demo.client.util.DialogUtil;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 我的订单activity
 *联系作者：QQ 240814476
 */
public class Set_allorderActivity extends Activity {


	JSONArray jsonArray = new JSONArray();
	JSONObject jsonObj=null;
	private ListView mListView;
	private TextView mBack;
	private MyGet myGet = new MyGet();
	private MyJson myJson = new MyJson();
	private List<Set_allorder_info> list = new ArrayList<Set_allorder_info>();
	private Set_allorderAdapter mAdapter = null;
	List<Set_allorder_info> newlist;
	
	@TargetApi(Build.VERSION_CODES.KITKAT)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_set_allorder);
		
		 Intent intent=getIntent();
		 String result=intent.getStringExtra("result");
		 try {
			jsonObj= new JSONObject(result);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  

		mBack=(TextView) findViewById(R.id.allorder_back);
		mBack.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				// TODO Auto-generated method stub
				
					Set_allorderActivity.this.finish();
				
			}
		});
		mListView = (ListView)findViewById(R.id.Set_allorderResulistView);
		mAdapter = new Set_allorderAdapter(list, Set_allorderActivity.this);
		Log.e("josnobject",jsonObj.toString());
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new MainListOnItemClickListener());
	
		
		List<Set_allorder_info> newlistr=new ArrayList<Set_allorder_info> ();
		
		
		try {
			Log.e("jsonArray", "hi");
			if(jsonObj==null) Log.e("wrong","yes");
			else Log.e("json",jsonObj.toString());
			
			jsonArray = jsonObj.getJSONArray("allorder");
			Log.e("rduh","jhfdej");
			
			
			//Log.e("jsonArrayactivity", jsonArray.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		newlist=myJson.getSetAllorderList(jsonArray);
		
		//List<Set_allorder_info> newlist=(List<Set_allorder_info>)intent.getSerializableExtra("list");	
		//Log.e("result",newlist.get(0).getAddressid());
		if (newlist != null) {
			
			for (Set_allorder_info info : newlist) {	
				Log.e("info",info.geIspay());
				list.add(info);
				Integer i=new Integer(list.size());
				Log.e("iiii", i.toString());
				Log.e("list-address", list.get(0).getAddressid());
			}
			mAdapter.notifyDataSetChanged();
			newlist=null;
		}
		
	}
	
	private class MainListOnItemClickListener implements OnItemClickListener {
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			DialogUtil.showDialog(Set_allorderActivity.this, "click！", false);
			Log.e("udh","ghfg");
			Intent intent = new Intent(Set_allorderActivity.this, ShopDetailsActivity.class);
			Bundle bund = new Bundle();
			bund.putSerializable("ShopInfo",(Serializable) list.get(arg2).getIdlist().get(0).getGooddetail());

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
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.search, menu);
		return true;
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
