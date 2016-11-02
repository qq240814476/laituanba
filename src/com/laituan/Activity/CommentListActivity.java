package com.laituan.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
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
 * 待点评列表模块
 * 联系作者：QQ 240814476
 * */
public class CommentListActivity extends Activity {

	private ListView mListView;
	private ImageView mShoplist_back;
	private LinearLayout  mShoplist_mainlist2,
			mShoplist_mainlist1;
	private TextView mShoplist_title_textbtn1, mShoplist_title_textbtn2,
			mShoplist_title_textbtn3;
	private MyGet myGet = new MyGet();
	private MyJson myJson = new MyJson();
	private List<ShopInfo> list = new ArrayList<ShopInfo>();
	private ShopAdapter mAdapter = null;
	private SearchMoreAdapter threeadapter = null;
	private SearchMoreAdapter twoadapter1 = null;
	private SearchMainAdapter oneadapter1 = null;
	private SearchMoreAdapter twoadapter2 = null;
	private SearchMainAdapter oneadapter2 = null;
	private Button ListBottem = null;
	private ImageView mSearch_city_img;
	private int mStart = 1;
	private int mEnd = 5;
	private String url = null;
	private boolean flag = true;
	private boolean listBottemFlag = true;
	private List<Map<String, Object>> mainList1;
	private List<Map<String, Object>> mainList2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_dianpinglist);
		initView();
	}

	private void initView() {
		//////////取值
		Intent intent=getIntent();
		String StringE=intent.getStringExtra("name");
		TextView text2=(TextView)findViewById(R.id.Shoplist_title_txt);
		text2.setText(StringE);
		mShoplist_back = (ImageView) findViewById(R.id.Shoplist_back);
		mShoplist_title_textbtn1 = (TextView) findViewById(R.id.Shoplist_title_textbtn1);
		mShoplist_title_textbtn2 = (TextView) findViewById(R.id.Shoplist_title_textbtn2);
		mListView = (ListView) findViewById(R.id.ShopListView);
		MyOnclickListener mOnclickListener = new MyOnclickListener();
		mShoplist_back.setOnClickListener(mOnclickListener);
		mShoplist_title_textbtn1.setOnClickListener(mOnclickListener);
		mShoplist_title_textbtn2.setOnClickListener(mOnclickListener);
		mShoplist_title_textbtn3.setOnClickListener(mOnclickListener);

		////////////////////////-----------------------------------
		mAdapter = new ShopAdapter(list, CommentListActivity.this);
		ListBottem = new Button(CommentListActivity.this);
		ListBottem.setText("点击加载更多");
		ListBottem.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (flag && listBottemFlag) {
					url = Model.SHOPURL + "start=" + mStart + "&end=" + mEnd;
					ThreadPoolUtils.execute(new HttpGetThread(hand, url));
					listBottemFlag = false;
				} else if (!listBottemFlag)
					Toast.makeText(CommentListActivity.this, "加载中请稍候", 1).show();
			}
		});
		mListView.addFooterView(ListBottem, null, false);
		ListBottem.setVisibility(View.GONE);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new MainListOnItemClickListener());
		// 拼接字符串操作
		url = Model.SHOPURL + "start=" + mStart + "&end=" + mEnd;
		ThreadPoolUtils.execute(new HttpGetThread(hand, url));
	}

	private class MyOnclickListener implements View.OnClickListener {
		public void onClick(View v) {
			int mID = v.getId();

			if (mID == R.id.Shoplist_back) {
				CommentListActivity.this.finish();
			}
			
			if (mID == R.id.Shoplist_title_textbtn2) {
				Drawable drawable = null;
				/////未点评
			}
			if (mID == R.id.Shoplist_title_textbtn1) {
				Drawable drawable = null;
				/////已点评
			}
		}
	}

	Handler hand = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 404) {
				Toast.makeText(CommentListActivity.this, "找不到地址", 1).show();
				listBottemFlag = true;
			} else if (msg.what == 100) {
				Toast.makeText(CommentListActivity.this, "传输失败", 1).show();
				listBottemFlag = true;
			} else if (msg.what == 200) {
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
					}
				}
				mAdapter.notifyDataSetChanged();
			}
		};
	};

	private class MainListOnItemClickListener implements OnItemClickListener {
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Intent intent = new Intent(CommentListActivity.this, ShopDetailsCommentActivity.class);
			Bundle bund = new Bundle();
			bund.putSerializable("ShopInfo",list.get(arg2));
			intent.putExtra("value",bund);
			startActivity(intent);
		}
	}


	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
		
		}
		return false;
	}

}
