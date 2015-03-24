package com.skynet.app.workflow.service;

import java.util.List;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.app.workflow.pojo.RFlowReader;
import com.skynet.app.workflow.spec.DBFieldConstants;
import com.skynet.app.workflow.spec.SplitTableConstants;
import com.skynet.framework.service.SkynetNameEntityService;

@InjectName("rflowreaderService")
@IocBean(args = { "refer:dao" })
public class RFlowReaderService extends SkynetNameEntityService<RFlowReader>
{
	public RFlowReaderService() {
		super();
	}

	public RFlowReaderService(Dao dao) {
		super(dao);
	}

	public RFlowReaderService(Dao dao, Class<RFlowReader> entityType) {
		super(dao, entityType);
	}
	
	public void update_from_bact_reader(String flowdefid, String runflowkey, String tableid) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select a.readerctx, a.cname, a.ctype \n");
		sql.append("  from t_sys_wfbflowreader a \n");
		sql.append(" where 1 = 1 \n" );
		sql.append("   and a.flowid = ");
		sql.append(SQLParser.charValueRT(flowdefid));
		
		List readers = queryForList(sql.toString());
		
		DynamicObject reader = new DynamicObject();
		
		String runactkey = "";
		for(int i=0;i<readers.size();i++)
		{
			reader = (DynamicObject)readers.get(i);			
			String readerctx = reader.getFormatAttr("readerctx");
			String ctype = reader.getFormatAttr("ctype");
			String cname = reader.getFormatAttr("cname");
			create(readerctx, cname, ctype, runflowkey, runactkey, DBFieldConstants.RFLOWREADER_SOURCE_ACTDEF, tableid);
		}
	}
	
	public String create(String readerctx, String readercname, String ctype, String runflowkey, String runactkey, String readersource, String tableid) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from " + SplitTableConstants.getSplitTable("T_SYS_WFrflowreader", tableid) + " a \n");
		sql.append(" where 1 = 1 \n");
		sql.append("   and a.runflowkey = " + SQLParser.charValueRT(runflowkey));
		sql.append("   and a.runactkey = " + SQLParser.charValueRT(runactkey));
		sql.append("   and a.readerctx = " + SQLParser.charValueRT(readerctx));
		sql.append("   and a.readerctx = " + SQLParser.charValueRT(readerctx));		
		sql.append("   and a.ctype = " + SQLParser.charValueRT(ctype));
		
		DynamicObject obj = queryForMap(sql.toString());
		
		if(!obj.getFormatAttr("id").equals(""))
		{
			return obj.getFormatAttr("id"); 
		}

		String id = UUIDGenerator.getInstance().getNextValue(); //String.valueOf(System.nanoTime()); ;
		
		sql = new StringBuffer();
		sql.append("insert into " + SplitTableConstants.getSplitTable("T_SYS_WFrflowreader", tableid) + "(id, readerctx, cname, ctype, runflowkey, runactkey, readersource) \n");
		sql.append("values(");
		sql.append(SQLParser.charValueEnd(id));
		sql.append(SQLParser.charValueEnd(readerctx));
		sql.append(SQLParser.charValueEnd(readercname));		
		sql.append(SQLParser.charValueEnd(ctype));
		sql.append(SQLParser.charValueEnd(runflowkey));
		sql.append(SQLParser.charValueEnd(runactkey));
		sql.append(SQLParser.charValue(readersource));
		sql.append(") \n");
		
		dao().execute(Sqls.create(sql.toString()));
		
		return id;
	}
}
