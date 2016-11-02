package com.laituan.Activity;

import com.example.laituanba.R;
import com.example.laituanba.R.id;
import com.example.laituanba.R.layout;
import com.example.laituanba.R.menu;

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
 * 编辑电话号码activity
 *联系作者：QQ 240814476
 */
public class EditPhoneNumberMainActivity extends Activity {
	private Button mEdit,mGet;
	private TextView mBack;
	private EditText mOldNumber,mNew_number,mIndentify_code;
	private String oldNumValue,numValue,identifyCode;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_phone_number_main);
		initview();
	}
	private void initview()
	{
		mGet=(Button) findViewById(R.id.get_edit_number_code);
		mGet.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				// TODO Auto-generated method stub
				Toast.makeText(EditPhoneNumberMainActivity.this,"获取验证码被单击", 1).show();
			}
		});
		mBack=(TextView) findViewById(R.id.edit_number_back);
		mBack.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				// TODO Auto-generated method stub
				EditPhoneNumberMainActivity.this.finish();
			}
		});
		mIndentify_code= (EditText) findViewById(R.id.text_edit_number_identify_code);
		mOldNumber= (EditText) findViewById(R.id.OldNumber);
		mNew_number = (EditText) findViewById(R.id.NewNumber);
		mEdit=(Button) findViewById(R.id.ensure_edit_number);
		mEdit.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				// TODO Auto-generated method stub
				
					Toast.makeText(EditPhoneNumberMainActivity.this,"修改手机号被单击", 1).show();
					oldNumValue = mOldNumber.getText().toString();
					numValue = mNew_number.getText().toString(); 
					identifyCode=mIndentify_code.getText().toString();
	                 if(oldNumValue.equals("")||numValue.equals(""))
	                 {
	                	 new AlertDialog.Builder(EditPhoneNumberMainActivity.this).setMessage("请输入手机号码！").setPositiveButton("OK",null).show();
	                	 return;
	                 }
	                
	                 if(identifyCode.equals(""))
	                 {
	                	 new AlertDialog.Builder(EditPhoneNumberMainActivity.this).setMessage("请获取验证码！").setPositiveButton("OK",null).show();
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
