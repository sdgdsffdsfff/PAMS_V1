package com.skynet.app.workflow.enginee;

import java.io.Reader;
import java.io.StringReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.app.workflow.expression.ConditionLexer;
import com.skynet.app.workflow.expression.ConditionParser;
import com.skynet.app.workflow.expression.WfCondAST;
import com.skynet.app.workflow.expression.formula.FormulaParser;
import com.skynet.app.workflow.pojo.RAct;
import com.skynet.app.workflow.pojo.RFlow;
import com.skynet.app.workflow.pojo.WFWaitWork;
import com.skynet.app.workflow.service.BActService;
import com.skynet.app.workflow.service.BFlowService;
import com.skynet.app.workflow.service.BFormService;
import com.skynet.app.workflow.service.BRouteService;
import com.skynet.app.workflow.service.LEventActService;
import com.skynet.app.workflow.service.LEventFlowService;
import com.skynet.app.workflow.service.LEventRouteReceiverService;
import com.skynet.app.workflow.service.LEventRouteService;
import com.skynet.app.workflow.service.LEventService;
import com.skynet.app.workflow.service.LFlowAssAppService;
import com.skynet.app.workflow.service.OrgService;
import com.skynet.app.workflow.service.RActAssorterService;
import com.skynet.app.workflow.service.RActHandlerService;
import com.skynet.app.workflow.service.RActOwnerService;
import com.skynet.app.workflow.service.RActService;
import com.skynet.app.workflow.service.RActTaskService;
import com.skynet.app.workflow.service.RFlowAuthorService;
import com.skynet.app.workflow.service.RFlowReaderService;
import com.skynet.app.workflow.service.RFlowService;
import com.skynet.app.workflow.service.WFWaitWorkService;
import com.skynet.app.workflow.spec.DBFieldConstants;
import com.skynet.app.workflow.spec.GlobalConstants;
import com.skynet.app.workflow.vo.VActUser;
import com.skynet.app.workflow.vo.VApply;
import com.skynet.app.workflow.vo.VBackward;
import com.skynet.app.workflow.vo.VForward;
import com.skynet.framework.common.generator.TimeGenerator;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.framework.services.function.Types;

@InjectName("actManager")
@IocBean
public class ActManager {
	
	protected DynamicObject swapFlow = new DynamicObject();
	
	@Inject
	private BActService bactService;
	
	@Inject
	private BFlowService bflowService;
	
	@Inject
	private BRouteService brouteService;	
	
	@Inject
	private BFormService bformService;
	
	@Inject
	private RFlowService rflowService;
	
	@Inject
	private RActService ractService;
	
	@Inject
	private RActHandlerService racthandlerService;
	
	@Inject
	private RActOwnerService ractownerService;
	
	@Inject
	private RActAssorterService ractassorterService;
	
	@Inject
	private RFlowAuthorService rflowauthorService;
	
	@Inject
	private RFlowReaderService rflowreaderService;
	
	@Inject
	private RActTaskService racttaskService;
	
	@Inject
	private LEventService leventService;
	
	@Inject
	private LEventFlowService leventflowService;
	
	@Inject
	private LEventActService leventactService;

	@Inject
	private LEventRouteService leventrouteService;
	
	@Inject
	private LEventRouteReceiverService leventroutereceiverService;

	@Inject
	private WFWaitWorkService waitworkService;
		
	@Inject
	private LFlowAssAppService lflowassappService;
	
	// 转发
	public List<DynamicObject> vforward(VForward vo) throws Exception
	{
		init_forward(vo);
		
		// 转发前检查
		checks_before_forward(vo);
		
		// 开始转发
		List<DynamicObject> acts_forward = forward(vo);
		
		// 转发结束
		forward_complete(vo);
		
		// 更新计划
		update_old_ract_plan(vo.runactkey);
		
		for(int i=0;i<acts_forward.size();i++)
		{
			update_new_ract_plan(acts_forward.get(i).getFormatAttr("runactkey"));
		}
		
		for(int i=0;i<acts_forward.size();i++)
		{
			String runactkey_route = acts_forward.get(i).getFormatAttr("runactkey");
			List<DynamicObject> actowners = ractownerService.findByCond(Cnd.where("runactkey", "=", runactkey_route));
			acts_forward.get(i).setObj("actowners", actowners);
		}
		
		return acts_forward;
	}
	
	// 回退
	public void vbackward(VBackward vo) throws Exception
	{
		// 回退前检查
		checks_before_backward(vo);
		
		// 开始回退
		backward(vo);
		
		// 回退结束
		backward_complete(vo);
	}
	
	public void init_forward(VForward vo) throws Exception
	{
		String runactkey = vo.runactkey;
		DynamicObject ract = getRactService().locate(runactkey);
		vo.runflowkey = ract.getFormatAttr("runflowkey");
		vo.beginactdefid = ract.getFormatAttr("actdefid");
		vo.tableid = ract.getFormatAttr("tableid");
		vo.dataid = ract.getFormatAttr("dataid");
		vo.flowdefid = ract.getFormatAttr("flowdefid");
	}
	
	public void checks_before_forward(VForward vo) throws Exception
	{
		String runactkey = vo.runactkey;
		String runflowkey = vo.runflowkey;
		String userid = vo.userid;
		String loginname = vo.loginname;
		String usertype = vo.usertype;
		// 根据活动标识找到当前实例
		
		// 检查流程是否已经结束
		check_rflow_complete(runflowkey);

		// 判断当前活动是否已经转发完成过
		check_ract_isforward(runactkey);
		
		// 检查是否有转发权限
		check_ract_enableforward(runactkey, loginname, usertype);
		
		// 检查当前活动的子流程是否结束
		check_subflow_end(runactkey);		
	}
	
	public List<DynamicObject> forward(VForward vo) throws Exception
	{
		String runactkey = vo.runactkey;
		String runflowkey = vo.runflowkey;
		DynamicObject ract = getRactService().locate(runactkey);
		String handletype = ract.getFormatAttr("handletype");
		
		List<DynamicObject> racts_forward = new ArrayList();
		
		if("普通".equals(handletype))
		{
			racts_forward = forward_normal(vo);
		}
		else
		if("多部门并行".equals(handletype))
		{
			racts_forward = forward_async_depts(vo);
		}
		
		return racts_forward;
	}
	
	// 普通活动转发
	public List<DynamicObject> forward_normal(VForward vo) throws Exception
	{
		String runactkey = vo.runactkey;
		String runflowkey = vo.runflowkey;
		String beginactdefid = vo.beginactdefid;
		
		// 检查活动前驱、后继路由
		check_routes(runactkey);

		// 记录当前活动处理人
		// create_racthandler(vo); // 签收时就已指定了处理人
		
		// 当前活动状态置为完成
		complete_ract(vo);
		
		// 清除当前活动流程作者列表
		clear_ract_author(vo);
		
		// 清除当前活动所有待办工作
		clear_ract_waitwork(runactkey);
		
		// 更新当前活动可用状态
		clear_ract_isuse(runflowkey, beginactdefid);
		
		// 记录转发后的目标活动实例清单
		List<DynamicObject> racts_forward = new ArrayList<DynamicObject>();
		
		// 根据后继路由列表逐项转发
		for(int i=0;i<vo.endacts.size();i++)
		{
			racts_forward.addAll(ract_forward(vo, i));
		}
		
		return racts_forward;
	}
	
	// 多部门并行转发
	public List<DynamicObject> forward_async_depts(VForward vo) throws Exception
	{
		// 记录转发后的目标活动实例清单
		List<DynamicObject> racts_forward = new ArrayList<DynamicObject>();
		
		String runactkey = vo.runactkey;
		String runflowkey = vo.runflowkey;
		String beginactdefid = vo.beginactdefid;
		
		// 检查活动前驱、后继路由
		check_routes(runactkey);

		// 记录当前活动处理人
		// create_racthandler(vo); // 签收时就已指定了处理人
		
		// 当前活动状态置为完成
		complete_ract(vo);
		
		// 清除当前活动流程作者列表
		clear_ract_author(vo);
		
		// 清除当前活动所有待办工作
		clear_ract_waitwork(runactkey);
		
		// 更新当前活动可用状态
		clear_ract_isuse(runflowkey, beginactdefid);
		
		// 检查当前并行活动子实例是否全部完成
		// 全部完成则转发主活动实例
		forward_sync_depts_sup(vo);
		
		return racts_forward;
	}
	
	// 检查当前并行活动子实例是否全部完成
	public List<DynamicObject> forward_sync_depts_sup(VForward vo) throws Exception
	{
		List<DynamicObject> racts_forward = new ArrayList<DynamicObject>();
		
		String runactkey = vo.runactkey;
		String suprunactkey = ractService.locate(runactkey).getFormatAttr("suprunactkey");
		int num_complete = ractService.sdao().count(RAct.class, Cnd.where("suprunactkey","=",suprunactkey).and("state", "=", DBFieldConstants.RACT_STATE_COMPLETED));
		int num_subs = ractService.sdao().count(RAct.class, Cnd.where("suprunactkey","=",suprunactkey));
		if(num_complete>=num_subs)
		{
			VForward svo = vo;
			svo.runactkey = suprunactkey;
			racts_forward = forward_normal(svo);
		}
		
		return racts_forward;
	}
	
	// 
	public int async_uncomplete_num(String suprunactkey) throws Exception
	{
		int num_complete = ractService.sdao().count(RAct.class, Cnd.where("suprunactkey","=",suprunactkey).and("state", "=", DBFieldConstants.RACT_STATE_COMPLETED));
		int num_subs = ractService.sdao().count(RAct.class, Cnd.where("suprunactkey","=",suprunactkey));
		return num_subs - num_complete;
	}
	

	
	public void update_old_ract_plan(String runactkey) throws Exception
	{
		//此种代码方式是为了不再引用计划的项目包。
		RAct ract = ractService.get(runactkey);
		String runflowkey = ract.getRunflowkey();
		String actdefid = ract.getActdefid();
		// 查找流程对应的活动上级计划
		DynamicObject plan = getRactService().sdao().queryForMap("select * from t_app_plan where 1 = 1 and runflowkey = " + SQLParser.charValue(runflowkey));
		String planid = plan.getFormatAttr("id");
		
		// 更新新节点时间
		int updates = getRactService().sdao().update("t_app_plan", Chain.make("actualenddate", ract.getCompletetime()), Cnd.where("parentid","=",planid).and("actdefid","=",actdefid));
		System.out.println("update " + updates + " rows.");
	}
	
	public void update_new_ract_plan(String runactkey) throws Exception
	{
		//此种代码方式是为了不再引用计划的项目包。
		// 查找流程对应的活动上级计划
		RAct ract = ractService.get(runactkey);
		String runflowkey = ract.getRunflowkey();
		RFlow rflow = rflowService.get(runflowkey);
		String planid = rflow.getPlanid();
		String actdefid = ract.getActdefid();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from t_app_plan where 1 = 1 ");
		sql.append(" and parentid = ").append(SQLParser.charValue(planid));
		sql.append(" and actdefid = ").append(SQLParser.charValue(actdefid));
		DynamicObject subplan = ractService.sdao().queryForMap(sql.toString());
		String subplanid = subplan.getFormatAttr("id");

		int updates = 0;
		
		updates = getRactService().sdao().update("t_sys_wfract", Chain.make("planid", subplanid), Cnd.where("runactkey", "=", runactkey));
		System.out.println("update " + updates + " rows.");
		
		updates = getRactService().sdao().update("t_app_plan", Chain.make("runflowkey", runflowkey).add("runactkey", runactkey).add("actualstartdate", ract.getCreatetime()), Cnd.where("parentid","=",planid).and("actdefid","=",actdefid));
		System.out.println("update " + updates + " rows.");
	}
	
	// 检查流程是否已经结束
	public void check_rflow_complete(String runflowkey) throws Exception
	{
		DynamicObject obj_rflow = rflowService.locate(runflowkey);
		String state = obj_rflow.getFormatAttr("state");
		if (state.equals(DBFieldConstants.RFlOW_STATE_COMPLETED))
		{
			throw new RuntimeException("当前流程已经结束，不能转发！");
		}
		if (state.equals(DBFieldConstants.RFlOW_STATE_SUSPENDED))
		{
			throw new RuntimeException("当前流程被挂起，不能转发！");
		}
		if (state.equals(DBFieldConstants.RFlOW_STATE_TERMINATED))
		{
			throw new RuntimeException("当前流程被终止，不能转发！");
		}
		if (state.equals(DBFieldConstants.RFlOW_STATE_RUNNING))
		{
			throw new RuntimeException("当前流程正在准备阶段，不能转发！");
		}
	}
	
	// 检查是否具有转发权限
	public void check_ract_enableforward(String runactkey, String ownerctx, String ctype) throws Exception
	{
		if(!ractService.enable_forward(runactkey, ownerctx, ctype))
		{
			throw new RuntimeException("当前用户没有转发该活动的权限！");
		}
	}
	
	// 检查流程是否已经结束
	public void check_ract_isforward(String runactkey) throws Exception
	{
		if (ractService.isforward(runactkey))
		{
			throw new RuntimeException("该活动已经完成，不允许重复转发！");
		}
	}
	
	public void check_subflow_end(String runactkey) throws Exception
	{
		
	}
	
	public void check_routes(String runactkey) throws Exception
	{
		DynamicObject obj_ract = ractService.locate(runactkey);
		String join = obj_ract.getFormatAttr("join");
		String split = obj_ract.getFormatAttr("split");
		String flowdefid = obj_ract.getFormatAttr("bflowid"); // 代码检查
		
		String tableid = obj_ract.getFormatAttr("tableid");
		String dataid = obj_ract.getFormatAttr("dataid");
		String runflowkey = obj_ract.getFormatAttr("runflowkey");

		if (join.equals("AND"))
		{
			List beforeacts = ractService.findCloseLoopActsFromEndAllComp(runflowkey, runactkey, tableid, dataid);
			
			if (beforeacts.size() > 0)
			{
				String msg = new String();
				for(int i=0;i<beforeacts.size();i++)
				{
					DynamicObject beforeactObj = (DynamicObject)beforeacts.get(i);
					msg += beforeactObj.getFormatAttr("cname");
					if(i<beforeacts.size()-1)
					{
						msg += ",";
					}
				}
				// 异常处理后续优化，不再放置代码处理输出信息。
				throw new RuntimeException("需要等待以下活动[<font color=\"red\">" + msg + "</font>]完成，现在不允许转发！");
			}
		}
		else
		if (join.equals("COPY"))
		{
			List beforeacts = ractService.findCopyRoutesActsFromEndAllComp(runflowkey, runactkey, tableid, dataid, "MAIN");
			
			if (beforeacts.size() > 0)
			{
				String msg = new String();
				for(int i=0;i<beforeacts.size();i++)
				{
					DynamicObject beforeactObj = (DynamicObject)beforeacts.get(i);
					msg += beforeactObj.getFormatAttr("cname");
					if(i<beforeacts.size()-1)
					{
						msg += ",";
					}
				}
				throw new RuntimeException("需要等待以下活动[<font color=\"red\">" + msg + "</font>]完成，现在不允许转发！");
			}
			else
			{
				// 将所有从属线上活动状态置为已处理
				ractService.updateCopyRoutesActsFromEndAll(runflowkey, runactkey, tableid, "SLAVE");
			}
		}
	}
	
	public void create_racthandler(VForward vo) throws Exception
	{
		String userid = vo.userid;
		String loginname = vo.loginname;
		String username = vo.username;
		String usertype = vo.usertype;
		String deptid = vo.deptid;
		String deptcname = vo.deptcname;
		String orgid = vo.orgid;
		String orgname = vo.orgname;
		String flowdefid = vo.flowdefid;
		String tableid = vo.tableid;
		String dataid = vo.dataid;
		String runactkey = vo.runactkey;
		String runflowkey = vo.runflowkey;
		String beginactdefid = vo.beginactdefid;
		
		// .添加当前活动的处理人
		racthandlerService.create(runactkey, loginname, username, DBFieldConstants.PUB_PARTICIPATOR_PERSON, tableid);
	}
	
	public void complete_ract(VForward vo) throws Exception
	{
		//String userid = vo.userid;
		String loginname = vo.loginname;
		String username = vo.username;
		String usertype = vo.usertype;
		String deptid = vo.deptid;
		String deptcname = vo.deptcname;
		String orgid = vo.orgid;
		String orgname = vo.orgname;
		String flowdefid = vo.flowdefid;
		String tableid = vo.tableid;
		String dataid = vo.dataid;
		String runactkey = vo.runactkey;
		String runflowkey = vo.runflowkey;
		String beginactdefid = vo.beginactdefid;
		
		// .将当前活动实例状态改为完成
		// 添加日志事件
		String sql = SQL_FORWARD_NORMAL_UPDATEACTSTATE_TABLE(runactkey, tableid);
		ractService.dao().execute(Sqls.create(sql));
		
		String c_id_event = leventService.create(TimeGenerator.getTimeStr(), DBFieldConstants.LEVENT_EVENTTYPE_ACT, runflowkey);

		leventactService.create(c_id_event, loginname, username, deptid, deptcname, DBFieldConstants.RACT_STATE_ACTIVE, DBFieldConstants.RACT_STATE_COMPLETED, beginactdefid, runactkey, flowdefid, runflowkey);
	}
	
	public void clear_ract_author(VForward vo) throws Exception
	{
		String tableid = vo.tableid;
		String dataid = vo.dataid;
		String runactkey = vo.runactkey;
		String runflowkey = vo.runflowkey;
		
		// 先清除原有的作者列表
		// 只删除当前活动所具有的作者				
		rflowauthorService.remove(runflowkey, runactkey, tableid);
	}
	
	public void clear_ract_waitwork(String runactkey) throws Exception
	{
		// 清除所有当前活动所有待办工作
		waitworkService.remove(runactkey);
	}
	
	public void clear_ract_isuse(String runflowkey, String actdefid) throws Exception
	{
		ractService.clear_ract_isuse(runflowkey, actdefid);
	}
	
	public List<DynamicObject> ract_forward(VForward vo, int i) throws Exception
	{
		// 检查后继路由任务的必需任务是否已完成
		check_route_task(vo, i);
		
		// 检查后继路由条件是否满足
		check_route_cond(vo, i);
		
		// 检查后继活动的类型，决定能否创建后继活动
		List<DynamicObject> racts_routed = create_forwardact(vo, i);
		
		return racts_routed;
	}
	
	public void check_route_task(VForward vo, int i) throws Exception
	{
		//String userid = vo.userid;
		String loginname = vo.loginname;
		String username = vo.username;
		String usertype = vo.usertype;
		String deptid = vo.deptid;
		String deptcname = vo.deptcname;
		String orgid = vo.orgid;
		String orgname = vo.orgname;
		String flowdefid = vo.flowdefid;
		String tableid = vo.tableid;
		String dataid = vo.dataid;
		String runactkey = vo.runactkey;
		String runflowkey = vo.runflowkey;
		String beginactdefid = vo.beginactdefid;
		
		String endactdefid = vo.endacts.get(i).actdefid;
		String sql = SQL_FORWARD_NORMAL_FINDROUTE(vo.beginactdefid, endactdefid);
		DynamicObject obj = ractService.sdao().queryForMap(sql);
		String id = obj.getAttr("id");
		String cname = obj.getAttr("cname");
		String routetype = obj.getAttr("routetype");
		String conditions = obj.getAttr("conditions");
		String startactid = obj.getAttr("startactid");
		String endactid = obj.getAttr("endactid");
		String flowid = obj.getAttr("flowid");
		
		List actowers = vo.endacts.get(i).actowners;
		List agenters = vo.endacts.get(i).actagenters;
		
		// .根据活动实例标识查找所有必需的任务
		// [如果必需的任务尚未完成，不允许转发]
		//sql = SQL_FORWARD_FINDACTTASK(runactkey);
		sql = SQL_FORWARD_FINDACTTASK(tableid, dataid, beginactdefid, endactid);
		List requires = ractService.queryForList(sql.toString());
		if (requires.size() > 0)
		{
			String msg = new String();
			for(int loop=0;loop<requires.size();loop++)
			{
				DynamicObject rtask = (DynamicObject)requires.get(loop);
				msg += rtask.getFormatAttr("cname");
				if(loop<requires.size()-1)
				{
					msg += ",";
				}
			}
			// 后续提示信息方式进行优化
			throw new RuntimeException("需要完成以下任务[<font color=\"red\">" + msg + "</font>]，现在不允许转发！");
		}
	}
	
	public boolean check_route_cond(VForward vo, int i) throws Exception
	{
		String runflowkey = vo.runflowkey;
		String endactdefid = vo.endacts.get(i).actdefid;
		String sql = SQL_FORWARD_NORMAL_FINDROUTE(vo.beginactdefid, endactdefid);
		DynamicObject obj = ractService.sdao().queryForMap(sql);

		String routetype = obj.getAttr("routetype");
		String conditions = obj.getAttr("conditions");
		
		// .解析条件公式，判断路由条件是否满足
		// if(conditions == null)
		if(StringToolKit.isBlank(conditions))
		{
			conditions = "1 = 1";
		}
		System.out.println("conditions: " + conditions);

		Reader reader = new StringReader(conditions);

		DynamicObject swapFlow = new DynamicObject();
		swapFlow.setAttr("runflowkey", runflowkey);

		String value = null;
		ConditionLexer lexer = new ConditionLexer(reader);
		ConditionParser parser = new ConditionParser(lexer);
		parser.setFlowSwap(swapFlow);

		try
		{
			parser.condition();
			WfCondAST wfAST = (WfCondAST) parser.getAST();
			value = wfAST.value();
		}
		catch(Exception e)
		{
			throw new RuntimeException("检查转发条件时出错，请检查流程定义！");			
		}
		
		System.out.println("conditions : " + value);
		
		if ("true".equalsIgnoreCase(value))
		{
			return true;
		}
		else
		{
			throw new RuntimeException("转发条件不满足，不允许转发！");
		}
	}
	
	public List<DynamicObject> create_forwardact(VForward vo, int i) throws Exception
	{
		List<DynamicObject> endacts = new ArrayList<DynamicObject>();
		
		//String userid = vo.userid;
		String loginname = vo.loginname;
		String username = vo.username;
		String usertype = vo.usertype;
		String deptid = vo.deptid;
		String deptcname = vo.deptcname;
		String orgid = vo.orgid;
		String orgname = vo.orgname;
		String flowdefid = vo.flowdefid;
		String tableid = vo.tableid;
		String dataid = vo.dataid;
		String runactkey = vo.runactkey;
		String runflowkey = vo.runflowkey;
		String beginactdefid = vo.beginactdefid;
		String endactdefid = vo.endacts.get(i).actdefid;
		
		DynamicObject obj_endact = bactService.locate(endactdefid);
		String handletype = obj_endact.getAttr("handletype");
		String formid = obj_endact.getFormatAttr("formid");
		String priority = obj_endact.getFormatAttr("priority");
		String ctype = obj_endact.getFormatAttr("ctype");
		String join = obj_endact.getFormatAttr("join");
		String cname = obj_endact.getFormatAttr("cname");
		
		// 目标活动类型是结束
		if ("END".equals(ctype))
		{
			RAct ract = new RAct();
			ract.setRunflowkey(runflowkey);
			ract.setCreatetime(new Timestamp(System.currentTimeMillis()));
			ract.setFlowdefid(flowdefid);
			ract.setActdefid(endactdefid);
			ract.setState(DBFieldConstants.RACT_STATE_INACTIVE);
			ract.setTableid(tableid);
			ract.setDataid(dataid);
			ract.setHandletype(handletype);
			ract.setCname(cname);
			
			String runactkey_routed = ractService.create(ract);
			DynamicObject runact_routed = ractService.locate(runactkey_routed);
			log_act_end(vo, runact_routed);
			endacts.add(runact_routed);
			return endacts;
		}

		// 目标活动汇聚类型是AND
		if("AND".equals(join))
		{
			// 如果后继活动汇聚类型为AND，并且已经有对应的实例，则不创建后继活动实例
			String sql = SQL_FORWARD_EXISTANDACT(endactdefid, tableid);
			List exist_obj = ractService.sdao().queryForList(sql.toString());
			
			if (exist_obj.size() > 0)
			{
				String runactkey_routed = ((DynamicObject) exist_obj.get(0)).getAttr("runactkey");
				DynamicObject runact_routed = ractService.locate(runactkey_routed);
				log_act_andhas(vo, runact_routed);
				endacts.add(runact_routed);
				return endacts;
			}
		}

		// 普通活动
		RAct ract = new RAct();
		ract.setRunflowkey(runflowkey);
		ract.setCreatetime(new Timestamp(System.currentTimeMillis()));
		ract.setFlowdefid(flowdefid);
		ract.setActdefid(endactdefid);
		ract.setState(DBFieldConstants.RACT_STATE_INACTIVE);
		ract.setTableid(tableid);
		ract.setDataid(dataid);
		ract.setHandletype(handletype);
		ract.setCname(cname);
		
		String runactkey_routed = ractService.create(ract);
		DynamicObject runact_routed = ractService.locate(runactkey_routed);
		log_act_normal(vo, runact_routed);
		
		// 后继活动的初始工作
		ract_init_normal(vo, i, runact_routed);

		endacts.add(runact_routed);				

		// 目标活动类型是多人串行
		// 目标活动类型是多人并行
		// 目标活动类型是多部门串行
		// 目标活动类型是多部门并行
		if("多部门并行".equals(handletype))
		{
			ract.setIspackage("Y");
			ractService.sdao().update(ract);
			
			// 根据选中的活动所有者顺序清单，创建多个相同活动子实例
			int owner_nums = vo.endacts.get(i).actowners.size();
			for(int j=0;j<owner_nums;j++)
			{
				RAct ract_sub = new RAct();
				ract_sub.setRunflowkey(runflowkey);
				ract_sub.setCreatetime(new Timestamp(System.currentTimeMillis()));
				ract_sub.setFlowdefid(flowdefid);
				ract_sub.setActdefid(endactdefid);
				ract_sub.setState(DBFieldConstants.RACT_STATE_INACTIVE);
				ract_sub.setTableid(tableid);
				ract_sub.setDataid(dataid);
				ract_sub.setHandletype(handletype);
				ract_sub.setCname(cname);
				ract_sub.setOrdernum(j);
				ract_sub.setSuprunflowkey(runflowkey);
				ract_sub.setSuprunactkey(runactkey_routed);
				
				String runactkey_routed_sub = ractService.create(ract_sub);
				DynamicObject runact_routed_sub = ractService.locate(runactkey_routed_sub);
				log_act_normal(vo, runact_routed_sub);
				// 后继活动的初始工作
				ract_init_normal(vo, i, j, runact_routed_sub);
			}
		}
		
		return endacts;
	}
	
	public void log_act_end(VForward vo, DynamicObject ract_routed) throws Exception
	{
		//String userid = vo.userid;
		String loginname = vo.loginname;
		String username = vo.username;
		String usertype = vo.usertype;
		String deptid = vo.deptid;
		String deptcname = vo.deptcname;
		String orgid = vo.orgid;
		String orgname = vo.orgname;
		String flowdefid = vo.flowdefid;
		String tableid = vo.tableid;
		String dataid = vo.dataid;
		String runactkey = vo.runactkey;
		String runflowkey = vo.runflowkey;
		String beginactdefid = vo.beginactdefid;
		
		String endactdefid = ract_routed.getFormatAttr("actdefid");
		
		String handletype = bactService.locate(endactdefid).getFormatAttr("handletype");
		
		String runactkey_routed = ract_routed.getFormatAttr("runactkey");
		
		// 新增路由事件
		String id_event = leventService.create(TimeGenerator.getTimeStr(), DBFieldConstants.LEVENT_EVENTTYPE_ROUTE, runflowkey);
		leventrouteService.create(id_event, flowdefid, runflowkey, loginname, username, endactdefid, runactkey_routed, beginactdefid, runactkey, DBFieldConstants.LEVENTROUTE_EVENTTYPE_FORWARD);
		
		id_event = leventService.create(TimeGenerator.getTimeStr(), DBFieldConstants.LEVENT_EVENTTYPE_ACT, runflowkey);
		leventactService.create(id_event, loginname, username, deptid, deptcname, DBFieldConstants.RACT_STATE_NULL, DBFieldConstants.RACT_STATE_INACTIVE, endactdefid, runactkey_routed, flowdefid, runflowkey);
		id_event = leventService.create(TimeGenerator.getTimeStr(), DBFieldConstants.LEVENT_EVENTTYPE_ACT, runflowkey);
		leventactService.create(id_event, loginname, username, deptid, deptcname, DBFieldConstants.RACT_STATE_INACTIVE, DBFieldConstants.RACT_STATE_ACTIVE, endactdefid, runactkey_routed, flowdefid, runflowkey);
		id_event = leventService.create(TimeGenerator.getTimeStr(), DBFieldConstants.LEVENT_EVENTTYPE_ACT, runflowkey);
		leventactService.create(id_event, loginname, username, deptid, deptcname, DBFieldConstants.RACT_STATE_ACTIVE, DBFieldConstants.RACT_STATE_COMPLETED, endactdefid, runactkey_routed, flowdefid, runflowkey);
		
		String sql = SQL_FORWARD_UPDATEFLOWSTATE(runflowkey, DBFieldConstants.RFlOW_STATE_COMPLETED, tableid);
		ractService.dao().execute(Sqls.create(sql.toString()));
		leventflowService.create(id_event, loginname, DBFieldConstants.RFlOW_STATE_ACTIVE, DBFieldConstants.RFlOW_STATE_COMPLETED, flowdefid, runflowkey, DBFieldConstants.LEVENTFLOW_EVENTTYPE_COMPLETE);
	}
	
	// 已有实例的AND活动
	public void log_act_andhas(VForward vo, DynamicObject ract_routed) throws Exception
	{
		//String userid = vo.userid;
		String loginname = vo.loginname;
		String username = vo.username;
		String usertype = vo.usertype;
		String deptid = vo.deptid;
		String deptcname = vo.deptcname;
		String orgid = vo.orgid;
		String orgname = vo.orgname;
		String flowdefid = vo.flowdefid;
		String tableid = vo.tableid;
		String dataid = vo.dataid;
		String runactkey = vo.runactkey;
		String runflowkey = vo.runflowkey;
		String beginactdefid = vo.beginactdefid;
		
		String endactdefid = ract_routed.getFormatAttr("actdefid");
		String handletype = bactService.locate(endactdefid).getFormatAttr("handletype");
		String runactkey_routed = ract_routed.getFormatAttr("runactkey");
		
		// .创建日志 路由事件
		String id_event = leventService.create(TimeGenerator.getTimeStr(), DBFieldConstants.LEVENT_EVENTTYPE_ROUTE, runflowkey);
		leventrouteService.create(id_event, flowdefid, runflowkey, loginname, username, endactdefid, runactkey_routed, beginactdefid, runactkey, DBFieldConstants.LEVENTROUTE_EVENTTYPE_FORWARD);
	}
	
	// 普通类型活动日志
	public void log_act_normal(VForward vo, DynamicObject ract_routed) throws Exception
	{
		//String userid = vo.userid;
		String loginname = vo.loginname;
		String username = vo.username;
		String usertype = vo.usertype;
		String deptid = vo.deptid;
		String deptcname = vo.deptcname;
		String orgid = vo.orgid;
		String orgname = vo.orgname;
		String flowdefid = vo.flowdefid;
		String tableid = vo.tableid;
		String dataid = vo.dataid;
		String runactkey = vo.runactkey;
		String runflowkey = vo.runflowkey;
		String beginactdefid = vo.beginactdefid;
		
		String endactdefid = ract_routed.getFormatAttr("actdefid");
		String handletype = bactService.locate(endactdefid).getFormatAttr("handletype");
		String runactkey_routed = ract_routed.getFormatAttr("runactkey");
		
		String id_event = leventService.create(TimeGenerator.getTimeStr(), DBFieldConstants.LEVENT_EVENTTYPE_ROUTE, runflowkey);
		leventrouteService.create(id_event, flowdefid, runflowkey, loginname, username, endactdefid, runactkey_routed, beginactdefid, runactkey, DBFieldConstants.LEVENTROUTE_EVENTTYPE_FORWARD);

		// .路由指定人员
		List owners = getRActOwners(runactkey_routed, tableid);
		for(int j=0;j<owners.size();j++)
		{
			DynamicObject o = (DynamicObject)owners.get(j);
			String ownerctx = o.getFormatAttr("ownerctx");
			String ownerctype = o.getFormatAttr("ctype");
			String ownercname = o.getFormatAttr("cname");
			
			leventroutereceiverService.create(id_event, ownerctx, ownercname, ownerctype);	
		}
		
		// 活动事件
		id_event = leventService.create(TimeGenerator.getTimeStr(), DBFieldConstants.LEVENT_EVENTTYPE_ACT, runflowkey);
		leventactService.create(id_event, loginname, username, deptid, deptcname, DBFieldConstants.RACT_STATE_NULL, DBFieldConstants.RACT_STATE_INACTIVE, endactdefid, runactkey_routed, flowdefid, runflowkey);

	}
	
//	public void ract_init(VForward vo, int i, DynamicObject ract_routed) throws Exception
//	{
//		String handletype = ract_routed.getFormatAttr("handletype");
//		if ("多部门串行".equals(handletype) || "多人串行".equals(handletype))
//		{
//			ract_init_sync(vo, i, ract_routed);
//		}
//		else
//		{
//			ract_init_normal(vo, i, ract_routed);
//		}
//	}
	
	public void ract_init_sync(VForward vo, int i, DynamicObject ract_routed) throws Exception
	{
		// 创建后继活动所有者
		// 更新流程的读者，作者
		// 创建后继活动的待办工作
		// 创建后继活动的任务	

		
	}
	
	public void ract_init_normal(VForward vo, int i, DynamicObject ract_routed) throws Exception
	{
		// 创建后继活动所有者
		// 更新流程的读者，作者
		// 创建后继活动的待办工作
		// 创建后继活动的任务
		
		String runflowkey = vo.runflowkey;
		// String userid = vo.userid;
		String tableid = vo.tableid;
		String dataid = vo.dataid;
		
		List<VActUser> c_actowners = vo.endacts.get(i).actowners;
//		List<VActUser> c_actagenters = vo.endacts.get(i).actagenters;
		List<VActUser> c_actagenters = vo.endacts.get(i).actowners;		
		String runactkey_routed = ract_routed.getFormatAttr("runactkey");
		String endactdefid = ract_routed.getFormatAttr("actdefid");	

		
		for (int j = 0; j < c_actowners.size(); j++)
		{
			// 修改
			String cuserid = c_actowners.get(j).userid;
			String cloginname = c_actowners.get(j).loginname;
			String cusername = c_actowners.get(j).username;
			String cusertype = c_actowners.get(j).usertype;
			
			String corganid = c_actowners.get(j).organid;
			String corganname = c_actowners.get(j).organname;
			String corgantype = c_actowners.get(j).organtype;
			// 委托人
//			String agentctx = c_actagenters.get(j).userid;
//			String agentctype = c_actagenters.get(j).usertype;
			
			ractownerService.create(runactkey_routed, cloginname, cusername, cusertype, null, tableid, corganid, corganname, corgantype);
			// .修改当前流程读者、作者
			rflowreaderService.create(cloginname, cusername, cusertype, runflowkey, runactkey_routed, DBFieldConstants.RFLOWREADER_SOURCE_ACTDEF, tableid);

			// 新增待办功能			
			create_waitwork(vo, ract_routed,  c_actowners.get(j), c_actagenters.get(j));
		}
		// .创建路由目标活动的任务实例
		racttaskService.update_from_bact_task(endactdefid, runactkey_routed, tableid);
	}
	
	public void ract_init_normal(VForward vo, int i, int j, DynamicObject ract_routed) throws Exception
	{
		// 创建后继活动所有者
		// 更新流程的读者，作者
		// 创建后继活动的待办工作
		// 创建后继活动的任务
		
		String runflowkey = vo.runflowkey;
		// String userid = vo.userid;
		String tableid = vo.tableid;
		String dataid = vo.dataid;
		
		List<VActUser> c_actowners = vo.endacts.get(i).actowners;
//		List<VActUser> c_actagenters = vo.endacts.get(i).actagenters;
		List<VActUser> c_actagenters = vo.endacts.get(i).actowners;		
		String runactkey_routed = ract_routed.getFormatAttr("runactkey");
		String endactdefid = ract_routed.getFormatAttr("actdefid");	
		
		{
			// 修改
			String cuserid = c_actowners.get(j).userid;
			String cloginname = c_actowners.get(j).loginname;
			String cusername = c_actowners.get(j).username;
			String cusertype = c_actowners.get(j).usertype;
			
			String corganid = c_actowners.get(j).organid;
			String corganname = c_actowners.get(j).organname;
			String corgantype = c_actowners.get(j).organtype;			
			// 委托人
//			String agentctx = c_actagenters.get(j).userid;
//			String agentctype = c_actagenters.get(j).usertype;
			
			ractownerService.create(runactkey_routed, cloginname, cusername, cusertype, null, tableid, corganid, corganname, corgantype);
			// .修改当前流程读者、作者
			rflowreaderService.create(cloginname, cusername, cusertype, runflowkey, runactkey_routed, DBFieldConstants.RFLOWREADER_SOURCE_ACTDEF, tableid);

			// 新增待办功能			
			create_waitwork(vo, ract_routed,  c_actowners.get(j), c_actagenters.get(j));
		}

			// .创建路由目标活动的任务实例
		racttaskService.update_from_bact_task(endactdefid, runactkey_routed, tableid);
	}
	
	public void forward_complete(VForward vo) throws Exception
	{
		String runactkey = vo.runactkey;
		String runflowkey = vo.runflowkey;
		String tableid = vo.tableid;

		// 如活动结束，记录活动结束时间，并置最新可用状态为否
		DynamicObject ract = ractService.locate(runactkey);
		if(ract.getFormatAttr("state").equals(DBFieldConstants.RACT_STATE_COMPLETED))
		{
			ractService.set_complete_time(runactkey, tableid, "FORWARD");
		}

		// 如果流程结束，记录流程结束时间
		DynamicObject rflow = rflowService.locate(runflowkey);
		if(rflow.getFormatAttr("state").equals(DBFieldConstants.RFlOW_STATE_COMPLETED))
		{
			rflowService.set_complete_time(runflowkey, tableid);
		}
	}
	
	
	public void create_waitwork(VForward vo, DynamicObject ract, VActUser owner, VActUser agent) throws Exception
	{
		WFWaitWork waitwork = new WFWaitWork();
		waitwork.setRunactkey(ract.getFormatAttr("runactkey"));
		waitwork.setActdefid(ract.getFormatAttr("actdefid"));
		waitwork.setTableid(vo.tableid);
		waitwork.setDataid(vo.dataid);
		waitwork.setConsigner(owner.loginname);
		waitwork.setReceiver(agent.loginname);
		waitwork.setSender(vo.loginname);
		waitwork.setOwnerorg(vo.orgid);
		waitwork.setOwnerorgname(vo.orgname);
		waitwork.setOwnerdept(vo.orgid);
		waitwork.setOwnerdeptname(vo.orgname);	
		
		waitworkService.create(waitwork);
	}
	
	public List getRActOwners(String runactkey, String tableid) throws Exception
	{
		List handlers = new ArrayList();

		StringBuffer sql = new StringBuffer();
		sql.append("select a.id, a.ownerctx, a.ctype \n");
		sql.append("  from t_sys_wfractowner a \n");
		sql.append(" where 1 = 1 \n");
		sql.append("   and a.runactkey = ");
		sql.append(SQLParser.charValue(runactkey));

		handlers = ractService.sdao().queryForList(sql.toString());

		for (int j = 0; j < handlers.size(); j++)
		{
			DynamicObject obj = (DynamicObject) handlers.get(j);

			String id = obj.getAttr("id");
			String ownerctx = obj.getAttr("ownerctx");
			String ctype = obj.getAttr("ctype");
			String cname = new String();

			if (ctype.equals("PERSON") || ctype.equals("ROLE") || ctype.equals("DEPT"))
			{
				sql = new StringBuffer();
				sql.append("select case \n");
				sql.append("  when substr(a.ownerctx,1,1)='P' then b.name \n");
				sql.append("  when substr(a.ownerctx,1,1)='R' then c.name \n");
				sql.append("  when substr(a.ownerctx,1,1)='D' then d.name \n");
				sql.append("   end cname \n");
				sql.append("  from t_sys_wfractowner a \n");
				sql.append("  left join t_sys_wfperson b \n");
				sql.append("    on a.ownerctx = b.personid \n");
				sql.append("  left join t_sys_wfrole c \n");
				sql.append("  	on a.ownerctx = c.roleid \n");
				sql.append("  left join t_sys_wfdepartment d \n");
				sql.append("  	on a.ownerctx = d.deptid \n");
				sql.append(" where 1 = 1 \n");
				sql.append("   and a.id = " + SQLParser.charValue(id));
				cname = 
					new DynamicObject(ractService.sdao().queryForMap(sql.toString())).getFormatAttr("cname");
			}
			else if (ctype.equals("DEPTROLE"))
			{
				String[] deptrole = StringToolKit.split(ownerctx, ":");
				String dept = deptrole[0];
				String role = deptrole[1];
				sql = new StringBuffer();
				sql.append("select a.name cname \n");
				sql.append("  from t_sys_wfdepartment a \n");
				sql.append(" where 1 = 1 \n");
				sql.append("   and a.deptid = " + SQLParser.charValue(dept));
				String deptname = ractService.sdao().queryForMap(sql.toString()).getFormatAttr("cname");

				sql = new StringBuffer();
				sql.append("select a.name cname \n");
				sql.append("  from t_sys_wfrole a \n");
				sql.append(" where 1 = 1 \n");
				sql.append("   and a.roleid = " + SQLParser.charValue(dept));
				String rolename = ractService.sdao().queryForMap(sql.toString()).getFormatAttr("cname");
				
				cname = deptname + ":" + rolename; 
			}
			obj.setAttr("cname", cname);
		}

		return handlers;
	}
	
	public List getActionOwnerToPerson(String actdefid) throws java.lang.Exception
	{
		List<DynamicObject> owners = new ArrayList();
		// 查询活动所有者
		StringBuffer sql = new StringBuffer();

        sql.append(" with temp as ").append("\n");
        sql.append(" ( ").append("\n");
        sql.append("select distinct b.loginname ownerctx, c.name cname, c.ordernum, 'PERSON' ctype ").append("\n");
        sql.append("  from t_sys_wfbactowner a, t_sys_wfgroupuser b, t_sys_wfperson c ").append("\n");
        sql.append(" where 1 = 1 ").append("\n");
        sql.append("   and a.ownerctx = b.groupid ").append("\n");
        sql.append("   and b.userid = c.personid ").append("\n");
        sql.append("   and (a.ctype = 'ROLE' or a.ctype = 'DEPT' or a.ctype = 'WORKGROUP' or a.ctype = 'ORG') ").append("\n");
        sql.append("   and a.actid = " + SQLParser.charValueRT(actdefid)).append("\n");
        sql.append(" union ").append("\n");
        sql.append("select a.ownerctx, c.name cname, a.ctype, c.ordernum ").append("\n");
        sql.append("  from t_sys_wfbactowner a, t_sys_wfperson c ").append("\n");
        sql.append(" where 1 = 1 ").append("\n");
        sql.append("   and a.ctype = 'PERSON' ").append("\n");
        sql.append("   and a.ownerctx = c.loginname ").append("\n");
        sql.append("   and a.actid = " + SQLParser.charValueRT(actdefid)).append("\n");
        sql.append("   order by ordernum, cname ").append("\n");        
        sql.append("   ) ").append("\n");
        
        sql.append("select temp.ownerctx, temp.cname, temp.ctype, temp.ordernum, gu.groupid organid, gu.groupname organname, gu.grouptype organtype  ");
        sql.append("  from temp ");
        sql.append("  left join t_sys_wfgroupuser gu ");
        sql.append("    on temp.ownerctx = gu.loginname ");
        sql.append(" where 1 = 1 ");
        sql.append("   and gu.grouptype = 'DEPT' ");
        sql.append("  order by ordernum, cname ").append("\n");

        
        owners.addAll(ractService.sdao().queryForList(sql.toString()));
		// System.out.println(sql.toString());

        sql = new StringBuffer();
        sql.append(" select * from t_sys_wfbactowner a where 1 = 1 and ctype = 'DEPTROLE' and actid = ").append(SQLParser.charValue(actdefid));
        List<DynamicObject> deptroles = ractService.sdao().queryForList(sql.toString());
        
        // 后期考虑到部门还是到具体人员
        for(int i=0;i<deptroles.size();i++)
        {
    		// 查询部门角色类型所有者
    		sql = new StringBuffer();
            sql.append("select v.loginname ownerctx, v.name cname, v.ordernum, 'PERSON' ctype, org.id organid, org.cname organname, org.ctype organtype").append("\n");
            sql.append("  from t_sys_organ org ").append("\n");          
            sql.append("  left join ");
            sql.append(" ( ");
            sql.append(" select p.loginname, p.name, p.ordernum, gu.groupid, gu.groupname ");
            sql.append("  from t_sys_wfperson p, t_sys_wfgroupuser gu, t_sys_wfgroupuser gr ").append("\n");
            sql.append(" where 1 = 1 ").append("\n");
            sql.append("   and p.loginname = gu.loginname ").append("\n");
            sql.append("   and p.loginname = gr.loginname ").append("\n");
            sql.append("   and gu.grouptype = 'DEPT'").append("\n");
            sql.append("   and gr.grouptype = 'ROLE'").append("\n");            
            sql.append("   and gr.groupid = ").append(SQLParser.charValue(deptroles.get(i).getFormatAttr("ownerctx")));
            sql.append(" ) v ").append("\n");        
            sql.append("  on org.id = v.groupid ");
            sql.append(" where 1 = 1 ");
            sql.append("   and org.ctype = 'DEPT'");
            
            List<DynamicObject> owners_deptrole = ractService.sdao().queryForList(sql.toString());
            if(owners_deptrole.size()>0)
            {
            	owners.addAll(owners_deptrole);
            }
            // 后期考虑到部门还是到具体人员
            
//            String roleid = deptroles.get(i).getFormatAttr("onwerctx");
//            for(int j=0;j<owners_deptrole.size();j++)
//            {
//        		sql = new StringBuffer();
//                sql.append("select p.loginname ownerctx, p.cname, p.ordernum, 'PERSON' ctype ").append("\n");
//                sql.append("  from t_sys_person p, t_sys_groupuser go, t_sys_groupuser gr ").append("\n");
//                sql.append(" where 1 = 1 ").append("\n");
//                sql.append("   and p.loginname = go.loginname ").append("\n");
//                sql.append("   and go.grouptype = 'DEPT' ").append("\n");
//                sql.append("   and go.gorupid = ").append(SQLParser.charValue(owners_deptrole.get(j).getFormatAttr("onwerctx"))).append("\n");
//                sql.append("   and gr.grouptype = 'ROLE' ").append("\n");
//                sql.append("   and gr.gorupid = ").append(SQLParser.charValue(roleid)).append("\n");    
//            }
        }

		// 查询公式定义的所有者
		sql = new StringBuffer();
		sql.append(SQL_GETACTIONOWNER_FORMULA(actdefid));
		List owner_temp = new ArrayList();
		owner_temp = ractService.sdao().queryForList(sql.toString());
		
		for (int i = 0; i < owner_temp.size(); i++)
		{
			String formula_ctx = ((DynamicObject) owner_temp.get(i)).getAttr("ownerctx");
			FormulaParser parser = new FormulaParser();
			parser.setDao(ractService.sdao());
			parser.setSwapFlow(swapFlow);
			List<DynamicObject> owner_ctx = parser.parser(formula_ctx);
			for (int j = 0; j < owner_ctx.size(); j++)
			{
				owners.add(owner_ctx.get(j));
			}
		}

		Map<String, DynamicObject> map = new HashMap(20);

		for (int i = 0; i < owners.size(); i++)
		{
			DynamicObject c_owner = (DynamicObject) owners.get(i);
			String key = c_owner.getFormatAttr("ownerctx");
			map.put(key, c_owner);
		}

		List<DynamicObject> n_owners = new ArrayList();

		Iterator<String> iter = map.keySet().iterator();

		while (iter.hasNext())
		{
			String key = (String) iter.next();
			n_owners.add(map.get(key));
		}

		return n_owners;
	}
	
	// 签收
	public void vapply(VApply vo) throws Exception
	{
		init_apply(vo);
		apply(vo);
	}
	
	public void init_apply(VApply vo) throws Exception
	{
		String runactkey = vo.runactkey;
		DynamicObject ract = getRactService().locate(runactkey);
		vo.runflowkey = ract.getFormatAttr("runflowkey");
		vo.actdefid = ract.getFormatAttr("actdefid");
		vo.tableid = ract.getFormatAttr("tableid");
		vo.dataid = ract.getFormatAttr("dataid");
		vo.flowdefid = ract.getFormatAttr("flowdefid");
	}
	
	public void apply(VApply vo) throws Exception
	{
		String userid = vo.userid;
		String loginname = vo.loginname;
		String username = vo.username;
		String usertype = vo.usertype;
		String deptid = vo.deptid;
		String deptcname = vo.deptcname;
		String orgid = vo.orgid;
		String orgname = vo.orgname;
		String flowdefid = vo.flowdefid;
		String tableid = vo.tableid;
		String dataid = vo.dataid;
		String runactkey = vo.runactkey;
		String runflowkey = vo.runflowkey;
		String actdefid = vo.actdefid;
		
		// 记录签收时间
		ractService.set_apply_time(runactkey, tableid);
		
		String sql = new String();
		
		DynamicObject ract = getRactService().locate(runactkey);
		String runhandletype = ract.getFormatAttr("handletype");
		
		// 如果是普通活动
		if(runhandletype.equals("普通"))
		{
			apply_normal(vo, ract);
			return;
		}
		
		if(runhandletype.equals("多部门串行"))
		{
			// 后期扩充
		}
		
		if(runhandletype.equals("多部门并行"))
		{
			apply_normal(vo, ract);
			// 后期扩充
		}
		
		if(runhandletype.equals("多人串行"))
		{
			// 后期扩充			
		}
		
		if(runhandletype.equals("多人并行"))
		{
			// 后期扩充			
		}
		
		if(runhandletype.equals("指定专人"))
		{
			// 后期扩充			
		}
		

	}
	
	public void apply_normal(VApply vo, DynamicObject ract) throws Exception
	{
		String loginname = vo.loginname;
		String username = vo.username;
		String usertype = vo.usertype;
		String runflowkey = vo.runflowkey;
		String tableid = vo.tableid;
		String deptid = vo.deptid;
		String deptname = vo.deptcname;
		String actdefid = vo.actdefid;
		String flowdefid = vo.flowdefid;
		
		String runactkey = ract.getFormatAttr("runactkey");
		String runactstate = ract.getFormatAttr("state");
		if(runactstate.equals(DBFieldConstants.RACT_STATE_INACTIVE))
		{
	        //将活动改为正处理状态
			// ractService.updateEntity(runactkey, "state", DBFieldConstants.RACT_STATE_ACTIVE);
			ractService.update(Chain.make("state", DBFieldConstants.RACT_STATE_ACTIVE), Cnd.where("runactkey","=",runactkey));
			// 将当前操作人员加入到流程读者
			rflowreaderService.create(loginname, username, usertype, runflowkey, runactkey, "", tableid);
			// 将当前操作人员加入到流程作者
			rflowauthorService.create(loginname, username, usertype, runflowkey, runactkey, "", tableid);
			
			// 清除非当前用户的流程作者
			List<DynamicObject> owners = ractownerService.findByCond(Cnd.where("runactkey", "=",runactkey));
			
			String ownerctx = "";
			String ctype = "";
			for(int i=0;i<owners.size();i++)
			{
				ownerctx = owners.get(i).getFormatAttr("ownerctx");
				ctype = owners.get(i).getFormatAttr("ctype");
				
				if(ownerctx.equals(loginname) && ctype.equals(usertype))
				{
					continue;
				}
				
				// 将其它人员从流程作者里面清除
				rflowauthorService.sdao().clear(rflowauthorService.getEntityClass(), Cnd.where("runflowkey","=",runflowkey).and("authorctx","=",ownerctx).and("ctype","=",ctype));
				// 将其它人员从流程读者里面清除
				rflowreaderService.sdao().clear(rflowreaderService.getEntityClass(), Cnd.where("runflowkey","=",runflowkey).and("readerctx","=",ownerctx).and("ctype","=",ctype));
				// 将其它人员从活动所有者里面清除
				ractownerService.sdao().clear(ractownerService.getEntityClass(), Cnd.where("runactkey","=",runactkey).and("ownerctx","=",ownerctx).and("ctype","=",ctype));
				// 将其它人员的待办清除
				waitworkService.sdao().clear(waitworkService.getEntityClass(), Cnd.where("runactkey","=",runactkey).and("receiver","=",ownerctx));
			}
			
			// 记录事件日志
			String id_event = leventService.create(TimeGenerator.getTimeStr(), DBFieldConstants.LEVENT_EVENTTYPE_ACT, runflowkey);
			leventactService.create(id_event, loginname, username, deptid, deptname, DBFieldConstants.RACT_STATE_INACTIVE, DBFieldConstants.RACT_STATE_ACTIVE, actdefid, runactkey, flowdefid, runflowkey, DBFieldConstants.LEVENTACT_EVENTTYPE_APPLY);
		}
	}
	
	public void checks_before_backward(VBackward vo) throws Exception
	{
		String runactkey = vo.runactkey;
		String runflowkey = vo.runflowkey;
		String loginname = vo.loginname;
		String usertype = vo.usertype;		
		
		// 检查流程是否已经结束
		check_rflow_complete(runflowkey);
		
		// .检查是否具有转发权限
		check_ract_enablebackward(runactkey, loginname, usertype);
	}
	
	// 检查是否具有转发权限
	public void check_ract_enablebackward(String runactkey, String ownerctx, String ctype) throws Exception
	{
		if(!ractService.enable_forward(runactkey, ownerctx, ctype))
		{
			throw new RuntimeException("当前用户没有转发该活动的权限！");
		}
	}
	
	public DynamicObject backward(VBackward vo) throws Exception
	{
		String loginname = vo.loginname;
		String username = vo.username;
		String usertype = vo.usertype;
		String runactkey = vo.runactkey;
		String beginactdefid = vo.beginactdefid;
		String runflowkey = vo.runflowkey;
		String tableid = vo.tableid;
		String dataid = vo.dataid;
		String c_handletype = ractService.locate(runactkey).getFormatAttr("handletype");
		String runactkey_back = new String();
		
		if(c_handletype.equals("多人串行"))
		{
			// 后续扩展
		}
		else		
		if(c_handletype.equals("多人并行"))
		{
			throw new RuntimeException("多人并行活动不允许回退！");
		}
		else		
		if(c_handletype.equals("多部门串行"))
		{
			//后续扩展
		}
		else
		{
			runactkey_back = backward_span(beginactdefid, tableid, dataid, loginname, username);
		}
		
		//
		return getBackwardHandler(runactkey_back, tableid);
	}
	
	public void backward_complete(VBackward vo)
	{
		
	}
	
	// 活动之间的回退
	public String backward_span(String beginactdefid, String tableid, String dataid, String user, String username) throws Exception
	{
		String sql = new String();
		// 查找流程当前活动反向活动定义
		sql = SQL_BACKWORD_ACT(beginactdefid);
		DynamicObject obj_backact = ractService.sdao().queryForMap(sql.toString());
		String actdefid_back = obj_backact.getFormatAttr("id");		
		
		if(actdefid_back.equals(""))
		{
			throw new RuntimeException("未找到可以退回的活动，无法退回！");
		}
		// 查找反向活动是否具有活动实例
		sql = SQL_FINDCOMPRUNACTKEY(tableid, dataid, actdefid_back);
		String runactkey_backed = ractService.sdao().queryForMap(sql.toString()).getFormatAttr("runactkey");
		if(runactkey_backed.equals(""))
		{
			throw new RuntimeException("未执行过要退回的活动，无法退回！");
		}
		// 如果有反向活动实例，检查活动实例处理类型
		// 如果是多人串行
		String handletype = ractService.locate(runactkey_backed).getFormatAttr("handletype");
		String runactkey_back = new String();
		if(handletype.equals("多人串行"))
		{
			// 后期扩展
		}
		else		
		if(handletype.equals("多人并行"))
		{
			// 后期扩展
		}
		else		
		if(handletype.equals("多部门串行"))
		{
			// 后期扩展
		}
		else
		{
			runactkey_back = backward_normal(tableid, dataid, beginactdefid, user, username, runactkey_backed);
		}
		
		return runactkey_back;
	}
	
	public String backward_normal(String tableid, String dataid, String beginactdefid, String user, String username, String runactkey_backed) throws Exception
	{
		String sql = new String();
		// 查找要回退的活动定义
		sql = SQL_BACKWORD_ACT(beginactdefid);
		DynamicObject obj_backact = ractService.sdao().queryForMap(sql.toString());
		String actdefid_back = obj_backact.getFormatAttr("id");		
		
		// 查找回退活动的处理人
		List list_racthandler_back = racthandlerService.findByCond(Cnd.where("runactkey", "=", runactkey_backed));
		if(list_racthandler_back.size()==0)
		{
			throw new RuntimeException("未找到要退回的活动的处理人，无法退回！");
		}
		
		sql = SQL_FORWARD_FINDRACT(tableid, dataid, beginactdefid);
		DynamicObject obj = ractService.sdao().queryForMap(sql.toString());
		String runflowkey = obj.getAttr("runflowkey");
		String runactkey = obj.getAttr("runactkey");
		// 更新当前活动状态
		sql = SQL_FORWARD_NORMAL_UPDATEACTSTATE_TABLE(runactkey, tableid);
		ractService.sdao().execute(Sqls.create(sql.toString()));
		
		// 查询当前活动对应流程的流程定义编号
		sql = SQL_FORWARD_FINDBYRUNACTKEY(runactkey, tableid);
		
		String flowdefid = ractService.sdao().queryForMap(sql.toString()).getAttr("flowdefid");
		String dept = swapFlow.getAttr(GlobalConstants.swap_coperatordeptid);
		String deptcname = swapFlow.getAttr(GlobalConstants.swap_coperatordeptcname);

		String id_event = leventService.create(TimeGenerator.getTimeStr(), DBFieldConstants.LEVENT_EVENTTYPE_ACT, runflowkey);
		leventactService.create(id_event, user, username, dept, deptcname, DBFieldConstants.RACT_STATE_INACTIVE, DBFieldConstants.RACT_STATE_ACTIVE, beginactdefid, runactkey, flowdefid, runflowkey);
		id_event = leventService.create(TimeGenerator.getTimeStr(), DBFieldConstants.LEVENT_EVENTTYPE_ACT, runflowkey);
		leventactService.create(id_event, user, username, dept, deptcname, DBFieldConstants.RACT_STATE_ACTIVE, DBFieldConstants.RACT_STATE_COMPLETED, beginactdefid, runactkey, flowdefid, runflowkey);
		
		String backactdefid = obj_backact.getFormatAttr("id");		
		String formid = obj_backact.getFormatAttr("formid");
		String handletype = obj_backact.getAttr("handletype");
		
		
		RAct ract = new RAct();
		ract.setRunflowkey(runflowkey);
		ract.setCreatetime(new Timestamp(System.currentTimeMillis()));
		ract.setFlowdefid(flowdefid);
		ract.setActdefid(backactdefid);
		ract.setState(DBFieldConstants.RACT_STATE_INACTIVE);
		ract.setTableid(tableid);
		ract.setDataid(dataid);
		ract.setHandletype(handletype);
		
		
		String runactkey_back = ractService.create(ract);
		
		//	.删除流程当前活动作者
		//dao_ractowner.delete_fk_runactkey(runactkey);
		// .删除流程当前的作者
		rflowauthorService.remove(runflowkey, runactkey, tableid);
		// .将反向活动原先的处理人(最后一人)添加为活动所有者
		DynamicObject c_handler = (DynamicObject)list_racthandler_back.get(list_racthandler_back.size()-1);
		String actorctype = c_handler.getFormatAttr("ctype");
		String actorctx = c_handler.getFormatAttr("handlerctx");
		String actorcname = c_handler.getFormatAttr("cname");
		
		String organid = c_handler.getFormatAttr("organid");
		String organname = c_handler.getFormatAttr("organname");
		String organtype = c_handler.getFormatAttr("organtype");
				
		ractownerService.create(runactkey_back, actorctx, actorcname, actorctype, "N", tableid, organid, organname, organtype);
		// .修改当前流程读者、作者
		rflowreaderService.create(actorctx, actorcname, actorctype, runflowkey, runactkey_back, DBFieldConstants.RFLOWREADER_SOURCE_ACTDEF, tableid);
		rflowauthorService.create(actorctx, actorcname, actorctype, runflowkey, runactkey_back, DBFieldConstants.RFLOWAUTHOR_SOURCE_ACTDEF, tableid);
		
		waitworkService.remove(runactkey);
		
		String ownerorg = swapFlow.getFormatAttr(GlobalConstants.swap_coperatororgid);
		String ownerorgcname = swapFlow.getFormatAttr(GlobalConstants.swap_coperatororgcname);
		String ownerdept = swapFlow.getFormatAttr(GlobalConstants.swap_coperatordeptid);
		String ownerdeptname = swapFlow.getFormatAttr(GlobalConstants.swap_coperatordeptcname);
		
		WFWaitWork waitwork = new WFWaitWork();
		
		waitwork.setRunactkey(runactkey_back);
		waitwork.setActdefid(backactdefid);
		waitwork.setTableid(tableid);
		waitwork.setDataid(dataid);
		waitwork.setConsigner(actorctx);
		waitwork.setReceiver(actorctx);
		waitwork.setSender(user);
		waitwork.setOwnerorg(ownerorg);
		waitwork.setOwnerorgname(ownerorgcname);
		waitwork.setOwnerdept(ownerdept);
		waitwork.setOwnerdeptname(ownerdeptname);	
		
		waitworkService.create(waitwork);
		
		// .创建路由目标活动的任务实例
		racttaskService.update_from_bact_task(actdefid_back, runactkey_back, tableid);
		
		//appendSupUser(tableid, dataid);
				
		// .新增事件日志记录
		// .创建路由事件
		// .处理人员是先前的处理人员
		DynamicObject old_handler = (DynamicObject)list_racthandler_back.get(list_racthandler_back.size()-1);
		String old_handlerctx = old_handler.getFormatAttr("handlerctx");
		String old_handlercname = old_handler.getFormatAttr("cname");
		String old_handlerdeptctx = old_handler.getFormatAttr("handledeptctx");
		String old_handlerdeptcname = old_handler.getFormatAttr("handledeptcname");
		
		id_event = leventService.create(TimeGenerator.getTimeStr(), DBFieldConstants.LEVENT_EVENTTYPE_ROUTE, runflowkey);

		leventrouteService.create(id_event, flowdefid, runflowkey, user, username, actdefid_back, runactkey_back, beginactdefid, runactkey, DBFieldConstants.LEVENTROUTE_EVENTTYPE_BACKWARD);
		leventroutereceiverService.create(id_event, old_handlerctx, old_handlercname, "PERSON");	
		
		id_event = leventService.create(TimeGenerator.getTimeStr(), DBFieldConstants.LEVENT_EVENTTYPE_ACT, runflowkey);
		leventactService.create(id_event, old_handlerctx, old_handlercname, old_handlerdeptctx, old_handlerdeptcname, DBFieldConstants.RACT_STATE_NULL, DBFieldConstants.RACT_STATE_INACTIVE, actdefid_back, runactkey_back, flowdefid, runflowkey);
		id_event = leventService.create(TimeGenerator.getTimeStr(), DBFieldConstants.LEVENT_EVENTTYPE_ACT, runflowkey);
		leventactService.create(id_event, old_handlerctx, old_handlercname, old_handlerdeptctx, old_handlerdeptcname, DBFieldConstants.RACT_STATE_INACTIVE, DBFieldConstants.RACT_STATE_ACTIVE, actdefid_back, runactkey_back, flowdefid, runflowkey);
		
		return runactkey_back; 
	}
	
	
	// 获取回退的当前处理人
	public DynamicObject getBackwardHandler(String runactkey_back, String tableid) throws Exception
	{
		//
		String sql = new String();
		DynamicObject actObj = new DynamicObject();
		DynamicObject ract = ractService.locate(runactkey_back);
		String backactdefid = ract.getFormatAttr("actdefid");
		String actname = bactService.locate(backactdefid).getFormatAttr("cname");		
		String handletype = ract.getFormatAttr("handletype");
		
		// 如果当前活动处理类型是多人串行
		if(handletype.equals("多人串行"))
		{
			// 后期扩展
		}
		else
		if(handletype.equals("多部门串行"))
		{
			// 后期扩展
		}
		else
		if(handletype.equals("多人并行"))
		{
			// 后期扩展
		}
		else
		{
			sql = SQL_FINDACTOWNER(runactkey_back, tableid);
			List owners = ractService.sdao().queryForList(sql.toString());
		
			actObj.setAttr("actname", actname);
			actObj.setObj("actowners", owners);
		}
		
		return actObj;
	}
	
	// 能否修改
	public boolean isEdit(Map map)
	{
		return false;
	}
	
	// 能否签收
	public boolean isApply(DynamicObject obj) throws java.lang.Exception
	{
		String runactkey = obj.getFormatAttr(GlobalConstants.swap_runactkey);
		String ownerctx = obj.getFormatAttr(GlobalConstants.swap_coperatorloginname);
		String ctype = "PERSON";
		String runflowkey = obj.getFormatAttr(GlobalConstants.swap_runflowkey);
		
		return isApply(runflowkey, runactkey, ownerctx, ctype);
	}
	
	// 能否转发
	public boolean isForward(DynamicObject obj) throws java.lang.Exception
	{
		String runactkey = obj.getFormatAttr(GlobalConstants.swap_runactkey);
		String ownerctx = obj.getFormatAttr(GlobalConstants.swap_coperatorloginname);
		String ctype = "PERSON";
		String runflowkey = obj.getFormatAttr(GlobalConstants.swap_runflowkey);	
		
		return isForward(runflowkey, runactkey, ownerctx, ctype);
	}
	
	// 能否收回
	public boolean isCallBack(DynamicObject obj) throws java.lang.Exception
	{
		String runactkey = obj.getFormatAttr(GlobalConstants.swap_runactkey);
		String ownerctx = obj.getFormatAttr(GlobalConstants.swap_coperatorloginname);
		String ctype = "PERSON";
		String runflowkey = obj.getFormatAttr(GlobalConstants.swap_runflowkey);		
		return isCallBack(runflowkey, runactkey, ownerctx, ctype);
	}
	
	// 能否退回
	public boolean isBackward(DynamicObject obj) throws java.lang.Exception
	{
		String runactkey = obj.getFormatAttr(GlobalConstants.swap_runactkey);
		String ownerctx = obj.getFormatAttr(GlobalConstants.swap_coperatorloginname);
		String ctype = "PERSON";
		String runflowkey = obj.getFormatAttr(GlobalConstants.swap_runflowkey);		
		
		return isBackward(runflowkey, runactkey, ownerctx, ctype);
	}
	
	public boolean isApply(String runflowkey, String runactkey, String ownerctx, String ctype) throws java.lang.Exception
	{
		boolean sign = false;
		
		String dataid = ractService.locate(runactkey).getFormatAttr("dataid");
		String state = rflowService.locate(runflowkey).getFormatAttr("state");
		
		// 流程实例是否结束、终止、挂起，流程结束无法签收
		if (DBFieldConstants.RFlOW_STATE_COMPLETED.equals(state) || DBFieldConstants.RFlOW_STATE_TERMINATED.equals(state) || DBFieldConstants.RFlOW_STATE_SUSPENDED.equals(state))
		{
			sign = false;
			return sign;
		}
		
		// 活动实例是否待处理，否则不得进行签收待处理；
		String actstate = ractService.locate(runactkey).getFormatAttr("state");
		if (!DBFieldConstants.RACT_STATE_INACTIVE.equals(actstate))
		{
			sign = false;
			return sign;
		}
		
		// 各级管理员权限检查
//		if(isAuthorSuper(tableid, dataid, ownerctx, ctype))
//		{
//			sign = true;
//			return sign;
//		}
			
		// 活动处理人可以签收
		sign = enableApplyNew(runactkey, ownerctx, ctype);
		
		return sign;
		
	}
	
	public boolean enableApplyNew(String runactkey, String ownerctx, String ctype) throws java.lang.Exception
	{
		boolean sign = false;
		
		String sql_idens = OrgService.build_idens(ownerctx);

		StringBuffer sql = new StringBuffer();

		sql.append("select c.id, c.ownerctx, c.ctype \n");
		sql.append("  from t_sys_wfract a, t_sys_wfractowner c, t_sys_wfrflow d \n");
		sql.append(" where 1 = 1 \n");
		sql.append("   and a.runactkey = c.runactkey \n");
		sql.append("   and a.runflowkey = d.runflowkey \n");
		sql.append("   and d.state not in ('尚未实例化','挂起','结束','终止') \n");
		sql.append("   and a.state = '待处理' \n");
		sql.append("   and a.handletype in ('普通','多人并行','多部门并行','指定专人') \n");
		sql.append("   and a.runactkey = " + SQLParser.charValue(runactkey));
		sql.append("   and (c.ownerctx, c.ctype) in ");
		sql.append("(" + sql_idens + ")");
		sql.append(" union \n");
		sql.append("select c.id, c.ownerctx, c.ctype \n");
		sql.append("  from t_sys_wfract a, t_sys_wfractowner c, t_sys_wfrflow d \n");
		sql.append(" where 1 = 1 \n");
		sql.append("   and a.runactkey = c.runactkey \n");
		sql.append("   and a.runflowkey = d.runflowkey \n");
		sql.append("   and d.state not in ('尚未实例化','挂起','结束','终止') \n");
		sql.append("   and a.state = '待处理' \n");
		sql.append("   and a.handletype in ('多人串行','多部门串行') \n");
		sql.append("   and a.runactkey = " + SQLParser.charValue(runactkey));
		sql.append("   and c.id = ( select min(id) id from t_sys_wfractowner");
		sql.append("                 where 1 = 1 \n");
		sql.append("                   and runactkey = a.runactkey \n");
		sql.append("                   and (complete is null or complete <> 'Y') \n");
		sql.append("              ) \n");
		sql.append("   and (c.ownerctx, c.ctype) in ");
		sql.append("(" + sql_idens + ")");

		List owners = new ArrayList();
		owners = ractService.queryForList(sql.toString());
		if (owners.size() > 0)
		{
			sign = true;
		}
		return sign;
	}
	
	// 能否转发
	public boolean isForward(String runflowkey, String runactkey, String ownerctx, String ctype) throws java.lang.Exception
	{
		boolean sign = false;
		
		String dataid = ractService.locate(runactkey).getFormatAttr("dataid");
		String state = rflowService.locate(runflowkey).getFormatAttr("state");
		
		// 流程实例是否结束、终止、挂起，流程结束无法转发
		if (DBFieldConstants.RFlOW_STATE_COMPLETED.equals(state) || DBFieldConstants.RFlOW_STATE_TERMINATED.equals(state) || DBFieldConstants.RFlOW_STATE_SUSPENDED.equals(state))
		{
			sign = false;
			return sign;
		}
		
		// 活动实例是否正处理，否则不得进行转发；
		String actstate = ractService.locate(runactkey).getFormatAttr("state");
		if (!DBFieldConstants.RACT_STATE_ACTIVE.equals(actstate))
		{
			sign = false;
			return sign;
		}
		
		// 检查子流程是否全部结束或关闭
		// 暂时屏蔽子流程校验，便于用户熟悉系统；
//		if(!dao_demand.checkSubFlowEnd(runactkey, tableid))
//		{
//			sign = false;
//			return sign;
//		}
		
		// 各级管理员权限检查
//		if(isAuthorSuper(tableid, dataid, ownerctx, ctype))
//		{
//			sign = true;
//			return sign;
//		}
		
		// 活动处理人可以签收
		sign = enableForwardNew(runactkey, ownerctx, ctype);
		
		return sign;
	}
	
	public boolean enableForwardNew(String runactkey, String ownerctx, String ctype) throws java.lang.Exception
	{
		boolean sign = false;
		String sql = SQL_ENABLEFORWARD_NEW(runactkey, ownerctx, ctype);

		List owners = new ArrayList();
		owners = ractService.sdao().queryForList(sql.toString());
		if (owners.size() > 0)
		{
			sign = true;
		}
		return sign;
	}	
	
	// 能否收回
	public boolean isCallBack(String runflowkey, String runactkey, String ownerctx, String ctype) throws java.lang.Exception
	{
		boolean sign = false;
		
		DynamicObject ract = ractService.locate(runactkey);
		
		String dataid = ract.getFormatAttr("dataid");
		String actdefid = ract.getFormatAttr("actdefid");
		String state = rflowService.locate(runflowkey).getFormatAttr("state");
		
		// 流程实例是否结束、终止、挂起，流程结束无法收回
		if (DBFieldConstants.RFlOW_STATE_COMPLETED.equals(state) || DBFieldConstants.RFlOW_STATE_TERMINATED.equals(state) || DBFieldConstants.RFlOW_STATE_SUSPENDED.equals(state))
		{
			sign = false;
			return sign;
		}
		
		// 活动实例不能是流程第一个环节
		if("Y".equals(bactService.locate(actdefid).getFormatAttr("isfirst")))
		{
			sign = false;
			return sign;	
		}
		
		// 活动实例是否待处理，否则不得进行收回
		String actstate = ract.getFormatAttr("state");
		if (!DBFieldConstants.RACT_STATE_INACTIVE.equals(actstate))
		{
			sign = false;
			return sign;
		}
		
		// 各级管理员权限检查
//		if(isAuthorSuper(tableid, dataid, ownerctx, ctype))
//		{
//			sign = true;
//			return sign;
//		}
		
		// 活动处理人可以收回
		sign = enableCallBackNew(runflowkey, runactkey, ownerctx, ctype);
		
		return sign;
	}
	
	
	public boolean enableCallBackNew(String runflowkey, String runactkey, String ownerctx, String ctype) throws java.lang.Exception
	{
		StringBuffer sql = new StringBuffer();
		
		sql.append(SQL_FORWARD_FINDRACT_NEW(runactkey));
		DynamicObject ract = ractService.sdao().queryForMap(sql.toString());
		String dataid = ract.getFormatAttr("dataid");
		String actdefid = ract.getFormatAttr("actdefid");
		
		String state = rflowService.locate(runflowkey).getFormatAttr("state");
		if (state.equals("挂起") || state.equals("结束") || state.equals("终止"))
		{
			return false;
		}

		if (bactService.locate(actdefid).getFormatAttr("ctype").equals("END"))
		{
			return false;
		}

		sql = new StringBuffer();
		if (bactService.locate(actdefid).getFormatAttr("isfirst").equals("Y"))
		{
			return false;
		}

		String runhandletype = ract.getFormatAttr("handletype");
		String runactstate = ract.getFormatAttr("state");

		if (runactstate.equals(DBFieldConstants.RACT_STATE_INACTIVE))
		{
			// 根据路由信息查找当前实例和对应的前驱实例数据

			sql = new StringBuffer();
			sql.append("select a.startrunactkey, b.id, b.isfirst, b.formid, b.handletype \n");
			sql.append("  from t_sys_wfleventroute a, t_sys_wfbact b \n");
			sql.append(" where 1 = 1 \n");
			sql.append("   and a.startactdefid = b.id \n");
			sql.append("   and b.ctype <> 'END' \n");
			sql.append("   and b.ctype <> 'BEGIN' \n");
			sql.append("   and a.endrunactkey = ");
			sql.append(SQLParser.charValue(runactkey));

			DynamicObject backact = ractService.sdao().queryForMap(sql.toString());
			String runactkey_backed = backact.getFormatAttr("startrunactkey");
			String actdefid_back = backact.getFormatAttr("id");

			if (runactkey_backed.equals(""))
			{
				return false;
			}

			// 查找反向活动的处理人
			sql = new StringBuffer();
			sql.append(" select a.handlerctx, a.cname, a.ctype \n");
			sql.append("  from t_sys_wfracthandler a \n");
			sql.append(" where 1 = 1 \n");
			sql.append(" and a.runactkey = " + SQLParser.charValue(runactkey_backed));
			sql.append(" and a.handlerctx = " + SQLParser.charValue(ownerctx));
			sql.append(" and a.ctype = " + SQLParser.charValue(ctype));

			List list_racthandler_back = ractService.sdao().queryForList(sql.toString());
			
			if (list_racthandler_back.size() == 0)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		return false;
	}
	
	// 能否退回
	public boolean isBackward(String runflowkey, String runactkey, String ownerctx, String ctype) throws java.lang.Exception
	{
		boolean sign = false;
		
		DynamicObject ract = ractService.locate(runactkey);
		
		String dataid = ract.getFormatAttr("dataid");
		String actdefid = ract.getFormatAttr("actdefid");
		String state = rflowService.locate(runflowkey).getFormatAttr("state");
		
		// 流程实例是否结束、终止、挂起，流程结束无法退回
		if (DBFieldConstants.RFlOW_STATE_COMPLETED.equals(state) || DBFieldConstants.RFlOW_STATE_TERMINATED.equals(state) || DBFieldConstants.RFlOW_STATE_SUSPENDED.equals(state))
		{
			sign = false;
			return sign;
		}
		
		// 活动实例不能是流程第一个环节
		
		if("Y".equals(bactService.locate(actdefid).getFormatAttr("isfirst")))
		{
			sign = false;
			return sign;
		}
		
		// 活动实例是否正处理，否则不得进行退回
		String actstate = ract.getFormatAttr("state");
		if (!DBFieldConstants.RACT_STATE_ACTIVE.equals(actstate))
		{
			sign = false;
			return sign;
		}
		
		// 检查子流程是否全部结束或关闭
		if(!checkSubFlowEnd(runactkey))
		{
			sign = false;
			return sign;
		}		
		
		// 各级管理员权限检查
//		if(isAuthorSuper(tableid, dataid, ownerctx, ctype))
//		{
//			sign = true;
//			return sign;
//		}
		
		// 活动处理人可以退回
		sign = enableBackwardNew(runflowkey, runactkey, ownerctx, ctype);
		
		return sign;
	}
	
	public boolean checkSubFlowEnd(String runactkey) throws Exception
	{
		boolean sign = false;
		
		StringBuffer sql = new StringBuffer(); 
		sql.append(SQL_CHECK_SUBFLOW_END(runactkey));

		int num = Types.parseInt(ractService.sdao().queryForMap(sql.toString()).getFormatAttr("num"), 1);
		if(num == 0)
		{
			sign = true;
		}
		
		return sign;
	}
	
	public boolean enableBackwardNew(String runflowkey, String runactkey, String ownerctx, String ctype) throws java.lang.Exception
	{
		DynamicObject obj_ract = ractService.locate(runactkey);
		String dataid = obj_ract.getFormatAttr("dataid");
		String actdefid = obj_ract.getFormatAttr("actdefid");
		String state = rflowService.locate(runflowkey).getFormatAttr("state");
		
		if (state.equals("挂起") || state.equals("结束") || state.equals("终止"))
		{
			return false;
		}
		
		// 是否是活动所有者
		if(!enableForwardNew(runactkey, ownerctx, ctype))
		{
			return false;
		}		

		return true;
	}	
	
	// 检查子流程是否结束
	public static String SQL_CHECK_SUBFLOW_END(String runactkey)
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(0) num ").append("\n");
		sql.append("  from t_sys_wfract a, t_sys_wfrflow b, t_sys_wfbact c  ").append("\n");
		sql.append("  where 1 = 1  ").append("\n");
		sql.append("  and a.runactkey = " + SQLParser.charValue(runactkey)).append("\n");
		sql.append("  and a.actdefid = c.id ").append("\n");
		sql.append("  and b.suprunactkey = a.runactkey ").append("\n");
		sql.append("  and (b.state <> '结束' and b.state <> '终止') ").append("\n");
		sql.append("  and c.subflowclose = 'ALL' ").append("\n");

		return sql.toString();
	}
	
	
	public static String SQL_FORWARD_FINDRACT_NEW(String runactkey)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select b.flowdefid, a.runactkey, a.actdefid, b.tableid, b.dataid, a.runflowkey, c.join, c.split, a.handletype, a.state \n");
		sql.append("  from t_sys_wfract a, t_sys_wfrflow b, t_sys_wfbact c \n");
		sql.append(" where 1 = 1 \n");
		sql.append("   and a.runflowkey = b.runflowkey \n");
		sql.append("   and a.actdefid = c.id \n");
		sql.append("   and a.actdefid = c.id \n");
		sql.append("   and a.runactkey = " + SQLParser.charValueRT(runactkey));
		return sql.toString();
	}

	public static String SQL_ENABLEFORWARD_NEW(String runactkey, String user, String ctype) throws Exception
	{
		String sql_idens = OrgService.build_idens(user);
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select a.id, a.ownerctx, a.ctype \n");
		sql.append("  from t_sys_wfractowner a \n");
		sql.append(" where 1 = 1 \n");
		sql.append("   and (a.ownerctx, a.ctype) in ");
		sql.append("(" + sql_idens + ")");
		sql.append("   and a.runactkey = ");
		sql.append("       (select a.runactkey runactkey \n");
		sql.append("          from t_sys_wfract a, t_sys_wfrflow b \n");
		sql.append("         where 1 = 1 \n");
		sql.append("   		   and a.tableid = b.tableid \n");
		sql.append("           and a.dataid = b.dataid \n");
		sql.append("           and b.state not in ('尚未实例化','挂起','结束','中止') \n");		
		sql.append("   		   and a.runactkey = " + SQLParser.charValue(runactkey));
		sql.append("       ) \n");  

		return sql.toString();
	}	
	
	
	public static String SQL_FORWARD_FINDRACT(String tableid, String dataid, String actdefid)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select b.flowdefid, a.runactkey, a.actdefid, a.runflowkey, c.join, c.split, a.handletype, a.state ").append("\n");
		sql.append("  from t_sys_wfract a, t_sys_wfrflow b, t_sys_wfbact c ").append("\n");
		sql.append(" where 1 = 1 ").append("\n");
		sql.append("   and a.runflowkey = b.runflowkey ").append("\n");
		sql.append("   and a.actdefid = c.id ").append("\n");
		sql.append("   and a.tableid = " + SQLParser.charValueRT(tableid));
		sql.append("   and a.dataid = " + SQLParser.charValueRT(dataid));
		sql.append("   and a.actdefid = " + SQLParser.charValueRT(actdefid));
		sql.append("   and a.isuse = 'Y' ").append("\n");
		return sql.toString();
	}
	
	public static String SQL_FORWARD_NORMAL_UPDATEACTSTATE_TABLE(String runactkey, String tableid)
	{
		StringBuffer sql = new StringBuffer(); 

		sql.append(" update t_sys_wfract ");
		sql.append(" set state = " + SQLParser.charValueEnd(DBFieldConstants.RACT_STATE_COMPLETED));
		sql.append(" completetime = sysdate ");
		sql.append(" where 1 = 1 \n");
		sql.append("   and runactkey = " + SQLParser.charValueRT(runactkey));
		
		return sql.toString();
	}
	
	public static String SQL_FORWARD_FINDBYRUNACTKEY(String runactkey, String tableid)
	{
		StringBuffer sql = new StringBuffer(); 

		sql.append("select distinct a.flowdefid  \n");
		sql.append("  from t_sys_wfrflow a, t_sys_wfract b \n");
		sql.append(" where 1 = 1 \n");
		sql.append("   and a.runflowkey = b.runflowkey \n");
		sql.append("   and b.runactkey = " + SQLParser.charValueRT(runactkey));
		
		return sql.toString();
	}
	
	public static String SQL_FORWARD_NORMAL_FINDROUTE(String beginactdefid, String endactdefid)
	{
		StringBuffer sql = new StringBuffer(); 

		sql.append(" select a.id, a.cname, a.routetype, a.conditions, a.startactid, a.endactid, a.flowid \n");
		sql.append("   from t_sys_wfbroute a \n");
		sql.append("  where 1 = 1 \n");
		sql.append("    and a.startactid = " + SQLParser.charValueRT(beginactdefid));
		sql.append("    and a.endactid = " + SQLParser.charValueRT(endactdefid));
		
		return sql.toString();
	}
	
	public static String SQL_FORWARD_FINDACTTASK(String tableid, String dataid, String startactdefid, String endactdefid)
	{
		StringBuffer sql = new StringBuffer(); 

		sql.append("select a.id, a.routeid, a.acttaskid, a.require, e.cname \n");
		sql.append("  from t_sys_wfbroutetask a, t_sys_wfbroute b, t_sys_wfracttask c, t_sys_wfract d, t_sys_wfbacttask e \n");
		sql.append(" where 1 = 1 \n");
		sql.append("   and a.acttaskid = e.id \n");
		sql.append("   and a.routeid = b.id \n");
		sql.append("   and b.startactid = " + SQLParser.charValueRT(startactdefid) );
		sql.append("   and b.endactid = " + SQLParser.charValueRT(endactdefid) );
		sql.append("   and a.acttaskid = c.acttaskdefid \n");
		sql.append("   and c.runactkey = d.runactkey \n");
		sql.append("   and b.startactid = d.actdefid \n");
		sql.append("   and d.tableid = " + SQLParser.charValueRT(tableid) );
		sql.append("   and d.dataid = " + SQLParser.charValueRT(dataid) );
		sql.append("   and a.require = '必需' \n");
		sql.append("   and c.complete = '未完成' \n");
		sql.append("   and d.tableid = " + SQLParser.charValueRT(tableid) );
		sql.append("   and d.dataid = " + SQLParser.charValueRT(dataid) );
		sql.append("   and d.actdefid = " + SQLParser.charValueRT(startactdefid) );
		sql.append("   and d.isuse = 'Y'");
		
		return sql.toString();
	}
	
	public static String SQL_FORWARD_UPDATEFLOWSTATE(String runflowkey, String state, String tableid)
	{
		StringBuffer sql = new StringBuffer(); 

		sql.append("update t_sys_wfrflow set state = " + SQLParser.charValueRT(state));
		sql.append(" where 1 = 1 \n");
		sql.append("   and runflowkey = " + SQLParser.charValueRT(runflowkey));
		
		return sql.toString();
	}
	
	// 查找转入为并的目标活动实例是否已经存在
	public static String SQL_FORWARD_EXISTANDACT(String actdefid, String tableid)
	{
		StringBuffer sql = new StringBuffer(); 

		sql.append("select a.runactkey \n");
		sql.append("  from t_sys_wfract a, t_sys_wfbact b \n");
		sql.append(" where 1 = 1 \n");
		sql.append("   and a.actdefid = b.id \n");
		sql.append("   and a.state <> '已处理' \n");
		sql.append("   and b.join = 'AND' \n");
		sql.append("   and a.actdefid = " + SQLParser.charValueRT(actdefid));
		sql.append("   and a.isuse = 'Y' ");
		
		return sql.toString();
	}
	
	// 查询出公式定义的所有者
	public static String SQL_GETACTIONOWNER_FORMULA(String actdefid)
	{
		StringBuffer sql = new StringBuffer();

		sql.append("select a.id, a.ownerchoice, a.ctype, a.ownerctx, a.actid \n");
		sql.append("  from t_sys_wfbactowner a \n");
		sql.append(" where 1 = 1 \n");
		sql.append("   and ctype in (");
		sql.append(DBFieldConstants.CTYPES_FORMULA_STR);
		sql.append(") \n");
		sql.append("   and actid = " + SQLParser.charValueRT(actdefid));

		return sql.toString();
	}
	
	public static String SQL_BACKWORD_ACT(String actdefid)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select a.id,  a.ctype, a.flowid, a.formid, a.handletype, a.isfirst \n");
		sql.append("  from t_sys_wfbact a, t_sys_wfbroute b \n");
		sql.append(" where 1 = 1 \n");
		sql.append("   and a.id = b.startactid \n");
		sql.append("   and b.endactid = ");
		sql.append(SQLParser.charValue(actdefid));
		return sql.toString();
	}
	
	public static String SQL_FINDCOMPRUNACTKEY(String tableid, String dataid, String actdefid)
	{
		StringBuffer sql = new StringBuffer();

		sql.append("select runactkey \n");
		sql.append("  from t_sys_wfract a \n");
		sql.append(" where 1 = 1 \n");
		sql.append("   and a.state = '已处理' \n");
		sql.append("   and a.tableid = " + SQLParser.charValueRT(tableid));
		sql.append("   and a.dataid = " + SQLParser.charValueRT(dataid));
		sql.append("   and a.actdefid = " + SQLParser.charValueRT(actdefid));
		sql.append("   and a.isuse = 'Y' ");
		return sql.toString();
	}
	
	public static String SQL_FINDACTOWNER(String runactkey, String tableid)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select a.id, a.ownerctx, a.ctype, a.complete, h.name cname \n");
		sql.append("  from t_sys_wfractowner a, t_sys_wfperson h \n");
		sql.append("　where 1 = 1 ").append("\n");
		sql.append("   and runactkey = " + SQLParser.charValueRT(runactkey));
		sql.append("   and a.ownerctx = h.personid \n");
		sql.append("   and a.ctype = 'PERSON' \n");
		sql.append(" union ").append("\n");
		sql.append("select a.id, a.ownerctx, a.ctype, a.complete, i.name cname\n");
		sql.append("  from t_sys_wfractowner a, t_sys_wfrole i \n");
		sql.append("　where 1 = 1 ").append("\n");
		sql.append("   and runactkey = " + SQLParser.charValueRT(runactkey));
		sql.append("   and a.ownerctx = i.roleid \n");
		sql.append("   and a.ctype = 'ROLE' \n");		
		
		return sql.toString();
	}
	
	private void demo()
	{
//		String userid = swapFlow.getFormatAttr(GlobalConstants.swap_coperatorid);
//		String loginname = swapFlow.getFormatAttr(GlobalConstants.swap_coperatorloginname);
//		String username = swapFlow.getFormatAttr(GlobalConstants.swap_coperatorcname);
//		String usertype = "PERSON";
//		String deptid = swapFlow.getFormatAttr(GlobalConstants.swap_coperatordeptid);
//		String deptcname = swapFlow.getFormatAttr(GlobalConstants.swap_coperatordeptcname);
//		String orgid = swapFlow.getFormatAttr(GlobalConstants.swap_coperatororgid);
//		String orgname = swapFlow.getFormatAttr(GlobalConstants.swap_coperatororgcname);
//		String flowdefid = swapFlow.getFormatAttr(GlobalConstants.swap_flowdefid);
//		String tableid = swapFlow.getFormatAttr(GlobalConstants.swap_tableid);
//		String dataid = swapFlow.getFormatAttr(GlobalConstants.swap_dataid);
//		String runactkey = swapFlow.getFormatAttr(GlobalConstants.swap_runactkey);
//		String runflowkey = swapFlow.getFormatAttr(GlobalConstants.swap_runflowkey);
//		String beginactdefid = swapFlow.getFormatAttr(GlobalConstants.swap_actdefid);
	}
	
	private void vo(VForward vo)
	{
		String userid = vo.userid;
		String loginname = vo.loginname;
		String username = vo.username;
		String usertype = vo.usertype;
		String deptid = vo.deptid;
		String deptcname = vo.deptcname;
		String orgid = vo.orgid;
		String orgname = vo.orgname;
		String flowdefid = vo.flowdefid;
		String tableid = vo.tableid;
		String dataid = vo.dataid;
		String runactkey = vo.runactkey;
		String runflowkey = vo.runflowkey;
		String beginactdefid = vo.beginactdefid;
	}

	public DynamicObject getSwapFlow() {
		return swapFlow;
	}

	public void setSwapFlow(DynamicObject swapFlow) {
		this.swapFlow = swapFlow;
	}

	public BActService getBactService() {
		return bactService;
	}

	public void setBactService(BActService bactService) {
		this.bactService = bactService;
	}

	public BFlowService getBflowService() {
		return bflowService;
	}

	public void setBflowService(BFlowService bflowService) {
		this.bflowService = bflowService;
	}

	public BRouteService getBrouteService() {
		return brouteService;
	}

	public void setBrouteService(BRouteService brouteService) {
		this.brouteService = brouteService;
	}

	public BFormService getBformService() {
		return bformService;
	}

	public void setBformService(BFormService bformService) {
		this.bformService = bformService;
	}

	public RFlowService getRflowService() {
		return rflowService;
	}

	public void setRflowService(RFlowService rflowService) {
		this.rflowService = rflowService;
	}

	public RActService getRactService() {
		return ractService;
	}

	public void setRactService(RActService ractService) {
		this.ractService = ractService;
	}

	public RActHandlerService getRacthandlerService() {
		return racthandlerService;
	}

	public void setRacthandlerService(RActHandlerService racthandlerService) {
		this.racthandlerService = racthandlerService;
	}

	public RActOwnerService getRactownerService() {
		return ractownerService;
	}

	public void setRactownerService(RActOwnerService ractownerService) {
		this.ractownerService = ractownerService;
	}

	public RActAssorterService getRactassorterService() {
		return ractassorterService;
	}

	public void setRactassorterService(RActAssorterService ractassorterService) {
		this.ractassorterService = ractassorterService;
	}

	public RFlowAuthorService getRflowauthorService() {
		return rflowauthorService;
	}

	public void setRflowauthorService(RFlowAuthorService rflowauthorService) {
		this.rflowauthorService = rflowauthorService;
	}

	public RFlowReaderService getRflowreaderService() {
		return rflowreaderService;
	}

	public void setRflowreaderService(RFlowReaderService rflowreaderService) {
		this.rflowreaderService = rflowreaderService;
	}

	public RActTaskService getRacttaskService() {
		return racttaskService;
	}

	public void setRacttaskService(RActTaskService racttaskService) {
		this.racttaskService = racttaskService;
	}

	public LEventService getLeventService() {
		return leventService;
	}

	public void setLeventService(LEventService leventService) {
		this.leventService = leventService;
	}

	public LEventFlowService getLeventflowService() {
		return leventflowService;
	}

	public void setLeventflowService(LEventFlowService leventflowService) {
		this.leventflowService = leventflowService;
	}

	public LEventActService getLeventactService() {
		return leventactService;
	}

	public void setLeventactService(LEventActService leventactService) {
		this.leventactService = leventactService;
	}

	public LEventRouteService getLeventrouteService() {
		return leventrouteService;
	}

	public void setLeventrouteService(LEventRouteService leventrouteService) {
		this.leventrouteService = leventrouteService;
	}

	public LEventRouteReceiverService getLeventroutereceiverService() {
		return leventroutereceiverService;
	}

	public void setLeventroutereceiverService(
			LEventRouteReceiverService leventroutereceiverService) {
		this.leventroutereceiverService = leventroutereceiverService;
	}

	public WFWaitWorkService getWaitworkService() {
		return waitworkService;
	}

	public void setWaitworkService(WFWaitWorkService waitworkService) {
		this.waitworkService = waitworkService;
	}

	public LFlowAssAppService getLflowassappService() {
		return lflowassappService;
	}

	public void setLflowassappService(LFlowAssAppService lflowassappService) {
		this.lflowassappService = lflowassappService;
	}

}
