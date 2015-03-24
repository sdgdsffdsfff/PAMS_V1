package com.skynet.app.workflow.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.services.db.SQLParser;
import com.skynet.app.workflow.common.WFTimeDebug;

// 工作流组织机构接口服务
@InjectName("orguiService")
@IocBean(args = { "refer:dao" })
public class OrgService
{
	@Inject
	Dao dao;
	
	// 原buildPersonIdentitiesStr
	public static String build_idens(String loginname) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		try
		{
			WFTimeDebug.log("begin build iden str:");

			sql.append("select " + SQLParser.charValue(loginname) + " groupid, 'PERSON' ctype \n");
			sql.append("  from t_sys_wfperson a \n");
			sql.append(" where 1 = 1 ");
			sql.append("   and a.loginname = ").append(SQLParser.charValue(loginname));
			sql.append(" union \n");
			sql.append("select a.groupid, 'ROLE' ctype \n");
			sql.append("  from t_sys_wfgroupuser a \n");
			sql.append(" where 1 = 1 \n");
			sql.append("   and a.loginname = " + SQLParser.charValueRT(loginname));
			sql.append("   and a.grouptype = 'ROLE' ").append("\n");
			// sql.append(" union \n");
			// sql.append("select a.groupid, 'DEPT' ctype \n");
			// sql.append("  from t_sys_wfgroupuser a, t_sys_wfdepartment b \n");
			// sql.append(" where 1 = 1 \n");
			// sql.append("   and a.groupid = b.deptid \n");
			// sql.append("   and b.workgroupflag = 0 \n");
			// sql.append("   and a.personid = " +
			// SQLParser.charValueRT(personId));
			// sql.append(" union \n");
			// sql.append("select a.groupid, 'WORKGROUP' ctype \n");
			// sql.append("  from t_sys_wfgroupuser a, t_sys_wfdepartment b \n");
			// sql.append(" where 1 = 1 \n");
			// sql.append("   and a.groupid = b.deptid \n");
			// sql.append("   and b.workgroupflag = 1 \n");
			// sql.append("   and a.personid = " +
			// SQLParser.charValueRT(personId));
			// sql.append(" union \n");
			// sql.append("select concat(concat(a.groupid, ':'), b.groupid) groupid, 'DEPTROLE' ctype \n");
			// sql.append("  from t_sys_wfgroupuser a, t_sys_wfgroupuser b, t_sys_wfdepartment c, t_sys_wfrole d \n");
			// sql.append(" where 1 = 1 \n");
			// sql.append("   and a.groupid = c.deptid \n");
			// sql.append("   and a.personid = " +
			// SQLParser.charValueRT(personId));
			// sql.append("   and c.workgroupflag = 0 \n");
			// sql.append("   and b.groupid = d.roleid \n");
			// sql.append("   and b.personid = " +
			// SQLParser.charValueRT(personId));

			WFTimeDebug.log("end build iden str:");
			return sql.toString();

		}
		catch (Exception e)
		{
			System.out.println(e);
			throw e;
		}
	}

}
