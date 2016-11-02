package com.laituan.net;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import android.util.Log;

import com.laituan.model.Model;

/**
 * 我的post请求方式工具类
 * 
 * </BR> </BR> 
 * By：苦涩 </BR> 
 * 联系作者：QQ 534429149
 * */

public class MyPost {

	public String doPost(String url,String mycode,String value){
		String result = null;
		HttpResponse httpResponse = null;
		HttpPost post = new HttpPost(Model.HTTPURL+url);
		DefaultHttpClient client = new DefaultHttpClient();
		client.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT,
				30000); // 超时设置
		client.getParams().setIntParameter(
				HttpConnectionParams.CONNECTION_TIMEOUT, 10000);// 连接超时
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		//Json字符串拼接
		nameValuePairs.add(new BasicNameValuePair("mycode", mycode));
		nameValuePairs.add(new BasicNameValuePair("value", value));
		try {
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "utf-8"));
			httpResponse = client.execute(post);
			Log.e("HTTP", "CODE" + httpResponse.getStatusLine().getStatusCode());
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
				Log.e("HTTP", "result:" + result);
			} else {
				result = null;
			}
		} catch (UnsupportedEncodingException e) {
			result = null;
		} catch (ClientProtocolException e) {
			result = null;
		} catch (IOException e) {
			result = null;
		}
		return result;
	}
}
