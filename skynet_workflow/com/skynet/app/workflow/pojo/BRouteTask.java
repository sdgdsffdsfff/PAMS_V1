package com.skynet.app.workflow.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_WFBROUTETASK")
public class BRouteTask extends IdEntity {
	@Column
	public String routeid;

	@Column
	public String acttaskid;

	@Column
	public String require;

	@Column
	public String taskname;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRouteid() {
		return routeid;
	}

	public void setRouteid(String routeid) {
		this.routeid = routeid;
	}

	public String getActtaskid() {
		return acttaskid;
	}

	public void setActtaskid(String acttaskid) {
		this.acttaskid = acttaskid;
	}

	public String getRequire() {
		return require;
	}

	public void setRequire(String require) {
		this.require = require;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

}
