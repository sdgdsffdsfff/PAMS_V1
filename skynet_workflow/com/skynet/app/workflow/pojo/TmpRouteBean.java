package com.skynet.app.workflow.pojo;

import org.nutz.dao.entity.annotation.Column;

public class TmpRouteBean {
	@Column
	private String oldid;

	@Column
	private String newid;

	public TmpRouteBean(String oldid, String newid) {
		this.oldid = oldid;
		this.newid = newid;
	}

	public String getOldid() {
		return oldid;
	}

	public void setOldid(String oldid) {
		this.oldid = oldid;
	}

	public String getNewid() {
		return newid;
	}

	public void setNewid(String newid) {
		this.newid = newid;
	}

}
