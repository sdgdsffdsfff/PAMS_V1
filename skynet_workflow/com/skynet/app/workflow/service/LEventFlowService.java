package com.skynet.app.workflow.service;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.services.db.SQLParser;
import com.skynet.app.workflow.pojo.LEventFlow;
import com.skynet.framework.service.SkynetNameEntityService;

@InjectName("leventflowService")
@IocBean(args = { "refer:dao" })
public class LEventFlowService extends SkynetNameEntityService<LEventFlow> {
	public LEventFlowService() {
		super();
	}

	public LEventFlowService(Dao dao) {
		super(dao);
	}

	public LEventFlowService(Dao dao, Class<LEventFlow> entityType) {
		super(dao, entityType);
	}

	public String create(String id, String eventer, String beginstate,
			String endstate, String flowdefid, String runflowkey,
			String eventtype) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into t_sys_wfleventflow (id, eventer, beginstate, endstate, flowdefid, runflowkey, eventtype) \n");
		sql.append(" values (");
		sql.append(SQLParser.charValueEnd(id));
		sql.append(SQLParser.charValueEnd(eventer));
		sql.append(SQLParser.charValueEnd(beginstate));
		sql.append(SQLParser.charValueEnd(endstate));
		sql.append(SQLParser.charValueEnd(flowdefid));
		sql.append(SQLParser.charValueEnd(runflowkey));
		sql.append(SQLParser.charValue(eventtype));
		sql.append(")");

		dao().execute(Sqls.create(sql.toString()));
		return id;
	}

}
