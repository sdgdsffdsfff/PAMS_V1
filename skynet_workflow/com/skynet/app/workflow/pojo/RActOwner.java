package com.skynet.app.workflow.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_WFRACTOWNER")
public class RActOwner extends IdEntity {
	@Column
	private String runactkey;

	@Column
	private String ctype; // 用户类型

	@Column
	private String ownerctx; // 标识（人员用户名、角色标识、组织标识）
	
	@Column
	private String cname; //用户姓名

	@Column
	private String complete;
	
	@Column
	private String organid; // 组织标识
	
	@Column
	private String organname; // 组织名称
	
	@Column
	private String organtype; // 组织类型

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

	public String getOrganid() {
		return organid;
	}

	public void setOrganid(String organid) {
		this.organid = organid;
	}

	public String getOrganname() {
		return organname;
	}

	public void setOrganname(String organname) {
		this.organname = organname;
	}

	public String getOrgantype() {
		return organtype;
	}

	public void setOrgantype(String organtype) {
		this.organtype = organtype;
	}

}
