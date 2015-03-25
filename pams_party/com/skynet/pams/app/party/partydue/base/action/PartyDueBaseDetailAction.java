package com.skynet.pams.app.party.partydue.base.action;

import java.sql.Timestamp;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.skynet.framework.action.BaseAction;
import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.pams.app.party.service.PartyDueUseSuggestDetailService;
import com.skynet.pams.app.party.service.PartyDueUseSuggestService;
import com.skynet.pams.base.pojo.PartyDueUseSuggestDetail;

@IocBean
@At("/party/partydue/research/basedetail")
public class PartyDueBaseDetailAction  extends BaseAction {
	
	@Inject
	private PartyDueUseSuggestService partydueusesuggestService;
	
	@Inject
	private PartyDueUseSuggestDetailService partydueusesuggestdetailService;
	
	public static String tableid = "PartyDueUseSuggestDetail";
	
	@At("/save")
	@Ok("redirect:/party/partydue/research/usesuggest/locate.action?runactkey=${obj.runactkey}")
	public Map save(@Param("..") PartyDueUseSuggestDetail obj) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject token = (DynamicObject)session.getAttribute(com.skynet.framework.spec.GlobalConstants.sys_login_token);
		String deptid = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_dept);
		String deptname = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_deptname);
		String loginname = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_user);
		String username = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_username);		
		HttpServletRequest req = Mvcs.getReq();
		String runactkey = req.getParameter("runactkey");
		Timestamp now = new Timestamp(System.currentTimeMillis());
		obj.setDeptid(deptid);
		obj.setDeptname(deptname);
		obj.setSuggester(loginname);
		obj.setSuggestercname(username);
		obj.setSuggesttime(now);
		
		if(StringToolKit.isBlank(obj.getId()))
		{
			obj.setId(UUIDGenerator.getInstance().getNextValue());
			partydueusesuggestdetailService.sdao().insert(obj);
		}
		else
		{
			partydueusesuggestdetailService.sdao().update(obj);				
		}

		ro.put("runactkey", runactkey);
		ro.put("usesuggestdetail", obj);
		return ro;
	}
}
