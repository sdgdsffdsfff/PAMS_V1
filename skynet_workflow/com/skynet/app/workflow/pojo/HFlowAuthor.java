package com.skynet.app.workflow.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_WFHFLOWAUTHOR")
public class HFlowAuthor extends IdEntity {
	@Column
	private String ctype;

	@Column
	private String authorctx;

	@Column
	private String runflowkey;

	@Column
	private String runactkey;

	@Column
	private String authorsource;

	@Column
	private String cname;

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

	public String getAuthorctx() {
		return authorctx;
	}

	public void setAuthorctx(String authorctx) {
		this.authorctx = authorctx;
	}

	public String getRunflowkey() {
		return runflowkey;
	}

	public void setRunflowkey(String runflowkey) {
		this.runflowkey = runflowkey;
	}

	public String getRunactkey() {
		return runactkey;
	}

	public void setRunactkey(String runactkey) {
		this.runactkey = runactkey;
	}

	public String getAuthorsource() {
		return authorsource;
	}

	public void setAuthorsource(String authorsource) {
		this.authorsource = authorsource;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

}
