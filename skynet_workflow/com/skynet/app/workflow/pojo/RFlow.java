package com.skynet.app.workflow.pojo;

import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("T_SYS_WFRFLOW")
public class RFlow {
	@Name
	@ColDefine(width = 32)
	private String runflowkey;

    @Column
    @ColDefine(type=ColType.DATETIME)
	private Timestamp createtime; // 创建时间

    @Column
    @ColDefine(type=ColType.DATETIME)
	private Timestamp completetime; // 结束时间

	@Column
	private String ccreatetime;

	@Column
	private String workname;

	@Column
	private String flowdefid;

	@Column
	private String state;

	@Column
	private String priority;

	@Column
	private String dataid;

	@Column
	private String formid;

	@Column
	private String creater;

	@Column
	private String tableid;

	@Column
	private String suprunflowkey;

	@Column
	private String suprunactkey;
	
	@Column
	private String planid;
	

	public String getRunflowkey() {
		return runflowkey;
	}

	public void setRunflowkey(String runflowkey) {
		this.runflowkey = runflowkey;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Timestamp getCompletetime() {
		return completetime;
	}

	public void setCompletetime(Timestamp completetime) {
		this.completetime = completetime;
	}

	public String getCcreatetime() {
		return ccreatetime;
	}

	public void setCcreatetime(String ccreatetime) {
		this.ccreatetime = ccreatetime;
	}

	public String getWorkname() {
		return workname;
	}

	public void setWorkname(String workname) {
		this.workname = workname;
	}

	public String getFlowdefid() {
		return flowdefid;
	}

	public void setFlowdefid(String flowdefid) {
		this.flowdefid = flowdefid;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getDataid() {
		return dataid;
	}

	public void setDataid(String dataid) {
		this.dataid = dataid;
	}

	public String getFormid() {
		return formid;
	}

	public void setFormid(String formid) {
		this.formid = formid;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getTableid() {
		return tableid;
	}

	public void setTableid(String tableid) {
		this.tableid = tableid;
	}

	public String getSuprunflowkey() {
		return suprunflowkey;
	}

	public void setSuprunflowkey(String suprunflowkey) {
		this.suprunflowkey = suprunflowkey;
	}

	public String getSuprunactkey() {
		return suprunactkey;
	}

	public void setSuprunactkey(String suprunactkey) {
		this.suprunactkey = suprunactkey;
	}

	public String getPlanid() {
		return planid;
	}

	public void setPlanid(String planid) {
		this.planid = planid;
	}

}
