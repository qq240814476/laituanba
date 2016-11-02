package com.laituan.myfragment;

/*import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;*/

import com.example.laituanba.R;
import com.laituan.Activity.AboutusActivity;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * 设置  界面  内容
 *
 */
public class set_content_fragment extends Fragment{
	private TextView mAboutus;
	private TextView mShare;
	private ToggleButton mRecom;
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle saveInstanceState)
	{
		View view=inflater.inflate(R.layout.fra_set_content,container,false);
		mShare=(TextView) view.findViewById(R.id.text_share);
		mAboutus=(TextView) view.findViewById(R.id.text_about_us);
		mRecom=(ToggleButton) view.findViewById(R.id.recommended);
		mAboutus.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				// TODO Auto-generated method stub
					Intent  intent=new Intent(getActivity(),AboutusActivity.class);
					getActivity().startActivity(intent);
				
			}
		});
		mShare.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				// TODO Auto-generated method stub
				//showShare();
				
			}
		});
		mRecom.setOnCheckedChangeListener(new OnCheckedChangeListener() { 
             
	            @Override
	            public void onCheckedChanged(CompoundButton arg0, boolean arg1) { 
	                if(arg1){ 
	                    //设置垂直布局 
	                    
	                }else{ 
	                    //设置水平布局 
	                    
	                } 
	                  
	            } 
	        }); 

		return view;
	}
	/*private void showShare() {
		 ShareSDK.initSDK(getActivity());
		 OnekeyShare oks = new OnekeyShare();
		 //关闭sso授权
		 oks.disableSSOWhenAuthorize(); 
		 
		// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
		 //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
		 // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		 oks.setTitle(getString(R.string.share));
		 // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		 oks.setTitleUrl("http://www.baidu.com");
		 // text是分享文本，所有平台都需要这个字段
		 oks.setText("来团吧--专注于大学生的综合信息平台，更多精彩，等你发现！");
		 // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		 oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
		 // url仅在微信（包括好友和朋友圈）中使用
		 oks.setUrl("http://www.baidu.com");
		 // comment是我对这条分享的评论，仅在人人网和QQ空间使用
		 oks.setComment("来团吧--专注于大学生的综合信息平台，更多精彩，等你发现！");
		 // site是分享此内容的网站名称，仅在QQ空间使用
		 oks.setSite(getString(R.string.app_name));
		 // siteUrl是分享此内容的网站地址，仅在QQ空间使用
		 oks.setSiteUrl("http://www.baidu.com");
		 
		// 启动分享GUI
		 oks.show(getActivity());
		 }*/
	

}
