package com.skynet.pams.base.pojo;

import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("T_APP_PLAN")
public class Plan {
    @Name
    private String id;
    
    @Column
    private String cname; // 计划标题名称
    
    @Column
    private String parentid; // 上级计划节点标识
    
    @Column
    @ColDefine(type=ColType.DATETIME)
    private Timestamp planstartdate; // 计划开始日期
    
    @Column
    @ColDefine(type=ColType.DATETIME)
    private Timestamp planenddate; // 计划结束日期
    
    @Column
    private String planheader; // 计划负责人用户名
    
    @Column
    private String planheadercname; // 计划负责人姓名
    
    @Column
    @ColDefine(type=ColType.DATETIME)
    private Timestamp actualstartdate; // 实际开始日期
    
    @Column
    @ColDefine(type=ColType.DATETIME)
    private Timestamp actualenddate; // 实际结束日期
    
    @Column
    private String actualheader; // 实际负责人用户名
    
    @Column
    private String actualheadercname; // 实际负责人姓名    
  
    @Column
    @ColDefine(type=ColType.DATETIME)
    private Timestamp basestartdate; // 基准开始日期
    
    @Column
    @ColDefine(type=ColType.DATETIME)
    private Timestamp baseenddate; // 基准结束日期
    
    @Column
    private int planworkload; // 计划工作量
    
    @Column
    private int baseplanworkload; // 基准工作量    
    
    @Column
    private int actualworkload; // 实际工作量
    
    @Column
    private int phaseorstep; // 类型 阶段 里程碑 任务 交付物 
    
    @Column
    private String sequencekey; // 内部码 原internal
    
    @Column
    private int completepercent; // 完成百分比
    
    @Column
    @ColDefine(type=ColType.DATETIME)
    private Timestamp createtime; // 创建时间   
    
    @Column
    private String creater; // 创建人用户名
    
    @Column
    private String creatercname; // 创建人姓名
    
    @Column
    @ColDefine(type=ColType.DATETIME)
    private Timestamp modifytime; // 修改时间
    
    @Column
    private String modifier; // 修改人员用户
    
    @Column
    private String modifiercname; // 修改人员姓名    
    
    @Column
    private String description; // 说明
    
    @Column
    private String remark; // 备注
    
    @Column
    private String state; // 计划阶段（计划、执行、完成）
    
    @Column
    private String ctype; // 计划类型（普通、流程、活动）    
    
    @Column
    private int cost; // 计划费用
    
    @Column
    private String flowdefid; // 流程定义标识（计划相关流程）
    
    @Column
    private String actdefid; // 活动定义标识（计划相关流程节点）

    @Column
    private String runflowkey; // 流程实例标识（计划相关流程）
    
    @Column
    private String runactkey; // 活动实例标识（计划相关流程节点）

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public Timestamp getPlanstartdate() {
		return planstartdate;
	}

	public void setPlanstartdate(Timestamp planstartdate) {
		this.planstartdate = planstartdate;
	}

	public Timestamp getPlanenddate() {
		return planenddate;
	}

	public void setPlanenddate(Timestamp planenddate) {
		this.planenddate = planenddate;
	}

	public String getPlanheader() {
		return planheader;
	}

	public void setPlanheader(String planheader) {
		this.planheader = planheader;
	}

	public String getPlanheadercname() {
		return planheadercname;
	}

	public void setPlanheadercname(String planheadercname) {
		this.planheadercname = planheadercname;
	}

	public Timestamp getActualstartdate() {
		return actualstartdate;
	}

	public void setActualstartdate(Timestamp actualstartdate) {
		this.actualstartdate = actualstartdate;
	}

	public Timestamp getActualenddate() {
		return actualenddate;
	}

	public void setActualenddate(Timestamp actualenddate) {
		this.actualenddate = actualenddate;
	}

	public String getActualheader() {
		return actualheader;
	}

	public void setActualheader(String actualheader) {
		this.actualheader = actualheader;
	}

	public String getActualheadercname() {
		return actualheadercname;
	}

	public void setActualheadercname(String actualheadercname) {
		this.actualheadercname = actualheadercname;
	}

	public Timestamp getBasestartdate() {
		return basestartdate;
	}

	public void setBasestartdate(Timestamp basestartdate) {
		this.basestartdate = basestartdate;
	}

	public Timestamp getBaseenddate() {
		return baseenddate;
	}

	public void setBaseenddate(Timestamp baseenddate) {
		this.baseenddate = baseenddate;
	}

	public int getPlanworkload() {
		return planworkload;
	}

	public void setPlanworkload(int planworkload) {
		this.planworkload = planworkload;
	}

	public int getBaseplanworkload() {
		return baseplanworkload;
	}

	public void setBaseplanworkload(int baseplanworkload) {
		this.baseplanworkload = baseplanworkload;
	}

	public int getActualworkload() {
		return actualworkload;
	}

	public void setActualworkload(int actualworkload) {
		this.actualworkload = actualworkload;
	}

	public int getPhaseorstep() {
		return phaseorstep;
	}

	public void setPhaseorstep(int phaseorstep) {
		this.phaseorstep = phaseorstep;
	}

	public String getSequencekey() {
		return sequencekey;
	}

	public void setSequencekey(String sequencekey) {
		this.sequencekey = sequencekey;
	}

	public int getCompletepercent() {
		return completepercent;
	}

	public void setCompletepercent(int completepercent) {
		this.completepercent = completepercent;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getCreatercname() {
		return creatercname;
	}

	public void setCreatercname(String creatercname) {
		this.creatercname = creatercname;
	}

	public Timestamp getModifytime() {
		return modifytime;
	}

	public void setModifytime(Timestamp modifytime) {
		this.modifytime = modifytime;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getModifiercname() {
		return modifiercname;
	}

	public void setModifiercname(String modifiercname) {
		this.modifiercname = modifiercname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getFlowdefid() {
		return flowdefid;
	}

	public void setFlowdefid(String flowdefid) {
		this.flowdefid = flowdefid;
	}

	public String getActdefid() {
		return actdefid;
	}

	public void setActdefid(String actdefid) {
		this.actdefid = actdefid;
	}

	public String getRunflowkey() {
		return runflowkey;
	}

	public void setRunflowkey(String runflowkey) {
		this.runflowkey = runflowkey;
	}

	public String getRunactkey() {
		return runactkey;
	}

	public void setRunactkey(String runactkey) {
		this.runactkey = runactkey;
	}

}
