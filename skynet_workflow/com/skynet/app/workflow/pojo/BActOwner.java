package com.skynet.app.workflow.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_WFBACTOWNER")
public class BActOwner extends IdEntity {
	@Column
	private String ownerchoice;

	@Column
	private String ctype;

	@Column
	private String ownerctx;

	@Column
	private String actid;

	@Column
	private String cname;

	@Column
	private String outstyle;

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

	public String getActid() {
		return actid;
	}

	public void setActid(String actid) {
		this.actid = actid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getOutstyle() {
		return outstyle;
	}

	public void setOutstyle(String outstyle) {
		this.outstyle = outstyle;
	}

}
