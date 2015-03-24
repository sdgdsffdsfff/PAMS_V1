package com.ray.dev.vinit.make;

import com.headray.framework.common.generator.FormatKey;
import com.headray.framework.services.function.StringToolKit;
import com.ray.dev.vinit.query.vo.SearchVO;

public class LocateMake
{
	public static void clear(SearchVO vo)
	{
		String searchid = vo.searchid + "02";
		BrowseMake.clear(searchid);
	}
	
	public static void search(SearchVO vo)
	{
		String searchid = vo.searchid + "02";
		
		StringBuffer sql = new StringBuffer();

        sql = new StringBuffer();
		sql.append(" insert into t_sys_search (searchid, searchname, title, entityname, macro, pagesize, divbutton, divnav)");
		sql.append(" values('" + searchid + "', '" + vo.entity + ".locate', '', '" + vo.clazz + "', '" + vo.macro + "locate.ftl', 20, 'Y', 'Y')");
		sql.append(";");
		System.out.println(sql.toString());
	}
	
	public static void searchoption(SearchVO vo)
	{
		String searchid = vo.searchid + "02";

		StringBuffer sql = new StringBuffer();
		
		for(int i=0;i<vo.names.length;i++)
		{
			sql = new StringBuffer();
			sql.append(" insert into t_sys_searchoption (searchoptionid, searchid, field, htmlfield, fieldtype, pkey, title, visible, edittype)");
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
			
			sql.append(" values('" + (searchid + FormatKey.format(i+1, 2)) + "', '" + searchid + "', '" + vo.names[i] + "', '" + vo.names[i] + "', '" + fieldtype + "', " + pkey + ", '" + vo.cnames[i] + "', 1, 'text' )");
			sql.append(";");
			System.out.println(sql.toString());
		}
		
		if(vo.parentkey!=null)
		{
			sql = new StringBuffer();
			sql.append(" insert into t_sys_searchoption (searchoptionid, searchid, field, htmlfield, fieldtype, pkey, title, visible, edittype)");
			sql.append(" values('" + (searchid + FormatKey.format(vo.names.length+1, 2)) + "', '" + searchid + "', '" + vo.parentkey + "', '" + vo.parenthtmlkey + "', '0', 0, '" + vo.parenthtmlkey + "', 1, 'text')");
			sql.append(";");
			System.out.println(sql.toString());
		}		

	}
	
	public static void searchitem(SearchVO vo)
	{
		String searchid = vo.searchid + "02";
		
		StringBuffer sql = new StringBuffer();

		for(int i=0;i<vo.pkeys.length;i++)
		{
			sql = new StringBuffer();
			sql.append(" insert into t_sys_searchitem (searchitemid, searchid, caption, field, htmlfield, qfield)");
			sql.append(" values('" + (searchid + FormatKey.format(i+1, 2)) + "', '" + searchid + "', '" + vo.cnames[StringToolKit.getTextInArrayIndex(vo.names, vo.pkeys[i])] + "', '" + vo.names[i] + "', '" + vo.names[i] + "','" + vo.names[i] + "')");
			sql.append(";");
			System.out.println(sql.toString());
		}
	}
	
	public static void searchurl(SearchVO vo)
	{
		String searchid = vo.searchid + "02";

		StringBuffer sql = new StringBuffer();
		sql.append(" insert into t_sys_searchurl (searchurlid, searchid, title, pname, visible)");
		sql.append(" values('" + (searchid + FormatKey.format(1, 2)) + "', '" + searchid + "', '保存', 'save', 1)");
		sql.append(";");
		System.out.println(sql.toString());
		
		sql = new StringBuffer();
		sql.append(" insert into t_sys_searchurl (searchurlid, searchid, title, pname, visible)");
		sql.append(" values('" + (searchid + FormatKey.format(2, 2)) + "', '" + searchid + "', '关闭', 'close', 1)");
		sql.append(";");
		System.out.println(sql.toString());
	}
}
