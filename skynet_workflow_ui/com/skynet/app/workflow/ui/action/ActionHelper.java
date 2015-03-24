package com.skynet.app.workflow.ui.action;

import javax.servlet.http.HttpSession;

import org.nutz.mvc.Mvcs;

import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.app.workflow.spec.GlobalConstants;


public class ActionHelper {
	
	public static DynamicObject prepared_author_base()
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject token = (DynamicObject)session.getAttribute(com.skynet.framework.spec.GlobalConstants.sys_login_token);
		DynamicObject swapFlow = new DynamicObject();
		
		swapFlow.setAttr(GlobalConstants.swap_coperatorid, token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_userid));
		swapFlow.setAttr(GlobalConstants.swap_coperatorloginname, token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_user));
		swapFlow.setAttr(GlobalConstants.swap_coperatorcname, token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_username));
		swapFlow.setAttr(GlobalConstants.swap_coperatordeptid, token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_dept));
		swapFlow.setAttr(GlobalConstants.swap_coperatordeptcname, token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_deptname));
		swapFlow.setAttr(GlobalConstants.swap_coperatororgid, token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_org));
		swapFlow.setAttr(GlobalConstants.swap_coperatororgcname, token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_orgname));
		
		return swapFlow;
	}
	

	
	
}
