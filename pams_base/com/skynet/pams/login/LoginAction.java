package com.skynet.pams.login;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.skynet.app.organ.service.GroupUserService;
import com.skynet.app.organ.service.OrganService;
import com.skynet.app.organ.service.UserService;
import com.skynet.framework.action.BaseAction;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.framework.spec.GlobalConstants;

@IocBean
@At("/author")
public class LoginAction extends BaseAction{
	@Inject
	private OrganService organService;
	
	@Inject
	private UserService userService;
	
	@Inject
	private GroupUserService groupuserService;
	
	@At("/login")
	@Ok("->:/index.ftl")
	public Map login(@Param("username") String loginname, String password) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		session.removeAttribute(GlobalConstants.sys_login_token);
		
		DynamicObject user = userService.locateBy(Cnd.where("loginname", "=", loginname).and("password", "=", password));
		if(StringToolKit.isBlank(user.getFormatAttr("id")))
		{
			session.removeAttribute(GlobalConstants.sys_login_token);
			ro.put("status", "failed");
			return ro;
		}
		
		DynamicObject dept = userService.getPrimaryDept(loginname);
		DynamicObject org = userService.getPrimaryOrg(loginname);		
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr(GlobalConstants.sys_login_user, loginname);
		obj.setAttr(GlobalConstants.sys_login_username, user.getFormatAttr("cname"));
		obj.setAttr(GlobalConstants.sys_login_userid, user.getFormatAttr("id"));
		
		obj.setAttr(GlobalConstants.sys_login_dept, dept.getFormatAttr("id"));
		obj.setAttr(GlobalConstants.sys_login_deptname, dept.getFormatAttr("cname"));
		obj.setAttr(GlobalConstants.sys_login_dept_internal, dept.getFormatAttr("internal"));

		obj.setAttr(GlobalConstants.sys_login_org, org.getFormatAttr("id"));
		obj.setAttr(GlobalConstants.sys_login_orgname, org.getFormatAttr("cname"));
		obj.setAttr(GlobalConstants.sys_login_org_internal, org.getFormatAttr("internal"));	

		session.setAttribute(GlobalConstants.sys_login_token, obj);
		
		return ro;
	}
	
	@At("/inittable")
	@Ok("json")
	public Map inittable() throws Exception
	{		
		Daos.createTablesInPackage(organService.dao(), "com.skynet.app.workflow.pojo", true);
		Daos.createTablesInPackage(organService.dao(), "com.skynet.app.organ.pojo", true);
		Daos.createTablesInPackage(organService.dao(), "com.skynet.pams.base.pojo", true);
		return ro;
	}
	
	@At("/clearbflow")
	@Ok("json")
	public Map clearblow() throws Exception
	{		
		organService.sdao().execute(Sqls.create("delete from t_sys_wfbflowclass"));
		organService.sdao().execute(Sqls.create("delete from t_sys_wfbform"));
		organService.sdao().execute(Sqls.create("delete from t_sys_wfbflow"));		
		organService.sdao().execute(Sqls.create("delete from t_sys_wfbact"));	
		organService.sdao().execute(Sqls.create("delete from t_sys_wfbactowner"));	
		organService.sdao().execute(Sqls.create("delete from t_sys_wfbroute"));	
		organService.sdao().execute(Sqls.create("delete from t_sys_wfbactpos"));	
		organService.sdao().execute(Sqls.create("delete from t_sys_wfbroutepos"));	
		return ro;
	}
	
	@At("/clearrunflow")
	@Ok("json")
	public Map clearrunflow() throws Exception
	{		
		organService.sdao().execute(Sqls.create("delete from t_sys_wflevent"));
		organService.sdao().execute(Sqls.create("delete from t_sys_wfleventact"));		
		organService.sdao().execute(Sqls.create("delete from t_sys_wfleventact_receiver"));	
		organService.sdao().execute(Sqls.create("delete from t_sys_wfleventact_toassorter"));	
		organService.sdao().execute(Sqls.create("delete from t_sys_wfleventact_tohander"));	
		organService.sdao().execute(Sqls.create("delete from t_sys_wfleventerror"));		
		organService.sdao().execute(Sqls.create("delete from t_sys_wfleventflow"));	
		organService.sdao().execute(Sqls.create("delete from t_sys_wfleventroute"));	
		organService.sdao().execute(Sqls.create("delete from t_sys_wfleventroute_receiver"));	
		organService.sdao().execute(Sqls.create("delete from t_sys_wflflowassapp"));		
		organService.sdao().execute(Sqls.create("delete from t_sys_wflflowauthor"));	
		organService.sdao().execute(Sqls.create("delete from t_sys_wflflowreader"));	
		organService.sdao().execute(Sqls.create("delete from t_sys_wfract"));	
		organService.sdao().execute(Sqls.create("delete from t_sys_wfractassorter"));		
		organService.sdao().execute(Sqls.create("delete from t_sys_wfracthandler"));	
		organService.sdao().execute(Sqls.create("delete from t_sys_wfractowner"));	
		organService.sdao().execute(Sqls.create("delete from t_sys_wfracttask"));	
		organService.sdao().execute(Sqls.create("delete from t_sys_wfrflow"));	
		organService.sdao().execute(Sqls.create("delete from t_sys_wfrflowauthor"));	
		organService.sdao().execute(Sqls.create("delete from t_sys_wfrflowreader"));	
		organService.sdao().execute(Sqls.create("delete from t_sys_wfrnoract"));	
		organService.sdao().execute(Sqls.create("delete from t_sys_wfwaitwork"));	
		
		return ro;
	}
	
	@At("/clearbusiness")
	@Ok("json")
	public Map clearbusiness() throws Exception
	{		
		organService.sdao().execute(Sqls.create("delete from t_app_plan"));
		organService.sdao().execute(Sqls.create("delete from t_app_partydue"));		
		organService.sdao().execute(Sqls.create("delete from t_app_pdusesuggest"));	
		organService.sdao().execute(Sqls.create("delete from t_app_pdusesuggestdetail"));			
		return ro;
	}

}
