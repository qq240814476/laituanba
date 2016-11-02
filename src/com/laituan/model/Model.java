package com.laituan.model;

import android.widget.Toast;

import com.example.laituanba.R;
import com.laituan.Activity.ShopListActivity;

/**
 * 静态变量的存放
 * </BR> </BR> 
 * By：苦涩 </BR> 
 * 联系作者：QQ 534429149
 * 联系作者：QQ 240814476
 * */

public class Model {

	// 网络交互地址前段
	public static String HTTPURL = "http://49.141.192.216/yershop/";
	// 店铺获取地址
	public static String SHOPURL = "index.php?s=/Home";
	// 签到获取地址
	public static String SELECTSIGNURL = "sign.php";
	// 店铺列表图片前段地址,没有最后/
	public static String SHOPLISTIMGURL = "http://49.141.192.216/yershop/";
	// 团购获取地址
	public static String TUANURL = "tuan.php?";
	// 添加签到地址
	public static String SIGNURL = "addsign.php";
	// 店铺详情地址
	public static String SHOPDETAILURL = "index.php?s=/Home/";
	// 第一个listview的图片数据数组
	public static int[] LISTVIEWIMG = new int[] {
			R.drawable.ic_category_2147483648, R.drawable.ic_category_10,
			R.drawable.ic_category_20, R.drawable.ic_category_30,
			R.drawable.ic_category_45, R.drawable.ic_category_50,
			R.drawable.ic_category_55, R.drawable.ic_category_60,
			R.drawable.ic_category_65, R.drawable.ic_category_70,
			R.drawable.ic_category_80, R.drawable.ic_category_none };
	// 第一个listview的文本数据数组
	public static String[] LISTVIEWTXT = new String[] { "热门分类", "美食", "购物",
			"休闲娱乐", "运动健身", "丽人", "结婚", "酒店", "爱车", "亲子", "生活服务", "家装" };
	// 第二个listview的文本数据
	public static String[][] MORELISTTXT = {
			{ "全部分类", "小吃快餐", "咖啡厅", "电影院", "KTV", "茶馆", "足疗按摩", "超市/便利店",
					"银行", "经济型酒店", "景点/郊游", "公园", "美发" },
			{ "全部美食", "小吃快餐", "西餐", "火锅", "北京菜", "川菜", "日本", "面包甜点", "粤菜",
					"韩国料理", "自助餐", "浙江菜", "云南菜", "湘菜", "东南亚菜", "西北菜", "鲁菜",
					"东北菜", "素菜", "新疆菜", "海鲜", "清真菜", "贵州菜", "湖北菜", "其他" },
			{ "全部购物", "综合商场", "服饰鞋包", "超市/便利店", "特色集市", "品牌折扣店", "眼镜店", "珠宝饰品",
					"化妆品", "运动户外", "食品茶酒", "书店", "数码产品", "药店", "京味儿购物", "亲子购物",
					"花店", "家具建材", "更多购物场所" },
			{ "全部休闲娱乐", "咖啡厅", "KTV", "景点/郊游", "电影院", "酒吧", "公园", "温泉", "文化艺术",
					"足疗按摩", "洗浴", "茶馆", "游乐游艺", "密室", "采摘/农家乐", "桌面游戏", "台球馆",
					"DIY手工坊", "休闲网吧", "真人CS", "棋牌室", "轰趴馆", "私人影院", "更多休闲娱乐" },
			{ "全部运动健身", "健身中心", "游泳馆", "瑜伽", "羽毛球馆", "台球馆", "舞蹈", "体育场馆",
					"高尔夫场", "网球场", "武术场馆", "篮球场", "保龄球馆", "足球场", "乒乓球馆",
					"更多体育运动" },
			{ "全部丽人", "美发", "美容/SPA", "齿科", "美甲", "化妆品", "瑜伽", "瘦身纤体", "舞蹈",
					"个性写真", "整形" },
			{ "全部结婚", "婚纱摄影", "婚宴酒店", "婚纱礼服", "婚庆公司", "婚戒首饰", "个性写真", "彩妆造型",
					"婚礼小礼品", "婚礼跟拍", "婚车租赁", "司仪主持", "婚房装修", "更多婚礼服务" },
			{ "全部酒店", "经济型酒店", "五星级酒店", "度假村", "四星级酒店", "三星级酒店", "农家院",
					"公寓式酒店", "青年旅社", "精品酒店", "更多酒店住宿" },
			{ "全部爱车", "维修保养", "驾校", "停车场", "4S店/汽车销售", "加油站", "配件/车饰", "汽车租赁",
					"汽车保险" },
			{ "全部亲子", "亲子摄影", "幼儿教育", "亲子游乐", "孕产护理", "亲子购物", "更多亲子服务" },
			{ "全部生活服务", "医院", "银行", "齿科", "宠物", "培训", "快照/冲印", "学校", "旅行社",
					"购物网站", "干洗店", "家政", "奢侈品护理", "商务楼", "小区", "更多生活服务" },
			{ "全部家装", "家具家装", "家用电器", "建材", "家装卖场", "装修设计" } };
	// shoplist中toplist文本
	public static String[] SHOPLIST_TOPLIST = new String[] { "全部商户", "四六级",
			"驾校", "健身", "小语种", "其他" };
	// shoplist中排序文本
	public static String[] SHOPLIST_THREELIST = { "默认排序", "距离最近", "人气最高",
			"评价最好", "口味最佳", "环境最雅", "服务最好", "费用最低", "费用最高" };
	// shoplist中商区文本
	public static String[] SHOPLIST_PLACE = new String[] { "附近", "全城热门商区",
			"道里区", "道外区", "南岗区", "香坊区", "平房区", "松北区", "呼兰区", "近郊" };
	// 美食全部地区数组2
	public static String[][] SHOPLIST_PLACESTREET = new String[][] {
			{ "500米", "1000米", "2000米", "5000米" },
			{ "全部商区", "中央大街", "开发区", "秋林", "哈工大", "菜艺街", "爱建社区", "芦家街/宣化街",
					"新阳路", "学府路", "三大动力路", "革新街", "江畔景区", "南极区", "和兴路沿线",
					"哈尔滨东站", "群力地区", "军工院", "阿城区", },
			{ "全部道里区", "中央大街", "爱建社区", "新阳路", "群力地区", "顾乡" },
			{ "全部道外区", "江畔景区", "南极街", "哈尔滨东站", "太平桥", "靖宇街沿线", "宏伟路" },
			{ "全部南岗区", "开发区", "秋林", "哈工大", "芦家街/宣化街", "学府路", "革新街", "和兴路沿线",
					"军工院", "哈尔滨站", "哈西大街", },
			{ "全部香坊区", "菜艺街", "三大动力路", "民生路", "木材街", "安埠街", },
			{ "全部平房区", "新疆大街", },
			{ "全部松北区", "太阳岛", "世茂大道", "中源大道", },
			{ "全部呼兰区", "学院路", },
			{ "全部近郊", "阿城区", "尚志市", "五常市", "宾县", "方正县", "延寿县", "双城市", "通河县",
					"巴彦县", "木兰县", "依兰县", } };
	// 排行榜中toplist数据
	public static String[] PAIHANGBANG_TOPLIST = new String[] { "餐厅排行榜",
			"休闲娱乐排行榜", "购物排行榜", "丽人排行榜", "运动健身排行榜", "酒店排行榜", "生活服务排行榜" };
	// 表情的数据
	public static int image1[] = { R.drawable.emoji000, R.drawable.emoji001,
			R.drawable.emoji002, R.drawable.emoji003, R.drawable.emoji004,
			R.drawable.emoji005, R.drawable.emoji006, R.drawable.emoji007,
			R.drawable.emoji008, R.drawable.emoji009, R.drawable.emoji010,
			R.drawable.emoji011, R.drawable.emoji012, R.drawable.emoji013,
			R.drawable.emoji014, R.drawable.emoji015, R.drawable.emoji016,
			R.drawable.emoji017, R.drawable.emoji018, R.drawable.emoji019,
			R.drawable.ic_key_delete_normal };

	public static int image2[] = { R.drawable.emoji020, R.drawable.emoji021,
			R.drawable.emoji022, R.drawable.emoji023, R.drawable.emoji024,
			R.drawable.emoji025, R.drawable.emoji026, R.drawable.emoji027,
			R.drawable.emoji101, R.drawable.emoji102, R.drawable.emoji103,
			R.drawable.emoji104, R.drawable.emoji105, R.drawable.emoji106,
			R.drawable.emoji107, R.drawable.emoji108, R.drawable.emoji109,
			R.drawable.emoji110, R.drawable.emoji201, R.drawable.emoji202,
			R.drawable.ic_key_delete_normal };
	public static int image3[] = { R.drawable.emoji203, R.drawable.emoji204,
			R.drawable.emoji205, R.drawable.emoji206, R.drawable.emoji207,
			R.drawable.emoji208, R.drawable.emoji209, R.drawable.emoji210,
			R.drawable.emoji211, R.drawable.emoji212, R.drawable.emoji213,
			R.drawable.emoji214, R.drawable.emoji215, R.drawable.emoji216,
			R.drawable.emoji217, R.drawable.emoji218, R.drawable.emoji219,
			R.drawable.emoji220, R.drawable.emoji221, R.drawable.emoji222,
			R.drawable.ic_key_delete_normal };
	public static int image4[] = { R.drawable.emoji301, R.drawable.emoji302,
			R.drawable.emoji303, R.drawable.emoji304, R.drawable.emoji305,
			R.drawable.emoji306, R.drawable.emoji307, R.drawable.emoji308,
			R.drawable.emoji309, R.drawable.emoji310, R.drawable.emoji311,
			R.drawable.emoji312, R.drawable.emoji313, R.drawable.emoji314,
			R.drawable.emoji315, R.drawable.emoji316,
			R.drawable.ic_key_delete_normal };

}
