package com.laituan.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

/**
 * 把网络图片保存到本地
 * 1.强引用，正常实例化一个对象。
 * jvm无论内存是否够用系统都不会释放这块内存
 * 2.软引用（softReference）:当我们系统内存不够时，会释放掉
 * 3.弱引用：当我们系统清理内存时发现是一个弱引用对象，直接清理掉
 * 4.虚引用：当我们清理内存时会
 * 把虚引用对象放入一个清理队列当中，
 * 让我们程序员保存当前对象的状态
 * 
 * FileUtiles 作用: 用来向我们的sdcard保存网络接收来的图片
 * </BR> </BR> By：苦涩 </BR> 联系作者：QQ 534429149
 * */
public class FileUtiles {

	private Context ctx;

	public FileUtiles(Context ctx){
		this.ctx = ctx;
	}

	//获取手机在sdcard保存图片的地址
	public String getAbsolutePath(){
		File root = ctx.getExternalFilesDir(null);
		//返回手机端的绝对路径，当我们软件卸载，以及清理缓存时会被清理掉
		if(root != null)
			return root.getAbsolutePath();
		return null;
	}

	//判断图片在本地缓存当中是否存在，如果存在返回一个true
	public boolean isBitmap(String name){
		File root = ctx.getExternalFilesDir(null);
		//file地址拼接
		File file = new File(root,name);
		return file.exists();
	}

	//添加到本地缓存当中
	public void saveBitmap(String name,Bitmap bitmap){
		if(bitmap == null)
			return;
		//如果sdcard不能使用
		if(!Environment.getExternalStorageState().
				equals(Environment.MEDIA_UNMOUNTED)){
			return;
		}
		//拼接图片要保存到sd卡的地址
		String BitPath = getAbsolutePath()+"/"+name;
		//mtn/sdcard/android/com.anjoyo.zhangxinyi/files/
		try {
			FileOutputStream fos = new FileOutputStream(BitPath);
			/**
			 * bitmap.compress把图片通过输出流保存到本地
			 * Bitmap.CompressFormat.JPEG 保存图片的格式
			 * 100 保存到本地的图片质量，需要压缩时适当调整大小
			 * 
			 * */
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
