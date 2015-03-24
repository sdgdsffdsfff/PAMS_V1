package com.skynet.app.workflow.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_WFLEVENTACT_TOASSORTER")
public class LEventActToAssorter extends IdEntity {
	@Column
	private String consignercname;

	@Column
	private String consigner;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConsignercname() {
		return consignercname;
	}

	public void setConsignercname(String consignercname) {
		this.consignercname = consignercname;
	}

	public String getConsigner() {
		return consigner;
	}

	public void setConsigner(String consigner) {
		this.consigner = consigner;
	}
}
