package com.ray.dev.vinit.make;

import com.headray.framework.common.generator.FormatKey;
import com.headray.framework.services.function.StringToolKit;
import com.ray.dev.vinit.query.vo.SearchVO;

public class BrowseMake
{
	public static void clear(SearchVO vo)
	{
		String searchid = vo.searchid + "01";
		clear(searchid);
	}
	
	public static void clear(String searchid)
	{
		StringBuffer sql = new StringBuffer();
	
		sql = new StringBuffer();
		sql.append(" delete from t_sys_searchlink where 1 = 1 and searchid = '" + searchid + "'");
		sql.append(";");
		System.out.println(sql.toString());
		
		sql = new StringBuffer();
		sql.append(" delete from t_sys_searchurl where 1 = 1 and searchid = '" + searchid + "'");
		sql.append(";");
		System.out.println(sql.toString());

		sql = new StringBuffer();
		sql.append(" delete from t_sys_searchoption where 1 = 1 and searchid = '" + searchid + "'");
		sql.append(";");
		System.out.println(sql.toString());
		
		sql = new StringBuffer();
		sql.append(" delete from t_sys_searchitem where 1 = 1 and searchid = '" + searchid + "'");
		sql.append(";");
		System.out.println(sql.toString());
		
		sql = new StringBuffer();
		sql.append(" delete from t_sys_search where 1 = 1 and searchid = '" + searchid + "'");
		sql.append(";");
		System.out.println(sql.toString());
	}

	
	public static void search(SearchVO vo)
	{
		String searchid = vo.searchid + "01";

		StringBuffer sql = new StringBuffer();
		sql = new StringBuffer();
		sql.append(" insert into t_sys_search (searchid, searchname, title, entityname, macro, pagesize, divbutton, divnav)");
		sql.append(" values('" + searchid + "', '" + vo.entity + ".browse', '', '" + vo.clazz + "', '" + vo.macro + "browse.ftl', 50, 'Y', 'Y')");
		sql.append(";");
		System.out.println(sql.toString());
	}
	
	public static void searchoption(SearchVO vo)
	{
		String searchid = vo.searchid + "01";

		StringBuffer sql = new StringBuffer();
		
		int visible = 1;
		
		for(int i=0;i<vo.names.length;i++)
		{
			sql = new StringBuffer();
			sql.append(" insert into t_sys_searchoption (searchoptionid, searchid, field, fieldtype, pkey, title, visible, edittype)");
			
			String fieldtype = "char";
			if("1".equals(vo.types[i]))
			{
				fieldtype = "integer";
			}

			int pkey = 0;
			if( StringToolKit.getTextInArrayIndex(vo.pkeys, vo.names[i]) >= 0)
			{
				pkey = 1;
			}
			
			if (i > 5)
			{
				visible = 0; 
			}
			
			sql.append(" values('" + (searchid + FormatKey.format(i+1, 2)) + "', '" + searchid + "', '" + vo.names[i] + "', '" + fieldtype + "', " + pkey + ", '" + vo.cnames[i] + "', " + visible + ", 'label')");
			sql.append(";");
			System.out.println(sql.toString());
		}

	}
	
	public static void searchitem(SearchVO vo)
	{
		String searchid = vo.searchid + "01";
		
		StringBuffer sql = new StringBuffer();

		for(int i=0;i<vo.pkeys.length;i++)
		{
			sql = new StringBuffer();
			sql.append(" insert into t_sys_searchitem (searchitemid, searchid, caption, field, htmlfield, qfield)");
			sql.append(" values('" + (searchid + FormatKey.format(i+1, 2)) + "', '" + searchid + "', '" + vo.cnames[i] + "', '" + vo.names[i] + "', '" + vo.names[i] + "','" + vo.names[i] + "')");
			sql.append(";");
			System.out.println(sql.toString());
		}
		
		if(vo.parentkey!=null)
		{
			sql = new StringBuffer();
			sql.append(" insert into t_sys_searchitem (searchitemid, searchid, caption, field, htmlfield, qfield)");
			sql.append(" values('" + (searchid + FormatKey.format(vo.pkeys.length+1, 2)) + "', '" + searchid + "', '" + vo.parentkey + "', '" + vo.parentkey + "', '" + vo.parenthtmlkey + "','" + vo.parentkey + "')");
			sql.append(";");
			System.out.println(sql.toString());
		}
	}
	
	public static void searchurl(SearchVO vo)
	{
		String searchid = vo.searchid + "01";

		StringBuffer sql = new StringBuffer();
		sql.append(" insert into t_sys_searchurl (searchurlid, searchid, title, pname, visible)");
		sql.append(" values('" + (searchid + FormatKey.format(1, 2)) + "', '" + searchid + "', '新增', 'input', 1)");
		sql.append(";");
		System.out.println(sql.toString());
		
		sql = new StringBuffer();
		sql.append(" insert into t_sys_searchurl (searchurlid, searchid, title, pname, visible)");
		sql.append(" values('" + (searchid + FormatKey.format(2, 2)) + "', '" + searchid + "', '删除', 'del', 1)");
		sql.append(";");
		System.out.println(sql.toString());
		
		sql = new StringBuffer();
		sql.append(" insert into t_sys_searchurl (searchurlid, searchid, title, pname, visible)");
		sql.append(" values('" + (searchid + FormatKey.format(3, 2)) + "', '" + searchid + "', '查询', 'query', 1)");
		sql.append(";");
		System.out.println(sql.toString());		
	}
}
