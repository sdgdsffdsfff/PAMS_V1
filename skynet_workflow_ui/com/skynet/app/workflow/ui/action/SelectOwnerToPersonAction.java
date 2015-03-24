package com.skynet.app.workflow.ui.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.app.workflow.enginee.ActManager;
import com.skynet.app.workflow.enginee.WorkFlowEngine;
import com.skynet.framework.action.BaseAction;

@IocBean
@At("/workflow/ui")
public class SelectOwnerToPersonAction extends BaseAction {
	@Inject
	private WorkFlowEngine workFlowEngine;
	
	@At("/selectownertoperson")
	@Ok("->:/page/workflow/ui/selectownertoperson.ftl")
	public Map main(String actdefid) throws Exception {
		return json(actdefid);
	}

	
	@At("/selectownertoperson/json")
    @Ok("json")
	public Map json(String actdefid) throws Exception {

		DynamicObject swapFlow = new DynamicObject();


		ActManager actManager = workFlowEngine.getActManager();
		actManager.setSwapFlow(swapFlow);
		List owners = actManager.getActionOwnerToPerson(actdefid);
		
//		DynamicObject user = new DynamicObject();
//		user.setAttr("ownerctx", "P0001");
//		user.setAttr("cname", "张瑜");
//		user.setAttr("ordernum", "0001");
//		user.setAttr("ctype", "PERSON");
//		
//		owners.add(user);

		List agents = new ArrayList();

		// 查询对应的代理人
		for (int i = 0; i < owners.size(); i++)
		{

		}

		// 检查活动类型，转向不同页面 后期完善
		String handletype = workFlowEngine.getActManager().getBactService().locate(actdefid).getFormatAttr("handletype");

		ro.put("owners", owners);
		return ro;
	}
}
