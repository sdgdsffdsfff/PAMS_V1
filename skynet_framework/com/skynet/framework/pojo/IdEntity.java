package com.skynet.framework.pojo;

import org.nutz.dao.entity.annotation.Name;

public class IdEntity {
    @Name
    protected String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
