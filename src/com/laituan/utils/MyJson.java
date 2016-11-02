package com.laituan.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.laituan.Activity.ShopListActivity;
import com.laituan.info.CommentsInfo;
import com.laituan.info.Set_allorder_0_info;
import com.laituan.info.Set_allorder_detail_info;
import com.laituan.info.Set_allorder_info;
import com.laituan.info.Set_id_list_info;
import com.laituan.info.ShopInfo;

/**
 * Json字符串解析工具类
 * @author 苦涩
 * </BR> </BR> By：苦涩 </BR> 联系作者：QQ 534429149
 */

public class MyJson {
	private ArrayList<CommentsInfo> CommentsList = new ArrayList<CommentsInfo>();

	
	 public ShopInfo getShopInfo(JSONObject job){
	    	Log.e("getshop job",job.toString());
	    	ShopInfo info = new ShopInfo();
	    	try{
	    	info.setRule(job.getString("rule"));
	    	info.setId(job.getString("id"));
	    	Log.e("bbbid", info.getId());
			info.setUid(job.getString("uid"));
			Log.e("bbbuid", info.getUid());
			info.setName(job.getString("name"));
			Log.e("bbbname", info.getName());
			info.setTitle(job.getString("title"));
			Log.e("bbbititle", info.getTitle());
			info.setCategory_id(job.getString("category_id"));
			Log.e("bbbcategoryid", info.getCategory_id());
			info.setDescription(job.getString("description"));
			Log.e("bbbidescription", info.getDescription());
			info.setRoot(job.getString("root"));
			Log.e("bbbroot", info.getRoot());
			info.setPid(job.getString("pid"));
			Log.e("bbbpid", info.getPid());
			info.setModel_id(job.getString("model_id"));
			info.setType(job.getString("type"));
			info.setPosition(job.getString("position"));
			info.setLink_id(job.getString("link_id"));
			info.setCover_id(job.getString("cover_id"));
			Log.e("bbbcoverid", info.getCover_id());
			info.setCover_path(job.getString("cover_path"));
			Log.e("coverpath",job.getString("cover_path"));
			Log.e("bbbcoverpath", info.getCover_path());
			info.setView(job.getString("view"));
			info.setComment(job.getString("comment"));
			info.setExtend(job.getString("extend"));
			info.setLevel(job.getString("level"));
			info.setCreate_time(job.getString("create_time"));
			info.setUpdate_time(job.getString("update_time"));
			info.setStatus(job.getString("status"));
			info.setPrice(job.getString("price"));
			info.setTuan_price(job.getString("tuan_price"));
			info.setQg_price(job.getString("qg_price"));
			info.setMs_price(job.getString("ms_price"));
			info.setTuan_amount(job.getString("tuan_amount"));
			info.setBrand(job.getString("brand"));
			info.setSale(job.getString("sale"));
			info.setPercent(job.getString("percent"));
	    	}catch(JSONException e){
			Log.e("getshopinfo e",e.toString());	
			}
	    	return info;
	    }
		public List<ShopInfo> getList(JSONArray jsonArray) {
			List<ShopInfo> list = null;
			try {				
				list = new ArrayList<ShopInfo>();
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject job = jsonArray.getJSONObject(i);
					Log.e("jobbbbbbbb",job.toString());
					ShopInfo info = new ShopInfo();
					info=getShopInfo(job);
					list.add(info);

				}
			} catch (JSONException e) {
			}
			return list;
		}
		
		// 解析商铺列表
		public List<ShopInfo> getShopList(String value) {
		//public JSONArray getShopList(String value) {
			
				Log.e("getshoplist",value);		
				
				JSONObject jsonObject = null;
				try {
					jsonObject = new JSONObject(value);
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					Log.e("shoploste1",e1.toString());
				}
				
				
			try {
				return  getList(jsonObject.getJSONArray("list"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

	
	// 解析商铺列表
	/*public List<ShopInfo> getShopList(String value) {
		List<ShopInfo> list = null;
		try {				
			list = new ArrayList<ShopInfo>();
			JSONObject jsonObject = new JSONObject(value);
			// 里面有一个数组数据，可以用getJSONArray获取数组
			JSONArray jsonArray = jsonObject.getJSONArray("list");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject job = jsonArray.getJSONObject(i);
				ShopInfo info = new ShopInfo();
				info.setId(job.getString("id"));
				info.setUid(job.getString("uid"));
				info.setName(job.getString("name"));			
				info.setTitle(job.getString("title"));
				info.setCategory_id(job.getString("category_id"));
				info.setDescription(job.getString("description"));
				info.setRoot(job.getString("root"));
				info.setPid(job.getString("pid"));
				info.setModel_id(job.getString("model_id"));
				info.setType(job.getString("type"));
				info.setPosition(job.getString("position"));
				info.setLink_id(job.getString("link_id"));
				info.setCover_id(job.getString("cover_id"));
				info.setCover_path(job.getString("cover_path"));
//				Log.e("bbb", info.getCover_path());
				info.setView(job.getString("view"));
				info.setComment(job.getString("comment"));
				info.setExtend(job.getString("extend"));
				info.setLevel(job.getString("level"));
				info.setCreate_time(job.getString("create_time"));
				info.setUpdate_time(job.getString("update_time"));
				info.setStatus(job.getString("status"));
				info.setPrice(job.getString("price"));
				info.setTuan_price(job.getString("tuan_price"));
				info.setQg_price(job.getString("qg_price"));
				info.setMs_price(job.getString("ms_price"));
				info.setTuan_amount(job.getString("tuan_amount"));
				info.setBrand(job.getString("brand"));
				info.setSale(job.getString("sale"));
				info.setPercent(job.getString("percent"));
				list.add(info);
			}
		} catch (JSONException e) {
		}
		return list;
	}*/

	// 被解析的json以及回调接口实现    
	public void getShopDetail(String mJson, DetailCallBack callback) {
//		try {
//			JSONObject job = new JSONObject(mJson);
//			String result = job.getString("result");
//			Log.e("result", "result:" + result);
//			if (result.equalsIgnoreCase("ok")) {
//				Log.e("result", "result:" + result);
//				String commentsValue = job.getString("comments");
//				JSONArray commentsArray = new JSONArray(commentsValue);
//				
//				for (int i = 0; i < commentsArray.length(); i++) {
//					JSONObject sJob = commentsArray.getJSONObject(i);
//					CommentsInfo info = new CommentsInfo();
//					info.setCid(sJob.getString("cid"));
//					info.setSid(sJob.getString("sid"));
//					info.setPid(sJob.getString("pid"));
//					info.setName(sJob.getString("name"));
//					info.setTime(sJob.getString("time"));
//					info.setComments(sJob.getString("comments"));
//					info.setClevel(sJob.getString("clevel"));
//					info.setKouweilevel(sJob.getString("kouweilevel"));
//					info.setHuanjinglevel(sJob.getString("huanjinglevel"));
//					info.setFuwulevel(sJob.getString("fuwulevel"));
//					info.setCpermoney(sJob.getString("cpermoney"));
//					CommentsList.add(info);
//				}
//				
//				Log.e("result", " CommentsList"
//						+ CommentsList.size());
//				callback.getList(CommentsList);
//			} else {
//				callback.getList( CommentsList);
//			}
//		} catch (JSONException e) {
//			callback.getList( CommentsList);
//		}
	}

	//签到信息的解析
//	public List<SignInfo> getSignList(String value) {
//		List<SignInfo> list = new ArrayList<SignInfo>();
//		try {
//			JSONArray jay = new JSONArray(value);
//			for (int i = 0; i < jay.length(); i++) {
//				JSONObject job = jay.getJSONObject(i);
//				SignInfo info = new SignInfo();
//				info.setName(job.getString("name"));
//				info.setSigncontent(job.getString("signcontent"));
//				info.setSignimage(job.getString("signimage"));
//				info.setSignlevel(job.getString("signlevel"));
//				info.setSigntime(job.getString("signtime"));
//				list.add(info);
//			}
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return list;
//	}
	// 解析set_tuan列表
			public List<Set_allorder_detail_info> getSetList(String value) {
			//public JSONArray getShopList(String value) {
				
							
					
					JSONObject jsonObject = null;
					try {
						jsonObject = new JSONObject(value);
						Log.e("json", jsonObject.toString());
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				
				try {
					return  getSetTuanList(jsonObject.getJSONArray("cartlist"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
			
			public List<Set_allorder_detail_info> getSetTuanList(JSONArray jsonArray) {
				List<Set_allorder_detail_info> list = null;
				try {
					Log.e("fdhgfhf","gjhfgjh");
					Log.e("tuanlistlenth",new Integer(jsonArray.length()).toString());
					list = new ArrayList<Set_allorder_detail_info>();
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject job = jsonArray.getJSONObject(i);
						Set_allorder_detail_info info = new Set_allorder_detail_info();
						info.setId(job.getString("id"));
						Log.e("id", job.getString("id"));
						info.setUid(job.getString("uid"));
						Log.e("uid", job.getString("uid"));
						info.setNum(job.getString("num"));	
						Log.e("num", job.getString("num"));
						info.setGoodid(job.getString("goodid"));
						Log.e("goodid", job.getString("goodid"));
						info.setSort(job.getString("sort"));
						Log.e("sort", job.getString("sort"));
						info.setParameters(job.getString("parameters"));
						Log.e("parameters", job.getString("parameters"));
						Log.e("str",job.getString("good_detail"));
						/*JSONObject jbt=null;
						jbt=new JSONObject(job.getString("good_detail"));
						Log.e("jbt",jbt.toString());
						info.setGooddetail(getShopInfo(jbt));*/
						JSONArray jay=new JSONArray(job.getString("good_detail"));
						info.setGooddetail(getList(jay));
						info.setCreate_time(job.getString("create_time"));
						info.setPrice(job.getString("price"));
						list.add(info);

					}
				} catch (JSONException e) {
					Log.e("settuanerro",e.toString());
				}
				Log.e("listsizetuan",new Integer(list.size()).toString());
				return list;
			}
			public Set_allorder_0_info getSet_allorder_0_info(JSONObject job)
			{
				Set_allorder_0_info info = new Set_allorder_0_info();
				try{
				
				info.setId(job.getString("id"));
				Log.e("id", job.getString("id"));
				info.setGoodid(job.getString("goodid"));
				Log.e("goodid", job.getString("goodid"));
				info.setUid(job.getString("uid"));
				Log.e("uid", job.getString("uid"));
				info.setOrderid(job.getString("orderid"));	
				Log.e("orderid", job.getString("orderid"));
				info.setCover_path(job.getString("cover_path"));
				Log.e("0000000000cover_path", job.getString("cover_path"));
				info.setNum(job.getString("num"));
				Log.e("num", job.getString("num"));
				info.setTag(job.getString("tag"));
				Log.e("tag", job.getString("tag"));
				info.setSort(job.getString("sort"));
				Log.e("sort", info.getSort());
				info.setCreate_time(job.getString("create_time"));
				info.setOrderid(job.getString("orderid"));
				Log.e("orderid", info.getOrderid());
				
				info.setStatus(job.getString("status"));
				Log.e("status", job.getString("status"));
				info.setIscomment(job.getString("iscomment"));
				Log.e("iscomment", job.getString("iscomment"));
				info.setTotal(job.getString("total"));
				Log.e("total", info.getTotal());
				info.setParameters(job.getString("parameters"));
				}catch(JSONException e ){
					Log.e("allorder_list_info",e.toString());
				}
				return info;
			}
			public List<Set_allorder_0_info> getSetAllorder_0_List(JSONArray jsonArray) {
				Log.e("Set_allorder_0_info", "hfdjh");
				List<Set_allorder_0_info> list = null;
				try {
					
					list = new ArrayList<Set_allorder_0_info>();
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject job = jsonArray.getJSONObject(i);
						Set_allorder_0_info info = new Set_allorder_0_info();
						info.setId(job.getString("id"));
						Log.e("id", job.getString("id"));
						info.setGoodid(job.getString("goodid"));
						Log.e("goodid", job.getString("goodid"));
						info.setUid(job.getString("uid"));
						Log.e("uid", job.getString("uid"));
						info.setOrderid(job.getString("orderid"));	
						Log.e("orderid", job.getString("orderid"));
						info.setCover_path(job.getString("cover_path"));
						Log.e("cover_path", job.getString("cover_path"));
						info.setNum(job.getString("num"));
						Log.e("num", job.getString("num"));
						info.setTag(job.getString("tag"));
						Log.e("tag", job.getString("tag"));
						info.setSort(job.getString("sort"));
						Log.e("sort", info.getSort());
						info.setCreate_time(job.getString("create_time"));
						info.setOrderid(job.getString("orderid"));
						Log.e("orderid", info.getOrderid());
						
						info.setStatus(job.getString("status"));
						Log.e("status", job.getString("status"));
						info.setIscomment(job.getString("iscomment"));
						Log.e("iscomment", job.getString("iscomment"));
						info.setTotal(job.getString("total"));
						Log.e("total", info.getTotal());
						info.setParameters(job.getString("parameters"));
						//info.setTuan(job.getJSONArray("id"));
						list.add(info);

					}
				} catch (JSONException e) {
				}
				return list;
			}
			
			// 解析商铺列表
			public List<Set_allorder_0_info> getSet_allorder_0_0_List(String value) {
			//public JSONArray getShopList(String value) {
				
							
					
					JSONObject jsonObject = null;
					try {
						jsonObject = new JSONObject(value);
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Log.e("0",value);
				
				try {
					return  getSetAllorder_0_List(jsonObject.getJSONArray("0"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
			
			// 解析商铺列表
					public List<Set_allorder_0_info> getSet_allorder_0_1_List(String value) {
					//public JSONArray getShopList(String value) {
						
									
							
							JSONObject jsonObject = null;
							try {
								jsonObject = new JSONObject(value);
							} catch (JSONException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							Log.e("1",value);	
						
						try {
							return  getSetAllorder_0_List(jsonObject.getJSONArray("1"));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return null;
					}
					// 解析商铺列表
					public List<Set_allorder_0_info> getSet_allorder_0_2_List(String value) {
					//public JSONArray getShopList(String value) {
						
									
							
							JSONObject jsonObject = null;
							try {
								jsonObject = new JSONObject(value);
							} catch (JSONException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							Log.e("2",value);
						
						try {
							return  getSetAllorder_0_List(jsonObject.getJSONArray("2"));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return null;
					}
					
			@TargetApi(Build.VERSION_CODES.KITKAT)
			public List<Set_allorder_info> getSetAllorderList(JSONArray jsonArray) {
				Integer ii=new Integer(jsonArray.length());
				Log.e("lenth", ii.toString());
				List<Set_allorder_info> list = null;
				try {
					
					list = new ArrayList<Set_allorder_info>();
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject job = jsonArray.getJSONObject(i);
						Set_allorder_info info = new Set_allorder_info();
						//Log.e("addressid", job.toString());
						
						
						info.setAddressid(job.getString("addressid"));
						info.setAassistant(job.getString("assistant"));
						info.setBackinfo(job.getString("backinfo"));
						info.setCodeid(job.getString("codeid"));
						info.setDisplay(job.getString("display"));
						info.setInvoice(job.getString("invoice"));
						info.setInfo(job.getString("info"));
						info.setIsdefault(job.getString("isdefault"));
						info.setIsover(job.getString("isover"));
						info.setMessage(job.getString("message"));
						info.seTool(job.getString("tool"));
						info.setUpdate_time(job.getString("update_time"));
						info.setSend_address(job.getString("send_address"));
						info.setSend_contact(job.getString("send_contact"));
						info.setSend_namee(job.getString("send_name"));
						info.setShipprice(job.getString("shipprice"));
						info.setShipway(job.getString("shipway"));
						info.setStatus(job.getString("status"));
						info.setTotal(job.getString("total"));
						info.setIspay(job.getString("ispay"));
						Log.e("ispay", job.getString("ispay"));
						info.setUid(job.getString("uid"));
						Log.e("uid", job.getString("uid"));
						info.setOrderid(job.getString("orderid"));	
						Log.e("orderid", job.getString("orderid"));
						info.seToolid(job.getString("toolid"));
						Log.e("toolid", job.getString("toolid"));
						info.setScore(job.getString("score"));
						Log.e("score", job.getString("score"));
						info.setTag(job.getString("tag"));
						Log.e("tag", job.getString("tag"));
						info.setCreate_time(job.getString("create_time"));
						info.setPricetotal(job.getString("pricetotal"));
						Log.e("pricetotal", info.getPricetotal());
						
						Log.e("id",job.getString("id"));
						JSONObject jsonObj=null;
						 try {
							jsonObj= new JSONObject(job.getString("id"));
						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}  
						
						info.setIdlist(getSetAllorder_id_List(jsonObj));
						Log.e("idlistendendend",info.getIdlist().toString());
						//info.setIdlist(getSetAllorder_id_List(job.getString("id")));
						/*JSONArray jsonarray = null;
						 try {
							 jsonarray= new JSONArray(job.getJSONArray("cartlist"));
							} catch (JSONException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								Log.e("tuanerror",e1.toString());
							}  
							
							info.setTuan(getSetTuanList(jsonarray));
							Log.e("idtuanlistendendend",info.getTuan().toString());*/
						list.add(info);

					}
				} catch (JSONException e) {
					Log.e("error", e.toString());
				}
				
				return list;
			}
		
			//public List<Set_id_list_info> getSetAllorder_id_List(String value) {
			public List<Set_id_list_info> getSetAllorder_id_List(JSONObject job) {
				Log.e("job", job.toString());
				List<Set_id_list_info> list = null;
				try {
					
					list = new ArrayList<Set_id_list_info>();
					
						Set_id_list_info info = new Set_id_list_info();
						Log.e("idlist_gooddetail",job.getJSONObject("gooddetail").toString());
						info.setGooddetail( getShopInfo(job.getJSONObject("gooddetail")));
						info.setList0( getSet_allorder_0_info(job.getJSONObject("0")));
						Log.e("idlistend",info.toString());
						//info.setList1( getSet_allorder_0_1_List(job.getJSONObject("1").toString()));
						//info.setList2( getSet_allorder_0_2_List(job.getJSONObject("2").toString()));
						//info.setTuan(job.getJSONArray("id"));
						//Log.e("gooddetail uid",info.getGooddetail().get(0).getUid());
						list.add(info);

					
				} catch (JSONException e) {
					Log.e("idlist",e.toString());
				}
				return list;
			}
	public interface DetailCallBack {
		public void getList(ArrayList<CommentsInfo> CommentsList);
	}
}
