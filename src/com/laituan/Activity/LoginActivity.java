package com.laituan.Activity;

import com.example.laituanba.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 登录模块
 * </BR> </BR> By：苦涩 </BR> 联系作者：QQ 240814476
 * */

public class LoginActivity extends FragmentActivity{

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		initView();
	}

	private void initView() {
		
		MyOnClickLietener myonclick = new MyOnClickLietener();
		
	}

	private class MyOnClickLietener implements View.OnClickListener {
		public void onClick(View arg0) {
			
			
		}
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			LoginActivity.this.finish();
			return true;
		}
		else return false;
		}
}
