package com.laituan.utils;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.laituanba.R;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;

/**
 * 正则表达式转换点评信息中图文混排的工具类
 * @author 苦涩
 * </BR> </BR> By：苦涩 </BR> 联系作者：QQ 534429149
 */

public class SmileyParser {
	/*
	 * 单例模式 
	 * 1文字资源，图片资源 
	 * 2.使用正则表达式进行匹配文字 
	 * 3.把edittext当中整体的内容匹配正则表达式一次
	 * 4.SpannableStringBuilder 进行替换
	 */
	private static SmileyParser sInstance;

	public static SmileyParser getInstance() {
		return sInstance;
	}

	public static void init(Context context) {
		sInstance = new SmileyParser(context);
	}

	private final Context mContext;
	private final String[] arrText;
	// 正则表达式
	private final Pattern mPattern;
	// String 图片字符串 Integer表情
	private final HashMap<String, Integer> mSmileyToRes;
	// arrays里面的表情内容
	public static final int DEFAULT_SMILEY_TEXTS = R.array.default_smiley_texts;
	public static final int DEFAULT_SMILEY_NAMES = R.array.default_smiley_names;

	private SmileyParser(Context context) {
		mContext = context;
		// 获取表情文字资源
		arrText = mContext.getResources().getStringArray(DEFAULT_SMILEY_TEXTS);
		// 获取表情ID与表情图标的Map
		mSmileyToRes = buildSmileyToRes();
		// 获取构建的正则表达式
		mPattern = buildPattern();
	}

	// static class Smileys {
	// 表情图片集合
	private static final int[] DEFAULT_SMILEY_RES_IDS = { R.drawable.emoji000,
			R.drawable.emoji001, R.drawable.emoji002, R.drawable.emoji003,
			R.drawable.emoji004, R.drawable.emoji005, R.drawable.emoji006,
			R.drawable.emoji007, R.drawable.emoji008, R.drawable.emoji009,
			R.drawable.emoji010, R.drawable.emoji011, R.drawable.emoji012,
			R.drawable.emoji013, R.drawable.emoji014, R.drawable.emoji015,
			R.drawable.emoji016, R.drawable.emoji017, R.drawable.emoji018,
			R.drawable.emoji019, R.drawable.emoji020, R.drawable.emoji021,
			R.drawable.emoji022, R.drawable.emoji023, R.drawable.emoji024,
			R.drawable.emoji025, R.drawable.emoji026, R.drawable.emoji027,
			R.drawable.emoji101, R.drawable.emoji102, R.drawable.emoji103,
			R.drawable.emoji104, R.drawable.emoji105, R.drawable.emoji106,
			R.drawable.emoji107, R.drawable.emoji108, R.drawable.emoji109,
			R.drawable.emoji110, R.drawable.emoji201, R.drawable.emoji202,
			R.drawable.emoji203, R.drawable.emoji204, R.drawable.emoji205,
			R.drawable.emoji206, R.drawable.emoji207, R.drawable.emoji208,
			R.drawable.emoji209, R.drawable.emoji210, R.drawable.emoji211,
			R.drawable.emoji212, R.drawable.emoji213, R.drawable.emoji214,
			R.drawable.emoji215, R.drawable.emoji216, R.drawable.emoji217,
			R.drawable.emoji218, R.drawable.emoji219, R.drawable.emoji220,
			R.drawable.emoji221, R.drawable.emoji222, R.drawable.emoji301,
			R.drawable.emoji302, R.drawable.emoji303, R.drawable.emoji304,
			R.drawable.emoji305, R.drawable.emoji306, R.drawable.emoji307,
			R.drawable.emoji308, R.drawable.emoji309, R.drawable.emoji310,
			R.drawable.emoji311, R.drawable.emoji312, R.drawable.emoji313,
			R.drawable.emoji314, R.drawable.emoji315, R.drawable.emoji316 };

	/**
	 * 使用HashMap的key-value的形式来影射表情的ID和图片资源
	 * 
	 * @return
	 */
	private HashMap<String, Integer> buildSmileyToRes() {
		if (DEFAULT_SMILEY_RES_IDS.length != arrText.length) {
			throw new IllegalStateException("ID和图片不匹配");
		}
		HashMap<String, Integer> smileyToRes = new HashMap<String, Integer>(
				arrText.length);
		for (int i = 0; i < arrText.length; i++) {
			// 图片名称作为key值，图片资源ID作为value值
			smileyToRes.put(arrText[i], DEFAULT_SMILEY_RES_IDS[i]);
		}
		return smileyToRes;
	}

	/**
	 * 构建正则表达式,用来找到我们所要使用的图片
	 * 
	 * @return
	 */
	private Pattern buildPattern() {
		StringBuilder patternString = new StringBuilder(arrText.length * 3);
		patternString.append('(');
		for (String s : arrText) {
			patternString.append(Pattern.quote(s));
			patternString.append('|');
		}
		patternString.replace(patternString.length() - 1,
				patternString.length(), ")");
		// 把String字符串编译成正则表达式(sad|sad|asd,zhang@163.com)
		// ([调皮]|[调皮]|[调皮])
		return Pattern.compile(patternString.toString());
	}

	/**
	 * 根据文本替换成图片
	 * 
	 * @param text
	 *            对应表情
	 * @return 一个表示图片的序列
	 */
	public CharSequence addSmileySpans(CharSequence text) {
		// 把文字替换为对应图片
		SpannableStringBuilder builder = new SpannableStringBuilder(text);
		// 判断提取工具类（按照正则表达式）
		Matcher matcher = mPattern.matcher(text);
		while (matcher.find()) {
			// 获取对应表情的图片id
			int resId = mSmileyToRes.get(matcher.group());
			// 替换制定字符
			builder.setSpan(new ImageSpan(mContext, resId), matcher.start(),
					matcher.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		return builder;
	}
}
