package com.pams.gxgl.kpi.jsl.service;

import java.util.List;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.pams.gxgl.rep.helper.ZXQKHelper;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.pams.report.common.RepHelper;
import com.skynet.pams.report.common.ReportService;

@InjectName("tabJSL_RY")
@IocBean(args = { "refer:dao" }) 
public class TabJSL_RY extends ReportService
{
	public List execute(DynamicObject obj) throws Exception
	{
		String begindate = obj.getFormatAttr("begindate");
		String enddate = obj.getFormatAttr("enddate");
		String internal = obj.getFormatAttr("internal");
		String reptype = obj.getFormatAttr("reptype");
		
		String sql_cdate = RepHelper.compare_sysdate(enddate);
		
		obj.setAttr("sql_cdate", sql_cdate);
		obj.setAttr("ispublish", "Y");
		obj.setAttr("isovertime", "Y");	
		obj.setAttr("isnodeovertime", "");		
		obj.setAttr("reptype", reptype);	
	    
		StringBuffer sql = new StringBuffer();
		
		sql.append("  select usr.loginname, usr.cname username, usr.ordernum, usr.orgname orgcname, usr.ownerdept, usr.deptname deptcname, case when sum(zxsckh) is null then 0 else sum(zxsckh) end zxsccskh ").append("\n");
		sql.append("  from t_sys_user usr ").append("\n");
		sql.append("  left join ").append("\n");
		sql.append("  ( ").append("\n");
		
		sql.append("    select v.deptid, v.creater, v.cno,case when ceil(sum(zxsc)-count(v.actdefid)) < 5 then  ceil(sum(zxsc)-count(v.actdefid)) else 5 end zxsckh  ").append("\n");
		sql.append("      from ").append("\n");
		sql.append("      ( ").append("\n");
		
		sql.append(ZXQKHelper.sql_xxgx_kpi_zxsc1(obj));
		
		sql.append("      ) v ").append("\n");

		sql.append(" group by v.deptid, v.creater, v.cno");
		
		sql.append("  ) v ").append("\n");
		sql.append("  on usr.loginname = v.creater ").append("\n");
		sql.append(" where 1 = 1 ").append("\n");
		sql.append(" and zxsckh >0 ").append("\n");
		if(!StringToolKit.isBlank(internal))
		{
			sql.append(" and usr.ownerdept = " + SQLParser.charValue(internal)).append("\n");
		}		
		
		sql.append(" group by usr.loginname, usr.cname, usr.ordernum, usr.orgname, usr.ownerdept, usr.deptname ").append("\n");
		sql.append(" order by usr.ownerdept, usr.ordernum, usr.cname ").append("\n");

		List datas = sdao().queryForList(sql.toString());

		return datas;
	}
}
