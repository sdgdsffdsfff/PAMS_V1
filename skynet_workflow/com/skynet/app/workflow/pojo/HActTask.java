package com.skynet.app.workflow.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_WFHACTTASK")
public class HActTask extends IdEntity {
	@Column
	private String runactkey;

	@Column
	private String complete;

	@Column
	private String acttaskdefid;

	@Column
	private String exectime;

	@Column
	private String sno;

	public String getRunactkey() {
		return runactkey;
	}

	public void setRunactkey(String runactkey) {
		this.runactkey = runactkey;
	}

	public String getComplete() {
		return complete;
	}

	public void setComplete(String complete) {
		this.complete = complete;
	}

	public String getActtaskdefid() {
		return acttaskdefid;
	}

	public void setActtaskdefid(String acttaskdefid) {
		this.acttaskdefid = acttaskdefid;
	}

	public String getExectime() {
		return exectime;
	}

	public void setExectime(String exectime) {
		this.exectime = exectime;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

}
