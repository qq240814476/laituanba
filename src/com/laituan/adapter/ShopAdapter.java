package com.laituan.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.example.laituanba.R;
import com.laituan.adapter.Set_tuanAdapter.Holder;
import com.laituan.info.ShopInfo;
import com.laituan.model.Model;
import com.laituan.utils.LoadImg;
import com.laituan.utils.LoadImg.ImageDownloadCallBack;

/**
 * �����б��������
 * @author ��ɬ
 *
 */

public class ShopAdapter extends BaseAdapter {

	private List<ShopInfo> list;
	private Context ctx;
	private LoadImg loadImg;

	public ShopAdapter(List<ShopInfo> list, Context ctx) {
		this.list = list;
		this.ctx = ctx;
		// ʵ������ȡͼƬ����
		loadImg = new LoadImg(ctx);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
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
		final Holder hold;
		
		if (arg1 == null) {
			hold = new Holder();
			//arg1 = View.inflate(ctx, R.layout.item_shop, null);
			arg1 = View.inflate(ctx, R.layout.adapter_listview_cart, null);
			hold.mName = (TextView) arg1.findViewById(R.id.tv_name);
			hold.mNum = (TextView) arg1.findViewById(R.id.tv_num);
			
			hold.mPrice = (TextView) arg1.findViewById(R.id.tv_price);
			hold.mTime = (TextView) arg1.findViewById(R.id.tv_time);
			//hold.cb_choice = (CheckBox) arg1.findViewById(R.id.cb_choice);
			hold.mImage = (ImageView) arg1.findViewById(R.id.tv_adapter_list_pic);
			hold.mAddress = (TextView) arg1.findViewById(R.id.tv_adress);
			arg1.setTag(hold);
		} else {
			hold = (Holder) arg1.getTag();
		}
			/*hold.mTitle = (TextView) arg1.findViewById(R.id.ShopItemTextView);
			hold.mImage = (ImageView) arg1.findViewById(R.id.ShopItemImage);
			hold.mMoney = (TextView) arg1.findViewById(R.id.ShopItemMoney);
			hold.mAddress = (TextView) arg1.findViewById(R.id.ShopItemAddress);
			arg1.setTag(hold);
		} else {
			hold = (Holder) arg1.getTag();
		}*/
		hold.mName.setText(list.get(arg0).getTitle());
		hold.mImage.setTag(Model.SHOPLISTIMGURL + list.get(arg0).getCover_path());
		hold.mPrice.setText("�۸�"+list.get(arg0).getPrice());
		hold.mNum.setText("������"+list.get(arg0).getTuan_amount());
		//hold.mAddress.setText("��ַ��"+list.get(arg0).getPosition());
		hold.mAddress.setText("��ҵ����");
		hold.mTime.setText("����ʱ�䣺 "+list.get(arg0).getCreate_time());
		// ����Ĭ����ʾ��ͼƬ
		hold.mImage.setImageResource(R.drawable.shop_photo_frame);
		// �����ȡͼƬ
		Bitmap bit = loadImg.loadImage(hold.mImage, Model.SHOPLISTIMGURL
				+ list.get(arg0).getCover_path(), new ImageDownloadCallBack() {
			@Override
			public void onImageDownload(ImageView imageView, Bitmap bitmap) {
				// ���罻��ʱ�ص�������ֹ��λ
				if (hold.mImage.getTag().equals(
						Model.SHOPLISTIMGURL + list.get(arg0).getCover_path())) {
					// �����������ػ���ͼƬ��ʾ
					hold.mImage.setImageBitmap(bitmap);
				}
			}
		});
		// �ӱ��ػ�ȡ��
		if (bit != null) {
			// ���û���ͼƬ��ʾ
			hold.mImage.setImageBitmap(bit);
		}

		return arg1;
	}

	static class Holder {
		/*TextView mTitle, mMoney, mAddress, mStytle;
		ImageView mImage, mStar, mTuan, mQuan, mDing, mCard;*/
		TextView mNum, mPrice, mTime, mName,mAddress;
		ImageView mImage;
	}

}
