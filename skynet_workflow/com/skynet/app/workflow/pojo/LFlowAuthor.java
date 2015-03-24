package com.skynet.app.workflow.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_WFLFLOWAUTHOR")
public class LFlowAuthor extends IdEntity {

	@Column
	private String ctype;

	@Column
	private String authorctx;

	@Column
	private String cname;

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

}
