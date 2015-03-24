package com.skynet.app.workflow.ui.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.app.workflow.enginee.WorkFlowEngine;
import com.skynet.framework.action.BaseAction;

@IocBean
@At("/workflow/ui")
public class ForwardSelectSingleFrameAction extends BaseAction {

	@Inject
	private WorkFlowEngine workFlowEngine;

	@At("/forwardselectsingleframe")
	@Ok("->:/page/workflow/ui/fowardselectsingleframe.ftl")
	public Map main(String runactkey, String endactdefid) throws Exception {
		
		DynamicObject ract = workFlowEngine.getActManager().getRactService().locate(runactkey);
        
        String actdefid = ract.getFormatAttr("actdefid");
        String dataid = ract.getFormatAttr("dataid");
        String tableid = ract.getFormatAttr("tableid");
        String flowdefid = ract.getFormatAttr("flowdefid");
        
        DynamicObject bact = workFlowEngine.getActManager().getBactService().locate(actdefid);
        
        List routes = new ArrayList();
        
        if (!StringToolKit.isBlank(endactdefid))
        {
        	routes.add(workFlowEngine.getActManager().getBrouteService().locateBy(Cnd.where("startactid", "=", actdefid).and("endactid", "=", endactdefid)));
        }
        else
        {
        	routes.addAll(workFlowEngine.getActManager().getBrouteService().findByCond(Cnd.where("startactid", "=", actdefid)));
        }
        
        List endacts = new ArrayList();
        
        for (int i = 0; i < routes.size(); i++)
        {
        	String aendactdefid = ((DynamicObject)routes.get(i)).getFormatAttr("endactid");
        	DynamicObject abact = workFlowEngine.getActManager().getBactService().locate(aendactdefid);
        	endacts.add(abact);
        }
        
	    ro.put("bact", bact);
	    ro.put("ract", ract);
	    ro.put("routes", routes);
	    ro.put("endacts", endacts);
	    
	    ro.put("flowdefid", flowdefid);
	    ro.put("runactkey", runactkey);
	    ro.put("tableid", tableid);
	    ro.put("dataid", dataid);
	    ro.put("actdefid", actdefid);

		return ro;
	}
}
