package com.laituan.myfragment;

import java.util.HashMap;

import android.preference.PreferenceManager;

import java.util.Map;

import org.json.JSONObject;

import com.example.laituanba.R;
import com.laituan.Activity.FindPasswordActivity;
import com.laituan.Activity.LoginActivity;
import com.laituan.Activity.Main;
import com.laituan.post.Login;
import com.test.demo.client.util.DialogUtil;
import com.test.demo.client.util.HttpUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 *登录界面  内容
 *
 */
public class login_content_fragment extends Fragment{
	public static int a,ID;
	public static String NAME;
	private TextView mForget;
	private TextView mLogin;
	private EditText mUser;
	private EditText mPassword;
	private String userNameValue,passwordValue;  
    private CheckBox rem_pw;
    private CheckBox auto_login;
    private SharedPreferences sp; 
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle saveInstanceState)
	{
		View view=inflater.inflate(R.layout.fra_login_content,container,false); 
		a=-1;
		sp = getActivity().getSharedPreferences("userInfo",  

               getActivity().MODE_PRIVATE);  

        String username = sp.getString("username", ""); 
        String password = sp.getString("password", ""); 
        Log.e(password,"rterter");
        Log.e(username,"fdghfgr");
		mForget=(TextView) view.findViewById(R.id.Login_wangjimima);
		mLogin=(TextView) view.findViewById(R.id.Login_OK);
		mUser=(EditText) view.findViewById(R.id.Login_user);
		mPassword=(EditText) view.findViewById(R.id.Login_password);
		rem_pw=(CheckBox) view.findViewById(R.id.rem_pw);
		auto_login=(CheckBox) view.findViewById(R.id.auto_login);
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
		 if(sp.getBoolean("ISCHECK", false))  
			         {  
			           //设置默认是记录密码状态   
			           rem_pw.setChecked(true);  
			           mUser.setText(sp.getString("username", ""));  
			           mPassword.setText(sp.getString("password", ""));  
			          //判断自动登陆多选框状态   
			          if(sp.getBoolean("AUTO_ISCHECK", false))  
			          {  
			                 //设置默认是自动登录状态   
			                 auto_login.setChecked(true); 
			                 Toast.makeText(login_content_fragment.this.getActivity(),"auto_login is checked", 1).show();
		                //跳转界面   
		                //Intent intent = new Intent(LoginActivity.this,LogoActivity.class);  
			            //     LoginActivity.this.startActivity(intent);  
			                   
			           }  
			         }  

		mForget.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				// TODO Auto-generated method stub
				
					Toast.makeText(login_content_fragment.this.getActivity(),"忘记密码按钮被单击", 1).show();
					Intent  intent=new Intent(getActivity(),FindPasswordActivity.class);
					login_content_fragment.this.getActivity().startActivity(intent);
				
			}
		});
		mLogin.setOnClickListener(new OnClickListener() {  
			  
			            public void onClick(View v) {  
			                userNameValue = mUser.getText().toString();  
			                passwordValue = mPassword.getText().toString();
			                Login b=new Login(userNameValue,passwordValue);
			                 if(userNameValue.equals("")|| passwordValue.equals(""))
			                 {
			                	 DialogUtil.showDialog(getActivity(), "账号或密码不能为空！", false);
			                	 return;
			                 }
			                
			                 else 
			                    {  
			                     //记住用户名、密码、  
			                    Toast.makeText(login_content_fragment.this.getActivity(),"rem_pw is checked!", 1).show();
			                      Editor editor = sp.edit();  
			                      editor.putString("username", userNameValue);  
			                      editor.putString("password",passwordValue);  
			                      editor.commit();  
			                    }
			                    if (b.login())
								{
			                    	DialogUtil.showDialog(getActivity()
											, "登录成功！", false);
			                    	Editor editor = sp.edit(); 
			                    	a=1;
			                    	NAME=userNameValue;
			                    	editor.putString("username", userNameValue);  

			                    	editor.putString("password", passwordValue); 

									// 启动Main Activity
//									Intent intent = new Intent(getActivity(), Main.class);
//									startActivity(intent);
									// 结束该Activity
									getActivity().finish();
								}
								else
								{
									DialogUtil.showDialog(getActivity()
										, "用户名称或者密码错误，请重新输入！", false);
								}
			                    //跳转界面   
			                    //Intent intent = new Intent(LoginActivity.this,LogoActivity.class);  
			                    //LoginActivity.this.startActivity(intent);  
			                    //finish();   
			                      
			               
			                  
			            }  
			        });  
		return view;
	}
	//protected void onCreated(Bundle savedInstanceState)
	//{
		//super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_login);
		//manager=getFragmentManager();
		
	//}
	
	private void setContentView(int activityLogin) {
		// TODO Auto-generated method stub
		
	}
}
