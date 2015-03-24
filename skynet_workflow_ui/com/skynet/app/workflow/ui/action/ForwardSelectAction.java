package com.skynet.app.workflow.ui.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.skynet.app.workflow.enginee.WorkFlowEngine;
import com.skynet.app.workflow.spec.DBFieldConstants;
import com.skynet.app.workflow.vo.VForward;
import com.skynet.framework.action.BaseAction;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;

@IocBean
@At("/workflow/ui")
public class ForwardSelectAction extends BaseAction {
	@Inject
	private WorkFlowEngine workFlowEngine;

	@At("/forwardselect")
	@Ok("redirect:${obj.url}")
	public Map main(String runactkey, String endactdefid) throws Exception 
	{
		DynamicObject ract = workFlowEngine.getActManager().getRactService().locate(runactkey);
        
        String actdefid = ract.getFormatAttr("actdefid");
        String dataid = ract.getFormatAttr("dataid");
        String tableid = ract.getFormatAttr("tableid");
        String flowdefid = ract.getFormatAttr("flowdefid");
        String handletype = ract.getFormatAttr("handletype");

        if("普通".equals(handletype))
        {
        	String url = "forwardselectsingleframe.action";
        	url += "?runactkey=" + StringToolKit.formatText(runactkey);
        	ro.put("url", url);
        	return ro;
        }
        else
        if("多部门并行".equals(handletype))
        {
        	// 检查是否是最后的子活动实例转发
        	// 如果是最后的自活动实例转发，转向普通的节点间转发界面
        	// 否则进入节点内转发界面。
        	String suprunactkey = workFlowEngine.getActManager().getRactService().locate(runactkey).getFormatAttr("suprunactkey");
        	int num_nucomplete = workFlowEngine.getActManager().async_uncomplete_num(suprunactkey);
        	if(num_nucomplete<=1)
        	{
            	String url = "forwardselectsingleframe.action";
            	url += "?runactkey=" + StringToolKit.formatText(runactkey);
            	url += "&endactdefid" + StringToolKit.formatText(endactdefid);
            	
            	ro.put("url", url);
            	return ro;
        	}
        	else
        	{
        		VForward vo = new VForward();
        		vo.runactkey = runactkey;
        		
        		HttpSession session = Mvcs.getHttpSession(true);
        		DynamicObject token = (DynamicObject)session.getAttribute(com.skynet.framework.spec.GlobalConstants.sys_login_token);
        		
        		vo.userid = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_userid);
        		vo.loginname = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_user);
        		vo.username = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_username);		
        		vo.usertype = DBFieldConstants.PUB_PARTICIPATOR_PERSON;
        		List<DynamicObject> acts_forward = workFlowEngine.getActManager().vforward(vo);
        		ro.put("acts", acts_forward);
        		ro.put("url", "/page/workflow/ui/forwardsuccess.ftl");
        		return ro;
        	}
        }
        
		return ro;
	}
}
