package com.skynet.pams.base.pojo;

import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

// 党费使用意见明细表
@Table("T_APP_PDUSESUGGESTDETAIL")
public class PartyDueUseSuggestDetail {
	@Name
	private String id; // 标识
	
	@Column
	private String suggestid; // 意见表标识（主表）

	@Column
	private String deptid; // 部门标识

	@Column
	private String deptname; // 部门名称
	
	@Column
	@ColDefine(type = ColType.DATETIME)
	private Timestamp suggesttime; // 意见填报时间
	
	@Column
	private String suggester; // 意见人用户名
	
	@Column
	private String suggestercname; // 意见人姓名

	@Column
	private String job; // 意见人职务

	@Column
	private String contact; // 联系方式
	
	@Column
	private String email; // 联系邮箱	
	
	@Column
	private String address; // 地址
	
	@Column
	private String suggestion; // 意见
	
	@Column
	@ColDefine(type = ColType.DATETIME)
	private Timestamp createtime; // 创建时间

	@Column
	private String creater; // 创建人用户名

	@Column
	private String creatercname; // 创建人姓名

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSuggestid() {
		return suggestid;
	}

	public void setSuggestid(String suggestid) {
		this.suggestid = suggestid;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public Timestamp getSuggesttime() {
		return suggesttime;
	}

	public void setSuggesttime(Timestamp suggesttime) {
		this.suggesttime = suggesttime;
	}

	public String getSuggester() {
		return suggester;
	}

	public void setSuggester(String suggester) {
		this.suggester = suggester;
	}

	public String getSuggestercname() {
		return suggestercname;
	}

	public void setSuggestercname(String suggestercname) {
		this.suggestercname = suggestercname;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getCreatercname() {
		return creatercname;
	}

	public void setCreatercname(String creatercname) {
		this.creatercname = creatercname;
	}

}
