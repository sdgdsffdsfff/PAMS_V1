package com.skynet.app.organ.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.app.organ.pojo.User;
import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;

@InjectName("userService")
@IocBean(args = { "refer:dao" }) 
public class UserService extends SkynetNameEntityService<User>
{
	public UserService()
	{
		super();
	}
	
	public UserService(Dao dao)
	{
		super(dao);
	}	
	
	public UserService(Dao dao, Class<User> entityType)
	{
		super(dao, entityType);
	}
	
	public DynamicObject getPrimaryDept(String loginname) throws Exception
	{
		DynamicObject dept = new DynamicObject();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select organ.* from t_sys_groupuser gu, t_sys_organ organ ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and gu.loginname = ").append(SQLParser.charValue(loginname));
		sql.append("    and gu.grouptype = 'DEPT' ").append("\n");
		sql.append("    and gu.groupid = organ.id ").append("\n");
		// sql.append("    and grouporder = '1' ");
		
		dept = sdao().queryForMap(sql.toString());
		
		return dept;
	}
	
	public DynamicObject getPrimaryOrg(String loginname) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" select organ.* ").append("\n");
		sql.append("   from t_sys_groupuser gu, t_sys_organ organ ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and gu.loginname = ").append(SQLParser.charValue(loginname));
		sql.append("    and gu.grouptype = 'DEPT' ").append("\n");
		sql.append("    and gu.groupid = organ.id ").append("\n");
		// sql.append("    and grouporder = '1' ");
		
		DynamicObject dept = sdao().queryForMap(sql.toString());
		
		String cinternal = dept.getFormatAttr("internal");
		
		DynamicObject  corgan = new DynamicObject();
		
		while(cinternal.length()>0)
		{
			corgan = new DynamicObject();
			sql = new StringBuffer();
			sql.append(" select * from t_sys_organ org ").append("\n");
			sql.append("  where 1 = 1 ").append("\n");
			sql.append("    and org.internal = ").append(SQLParser.charValue(dept.getFormatAttr("internal")));

			corgan =  sdao().queryForMap(sql.toString());
			if("ORG".equals(corgan.getFormatAttr("ctype")))
			{
				break;
			}
			cinternal = cinternal.substring(0, cinternal.length() - 4);
		}
		
		return corgan;
	}
	
}
