package com.laituan.post;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.test.demo.client.util.DialogUtil;
import com.test.demo.client.util.HttpUtil;

/**
 * 登录post请求
 *
 */
public class Login extends Activity{
	private int id;
	private String username;
	private String password;
	private SharedPreferences sp;
	
	public Login (){
		;
	}
	public Login(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getId()
	{
		return this.id;
	}
	public void setUserName(String name){
		this.username=name;
	}
	public String getUserName(){
		return this.username;
	}
	public boolean login()
	{
		JSONObject jsonObj;
		try
		{
			jsonObj = query(username, password);
			int a=jsonObj.getInt("status");
			System.out.println(jsonObj.toString());
			// 如果status 等于1
			if (a==1)
			{
				return true;
			}
		}
		catch (Exception e)
		{
			DialogUtil.showDialog(Login.this, "服务器响应异常，请稍后再试！", false);
			Log.e("eeeeeeeee",e.toString());
			e.printStackTrace();
		}
		return false;
	}

	// 定义发送请求的方法
	private JSONObject query(String username, String password) throws Exception
	{
		// 使用Map封装请求参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", username);
		map.put("password", password);
		Log.e("anme",username);
		Log.e("word",password);
		// 定义发送请求的URL
		String url = HttpUtil.Log_URL ;
		// 发送请求
		return new JSONObject(HttpUtil.postRequest(url, map));
	}

}
