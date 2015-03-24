package com.skynet.pams.app.plan.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.skynet.app.workflow.pojo.BAct;
import com.skynet.framework.action.BaseAction;
import com.skynet.framework.common.generator.FormatKey;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.pams.app.plan.service.PlanService;
import com.skynet.pams.base.pojo.Plan;

@IocBean
@At("/plan")
public class PlanAction extends BaseAction {

	@Inject
	private PlanService planService;

	@At("/browsecreate")
	@Ok("Forward:/page/plan/browsecreate.html")
	public String browsecreate() throws Exception {
		return "browsecreate";
	}

	@At("/browsecreate/json")
	@Ok("->:/page/plan/browsecreate.json.ftl")
	public Map browsecreatejson() throws Exception {
		List plans = planService.sdao().findBy("t_app_plan",
				Cnd.where("parentid", "=", null).orderBy("sequencekey", "asc"));
		ro.put("plans", plans);
		return ro;
	}

	@At("/locate")
	@Ok("Forward:/page/plan/locate.html")
	public Map locate(String id) throws Exception {
		Map arg = new HashMap();
		arg.put("id", id);
		Map obj = new HashMap();
		obj.put("arg", arg);
		return obj;
	}

	@At("/listsubplanjson")
	@Ok("->:/page/plan/listsubplan.ftl")
	public Map findsubplan(String id) throws Exception {
		Map data = new HashMap();

		DynamicObject plan = planService.locate(id);

		String sequencekey = plan.getFormatAttr("sequencekey");

		List<DynamicObject> subplans = planService.findByCond(Cnd.where(
				"sequencekey", "like", sequencekey + "%").and("id", "<>", id)); // 所有子节点
		int nums = subplans.size();
		// 查询所有的流程子结点
		String runflowkey = "";
		String runactkey = "";
		String subplanid = "";
		String subsequencekey = "";
		DynamicObject subplan = new DynamicObject();
		for (int i = 0; i < nums; i++) {
			
			subplan = subplans.get(i);
			subplanid = subplan.getFormatAttr("id");
			runflowkey = subplan.getFormatAttr("runflowkey");
			runactkey = subplan.getFormatAttr("runactkey");
			subsequencekey = subplan.getFormatAttr("sequencekey");

			if (StringToolKit.isBlank(runactkey)) 
			{
				continue;
			}
			
			List<DynamicObject> subracts = planService.getWorkFlowEngine()
					.getFlowManager().sub_plans(runactkey);
			for (int j = 0; j < subracts.size(); j++) {
				subracts.get(j).setAttr("parentid", subplanid);
				subracts.get(j).setAttr("sequencekey",
						subsequencekey + FormatKey.format(j, 4));
				subracts.get(j).setAttr("remark", "");
				subracts.get(j).setAttr("modifytime", "");
				subracts.get(j).setAttr("modifier", "");
				subracts.get(j).setAttr("planstartdate",
						subplan.getFormatAttr("planstartdate"));
				subracts.get(j).setAttr("planenddate",
						subplan.getFormatAttr("planstartdate"));
				subracts.get(j).setAttr("planworkload",
						subplan.getFormatAttr("planworkload"));
				subracts.get(j).setAttr("baseplanworkload",
						subplan.getFormatAttr("baseplanworkload"));

				subracts.get(j).setAttr("phaseorstep", "1");

				if (subracts.size() > 0) {
					subplans.addAll(subracts);
				}
			}
		}

		data.put("plan", plan);
		data.put("subplans", subplans);

		return data;
	}

	@At("/addsubplan")
	@Ok("->:/page/plan/addsubplan.ftl")
	public Map addsubplan(int type, String pid, String sname) {

		Plan plan = planService.dao().fetch(Plan.class, pid);

		Plan subplan = new Plan();
		subplan.setId(UUID.randomUUID().toString());
		subplan.setCname(sname);
		subplan.setParentid(pid);
		subplan.setPhaseorstep(type);
		subplan.setSequencekey(plan.getSequencekey() + FormatKey.format(0, 4));

		long time = System.currentTimeMillis();
		subplan.setPlanstartdate(new Timestamp(time));
		subplan.setPlanenddate(new Timestamp(time + 1000 * 60 * 60 * 24 * 10));
		planService.dao().insert(subplan);

		Map data = new HashMap();
		data.put("status", "success");
		data.put("cname", sname);
		data.put("id", plan.getId());
		data.put("parentid", pid);
		data.put("phaseorstep", type);
		return data;
	}

	@At("/saveplan")
	@Ok("->:/page/plan/saveplan.ftl")
	public Map saveplan(@Param("..") Plan plan) {
		planService.dao().update(plan);
		DynamicObject planobj = DynamicObject.transBean(plan);
		return planobj;
	}

	@At("/deleteplan")
	@Ok("->:/page/plan/deleteplan.ftl")
	public String deleteplan(String ids) {
		String[] idss = StringToolKit.split(ids, ",");
		for (int i = 0; i < idss.length; i++) {
			Sql sql = Sqls
					.create("delete from t_app_plan where 1 = 1 and id = @id");
			sql.params().set("id", idss[i]);
			planService.dao().execute(sql);
		}

		return ids;
	}

	@At("/decomposeplan")
	@Ok("json")
	public List decomposeplan(String flowid, String planid) {

		Plan plan = planService.dao().fetch(Plan.class, planid);
		plan.setFlowdefid(flowid);
		plan.setCtype("流程");
		planService.sdao().update(plan);

		List datas = planService
				.getWorkFlowEngine()
				.getActManager()
				.getRactService()
				.sdao()
				.query(BAct.class,
						Cnd.where("flowid", "=", flowid)
								.andNot("ctype", "=", "BEGIN")
								.andNot("ctype", "=", "END"));
		List subplans = new ArrayList();

		long s = 1000 * 60 * 60 * 24;
		
		for (int i = 0; i < datas.size(); i++) {
			BAct bact = (BAct) datas.get(i);

			Plan subplan = new Plan();
			subplan.setId(UUID.randomUUID().toString());
			subplan.setCname(bact.getCname());
			subplan.setParentid(planid);
			subplan.setPhaseorstep(1);
			subplan.setSequencekey(plan.getSequencekey()
					+ FormatKey.format(i, 4));
			subplan.setFlowdefid(flowid);
			subplan.setActdefid(bact.getId());
			subplan.setState("计划");
			subplan.setCtype("活动");

			long time = plan.getPlanstartdate().getTime();
			subplan.setPlanstartdate(new Timestamp(time + s * i * 2));
			subplan.setPlanenddate(new Timestamp(time + s * (i + 1) * 2));
			subplan.setPlanworkload(2);
			subplan.setBaseplanworkload(2);
			planService.dao().insert(subplan);

			subplans.add(subplan);
		}

		return subplans;
	}

	@At("/plan")
	@Ok("json")
	public Map plan(String planid) throws Exception {
		DynamicObject plan = planService.locate(planid);
		return plan;
	}

	@At("/gantt")
	@Ok("->:/page/plan/gantt.ftl")
	public Map gantt(String planid) throws Exception {
		DynamicObject plan = planService.locate(planid);

		List subplans = planService.findByCond(Cnd.where("parentid", "=",
				planid).orderBy("sequencekey", "asc"));

		plan.put("subplans", subplans);
		ro.put("plan", plan);
		return ro;
	}

	@At("/tasks")
	@Ok("->:/page/plan/tasks.ftl")
	public Map tasks(String start, String end, String planid) throws Exception {

		DynamicObject plan = planService.locate(planid);
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from t_app_plan plan ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and plan.parentid = ")
				.append(SQLParser.charValue(planid)).append("\n");
		;
		sql.append("    and plan.planstartdate >= to_date('").append(start)
				.append("','yyyy-MM-dd')").append("\n");
		sql.append("    and plan.planstartdate <= to_date('").append(end)
				.append("','yyyy-MM-dd')").append("\n");
		sql.append(" union ").append("\n");
		sql.append(" select * from t_app_plan plan ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and plan.parentid = ")
				.append(SQLParser.charValue(planid)).append("\n");
		;
		sql.append("    and plan.planenddate >= to_date('").append(start)
				.append("','yyyy-MM-dd')").append("\n");
		sql.append("    and plan.planenddate <= to_date('").append(end)
				.append("','yyyy-MM-dd')").append("\n");
		sql.append(" union ").append("\n");
		sql.append(" select * from t_app_plan plan ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and plan.parentid = ")
				.append(SQLParser.charValue(planid)).append("\n");
		;
		sql.append("    and plan.planstartdate >= to_date('").append(start)
				.append("','yyyy-MM-dd')").append("\n");
		sql.append("    and plan.planenddate <= to_date('").append(end)
				.append("','yyyy-MM-dd')").append("\n");
		sql.append(" union ").append("\n");
		sql.append(" select * from t_app_plan plan ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and plan.planstartdate <= to_date('").append(start)
				.append("','yyyy-MM-dd')").append("\n");
		sql.append("    and plan.planenddate >= to_date('").append(end)
				.append("','yyyy-MM-dd')").append("\n");
		sql.append("    and plan.parentid = ")
				.append(SQLParser.charValue(planid)).append("\n");
		;
		List subplans = planService.queryForList(sql.toString());

		plan.put("subplans", subplans);
		ro.put("plan", plan);
		return ro;
	}

	@At("/init")
	@Ok("json")
	public String init() throws Exception {
		// init_plandata();
		init_plandata_dfgl();
		return "success";
	}
	
	
	public void init_plandata_dfgl() throws Exception {
		long s = 1000 * 60 * 60 * 24;
		// planService.dao().create(Plan.class, true);
		Plan plan = new Plan();
		plan.setId(UUID.randomUUID().toString());
		plan.setCname("党费管理子专业2015年度工作计划");
		plan.setSequencekey("0001");
		plan.setPlanworkload(100);
		plan.setBaseplanworkload(100);
		plan.setState("计划");

		long time = System.currentTimeMillis();
		plan.setPlanstartdate(new Timestamp(time - s * 60));
		plan.setPlanenddate(new Timestamp(time + s * 40));
		planService.dao().insert(plan);

		Plan subplan1 = new Plan();
		subplan1.setId(UUID.randomUUID().toString());
		subplan1.setCname("工作调研");
		subplan1.setSequencekey("00010001");
		subplan1.setPhaseorstep(1);
		subplan1.setParentid(plan.getId());
		subplan1.setPlanworkload(10);
		subplan1.setBaseplanworkload(10);
		subplan1.setState("计划");	

		subplan1.setPlanstartdate(new Timestamp(time - s * 60));
		subplan1.setPlanenddate(new Timestamp(time - s * 50));

		planService.dao().insert(subplan1);

		Plan subplan2 = new Plan();
		subplan2.setId(UUID.randomUUID().toString());
		subplan2.setCname("计划编制");
		subplan2.setSequencekey("00010002");
		subplan2.setPhaseorstep(1);
		subplan2.setParentid(plan.getId());
		subplan2.setPlanworkload(10);
		subplan2.setBaseplanworkload(10);
		subplan2.setState("计划");			

		subplan2.setPlanstartdate(new Timestamp(time - s * 50));
		subplan2.setPlanenddate(new Timestamp(time - s * 40));

		planService.dao().insert(subplan2);

		Plan subplan3 = new Plan();
		subplan3.setId(UUID.randomUUID().toString());
		subplan3.setCname("党费收取");
		subplan3.setSequencekey("00010003");
		subplan3.setPhaseorstep(1);
		subplan3.setParentid(plan.getId());
		subplan3.setPlanworkload(20);
		subplan3.setBaseplanworkload(20);
		subplan3.setState("计划");			

		subplan3.setPlanstartdate(new Timestamp(time - s * 40));
		subplan3.setPlanenddate(new Timestamp(time - s * 20));

		planService.dao().insert(subplan3);

		Plan subplan4 = new Plan();
		subplan4.setId(UUID.randomUUID().toString());
		subplan4.setCname("党费使用");
		subplan4.setSequencekey("00010004");
		subplan4.setPhaseorstep(1);
		subplan4.setParentid(plan.getId());
		subplan4.setPlanworkload(30);
		subplan4.setBaseplanworkload(30);
		subplan4.setState("计划");			

		subplan4.setPlanstartdate(new Timestamp(time - s * 20));
		subplan4.setPlanenddate(new Timestamp(time + s * 10));

		planService.dao().insert(subplan4);

		Plan subplan5 = new Plan();
		subplan5.setId(UUID.randomUUID().toString());
		subplan5.setCname("工作总结");
		subplan5.setSequencekey("00010005");
		subplan5.setPhaseorstep(1);
		subplan5.setParentid(plan.getId());
		subplan5.setPlanworkload(15);
		subplan5.setBaseplanworkload(15);
		subplan5.setState("计划");			

		subplan5.setPlanstartdate(new Timestamp(time + s * 11));
		subplan5.setPlanenddate(new Timestamp(time + s * 25));

		planService.dao().insert(subplan5);

		Plan subplan6 = new Plan();
		subplan6.setId(UUID.randomUUID().toString());
		subplan6.setCname("归档共享");
		subplan6.setSequencekey("00010006");
		subplan6.setPhaseorstep(1);
		subplan6.setParentid(plan.getId());
		subplan6.setPlanworkload(5);
		subplan6.setBaseplanworkload(5);
		subplan6.setState("计划");			

		subplan6.setPlanstartdate(new Timestamp(time + s * 26));
		subplan6.setPlanenddate(new Timestamp(time + s * 30));

		planService.dao().insert(subplan6);
		
		
		Plan subplan11 = new Plan();
		subplan11.setId(UUID.randomUUID().toString());
		subplan11.setCname("意见征求");
		subplan11.setSequencekey("000100010001");
		subplan11.setPhaseorstep(1);
		subplan11.setParentid(subplan1.getId());
		subplan11.setPlanworkload(10);
		subplan11.setBaseplanworkload(10);
		subplan11.setState("计划");	

		subplan11.setPlanstartdate(new Timestamp(time - s * 60));
		subplan11.setPlanenddate(new Timestamp(time - s * 55));

		planService.dao().insert(subplan11);
		
		Plan subplan12 = new Plan();
		subplan12.setId(UUID.randomUUID().toString());
		subplan12.setCname("基数核准");
		subplan12.setSequencekey("000100010002");
		subplan12.setPhaseorstep(1);
		subplan12.setParentid(subplan1.getId());
		subplan12.setPlanworkload(10);
		subplan12.setBaseplanworkload(10);
		subplan12.setState("计划");	

		subplan12.setPlanstartdate(new Timestamp(time - s * 55));
		subplan12.setPlanenddate(new Timestamp(time - s * 50));

		planService.dao().insert(subplan12);

	}	

	public void init_plandata() throws Exception {
		long s = 1000 * 60 * 60 * 24;
		// planService.dao().create(Plan.class, true);
		Plan plan = new Plan();
		plan.setId(UUID.randomUUID().toString());
		plan.setCname("数字神经二期项目");
		plan.setSequencekey("0001");
		plan.setPlanworkload(100);
		plan.setBaseplanworkload(100);
		plan.setState("计划");

		long time = System.currentTimeMillis();
		plan.setPlanstartdate(new Timestamp(time - s * 60));
		plan.setPlanenddate(new Timestamp(time + s * 40));
		planService.dao().insert(plan);

		Plan subplan1 = new Plan();
		subplan1.setId(UUID.randomUUID().toString());
		subplan1.setCname("准备阶段");
		subplan1.setSequencekey("00010001");
		subplan1.setPhaseorstep(1);
		subplan1.setParentid(plan.getId());
		subplan1.setPlanworkload(10);
		subplan1.setBaseplanworkload(10);
		subplan1.setState("计划");	

		subplan1.setPlanstartdate(new Timestamp(time - s * 60));
		subplan1.setPlanenddate(new Timestamp(time - s * 50));

		planService.dao().insert(subplan1);

		Plan subplan2 = new Plan();
		subplan2.setId(UUID.randomUUID().toString());
		subplan2.setCname("概要设计阶段");
		subplan2.setSequencekey("00010002");
		subplan2.setPhaseorstep(1);
		subplan2.setParentid(plan.getId());
		subplan2.setPlanworkload(10);
		subplan2.setBaseplanworkload(10);
		subplan2.setState("计划");			

		subplan2.setPlanstartdate(new Timestamp(time - s * 50));
		subplan2.setPlanenddate(new Timestamp(time - s * 40));

		planService.dao().insert(subplan2);

		Plan subplan3 = new Plan();
		subplan3.setId(UUID.randomUUID().toString());
		subplan3.setCname("详细设计阶段");
		subplan3.setSequencekey("00010003");
		subplan3.setPhaseorstep(1);
		subplan3.setParentid(plan.getId());
		subplan3.setPlanworkload(20);
		subplan3.setBaseplanworkload(20);
		subplan3.setState("计划");			

		subplan3.setPlanstartdate(new Timestamp(time - s * 40));
		subplan3.setPlanenddate(new Timestamp(time - s * 20));

		planService.dao().insert(subplan3);

		Plan subplan4 = new Plan();
		subplan4.setId(UUID.randomUUID().toString());
		subplan4.setCname("编码开发阶段");
		subplan4.setSequencekey("00010004");
		subplan4.setPhaseorstep(1);
		subplan4.setParentid(plan.getId());
		subplan4.setPlanworkload(30);
		subplan4.setBaseplanworkload(30);
		subplan4.setState("计划");			

		subplan4.setPlanstartdate(new Timestamp(time - s * 20));
		subplan4.setPlanenddate(new Timestamp(time + s * 10));

		planService.dao().insert(subplan4);

		Plan subplan5 = new Plan();
		subplan5.setId(UUID.randomUUID().toString());
		subplan5.setCname("测试验证阶段");
		subplan5.setSequencekey("00010005");
		subplan5.setPhaseorstep(1);
		subplan5.setParentid(plan.getId());
		subplan5.setPlanworkload(15);
		subplan5.setBaseplanworkload(15);
		subplan5.setState("计划");			

		subplan5.setPlanstartdate(new Timestamp(time + s * 11));
		subplan5.setPlanenddate(new Timestamp(time + s * 25));

		planService.dao().insert(subplan5);

		Plan subplan6 = new Plan();
		subplan6.setId(UUID.randomUUID().toString());
		subplan6.setCname("部署实施阶段");
		subplan6.setSequencekey("00010006");
		subplan6.setPhaseorstep(1);
		subplan6.setParentid(plan.getId());
		subplan6.setPlanworkload(5);
		subplan6.setBaseplanworkload(5);
		subplan6.setState("计划");			

		subplan6.setPlanstartdate(new Timestamp(time + s * 26));
		subplan6.setPlanenddate(new Timestamp(time + s * 30));

		planService.dao().insert(subplan6);

		Plan subplan7 = new Plan();
		subplan7.setId(UUID.randomUUID().toString());
		subplan7.setCname("上线运行阶段");
		subplan7.setSequencekey("00010007");
		subplan7.setPhaseorstep(1);
		subplan7.setParentid(plan.getId());
		subplan7.setPlanworkload(10);
		subplan7.setBaseplanworkload(10);
		subplan7.setState("计划");	

		subplan7.setPlanstartdate(new Timestamp(time + s * 31));
		subplan7.setPlanenddate(new Timestamp(time + s * 40));

		planService.dao().insert(subplan7);

		// Plan subplan11 = new Plan();
		// subplan11.setId(UUID.randomUUID().toString());
		// subplan11.setCname("项目实施方法论1");
		// subplan11.setSequencekey("000100010001");
		// subplan11.setPhaseorstep(1);
		// subplan11.setParentid(subplan1.getId());
		//
		// subplan11.setPlanstartdate(new Timestamp(time));
		// subplan11.setPlanenddate(new Timestamp(time + 1000 * 60 * 60 * 24 *
		// 3));
		//
		// planService.dao().insert(subplan11);
		//
		// Plan subplan111 = new Plan();
		// subplan111.setId(UUID.randomUUID().toString());
		// subplan111.setCname("任务1");
		// subplan111.setSequencekey("0001000100010001");
		// subplan111.setPhaseorstep(1);
		// subplan111.setParentid(subplan11.getId());
		//
		// subplan111.setPlanstartdate(new Timestamp(time));
		// subplan111.setPlanenddate(new Timestamp(time + 1000 * 60 * 60 * 24 *
		// 3));
		//
		// planService.dao().insert(subplan111);
		//
		// Plan subplan112 = new Plan();
		// subplan112.setId(UUID.randomUUID().toString());
		// subplan112.setCname("任务2");
		// subplan112.setSequencekey("0001000100010002");
		// subplan112.setPhaseorstep(1);
		// subplan112.setParentid(subplan11.getId());
		//
		// subplan112.setPlanstartdate(new Timestamp(time));
		// subplan112.setPlanenddate(new Timestamp(time + 1000 * 60 * 60 * 24 *
		// 3));
		//
		// planService.dao().insert(subplan112);
		//
		// Plan subplan12 = new Plan();
		// subplan12.setId(UUID.randomUUID().toString());
		// subplan12.setCname("启动会2");
		// subplan12.setSequencekey("000100010002");
		// subplan12.setPhaseorstep(1);
		// subplan12.setParentid(subplan1.getId());
		//
		// subplan12.setPlanstartdate(new Timestamp(time));
		// subplan12.setPlanenddate(new Timestamp(time + 1000 * 60 * 60 * 24 *
		// 3));
		//
		// planService.dao().insert(subplan12);
		//
		// Plan subplan31 = new Plan();
		// subplan31.setId(UUID.randomUUID().toString());
		// subplan31.setCname("详细设计说明书");
		// subplan31.setSequencekey("000100020001");
		// subplan31.setPhaseorstep(1);
		// subplan31.setParentid(subplan3.getId());
		//
		// subplan31.setPlanstartdate(new Timestamp(time + 1000 * 60 * 60 * 24 *
		// 10));
		// subplan31.setPlanenddate(new Timestamp(time + 1000 * 60 * 60 * 24 *
		// 20));
		//
		// planService.dao().insert(subplan31);
	}
}
