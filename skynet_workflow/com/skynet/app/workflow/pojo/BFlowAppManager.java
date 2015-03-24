package com.skynet.app.workflow.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_WFBFLOWAPPMANAGER")
public class BFlowAppManager extends IdEntity {
	@Column
	private String ownerctype;

	@Column
	private String ownerctx;

	@Column
	private String classid;

	@Column
	private String cname;

	public String getOwnerctype() {
		return ownerctype;
	}

	public void setOwnerctype(String ownerctype) {
		this.ownerctype = ownerctype;
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

	public String getClassid() {
		return classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

}
