package com.skynet.app.workflow.ui.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.skynet.app.workflow.enginee.WorkFlowEngine;
import com.skynet.app.workflow.spec.DBFieldConstants;
import com.skynet.app.workflow.vo.VForward;
import com.skynet.framework.action.BaseAction;
import com.skynet.framework.services.db.dybeans.DynamicObject;

@IocBean
@At("/workflow/ui")
public class ForwardAction extends BaseAction {

	@Inject
	private WorkFlowEngine workFlowEngine;

//	@At("/forward")
//	@Ok("json")
//	@AdaptBy(type = JsonAdaptor.class)
//	public Map main(VForward vo) throws Exception {
//		HttpSession session = Mvcs.getHttpSession(true);
//		DynamicObject token = (DynamicObject)session.getAttribute(com.skynet.framework.spec.GlobalConstants.sys_login_token);
//		
//		vo.userid = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_userid);
//		vo.loginname = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_user);
//		vo.username = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_username);		
//		vo.usertype = DBFieldConstants.PUB_PARTICIPATOR_PERSON;
//		List<DynamicObject> acts_forward = workFlowEngine.getActManager().vforward(vo);
//		ro.put("acts", acts_forward);
//		return ro;
//	}
	
	
	@At("/forward")
	@Ok("->:/page/workflow/ui/forwardsuccess.ftl")
	public Map main(@Param("vo") String vochar) throws Exception {
		
		Map o = (Map)Json.fromJson(vochar);
		VForward vo  = Lang.map2Object(o, VForward.class);
		
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject token = (DynamicObject)session.getAttribute(com.skynet.framework.spec.GlobalConstants.sys_login_token);
		
		vo.userid = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_userid);
		vo.loginname = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_user);
		vo.username = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_username);		
		vo.usertype = DBFieldConstants.PUB_PARTICIPATOR_PERSON;
		List<DynamicObject> acts_forward = workFlowEngine.getActManager().vforward(vo);
		ro.put("acts", acts_forward);
		return ro;
	}
}
