package com.skynet.app.workflow.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.app.workflow.pojo.LEventRouteReceiver;
import com.skynet.framework.service.SkynetNameEntityService;

@InjectName("leventroutereceiverService")
@IocBean(args = { "refer:dao" })
public class LEventRouteReceiverService extends SkynetNameEntityService<LEventRouteReceiver> {
	public LEventRouteReceiverService() {
		super();
	}

	public LEventRouteReceiverService(Dao dao) {
		super(dao);
	}

	public LEventRouteReceiverService(Dao dao, Class<LEventRouteReceiver> entityType) {
		super(dao, entityType);
	}
	
	public String create(String eventid, String receiver, String receivercname, String receiverctype) throws Exception
	{
		String id = UUIDGenerator.getInstance().getNextValue(); //String.valueOf(System.nanoTime()); ;
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into t_sys_wfleventroute_receiver (id, receiver, receivercname, receiverctype, eventid) values (" + SQLParser.charValue(id) + "," + SQLParser.charValue(receiver) + "," + SQLParser.charValue(receivercname) + "," + SQLParser.charValue(receiverctype) + "," + SQLParser.charValue(eventid) + ")");
		
		
		return id;		
	}	
}
