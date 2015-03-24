package com.skynet.app.workflow.pojo;

import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("T_SYS_WFRACT")
public class RAct {
	@Name
	@ColDefine(width = 32)
	private String runactkey;

	@Column
	private String runflowkey;

    @Column
    @ColDefine(type=ColType.DATETIME)
	private Timestamp createtime; // 创建时间 （活动到达时间）

    @Column
    @ColDefine(type=ColType.DATETIME)
	private Timestamp applytime; // 签收时间（确认工作开始时间）

    @Column
    @ColDefine(type=ColType.DATETIME)
	private Timestamp completetime; // 完成时间（确认工作结束时间）

	@Column
	private String completetype; // 完成类型（转出、退回等）

	@Column
	private String isuse; // 当前最新活动标识（最后一次创建该活动节点的实例）	
	
	@Column
	private String isinit; // 初始活动标识（第一次创建该活动节点的实例）	
	
	@Column
	private String cname; // 实例名称 （一般不赋值，采用活动定义名称，特殊情况下手动设置名称，如多部门并行活动实例）

	@Column
	private String ccreatetime;

	@Column
	private String actdefid;

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
	private String tableid;

	@Column
	private String handletype;
	
	@Column
	private int ordernum;	//序号，用于串行、并行活动的记录顺序使用。
	
	@Column
	private String suprunflowkey; // 上级流程实例标识（用于串行、并行、子流程等活动）
	
	@Column
	private String suprunactkey; // 上级活动实例标识（用于串行、并行、子流程等活动）
	
	@Column
	private String ispackage; // 是否包含下级实例节点 （用于识别串行、并行、子流程等虚实例活动）  
	
	@Column
	private String planid; // 对应计划标识  

	public String getRunactkey() {
		return runactkey;
	}

	public void setRunactkey(String runactkey) {
		this.runactkey = runactkey;
	}

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

	public Timestamp getApplytime() {
		return applytime;
	}

	public void setApplytime(Timestamp applytime) {
		this.applytime = applytime;
	}

	public Timestamp getCompletetime() {
		return completetime;
	}

	public void setCompletetime(Timestamp completetime) {
		this.completetime = completetime;
	}

	public String getCompletetype() {
		return completetype;
	}

	public void setCompletetype(String completetype) {
		this.completetype = completetype;
	}

	public String getIsuse() {
		return isuse;
	}

	public void setIsuse(String isuse) {
		this.isuse = isuse;
	}

	public String getIsinit() {
		return isinit;
	}

	public void setIsinit(String isinit) {
		this.isinit = isinit;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCcreatetime() {
		return ccreatetime;
	}

	public void setCcreatetime(String ccreatetime) {
		this.ccreatetime = ccreatetime;
	}

	public String getActdefid() {
		return actdefid;
	}

	public void setActdefid(String actdefid) {
		this.actdefid = actdefid;
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

	public String getTableid() {
		return tableid;
	}

	public void setTableid(String tableid) {
		this.tableid = tableid;
	}

	public String getHandletype() {
		return handletype;
	}

	public void setHandletype(String handletype) {
		this.handletype = handletype;
	}

	public int getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
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

	public String getIspackage() {
		return ispackage;
	}

	public void setIspackage(String ispackage) {
		this.ispackage = ispackage;
	}

	public String getPlanid() {
		return planid;
	}

	public void setPlanid(String planid) {
		this.planid = planid;
	}

}
