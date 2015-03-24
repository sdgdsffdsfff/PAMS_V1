package com.skynet.app.workflow.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_WFLEVENTACTTOHANDAGENTER")
public class LEventActToHandlerAgenter extends IdEntity {
	@Column
	private String eventid;

	@Column
	private String agentercname;

	@Column
	private String agenter;

	public String getEventid() {
		return eventid;
	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAgentercname() {
		return agentercname;
	}

	public void setAgentercname(String agentercname) {
		this.agentercname = agentercname;
	}

	public String getAgenter() {
		return agenter;
	}

	public void setAgenter(String agenter) {
		this.agenter = agenter;
	}

}
