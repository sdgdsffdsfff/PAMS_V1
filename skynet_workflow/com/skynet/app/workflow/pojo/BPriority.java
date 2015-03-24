package com.skynet.app.workflow.pojo;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_WFBPRIORITY")
public class BPriority extends IdEntity {
	@Column
	@ColDefine(width = 2000)
	public String descript;

	@Column
	public String cname;

	@Column
	public Integer outtime;

	@Column
	public Integer agenttime;

	@Column
	public Integer worktime;

	@Column
	public Integer agentnum;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Integer getOuttime() {
		return outtime;
	}

	public void setOuttime(Integer outtime) {
		this.outtime = outtime;
	}

	public Integer getAgenttime() {
		return agenttime;
	}

	public void setAgenttime(Integer agenttime) {
		this.agenttime = agenttime;
	}

	public Integer getWorktime() {
		return worktime;
	}

	public void setWorktime(Integer worktime) {
		this.worktime = worktime;
	}

	public Integer getAgentnum() {
		return agentnum;
	}

	public void setAgentnum(Integer agentnum) {
		this.agentnum = agentnum;
	}

}
