package com.skynet.pams.base.pojo;

import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

// 党费使用意见表
@Table("T_APP_PDUSESUGGEST")
public class PartyDueUseSuggest {
	@Name
	private String id; // 标识

	@Column
	private String cno; // 单号

	@Column
	private String cname; // 标题名称
	
	@Column
	private int cyear; // 年度

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

	public int getCyear() {
		return cyear;
	}

	public void setCyear(int cyear) {
		this.cyear = cyear;
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
