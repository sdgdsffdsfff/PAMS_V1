package com.skynet.app.workflow.service;

import java.util.List;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.app.workflow.pojo.RActTask;
import com.skynet.app.workflow.spec.DBFieldConstants;
import com.skynet.app.workflow.spec.SplitTableConstants;
import com.skynet.framework.service.SkynetNameEntityService;

@InjectName("racttaskService")
@IocBean(args = { "refer:dao" })
public class RActTaskService extends SkynetNameEntityService<RActTask>
{
	public RActTaskService() {
		super();
	}

	public RActTaskService(Dao dao) {
		super(dao);
	}

	public RActTaskService(Dao dao, Class<RActTask> entityType) {
		super(dao, entityType);
	}
	
	public void update_from_bact_task(String actdefid, String runactkey, String tableid) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select a.id, a.actid, a.descript, a.sno, a.require, a.ctype \n");
		sql.append("  from t_sys_wfbacttask a \n");
		sql.append(" where 1 = 1 \n");
		sql.append("   and a.actid = " + SQLParser.charValueRT(actdefid));
	
		List<DynamicObject> tasks = queryForList(sql.toString());
		
		for(int i=0;i<tasks.size();i++)
		{
			DynamicObject task = tasks.get(i);
			
			String sno = task.getAttr("sno");
			String complete = DBFieldConstants.RACTTASK_COMPLETE_NO;
			String acttaskdefid = task.getAttr("id");
			String exectime = null;
			create(runactkey, sno, complete, acttaskdefid, exectime, tableid);
		}
	}
	
	public String create(String runactkey,String sno, String complete,String acttaskdefid,String exectime, String tableid) throws Exception
	{
		String runacttaskkey = UUIDGenerator.getInstance().getNextValue(); //String.valueOf(System.nanoTime()); ;
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into " + SplitTableConstants.getSplitTable("t_sys_wfracttask", tableid) + "(id,sno,runactkey,complete,acttaskdefid,exectime) \n");
		sql.append("values(");
		sql.append(SQLParser.charValueEnd(runacttaskkey));
		sql.append(SQLParser.charValueEnd(sno));
		sql.append(SQLParser.charValueEnd(runactkey));
		sql.append(SQLParser.charValueEnd(complete));
		sql.append(SQLParser.charValueEnd(acttaskdefid));
		sql.append(SQLParser.charValue(exectime));
		sql.append(") \n");

		dao().execute(Sqls.create(sql.toString()));
		
		return runacttaskkey;
	}
}
