package com.skynet.app.workflow.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_WFBROUTE")
public class BRoute extends IdEntity {
	@Column
	public String cname;

	@Column
	public String routetype;

	@Column
	public String conditions;

	@Column
	public String startactid;

	@Column
	public String flowid;

	@Column
	public String direct;

	@Column
	public String endactid;

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

	public String getRoutetype() {
		return routetype;
	}

	public void setRoutetype(String routetype) {
		this.routetype = routetype;
	}

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	public String getStartactid() {
		return startactid;
	}

	public void setStartactid(String startactid) {
		this.startactid = startactid;
	}

	public String getFlowid() {
		return flowid;
	}

	public void setFlowid(String flowid) {
		this.flowid = flowid;
	}

	public String getDirect() {
		return direct;
	}

	public void setDirect(String direct) {
		this.direct = direct;
	}

	public String getEndactid() {
		return endactid;
	}

	public void setEndactid(String endactid) {
		this.endactid = endactid;
	}

}
