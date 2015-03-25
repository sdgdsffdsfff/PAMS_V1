package com.skynet.app.organ.pojo;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_ORGAN")
public class Organ extends IdEntity
{
	private String cno; // 机构部门编号
	
	private String cname; // 机构部门名称
	
	private String shortname; // 机构部门简称

	@ColDefine(width = 500)
	private String allname; // 机构部门完整名称

	private String parentorganid; // 上级标识
	
	private String clevel; // 层次

	private String ctype; // 类型

	private String address; // 地址

	private String email; // 电子邮件

	private String phone; // 电话

	private String ordernum; // 排序号

	private String internal; // 内部编码

	private String memo; // 备注

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getAllname() {
		return allname;
	}

	public void setAllname(String allname) {
		this.allname = allname;
	}

	public String getParentorganid() {
		return parentorganid;
	}

	public void setParentorganid(String parentorganid) {
		this.parentorganid = parentorganid;
	}

	public String getClevel() {
		return clevel;
	}

	public void setClevel(String clevel) {
		this.clevel = clevel;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}

	public String getInternal() {
		return internal;
	}

	public void setInternal(String internal) {
		this.internal = internal;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
   
}
