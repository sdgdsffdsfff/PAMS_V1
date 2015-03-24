package com.skynet.app.workflow.service;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.app.workflow.pojo.LEventAct;
import com.skynet.framework.service.SkynetNameEntityService;

@InjectName("leventactService")
@IocBean(args = { "refer:dao" })
public class LEventActService extends SkynetNameEntityService<LEventAct> 
{
	public LEventActService() {
		super();
	}

	public LEventActService(Dao dao) {
		super(dao);
	}

	public LEventActService(Dao dao, Class<LEventAct> entityType) {
		super(dao, entityType);
	}

	public String create(String id, String eventer, String eventercname, String eventdept, String eventdeptcname, String beginstate, String endstate, String actdefid, String runactkey, String flowdefid, String runflowkey) throws Exception
	{
		String eventtype = new String();
		StringBuffer sql = new StringBuffer();
		sql.append("insert into t_sys_wfleventact (id, eventer, eventercname, eventdept, eventdeptcname, beginstate, endstate, actdefid, runactkey, flowdefid, runflowkey, eventtype) \n");
		sql.append(" values (");
		sql.append(SQLParser.charValueEnd(id));
		sql.append(SQLParser.charValueEnd(eventer));
		sql.append(SQLParser.charValueEnd(eventercname));
		sql.append(SQLParser.charValueEnd(eventdept));
		sql.append(SQLParser.charValueEnd(eventdeptcname));
		sql.append(SQLParser.charValueEnd(beginstate));
		sql.append(SQLParser.charValueEnd(endstate));
		sql.append(SQLParser.charValueEnd(actdefid));
		sql.append(SQLParser.charValueEnd(runactkey));
		sql.append(SQLParser.charValueEnd(flowdefid));
		sql.append(SQLParser.charValueEnd(runflowkey));
		sql.append(SQLParser.charValue(eventtype));
		sql.append(")");
		
		dao().execute(Sqls.create(sql.toString()));
		
		return id;
	}	
	
	public DynamicObject findById(DynamicObject obj) throws Exception
	{
		String runflowkey = obj.getAttr("id");
		return locate(runflowkey);
	}
	//	
	public DynamicObject locate(String id) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select a.id, a.eventer, beginstate, a.endstate, a.actdefid, runactkey, a.flowdefid, a.runflowkey from t_sys_wfleventact a where 1 = 1 and a.id = " + SQLParser.charValueRT(id));

		DynamicObject dvo = queryForMap(sql.toString());
		return dvo;
	}
	// 新增流程日志事件
	public String create(DynamicObject obj) throws Exception
	{
		String id = obj.getAttr("id");
		String eventer = obj.getAttr("eventer");
		String eventercname = obj.getAttr("eventercname");
		String eventdept = obj.getAttr("eventerdept");
		String eventdeptcname = obj.getAttr("eventdeptcname");
		String beginstate = obj.getAttr("beginstate");
		String endstate = obj.getAttr("endstate");
		String actdefid = obj.getAttr("actdefid");
		String runactkey = obj.getAttr("runactkey");
		String flowdefid = obj.getAttr("flowdefid");
		String runflowkey = obj.getAttr("runflowkey");
		String eventtype = obj.getAttr("eventtype");
		
		return create(id, eventer, eventercname, eventdept, eventdeptcname, beginstate, endstate, actdefid, runactkey, flowdefid, runflowkey, eventtype);
	}
	

	
	// 新增流程日志事件
	public String create(String id, String eventer, String eventercname, String eventdept, String eventdeptcname, String beginstate, String endstate, String actdefid, String runactkey, String flowdefid, String runflowkey, String eventtype) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append("insert into t_sys_wfleventact (id, eventer, eventercname, eventdept, eventdeptcname, beginstate, endstate, actdefid, runactkey, flowdefid, runflowkey, eventtype) \n");
		sql.append(" values (");
		sql.append(SQLParser.charValueEnd(id));
		sql.append(SQLParser.charValueEnd(eventer));
		sql.append(SQLParser.charValueEnd(eventercname));
		sql.append(SQLParser.charValueEnd(eventdept));
		sql.append(SQLParser.charValueEnd(eventdeptcname));
		sql.append(SQLParser.charValueEnd(beginstate));
		sql.append(SQLParser.charValueEnd(endstate));
		sql.append(SQLParser.charValueEnd(actdefid));
		sql.append(SQLParser.charValueEnd(runactkey));
		sql.append(SQLParser.charValueEnd(flowdefid));
		sql.append(SQLParser.charValueEnd(runflowkey));
		sql.append(SQLParser.charValue(eventtype));
		sql.append(")");
		
		dao().execute(Sqls.create(sql.toString()));
		
		return id;
	}
	
}
