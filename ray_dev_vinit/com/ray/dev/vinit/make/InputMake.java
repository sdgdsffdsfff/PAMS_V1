package com.ray.dev.vinit.make;

import com.headray.framework.common.generator.FormatKey;
import com.ray.dev.vinit.query.vo.SearchVO;

public class InputMake
{
	public static void clear(SearchVO vo)
	{
		String searchid = vo.searchid + "03";
		BrowseMake.clear(searchid);
	}
	
	public static void search(SearchVO vo)
	{
		String searchid = vo.searchid + "03";
		
		StringBuffer sql = new StringBuffer();

		sql = new StringBuffer();
		sql.append(" insert into t_sys_search (searchid, searchname, entityname, macro, mysql, formname, pagesize, divbutton, divnav)");
		sql.append(" values('" + searchid + "', '" + vo.entity + ".input', '" + vo.entity + "', '" + vo.macro + "input.ftl'," + "'', 'form', 20, 'Y', 'Y')");
		sql.append(";");
		System.out.println(sql.toString());
	}
	
	public static void searchoption(SearchVO vo)
	{
		String searchid = vo.searchid + "03";

		StringBuffer sql = new StringBuffer();
		
		for(int i=0;i<vo.names.length;i++)
		{
			int sign = 0;
			for(int j=0;j<vo.pkeys.length;j++)
			{
				if(vo.names[i].equals(vo.pkeys[j]))
				{
					sign = 1;
					break;
				}
			}
			
			if(sign == 1)
			{
				continue;
			}
			
			sql = new StringBuffer();
			sql.append(" insert into t_sys_searchoption (searchoptionid, searchid, field, htmlfield, fieldtype, pkey, title, visible, edittype)");
			String fieldtype = "char";
			if("1".equals(vo.types[i]))
			{
				fieldtype = "integer";
			}
			sql.append(" values('" + (searchid + FormatKey.format(i+1, 2)) + "', '" + searchid + "', '" + vo.names[i] + "','" + vo.names[i] + "', '" + fieldtype + "', 0, '" + vo.cnames[i] + "', 1, 'text')");
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
		String searchid = vo.searchid + "03";
		
		StringBuffer sql = new StringBuffer();

		for(int i=0;i<vo.pkeys.length;i++)
		{
			sql = new StringBuffer();
			sql.append(" insert into t_sys_searchitem (searchitemid, searchid, caption, field, htmlfield, qfield)");
			sql.append(" values('" + (searchid + FormatKey.format(i+1, 2)) + "', '" + searchid + "', '" + vo.cnames[i] + "', '" + vo.names[i] + "', '" + vo.names[i] + "','" + vo.names[i] + "')");
			sql.append(";");
			System.out.println(sql.toString());
		}
	}
	
	public static void searchurl(SearchVO vo)
	{
		String searchid = vo.searchid + "03";

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
