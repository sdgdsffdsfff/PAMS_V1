package com.skynet.framework.service;

import java.lang.annotation.Annotation;
import java.util.List;

import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Entity;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.service.NameEntityService;

import com.skynet.framework.dao.SkynetDao;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;

public abstract class SkynetNameEntityService<T> extends NameEntityService<T> {
	public SkynetNameEntityService() {
		super();
	}

	public SkynetNameEntityService(Dao dao) {
		super(dao);
	}

	public SkynetNameEntityService(Dao dao, Class<T> entityType) {
		super(dao, entityType);
	}

	public SkynetDao sdao() {
		return (SkynetDao) dao();
	}

	// SQL语句查询，用于关联查询，返回DynamicObject对象；
	public List<DynamicObject> queryForList(String sql) throws Exception {
		return sdao().queryForList(sql);
	}

	// SQL语句查询，用于关联查询，返回DynamicObject对象；
	public DynamicObject queryForMap(String sql) throws Exception {
		return sdao().queryForMap(sql);
	}

	// 属性查询，用于单体查询，返回DynamicObject列表对象；
	public List<DynamicObject> findBy(String propertyName, String value) throws Exception {
		List datas = sdao().findBy(getEntityClass()
				.getAnnotation(Table.class).value(), propertyName, value);
		return datas;
	}
	
	// 条件查询，用于单体查询，返回DynamicObject列表对象；
	public List<DynamicObject> findByCond(Condition cnd) throws Exception {
		List datas = sdao().findBy(getEntityClass()
				.getAnnotation(Table.class).value(), cnd);
		return datas;
	}

	// 标识查询，用于单体查询，返回DynamicObject对象；
	public DynamicObject locate(String id) throws Exception {
		DynamicObject obj = new DynamicObject();
		obj = sdao().locateBy(getEntityClass().getAnnotation(Table.class).value(), getEntity().getNameField().getName(), id);
		return obj;
	}
	
	// 条件查询，用于单体查询，返回DynamicObject对象；
	public DynamicObject locateBy(Condition cnd) throws Exception {
//		DynamicObject obj = new DynamicObject();
//		obj = DynamicObject.transBean(sdao().fetch(getEntityClass(), cnd));
		DynamicObject obj = new DynamicObject();
		obj = sdao().locateBy(getEntityClass().getAnnotation(Table.class).value(), cnd);
		return obj;
	}

	public T get(String id) throws Exception {
		return dao().fetch(getEntityClass(), id);
	}
	
	// 标识更新，用于单体更新；
	public void updateEntity(String id, String pname, String value) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" update ").append(getEntityClass()
				.getAnnotation(Table.class).value());
		sql.append(" set ").append(pname).append(" = ").append(SQLParser.charValue(value));
		sdao().execute(Sqls.create(sql.toString()));
	}

}
