package com.skynet.app.workflow.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("T_SYS_WFHACTOWNER")
public class HActOwner
{
    @Name
    protected String id;
    
	@Column
	private String runactkey;

	@Column
	private String ctype;

	@Column
	private String ownerctx;

	@Column
	private String cname;

	@Column
	private String complete;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRunactkey() {
		return runactkey;
	}

	public void setRunactkey(String runactkey) {
		this.runactkey = runactkey;
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

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getComplete() {
		return complete;
	}

	public void setComplete(String complete) {
		this.complete = complete;
	}

}
