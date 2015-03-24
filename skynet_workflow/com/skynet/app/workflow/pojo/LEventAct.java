package com.skynet.app.workflow.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_WFLEVENTACT")
public class LEventAct extends IdEntity {
	@Column
	public String actdefid;
	
	@Column
	public String runactkey;
	
	@Column
	public String flowdefid;
	
	@Column
	public String runflowkey;
	
	@Column
	public String beginstate;
	
	@Column
	public String endstate;
	
	@Column
	public String eventercname;
	
	@Column
	public String eventdeptcname;
	
	@Column
	public String eventdept;
	
	@Column
	public String eventtype;
	
	@Column
	public String eventer;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getActdefid() {
		return actdefid;
	}

	public void setActdefid(String actdefid) {
		this.actdefid = actdefid;
	}

	public String getRunactkey() {
		return runactkey;
	}

	public void setRunactkey(String runactkey) {
		this.runactkey = runactkey;
	}

	public String getFlowdefid() {
		return flowdefid;
	}

	public void setFlowdefid(String flowdefid) {
		this.flowdefid = flowdefid;
	}

	public String getRunflowkey() {
		return runflowkey;
	}

	public void setRunflowkey(String runflowkey) {
		this.runflowkey = runflowkey;
	}

	public String getBeginstate() {
		return beginstate;
	}

	public void setBeginstate(String beginstate) {
		this.beginstate = beginstate;
	}

	public String getEndstate() {
		return endstate;
	}

	public void setEndstate(String endstate) {
		this.endstate = endstate;
	}

	public String getEventercname() {
		return eventercname;
	}

	public void setEventercname(String eventercname) {
		this.eventercname = eventercname;
	}

	public String getEventdeptcname() {
		return eventdeptcname;
	}

	public void setEventdeptcname(String eventdeptcname) {
		this.eventdeptcname = eventdeptcname;
	}

	public String getEventdept() {
		return eventdept;
	}

	public void setEventdept(String eventdept) {
		this.eventdept = eventdept;
	}

	public String getEventtype() {
		return eventtype;
	}

	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}

	public String getEventer() {
		return eventer;
	}

	public void setEventer(String eventer) {
		this.eventer = eventer;
	}

}
