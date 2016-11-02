package com.laituan.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 * 签到墙的自定义ScrollView，用来显示两个listView且listview不会获得焦点
 * 按顺序添加listview中的数据来显示出来
 * @author 苦涩
 *</BR> </BR> By：苦涩 </BR> 联系作者：QQ 534429149
 */

public class MyScrollListView extends ScrollView{


	private ListView left;
	private ListView right;
	private LinearLayout innerLayout;

	public MyScrollListView(Context context,
			ListView left,
			ListView right) {
		super(context);
		this.left = left;
		this.right = right;
		initView(context);
	}

	private void initView(Context context){
		innerLayout = new LinearLayout(context);
		innerLayout.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		innerLayout.setOrientation(LinearLayout.HORIZONTAL);
		setListViewHeightBasedOnChildren(left);
		setListViewHeightBasedOnChildren(right);
		innerLayout.addView(left);
		innerLayout.addView(right);
		addView(innerLayout);
	}

	//获取listview高度(用一条item高度累加)
	public static void setListViewHeightBasedOnChildren(ListView listView)
	{
		//获取listview、适配器
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null)
		{
			return;
		}

		int totalHeight = 0;//listview整体的高度
		//获取适配器中的itemview
		for (int i = 0, len = listAdapter.getCount(); i < len; i++)
		{
			//i:第几个itemview
			//null是否需要创建一个新的view
			//listview哪个viewgroup当中的
			View listItem = listAdapter.getView(i, null, listView);
			//父容器约束
			listItem.measure(0, 0);
			//getHeight()xml在绘制到界面后使用获取高度(有偏差存在父容器约束)
			//getMeasuredHeight() 获取xml实际高度(在你xml加载前可得到)
			totalHeight += listItem.getMeasuredHeight();
		}
		//子view通过LayoutParams来告诉父容器它所需要的宽高
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		//设置子view的权重
		params.weight = 1;
		//把我们listview当中分割线的高度添加上去
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1)) + listView.getPaddingTop() + listView.getPaddingBottom();
		//设置计算好的listview的高度
		listView.setLayoutParams(params);
	}
}
