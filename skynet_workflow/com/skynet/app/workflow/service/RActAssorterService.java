package com.skynet.app.workflow.service;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.app.workflow.pojo.RActAssorter;
import com.skynet.framework.service.SkynetNameEntityService;


@InjectName("ractassorterService")
@IocBean(args = { "refer:dao" })
public class RActAssorterService extends SkynetNameEntityService<RActAssorter> 
{
	public RActAssorterService() {
		super();
	}

	public RActAssorterService(Dao dao) {
		super(dao);
	}

	public RActAssorterService(Dao dao, Class<RActAssorter> entityType) {
		super(dao, entityType);
	}
	
	public String create(String assorttype, String assortctx, String consigntype, String consignctx, String runactkey, String tableid) throws Exception
	{
		String id = UUIDGenerator.getInstance().getNextValue(); //String.valueOf(System.nanoTime()); ;
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into t_sys_wfractassorter (id, assorttype, assortctx, consigntype, consignctx, runactkey) \n");
		sql.append("values(");
		sql.append(SQLParser.charValueEnd(id));
		sql.append(SQLParser.charValueEnd(assorttype));
		sql.append(SQLParser.charValueEnd(assortctx));
		sql.append(SQLParser.charValueEnd(consigntype));
		sql.append(SQLParser.charValueEnd(consignctx));
		sql.append(SQLParser.charValue(runactkey));
		sql.append(") \n");
		
		dao().execute(Sqls.create(sql.toString()));
		
		return id;
	}	
}
