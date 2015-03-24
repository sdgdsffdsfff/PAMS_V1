package com.skynet.app.workflow.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_WFBFLOWOWNER")
public class BFlowOwner extends IdEntity {
	@Column
	private String ctype;

	@Column
	private String ownerctx;

	@Column
	private String ownerchoice;

	@Column
	private String flowid;

	@Column
	private String cname;

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getOwnerctx() {
		return ownerctx;
	}

	public void setOwnerctx(String ownerctx) {
		this.ownerctx = ownerctx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwnerchoice() {
		return ownerchoice;
	}

	public void setOwnerchoice(String ownerchoice) {
		this.ownerchoice = ownerchoice;
	}

	public String getFlowid() {
		return flowid;
	}

	public void setFlowid(String flowid) {
		this.flowid = flowid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

}
