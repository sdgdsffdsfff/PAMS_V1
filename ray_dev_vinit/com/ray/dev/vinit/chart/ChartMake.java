package com.ray.dev.vinit.chart;

import com.headray.framework.services.db.SQLParser;


public class ChartMake
{
	public static void main(String[] args)
	{
//		project_chart();
		task_chart();
	}
	
	public static void config_chart()
	{
		// chart("config.config.pzxbgcbfl", "com.ray.xj.sgcc.irm.report.config.pzxbgcbfl.service.RepPZXBGCBFL", "pzxfl", "配置项类别","applytime", "时间", "pzxsl,bgcs,bgzjcb,bgrlcb", "配置项数量（项）,变更次数（次）,变更资金成本（元）,变更人力成本（人天）");
		chart("config.config.yyryjgc", "com.ray.xj.sgcc.irm.report.config.yyryjgc.service.RepYYRYJGC", "yyxt", "应用系统","", "", "zjzs,xnzjzs,ccsbzs,wlsbzs,zjjzs,sjkzs,yyslzs,sjkslzs,czxtzs,qtrjzs,jcgxzs", "主机总数,虚拟主机总数,存储设备总数,网络设备总数,中间件部署总数,数据库部署总数,应用实例部署总数,数据库实例部署总数,操作系统部署总数,其他软件总数,集成关系总数");
		chart("config.config.yyryjgczj", "com.ray.xj.sgcc.irm.report.config.yyryjgczj.service.RepYYRYJGCZJ", "yyxt", "应用系统","", "", "zjzs,xnzjzs,ccsbzs,wlsbzs,zjjzs,sjkzs,yyslzs,sjkslzs,czxtzs,qtrjzs,jcgxzs", "主机总数,虚拟主机总数,存储设备总数,网络设备总数,中间件部署总数,数据库部署总数,应用实例部署总数,数据库实例部署总数,操作系统部署总数,其他软件总数,集成关系总数");

	}

	
	public static void project_chart()
	{
		// 项目状态分类
		chart("project.project.xmztcbflhzb", "com.ray.xj.sgcc.irm.report.project.xmztcbflhzb.service.RepXMZTCBFLHZB", "cclass", "项目分类","cclass", "项目分类", "num1,num2,num3,num4,num5", "正常,拖期,暂停,取消,完成");
		// 项目人力成本
		chart("project.project.xmrlcbflhzb", "com.ray.xj.sgcc.irm.report.project.xmztcbflhzb.service.RepXMZTCBFLHZB", "cclass", "项目分类","cclass", "项目分类", "sumfn", "人力成本");
		// 项目资金成本
		chart("project.project.xmzzcbflhzb", "com.ray.xj.sgcc.irm.report.project.xmztcbflhzb.service.RepXMZTCBFLHZB", "cclass", "项目分类","cclass", "项目分类", "sumhr", "资金成本");
		
		// 项目经理状态分类
		chart("project.project.xmjlcxxmzt", "com.ray.xj.sgcc.irm.report.project.xmjlcxxmzt.service.RepXMJLCXXMZT", "pm", "项目经理","pm", "项目经理", "num1,num2,num3,num4,num5", "正常,拖期,暂停,取消,完成");
		// 项目经理人力成本
		chart("project.project.xmjltjrlcb", "com.ray.xj.sgcc.irm.report.project.xmjlcxxmzt.service.RepXMJLCXXMZT", "pm", "项目经理","pm", "项目经理", "sumfn", "人力成本");
		// 项目经理资金成本
		chart("project.project.xmjltjzjcb", "com.ray.xj.sgcc.irm.report.project.xmjlcxxmzt.service.RepXMJLCXXMZT", "pm", "项目经理","pm", "项目经理", "sumhr", "资金成本");
	}
	
	public static void task_chart()
	{
		// 任务状态分类
		chart("task.task.rwztcbflhzb", "com.ray.xj.sgcc.irm.report.task.rwztcbflhzb.service.RepRWZTCBFLHZB", "tasktype", "任务分类","tasktype", "任务分类", "num1,num2,num3,num4,num5", "正常,拖期,暂停,取消,完成");
		// 任务人力成本
		chart("task.task.rwrlcbflhzb", "com.ray.xj.sgcc.irm.report.task.rwztcbflhzb.service.RepRWZTCBFLHZB", "tasktype", "任务分类","tasktype", "任务分类", "sumhr", "人力成本");
		// 任务资金成本
		chart("task.task.rwzjcbflhzb", "com.ray.xj.sgcc.irm.report.task.rwztcbflhzb.service.RepRWZTCBFLHZB", "tasktype", "任务分类","tasktype", "任务分类", "sumfn", "资金成本");
		
		// 任务下达人状态分类
		chart("task.task.rwxdrcxxmzt", "com.ray.xj.sgcc.irm.report.task.rwxdrcxxmzt.service.RepRWXDRCXXMZT", "issuer", "任务下达人","issuer", "任务下达人", "num1,num2,num3,num4,num5", "正常,拖期,暂停,取消,完成");
		// 任务下达人人力成本
		chart("task.task.rwxdrcxxmrlcb", "com.ray.xj.sgcc.irm.report.task.rwxdrcxxmzt.service.RepRWXDRCXXMZT", "issuer", "任务下达人","issuer", "任务下达人", "sumhr", "人力成本");
		// 任务下达人资金成本
		chart("task.task.rwxdrcxxmzjcb", "com.ray.xj.sgcc.irm.report.task.rwxdrcxxmzt.service.RepRWXDRCXXMZT", "issuer", "任务下达人","issuer", "任务下达人", "sumfn", "资金成本");
	}
	
	
	public static void chart(String chartname, String beanid, String fs, String fscname, String fx, String fxcname, String fy, String fycname)
	{
		StringBuffer sql = new StringBuffer();
		
		String[] fys = fy.split(",");
		String[] fycnames = fycname.split(",");
		String ctype = "Column3D";
		if(fys.length>1)
		{
			ctype = "MSColumn3D";
		}
		
		sql = new StringBuffer();
		sql.append(" delete from t_app_chartoption where 1 = 1 and chartid = " + SQLParser.charValue(chartname)).append(";");
		System.out.println(sql.toString());
		sql = new StringBuffer();
		sql.append(" delete from t_app_chart where 1 = 1 and id = " + SQLParser.charValue(chartname)).append(";");
		System.out.println(sql.toString());
		
		sql = new StringBuffer();		
		sql.append(" insert into t_app_chart (id, chartname, ctype, beanid, fs, fscname, fx, fxcname, width, height)");
		sql.append(" values ( ");
		sql.append(SQLParser.charValueEnd(chartname));
		sql.append(SQLParser.charValueEnd(chartname));
		sql.append(SQLParser.charValueEnd(ctype));
		sql.append(SQLParser.charValueEnd(beanid));
		sql.append(SQLParser.charValueEnd(fs));
		sql.append(SQLParser.charValueEnd(fscname));
		sql.append(SQLParser.charValueEnd(fx));
		sql.append(SQLParser.charValueEnd(fxcname));
		sql.append(SQLParser.numberValueEnd("473"));
		sql.append(SQLParser.numberValue("300"));
		sql.append(" ); ");
		
		System.out.println(sql.toString());
		
		for(int i=0;i<fys.length;i++)
		{
			sql = new StringBuffer();
			sql.append(" insert into t_app_chartoption (id, chartid, fy, fycname, oorder)");
			sql.append(" values (");
			sql.append(SQLParser.charValueEnd(chartname + "." + fys[i]));
			sql.append(SQLParser.charValueEnd(chartname));
			sql.append(SQLParser.charValueEnd(fys[i]));
			sql.append(SQLParser.charValueEnd(fycnames[i]));
			sql.append(SQLParser.numberValue(i + 1));
			sql.append(" ); ");
			System.out.println(sql.toString());
		}
		
		System.out.println("");
		
	}
	
	public static void widget(String chartname, String beanid, String fs, String fscname, String fx, String fxcname, String fy, String fycname)
	{
		StringBuffer sql = new StringBuffer();
		
		String[] fys = fy.split(",");
		String[] fycnames = fycname.split(",");
		String ctype = "AngularGauge";
		
		sql = new StringBuffer();
		sql.append(" delete from t_app_chartoption where 1 = 1 and chartid = " + SQLParser.charValue(chartname)).append(";");
		System.out.println(sql.toString());
		sql = new StringBuffer();
		sql.append(" delete from t_app_chart where 1 = 1 and id = " + SQLParser.charValue(chartname)).append(";");
		System.out.println(sql.toString());
		
		sql = new StringBuffer();		
		sql.append(" insert into t_app_chart (id, chartname, ctype, beanid, fs, fscname, fx, fxcname, width, height)");
		sql.append(" values ( ");
		sql.append(SQLParser.charValueEnd(chartname));
		sql.append(SQLParser.charValueEnd(chartname));
		sql.append(SQLParser.charValueEnd(ctype));
		sql.append(SQLParser.charValueEnd(beanid));
		sql.append(SQLParser.charValueEnd(fs));
		sql.append(SQLParser.charValueEnd(fscname));
		sql.append(SQLParser.charValueEnd(fx));
		sql.append(SQLParser.charValueEnd(fxcname));
		sql.append(SQLParser.numberValueEnd("473"));
		sql.append(SQLParser.numberValue("300"));
		sql.append(" ); ");
		
		System.out.println(sql.toString());
		
		for(int i=0;i<fys.length;i++)
		{
			sql = new StringBuffer();
			sql.append(" insert into t_app_chartoption (id, chartid, fy, fycname, oorder)");
			sql.append(" values (");
			sql.append(SQLParser.charValueEnd(chartname + "." + fys[i]));
			sql.append(SQLParser.charValueEnd(chartname));
			sql.append(SQLParser.charValueEnd(fys[i]));
			sql.append(SQLParser.charValueEnd(fycnames[i]));
			sql.append(SQLParser.numberValue(i + 1));
			sql.append(" ); ");
			System.out.println(sql.toString());
		}
		
		System.out.println("");
		
	}

	
}
