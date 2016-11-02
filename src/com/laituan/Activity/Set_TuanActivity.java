package com.laituan.Activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.laituanba.R;
import com.example.laituanba.R.id;
import com.example.laituanba.R.layout;
import com.example.laituanba.R.menu;
import com.laituan.adapter.Set_tuanAdapter;
import com.laituan.adapter.ShopAdapter;
import com.laituan.info.Set_allorder_detail_info;
import com.laituan.info.ShopInfo;
import com.laituan.utils.MyJson;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

/**
 *我的团购车activity
 *联系作者：QQ 240814476
 */
public class Set_TuanActivity extends Activity {

	private JSONArray jsonArray=new JSONArray();
	private ListView mListView=null;
	private MyJson myJson = new MyJson();
	 private TextView mBack;
	private List<Set_allorder_detail_info> list = new ArrayList<Set_allorder_detail_info>();
	private Set_tuanAdapter mAdapter = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set__tuan);
		
		
		
		
		 Intent intent=getIntent();
		 String result=intent.getStringExtra("buyresult");
		 Log.e("resultsettuuan",result);
		 JSONObject jsonObject = null;
			try {
				jsonObject = new JSONObject(result);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		
		 mBack=(TextView) findViewById(R.id.set_tuan_back);
			mBack.setOnClickListener(new OnClickListener(){
				public void onClick(View view) {
					// TODO Auto-generated method stub
					
						Set_TuanActivity.this.finish();
					
				}
			});
		/*List<Set_allorder_detail_info> newlist=(List<Set_allorder_detail_info>)this.getIntent().getSerializableExtra("result");
		Log.e("newlist",newlist.toString());*/
		mListView = (ListView)findViewById(R.id.SetTuanResultView);
		mAdapter = new Set_tuanAdapter(list, Set_TuanActivity.this);
		Log.e("secondtuan","tuan");
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new MainListOnItemClickListener());
		//List<ShopInfo> newList = myJson.getShopList(result);	
		
		List<Set_allorder_detail_info> newlist=new ArrayList<Set_allorder_detail_info> ();
		try {
			
			jsonArray = jsonObject.getJSONArray("cartlist");
			Log.e("jsonArray", "hi");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.e("jsonArray", jsonArray.toString());
		  newlist=myJson.getSetTuanList(jsonArray);
		  Log.e("newlist", newlist.toString());
		
		if (newlist != null) {
			
			for (Set_allorder_detail_info info : newlist) {	
				Log.e("info",info.toString());
				list.add(info);;
			}
			
			mAdapter.notifyDataSetChanged();
			
			newlist=null;
			Log.e("fdfdf","tret");
		}
		//String Data="";
		
		//Intent intent=getIntent();
		//tuan=intent.getBundleExtra("tuan");
	}
	private class MainListOnItemClickListener implements OnItemClickListener {
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Intent intent = new Intent(Set_TuanActivity.this, TuanDetailsActivity.class);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.set__tuan, menu);
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
