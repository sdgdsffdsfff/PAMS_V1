package com.skynet.app.workflow.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_WFLFLOWREADER")
public class LFlowReader extends IdEntity {
	@Column
	private String ctype;

	@Column
	private String readerctx;

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

	public String getReaderctx() {
		return readerctx;
	}

	public void setReaderctx(String readerctx) {
		this.readerctx = readerctx;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

}
