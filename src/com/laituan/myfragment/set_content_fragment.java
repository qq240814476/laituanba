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
 * ����  ����  ����
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
	                    //���ô�ֱ���� 
	                    
	                }else{ 
	                    //����ˮƽ���� 
	                    
	                } 
	                  
	            } 
	        }); 

		return view;
	}
	/*private void showShare() {
		 ShareSDK.initSDK(getActivity());
		 OnekeyShare oks = new OnekeyShare();
		 //�ر�sso��Ȩ
		 oks.disableSSOWhenAuthorize(); 
		 
		// ����ʱNotification��ͼ�������  2.5.9�Ժ�İ汾�����ô˷���
		 //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
		 // title���⣬ӡ��ʼǡ����䡢��Ϣ��΢�š���������QQ�ռ�ʹ��
		 oks.setTitle(getString(R.string.share));
		 // titleUrl�Ǳ�����������ӣ�������������QQ�ռ�ʹ��
		 oks.setTitleUrl("http://www.baidu.com");
		 // text�Ƿ����ı�������ƽ̨����Ҫ����ֶ�
		 oks.setText("���Ű�--רע�ڴ�ѧ�����ۺ���Ϣƽ̨�����ྫ�ʣ����㷢�֣�");
		 // imagePath��ͼƬ�ı���·����Linked-In�����ƽ̨��֧�ִ˲���
		 oks.setImagePath("/sdcard/test.jpg");//ȷ��SDcard������ڴ���ͼƬ
		 // url����΢�ţ��������Ѻ�����Ȧ����ʹ��
		 oks.setUrl("http://www.baidu.com");
		 // comment���Ҷ�������������ۣ�������������QQ�ռ�ʹ��
		 oks.setComment("���Ű�--רע�ڴ�ѧ�����ۺ���Ϣƽ̨�����ྫ�ʣ����㷢�֣�");
		 // site�Ƿ�������ݵ���վ���ƣ�����QQ�ռ�ʹ��
		 oks.setSite(getString(R.string.app_name));
		 // siteUrl�Ƿ�������ݵ���վ��ַ������QQ�ռ�ʹ��
		 oks.setSiteUrl("http://www.baidu.com");
		 
		// ��������GUI
		 oks.show(getActivity());
		 }*/
	

}
