package com.laituan.Activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laituanba.R;
import com.laituan.adapter.ShopAdapter;
import com.laituan.adapter.ShopDetailAdapter;
import com.laituan.info.CommentsInfo;
import com.laituan.info.ShopInfo;
import com.laituan.model.Model;
import com.laituan.myfragment.login_content_fragment;
import com.laituan.net.ThreadPoolUtils;
import com.laituan.post.Login;
import com.laituan.post.Shopcart;
import com.laituan.thread.HttpGetThread;
import com.laituan.utils.LoadImg;
import com.laituan.utils.LoadImg.ImageDownloadCallBack;
import com.laituan.utils.MyJson;
import com.laituan.utils.MyJson.DetailCallBack;
import com.test.demo.client.util.HttpUtil;
/**
 * 店铺详情模块
 * */
public class ShopDetailsActivity extends Activity {

	private ShopInfo info = null;
	private String url = null;
	private List<ShopInfo> list = new ArrayList<ShopInfo>();
	private LoadImg loadImg;
	private HttpGetThread http = null;
	private SharedPreferences sp;
	private MyJson myJson = new MyJson();
	private ArrayList<CommentsInfo> CommentsList;
	// top和店铺的属性
	private ImageView mShop_details_back, mShop_details_share,
			mShop_details_off, mShop_details_photo, mShop_details_star;
	private TextView mShop_details_name, mShop_details_money;
	// 最下面的导航式按钮
	private LinearLayout mShop_details_bottom_img2,
			mShop_details_bottom_img3;
	// 店铺详情下面的属性
	private RelativeLayout mshop_details_address, mshop_details_phone,
			mshop_details_ding, mshop_details_card, mshop_details_quan,
			mshop_details_tuan;
	private TextView mshop_details_address_txt, mshop_details_phone_txt,
			mshop_details_card_txt, mshop_details_quan_txt,
			mshop_details_tuan_txt;
	private ImageView mshop_details_ding_hui, mshop_details_ding_jiang;
	// 网友推荐的layout
	private RelativeLayout mshop_details_tuijian;
	private TextView mshop_details_tuijian_txt;
	// 点评的layout
	private RelativeLayout mshop_details_dianping;
	private TextView mshop_dianping_top, mshop_details_dianping_name,
			mshop_details_dianping_txt, mshop_details_dianping_time;
	private ImageView mshop_details_dianping_star;
	// 其他信息的layout
	private RelativeLayout mshop_details_qita;
	// 这家店附近的layout
	private TextView mshop_fujin_meishi, mshop_fujin_jingdian,
			mshop_fujin_jiudian, mshop_fujin_quanbu;
	// 其他分店的layout
	private RelativeLayout mshop_details_fendian;
	private TextView mshop_details_fendians_txt;
	// 看过这家店的layout
	private RelativeLayout mshop_details_kanguo;
	// 创建popupWindow
	private View parent;
	private PopupWindow popupWindow;
	String b;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//ListView a=(ListView)findViewById(R.layout.activity_shop_details);
		
		setContentView(R.layout.activity_shop_details);
		// 初始化图片异步加载类
		loadImg = new LoadImg(ShopDetailsActivity.this);
		// 获取从列表当中传递过来的数据
		Intent intent = getIntent();
		b=intent.getStringExtra("activity");
		Log.e("shopdetailbbb",b);
		Bundle bund = intent.getBundleExtra("value");
		info = (ShopInfo) bund.getSerializable("ShopInfo");
		// 查找网络数据
		String endParames = Model.SHOPDETAILURL + "Article/detail/id/" + info.getId();
		http = new HttpGetThread(hand, endParames);
		ThreadPoolUtils.execute(http);
		initView();
		
	}

	private void initView() {
		MyOnClickListener myOnClickListener = new MyOnClickListener();
		Log.e("bbb",b);
	

		// 标题控件
		mShop_details_back = (ImageView) findViewById(R.id.Shop_details_back);
		mShop_details_share = (ImageView) findViewById(R.id.Shop_details_share);
		mShop_details_off = (ImageView) findViewById(R.id.Shop_details_off);
		// 最下面的导航式按钮
		
		mShop_details_bottom_img2 = (LinearLayout) findViewById(R.id.Shop_details_bottom_img2);
		
		mShop_details_bottom_img3 = (LinearLayout) findViewById(R.id.Shop_details_bottom_img3);
		/*if(b.equals("tuangouche"))
		{
			mShop_details_bottom_img3.setVisibility(View.GONE);
		}*/
		//mShop_details_bottom_img3.setVisibility(View.GONE);
		// 店铺信息控件
		mShop_details_name = (TextView) findViewById(R.id.Shop_details_name);
		mShop_details_photo = (ImageView) findViewById(R.id.Shop_details_photo);
		mShop_details_star = (ImageView) findViewById(R.id.Shop_details_star);
		mShop_details_money = (TextView) findViewById(R.id.Shop_details_money);
		// 店铺信息下面的地址，电话，定，卡，券，团控件
		mshop_details_address = (RelativeLayout) findViewById(R.id.shop_details_address);
		mshop_details_phone = (RelativeLayout) findViewById(R.id.shop_details_phone);
		mshop_details_ding = (RelativeLayout) findViewById(R.id.shop_details_ding);
		mshop_details_card = (RelativeLayout) findViewById(R.id.shop_details_card);
		mshop_details_quan = (RelativeLayout) findViewById(R.id.shop_details_quan);
		mshop_details_tuan = (RelativeLayout) findViewById(R.id.shop_details_tuan);
		mshop_details_address_txt = (TextView) findViewById(R.id.shop_details_address_txt);
		mshop_details_phone_txt = (TextView) findViewById(R.id.shop_details_phone_txt);
		mshop_details_card_txt = (TextView) findViewById(R.id.shop_details_card_txt);
		mshop_details_quan_txt = (TextView) findViewById(R.id.shop_details_quan_txt);
		mshop_details_tuan_txt = (TextView) findViewById(R.id.shop_details_tuan_txt);
		mshop_details_ding_hui = (ImageView) findViewById(R.id.shop_details_ding_hui);
		mshop_details_ding_jiang = (ImageView) findViewById(R.id.shop_details_ding_jiang);
		// 网友推荐信息的控件查找
		mshop_details_tuijian = (RelativeLayout) findViewById(R.id.shop_details_tuijian);
		mshop_details_tuijian_txt = (TextView) findViewById(R.id.shop_details_tuijian_txt);
		// 点评
		mshop_details_dianping = (RelativeLayout) findViewById(R.id.shop_details_dianping);
		mshop_dianping_top = (TextView) findViewById(R.id.shop_dianping_top);
		mshop_details_dianping_name = (TextView) findViewById(R.id.shop_details_dianping_name);
		mshop_details_dianping_star = (ImageView) findViewById(R.id.shop_details_dianping_star);
		mshop_details_dianping_txt = (TextView) findViewById(R.id.shop_details_dianping_txt);
		mshop_details_dianping_time = (TextView) findViewById(R.id.shop_details_dianping_time);
		// 其他信息
		mshop_details_qita = (RelativeLayout) findViewById(R.id.shop_details_qita);
		// 在这家店附近
		mshop_fujin_meishi = (TextView) findViewById(R.id.shop_fujin_meishi);
		mshop_fujin_jingdian = (TextView) findViewById(R.id.shop_fujin_jingdian);
		mshop_fujin_jiudian = (TextView) findViewById(R.id.shop_fujin_jiudian);
		mshop_fujin_quanbu = (TextView) findViewById(R.id.shop_fujin_quanbu);
		// 查看其他分店
		mshop_details_fendian = (RelativeLayout) findViewById(R.id.shop_details_fendian);
		mshop_details_fendians_txt = (TextView) findViewById(R.id.shop_details_fendians_txt);
		// 看过这家店的人还看过
		mshop_details_kanguo = (RelativeLayout) findViewById(R.id.shop_details_kanguo);

		// 给控件设置监听
		mShop_details_back.setOnClickListener(myOnClickListener);
//		mShop_details_share.setOnClickListener(myOnClickListener);
//		mShop_details_off.setOnClickListener(myOnClickListener);
//		mShop_details_bottom_img1.setOnClickListener(myOnClickListener);
		mShop_details_bottom_img2.setOnClickListener(myOnClickListener);
		mShop_details_bottom_img3.setOnClickListener(myOnClickListener);
//		mShop_details_bottom_img4.setOnClickListener(myOnClickListener);
//		mShop_details_photo.setOnClickListener(myOnClickListener);
//		mshop_details_address.setOnClickListener(myOnClickListener);
//		mshop_details_phone.setOnClickListener(myOnClickListener);
//		mshop_details_ding.setOnClickListener(myOnClickListener);
//		mshop_details_card.setOnClickListener(myOnClickListener);
//		mshop_details_quan.setOnClickListener(myOnClickListener);
		mshop_details_tuan.setOnClickListener(myOnClickListener);
//		mshop_details_tuijian.setOnClickListener(myOnClickListener);
//		mshop_details_dianping.setOnClickListener(myOnClickListener);
//		mshop_details_qita.setOnClickListener(myOnClickListener);
//		mshop_fujin_meishi.setOnClickListener(myOnClickListener);
//		mshop_fujin_jingdian.setOnClickListener(myOnClickListener);
//		mshop_fujin_jiudian.setOnClickListener(myOnClickListener);
//		mshop_fujin_quanbu.setOnClickListener(myOnClickListener);
//		mshop_details_fendian.setOnClickListener(myOnClickListener);
//		mshop_details_kanguo.setOnClickListener(myOnClickListener);
		
		//赋值给控件
		mShop_details_name.setText(info.getTitle());
		mShop_details_photo.setTag(Model.SHOPLISTIMGURL + info.getCover_path());
		mShop_details_money.setText(info.getPrice());
		mshop_details_address_txt.setText(info.getPosition());

		int slevel = Integer.valueOf( info.getLevel());
		switch (slevel) {
		case 0:
			mShop_details_star.setImageResource(R.drawable.star0);
			break;
		case 1:
			mShop_details_star.setImageResource(R.drawable.star1);
			break;
		case 2:
			mShop_details_star.setImageResource(R.drawable.star2);
			break;
		case 3:
			mShop_details_star.setImageResource(R.drawable.star3);
			break;
		case 4:
			mShop_details_star.setImageResource(R.drawable.star4);
			break;
		case 5:
			mShop_details_star.setImageResource(R.drawable.star5);
			break;
		}
		// 添加商铺的图片的方法
		addImg();
		// 判断是否要显示隐藏团券定卡的控件方法
//		xianshitqdk();
	}

	// 控件的监听事件
	private class MyOnClickListener implements View.OnClickListener {
		public void onClick(View v) {
			int mID = v.getId();
			if (mID == R.id.Shop_details_back) {
				ShopDetailsActivity.this.finish();
			}
			
			if (mID == R.id.Shop_details_bottom_img2) {
				/*sp = getSharedPreferences("userInfo", Activity.MODE_PRIVATE); 
				String username = sp.getString("username", ""); 
		        String password = sp.getString("password", ""); */
				
				/*if(username==null||password==null){
					Toast.makeText(ShopDetailsActivity.this, "请先登录再进行操作", 1).show();*/
				if(login_content_fragment.a==1){
					sp = getSharedPreferences("userInfo", Activity.MODE_PRIVATE); 
					String username = sp.getString("username", ""); 
			        String password = sp.getString("password", "");
					 Login a=new Login(username,password);
				        a.login();
				        Shopcart b=new Shopcart(info.getId(),"1",info.getPrice(),"","",HttpUtil.Buy_URL);
						if(b.shopcart()){
							Toast.makeText(ShopDetailsActivity.this, "入团成功", 1).show();
						}
		    	
				}else{ 
			     
					
					Intent intent=new Intent(ShopDetailsActivity.this,LoginActivity.class);
					startActivity(intent);
				}
				
			}
			if (mID == R.id.Shop_details_bottom_img3) {
				//加入团购车   shopcart/addItem  id num price sort i
				sp = getSharedPreferences("userInfo", Activity.MODE_PRIVATE); 
				String username = sp.getString("username", ""); 
		        String password = sp.getString("password", ""); 
				if(username==""||password==""){
					Toast.makeText(ShopDetailsActivity.this, "请先登录再进行操作", 1).show();   	
				}else{ 
			        Login a=new Login(username,password);
			        a.login();
			        Shopcart b=new Shopcart(info.getId(),"1",info.getPrice(),"","",HttpUtil.Car_URL);
					if(b.shopcart()){
						Toast.makeText(ShopDetailsActivity.this, "加入团购车成功", 1).show();
					}
				}
				/*if(login_content_fragment.a==1){
					sp = getSharedPreferences("userInfo", Activity.MODE_PRIVATE); 
					String username = sp.getString("username", ""); 
			        String password = sp.getString("password", "");
					 Login a=new Login(username,password);
				        a.login();
				        Shopcart b=new Shopcart(info.getId(),"1",info.getPrice(),"","",HttpUtil.Car_URL);
						if(b.shopcart()){
							Toast.makeText(ShopDetailsActivity.this, "入团成功", 1).show();
						}
		    	
				}else{ 
			     
					
					Intent intent=new Intent(ShopDetailsActivity.this,LoginActivity.class);
					startActivity(intent);
				}		*/	
			}
			if(mID == R.id.shop_details_tuan){
				Intent intent = new Intent(ShopDetailsActivity.this,
						TuanDetailsActivity.class);
				Bundle bund = new Bundle();
				bund.putSerializable("ShopInfo", info);
				intent.putExtra("value", bund);
				startActivity(intent);
			}
		}
	}

//	private void creatPopupWindow() {
//		Builder builder = new Builder(ShopDetailsActivity.this);
//		builder.setTitle("报错类型");
//		final String[] items = new String[] { "商户已关闭", "地图位置错误", "商户信息错误",
//				"商户重复", "其他" };
//		DialogInterface.OnClickListener dialog = new DialogInterface.OnClickListener() {
//			public void onClick(DialogInterface arg0, int arg1) {
//				// TODO Auto-generated method stub
//				if (arg1 == DialogInterface.BUTTON_POSITIVE) {
//					arg0.cancel();
//				}
//				switch (arg1) {
//				case 0:
//					Toast.makeText(ShopDetailsActivity.this,
//							"报错信息0:" + items[arg1], 1).show();
//					break;
//				case 1:
//					Toast.makeText(ShopDetailsActivity.this,
//							"报错信息1:" + items[arg1], 1).show();
//					break;
//				case 2:
//					Toast.makeText(ShopDetailsActivity.this,
//							"报错信息2:" + items[arg1], 1).show();
//					break;
//				case 3:
//					Toast.makeText(ShopDetailsActivity.this,
//							"报错信息3:" + items[arg1], 1).show();
//					break;
//				case 4:
//					Toast.makeText(ShopDetailsActivity.this,
//							"报错信息4:" + items[arg1], 1).show();
//					break;
//				}
//			}
//		};
//		builder.setItems(items, dialog);
//		builder.setPositiveButton("取消", dialog);
//		AlertDialog alertDialog = builder.create();
//		alertDialog.show();
//	}

	// 添加图片方法
	private void addImg() {
		mShop_details_photo.setTag(Model.SHOPLISTIMGURL + info.getCover_path());
		Bitmap bit = loadImg.loadImage(mShop_details_photo,
				Model.SHOPLISTIMGURL + info.getCover_path(),
				new ImageDownloadCallBack() {
					public void onImageDownload(ImageView imageView,
							Bitmap bitmap) {
						// 不需要按照tag查找图片，不存在img复用问题
						mShop_details_photo.setImageBitmap(bitmap);
					}
				});
		if (bit != null) {
			mShop_details_photo.setImageBitmap(bit);
		}
	}

	// 线程返回信息
	Handler hand = new Handler() {

		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 404) {
				Toast.makeText(ShopDetailsActivity.this, "找不到地址", 1).show();
			} else if (msg.what == 100) {
				Toast.makeText(ShopDetailsActivity.this, "传输失败", 1).show();
			} else if (msg.what == 200) {
				String result = (String) msg.obj;
				Log.e("result", "result:" + result);
				if (result != null) {
					// 解析数据
					myJson.getShopDetail(result, new DetailCallBack() {

						@Override
						public void getList(
								ArrayList<com.laituan.info.CommentsInfo> CommentsList) {
							// 获取解析回调数据
							ShopDetailsActivity.this.CommentsList = CommentsList;
							// 显示界面
							if (ShopDetailsActivity.this.CommentsList.size() > 0) {
								mshop_details_dianping
										.setVisibility(View.VISIBLE);
								CommentsInfo commentsinfo = CommentsList
										.get(CommentsList.size() - 1);
								mshop_dianping_top.setText("点评(共"
										+ CommentsList.size() + ")条：");
								mshop_details_dianping_name
										.setText(commentsinfo.getName());
								mshop_details_dianping_txt.setText(commentsinfo
										.getComments());
								mshop_details_dianping_time
										.setText(commentsinfo.getTime());
								int slevel = Integer.valueOf(commentsinfo
										.getClevel());
								switch (slevel) {
								case 0:
									mshop_details_dianping_star
											.setImageResource(R.drawable.star0);
									break;
								case 1:
									mshop_details_dianping_star
											.setImageResource(R.drawable.star1);
									break;
								case 2:
									mshop_details_dianping_star
											.setImageResource(R.drawable.star2);
									break;
								case 3:
									mshop_details_dianping_star
											.setImageResource(R.drawable.star3);
									break;
								case 4:
									mshop_details_dianping_star
											.setImageResource(R.drawable.star4);
									break;
								case 5:
									mshop_details_dianping_star
											.setImageResource(R.drawable.star5);
									break;
								}
							}
//							 添加点评信息

						}
					}
			);
				}
			}
		};

	};

	// 知否支持团券定卡然后判断是否显示以及现实修改要显示的文字
	private void xianshitqdk() {
//		mShop_details_name.setText(info.getSname());
//		mShop_details_money.setText(info.getSmoney());
//		mshop_details_address_txt.setText(info.getSaddress());
//		mshop_details_phone_txt.setText(info.getStel());
//
//		int slevel = Integer.valueOf(info.getSlevel());
//		switch (slevel) {
//		case 0:
//			mShop_details_star.setImageResource(R.drawable.star0);
//			break;
//		case 1:
//			mShop_details_star.setImageResource(R.drawable.star1);
//			break;
//		case 2:
//			mShop_details_star.setImageResource(R.drawable.star2);
//			break;
//		case 3:
//			mShop_details_star.setImageResource(R.drawable.star3);
//			break;
//		case 4:
//			mShop_details_star.setImageResource(R.drawable.star4);
//			break;
//		case 5:
//			mShop_details_star.setImageResource(R.drawable.star5);
//			break;
//		}
//
//		if (info.getSflag_tuan().equals("1")) {
//			mshop_details_tuan.setVisibility(View.VISIBLE);
//			mshop_details_tuan_txt.setText("仅售255元后海16号代金券1张");
//		}
//		if (info.getSflag_quan().equals("1")) {
//			mshop_details_quan.setVisibility(View.VISIBLE);
//			mshop_details_quan_txt.setText("四合院美食到店减500，午市集...");
//		}
//		if (info.getSflag_ding().equals("1")) {
//			mshop_details_ding.setVisibility(View.VISIBLE);
//			mshop_details_ding_hui.setVisibility(View.VISIBLE);
//			mshop_details_ding_jiang.setVisibility(View.VISIBLE);
//		}
//		if (info.getSflag_ka().equals("1")) {
//			mshop_details_card.setVisibility(View.VISIBLE);
//			mshop_details_card_txt.setText("会员专享9.5折，免费申请");
//		}
	}

}
