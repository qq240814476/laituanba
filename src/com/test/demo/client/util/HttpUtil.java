
package com.test.demo.client.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.util.Log;

import com.laituan.model.Model;

/**
 * �ͻ�������
 *
 */
public class HttpUtil
{
	// ����HttpClient����
	public static HttpClient httpClient = new DefaultHttpClient();
	public static final String Log_URL = 
		Model.HTTPURL+Model.SHOPURL+"/User/login.html";
	public static final String Sear_URL = 
			Model.HTTPURL+Model.SHOPURL+"/Search/index.html";
	public static final String Res_URL = 
			Model.HTTPURL+Model.SHOPURL+"/User/register.html";
	public static final String Per_URL = 
			Model.HTTPURL+Model.SHOPURL+"/center/index.html";
	public static final String Logout_URL=
			Model.HTTPURL+Model.SHOPURL+"/User/logout.html";
		public static final String Car_URL = 
				Model.HTTPURL+Model.SHOPURL+"/shopcart/addItem/index.html";
		public static final String Buy_URL = 
				Model.HTTPURL+Model.SHOPURL+"/Shopcart/order.html";
	/**
	 * 
	 * 
	 * @param url ���������URL
	 * @return ��������Ӧ�ַ���
	 * @throws Exception
	 */
	public static String getRequest(String url)
		throws Exception
	{
		// ����HttpGet����
		HttpGet get = new HttpGet(url);
		// ����GET����
		HttpResponse httpResponse = httpClient.execute(get);
		// ����������ɹ��ط�����Ӧ
		if (httpResponse.getStatusLine()
			.getStatusCode() == 200)
		{
			// ��ȡ��������Ӧ�ַ���
			String result = EntityUtils
				.toString(httpResponse.getEntity());
			return result;
		}
		return null;
	}

	/**
	 * 
	 * @param url ���������URL
	 * @param params �������
	 * @return ��������Ӧ�ַ���
	 * @throws Exception
	 */
	public static String postRequest(String url
		, Map<String ,String> rawParams)throws Exception
	{
		// ����HttpPost����
		HttpPost post = new HttpPost(url);
		// ������ݲ��������Ƚ϶�Ļ����ԶԴ��ݵĲ������з�װ
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for(String key : rawParams.keySet())
		{
			//��װ�������
			params.add(new BasicNameValuePair(key , rawParams.get(key)));
		}
		// �����������
		post.setEntity(new UrlEncodedFormEntity(
			params, "UTF-8"));
		// ����POST����
		HttpResponse httpResponse = httpClient.execute(post);
		
		// ����������ɹ��ط�����Ӧ
		if (httpResponse.getStatusLine()
			.getStatusCode() == 200)
		{
			Log.e("logout",url);
			// ��ȡ��������Ӧ�ַ���
			String result = EntityUtils
				.toString(httpResponse.getEntity());
			Log.e("result11111111",result);
			return result;
		}
		else Log.e("posterror",url);
		return null;
	}
}
