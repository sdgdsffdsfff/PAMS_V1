package com.skynet.app.workflow.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_WFLEVENTACT_RECEIVER")
public class LEventActReceiver extends IdEntity {
	
	@Column
	public String eventid;

	@Column
	public String receivercname;

	@Column
	public String receiver;

	@Column
	public String receiverctype;

	public String getEventid() {
		return eventid;
	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
	}

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

	public String getReceiverctype() {
		return receiverctype;
	}

	public void setReceiverctype(String receiverctype) {
		this.receiverctype = receiverctype;
	}

}
