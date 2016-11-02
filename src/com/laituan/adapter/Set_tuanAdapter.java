package com.laituan.adapter;

import java.util.List;

import com.example.laituanba.R;
import com.laituan.adapter.ShopAdapter.Holder;
import com.laituan.info.Set_allorder_detail_info;
import com.laituan.info.ShopInfo;
import com.laituan.model.Model;
import com.laituan.utils.LoadImg;
import com.laituan.utils.LoadImg.ImageDownloadCallBack;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

/**
 * 我的团购车适配器
 *联系作者：QQ 240814476
 */
public class Set_tuanAdapter extends BaseAdapter {
	private List<Set_allorder_detail_info> list;
	private Context context;
	private LoadImg loadImg;
	private onCheckedChanged listener;
	public Set_tuanAdapter(List<Set_allorder_detail_info>list, Context context) {
		this.list = list;
		this.context = context;
		Log.e("tuanadpter",list.toString());
		// 实例化获取图片的类
		loadImg = new LoadImg(context);
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
		/*HolderView holderView = null;
		if (currentView == null) {
			holderView = new HolderView();
			currentView = LayoutInflater.from(context).inflate(R.layout.adapter_listview_cart, null);
			holderView.tv_num = (TextView) currentView.findViewById(R.id.tv_num);
			holderView.cb_choice = (CheckBox) currentView.findViewById(R.id.cb_choice);
			currentView.setTag(holderView);
		} else {
			holderView = (HolderView) currentView.getTag();
		}
		if (arrayList.size() != 0) {
			
			holderView.cb_choice.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton arg0, boolean choice) {
					listener.getChoiceData(position, choice);
				}
			});
		
		
		}*/
		
		final Holder hold;
		if (arg1 == null) {
			hold = new Holder();
			arg1 = View.inflate(context, R.layout.adapter_listview_cart, null);
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
		hold.mName.setText(list.get(arg0).getGooddetail().get(0).getTitle());
		hold.mNum.setText("+"+list.get(arg0).getNum());
		Log.e("second","getview");
		Log.e("seconddrfdganum",list.get(arg0).getNum().trim());
		hold.mImage.setTag(Model.SHOPLISTIMGURL + list.get(arg0).getGooddetail().get(0).getCover_path());
		hold.mPrice.setText("价格：￥ "+list.get(arg0).getPrice());
		//hold.mAddress.setText("地址： "+list.get(arg0).getGooddetail().get(0).getPosition());
		hold.mAddress.setText("创业大厦");
		hold.mTime.setText(list.get(arg0).getGooddetail().get(0).getCreate_time());

		// 设置默认显示的图片
				hold.mImage.setImageResource(R.drawable.shop_photo_frame);
				// 网络获取图片
				Bitmap bit = loadImg.loadImage(hold.mImage, Model.SHOPLISTIMGURL
						+ list.get(arg0).getGooddetail().get(0).getCover_path(), new ImageDownloadCallBack() {
					@Override
					public void onImageDownload(ImageView imageView, Bitmap bitmap) {
						// 网络交互时回调进来防止错位
						if (hold.mImage.getTag().equals(
								Model.SHOPLISTIMGURL + list.get(arg0).getGooddetail().get(0).getCover_path())) {
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
				/*hold.cb_choice.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton arg01, boolean choice) {
						listener.getChoiceData(arg0, choice);
					}
				});*/
		
		return arg1;
	}

	/*public class HolderView {

		private TextView tv_type_color;
		private TextView tv_num;
		private CheckBox cb_choice;

	}*/
	
public interface onCheckedChanged{
		
		//public void getChoiceData(int position,boolean isChoice);
	}
	public void setOnCheckedChanged(onCheckedChanged listener){
		this.listener=listener;
	}
	static class Holder {
		TextView mNum, mPrice, mTime, mName,mAddress;
		ImageView mImage;
		//CheckBox cb_choice;
	}
}
