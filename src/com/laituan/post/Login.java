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
 * ��¼post����
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
			// ���status ����1
			if (a==1)
			{
				return true;
			}
		}
		catch (Exception e)
		{
			DialogUtil.showDialog(Login.this, "��������Ӧ�쳣�����Ժ����ԣ�", false);
			Log.e("eeeeeeeee",e.toString());
			e.printStackTrace();
		}
		return false;
	}

	// ���巢������ķ���
	private JSONObject query(String username, String password) throws Exception
	{
		// ʹ��Map��װ�������
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", username);
		map.put("password", password);
		Log.e("anme",username);
		Log.e("word",password);
		// ���巢�������URL
		String url = HttpUtil.Log_URL ;
		// ��������
		return new JSONObject(HttpUtil.postRequest(url, map));
	}

}
