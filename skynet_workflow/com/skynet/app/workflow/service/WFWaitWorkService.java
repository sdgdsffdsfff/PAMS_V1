package com.skynet.app.workflow.service;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.app.workflow.pojo.WFWaitWork;
import com.skynet.framework.service.SkynetNameEntityService;


@InjectName("waitworkService")
@IocBean(args = { "refer:dao" })
public class WFWaitWorkService extends SkynetNameEntityService<WFWaitWork>
{
	public WFWaitWorkService() {
		super();
	}

	public WFWaitWorkService(Dao dao) {
		super(dao);
	}

	public WFWaitWorkService(Dao dao, Class<WFWaitWork> entityType) {
		super(dao, entityType);
	}
	
	public String create(WFWaitWork waitwork) throws Exception
	{
		String id = UUIDGenerator.getInstance().getNextValue(); 
		waitwork.setId(id);
		dao().insert(waitwork);
		return waitwork.getId();
	}

	public void remove(String runactkey) throws Exception
	{
		StringBuffer sql = new StringBuffer(); 

		sql.append("delete from t_sys_wfwaitwork \n");
		sql.append(" where 1 = 1 \n");
		sql.append(" and runactkey = " + SQLParser.charValueRT(runactkey));
		
		dao().execute(Sqls.create(sql.toString()));
	}
	
	public void remove(String runactkey, String receiver) throws Exception
	{
		StringBuffer sql = new StringBuffer(); 

		sql.append("delete from t_sys_wfwaitwork \n");
		sql.append(" where 1 = 1 \n");
		sql.append(" and runactkey = " + SQLParser.charValueRT(runactkey));
		sql.append(" and receiver = " + SQLParser.charValueRT(receiver));
		
		dao().execute(Sqls.create(sql.toString()));
	}
	
	public void removeAll(String runflowkey) throws Exception
	{
		StringBuffer sql = new StringBuffer(); 
		
		sql.append("delete from t_sys_wfwaitwork \n");
		sql.append(" where 1 = 1 \n");
		sql.append(" and runflowkey =  " + SQLParser.charValueRT(runflowkey));
		
		dao().execute(Sqls.create(sql.toString()));
	}
		
}
