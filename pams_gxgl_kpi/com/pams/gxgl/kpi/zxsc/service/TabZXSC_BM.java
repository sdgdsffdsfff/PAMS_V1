package com.pams.gxgl.kpi.zxsc.service;

import java.util.List;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.pams.gxgl.rep.helper.ZXQKHelper;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.pams.report.common.RepHelper;
import com.skynet.pams.report.common.ReportService;

@InjectName("tabZXSC_BM")
@IocBean(args = { "refer:dao" }) 
public class TabZXSC_BM extends ReportService
{

	public List execute(DynamicObject obj) throws Exception
	{
		String begindate = obj.getFormatAttr("begindate");
		String enddate = obj.getFormatAttr("enddate");
		String internal = obj.getFormatAttr("internal");
		String reptype = obj.getFormatAttr("reptype");
		
		String sql_cdate = RepHelper.compare_sysdate(enddate);
		
		obj.setAttr("sql_cdate", sql_cdate);
		obj.setAttr("ispublish", "");
		obj.setAttr("isovertime", "");			
		obj.setAttr("reptype", reptype);	
	    
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select org.internal, org.cname orgcname, case when sum(zxsccskh) is null then 0 else sum(zxsccskh) end zxsccskh ").append("\n");
		sql.append("   from t_sys_organ org ").append("\n");
		sql.append("   left join   ").append("\n");
		sql.append("  (   ").append("\n");
		
		sql.append("  select org.internal, sum(zxsccskh) zxsccskh ").append("\n");
		sql.append("  from t_sys_organ org,  ").append("\n");
		sql.append("  ( ").append("\n");
		
		sql.append("  select v.deptid, v.creater, v.cno, v.actdefid, case when zxsccs > 5 then 5 else zxsccs end zxsccskh ").append("\n");
		sql.append("    from").append("\n");
		sql.append("    ( ").append("\n");
		sql.append("    select v.deptid, v.creater, v.cno, v.actdefid, case when zxsc < 1 then 0 else ceil(zxsc - 1) end zxsccs ").append("\n");
		sql.append("      from ").append("\n");
		sql.append("      ( ").append("\n");
		
		sql.append(ZXQKHelper.sql_xxgx_kpi_zxsc1(obj));
		
		sql.append("      ) v ").append("\n");
		sql.append("    ) v ").append("\n");
		sql.append("  ) v ").append("\n");
		sql.append(" where 1 = 1 ").append("\n");
		sql.append("   and org.id = v.deptid ").append("\n");
		sql.append(" group by org.internal ").append("\n");
		sql.append("  ) v ").append("\n");		
		sql.append(" on substr(v.internal, 0, length(org.internal)) = org.internal ").append("\n");
		sql.append(" where 1 = 1 ").append("\n");
		sql.append("   and org.ctype = 'DEPT' ").append("\n");
		if(!StringToolKit.isBlank(internal))
		{
			sql.append(" and substr(org.internal, 0, length(org.internal) - 4) = " + SQLParser.charValue(internal)).append("\n");
		}
		sql.append("   group by org.internal, org.cname ").append("\n");
		sql.append(" order by internal ").append("\n");

		List datas = sdao().queryForList(sql.toString());

		return datas;
	}

}
