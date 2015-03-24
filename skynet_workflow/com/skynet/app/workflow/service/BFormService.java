package com.skynet.app.workflow.service;

import java.util.List;
import java.util.Map;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.app.workflow.pojo.BForm;
import com.skynet.framework.service.SkynetNameEntityService;

@InjectName("bformService")
@IocBean(args = { "refer:dao" })
public class BFormService extends SkynetNameEntityService<BForm> 
{
	public BFormService() {
		super();
	}

	public BFormService(Dao dao) {
		super(dao);
	}

	public BFormService(Dao dao, Class<BForm> entityType) {
		super(dao, entityType);
	}
	
	public void save(BForm entity) throws Exception
	{
		dao().update(entity);
	}

	public void delete(String[] ids) throws Exception
	{
		for (int i = 0; i < ids.length; i++)
		{
			dao().delete(ids[i]);
		}
	}

	//
	public DynamicObject select_fk_bflow(String flowdefid) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select a.id, a.cname, a.url, a.descript ");
		sql.append("  from t_sys_wfbflow b ");
		sql.append("  left join t_sys_wfbform a on a.id = b.formid where b.id = ");
		sql.append(SQLParser.charValueRT(flowdefid));

		DynamicObject dvo = queryForMap(sql.toString());
		return dvo;
	}

	public List browseBForm(String classid) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select a.id,a.cname,a.url,a.descript,b.cname classname  ");
		sql.append("  from t_sys_wfbform a ");
		sql.append("  left join t_sys_wfbflowclass b ");
		sql.append("    on a.classid=b.id ");
		sql.append("  where a.classid = " + SQLParser.charValue(classid));

		List datas = queryForList(sql.toString());
		return datas;
	}

	public String get_browse_sql(Map map)
	{
		String cname = (String) map.get("cname");
		String classname = (String) map.get("classname");
		StringBuffer sql = new StringBuffer();
		sql.append("  select f.id id,f.cname cname,f.url url,c.cname classname  ").append("\n");
		sql.append(" from t_sys_wfbform f left join t_sys_wfbflowclass c on f.classid=c.id ");
		sql.append(" where 1=1 ").append("\n");
		if (!StringToolKit.isBlank(cname))
		{
			sql.append(" and Lower(f.cname) like Lower(" + SQLParser.charLikeValue(cname) + ")").append("\n");
		}
		if (!StringToolKit.isBlank(classname))
		{
			sql.append(" and Lower(c.cname) like Lower(" + SQLParser.charLikeValue(classname) + ")").append("\n");
		}
		sql.append("order by f.cname");
		return sql.toString();
	}
}
