package com.laituan.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.laituanba.R;
import com.laituan.info.ShopInfo;
/**
 * 店铺详情-其他信息模块
 * */
public class ShopDetailsMore extends Activity {

	private ShopInfo info = null;
	private TextView mShop_details_more_title, mShop_details_more_time;
	private ImageView mShoplist_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_shop_details_more);
		Intent intent = getIntent();
		Bundle bund = intent.getBundleExtra("value");
		info = (ShopInfo) bund.getSerializable("ShopInfo");
		initView();
		initModel();
	}

	private void initView() {
		mShop_details_more_time = (TextView) findViewById(R.id.Shop_details_more_time);
		mShop_details_more_title = (TextView) findViewById(R.id.Shop_details_more_title);
		mShoplist_back = (ImageView) findViewById(R.id.Shoplist_back);
		mShoplist_back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				ShopDetailsMore.this.finish();
			}
		});
	}

	private void initModel() {
		mShop_details_more_title.setText(info.getName());
		mShop_details_more_time.setText(info.getPrice());
	}
}
