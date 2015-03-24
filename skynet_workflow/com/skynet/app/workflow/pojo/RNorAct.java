package com.skynet.app.workflow.pojo;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("T_SYS_WFRNORACT")
public class RNorAct {
	@Name
	@ColDefine(width = 32)
	private String runactkey;

	@Column
	private String status;

	public String getRunactkey() {
		return runactkey;
	}

	public void setRunactkey(String runactkey) {
		this.runactkey = runactkey;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
