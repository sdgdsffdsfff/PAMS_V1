package com.skynet.app.workflow.ui.service;

import java.util.Map;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.app.workflow.spec.GlobalConstants;
import com.skynet.framework.dao.SkynetDao;

@InjectName("uIService")
@IocBean
public class UIService
{
	@Inject
	Dao dao;
	
	// 待办视图的流程字段
	public String get_browsewait_field(Map map)
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" bact.id as bactid, bact.cname as bactcname, ract.createtime as ractcreatetime, ract.state as ractstate, ractowner.ownerctx ractownerctx, usr.cname username, ract.runactkey runactkey, rflow.runflowkey runflowkey,").append("\n");
		sql.append(" ((case when ract.completetime is null then sysdate - ract.createtime else ract.completetime - ract.createtime end) * 24) zxsc ").append("\n");
		return sql.toString();
	}
	
	// 待办视图的流程表
	public String get_browsewait_table(Map map)
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" t_sys_wfbact bact, t_sys_wfrflow rflow, t_sys_wfract ract, t_sys_wfractowner ractowner, t_sys_wfbflow bflow, t_sys_user usr ").append("\n");
		return sql.toString();
	}
	
	// 待办视图的流程条件
	public String get_browsewait_where(Map map)
	{
	    // 工作流交换数据
		// String userid = (String)map.get(GlobalConstants.swap_coperatorid); //用户标识
		String loginname = (String)map.get(GlobalConstants. swap_coperatorloginname); // 用户登录名
		String username = (String)map.get(GlobalConstants. swap_coperatorcname); // 用户姓名
		String flowcclass = (String)map.get("flowcclass");
		String flowsno = (String)map.get("flowsno");

		StringBuffer sql = new StringBuffer();
		sql.append("  and bv.id = rflow.dataid ").append("\n");
		sql.append("  and ract.runflowkey = rflow.runflowkey ").append("\n");
		sql.append("  and (ract.state in ('待处理', '正处理') and rflow.state not in ('结束', '终止')) ").append("\n");
		sql.append("  and bact.id = ract.actdefid ").append("\n");
		sql.append("  and ractowner.runactkey = ract.runactkey ").append("\n");
		sql.append("  and ractowner.ownerctx =  " + SQLParser.charValue(loginname)).append("\n");
		sql.append("  and usr.loginname = ractowner.ownerctx ").append("\n");
		sql.append("  and bact.flowid = bflow.id ").append("\n");
		sql.append("  and ract.ispackage is null ").append("\n");
		
		if(!StringToolKit.isBlank("flowcclass"))
		{
			sql.append("  and bflow.classid = " + SQLParser.charValue(flowcclass));
		}
		
		if(!StringToolKit.isBlank(flowsno))
		{
			sql.append("  and bflow.sno = " + SQLParser.charValue(flowsno));
		}
		
		return sql.toString();
	}	
	
	// 已处理视图的流程字段
	public String get_browsehandle_field(Map map)
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" bact.id as bactid, bact.cname as bactcname, ract.createtime as ractcreatetime, ract.state as ractstate, ract.runactkey runactkey, rflow.runflowkey, ").append("\n");
		sql.append(" ((case when ract.completetime is null then sysdate - ract.createtime else ract.completetime - ract.createtime end) * 24) zxsc ").append("\n");
		return sql.toString();
	}
	
	// 已处理视图的流程表
	public String get_browsehandle_table(Map map)
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" t_sys_wfbflow bflow, t_sys_wfbact bact, t_sys_wfrflow rflow, t_sys_wfract ract, t_sys_wfrflowreader rflowreader ").append("\n");
		return sql.toString();
	}	
	
	// 已处理视图的流程条件
	public String get_browsehandle_where(Map map)
	{
	    // 工作流交换数据
		// String userid = (String)map.get(GlobalConstants.swap_coperatorid); //用户标识
		String loginname = (String)map.get(GlobalConstants.swap_coperatorloginname); // 用户登录名
		String username = (String)map.get(GlobalConstants.swap_coperatorcname); // 用户姓名
		String flowsno = (String)map.get("flowsno");
		String flowcclass = (String)map.get("flowcclass");
		
		StringBuffer sql = new StringBuffer();
		sql.append("  and bv.id = rflow.dataid ").append("\n");
		sql.append("  and ract.runflowkey = rflow.runflowkey ").append("\n");
		sql.append("  and ract.state in ('待处理', '正处理') ").append("\n");
		sql.append("  and bact.id = ract.actdefid ").append("\n");
		sql.append("  and bact.flowid = bflow.id ").append("\n");
		sql.append("  and ract.runflowkey = rflowreader.runflowkey ").append("\n");
		sql.append("  and rflowreader.readerctx =  " + SQLParser.charValue(loginname)).append("\n");
		sql.append("  and not exists ").append("\n");
		sql.append("    ( ").append("\n");
		sql.append("      select cract.runflowkey ").append("\n");
		sql.append("        from t_sys_wfract cract, t_sys_wfrflowreader crflowreader ").append("\n");
		sql.append("       where 1 = 1 ").append("\n");
		sql.append("         and ract.runactkey = cract.runactkey ").append("\n");
		sql.append("         and cract.runactkey = crflowreader.runactkey ").append("\n");
		sql.append("         and cract.state in ('待处理', '正处理') ").append("\n");
		sql.append("         and crflowreader.readerctx = " + SQLParser.charValue(loginname)).append("\n");
		sql.append("    ) ").append("\n");
		
		
		if(!StringToolKit.isBlank("flowcclass"))
		{
			sql.append("  and bflow.classid = " + SQLParser.charValue(flowcclass));
		}
		
		if(!StringToolKit.isBlank(flowsno))
		{
			sql.append("  and bflow.sno = " + SQLParser.charValue(flowsno));
		}
		
		return sql.toString();
	}
	
	// 个人全部视图的流程字段
	public String get_browseall_field(Map map)
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" bact.id as bactid, bact.cname as bactcname, ract.createtime as ractcreatetime, ract.state as ractstate, ract.runactkey runactkey, rflow.runflowkey, ").append("\n");
		sql.append(" ((case when ract.completetime is null then sysdate - ract.createtime else ract.completetime - ract.createtime end) * 24) zxsc ").append("\n");
		return sql.toString();
	}
	
	// 个人全部视图的流程表
	public String get_browseall_table(Map map)
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" t_sys_wfbflow bflow, t_sys_wfbact bact, t_sys_wfrflow rflow, t_sys_wfract ract, t_sys_wfrflowreader rflowreader ").append("\n");
		return sql.toString();
	}	
	
	// 个人全部视图的流程条件
	public String get_browseall_where(Map map)
	{
	    // 工作流交换数据
		// String userid = (String)map.get(GlobalConstants.swap_coperatorid); //用户标识
		String loginname = (String)map.get(GlobalConstants.swap_coperatorloginname); // 用户登录名
		String username = (String)map.get(GlobalConstants.swap_coperatorcname); // 用户姓名
		String flowsno = (String)map.get("flowsno");
		String flowcclass = (String)map.get("flowcclass");
		
		StringBuffer sql = new StringBuffer();
		sql.append("  and bv.id = rflow.dataid ").append("\n");
		sql.append("  and ract.runflowkey = rflow.runflowkey ").append("\n");
		sql.append("  and ract.state in ('待处理', '正处理') ").append("\n");
		sql.append("  and bact.id = ract.actdefid ").append("\n");
		sql.append("  and bact.flowid = bflow.id ").append("\n");
		sql.append("  and ract.runflowkey = rflowreader.runflowkey ").append("\n");
		sql.append("  and rflowreader.readerctx =  " + SQLParser.charValue(loginname)).append("\n");
		
		if(!StringToolKit.isBlank("flowcclass"))
		{
			sql.append("  and bflow.classid = " + SQLParser.charValue(flowcclass));
		}
		
		if(!StringToolKit.isBlank(flowsno))
		{
			sql.append("  and bflow.sno = " + SQLParser.charValue(flowsno));
		}
		
		return sql.toString();
	}	
	
	
	// 全部视图的流程字段
	public String get_browsegroupall_field(Map map)
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" bact.id as bactid, bact.cname as bactcname, ract.createtime as ractcreatetime, ract.state as ractstate, ract.runactkey runactkey, ").append("\n");
		sql.append(" ((case when ract.completetime is null then sysdate - ract.createtime else ract.completetime - ract.createtime end) * 24) zxsc ").append("\n");
		return sql.toString();
	}
	
	// 全部视图的流程表
	public String get_browsegroupall_table(Map map)
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" t_sys_wfbact bact, t_sys_wfrflow rflow, t_sys_wfract ract, t_sys_wfbflow bflow ").append("\n");
		return sql.toString();
	}	
	
	// 全部视图的流程条件
	public String get_browsegroupall_where(Map map)
	{
	    // 工作流交换数据
		String userid = (String)map.get(GlobalConstants.swap_coperatorid); //用户标识
		String loginname = (String)map.get(GlobalConstants.swap_coperatorloginname); // 用户登录名
		String username = (String)map.get(GlobalConstants.swap_coperatorcname); // 用户姓名
		String flowcclass = (String)map.get("flowcclass");
		String flowsno = (String)map.get("flowsno");		

		StringBuffer sql = new StringBuffer();
		sql.append("  and bv.id = rflow.dataid  ").append("\n");
		sql.append("  and ract.runflowkey = rflow.runflowkey ").append("\n");
		sql.append("  and ract.state in ('待处理', '正处理') ").append("\n");
		sql.append("  and bact.id = ract.actdefid ").append("\n");
		sql.append("  and bact.flowid = bflow.id ").append("\n");
		if(!StringToolKit.isBlank("flowcclass"))
		{
			sql.append("  and bflow.classid = " + SQLParser.charValue(flowcclass));
		}
		
		if(!StringToolKit.isBlank(flowsno))
		{
			sql.append("  and bflow.sno = " + SQLParser.charValue(flowsno));
		}
		
		return sql.toString();
	}
	
	// 全部视图的流程字段
	public String get_browsegroupsall_field(Map map)
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" bact.id as bactid, sbact.cname bactcname, ract.createtime as ractcreatetime, ract.state as ractstate, ract.runactkey runactkey ").append("\n");
		return sql.toString();
	}
	
	// 全部视图的流程表
	public String get_browsegroupsall_table(Map map)
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" t_sys_wfbact bact, t_sys_wfrflow rflow, t_sys_wfract ract, t_sys_wfbflow sbflow, t_sys_wfbact sbact ").append("\n");
		return sql.toString();
	}	
	
	// 全部视图的流程条件
	public String get_browsegroupsall_where(Map map)
	{
	    // 工作流交换数据
		String userid = (String)map.get(GlobalConstants.swap_coperatorid); //用户标识
		String loginname = (String)map.get(GlobalConstants.swap_coperatorloginname); // 用户登录名
		String username = (String)map.get(GlobalConstants.swap_coperatorcname); // 用户姓名
		String flowcclass = (String)map.get("flowcclass");
		String flowsno = (String)map.get("flowsno");		

		StringBuffer sql = new StringBuffer();
		sql.append("  and bv.id = rflow.dataid  ").append("\n");
		sql.append("  and ract.runflowkey = rflow.runflowkey ").append("\n");
		sql.append("  and (  (ract.state in ('待处理', '正处理') and rflow.state <> '结束')  ").append("\n");
		sql.append("      or (bact.ctype = 'END' and rflow.state = '结束') ) ").append("\n");
		sql.append("  and bact.id = ract.actdefid ").append("\n");
		sql.append("  and bact.flowid = sbact.subflowid ").append("\n");
		sql.append("  and sbact.flowid = sbflow.id ").append("\n");
		if(!StringToolKit.isBlank("flowcclass"))
		{
			sql.append("  and bflow.classid = " + SQLParser.charValue(flowcclass));
		}
		
		if(!StringToolKit.isBlank(flowsno))
		{
			sql.append("  and bflow.sno = " + SQLParser.charValue(flowsno));
		}	
		
		return sql.toString();
	}
	
	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}
	
	public SkynetDao sdao() {
		return (SkynetDao)dao;
	}
	
}
