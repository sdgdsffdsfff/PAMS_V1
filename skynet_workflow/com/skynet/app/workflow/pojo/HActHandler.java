package com.skynet.app.workflow.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_WFHACTHANDLER")
public class HActHandler extends IdEntity {
	@Column
	private String ctype;

	@Column
	private String handlerctx;

	@Column
	private String runactkey;

	@Column
	private String cname;

	@Column
	private String handledeptcname;

	@Column
	private String handledeptctx;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getHandledeptcname() {
		return handledeptcname;
	}

	public void setHandledeptcname(String handledeptcname) {
		this.handledeptcname = handledeptcname;
	}

	public String getHandledeptctx() {
		return handledeptctx;
	}

	public void setHandledeptctx(String handledeptctx) {
		this.handledeptctx = handledeptctx;
	}

}
