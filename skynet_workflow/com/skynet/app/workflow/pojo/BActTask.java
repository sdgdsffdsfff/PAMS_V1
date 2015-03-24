package com.skynet.app.workflow.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_WFBACTTASK")
public class BActTask extends IdEntity {
	@Column
	private String actid; // 活动标识

	@Column
	private String descript; // 说明

	@Column
	private String sno; // 任务编号

	@Column
	private String require; // 是否必需

	@Column
	private String ctype; // 任务类型（人工、自动）

	@Column
	private String cname; // 名称

	@Column
	private String apptaskid; // 业务任务标识

	@Column
	private String cclass; // 自动任务代码类名

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getActid() {
		return actid;
	}

	public void setActid(String actid) {
		this.actid = actid;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getRequire() {
		return require;
	}

	public void setRequire(String require) {
		this.require = require;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getApptaskid() {
		return apptaskid;
	}

	public void setApptaskid(String apptaskid) {
		this.apptaskid = apptaskid;
	}

	public String getCclass() {
		return cclass;
	}

	public void setCclass(String cclass) {
		this.cclass = cclass;
	}

}
