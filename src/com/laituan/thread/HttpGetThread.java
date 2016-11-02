package com.laituan.thread;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import android.os.Handler;
import android.os.Message;

import com.laituan.model.Model;
import com.laituan.net.MyGet;

/**
 * 网络Get请求的线程
 * 
 * </BR> </BR> By：苦涩 </BR> 联系作者：QQ 534429149
 * */
public class HttpGetThread implements Runnable {

	private Handler hand;
	private String url;
	private MyGet myGet = new MyGet();

	public HttpGetThread(Handler hand, String endParamerse) {
		this.hand = hand;
		// 拼接访问服务器完整的地址
		url = Model.HTTPURL + endParamerse;
	}

	@Override
	public void run() {
		// 获取我们回调主ui的message
		Message msg = hand.obtainMessage();
		try {
			String result = myGet.doGet(url);
			msg.what = 200;
			msg.obj = result;
		} catch (ClientProtocolException e) {
			msg.what = 404;
		} catch (IOException e) {
			msg.what = 100;
		}
		// 给主ui发送消息传递数据
		hand.sendMessage(msg);
	}
}
