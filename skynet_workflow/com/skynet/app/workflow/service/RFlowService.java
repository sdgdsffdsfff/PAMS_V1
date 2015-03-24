package com.skynet.app.workflow.service;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.common.generator.TimeGenerator;
import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.function.Types;
import com.skynet.app.workflow.pojo.RFlow;
import com.skynet.framework.service.SkynetNameEntityService;

@InjectName("rflowService")
@IocBean(args = { "refer:dao" })
public class RFlowService extends SkynetNameEntityService<RFlow>
{
	public RFlowService() {
		super();
	}

	public RFlowService(Dao dao) {
		super(dao);
	}

	public RFlowService(Dao dao, Class<RFlow> entityType) {
		super(dao, entityType);
	}
	
	public void set_complete_time(String runflowkey, String tableid) throws Exception
	{
		StringBuffer sql = new StringBuffer();

		sql.append(" update t_sys_wfrflow ");
		sql.append(" set completetime = sysdate ");
		sql.append(" where 1 = 1 ");
		sql.append(" and runflowkey = " + SQLParser.charValue(runflowkey));
		
		dao().execute(Sqls.create(sql.toString()));
	}
	
	public String create(RFlow rflow) throws Exception
	{
		String runflowkey = UUIDGenerator.getInstance().getNextValue(); 
		rflow.setRunflowkey(runflowkey);
		sdao().insert(rflow);
		return rflow.getRunflowkey();
	}
	
//	public String create(String workname, String flowdefid, String state, String priority, String dataid, String formid, String tableid, String creater) throws Exception
//	{
//		String runflowkey = UUIDGenerator.getInstance().getNextValue(); //String.valueOf(System.nanoTime()); ;
//		
//		StringBuffer sql = new StringBuffer();
//		sql.append("insert into t_sys_wfrflow (createtime, ccreatetime, workname, runflowkey, flowdefid, state, priority, dataid, formid, tableid, creater) ").append("\n");
//		sql.append("values (sysdate,");
//		sql.append(SQLParser.charValueEnd(TimeGenerator.getTimeStr()));
//		sql.append(SQLParser.charValueEnd(workname));
//		sql.append(SQLParser.charValueEnd(runflowkey));
//		sql.append(SQLParser.charValueEnd(flowdefid));
//		sql.append(SQLParser.charValueEnd(state));
//		sql.append(SQLParser.charValueEnd(priority));
//		sql.append(SQLParser.charValueEnd(dataid));
//		sql.append(SQLParser.charValueEnd(formid));
//		sql.append(SQLParser.charValueEnd(tableid));
//		sql.append(SQLParser.charValue(creater));
//		sql.append(")\n");
//		
//		dao().execute(Sqls.create(sql.toString()));
//		
//		return runflowkey;	
//	}
//	
//	public String create(String workname, String flowdefid, String state, String priority, String dataid, String formid, String tableid, String creater, String suprunflowkey, String suprunactkey) throws Exception
//	{
//		String runflowkey = UUIDGenerator.getInstance().getNextValue(); //String.valueOf(System.nanoTime()); ;
//		
//		StringBuffer sql = new StringBuffer();
//		sql.append("insert into t_sys_wfrflow (createtime, ccreatetime, workname, runflowkey, flowdefid, state, priority, dataid, formid, tableid, creater, suprunflowkey, suprunactkey) ");
//		sql.append("values (sysdate,");
//		sql.append(SQLParser.charValueEnd(TimeGenerator.getTimeStr()));		
//		sql.append(SQLParser.charValueEnd(workname));
//		sql.append(SQLParser.charValueEnd(runflowkey));
//		sql.append(SQLParser.charValueEnd(flowdefid));
//		sql.append(SQLParser.charValueEnd(state));
//		sql.append(SQLParser.charValueEnd(priority));
//		sql.append(SQLParser.charValueEnd(dataid));
//		sql.append(SQLParser.charValueEnd(formid));
//		sql.append(SQLParser.charValueEnd(tableid));
//		sql.append(SQLParser.charValueEnd(creater));
//		sql.append(SQLParser.charValueEnd(suprunflowkey));
//		sql.append(SQLParser.charValue(suprunactkey));
//		sql.append(")");
//		dao().execute(Sqls.create(sql.toString()));
//		
//		return runflowkey;	
//	}
	
	// 新创建的检查流程是否已转发
	public boolean checkflowforwarded(String runflowkey) throws Exception
	{
		boolean sign = false;
		
		StringBuffer sql = new StringBuffer();
		
		// 查询流程是否转发过，转发过不允许删除
		// 查看是否有开始、第一步以外的活动节点，如果有，表明已经转发过，不允许删除
		sql = new StringBuffer();
		sql.append("select count(a.runactkey) num ").append("\n");
		sql.append("  from t_sys_wfract a, t_sys_wfbact b, t_sys_wfrflow c ").append("\n");
		sql.append(" where 1 = 1 ").append("\n");
		sql.append("   and a.actdefid = b.id ").append("\n");
		sql.append("   and b.isfirst <> 'Y' ").append("\n");
		sql.append("   and b.ctype <> 'BEGIN' ").append("\n");
		sql.append("   and a.runflowkey = c.runflowkey ").append("\n");
		sql.append("   and c.runflowkey = " + SQLParser.charValueRT(runflowkey));	

		int num = 0;
		num = Types.parseInt(sdao().queryForMap(sql.toString()).getFormatAttr("num"), 1); 
		if(num == 0)
		{
			sign = true;
		}
		
		return sign;
	}

}
