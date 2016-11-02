package com.laituan.info;

import java.io.Serializable;

public class Set_allorder_0_info implements Serializable{
	private String id;
	private String goodid;
	private String cover_path;
	private String uid;
	private String num;
	private String sort;
	private String parameters;
	private String price;
	private String create_time;
	private String orderid;
	
	private String status;
	private String iscomment;
	private String total;
	private String tag;
	
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag =tag;
	}
	
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total =total;
	}
	
	public String getIscomment() {
		return iscomment;
	}
	public void setIscomment(String iscomment) {
		this.iscomment =iscomment;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status =status;
	}
	
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
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
	public String getCover_path() {
		return cover_path;
	}
	public void setCover_path(String cover_path) {
		this.cover_path = cover_path;
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
