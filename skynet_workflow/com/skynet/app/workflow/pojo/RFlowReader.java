package com.skynet.app.workflow.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_WFRFLOWREADER")
public class RFlowReader extends IdEntity {
	@Column
	private String runflowkey;

	@Column
	private String runactkey;

	@Column
	private String ctype;

	@Column
	private String readerctx;

	@Column
	private String readersource;

	@Column
	private String cname;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getReadersource() {
		return readersource;
	}

	public void setReadersource(String readersource) {
		this.readersource = readersource;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

}
