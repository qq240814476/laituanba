package com.laituan.net;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

/**
 * 我的get请求方式工具类
 * 
 * </BR> </BR> 
 * By：苦涩 </BR> 
 * 联系作者：QQ 534429149
 * */
public class MyGet {

	public String doGet(String url) throws ClientProtocolException, IOException{
		String result = null;//我们的网络交互返回值
		HttpGet myGet = new HttpGet(url);
		HttpClient httpClient = new DefaultHttpClient();
		httpClient.getParams().setIntParameter(
				HttpConnectionParams.CONNECTION_TIMEOUT, 5*1000);
		httpClient.getParams().setIntParameter(
				HttpConnectionParams.SO_TIMEOUT, 30*1000);
		HttpResponse httpResponse = httpClient.execute(myGet);
		if(httpResponse.getStatusLine().getStatusCode() == 200){
			result = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
		}
		return result;
	}

}
