package com.laituan.myfragment;

import com.example.laituanba.R;
import com.laituan.Activity.Main;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

/**
 *�һ�����������
 *��ϵ���ߣ�QQ 240814476
 */
public class findpass_title_fragment extends Fragment{
	private TextView mMain;
	private TextView mBack;
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle saveInstanceState)
	{
		View view=inflater.inflate(R.layout.fra_findpass_title,container,false);
		mMain=(TextView) view.findViewById(R.id.findpass_main_back);
		mBack=(TextView) view.findViewById(R.id.findpass_back);
		mBack.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				// TODO Auto-generated method stub
				
					Toast.makeText(findpass_title_fragment.this.getActivity(),"���ذ�ť������", 1).show();
					findpass_title_fragment.this.getActivity().finish();
					//Toast.makeText(login_title_fragment.this.getActivity().this, "ע�ᰴť������", 1).show();
				
			}
		});
		mMain.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				// TODO Auto-generated method stub
					Intent  intent=new Intent(getActivity(),Main.class);
					findpass_title_fragment.this.getActivity().startActivity(intent);
					//Toast.makeText(login_title_fragment.this.getActivity().this, "ע�ᰴť������", 1).show();
				
			}
		});
		return view;
	}

}
