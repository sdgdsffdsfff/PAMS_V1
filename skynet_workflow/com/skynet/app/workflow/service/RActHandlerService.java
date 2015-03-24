package com.skynet.app.workflow.service;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.app.workflow.pojo.RActHandler;
import com.skynet.app.workflow.spec.SplitTableConstants;
import com.skynet.framework.service.SkynetNameEntityService;


@InjectName("racthandlerService")
@IocBean(args = { "refer:dao" })
public class RActHandlerService extends SkynetNameEntityService<RActHandler>  
{
	public RActHandlerService() {
		super();
	}

	public RActHandlerService(Dao dao) {
		super(dao);
	}

	public RActHandlerService(Dao dao, Class<RActHandler> entityType) {
		super(dao, entityType);
	}
	

	public String create(String runactkey, String handlerctx, String handlercname, String ctype, String tableid) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from t_sys_wfracthandler a \n");
		sql.append(" where 1 = 1 \n");
		sql.append("   and a.runactkey = " + SQLParser.charValueRT(runactkey));
		sql.append("   and a.handlerctx = " + SQLParser.charValueRT(handlerctx));
		sql.append("   and a.ctype = " + SQLParser.charValueRT(ctype));
		
		DynamicObject obj = queryForMap(sql.toString());
		if(!obj.getFormatAttr("id").equals(""))
		{
			return obj.getFormatAttr("id"); 
		}
		
		String id = UUIDGenerator.getInstance().getNextValue(); //String.valueOf(System.nanoTime()); ;
		sql = new StringBuffer();
		sql.append("insert into t_sys_wfracthandler (id, runactkey, handlerctx, cname, ctype) \n");
		sql.append("values(");
		sql.append(SQLParser.charValueEnd(id));
		sql.append(SQLParser.charValueEnd(runactkey));
		sql.append(SQLParser.charValueEnd(handlerctx));
		sql.append(SQLParser.charValueEnd(handlercname));
		sql.append(SQLParser.charValue(ctype));
		sql.append(") \n");

		dao().execute(Sqls.create(sql.toString()));
		return id;
	}
	
	/*	
	public String create(String runactkey, String userid, String usertype, String dept, String tableid) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from t_sys_wfracthandler a \n");
		sql.append(" where 1 = 1 \n");
		sql.append("   and a.runactkey = " + SQLParser.charValueRT(runactkey));
		sql.append("   and a.handlerctx = " + SQLParser.charValueRT(userid));
		sql.append("   and a.ctype = " + SQLParser.charValueRT(usertype));
		sql.append("   and a.handledeptctx = " + SQLParser.charValueRT(dept));		
		
		DynamicObject obj = queryForMap(sql.toString());
		if(!obj.getFormatAttr("id").equals(""))
		{
			return obj.getFormatAttr("id"); 
		}
		
		String id = UUIDGenerator.getInstance().getNextValue(); //String.valueOf(System.nanoTime()); ;
		sql = new StringBuffer();
		sql.append("insert into " + SplitTableConstants.getSplitTable("t_sys_wfracthandler", tableid) + " (id, runactkey, ctype, handlerctx, handledeptctx) \n");
		sql.append(" values("); 
		sql.append(SQLParser.charValueEnd(id)); 
		sql.append(SQLParser.charValueEnd(runactkey)); 		
		sql.append(SQLParser.charValueEnd(usertype));
		sql.append(SQLParser.charValueEnd(userid));
		sql.append(SQLParser.charValue(dept));
		sql.append(") \n");
		dao().execute(Sqls.create(sql.toString()));
		return id;
	}
	
	
	public String create(String runactkey, String ctype, String handlerctx, String handlercname, String dept, String deptcname, String tableid) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from " + SplitTableConstants.getSplitTable("t_sys_wfracthandler", tableid) + " a \n");
		sql.append(" where 1 = 1 \n");
		sql.append("   and a.runactkey = " + SQLParser.charValueRT(runactkey));
		sql.append("   and a.handlerctx = " + SQLParser.charValueRT(handlerctx));
		sql.append("   and a.ctype = " + SQLParser.charValueRT(ctype));
		sql.append("   and a.handledeptctx = " + SQLParser.charValueRT(dept));		


		DynamicObject obj = queryForMap(sql.toString());
		if(!obj.getFormatAttr("id").equals(""))
		{
			return obj.getFormatAttr("id"); 
		}
		
		String id = UUIDGenerator.getInstance().getNextValue(); //String.valueOf(System.nanoTime()); ;
		
		sql = new StringBuffer();
		sql.append("insert into " + SplitTableConstants.getSplitTable("t_sys_wfracthandler", tableid) + " (id, runactkey,ctype,handlerctx, cname, handledeptctx, handledeptcname) \n");
		sql.append(" values("); 
		sql.append(SQLParser.charValueEnd(id)); 
		sql.append(SQLParser.charValueEnd(runactkey)); 		
		sql.append(SQLParser.charValueEnd(ctype));
		sql.append(SQLParser.charValueEnd(handlerctx));
		sql.append(SQLParser.charValueEnd(handlercname));
		sql.append(SQLParser.charValueEnd(dept));
		sql.append(SQLParser.charValue(deptcname));
		sql.append(") \n");
		dao().execute(Sqls.create(sql.toString()));

		return id;
	}
	
	*/

	public void remove(String runactkey, String handlerctx, String ctype, String tableid) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append("delete from " + SplitTableConstants.getSplitTable("t_sys_wfracthandler", tableid) + " a \n");
		sql.append(" where 1 = 1 \n");
		sql.append("   and a.runactkey = " + SQLParser.charValueRT(runactkey));
		sql.append("   and a.handlerctx = " + SQLParser.charValueRT(handlerctx));
		sql.append("   and a.ctype = " + SQLParser.charValueRT(ctype));
		
		dao().execute(Sqls.create(sql.toString()));
	}
}
