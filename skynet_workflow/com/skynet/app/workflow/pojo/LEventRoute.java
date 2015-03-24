package com.skynet.app.workflow.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_WFLEVENTROUTE")
public class LEventRoute extends IdEntity {
	@Column
	private String flowdefid;

	@Column
	private String runflowkey;

	@Column
	private String eventer;

	@Column
	private String endactdefid;

	@Column
	private String endrunactkey;

	@Column
	private String startactdefid;

	@Column
	private String startrunactkey;

	@Column
	private String eventdeptcname;

	@Column
	private String eventdept;

	@Column
	private String eventercname;

	@Column
	private String state;

	@Column
	private String eventtype;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getEventer() {
		return eventer;
	}

	public void setEventer(String eventer) {
		this.eventer = eventer;
	}

	public String getEndactdefid() {
		return endactdefid;
	}

	public void setEndactdefid(String endactdefid) {
		this.endactdefid = endactdefid;
	}

	public String getEndrunactkey() {
		return endrunactkey;
	}

	public void setEndrunactkey(String endrunactkey) {
		this.endrunactkey = endrunactkey;
	}

	public String getStartactdefid() {
		return startactdefid;
	}

	public void setStartactdefid(String startactdefid) {
		this.startactdefid = startactdefid;
	}

	public String getStartrunactkey() {
		return startrunactkey;
	}

	public void setStartrunactkey(String startrunactkey) {
		this.startrunactkey = startrunactkey;
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

	public String getEventercname() {
		return eventercname;
	}

	public void setEventercname(String eventercname) {
		this.eventercname = eventercname;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEventtype() {
		return eventtype;
	}

	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}

}
