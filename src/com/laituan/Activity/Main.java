package com.laituan.Activity;
  
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.example.laituanba.R;
import com.laituan.adapter.TabsAdapter;
import com.laituan.myfragment.FirstFragment;
import com.laituan.myfragment.SecondFragment;
import com.laituan.myfragment.ThirdFragment;
import com.laituan.post.Login;
import com.test.demo.client.util.DialogUtil;
import com.test.demo.client.util.HttpUtil;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost.TabSpec;

/**
 * 主activity
 *联系作者：QQ 240814476
 */
public class Main extends FragmentActivity {
	private FragmentTabHost mTabHost;
	private RadioGroup mTabRg;
	private ViewPager mViewPage;
	TabsAdapter mTabsAdapter;
	public JSONObject a;
	private final Class[] fragments = { FirstFragment.class,
			SecondFragment.class, ThirdFragment.class };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		initView();
		if (savedInstanceState != null) {
			mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
		}
	}

	private void initView() {

		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager());
		mViewPage = (ViewPager) findViewById(R.id.pager);
		mTabRg = (RadioGroup) findViewById(R.id.tab_rg_menu);
		mTabsAdapter = new TabsAdapter(this, mTabHost, mViewPage, mTabRg);
		// 添加tab
		int count = fragments.length;
		for (int i = 0; i < count; i++) {
			TabSpec tabSpec = mTabHost.newTabSpec(i + "").setIndicator(i + "");
			mTabHost.addTab(tabSpec, fragments[i], null);

			mTabsAdapter.addTab(mTabHost.newTabSpec(i + "")
					.setIndicator(i + ""), fragments[i], null);
		}

		mTabRg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.tab_rb_1:
					mTabHost.setCurrentTab(0);
					break;
				case R.id.tab_rb_2:
					mTabHost.setCurrentTab(1);

					break;
				case R.id.tab_rb_3:

					mTabHost.setCurrentTab(2);
					break;
				default:
					break;
				}
			}
		});
		// mTabHost.setCurrentTab(0);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("tab", mTabHost.getCurrentTabTag());
	}
	
	// 定义发送请求的方法
		private JSONObject query(String username, String password) throws Exception
		{
			// 使用Map封装请求参数
			Map<String, String> map = new HashMap<String, String>();
			map.put("username", username);
			map.put("password", password);
			// 定义发送请求的URL
			String url = HttpUtil.Log_URL ;
			// 发送请求
			return new JSONObject(HttpUtil.postRequest(url, map));
		}
		

}
