package com.laituan.Activity;

import java.util.ArrayList;
import java.util.List;

import com.example.laituanba.R;
import com.laituan.info.CommentsInfo;
import com.laituan.info.Set_allorder_detail_info;
import com.laituan.info.ShopInfo;
import com.laituan.model.Model;
import com.laituan.thread.HttpGetThread;
import com.laituan.utils.LoadImg;
import com.laituan.utils.MyJson;
import com.laituan.utils.LoadImg.ImageDownloadCallBack;
import com.laituan.utils.MyJson.DetailCallBack;
import com.laituan.myview.MyScrollView;
import com.laituan.myview.MyScrollView.OnScrollListener;
import com.laituan.net.ThreadPoolUtils;
import com.laituan.post.Login;
import com.laituan.post.Shopcart;
import com.test.demo.client.util.HttpUtil;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 团购详情模块
 * 
 * 联系作者：QQ 240814476
 * */
public class TuanDetailsActivity extends Activity implements OnScrollListener {

	
	private Set_allorder_detail_info info = new  Set_allorder_detail_info();
	private String url = null;
	private List<ShopInfo> list = new ArrayList<ShopInfo>();
	private LoadImg loadImg;
	private HttpGetThread http = null;
	private SharedPreferences sp;
	private MyJson myJson = new MyJson();
	private ArrayList<CommentsInfo> CommentsList;

	String b="";
	
	private ShopInfo Values;
	private ImageView  mTuan_details_share,
			mTuan_details_off, mTuan_details_img;
	private MyScrollView mTuan_details_scroll;
	private TextView mTuan_details_back,mTuan_details_money1, mTuan_details_time,
			mTuan_details_num, mTuan_details, mTuan_details_qianggou,mName,mSale,
			mIntroduction;
	private RelativeLayout Tuan_details_tuangou;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_tuan_details);

		loadImg = new LoadImg(TuanDetailsActivity.this);
		Intent intent = getIntent();
		b=intent.getStringExtra("activity");
		Log.e("b",intent.getStringExtra("activity"));
		Bundle bund = intent.getBundleExtra("value");
		info = (Set_allorder_detail_info) bund.getSerializable("DetailsInfo");
		Log.e("infodetailprice",info.getPrice());
		// 查找网络数据
		String endParames = Model.SHOPDETAILURL + "Article/detail/id/" + info.getId();
		http = new HttpGetThread(hand, endParames);
		ThreadPoolUtils.execute(http);
		initView();
		addImg();
		/*mTuan_details_img.setTag(Model.SHOPLISTIMGURL + Values.getName());
		Bitmap bit = loadImg.loadImage(mTuan_details_img, Model.SHOPLISTIMGURL
				+ Values.getName(), new ImageDownloadCallBack() {
			@Override
			public void onImageDownload(ImageView imageView, Bitmap bitmap) {
				mTuan_details_img.setImageBitmap(bitmap);
			}
		});
		if (bit != null) {
			mTuan_details_img.setImageBitmap(bit);
		}*/
	}

	private void initView() {
		MyOnClickListener myOnClickListener = new MyOnClickListener();
		Log.e("udfh","krjjhr");
		Log.e("bbb",b);
	

		Log.e("infodetailname",info.getPrice());
		
		
		// 店铺信息控件
		mTuan_details=(TextView) findViewById(R.id.teammain_mes_details);
		mSale=(TextView) findViewById(R.id.teammain_mes_num);
		mIntroduction=(TextView) findViewById(R.id.teammain_mes_introduction);
		mName = (TextView) findViewById(R.id.teammain_mes_name);
		mTuan_details_img = (ImageView) findViewById(R.id.Tuan_details_img);
		mTuan_details_money1 = (TextView) findViewById(R.id.Tuan_details_money1);
		mTuan_details_num = (TextView) findViewById(R.id.Tuan_details_num);
		mTuan_details_qianggou=(TextView) findViewById(R.id.Tuan_details_qianggou);
		mTuan_details_qianggou.setOnClickListener(myOnClickListener);
		mTuan_details_time = (TextView) findViewById(R.id.Tuan_details_time);
		mTuan_details_back=(TextView) findViewById(R.id.tuan_detail_back);
		mTuan_details_back.setOnClickListener(myOnClickListener);
		//赋值给控件
		mName.setText(info.getGooddetail().get(0).getTitle());
		mTuan_details_img.setTag(Model.SHOPLISTIMGURL + info.getGooddetail().get(0).getCover_path());
		mTuan_details_money1.setText("￥"+info.getPrice());
		mTuan_details_num.setText("+"+info.getNum());
		mTuan_details_time.setText("创建时间："+info.getGooddetail().get(0).getCreate_time());

		mSale.setText(info.getGooddetail().get(0).getSale()+"人已定");
		mIntroduction.setText(info.getGooddetail().get(0).getDescription());
		mTuan_details.setText(info.getGooddetail().get(0).getRule());
	}
	private void addImg() {
		
		
		mTuan_details_img.setTag(Model.SHOPLISTIMGURL + info.getGooddetail().get(0).getCover_path());
		
		
		
		mTuan_details_img.setImageResource(R.drawable.shop_photo_frame);
		// 网络获取图片
		Log.e("add","img");
		Bitmap bit = loadImg.loadImage(mTuan_details_img, Model.SHOPLISTIMGURL
				+ info.getGooddetail().get(0).getCover_path(), new ImageDownloadCallBack() {
			@Override
			public void onImageDownload(ImageView imageView, Bitmap bitmap) {
				// 网络交互时回调进来防止错位
				if (mTuan_details_img.getTag().equals(
						Model.SHOPLISTIMGURL +info.getGooddetail().get(0).getCover_path())) {
					// 设置网络下载回来图片显示
					mTuan_details_img.setImageBitmap(bitmap);
				}
			}
		});
		Log.e("add","img");
		// 从本地获取的
		if (bit != null) {
			// 设置缓存图片显示
			mTuan_details_img.setImageBitmap(bit);
		}
		
		/*if(info==null) Log.e("path","wrong");
		else Log.e("path",info.getCover_path());*/
	/*	Bitmap bit = loadImg.loadImage(mTuan_details_img,
				Model.SHOPLISTIMGURL + info.getCover_path(),
				new ImageDownloadCallBack() {
					public void onImageDownload(ImageView imageView,
							Bitmap bitmap) {
						// 不需要按照tag查找图片，不存在img复用问题
						mTuan_details_img.setImageBitmap(bitmap);
					}
				});
		if (bit != null) {
			mTuan_details_img.setImageBitmap(bit);
		}*/
	}	
	private class MyOnClickListener implements View.OnClickListener {
		public void onClick(View v) {
			int mID = v.getId();
			switch (mID) {
			case R.id.tuan_detail_back:
				TuanDetailsActivity.this.finish();
				break;
			
			case R.id.Tuan_details_qianggou:
				Log.e("img2","img2");
				sp = getSharedPreferences("userInfo", Activity.MODE_PRIVATE); 
				String username = sp.getString("username", ""); 
		        String password = sp.getString("password", ""); 
		        Log.e("aaaaaaa",username);
		        Log.e("bbbbbbb",password);
				if(username==""||password==""){
					Log.e("2","2");
					 Intent intent = new Intent(TuanDetailsActivity.this, LoginActivity.class);
					 TuanDetailsActivity.this.startActivity(intent);
		    	
				}else{ 
					Log.e("3","3");
			        Login a=new Login(username,password);
			        
			        a.login();
			        Log.e("uiyui","reytre");
			        Shopcart b=new Shopcart(info.getGoodid(),"1",info.getPrice(),"","",HttpUtil.Buy_URL);
					if(b.shopcart()){	
						if(b.getTag()){
							Toast.makeText(TuanDetailsActivity.this, "生成订单成功", 1).show();
						}
					}
				}
			break;
		}

		}
	}


	Handler hand = new Handler() {

		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 404) {
				Toast.makeText(TuanDetailsActivity.this, "找不到地址", 1).show();
			} else if (msg.what == 100) {
				Toast.makeText(TuanDetailsActivity.this, "传输失败", 1).show();
			} else if (msg.what == 200) {
				String result = (String) msg.obj;
				Log.e("resulthander", "result:" + result);
				if (result != null) {
					
					}
			}
		};

	};
	@Override
	public void onScroll(int scrollY) {
		// TODO Auto-generated method stub
		
	}

	/*@Override
	public void onScroll(int scrollY) {
		if (scrollY >= mTuan_details_img.getHeight()) {
			Tuan_details_tuangou.setVisibility(View.VISIBLE);
		} else {
			Tuan_details_tuangou.setVisibility(View.GONE);
		}
	}*/
	// 线程返回信息
}