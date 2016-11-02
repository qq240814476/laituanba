package com.laituan.myfragment;

import com.example.laituanba.R;
import com.laituan.Activity.LoginActivity;
import com.laituan.Activity.RegistrationActivity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 登陆界面标题
 *
 */
public class login_title_fragment extends Fragment {
	private ImageView mBack;
	private TextView mRegister;
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle saveInstanceState)
	{
		View view=inflater.inflate(R.layout.fra_login_title,container,false);
		mBack=(ImageView) view.findViewById(R.id.login_back);
		mRegister=(TextView) view.findViewById(R.id.login_register_go);
		mRegister.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				// TODO Auto-generated method stub
				
					Toast.makeText(login_title_fragment.this.getActivity(),"注册按钮被单击", 1).show();
					Intent  intent=new Intent(getActivity(),RegistrationActivity.class);
					login_title_fragment.this.getActivity().startActivity(intent);
					//Toast.makeText(login_title_fragment.this.getActivity().this, "注册按钮被单击", 1).show();
				
			}
		});
		mBack.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				// TODO Auto-generated method stub
				
					
					login_title_fragment.this.getActivity().finish();
					//Toast.makeText(login_title_fragment.this.getActivity().this, "注册按钮被单击", 1).show();
				
			}
		});
		return view;
	}
	
	
}
