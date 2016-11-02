package com.laituan.info;

/**
 * 点评详情的实体类
 * */

public class CommentsInfo {

	private String cid;
	private String sid;
	private String pid;
	private String name;
	private String comments;
	private String clevel;
	private String kouweilevel;
	private String huanjinglevel;
	private String fuwulevel;
	private String cpermoney;
	private String time;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getClevel() {
		return clevel;
	}

	public void setClevel(String clevel) {
		this.clevel = clevel;
	}

	public String getKouweilevel() {
		return kouweilevel;
	}

	public void setKouweilevel(String kouweilevel) {
		this.kouweilevel = kouweilevel;
	}

	public String getHuanjinglevel() {
		return huanjinglevel;
	}

	public void setHuanjinglevel(String huanjinglevel) {
		this.huanjinglevel = huanjinglevel;
	}

	public String getFuwulevel() {
		return fuwulevel;
	}

	public void setFuwulevel(String fuwulevel) {
		this.fuwulevel = fuwulevel;
	}

	public String getCpermoney() {
		return cpermoney;
	}

	public void setCpermoney(String cpermoney) {
		this.cpermoney = cpermoney;
	}

}
