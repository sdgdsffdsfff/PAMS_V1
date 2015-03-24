package com.skynet.app.workflow.service;

import java.util.List;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.app.workflow.pojo.BRoute;
import com.skynet.framework.service.SkynetNameEntityService;

@InjectName("brouteService")
@IocBean(args = { "refer:dao" }) 
public class BRouteService extends SkynetNameEntityService<BRoute>
{
	public BRouteService() {
		super();
	}

	public BRouteService(Dao dao) {
		super(dao);
	}

	public BRouteService(Dao dao, Class<BRoute> entityType) {
		super(dao, entityType);
	}
	
	public List<DynamicObject> getRoutes(String actdefid) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select a.id, a.endactid, b.cname endactname, b.ctype, a.cname routename \n");
		sql.append("  from t_sys_wfbroute a, t_sys_wfbact b \n");
		sql.append(" where 1 = 1 \n");
		sql.append("   and a.endactid = b.id \n");
		sql.append("   and a.flowid = b.flowid \n");
		sql.append("   and a.startactid = " + SQLParser.charValueRT(actdefid));
		
		List<DynamicObject> routes = sdao().queryForList(sql.toString());
		return routes;
	}
	
}
