package com.skynet.framework.services.db.dybeans;

import java.sql.Statement;
import java.util.List;

import com.skynet.framework.services.db.DBSessionUtil;

public class DyDataAccess
{
	public static List queryobj(String sql) throws Exception
	{
		Statement stmt = DBSessionUtil.getSession();		
		
		DyCommandService service = new DyCommandService();
		
		service.setStmt(stmt);
		
		List list = service.queryobj(sql);
		
		return list;
	}

	public static DynamicObject queryoneobj(String sql) throws Exception
	{
		Statement stmt = DBSessionUtil.getSession();
				
		DyCommandService service = new DyCommandService();
		
		service.setStmt(stmt);
		
		DynamicObject obj = service.queryoneobj(sql);
		
		return obj;
	}
}
