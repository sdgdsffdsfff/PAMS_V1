package com.skynet.pams.app.party.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.app.organ.pojo.GroupUser;
import com.skynet.app.workflow.enginee.WorkFlowEngine;
import com.skynet.app.workflow.pojo.RAct;
import com.skynet.app.workflow.spec.DBFieldConstants;
import com.skynet.app.workflow.spec.GlobalConstants;
import com.skynet.app.workflow.ui.service.UIService;
import com.skynet.app.workflow.vo.VCreate;
import com.skynet.framework.service.GeneratorService;
import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.pams.base.pojo.PartyDue;
import com.skynet.pams.base.pojo.PartyDueUseBudgetDetail;
import com.skynet.pams.base.pojo.Plan;

@InjectName("partydueusebudgetdetailService")
@IocBean(args = { "refer:dao" }) 
public class PartyDueUseBudgetDetailService extends SkynetNameEntityService<PartyDueUseBudgetDetail>
{
	public PartyDueUseBudgetDetailService()
	{
		super();
	}
	
	public PartyDueUseBudgetDetailService(Dao dao)
	{
		super(dao);
	}	
	
	public PartyDueUseBudgetDetailService(Dao dao, Class<PartyDueUseBudgetDetail> entityType)
	{
		super(dao, entityType);
	}
	
	@Inject
	GeneratorService generatorService;
	
	@Inject
	WorkFlowEngine workFlowEngine;
	
	@Inject
	UIService uIService;
	
	// 查询待启动计划
	public String get_browseplan_sql(Map map) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" select plan.id, plan.cname, plan.planstartdate, plan.planenddate, plan.creater, bflow.id flowdefid from t_app_plan plan, t_sys_wfbflow bflow ");
		sql.append("  where 1 = 1 ");
		sql.append("    and plan.flowdefid = bflow.id ");
		sql.append("    and bflow.classid = 'DFGL' ");
		sql.append("    and plan.ctype = '流程' ");
		sql.append("    and plan.state = '计划' ");
		return sql.toString();
	}
		
	// 查询待办记录
	public String get_browsewait_sql(Map map) throws Exception
	{
		String cno = (String)map.get("cno");
		
		StringBuffer sql = new StringBuffer();

		sql.append(" select bv.id, bv.cname, bv.cno, ").append("\n");
		sql.append(uIService.get_browsewait_field(map)).append("\n");
		sql.append(" from t_app_partydue bv, " + uIService.get_browsewait_table(map)).append("\n");
		sql.append(" where 1 = 1 ").append("\n");
		sql.append(uIService.get_browsewait_where(map)).append("\n");
		
		if (!StringToolKit.isBlank(cno))
		{
			sql.append(" and lower(bv.cno) like lower(" + SQLParser.charLikeValue(cno) + ")");
		}		

		sql.append(" order by ract.createtime desc ").append("\n");
		return sql.toString();
		
	}
	
	// 查询已办记录
	public String get_browsehandle_sql(Map map) throws Exception
	{
		String cno = (String)map.get("cno");

		
		StringBuffer sql = new StringBuffer();

		sql.append(" select v.id, v.cname, v.cno, v.bactid, v.bactcname, v.ractcreatetime, v.ractstate, v.runactkey, v.runflowkey, ractowner.ownerctx ractownerctx, usr.cname username, v.zxsc ").append("\n");
		sql.append("  from ( ").append("\n");
		
		sql.append(" select distinct bv.id, bv.cname, bv.cno, ").append("\n");
		sql.append(uIService.get_browsehandle_field(map)).append("\n");
		sql.append(" from t_app_partydue bv, " + uIService.get_browsehandle_table(map)).append("\n");
		sql.append(" where 1 = 1 ").append("\n");
		sql.append(uIService.get_browsehandle_where(map)).append("\n");
		
		if (!StringToolKit.isBlank(cno))
		{
			sql.append(" and lower(bv.cno) like lower(" + SQLParser.charLikeValue(cno) + ")");
		}	
				
		sql.append("   ) v ").append("\n");
		sql.append("   left join t_sys_wfractowner ractowner ").append("\n");
		sql.append("   on v.runactkey = ractowner.runactkey ").append("\n");
		sql.append("   left join t_sys_user usr ").append("\n");
		sql.append("   on ractowner.ownerctx = usr.loginname ").append("\n");
		
		sql.append(" order by v.ractcreatetime desc ").append("\n");
		return sql.toString();
		
	}
	
	// 查询已办记录
	public String get_browseall_sql(Map map) throws Exception
	{
		String cno = (String)map.get("cno");
		
		StringBuffer sql = new StringBuffer();

		sql.append(" select v.id, v.cname, v.cno, v.bactid, v.bactcname, v.ractcreatetime, v.ractstate, v.runactkey, v.runflowkey, ractowner.ownerctx ractownerctx, usr.cname username, v.zxsc ").append("\n");
		sql.append("  from ( ").append("\n");
		
		sql.append(" select distinct bv.id, bv.cname, bv.cno, ").append("\n");
		sql.append(uIService.get_browseall_field(map)).append("\n");
		sql.append(" from t_app_partydue bv, " + uIService.get_browseall_table(map)).append("\n");
		sql.append(" where 1 = 1 ").append("\n");
		sql.append(uIService.get_browseall_where(map)).append("\n");

		if (!StringToolKit.isBlank(cno))
		{
			sql.append(" and lower(bv.cno) like lower(" + SQLParser.charLikeValue(cno) + ")");
		}
		
		sql.append("   ) v ").append("\n");
		sql.append("   left join t_sys_wfractowner ractowner ").append("\n");
		sql.append("   on v.runactkey = ractowner.runactkey ").append("\n");
		sql.append("   left join t_sys_user usr ").append("\n");
		sql.append("   on ractowner.ownerctx = usr.loginname ").append("\n");
		
		sql.append(" order by v.ractcreatetime desc ").append("\n");
		return sql.toString();
	}
	
	//
	public String plancreate(Plan plan, PartyDue partydue, DynamicObject swapFlow) throws Exception
	{
		Timestamp systime = new Timestamp(System.currentTimeMillis());

		// 创建业务数据
		String cno = gen_cno();
		partydue.setCno(cno);
		dao().insert(partydue);
		String id = partydue.getId();

		// 创建流程数据
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String workname = "[" + StringToolKit.formatText(partydue.getCno()) + "]　" + StringToolKit.formatText(partydue.getCname());
		
		VCreate vo = new VCreate();
		vo.flowdefid = swapFlow.getFormatAttr(GlobalConstants.swap_flowdefid);
		vo.priority = "1";
		vo.dataid = partydue.getId();
		vo.tableid = swapFlow.getFormatAttr(GlobalConstants.swap_tableid);
		vo.loginname = swapFlow.getFormatAttr(GlobalConstants.swap_coperatorloginname);
		vo.username = swapFlow.getFormatAttr(GlobalConstants.swap_coperatorcname);
		vo.workname = workname;
		vo.planid = plan.getId();
		
		String runactkey = workFlowEngine.getFlowManager().create(vo);
		RAct ract = workFlowEngine.getActManager().getRactService().get(runactkey);
		String flowdefid = ract.getFlowdefid();
		String actdefid = ract.getActdefid();
		String runflowkey = ract.getRunflowkey();
		Timestamp createtime = ract.getCreatetime();
		
		plan.setRunflowkey(runflowkey);
		plan.setActualstartdate(createtime);
		plan.setState("执行");
		sdao().update(plan);
		
		Plan subplan = sdao().fetch(Plan.class, Cnd.where("parentid","=",plan.getId()).and("flowdefid","=",flowdefid).and("actdefid","=",actdefid));
		subplan.setRunflowkey(runflowkey);
		subplan.setRunactkey(runactkey);
		subplan.setActualstartdate(createtime);
		sdao().update(subplan);

		return runactkey;
	}
	
	public String create(PartyDue partydue, DynamicObject swapFlow) throws Exception
	{
		Timestamp systime = new Timestamp(System.currentTimeMillis());

		// 创建业务数据
		String cno = gen_cno();
		partydue.setCno(cno);
		dao().insert(partydue);
		String id = partydue.getId();

		// 创建流程数据
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String workname = "[" + StringToolKit.formatText(partydue.getCno()) + "]　" + StringToolKit.formatText(partydue.getCname());
		
		VCreate vo = new VCreate();
		vo.flowdefid = swapFlow.getFormatAttr(GlobalConstants.swap_flowdefid);
		vo.priority = "1";
		vo.dataid = partydue.getId();
		vo.tableid = swapFlow.getFormatAttr(GlobalConstants.swap_tableid);
		vo.loginname = swapFlow.getFormatAttr(GlobalConstants.swap_coperatorloginname);
		vo.username = swapFlow.getFormatAttr(GlobalConstants.swap_coperatorcname);
		vo.workname = workname;
		
		String runactkey = workFlowEngine.getFlowManager().create(vo);

		return runactkey;
	}
	
	// 流程权限
	// 是否可读
	public boolean isread(DynamicObject map)
	{
		// 读取权限后期可优化为由系统权限或工作流平台进行设置，权限控制将更为灵活；

		boolean sign = false;

		// 业务数据标识
		// 用户名
		String dataid = (String) map.get(GlobalConstants.swap_dataid);
		String loginname = (String) map.get(GlobalConstants.swap_coperatorloginname);

		long num = 0;

		// 系统管理员具有权限
		if ("admin".equals(loginname))
		{
			sign = true;
			return sign;
		}

		// 应用管理员具有权限
		if (isarole(loginname, "SYSTEM"))
		{
			sign = true;
			return sign;
		}

		// 党费管理员具有权限
		if (isarole(loginname, "DFGL_GLY"))
		{
			sign = true;
			return sign;
		}

		// 信息共享用户具有权限
		if (isarole(loginname, "DFGL_YH"))
		{
			sign = true;
			return sign;
		}

		return sign;
	}
	
	// 是否可写（修改）
	public boolean isedit(DynamicObject map) throws Exception
	{
		boolean sign = workFlowEngine.getActManager().isEdit(map);
		return sign;
	}
	
	// 是否可签收
	public boolean isapply(DynamicObject map) throws Exception
	{
		boolean sign = workFlowEngine.getActManager().isApply(map);
		return sign;
	}
	
	// 可否转发
	public boolean isforward(DynamicObject map) throws Exception
	{
		boolean sign = workFlowEngine.getActManager().isForward(map);
		return sign;
	}
	
	// 可否收回
	public boolean iscallback(DynamicObject map) throws Exception
	{
		boolean sign = workFlowEngine.getActManager().isCallBack(map);
		return sign;
	}
	
	// 可否退回
	public boolean isbackward(DynamicObject map) throws Exception
	{
		boolean sign = workFlowEngine.getActManager().isBackward(map);
		return sign;
	}
	
	// 可否保存
	public boolean issave(DynamicObject map) throws Exception
	{
		boolean sign = false;
		boolean isedit = workFlowEngine.getActManager().isEdit(map);
		
		if(!isedit)
		{
			sign = false;
			return sign;
		}
		
		String runflowkey = map.getFormatAttr(GlobalConstants.swap_runflowkey);
		String state = workFlowEngine.getFlowManager().getRflowService().locate(runflowkey).getFormatAttr("state");
		
		// 流程在起草环节，允许保存
		if ("起草".equals(state))
		{
			sign = true;
			return sign;
		}
		
		return sign;
	}
	
	// 可否删除多条
	public boolean isdeletes(DynamicObject map) throws Exception
	{
		boolean sign = false;
		String loginname = (String) map.get(GlobalConstants.swap_coperatorloginname);

		// 系统管理员具有权限
		if ("admin".equals(loginname))
		{
			sign = true;
			return sign;
		}

		// 应用管理员具有权限
		if (isarole(loginname, "SYSTEM"))
		{
			sign = true;
			return sign;
		}

		// 信息共享管理员具有该功能；
		if (isarole(loginname, "GXGL_GLY"))
		{
			sign = true;
			return sign;
		}

		return sign;
	}
	
	// 可否删除
	public boolean isdelete(DynamicObject map) throws Exception
	{
		boolean sign = false;

		// 业务数据标识
		// 用户名
		String dataid = map.getFormatAttr(GlobalConstants.swap_dataid);
		String loginname = map.getFormatAttr(GlobalConstants.swap_coperatorloginname);
		String userid = map.getFormatAttr(GlobalConstants.swap_coperatorid);
		String tableid = "InfoShare";
		String runactkey = map.getFormatAttr("runactkey");
		String runflowkey = map.getFormatAttr("runflowkey");
		
		DynamicObject ract = workFlowEngine.getActManager().getRactService().locate(runactkey);
		
		String actdefid = workFlowEngine.getActManager().getRactService().locate(runactkey).getFormatAttr("actdefid");
		String flowdefid = ract.getFormatAttr("flowdefid");
		String ctype = "PERSON";

		// 检查流程是否终止或结束 不允许删除,终止的视为流转过
		String state = workFlowEngine.getFlowManager().getRflowService().locate(runflowkey).getFormatAttr("state");

		// 系统管理员具有权限
		if ("admin".equals(loginname))
		{
			sign = true;
			return sign;
		}

		// 应用管理员具有权限
		if (isarole(loginname, "SYSTEM"))
		{
			sign = true;
			return sign;
		}

		// 信息共享管理员具有权限
		if (isarole(loginname, "GXGL_GLY"))
		{
			sign = true;
			return sign;
		}

		if (DBFieldConstants.RFlOW_STATE_COMPLETED.equals(state) || DBFieldConstants.RFlOW_STATE_TERMINATED.equals(state))
		{
			sign = false;
			return sign;
		}
		// 判断流程是否允许删除
		boolean enabledelete = workFlowEngine.getFlowManager().getRflowService().checkflowforwarded(runflowkey);// 值真表示转未发过，值为false表示转发过

		if (enabledelete)
		{
			sign = true;
			return sign;
		}

		return sign;
	}
	
	// 当前用户是否为指定的角色
	public boolean isarole(String loginname, String rolename)
	{
		boolean sign = false;

		int num = 0;
		
		num = sdao().count(GroupUser.class, Cnd.where("groupname","=",rolename).and("grouptype","=","ROLE").and("loginname","=",loginname));

		if (num > 0)
		{
			sign = true;
			return sign;
		}

		return sign;
	}
	
	
	public String gen_cno() throws Exception
	{
		// 生成编号
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		String sysdate = sf.format(date);
		String csql = " select substr(max(cno),length(max(cno))-3, 4) as cno from t_app_partydue where to_char(createtime,'yyyy-mm-dd') = to_char(to_date('" + sysdate + "', 'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd')";
		String express = "$yy$mm$dd####";

		Map map = new HashMap();
		map.put("csql", csql);
		map.put("express", express);

		return generatorService.getNextValue(map);
	}

	public WorkFlowEngine getWorkFlowEngine() {
		return workFlowEngine;
	}

	public void setWorkFlowEngine(WorkFlowEngine workFlowEngine) {
		this.workFlowEngine = workFlowEngine;
	}
	
	
}
