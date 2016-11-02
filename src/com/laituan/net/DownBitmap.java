package com.laituan.net;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/**
 * 下载服务器网络图片
 * */
public class DownBitmap {

	private DownBitmap(){}
	private static DownBitmap my = null;

	public static synchronized DownBitmap getInstance(){
		if(my == null)
			my = new DownBitmap();
		return my;
	} 

	//获取网络图片下载时返回的流
	public InputStream getInputStream(String Biturl){
		//使用get请求方式获取图片资源
		HttpGet get = new HttpGet(Biturl);
		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 5*1000);
		HttpConnectionParams.setSoTimeout(httpParams, 30*1000);
		HttpClient httpClient = new DefaultHttpClient(httpParams);
		try {
			HttpResponse hr = httpClient.execute(get);
			if(hr.getStatusLine().getStatusCode() == 200){
				return hr.getEntity().getContent();//得到服务器返回的输入流
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//建立response连接从而方便获取状态吗


		return null;
	}

}
