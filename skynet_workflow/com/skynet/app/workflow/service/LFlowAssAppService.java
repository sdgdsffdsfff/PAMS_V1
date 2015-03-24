package com.skynet.app.workflow.service;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.app.workflow.pojo.LFlowAssApp;
import com.skynet.framework.service.SkynetNameEntityService;

@InjectName("lflowassappService")
@IocBean(args = { "refer:dao" })
public class LFlowAssAppService extends SkynetNameEntityService<LFlowAssApp> {
	public LFlowAssAppService() {
		super();
	}

	public LFlowAssAppService(Dao dao) {
		super(dao);
	}

	public LFlowAssAppService(Dao dao, Class<LFlowAssApp> entityType) {
		super(dao, entityType);
	}
	
	public String create(String runflowkey, String formid, String dataid, String tableid, String workname, String flowdefid) throws Exception
	{
		String id = UUIDGenerator.getInstance().getNextValue(); //String.valueOf(System.nanoTime()); ;
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into T_SYS_WFlflowassapp (id, runflowkey, formid, dataid, tableid, workname, flowdefid, createtime) \n");
		sql.append("values (");
		sql.append(SQLParser.charValueEnd(id));
		sql.append(SQLParser.charValueEnd(runflowkey));
		sql.append(SQLParser.charValueEnd(formid));
		sql.append(SQLParser.charValueEnd(dataid));
		sql.append(SQLParser.charValueEnd(tableid));
		sql.append(SQLParser.charValueEnd(workname));
		sql.append(SQLParser.charValueEnd(flowdefid));
		sql.append(SQLParser.exp(" sysdate "));
		sql.append(")");
		
		dao().execute(Sqls.create(sql.toString()));
		return id;
	}	
}
