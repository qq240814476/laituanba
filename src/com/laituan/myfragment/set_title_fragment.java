package com.laituan.myfragment;

import com.example.laituanba.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * 设置界面标题
 *
 */
public class set_title_fragment extends Fragment{
	private TextView mBack;
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle saveInstanceState)
	{
		View view=inflater.inflate(R.layout.fra_set_titile,container,false);
		mBack=(TextView) view.findViewById(R.id.set_back);
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
