package com.laituan.info;

import java.io.Serializable;
import java.util.List;

/**
 * 订单的实体类
 *
 */
public class Set_allorder_info implements Serializable{
	private String orderid;
	private String tag;
	private String pricetotal;
	private String create_time;
	private String status;
	private String update_time;
	private String uid;
	private String shipprice;
	private String assistant;
	private List<Set_allorder_detail_info> tuan;
	private List<Set_id_list_info> idlist; 
	private String display;
	private String isover;
	private String ispay;
	private String tool;
	private String total;
	private String addressid;
	
	private String toolid;
	private String isdefault;
	private String info;
	private String shipway;
	private String invoice;
	private String message;
	
	private String backinfo;
	private String score;
	private String codeid;
	private String send_name;
	private String send_contact;
	private String send_address;
	
	public List<Set_allorder_detail_info> getTuan() {
		return tuan;
	}
	public void setTuan(List<Set_allorder_detail_info> tuan) {
		this.tuan = tuan;
	}
	
	public List<Set_id_list_info> getIdlist() {
		return idlist;
	}
	public void setIdlist(List<Set_id_list_info> idlist) {
		this.idlist =idlist;
	}
	
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	
	
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag =tag;
	}
	public String getPricetotal() {
		return pricetotal;
	}
	public void setPricetotal(String pricetotal) {
		this.pricetotal = pricetotal;
	}
	
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.tag =status;
	}
	public String getAssistant() {
		return assistant;
	}
	public void setAassistant(String assistant) {
		this.assistant = assistant;
	}
	
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time =update_time;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display =display;
	}
	public String getShipprice() {
		return shipprice;
	}
	public void setShipprice(String shipprice) {
		this.shipprice = shipprice;
	}
	
	public String getIsover() {
		return isover;
	}
	public void setIsover(String isover) {
		this.isover =isover;
	}
	public String geIspay() {
		return ispay;
	}
	public void setIspay(String ispay) {
		this.ispay = ispay;
	}
	
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total =total;
	}
	public String geTool() {
		return tool;
	}
	public void seTool(String tool) {
		this.tool = tool;
	}
	
	public String getAddressid() {
		return addressid;
	}
	public void setAddressid(String addressid) {
		this.addressid =addressid;
	}
	public String geToolid() {
		return toolid;
	}
	public void seToolid(String toolid) {
		this.toolid = toolid;
	}
	
	public String getIsdefault() {
		return isdefault;
	}
	public void setIsdefault(String isdefault) {
		this.isdefault =isdefault;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	public String getShipway() {
		return shipway;
	}
	public void setShipway(String shipway) {
		this.shipway =shipway;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message =message;
	}
	public String getBackinfo() {
		return backinfo;
	}
	public void setBackinfo(String backinfo) {
		this.backinfo = backinfo;
	}

	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score =score;
	}
	public String getCodeid() {
		return codeid;
	}
	public void setCodeid(String codeid) {
		this.codeid = codeid;
	}
	

	public String getSend_name() {
		return send_name;
	}
	public void setSend_namee(String send_name) {
		this.send_name =send_name;
	}
	public String getSend_contact() {
		return send_contact;
	}
	public void setSend_contact(String send_contact) {
		this.send_contact = send_contact;
	}
	
	public String getSend_address() {
		return send_address;
	}
	public void setSend_address(String send_address) {
		this.send_address = send_address;
	}
}
