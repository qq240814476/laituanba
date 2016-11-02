package com.laituan.Activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laituanba.R;
import com.laituan.adapter.SearchMainAdapter;
import com.laituan.adapter.SearchMoreAdapter;
import com.laituan.adapter.ShopAdapter;
import com.laituan.info.ShopInfo;
import com.laituan.model.Model;
import com.laituan.net.MyGet;
import com.laituan.net.ThreadPoolUtils;
import com.laituan.thread.HttpGetThread;
import com.laituan.utils.MyJson;
/**
 * 店铺列表模块
 * */
public class ShopListActivity extends Activity {

	private ListView mListView, mShoplist_toplist, mShoplist_threelist,
			mShoplist_onelist2, mShoplist_twolist2, mShoplist_onelist1,
			mShoplist_twolist1;
	private ImageView mShoplist_back;
	private LinearLayout mShoplist_shanghuleixing, mShoplist_mainlist2,
			mShoplist_mainlist1;
	private TextView mShoplist_title_textbtn1, mShoplist_title_textbtn2,
			mShoplist_title_textbtn3,text2;
	private MyGet myGet = new MyGet();
	private MyJson myJson = new MyJson();
	private List<ShopInfo> list = new ArrayList<ShopInfo>();
	private ShopAdapter mAdapter = null;
	private SearchMoreAdapter topadapter = null;
	private SearchMoreAdapter threeadapter = null;
	private SearchMoreAdapter twoadapter1 = null;
	private SearchMainAdapter oneadapter1 = null;
	private SearchMoreAdapter twoadapter2 = null;
	private SearchMainAdapter oneadapter2 = null;
	private Button ListBottem = null;
	private ImageView mSearch_city_img;
	private TextView mShoplist_title_txt;
	private int mStart = 1;
	private int mEnd = 5;
	private String url = null;
	private boolean flag = true;
	private boolean listBottemFlag = true;
	private boolean toplistview = false;
	private boolean threelistview = false;
	private boolean mainlistview1 = false;
	private boolean mainlistview2 = false;
	private List<Map<String, Object>> mainList1;
	private List<Map<String, Object>> mainList2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_shoplist);		
		initView();
	}

	private void initView() {
		//////////取值	
		Intent intent=getIntent();
		String StringE=intent.getStringExtra("name");
		text2=(TextView)findViewById(R.id.Shoplist_title_txt);
		text2.setText(StringE);
		mShoplist_back = (ImageView) findViewById(R.id.Shoplist_back);
		mShoplist_shanghuleixing = (LinearLayout) findViewById(R.id.Shoplist_shanghuleixing);
		mShoplist_title_txt = (TextView) findViewById(R.id.Shoplist_title_txt);
		mSearch_city_img = (ImageView) findViewById(R.id.Search_city_img);
		mShoplist_title_textbtn1 = (TextView) findViewById(R.id.Shoplist_title_textbtn1);
		mShoplist_title_textbtn2 = (TextView) findViewById(R.id.Shoplist_title_textbtn2);
		mShoplist_title_textbtn3 = (TextView) findViewById(R.id.Shoplist_title_textbtn3);
		mShoplist_toplist = (ListView) findViewById(R.id.Shoplist_toplist);
		mShoplist_mainlist1 = (LinearLayout) findViewById(R.id.Shoplist_mainlist1);
		mShoplist_onelist1 = (ListView) findViewById(R.id.Shoplist_onelist1);
		mShoplist_twolist1 = (ListView) findViewById(R.id.Shoplist_twolist1);
		mShoplist_mainlist2 = (LinearLayout) findViewById(R.id.Shoplist_mainlist2);
		mShoplist_onelist2 = (ListView) findViewById(R.id.Shoplist_onelist2);
		mShoplist_twolist2 = (ListView) findViewById(R.id.Shoplist_twolist2);
		mShoplist_threelist = (ListView) findViewById(R.id.Shoplist_threelist);
		mListView = (ListView) findViewById(R.id.ShopListView);

		MyOnclickListener mOnclickListener = new MyOnclickListener();
		mShoplist_back.setOnClickListener(mOnclickListener);
		mShoplist_shanghuleixing.setOnClickListener(mOnclickListener);
		mShoplist_title_textbtn1.setOnClickListener(mOnclickListener);
		mShoplist_title_textbtn2.setOnClickListener(mOnclickListener);
		mShoplist_title_textbtn3.setOnClickListener(mOnclickListener);
		// -----------------------------------------------------------------
		initModel1();
		initModel2();
		oneadapter1 = new SearchMainAdapter(ShopListActivity.this, mainList1,R.layout.shop_list1_item,false);
		oneadapter1.setSelectItem(0);
		oneadapter2 = new SearchMainAdapter(ShopListActivity.this, mainList2,R.layout.shop_list1_item,true);
		oneadapter2.setSelectItem(0);
		topadapter = new SearchMoreAdapter(ShopListActivity.this,Model.SHOPLIST_TOPLIST,R.layout.shop_list2_item);
		threeadapter = new SearchMoreAdapter(ShopListActivity.this,Model.SHOPLIST_THREELIST,R.layout.shop_list2_item);
		mShoplist_toplist.setAdapter(topadapter);
		mShoplist_onelist1.setAdapter(oneadapter1);
		initAdapter1(Model.SHOPLIST_PLACESTREET[0]);
		mShoplist_onelist2.setAdapter(oneadapter2);
		initAdapter2(Model.MORELISTTXT[0]);
		mShoplist_threelist.setAdapter(threeadapter);
		TopListOnItemclick topListOnItemclick = new TopListOnItemclick();
		Onelistclick1 onelistclick1 = new Onelistclick1();
		Twolistclick1 twolistclick1 = new Twolistclick1();
		Onelistclick2 onelistclick2 = new Onelistclick2();
		Twolistclick2 twolistclick2 = new Twolistclick2();
		ThreeListOnItemclick threeListOnItemClick = new ThreeListOnItemclick();
		mShoplist_toplist.setOnItemClickListener(topListOnItemclick);
		mShoplist_onelist1.setOnItemClickListener(onelistclick1);
		mShoplist_twolist1.setOnItemClickListener(twolistclick1);
		mShoplist_onelist2.setOnItemClickListener(onelistclick2);
		mShoplist_twolist2.setOnItemClickListener(twolistclick2);
		mShoplist_threelist.setOnItemClickListener(threeListOnItemClick);
		// -----------------------------------------------------------------
		mAdapter = new ShopAdapter(list, ShopListActivity.this);
		ListBottem = new Button(ShopListActivity.this);
		ListBottem.setText("点击加载更多");
		ListBottem.setBackgroundResource(R.drawable.btn_addshop);
		ListBottem.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (flag && listBottemFlag) {
					url = Model.SHOPURL + "/article/lists/category/siliuji.html";
					ThreadPoolUtils.execute(new HttpGetThread(hand, url));
					listBottemFlag = false;
				} else if (!listBottemFlag)
					Toast.makeText(ShopListActivity.this, "加载中请稍候", 1).show();
			}
		});
		mListView.addFooterView(ListBottem, null, false);
		ListBottem.setVisibility(View.GONE);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new MainListOnItemClickListener());
		// 拼接字符串操作
		url = Model.SHOPURL+"/article/lists/category/siliuji.html";
		ThreadPoolUtils.execute(new HttpGetThread(hand, url));
	}
	Handler hand = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 404) {
				Toast.makeText(ShopListActivity.this, "找不到地址", 1).show();
				listBottemFlag = true;
			} else if (msg.what == 100) {
				Toast.makeText(ShopListActivity.this, "传输失败", 1).show();
				listBottemFlag = true;
			} else if (msg.what == 200) {
				Toast.makeText(ShopListActivity.this, "传输成功", 1).show();				
				String result = (String) msg.obj;
				// 在activity当中获取网络交互的数据
				if (result != null) {
					// 1次网络请求返回的数据
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
						newList=null;
					}
				}

				mAdapter.notifyDataSetChanged();				
			}
		};
	};
	private class MyOnclickListener implements View.OnClickListener {
		public void onClick(View v) {
			int mID = v.getId();

			if (mID == R.id.Shoplist_back) {
				ShopListActivity.this.finish();
			}
			if (mID == R.id.Shoplist_shanghuleixing) {
				if (!toplistview) {
					mSearch_city_img
							.setImageResource(R.drawable.title_arrow_up);
					mShoplist_toplist.setVisibility(View.VISIBLE);
					topadapter.notifyDataSetChanged();
					toplistview = true;
				} else {
					mSearch_city_img.setImageResource(R.drawable.search_city);
					mShoplist_toplist.setVisibility(View.GONE);
					toplistview = false;
				}
			} else {
				mSearch_city_img.setImageResource(R.drawable.search_city);
				mShoplist_toplist.setVisibility(View.GONE);
				toplistview = false;
			}
			if (mID == R.id.Shoplist_title_textbtn3) {
				Drawable drawable = null;
				if (!threelistview) {
					drawable = getResources().getDrawable(
							R.drawable.ic_arrow_up_black);
					mShoplist_threelist.setVisibility(View.VISIBLE);
					threeadapter.notifyDataSetChanged();
					threelistview = true;
				} else {
					drawable = getResources().getDrawable(
							R.drawable.ic_arrow_down_black);
					mShoplist_threelist.setVisibility(View.GONE);
					threelistview = false;
				}
				// 这一步必须要做,否则不会显示.
				drawable.setBounds(0, 0, drawable.getMinimumWidth(),
						drawable.getMinimumHeight());
				mShoplist_title_textbtn3.setCompoundDrawables(null, null,
						drawable, null);
			} else {
				Drawable drawable = getResources().getDrawable(
						R.drawable.ic_arrow_down_black);
				drawable.setBounds(0, 0, drawable.getMinimumWidth(),
						drawable.getMinimumHeight());
				mShoplist_title_textbtn3.setCompoundDrawables(null, null,
						drawable, null);
				mShoplist_threelist.setVisibility(View.GONE);
				threelistview = false;

			}
			if (mID == R.id.Shoplist_title_textbtn2) {
				Drawable drawable = null;
				if (!mainlistview2) {
					drawable = getResources().getDrawable(
							R.drawable.ic_arrow_up_black);
					mShoplist_mainlist2.setVisibility(View.VISIBLE);
					twoadapter2.notifyDataSetChanged();
					mainlistview2 = true;
				} else {
					drawable = getResources().getDrawable(
							R.drawable.ic_arrow_down_black);
					mShoplist_mainlist2.setVisibility(View.GONE);
					mainlistview2 = false;
				}
				// 这一步必须要做,否则不会显示.
				drawable.setBounds(0, 0, drawable.getMinimumWidth(),
						drawable.getMinimumHeight());
				mShoplist_title_textbtn2.setCompoundDrawables(null, null,
						drawable, null);
			} else {
				Drawable drawable = getResources().getDrawable(
						R.drawable.ic_arrow_down_black);
				drawable.setBounds(0, 0, drawable.getMinimumWidth(),
						drawable.getMinimumHeight());
				mShoplist_title_textbtn2.setCompoundDrawables(null, null,
						drawable, null);
				mShoplist_mainlist2.setVisibility(View.GONE);
				mainlistview2 = false;
			}
			if (mID == R.id.Shoplist_title_textbtn1) {
				Drawable drawable = null;
				if (!mainlistview1) {
					drawable = getResources().getDrawable(
							R.drawable.ic_arrow_up_black);
					mShoplist_mainlist1.setVisibility(View.VISIBLE);
					twoadapter1.notifyDataSetChanged();
					mainlistview1 = true;
				} else {
					drawable = getResources().getDrawable(
							R.drawable.ic_arrow_down_black);
					mShoplist_mainlist1.setVisibility(View.GONE);
					mainlistview1 = false;
				}
				// 这一步必须要做,否则不会显示.
				drawable.setBounds(0, 0, drawable.getMinimumWidth(),
						drawable.getMinimumHeight());
				mShoplist_title_textbtn1.setCompoundDrawables(null, null,
						drawable, null);
			} else {
				Drawable drawable = getResources().getDrawable(
						R.drawable.ic_arrow_down_black);
				drawable.setBounds(0, 0, drawable.getMinimumWidth(),
						drawable.getMinimumHeight());
				mShoplist_title_textbtn1.setCompoundDrawables(null, null,
						drawable, null);
				mShoplist_mainlist1.setVisibility(View.GONE);
				mainlistview1 = false;
			}
		}
	}
	
	

	private class MainListOnItemClickListener implements OnItemClickListener {
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Intent intent = new Intent(ShopListActivity.this, ShopDetailsActivity.class);
			Bundle bund = new Bundle();
			bund.putSerializable("ShopInfo",list.get(arg2));
			intent.putExtra("activity","");
			intent.putExtra("value",bund);
			startActivity(intent);
		}
	}

	private class TopListOnItemclick implements OnItemClickListener {
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			topadapter.setSelectItem(arg2);
			mSearch_city_img.setImageResource(R.drawable.search_city);
			mShoplist_title_txt.setText(Model.SHOPLIST_TOPLIST[arg2]);
			mShoplist_toplist.setVisibility(View.GONE);
			toplistview = false;
		}
	}

	private class Onelistclick1 implements OnItemClickListener {
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			initAdapter1(Model.SHOPLIST_PLACESTREET[arg2]);
			oneadapter1.setSelectItem(arg2);
			oneadapter1.notifyDataSetChanged();
		}
	}

	private class Twolistclick1 implements OnItemClickListener {
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			twoadapter1.setSelectItem(arg2);
			Drawable drawable = getResources().getDrawable(
					R.drawable.ic_arrow_down_black);
			drawable.setBounds(0, 0, drawable.getMinimumWidth(),
					drawable.getMinimumHeight());
			mShoplist_title_textbtn1.setCompoundDrawables(null, null, drawable,
					null);
			int position = oneadapter1.getSelectItem();
			mShoplist_title_textbtn1
					.setText(Model.SHOPLIST_PLACESTREET[position][arg2]);
			mShoplist_mainlist1.setVisibility(View.GONE);
			mainlistview1 = false;
		}
	}

	private class Onelistclick2 implements OnItemClickListener {
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			initAdapter2(Model.MORELISTTXT[arg2]);
			oneadapter2.setSelectItem(arg2);
			oneadapter2.notifyDataSetChanged();
		}
	}

	private class Twolistclick2 implements OnItemClickListener {
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			twoadapter2.setSelectItem(arg2);
			Drawable drawable = getResources().getDrawable(
					R.drawable.ic_arrow_down_black);
			drawable.setBounds(0, 0, drawable.getMinimumWidth(),
					drawable.getMinimumHeight());
			mShoplist_title_textbtn2.setCompoundDrawables(null, null, drawable,
					null);
			int position = oneadapter2.getSelectItem();
			mShoplist_title_textbtn2.setText(Model.MORELISTTXT[position][arg2]);
			mShoplist_mainlist2.setVisibility(View.GONE);
			mainlistview2 = false;
		}
	}

	private void initModel1() {
		mainList1 = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < Model.SHOPLIST_PLACE.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("txt", Model.SHOPLIST_PLACE[i]);
			mainList1.add(map);
		}
	}

	private void initModel2() {
		mainList2 = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < Model.LISTVIEWTXT.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("img", Model.LISTVIEWIMG[i]);
			map.put("txt", Model.LISTVIEWTXT[i]);
			mainList2.add(map);
		}
	}

	private void initAdapter1(String[] array) {
		twoadapter1 = new SearchMoreAdapter(ShopListActivity.this, array,R.layout.shop_list2_item);
		mShoplist_twolist1.setAdapter(twoadapter1);
		twoadapter1.notifyDataSetChanged();
	}

	private void initAdapter2(String[] array) {
		twoadapter2 = new SearchMoreAdapter(ShopListActivity.this, array,R.layout.shop_list2_item);
		mShoplist_twolist2.setAdapter(twoadapter2);
		twoadapter2.notifyDataSetChanged();
	}

	private class ThreeListOnItemclick implements OnItemClickListener {
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			threeadapter.setSelectItem(arg2);
			Drawable drawable = getResources().getDrawable(
					R.drawable.ic_arrow_down_black);
			drawable.setBounds(0, 0, drawable.getMinimumWidth(),
					drawable.getMinimumHeight());
			mShoplist_title_textbtn3.setCompoundDrawables(null, null, drawable,
					null);
			mShoplist_title_textbtn3.setText(Model.SHOPLIST_THREELIST[arg2]);
			mShoplist_threelist.setVisibility(View.GONE);
			threelistview = false;
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (toplistview == true) {
				mSearch_city_img.setImageResource(R.drawable.search_city);
				mShoplist_toplist.setVisibility(View.GONE);
				toplistview = false;
			} else if (threelistview == true) {

				Drawable drawable = getResources().getDrawable(
						R.drawable.ic_arrow_down_black);
				drawable.setBounds(0, 0, drawable.getMinimumWidth(),
						drawable.getMinimumHeight());
				mShoplist_title_textbtn3.setCompoundDrawables(null, null,
						drawable, null);
				mShoplist_threelist.setVisibility(View.GONE);
				threelistview = false;
			} else if (mainlistview1 == true) {
				Drawable drawable = getResources().getDrawable(
						R.drawable.ic_arrow_down_black);
				drawable.setBounds(0, 0, drawable.getMinimumWidth(),
						drawable.getMinimumHeight());
				mShoplist_title_textbtn1.setCompoundDrawables(null, null,
						drawable, null);
				mShoplist_mainlist1.setVisibility(View.GONE);
				mainlistview1 = false;
			} else if (mainlistview2 == true) {
				Drawable drawable = getResources().getDrawable(
						R.drawable.ic_arrow_down_black);
				drawable.setBounds(0, 0, drawable.getMinimumWidth(),
						drawable.getMinimumHeight());
				mShoplist_title_textbtn2.setCompoundDrawables(null, null,
						drawable, null);
				mShoplist_mainlist2.setVisibility(View.GONE);
				mainlistview2 = false;
			} else {
				ShopListActivity.this.finish();
			}
		}
		return false;
	}

}
