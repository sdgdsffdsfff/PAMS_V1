package com.skynet.app.workflow.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_WFBNORACT")
public class BNorAct extends IdEntity {

	@Column
	public String allowagent;

	@Column
	public String ownerexectype;

	public String getAllowagent() {
		return allowagent;
	}

	public void setAllowagent(String allowagent) {
		this.allowagent = allowagent;
	}

	public String getOwnerexectype() {
		return ownerexectype;
	}

	public void setOwnerexectype(String ownerexectype) {
		this.ownerexectype = ownerexectype;
	}

}
