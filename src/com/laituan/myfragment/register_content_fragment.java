package com.laituan.myfragment;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.laituan.Activity.RegistrationActivity;
import com.laituan.myfragment.register_content_fragment;
import com.test.demo.client.util.DialogUtil;
import com.test.demo.client.util.HttpUtil;
import com.example.laituanba.R;

import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 注册界面  内容
 *
 */
public class register_content_fragment extends Fragment{
	private ImageView mRegistration_back;
	private EditText mRegistration_user, mRegistration_name,
			mRegistration_password, mRegistration_password2,mIndentify_code;
	private TextView mRegistration_OK;
	private String userNameValue,passwordValue1,passwordValue2,nickNameValue,identifyCode;
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle saveInstanceState)
	{
		View view=inflater.inflate(R.layout.fra_register_content,container,false);
		//mIndentify_code= (EditText) view.findViewById(R.id.text_identify_code);
		mRegistration_user = (EditText) view.findViewById(R.id.Registration_user);
		mRegistration_name = (EditText) view.findViewById(R.id.Registration_name);
		mRegistration_password = (EditText) view.findViewById(R.id.Registration_password);
		mRegistration_password2 = (EditText) view.findViewById(R.id.Registration_password2);
		mRegistration_OK = (TextView) view.findViewById(R.id.Registration_OK);
		mRegistration_OK.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				// TODO Auto-generated method stub
				
					Toast.makeText(register_content_fragment.this.getActivity(),"注册被单击", 1).show();
					identifyCode=mIndentify_code.getText().toString();
					userNameValue = mRegistration_user.getText().toString(); 
					nickNameValue=mRegistration_name.getText().toString(); 
					passwordValue1 = mRegistration_password.getText().toString(); 
					passwordValue2=mRegistration_password2.getText().toString();
	                 if(userNameValue.equals("")|| passwordValue1.equals("")|| passwordValue1.equals(""))
	                 {
	                	 new AlertDialog.Builder(getActivity()).setMessage("请输入账号或密码！").setPositiveButton("OK",null).show();
	                	 return;
	                 }
	                 if(!passwordValue1.equals(passwordValue2))
	                 {
	                	 new AlertDialog.Builder(getActivity()).setMessage("两次输入的密码不一致！").setPositiveButton("OK",null).show();
	                 }
//	                 if(identifyCode.equals(""))
//	                 {
//	                	 new AlertDialog.Builder(getActivity()).setMessage("请获取验证码！").setPositiveButton("OK",null).show();
//	                	 return;
//	                 }
	                 if (loginPro())
						{
	                    	DialogUtil.showDialog(getActivity()
									, "注册成功！", false);
							// 启动Main Activity
//							Intent intent = new Intent(getActivity(), Main.class);
//							startActivity(intent);
							// 结束该Activity
//							getActivity().finish();
						}
	                 }
	                 
					//Intent  intent=new Intent(getActivity(),RegistrationActivity.class);
					//register_content_fragment.this.getActivity().startActivity(intent);
					//Toast.makeText(login_title_fragment.this.getActivity().this, "注册按钮被单击", 1).show();
				
			
		});
		
		return view; 
	}
	
	private boolean loginPro()
	{
		// 获取用户输入的用户名、密码
		 String username = mRegistration_user.getText().toString(); 
		 String nickname=mRegistration_name.getText().toString(); 
		String pwd1 = mRegistration_password.getText().toString();
		String pwd2=mRegistration_password2.getText().toString();
		System.out.println(username);
		System.out.println(nickname);
		System.out.println(pwd1);
		System.out.println(pwd2);
		JSONObject jsonObj;
		try
		{
			jsonObj = register(username,nickname,pwd1,pwd2);
			int a=jsonObj.getInt("status");
			String b=jsonObj.getString("info");
			System.out.println(jsonObj.toString());
			// 如果status 等于1
			if (a==1)
			{
				return true;
			}
			else{
				DialogUtil.showDialog(getActivity(), b, false);
				return false;
			}
		}
		catch (Exception e)
		{
			DialogUtil.showDialog(getActivity(), "服务器响应异常，请稍后再试！", false);
			Log.e("eeeeeeeee",e.toString());
			e.printStackTrace();
		}
		return false;
	}

	// 定义发送请求的方法
	private JSONObject register(String username,String email, String password1,String password2) throws Exception
	{
		// 使用Map封装请求参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", username);
		map.put("password", password1);
		map.put("repassword", password2);
		map.put("email", email);
		
		// 定义发送请求的URL
		String url = HttpUtil.Res_URL ;
		// 发送请求
		return new JSONObject(HttpUtil.postRequest(url, map));
	}
	
	private void setContentView(int activityLogin) {
		// TODO Auto-generated method stub
		
	}

}
