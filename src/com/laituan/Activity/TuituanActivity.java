package com.laituan.Activity;

import com.example.laituanba.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * 退团activity
 *联系作者：QQ 240814476
 */
public class TuituanActivity extends Activity {
	CheckBox res1,res2,res3,res4;
	EditText resqita;
	LinearLayout tuituan;
	ImageView back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tuituan);
		initView();
	}
	private void initView() {
		res1=(CheckBox)findViewById(R.id.res1);
		res2=(CheckBox)findViewById(R.id.res2);
		res3=(CheckBox)findViewById(R.id.res3);
		res4=(CheckBox)findViewById(R.id.res4);
		resqita=(EditText)findViewById(R.id.resqita);
		tuituan=(LinearLayout)findViewById(R.id.tuituan_bottom);
		back=(ImageView)findViewById(R.id.Shoplist_back);
	}
	
	private class MyOnClickListener implements View.OnClickListener {
		public void onClick(View v) {
			int mID = v.getId();
			if (mID == R.id.Shoplist_back) {
				TuituanActivity.this.finish();
			}if(mID == R.id.tuituan_bottom){
				//入团成功
			}
		}
	}


}
