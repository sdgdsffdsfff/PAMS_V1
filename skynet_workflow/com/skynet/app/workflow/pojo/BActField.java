package com.skynet.app.workflow.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_WFBACTFIELD")
public class BActField extends IdEntity {
	@Column
	private String actdefid;

	@Column
	private String fieldaccess;

	@Column
	private String fieldid;

	public String getActdefid() {
		return actdefid;
	}

	public void setActdefid(String actdefid) {
		this.actdefid = actdefid;
	}

	public String getFieldaccess() {
		return fieldaccess;
	}

	public void setFieldaccess(String fieldaccess) {
		this.fieldaccess = fieldaccess;
	}

	public String getFieldid() {
		return fieldid;
	}

	public void setFieldid(String fieldid) {
		this.fieldid = fieldid;
	}

}
