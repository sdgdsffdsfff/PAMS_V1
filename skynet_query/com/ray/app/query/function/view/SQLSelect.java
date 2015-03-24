package com.ray.app.query.function.view;

import java.util.List;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.dao.SkynetDao;
import com.skynet.framework.services.db.dybeans.DynamicObject;

@IocBean
@InjectName("SQLSelect")
public class SQLSelect
{
	@Inject
	protected static Dao dao;
	
	public static String test(String value) throws Exception
	{
		return "hello:" + value;
	}
	
//	public static List<Map> get_data(String sql) throws Exception
//	{
//		Map map = new HashMap();
//		List datas = searchDao.find(sql, map);
//	}
	
	public static List<DynamicObject> get_data(String sql) throws Exception
	{
		List<DynamicObject> datas = ((SkynetDao)dao).queryForList(sql);
		return datas;
	}
	
}
