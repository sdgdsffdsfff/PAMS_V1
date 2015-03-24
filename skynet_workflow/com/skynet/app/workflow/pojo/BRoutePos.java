package com.skynet.app.workflow.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_WFBROUTEPOS")
public class BRoutePos extends IdEntity {

	@Column
	private String routeid;

	@Column
	private String mpoints;

	public String getRouteid() {
		return routeid;
	}

	public void setRouteid(String routeid) {
		this.routeid = routeid;
	}

	public String getMpoints() {
		return mpoints;
	}

	public void setMpoints(String mpoints) {
		this.mpoints = mpoints;
	}

}
