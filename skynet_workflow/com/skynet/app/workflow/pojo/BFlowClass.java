package com.skynet.app.workflow.pojo;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_WFBFLOWCLASS")
public class BFlowClass extends IdEntity {
	@Column
	private String cname; // 分类中文名

	@Column
	private String cclass; // 分类英文名

	@Column
	private String appid; // 应用标识

	@Column
	@ColDefine(width = 2000)
	private String memo; // 备注

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCclass() {
		return cclass;
	}

	public void setCclass(String cclass) {
		this.cclass = cclass;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
