package com.skynet.framework.action;

import java.util.Map;

import com.skynet.framework.services.db.dybeans.DynamicObject;

public class BaseAction 
{
	protected DynamicObject ro = new DynamicObject(); // 存放数据

	public Map getRo() {
		return ro;
	}

	public void setRo(DynamicObject ro) {
		this.ro = ro;
	}
	
}
