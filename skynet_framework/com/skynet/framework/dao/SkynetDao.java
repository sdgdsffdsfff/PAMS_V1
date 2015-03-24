package com.skynet.framework.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.sql.DataSource;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.SqlManager;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.sql.PojoCallback;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.skynet.framework.services.db.dybeans.DyDBToolKit;
import com.skynet.framework.services.db.dybeans.DynamicObject;

public class SkynetDao extends NutDao {

	private static final Log log = Logs.get();

	private static final AtomicLong atomLong = new AtomicLong();

	private PojoCallback _pojo_queryEntity;

	private PojoCallback _pojo_fetchEntity;

	private PojoCallback _pojo_eachEntity;

	private PojoCallback _pojo_queryRecord;

	private PojoCallback _pojo_fetchRecord;

	private PojoCallback _pojo_eachRecord;

	private PojoCallback _pojo_fetchInt;

	private PojoCallback _pojo_fetchObject;

	public SkynetDao() {
		super();
	}

	public SkynetDao(DataSource dataSource) {
		super(dataSource);
	}

	public SkynetDao(DataSource dataSource, SqlManager sqlManager) {
		super(dataSource, sqlManager);
	}

	// SQL语句查询，用于关联查询，返回DynamicObject列表对象；
	public List<DynamicObject> queryForList(String sql) throws Exception {

		Sql s = Sqls.create(sql);
		s.setCallback(new SqlCallback() {
			public Object invoke(Connection conn, ResultSet rs, Sql sql)
					throws SQLException {
				List list = DyDBToolKit.packObjData(rs);
				return list;
			}
		});
		execute(s);
		return s.getList(DynamicObject.class);
	}

	// SQL语句查询，用于关联查询，返回DynamicObject对象；
	public DynamicObject queryForMap(String sql) throws Exception {

		Sql s = Sqls.create(sql);
		s.setCallback(new SqlCallback() {
			public Object invoke(Connection conn, ResultSet rs, Sql sql)
					throws SQLException {
				DynamicObject obj = new DynamicObject();
				List list = DyDBToolKit.packEmptyObjData(rs);
				if (list.size() > 0) {
					obj = (DynamicObject) list.get(0);
				}

				return obj;
			}
		});
		execute(s);
		return s.getObject(DynamicObject.class);
	}

	// 属性查询，用于单体查询，返回DynamicObject列表对象；
	public List<DynamicObject> findBy(String table, String propertyName,
			String value) throws Exception {

		List datas = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ").append(table);
		sql.append(" where 1 = 1 ");
		sql.append(" and ").append(propertyName).append("=").append(value);

		datas = queryForList(sql.toString());

		return datas;
	}

	// 条件查询，用于单体查询，返回DynamicObject列表对象；
	public List<DynamicObject> findBy(String table, Condition cnd)
			throws Exception {

		List datas = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ").append(table);
		sql.append(cnd.toString());

		datas = queryForList(sql.toString());

		return datas;
	}

	// 条件查询，用于单体查询，返回DynamicObject列表对象；
	public DynamicObject locateBy(String table, String propertyName,
			String value) throws Exception {

		DynamicObject obj = new DynamicObject();
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ").append(table);
		sql.append(Cnd.where(propertyName, "=", value).toString());

		obj = queryForMap(sql.toString());

		return obj;
	}

	// 条件查询，用于单体查询，返回DynamicObject对象；
	public DynamicObject locateBy(String table, Condition cnd) throws Exception {

		DynamicObject obj = new DynamicObject();
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ").append(table);
		sql.append(cnd.toString());

		obj = queryForMap(sql.toString());

		return obj;
	}

	public static void main(String[] args) throws Exception {
		Condition cnd = Cnd.where("actdefid", "=", "test").and("runactkey",
				"=", "0000");
		System.out.println(cnd.toString());
	}
}
