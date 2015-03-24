package com.skynet.app.workflow.enginee;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@InjectName("workFlowEngine")
@IocBean
public class WorkFlowEngine {
	@Inject
	FlowManager flowManager; // 流程管理器

	@Inject
	ActManager actManager; // 活动管理器

	public FlowManager getFlowManager() {
		return flowManager;
	}

	public void setFlowManager(FlowManager flowManager) {
		this.flowManager = flowManager;
	}

	public ActManager getActManager() {
		return actManager;
	}

	public void setActManager(ActManager actManager) {
		this.actManager = actManager;
	}

}
