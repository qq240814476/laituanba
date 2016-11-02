package com.laituan.myfragment;

import com.example.laituanba.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 找回密码主界面
 *
 */
public class findpass_content_fragment extends Fragment{
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle saveInstanceState)
	{
		View view=inflater.inflate(R.layout.fra_findpass_content,container,false);
		return view;
	}

}

