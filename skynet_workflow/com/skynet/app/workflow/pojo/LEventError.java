package com.skynet.app.workflow.pojo;

import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_WFLEVENTERROR")
public class LEventError extends IdEntity {
	@Column
	private String memo;

	@Column
	private String ctype;

	@Column
	private String endactid;

	@Column
	private String startactid;

	@Column
	private String runflowkey;

	@Column
	private String endrunactkey;

	@Column
	private String startrunactkey;

	@Column
	private String flowdefid;

	@Column
	private String eventerctype;

	@Column
	private String eventer;

    @Column
    @ColDefine(type=ColType.DATETIME)
	private Timestamp runtime;

	@Column
	private String state;

	@Column
	private String dataid;

	@Column
	private String tableid;

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getEndactid() {
		return endactid;
	}

	public void setEndactid(String endactid) {
		this.endactid = endactid;
	}

	public String getStartactid() {
		return startactid;
	}

	public void setStartactid(String startactid) {
		this.startactid = startactid;
	}

	public String getRunflowkey() {
		return runflowkey;
	}

	public void setRunflowkey(String runflowkey) {
		this.runflowkey = runflowkey;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEndrunactkey() {
		return endrunactkey;
	}

	public void setEndrunactkey(String endrunactkey) {
		this.endrunactkey = endrunactkey;
	}

	public String getStartrunactkey() {
		return startrunactkey;
	}

	public void setStartrunactkey(String startrunactkey) {
		this.startrunactkey = startrunactkey;
	}

	public String getFlowdefid() {
		return flowdefid;
	}

	public void setFlowdefid(String flowdefid) {
		this.flowdefid = flowdefid;
	}

	public String getEventerctype() {
		return eventerctype;
	}

	public void setEventerctype(String eventerctype) {
		this.eventerctype = eventerctype;
	}

	public String getEventer() {
		return eventer;
	}

	public void setEventer(String eventer) {
		this.eventer = eventer;
	}

	public Timestamp getRuntime() {
		return runtime;
	}

	public void setRuntime(Timestamp runtime) {
		this.runtime = runtime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDataid() {
		return dataid;
	}

	public void setDataid(String dataid) {
		this.dataid = dataid;
	}

	public String getTableid() {
		return tableid;
	}

	public void setTableid(String tableid) {
		this.tableid = tableid;
	}

}
