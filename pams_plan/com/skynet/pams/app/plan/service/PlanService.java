package com.skynet.pams.app.plan.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.app.workflow.enginee.WorkFlowEngine;
import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.pams.base.pojo.Plan;

@InjectName("planService")
@IocBean(args = { "refer:dao" }) 
public class PlanService extends SkynetNameEntityService<Plan>
{
	public PlanService()
	{
		super();
	}
	
	public PlanService(Dao dao)
	{
		super(dao);
	}	
	
	public PlanService(Dao dao, Class<Plan> entityType)
	{
		super(dao, entityType);
	}
	
	@Inject
	WorkFlowEngine workFlowEngine;

	public WorkFlowEngine getWorkFlowEngine() {
		return workFlowEngine;
	}

	public void setWorkFlowEngine(WorkFlowEngine workFlowEngine) {
		this.workFlowEngine = workFlowEngine;
	}
	
	
}
