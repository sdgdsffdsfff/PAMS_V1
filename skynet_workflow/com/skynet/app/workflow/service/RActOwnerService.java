package com.skynet.app.workflow.service;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.app.workflow.pojo.RActOwner;
import com.skynet.app.workflow.spec.SplitTableConstants;
import com.skynet.framework.service.SkynetNameEntityService;


@InjectName("ractownerService")
@IocBean(args = { "refer:dao" })
public class RActOwnerService extends SkynetNameEntityService<RActOwner>  
{
	public RActOwnerService() {
		super();
	}

	public RActOwnerService(Dao dao) {
		super(dao);
	}

	public RActOwnerService(Dao dao, Class<RActOwner> entityType) {
		super(dao, entityType);
	}
	
//	public String create(String runactkey, String ownerctx, String cname, String ctype, String tableid) throws Exception
//	{
//		StringBuffer sql = new StringBuffer();
//		sql.append("select * from t_sys_wfractowner a \n");
//		sql.append(" where 1 = 1 \n");
//		sql.append("   and a.runactkey = " + SQLParser.charValueRT(runactkey));
//		sql.append("   and a.ownerctx = " + SQLParser.charValueRT(ownerctx));
//		sql.append("   and a.ctype = " + SQLParser.charValueRT(ctype));
//		
//		DynamicObject obj = queryForMap(sql.toString());
//
//		if(!obj.getFormatAttr("id").equals(""))
//		{
//			return obj.getFormatAttr("id"); 
//		}
//		
//		String id = UUIDGenerator.getInstance().getNextValue(); //String.valueOf(System.nanoTime()); ;
//		
//		sql = new StringBuffer();
//		sql.append("insert into t_sys_wfractowner (id, runactkey,ctype,ownerctx, cname, complete) \n");
//		sql.append("values(");
//		sql.append(SQLParser.charValueEnd(id));
//		sql.append(SQLParser.charValueEnd(runactkey));
//		sql.append(SQLParser.charValueEnd(ctype));
//		sql.append(SQLParser.charValueEnd(ownerctx));
//		sql.append(SQLParser.charValueEnd(cname));
//		sql.append(SQLParser.charValue("N"));
//		sql.append(") \n");
//		
//		//
//		dao().execute(Sqls.create(sql.toString()));
//		return id;
//	}
	
	public String create(String runactkey, String ownerctx, String cname, String ctype, String complete, String tableid, String organid, String organname, String organtype) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from t_sys_wfractowner a \n");
		sql.append(" where 1 = 1 \n");
		sql.append("   and a.runactkey = " + SQLParser.charValueRT(runactkey));
		sql.append("   and a.ownerctx = " + SQLParser.charValueRT(ownerctx));
		sql.append("   and a.ctype = " + SQLParser.charValueRT(ctype));
		
		DynamicObject obj = queryForMap(sql.toString());
		if(!obj.getFormatAttr("id").equals(""))
		{
			return obj.getFormatAttr("id"); 
		}
		
		String id = UUIDGenerator.getInstance().getNextValue(); //String.valueOf(System.nanoTime()); ;
		sql = new StringBuffer();
		sql.append("insert into t_sys_wfractowner (id, runactkey, ownerctx, cname, ctype, complete, organid, organname, organtype) \n");
		sql.append("values(");
		sql.append(SQLParser.charValueEnd(id));
		sql.append(SQLParser.charValueEnd(runactkey));
		sql.append(SQLParser.charValueEnd(ownerctx));
		sql.append(SQLParser.charValueEnd(cname));
		sql.append(SQLParser.charValueEnd(ctype));
		sql.append(SQLParser.charValueEnd(complete));
		sql.append(SQLParser.charValueEnd(organid));
		sql.append(SQLParser.charValueEnd(organname));
		sql.append(SQLParser.charValue(organtype));
		sql.append(") \n");
		//
		dao().execute(Sqls.create(sql.toString()));
		return id;
	}
	
	public void remove(String id, String tableid) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append("delete from t_sys_wfractowner" + " \n");
		sql.append(" where 1 = 1 \n");
		sql.append("   and id = " + SQLParser.charValueRT(id));

		dao().execute(Sqls.create(sql.toString()));
	}
	
	public void delete_fk_runactkey(String runactkey, String tableid) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append("delete from t_sys_wfractowner \n");
		sql.append(" where 1 = 1 \n");
		sql.append("   and runactkey = " + SQLParser.charValueRT(runactkey));
		 
		dao().execute(Sqls.create(sql.toString()));
	}	
	
}
