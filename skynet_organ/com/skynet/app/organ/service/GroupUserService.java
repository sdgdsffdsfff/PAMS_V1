package com.skynet.app.organ.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.app.organ.pojo.GroupUser;
import com.skynet.framework.service.SkynetNameEntityService;

@InjectName("groupuserService")
@IocBean(args = { "refer:dao" }) 
public class GroupUserService extends SkynetNameEntityService<GroupUser>
{
	public GroupUserService()
	{
		super();
	}
	
	public GroupUserService(Dao dao)
	{
		super(dao);
	}	
	
	public GroupUserService(Dao dao, Class<GroupUser> entityType)
	{
		super(dao, entityType);
	}
}
