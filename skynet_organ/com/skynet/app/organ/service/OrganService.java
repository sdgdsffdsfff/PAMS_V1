package com.skynet.app.organ.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.app.organ.pojo.Organ;
import com.skynet.framework.service.SkynetNameEntityService;

@InjectName("organService")
@IocBean(args = { "refer:dao" }) 
public class OrganService extends SkynetNameEntityService<Organ>
{
	public OrganService()
	{
		super();
	}
	
	public OrganService(Dao dao)
	{
		super(dao);
	}	
	
	public OrganService(Dao dao, Class<Organ> entityType)
	{
		super(dao, entityType);
	}
}
