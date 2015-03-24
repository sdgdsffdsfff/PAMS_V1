package com.skynet.app.workflow.service;

import java.util.List;
import java.util.Map;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.app.workflow.pojo.BAct;
import com.skynet.app.workflow.spec.DBFieldConstants;
import com.skynet.framework.service.SkynetNameEntityService;

@InjectName("bactService")
@IocBean(args = { "refer:dao" })
public class BActService extends SkynetNameEntityService<BAct> {

	public BActService() {
		super();
	}

	public BActService(Dao dao) {
		super(dao);
	}

	public BActService(Dao dao, Class<BAct> entityType) {
		super(dao, entityType);
	}

//	public BAct get(String id) throws Exception {
//		return dao().fetch(BAct.class, id);
//	}

//	public List findBy(String propertyName, String value) throws Exception {
//		return findBy(propertyName, value);
//	}
	
	
	public String create(String runflowkey, String createtime, String flowdefid, String actdefid, String state, String priority, String dataid, String formid, String tableid, String handletype) throws Exception
	{
		String runactkey = UUIDGenerator.getInstance().getNextValue(); //String.valueOf(System.nanoTime()); ;

		StringBuffer sql = new StringBuffer();
		sql.append("insert into t_sys_wfract (runflowkey,runactkey,createtime,ccreatetime,flowdefid,actdefid,state,priority,dataid,formid,tableid,handletype) \n");
		sql.append(" values(");
		sql.append(SQLParser.charValueEnd(runflowkey));
		sql.append(SQLParser.charValueEnd(runactkey));
		sql.append(" sysdate, ");
		sql.append(SQLParser.charValueEnd(createtime));
		sql.append(SQLParser.charValueEnd(flowdefid));
		sql.append(SQLParser.charValueEnd(actdefid));
		sql.append(SQLParser.charValueEnd(state));
		sql.append(" null, ");
		sql.append(SQLParser.charValueEnd(dataid));
		sql.append(SQLParser.charValueEnd(formid));
		sql.append(SQLParser.charValueEnd(tableid));
		sql.append(SQLParser.charValue(handletype));
		sql.append(")");

		dao().execute(Sqls.create(sql.toString()));
		return runactkey;
	}

	public DynamicObject select_bact_begin(String flowdefid) throws Exception {
		String sql = " select a.id from t_sys_wfbact a \n" + "  where 1 = 1 \n"
				+ "    and a.flowid = " + SQLParser.charValueRT(flowdefid)
				+ "    and a.ctype = "
				+ SQLParser.charValueRT(DBFieldConstants.BACT_CTYPE_BEGIN);
		DynamicObject dvo = queryForMap(sql);
		return dvo;
	}

	public DynamicObject locate(String id) throws Exception {
		String sql = "select a.cname, a.id, a.ctype, a.flowid, a.formid, a.handletype, a.split, a.join from T_SYS_WFbact a where 1 = 1 and a.id = "
				+ SQLParser.charValue(id);
		DynamicObject dvo = queryForMap(sql);
		return dvo;
	}

	public DynamicObject select_bact_first(String flowdefid, String actdefid)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select b.endactid from t_sys_wfbact a, t_sys_wfbroute b \n");
		sql.append("  where 1 = 1 ");
		sql.append("    and a.flowid = b.flowid ");
		sql.append("    and a.id = b.startactid ");
		sql.append("    and a.flowid = " + SQLParser.charValueRT(flowdefid));
		sql.append("    and a.id = " + SQLParser.charValueRT(actdefid));
		sql.append("    and a.ctype = "
				+ SQLParser.charValueRT(DBFieldConstants.BACT_CTYPE_BEGIN));

		DynamicObject dvo = queryForMap(sql.toString());
		return dvo;
	}

	public DynamicObject select_bact_first(String flowdefid) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.* from t_sys_wfbact a \n");
		sql.append("  where 1 = 1 ");
		sql.append("    and a.flowid = " + SQLParser.charValueRT(flowdefid));
		sql.append("    and a.isfirst = 'Y'");

		DynamicObject dvo = queryForMap(sql.toString());
		return dvo;
	}

	public List browseTask(String classid) throws Exception {
		String sql = "select a.taskid,a.taskname,a.shm,a.applicationid,b.cname classname from t_sys_wftask a left join t_sys_wfbflowclass b on a.applicationid=b.appid   where   a.applicationid in(select  distinct appid  from  t_sys_wfbflowclass  where id="
				+ SQLParser.charValue(classid).trim() + " )";
		return queryForList(sql);
	}

	public String get_subflow_sql(Map map) throws Exception {
		String classname = (String) map.get("classname");
		String state = (String) map.get("state");
		String sno = (String) map.get("sno");
		String version = (String) map.get("verson");

		StringBuffer sql = new StringBuffer();
		sql.append(
				" select  t.cname cname, t.sno sno,t.verson verson,t.state state,c.cname classname,t.id id")
				.append("\n");
		sql.append(" from t_sys_wfbflow t ").append("\n");
		sql.append(" left join t_sys_wfbflowclass c ").append("\n");
		sql.append(" on t.classid = c.id ").append("\n");
		sql.append(" where 1 = 1 ").append("\n");
		sql.append(" and t.state = '激活' ").append("\n");
		if (!StringToolKit.isBlank(classname)) {
			sql.append(
					" and Lower(c.cname) like Lower("
							+ SQLParser.charLikeValue(classname) + ")").append(
					"\n");
		}
		if (!StringToolKit.isBlank(state)) {
			sql.append(
					" and Lower(t.state) like Lower("
							+ SQLParser.charLikeValue(state) + ")")
					.append("\n");
		}
		if (!StringToolKit.isBlank(sno)) {
			sql.append(
					" and Lower(t.sno) like Lower("
							+ SQLParser.charLikeValue(sno) + ")").append("\n");
		}
		if (!StringToolKit.isBlank(version)) {
			sql.append(
					" and Lower(t.verson) like Lower("
							+ SQLParser.charLikeValue(version) + ")").append(
					"\n");
		}

		return sql.toString();
	}
}
