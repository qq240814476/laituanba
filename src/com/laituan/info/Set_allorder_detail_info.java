package com.laituan.info;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Set_allorder_detail_info implements Serializable {
	private String id;
	private String goodid;
	private String uid;
	private String num;
	private String sort;
	private String parameters;
	private String price;
	private String create_time;
	private List<ShopInfo> gooddetail;
	//private List<ShopInfo> gooddetail=new ArrayList<ShopInfo>();
	
	
/*	public List<ShopInfo> getGooddetail(){
		return gooddetail;
	}
	public void setGooddetail(ShopInfo gooddetail){
		this.gooddetail.add(gooddetail);
	}*/
	public List<ShopInfo> getGooddetail(){
		return gooddetail;
	}
	public void setGooddetail(List<ShopInfo> gooddetail){
		this.gooddetail=gooddetail;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getGoodid() {
		return goodid;
	}
	public void setGoodid(String goodid) {
		this.goodid = goodid;
	}
	
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getParameters() {
		return parameters;
	}
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time=create_time;
	}

}
