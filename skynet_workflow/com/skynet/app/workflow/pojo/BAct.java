package com.skynet.app.workflow.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_WFBACT")
public class BAct extends IdEntity{

	@Column
	private String cname;

	@Column
	private String ctype; // 类型（BEGIN，END，NORMAL，SUBFLOW）

	@Column
	private String flowid;

	@Column
	private String formid;

	@Column
	private String handletype;

	@Column
	private String split;

	@Column
	private String join;

	@Column
	private String isfirst;

	@Column
	private String outstyle;

	@Column
	private String selectstyle;

	@Column
	private String selectother;

	@Column
	private String formaccess;

	@Column
	private String subflowid; // 子流程定义标识（后期将作废）

	@Column
	private String subflowsno; // 子流程定义编号

	@Column
	private String subflowname; // 子流程定义名称

	@Column
	private String subflowcreate; // 创建子流程[MANUAL/AUTO][人工/自动]

	@Column
	private String subflowclose; // 关闭子流程[ALL/NOTALL][全部/非全部]

	@Column
	private String subflowlink; // 子流程关联模式[MAIN][主流程]
	
	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getFlowid() {
		return flowid;
	}

	public void setFlowid(String flowid) {
		this.flowid = flowid;
	}

	public String getFormid() {
		return formid;
	}

	public void setFormid(String formid) {
		this.formid = formid;
	}

	public String getHandletype() {
		return handletype;
	}

	public void setHandletype(String handletype) {
		this.handletype = handletype;
	}

	public String getSplit() {
		return split;
	}

	public void setSplit(String split) {
		this.split = split;
	}

	public String getJoin() {
		return join;
	}

	public void setJoin(String join) {
		this.join = join;
	}

	public String getIsfirst() {
		return isfirst;
	}

	public void setIsfirst(String isfirst) {
		this.isfirst = isfirst;
	}

	public String getOutstyle() {
		return outstyle;
	}

	public void setOutstyle(String outstyle) {
		this.outstyle = outstyle;
	}

	public String getSelectstyle() {
		return selectstyle;
	}

	public void setSelectstyle(String selectstyle) {
		this.selectstyle = selectstyle;
	}

	public String getSelectother() {
		return selectother;
	}

	public void setSelectother(String selectother) {
		this.selectother = selectother;
	}

	public String getFormaccess() {
		return formaccess;
	}

	public void setFormaccess(String formaccess) {
		this.formaccess = formaccess;
	}

	public String getSubflowid() {
		return subflowid;
	}

	public void setSubflowid(String subflowid) {
		this.subflowid = subflowid;
	}

	public String getSubflowsno() {
		return subflowsno;
	}

	public void setSubflowsno(String subflowsno) {
		this.subflowsno = subflowsno;
	}

	public String getSubflowname() {
		return subflowname;
	}

	public void setSubflowname(String subflowname) {
		this.subflowname = subflowname;
	}

	public String getSubflowcreate() {
		return subflowcreate;
	}

	public void setSubflowcreate(String subflowcreate) {
		this.subflowcreate = subflowcreate;
	}

	public String getSubflowclose() {
		return subflowclose;
	}

	public void setSubflowclose(String subflowclose) {
		this.subflowclose = subflowclose;
	}

	public String getSubflowlink() {
		return subflowlink;
	}

	public void setSubflowlink(String subflowlink) {
		this.subflowlink = subflowlink;
	}

}
