package com.laituan.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.laituanba.R;
import com.laituan.adapter.Set_tuanAdapter.Holder;
import com.laituan.adapter.Set_tuanAdapter.onCheckedChanged;
import com.laituan.info.Set_allorder_0_info;
import com.laituan.info.Set_allorder_detail_info;
import com.laituan.info.Set_allorder_info;
import com.laituan.info.Set_id_list_info;
import com.laituan.info.ShopInfo;
import com.laituan.model.Model;
import com.laituan.utils.LoadImg;
import com.laituan.utils.LoadImg.ImageDownloadCallBack;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

/**
 * 所有订单的适配器
 *联系作者：QQ 240814476
 */
public class Set_allorderAdapter extends BaseAdapter {
	private List<Set_allorder_info> list;
	private Context context;
	private LoadImg loadImg;

	public Set_allorderAdapter(List<Set_allorder_info> list, Context context) {
		this.list = list;
		this.context = context;
		// 实例化获取图片的类
		loadImg = new LoadImg(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		Integer t = new Integer(list.size());
		Log.e("count", t.toString());
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(final int arg0, View arg1, ViewGroup arg2) {
		/*
		 * HolderView holderView = null; if (currentView == null) { holderView =
		 * new HolderView(); currentView =
		 * LayoutInflater.from(context).inflate(R.layout.adapter_listview_cart,
		 * null); holderView.tv_num = (TextView)
		 * currentView.findViewById(R.id.tv_num); holderView.cb_choice =
		 * (CheckBox) currentView.findViewById(R.id.cb_choice);
		 * currentView.setTag(holderView); } else { holderView = (HolderView)
		 * currentView.getTag(); } if (arrayList.size() != 0) {
		 * 
		 * holderView.cb_choice.setOnCheckedChangeListener(new
		 * OnCheckedChangeListener() {
		 * 
		 * @Override public void onCheckedChanged(CompoundButton arg0, boolean
		 * choice) { listener.getChoiceData(position, choice); } });
		 * 
		 * 
		 * }
		 */

		final Holder hold;
		if (arg1 == null) {
			hold = new Holder();
			arg1 = View.inflate(context, R.layout.set_order, null);
			hold.mOver = (TextView) arg1.findViewById(R.id.AllorderIsover);
			hold.mComment = (TextView) arg1
					.findViewById(R.id.AllorderIscomment);
			hold.mName = (TextView) arg1.findViewById(R.id.tv_name);
			hold.mNum = (TextView) arg1.findViewById(R.id.tv_num);

			hold.mPrice = (TextView) arg1.findViewById(R.id.tv_price);
			hold.mTime = (TextView) arg1.findViewById(R.id.tv_time);
			// hold.cb_choice = (CheckBox) arg1.findViewById(R.id.cb_choice);
			hold.mImage = (ImageView) arg1
					.findViewById(R.id.tv_adapter_list_pic);
			hold.mAddress = (TextView) arg1.findViewById(R.id.tv_adress);
			hold.mTag = (TextView) arg1.findViewById(R.id.AllorderId);
			hold.mStatus = (TextView) arg1.findViewById(R.id.AllorderIspay);
			hold.list2 = new ArrayList<Set_id_list_info>();
			hold.list3 = new ArrayList<Set_allorder_0_info>();
			// hold.mAddress = (TextView) arg1.findViewById(R.id.tv_adress);
			arg1.setTag(hold);
		} else {
			hold = (Holder) arg1.getTag();
		}
		if (list.get(arg0).geIspay().equals("-1"))
			hold.mStatus.setText("未付款");
		else
			hold.mStatus.setText("已付款");
		if (list.get(arg0).getIsover().equals("-1"))
			hold.mOver.setText("组团未结束");
		else
			hold.mOver.setText("组团结束");

		// hold.mName.setText(list.get(arg0).getT);
		// hold.mComment.setText(list.get(arg0).g);
		// hold.mAssistant.setText(list.get(arg0).getAssistant());
		hold.mTime.setText("创建时间：" + list.get(arg0).getCreate_time());
		hold.mTag.setText(list.get(arg0).getTag());
		hold.mNum.setText("    数量："
				+ list.get(arg0).getIdlist().get(0).getGooddetail().get(0)
						.getSale());
		Integer i = new Integer(arg0);
		if (list.get(arg0).getAddressid() != null)
			Log.e("Address", list.get(arg0).getAddressid());
		else {
			Log.e("wrong", "wrong");
		}
		hold.list2 = list.get(arg0).getIdlist();
		hold.list3 = hold.list2.get(0).getList0();
		if (hold.list3.get(0).getIscomment().equals("-1"))
			hold.mComment.setText("未评论");
		else
			hold.mComment.setText("已评论");
		hold.gooddetail = hold.list2.get(0).getGooddetail();
		hold.mName.setText(hold.gooddetail.get(0).getTitle());
		hold.list2.addAll(list.get(arg0).getIdlist());
		hold.mPrice.setText("价格：￥ " + list.get(arg0).getPricetotal());
		// hold.list3.addAll(hold.list2.get(arg0).getList1());
		hold.mImage.setTag(Model.SHOPLISTIMGURL
				+ hold.list3.get(0).getCover_path());
		Log.e("图片", Model.SHOPLISTIMGURL + hold.list3.get(0).getCover_path());

		// hold.mAddress.setText( hold.list3.get(0).g);

		// 设置默认显示的图片
		hold.mImage.setImageResource(R.drawable.shop_photo_frame);
		// 网络获取图片
		Bitmap bit = loadImg.loadImage(hold.mImage, Model.SHOPLISTIMGURL
				+ hold.list3.get(0).getCover_path(),
				new ImageDownloadCallBack() {
					@Override
					public void onImageDownload(ImageView imageView,
							Bitmap bitmap) {
						// 网络交互时回调进来防止错位
						if (hold.mImage.getTag().equals(
								Model.SHOPLISTIMGURL
										+ hold.list3.get(0).getCover_path())) {
							// 设置网络下载回来图片显示
							hold.mImage.setImageBitmap(bitmap);
						}
					}
				});
		// 从本地获取的
		
		if (bit != null) {
			// 设置缓存图片显示
			hold.mImage.setImageBitmap(bit);
		}
		 

		/*
		 * hold.cb_choice.setOnCheckedChangeListener(new
		 * OnCheckedChangeListener() {
		 * 
		 * @Override public void onCheckedChanged(CompoundButton arg01, boolean
		 * choice) { //listener.getChoiceData(arg0, choice); } });
		 */
		 

		return arg1;
	}

	/*
	 * public class HolderView {
	 * 
	 * private TextView tv_type_color; private TextView tv_num; private CheckBox
	 * cb_choice;
	 * 
	 * }
	 */



	static class Holder {
		TextView mPrice, mTime, mName, mAddress, mTag, mStatus, mAssistant,
				mComment, mOver, mNum;

		ImageView mImage;
		CheckBox cb_choice;
		private List<Set_id_list_info> list2;
		private List<Set_allorder_0_info> list3;
		private List<ShopInfo> gooddetail;
	}

}
