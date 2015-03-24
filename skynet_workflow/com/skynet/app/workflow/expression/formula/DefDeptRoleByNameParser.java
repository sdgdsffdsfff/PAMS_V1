package com.skynet.app.workflow.expression.formula;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.app.workflow.spec.GlobalConstants;
import com.skynet.framework.dao.SkynetDao;

@InjectName("DefDeptRoleByNameParser")
@IocBean
public class DefDeptRoleByNameParser
{
	@Inject
	Dao dao;
	
	DynamicObject swapFlow = new DynamicObject();
	
	public DynamicObject getSwapFlow()
	{
		return swapFlow;
	}
	public void setSwapFlow(DynamicObject swapFlow)
	{
		this.swapFlow = swapFlow;
	}
	public List parser(String formula_str) throws Exception
	{
		List list_persons = new ArrayList();		

		try
		{
			String deptid = swapFlow.getAttr(GlobalConstants.swap_coperatordeptid);
			
			int lp = formula_str.indexOf("(");
			int rp = formula_str.indexOf(")");
		
			String[] contexts = StringToolKit.split(formula_str.substring(lp+1, rp), "#");
			
			if(contexts.length==1)
			{
				String rolename = contexts[0];
			
				String sql = "select * from t_sys_wfrole a where a.name = " + SQLParser.charValueRT(rolename);
				
				List roles = sdao().queryForList(sql);
				
//				for(int i=0;i<roles.size();i++)
//				{
//					String roleid = ((DynamicObject)roles.get(i)).getFormatAttr("roleid");
//					
//					OrgService orgService = new OrgService();
//					orgService.setJdbcDao(getJdbcDao());
//
//					Iterator iter_temp = orgService.getDeptRoleUsers(deptid, roleid).iterator();
//					while (iter_temp.hasNext())
//					{
//						Map obj = (Map) iter_temp.next();
//						list_persons.add(obj);
//					}
//				}
			}
			else
			if(contexts.length==2)
			{
				String rolename = contexts[0];
				int level = Integer.parseInt(contexts[1]);				
			
				String sql = "select * from t_sys_wfrole a where a.name = " + SQLParser.charValueRT(rolename);
				
				List roles = sdao().queryForList(sql);
				
				for(int j=0;j<roles.size();j++)
				{
					String roleid = ((DynamicObject)roles.get(j)).getFormatAttr("roleid");
					
//					OrgService orgService = new OrgService();
//					orgService.setJdbcDao(getJdbcDao());
//					
//					List deptList = orgService.getManyLevelDepts(deptid, level);
//			
//					for(int i=0;i<deptList.size();i++)
//					{
//						Map deptObj = (Map)deptList.get(i);
//						
//						System.out.println("name：" + deptObj.get("name"));
//				
//						Iterator iter_temp = orgService.getDeptRoleUsers((String)deptObj.get("deptid"), roleid).iterator();
//		
//						while (iter_temp.hasNext())
//						{
//							Map obj = (Map) iter_temp.next();
//							list_persons.add(obj);
//						}
//					}
				}
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			
		}
		
		return list_persons;
	}
	
	public DynamicObject transPerson(Map person)
	{
		
		DynamicObject personObj = new DynamicObject();
		personObj.setAttr("ownerctx", (String)person.get("id"));
		personObj.setAttr("cname", (String)person.get("name"));
		personObj.setAttr("ctype", "PERSON");
		personObj.setAttr("ordernum", (String)person.get("ordernum"));				
		return personObj;
	}	
	
	
	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	public SkynetDao sdao() {
		return (SkynetDao)dao;
	}
	
	
}
