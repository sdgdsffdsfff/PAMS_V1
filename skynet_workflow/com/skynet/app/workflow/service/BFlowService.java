package com.skynet.app.workflow.service;

import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.app.workflow.pojo.BFlow;
import com.skynet.framework.service.SkynetNameEntityService;

@InjectName("bflowService")
@IocBean(args = { "refer:dao" }) 
public class BFlowService extends SkynetNameEntityService<BFlow>
{
	public BFlowService() {
		super();
	}

	public BFlowService(Dao dao) {
		super(dao);
	}

	public BFlowService(Dao dao, Class<BFlow> entityType) {
		super(dao, entityType);
	}

	public void save(BFlow entity) throws Exception
	{
		dao().update(entity);
	}

	public BFlow findUniqueBySno(String sno) throws Exception
	{
		BFlow bflow = dao().fetch(BFlow.class, Cnd.where("sno", "=", sno).and("state", "=", "激活"));
		return bflow;
	}

	public List<BFlow> getAll() throws Exception
	{
		return dao().query(BFlow.class, Cnd.wrap(" 1 = 1 "));
	}

	// 浏览所有流程
	public String get_browse_sql(Map map) throws Exception
	{
		String classname = (String) map.get("classname");
		String state = (String) map.get("state");
		String sno = (String) map.get("sno");
		String version = (String) map.get("verson");

		StringBuffer sql = new StringBuffer();
		sql.append("  select  t.cname cname, t.sno sno,t.verson verson,t.state state,c.cname classname,t.id id").append("\n");
		sql.append(" from t_sys_wfbflow t,t_sys_wfbflowclass c where 1=1 and t.classid=c.id ");
		if (!StringToolKit.isBlank(classname))
		{
			sql.append(" and Lower(c.classid) like Lower(" + SQLParser.charLikeValue(classname) + ")").append("\n");
		}
		if (!StringToolKit.isBlank(state))
		{
			sql.append(" and Lower(t.state) like Lower(" + SQLParser.charLikeValue(state) + ")").append("\n");
		}
		if (!StringToolKit.isBlank(sno))
		{
			sql.append(" and Lower(t.id) like Lower(" + SQLParser.charLikeValue(sno) + ")").append("\n");
		}
		if (!StringToolKit.isBlank(version))
		{
			sql.append(" and Lower(t.verson) like Lower(" + SQLParser.charLikeValue(version) + ")").append("\n");
		}
		sql.append(" order by sno, cname, verson ").append("\n");

		return sql.toString();
	}

}
