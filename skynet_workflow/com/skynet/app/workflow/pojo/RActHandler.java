package com.skynet.app.workflow.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_WFRACTHANDLER")
public class RActHandler extends IdEntity {
	@Column
	private String ctype;

	@Column
	private String handlerctx; // 标识（人员用户名、角色标识、组织标识）

	@Column
	private String runactkey;

	@Column
	private String cname;

	@Column
	private String organid; // 组织标识
	
	@Column
	private String organname; // 组织名称
	
	@Column
	private String organtype; // 组织类型

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getHandlerctx() {
		return handlerctx;
	}

	public void setHandlerctx(String handlerctx) {
		this.handlerctx = handlerctx;
	}

	public String getRunactkey() {
		return runactkey;
	}

	public void setRunactkey(String runactkey) {
		this.runactkey = runactkey;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
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
