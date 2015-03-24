package com.skynet.app.workflow.service;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.app.workflow.pojo.RFlowAuthor;
import com.skynet.app.workflow.spec.SplitTableConstants;
import com.skynet.framework.service.SkynetNameEntityService;

@InjectName("rflowauthorService")
@IocBean(args = { "refer:dao" })
public class RFlowAuthorService extends SkynetNameEntityService<RFlowAuthor>
{
	public RFlowAuthorService() {
		super();
	}

	public RFlowAuthorService(Dao dao) {
		super(dao);
	}

	public RFlowAuthorService(Dao dao, Class<RFlowAuthor> entityType) {
		super(dao, entityType);
	}
	
	public DynamicObject findById(DynamicObject obj) throws Exception
	{
		String runflowkey = obj.getAttr("runflowkey");
		return locate(runflowkey);
	}

	public DynamicObject locate(String runflowkey) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select id, authorctx, ctype, runflowkey \n");
		sql.append("  from t_sys_wfrflowauthor \n");
		sql.append(" where 1 = 1 \n");
		sql.append("   and runflowkey = " + SQLParser.charValue(runflowkey));		

		DynamicObject dvo = queryForMap(sql.toString());
		return dvo;
	}
	
	public DynamicObject findById(String runflowkey, String tableid) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select id, authorctx, ctype, runflowkey \n");
		sql.append("  from " + SplitTableConstants.getSplitTable("t_sys_wfrflowauthor", tableid) + " \n");
		sql.append(" where 1 = 1 \n");
		sql.append("   and runflowkey = " + SQLParser.charValue(runflowkey));		

		DynamicObject dvo = queryForMap(sql.toString());
		return dvo;
	}
	
	// 新增流程实例作者
	public String create(DynamicObject obj) throws Exception
	{
		//String id = KeyGenerator.getInstance().getNextValue("t_sys_wfrFLOWAUTHOR", "ID");
		String authorctx = obj.getAttr("authorctx");
		String cname = obj.getAttr("cname");
		String ctype = obj.getAttr("ctype");
		String runflowkey = obj.getAttr("runflowkey");
		String runactkey = obj.getAttr("runactkey");
		String authorsource = obj.getAttr("authorsource");
		String tableid = obj.getAttr("tableid");
		return create(authorctx, cname, ctype, runflowkey, runactkey, authorsource, tableid);
	}
	
	// 新增流程实例作者
	public String create(String authorctx, String authorcname, String ctype, String runflowkey, String runactkey, String authorsource, String tableid) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from " + SplitTableConstants.getSplitTable("t_sys_wfrflowauthor", tableid) + " a \n");
		sql.append(" where 1 = 1 \n");
		sql.append("   and a.runflowkey = " + SQLParser.charValueRT(runflowkey));
		sql.append("   and a.runactkey = " + SQLParser.charValueRT(runactkey));		
		sql.append("   and a.authorctx = " + SQLParser.charValueRT(authorctx));
		sql.append("   and a.ctype = " + SQLParser.charValueRT(authorcname));
		
		DynamicObject obj = queryForMap(sql.toString());
		
		if(!obj.getFormatAttr("id").equals(""))
		{
			return obj.getFormatAttr("id"); 
		}

		String id = UUIDGenerator.getInstance().getNextValue(); //String.valueOf(System.nanoTime()); ;
		
		sql = new StringBuffer();
		sql.append("insert into " + SplitTableConstants.getSplitTable("t_sys_wfrflowauthor", tableid) + "(id, authorctx, cname, ctype, runflowkey, runactkey, authorsource) \n");
		sql.append("values(");
		sql.append(SQLParser.charValueEnd(id));
		sql.append(SQLParser.charValueEnd(authorctx));
		sql.append(SQLParser.charValueEnd(authorcname));		
		sql.append(SQLParser.charValueEnd(ctype));
		sql.append(SQLParser.charValueEnd(runflowkey));
		sql.append(SQLParser.charValueEnd(runactkey));
		sql.append(SQLParser.charValue(authorsource));
		sql.append(") \n");
		
		dao().execute(Sqls.create(sql.toString()));
		return id;
	}
	
	public void remove(String runflowkey, String tableid) throws Exception
	{
		StringBuffer sql = new StringBuffer(); 

		sql.append(" delete from t_sys_wfrflowauthor a \n");
		sql.append(" where 1 = 1 \n");
		sql.append("   and a.runflowkey = " + SQLParser.charValueRT(runflowkey));
		
		dao().execute(Sqls.create(sql.toString()));
	}
	
	public void remove(String runflowkey, String runactkey, String tableid) throws Exception
	{
		StringBuffer sql = new StringBuffer(); 

		sql.append(" delete from t_sys_wfrflowauthor a \n");
		sql.append(" where 1 = 1 \n");
		sql.append("   and a.runflowkey = " + SQLParser.charValueRT(runflowkey));
		sql.append("   and a.runactkey = " + SQLParser.charValueRT(runactkey));
		
		dao().execute(Sqls.create(sql.toString()));
	}
	
	public void remove(String runflowkey, String runactkey, String user, String ctype, String tableid) throws Exception
	{
		StringBuffer sql = new StringBuffer(); 

		sql.append("delete from t_sys_wfrflowauthor a \n");
		sql.append(" where 1 = 1 \n");
		sql.append("   and a.runflowkey = " + SQLParser.charValueRT(runflowkey));
		sql.append("   and a.runactkey = " + SQLParser.charValueRT(runactkey));
		sql.append("   and a.authorctx = " + SQLParser.charValueRT(user));
		sql.append("   and a.ctype = " + SQLParser.charValueRT(ctype));
		
		dao().execute(Sqls.create(sql.toString()));
	}
	
	
	
}
