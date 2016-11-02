package com.laituan.utils;

import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.laituan.net.DownBitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

/**
 * 控制图片的加载类
 * 
 * 列表在滑动过程时,没有图片会进行下载,并保存到sdcard与
 * imageCaches 当中去,使用软引用进行封装，如果内存不够时
 * 我们的imageCaches 当中的Bitmap对象会被清理掉,图片被释放掉
 * 再次需要加载的时候，先从1级缓存当中获取，如果没有的话，去
 * 本地获取，本地也获取不到的话，去网络下载。
 * 一级缓存作用：对于listview当中刚刚滑动过的item显示的图片进行保存
 * 二级缓存作用：对于listview当中很久前查看的图片或已经被释放掉图片
 * 进行保存
 * </BR> </BR> By：苦涩 </BR> 联系作者：QQ 534429149
 * */
public class LoadImg {

	//下载图片最大并行线程数
	private static final int Max = 5;
	//图片的一级缓存,保存在我们程序内部
	private Map<String,SoftReference<Bitmap>> imageCaches = null;

	//查看本地缓存工具类
	private FileUtiles fileUtiles;
	//android 提供给我们的一个线程池,使用方便
	private ExecutorService threadPools = null;

	//初始化上面的相关的变量
	public LoadImg(Context ctx){
		imageCaches = new HashMap<String, SoftReference<Bitmap>>();
		fileUtiles = new FileUtiles(ctx);
	}

	//加载图片时，入口
	public Bitmap loadImage(final ImageView imageView,
			final String imageUrl,
			final ImageDownloadCallBack imageDownloadCallBack){
		//imageUrl 由于其唯一型，把他作为我们map当中的key
		//图片名称
		final String filename = imageUrl.substring(imageUrl.lastIndexOf("/")+1,
				imageUrl.length());
		//图片保存到本地时的地址
		String filepath = fileUtiles.getAbsolutePath()+"/"+filename;
		//查找一级缓存，看看是否有这张图片
		//如果map当中有这个key返回一个true
		if(imageCaches.containsKey(imageUrl)){
			//找到对应图片软引用的封装
			SoftReference<Bitmap> soft = imageCaches.get(imageUrl);
			//从软引用当中获取图片
			Bitmap bit = soft.get();
			if(bit != null)
				return bit;
			//从我们的一级缓存（程序内部获取图片）
		}
		//从二级缓存当中获取图片
		if(fileUtiles.isBitmap(filename)){
			Bitmap bit = BitmapFactory.decodeFile(filepath);
			//在二级缓存读取的时候直接添加到一级缓存当中
			imageCaches.put(imageUrl, new SoftReference<Bitmap>(bit));
			return bit;
		}

		//一级缓存，二级缓存都不存在，直接到网络加载
		if(imageUrl != null && !imageUrl.equals("")){
			if(threadPools == null){
				//实例化我们的线程池
				threadPools = Executors.newFixedThreadPool(Max);
			}

			//下载回图片回调Handler
			final Handler hand = new Handler(){
				@Override
				public void handleMessage(Message msg) {
					//如果图片下载成功，并且回调对象不为空时
					if(msg.what == 111 && 
							imageDownloadCallBack != null){
						Bitmap bit = (Bitmap) msg.obj;
						//调用回调自定义适配器的接口方法传递数据
						imageDownloadCallBack.onImageDownload(imageView, bit);
					}
					super.handleMessage(msg);
				}
			};

			//下载图片线程
			Thread thread = new Thread(){
				public void run(){
					//网络下载时的字节流
					InputStream inputStream = DownBitmap.
							getInstance().
							getInputStream(imageUrl);
					//图片压缩为原来的一半
					BitmapFactory.Options op = new BitmapFactory.Options();
					op.inSampleSize = 2;
					Bitmap bit = BitmapFactory.decodeStream(inputStream, null, op);
					if(bit != null){
						//添加到一级缓存当中
						imageCaches.put(imageUrl,new SoftReference<Bitmap>(bit));
						//添加到二级缓存
						fileUtiles.saveBitmap(filename, bit);
						//传递给Handler
						Message msg = hand.obtainMessage();
						msg.what = 111;
						msg.obj = bit;
						hand.sendMessage(msg);
					}
				}
			};

			threadPools.execute(thread);
		}

		return null;
	}


	//通过回调机制设置图片时的接口(类似于Button的Onclick)
	public interface ImageDownloadCallBack{
		//ImageView 你所想要设定的imageview Bitmap 想要设定的图片
		void onImageDownload(ImageView imageView,Bitmap bitmap);
	}

}
