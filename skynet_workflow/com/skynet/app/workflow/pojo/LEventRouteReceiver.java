package com.skynet.app.workflow.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_WFLEVENTROUTE_RECEIVER")
public class LEventRouteReceiver extends IdEntity {

	@Column
	private String receivercname;

	@Column
	private String receiver;

	@Column
	private String eventid;

	@Column
	private String receiverctype;

	public String getReceivercname() {
		return receivercname;
	}

	public void setReceivercname(String receivercname) {
		this.receivercname = receivercname;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

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

	public String getReceiverctype() {
		return receiverctype;
	}

	public void setReceiverctype(String receiverctype) {
		this.receiverctype = receiverctype;
	}

}
