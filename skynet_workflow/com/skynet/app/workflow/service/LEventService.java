package com.skynet.app.workflow.service;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.app.workflow.pojo.LEvent;
import com.skynet.framework.service.SkynetNameEntityService;

@InjectName("leventService")
@IocBean(args = { "refer:dao" })
public class LEventService extends SkynetNameEntityService<LEvent> {
	public LEventService() {
		super();
	}

	public LEventService(Dao dao) {
		super(dao);
	}

	public LEventService(Dao dao, Class<LEvent> entityType) {
		super(dao, entityType);
	}
	
	public String create(String eventtime, String eventtype, String runflowkey) throws Exception
	{
		String id = UUIDGenerator.getInstance().getNextValue(); //String.valueOf(System.nanoTime()); ;
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into t_sys_wflevent (id, eventtime, ceventtime, eventtype, runflowkey) \n");
		sql.append("values (");
		sql.append(SQLParser.charValueEnd(id));
		sql.append(SQLParser.expEnd("sysdate"));
		sql.append(SQLParser.charValueEnd(eventtime));
		sql.append(SQLParser.charValueEnd(eventtype));
		sql.append(SQLParser.charValue(runflowkey));
		sql.append(")");
		
		dao().execute(Sqls.create(sql.toString()));
		return id;
	}	
}
