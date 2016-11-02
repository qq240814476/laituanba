package com.laituan.Activity;

import com.example.laituanba.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 *用户基本信息activity
 *联系作者：QQ 240814476
 */
public class UserInfoSimpleActivity extends Activity {
	private TextView mBack;
	private TextView mKeep;
	private EditText mUsername;
	private EditText mNickname;
	private EditText mGender;
	private EditText mMyschool;
	private EditText mMyaim;
	private Button mEditor;
	private Button mEditorPass;
	private Button mEditorNum;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_user_info_simple);
		initview();
	}
	private void initview()
	{
		mUsername=(EditText) findViewById(R.id.user_name);
		mNickname=(EditText) findViewById(R.id.nick_name);
		mGender=(EditText) findViewById(R.id.gender);
		mMyschool=(EditText) findViewById(R.id.my_school);
		mMyaim=(EditText) findViewById(R.id.my_aim);
		Toast.makeText(UserInfoSimpleActivity.this,"个人基本信息", 1).show();
		mKeep=(TextView) findViewById(R.id.keep);
		mBack=(TextView) findViewById(R.id.userinfo_simple_back);
		//mGender.ont
		mUsername.setFocusable(false);
		mNickname.setFocusable(false);
		mGender.setFocusable(false);
		mMyschool.setFocusable(false);
		mMyaim.setFocusable(false);
		mEditor=(Button) findViewById(R.id.edit_info);
		mEditorPass=(Button) findViewById(R.id.edit_password);
		mEditorPass.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				// TODO Auto-generated method stub
				Toast.makeText(UserInfoSimpleActivity.this,"修改密码按钮被单击", 1).show();
				Intent intent=new Intent();
				intent.setClass(UserInfoSimpleActivity.this,EditPasswordActivity.class);
				startActivity(intent);
			}
		});
		mEditorNum=(Button) findViewById(R.id.edit_phone_number);
		mEditorNum.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				// TODO Auto-generated method stub
				Toast.makeText(UserInfoSimpleActivity.this,"修改手机号按钮被单击", 1).show();
				Intent intent=new Intent();
				intent.setClass(UserInfoSimpleActivity.this,EditPhoneNumberMainActivity.class);
				startActivity(intent);
			}
		});
		mEditor.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				// TODO Auto-generated method stub
				
					Toast.makeText(UserInfoSimpleActivity.this,"编辑按钮被单击", 1).show();
					mUsername.setFocusable(true);
					mUsername.setEnabled(true);
					mNickname.setEnabled(true);
					mGender.setEnabled(true);
					mMyschool.setEnabled(true);
					mMyaim.setEnabled(true);
					mUsername.setFocusableInTouchMode(true);
					mNickname.setFocusableInTouchMode(true);
					mGender.setFocusableInTouchMode(true);
					mMyschool.setFocusableInTouchMode(true);
					mMyaim.setFocusableInTouchMode(true);
					
					//Toast.makeText(login_title_fragment.this.getActivity().this, "注册按钮被单击", 1).show();
				
			}
		});
			mBack.setOnClickListener(new OnClickListener(){
				public void onClick(View view) {
					// TODO Auto-generated method stub
					
						Toast.makeText(UserInfoSimpleActivity.this,"返回按钮被单击", 1).show();
						UserInfoSimpleActivity.this.finish();
						//Toast.makeText(login_title_fragment.this.getActivity().this, "注册按钮被单击", 1).show();
					
				}
			});
			mKeep.setOnClickListener(new OnClickListener(){
				public void onClick(View view) {
					// TODO Auto-generated method stub
					
						Toast.makeText(UserInfoSimpleActivity.this,"保存按钮被单击", 1).show();
						mUsername.setFocusable(false);
						mNickname.setFocusable(false);
						mGender.setFocusable(false);
						mMyschool.setFocusable(false);
						mMyaim.setFocusable(false);
					
				}
			});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
