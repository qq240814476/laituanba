package com.laituan.Activity;

import com.example.laituanba.R;
import com.example.laituanba.R.id;
import com.example.laituanba.R.layout;
import com.example.laituanba.R.menu;
import com.laituan.myfragment.register_content_fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 输入密码activity
 *联系作者：QQ 240814476
 */
public class EditPasswordActivity extends Activity {
	private Button mEdit,mGet; 
	private TextView mBack;
	private EditText mOldPass,mNew_password, mNew_password2,mIndentify_code;
	private String oldPasswordValue,passwordValue1,passwordValue2,identifyCode;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_password);
		initview();
	}
	private void initview()
	{
		mBack=(TextView) findViewById(R.id.edit_pass_back);
		mBack.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				// TODO Auto-generated method stub
				EditPasswordActivity.this.finish();
			}
		});
		mIndentify_code= (EditText) findViewById(R.id.text_edit_identify_code);
		mGet=(Button) findViewById(R.id.get_edit_code);
		mGet.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				// TODO Auto-generated method stub
				Toast.makeText(EditPasswordActivity.this,"获取验证码被单击", 1).show();
			}
		});
		mOldPass= (EditText) findViewById(R.id.OldPass);
		mNew_password = (EditText) findViewById(R.id.New_password);
		mNew_password2 = (EditText) findViewById(R.id.New_password2);
		mEdit=(Button) findViewById(R.id.ensure_edit_password);
		mEdit.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				// TODO Auto-generated method stub
				
					Toast.makeText(EditPasswordActivity.this,"注册被单击", 1).show();
					oldPasswordValue = mOldPass.getText().toString();
					passwordValue1 = mNew_password.getText().toString(); 
					passwordValue2=mNew_password2.getText().toString();
					identifyCode=mIndentify_code.getText().toString();
	                 if(oldPasswordValue.equals("")||passwordValue1.equals("")|| passwordValue1.equals(""))
	                 {
	                	 new AlertDialog.Builder(EditPasswordActivity.this).setMessage("请输入密码！").setPositiveButton("OK",null).show();
	                	 return;
	                 }
	                 if(!passwordValue1.equals(passwordValue2))
	                 {
	                	 new AlertDialog.Builder(EditPasswordActivity.this).setMessage("两次输入的密码不一致！").setPositiveButton("OK",null).show();
	                 }
	                 if(identifyCode.equals(""))
	                 {
	                	 new AlertDialog.Builder(EditPasswordActivity.this).setMessage("请获取验证码！").setPositiveButton("OK",null).show();
	                	 return;
	                 }
	                 }
					//Intent  intent=new Intent(getActivity(),RegistrationActivity.class);
					//register_content_fragment.this.getActivity().startActivity(intent);
					//Toast.makeText(login_title_fragment.this.getActivity().this, "注册按钮被单击", 1).show();
				
			
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
