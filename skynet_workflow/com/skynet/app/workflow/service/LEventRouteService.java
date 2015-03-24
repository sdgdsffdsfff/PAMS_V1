package com.skynet.app.workflow.service;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.services.db.SQLParser;
import com.skynet.app.workflow.pojo.LEventRoute;
import com.skynet.framework.service.SkynetNameEntityService;

@InjectName("leventrouteService")
@IocBean(args = { "refer:dao" })
public class LEventRouteService extends SkynetNameEntityService<LEventRoute> {
	public LEventRouteService() {
		super();
	}

	public LEventRouteService(Dao dao) {
		super(dao);
	}

	public LEventRouteService(Dao dao, Class<LEventRoute> entityType) {
		super(dao, entityType);
	}

	public String create(String id, String flowdefid, String runflowkey,
			String eventer, String eventercname, String endactdefid, String endrunactkey,
			String startactdefid, String startrunactkey, String eventtype)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into t_sys_wfleventroute (id, flowdefid, runflowkey, eventer, eventercname, endactdefid, endrunactkey, startactdefid, startrunactkey, eventtype) \n");
		sql.append(" values (");
		sql.append(SQLParser.charValueEnd(id));
		sql.append(SQLParser.charValueEnd(flowdefid));
		sql.append(SQLParser.charValueEnd(runflowkey));
		sql.append(SQLParser.charValueEnd(eventer));
		sql.append(SQLParser.charValueEnd(eventercname));
		sql.append(SQLParser.charValueEnd(endactdefid));
		sql.append(SQLParser.charValueEnd(endrunactkey));
		sql.append(SQLParser.charValueEnd(startactdefid));
		sql.append(SQLParser.charValueEnd(startrunactkey));
		sql.append(SQLParser.charValue(eventtype));
		sql.append(")");

		dao().execute(Sqls.create(sql.toString()));
		return id;
	}
}
