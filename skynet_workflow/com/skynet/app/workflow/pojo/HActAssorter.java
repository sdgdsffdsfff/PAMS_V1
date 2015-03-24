package com.skynet.app.workflow.pojo;

import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_WFHACTASSORTER")
public class HActAssorter extends IdEntity {
	@Column
	private String assorttype;

	@Column
	private String assortctx;

    @Column
	private String consigntype;

	@Column
	private String consignctx;

	@Column
	private String runactkey;

	@Column
	private String assortcname;

	@Column
	private String consigncname;

	public String getAssorttype() {
		return assorttype;
	}

	public void setAssorttype(String assorttype) {
		this.assorttype = assorttype;
	}

	public String getAssortctx() {
		return assortctx;
	}

	public void setAssortctx(String assortctx) {
		this.assortctx = assortctx;
	}

	public String getConsigntype() {
		return consigntype;
	}

	public void setConsigntype(String consigntype) {
		this.consigntype = consigntype;
	}

	public String getConsignctx() {
		return consignctx;
	}

	public void setConsignctx(String consignctx) {
		this.consignctx = consignctx;
	}

	public String getRunactkey() {
		return runactkey;
	}

	public void setRunactkey(String runactkey) {
		this.runactkey = runactkey;
	}

	public String getAssortcname() {
		return assortcname;
	}

	public void setAssortcname(String assortcname) {
		this.assortcname = assortcname;
	}

	public String getConsigncname() {
		return consigncname;
	}

	public void setConsigncname(String consigncname) {
		this.consigncname = consigncname;
	}

}
