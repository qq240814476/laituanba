package com.laituan.info;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

/**
 * ÁĞ±íÊÊÅäÆ÷
 *
 */
public class Set_id_list_info implements Serializable{
	private List<Set_allorder_0_info> list0=new ArrayList<Set_allorder_0_info>();
	private List<Set_allorder_0_info> list1;
	private List<Set_allorder_0_info> list2;
	private List<ShopInfo> gooddetail=new ArrayList<ShopInfo>();
	
	public List<Set_allorder_0_info> getList1() {
		return list1;
	}
	public void setList1(List<Set_allorder_0_info> list1) {
		this.list1 =list1;
	}
	public List<Set_allorder_0_info> getList0() {
		return list0;
	}
	public void setList0(Set_allorder_0_info list0) {
		this.list0 .add(list0);
	}
	public List<Set_allorder_0_info> getList2() {
		return list2;
	}
	public void setList2(List<Set_allorder_0_info> list2) {
		this.list2 =list2;
	}
	public List<ShopInfo> getGooddetail() {
		return gooddetail;
	}
	public void setGooddetail(ShopInfo list) {
		this.gooddetail.add(list);
	}
	
}
