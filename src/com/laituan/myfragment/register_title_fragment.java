package com.laituan.myfragment;

import com.example.laituanba.R;
import com.laituan.Activity.LoginActivity;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 注册标题界面
 *
 */
public class register_title_fragment extends Fragment {
	private TextView mBack;
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle saveInstanceState)
	{
		View view=inflater.inflate(R.layout.fra_register_title,container,false);
		Toast.makeText(register_title_fragment.this.getActivity(),"注册", 1).show();
		mBack=(TextView) view.findViewById(R.id.register_back);
		mBack.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				// TODO Auto-generated method stub
				getActivity().finish();
//					Toast.makeText(register_title_fragment.this.getActivity(),"返回首页按钮被单击", 1).show();
//					Intent  intent=new Intent(getActivity(),MainActivity.class);
//					register_title_fragment.this.getActivity().startActivity(intent);
					//Toast.makeText(login_title_fragment.this.getActivity().this, "注册按钮被单击", 1).show();
				
			}
		});
		return view;
	}
	
	
}

