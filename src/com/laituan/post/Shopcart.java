package com.laituan.post;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.laituan.model.Model;
import com.test.demo.client.util.DialogUtil;
import com.test.demo.client.util.HttpUtil;

/**
 * 购物车post请求
 *
 */
public class Shopcart extends Activity{
	private String id;
	private String num;
	private String price;
	private String sort;
	private String i;
	private String tag;
	private String url;
	private JSONObject jsonObj;

	public Shopcart(String id, String num, String price, String sort, String i,String url) {
		super();
		this.id = id;
		this.num = num;
		this.price = price;
		this.sort = sort;
		this.i = i;
		this.url = url;
	}

	public boolean shopcart()
	{
		try
		{
			jsonObj = shopcart(id, num,price,sort,i,url);
			int a=jsonObj.getInt("status");
			System.out.println(jsonObj.toString());
			// 如果status 等于1
			if (a==1)
			{	
				if(url==HttpUtil.Buy_URL){
				if(tag()!=null){
				return true;}
				else{
					Log.e("aaaa","tag为空");
				}
				}
				else{
					return true;
				}
			}
		}
		catch (Exception e)
		{
			DialogUtil.showDialog(Shopcart.this, "服务器响应异常，请稍后再试！", false);
			Log.e("aaaa",e.toString());
			e.printStackTrace();
		}
		return false;
	}
	public boolean getTag()
	{
		try
		{
			Log.e("hhhh",this.tag);
			jsonObj = getTag(this.tag);
			Log.e("eeee",jsonObj.toString());
			return true;
		}
		catch (Exception e)
		{
			DialogUtil.showDialog(Shopcart.this, "服务器响应异常，请稍后再试！", false);
			Log.e("bbbb",e.toString());
			e.printStackTrace();
		}
		return false;
	}
	public String tag(){
		String a=null;
		try
		{
			a=jsonObj.getString("tag");
			Log.e("rrrrrr",a);
			this.tag=a;
		}
		catch (Exception e)
		{
			DialogUtil.showDialog(Shopcart.this, "没有订单号！", false);
			Log.e("cccc",e.toString());
			e.printStackTrace();
		}
		
		return a;
	}

	// 定义发送请求的方法
	private JSONObject shopcart(String id, String num, String price, String sort, String i,String url) throws Exception
	{
		// 使用Map封装请求参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("num", num);
		map.put("price", price);
		Log.e("aaaaa",price);
		
		if(url==HttpUtil.Car_URL){
		map.put("i", i);
		}else{
			map.put("parameters",i);
		}
		map.put("sort", sort);
		
		// 发送请求
		return new JSONObject(HttpUtil.postRequest(url, map));
	}
	private JSONObject getTag(String tag) throws Exception
	{
		// 使用Map封装请求参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("tag",tag);
		map.put("score","0");
		map.put("couponcode","");
		map.put("sender","1");
		map.put("PayType","1");
		// 发送请求
		return new JSONObject(HttpUtil.postRequest(Model.HTTPURL+Model.SHOPURL+"/Shopcart/createorder.html", map));
	}


}
