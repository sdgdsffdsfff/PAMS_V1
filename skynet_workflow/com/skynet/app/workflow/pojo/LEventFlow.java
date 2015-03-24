package com.skynet.app.workflow.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_WFLEVENTFLOW")
public class LEventFlow extends IdEntity {
	@Column
	private String eventer;

	@Column
	private String flowdefid;

	@Column
	private String runflowkey;

	@Column
	private String beginstate;

	@Column
	private String endstate;

	@Column
	private String eventercname;

	@Column
	private String eventdept;

	@Column
	private String eventdeptcname;

	@Column
	private String eventtype;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEventer() {
		return eventer;
	}

	public void setEventer(String eventer) {
		this.eventer = eventer;
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

	public String getEventdept() {
		return eventdept;
	}

	public void setEventdept(String eventdept) {
		this.eventdept = eventdept;
	}

	public String getEventdeptcname() {
		return eventdeptcname;
	}

	public void setEventdeptcname(String eventdeptcname) {
		this.eventdeptcname = eventdeptcname;
	}

	public String getEventtype() {
		return eventtype;
	}

	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}

}
