package com.ray.dev.vinit.query;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.headray.framework.common.generator.FormatKey;
import com.headray.framework.services.function.StringToolKit;
import com.ray.dev.vinit.query.define.TempDefine;
import com.ray.dev.vinit.query.define.SearchDefine;
import com.ray.dev.vinit.query.define.SearchItemDefine;
import com.ray.dev.vinit.query.define.SearchLinkDefine;
import com.ray.dev.vinit.query.define.SearchOptionDefine;
import com.ray.dev.vinit.query.define.SearchUrlDefine;
import com.ray.dev.vinit.query.vo.SearchVO;

public class VinitSearch
{
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	public static void main(String[] args)
	{
		VinitSearch client = new VinitSearch();
		
		List s = client.init();
		
		client.make(s);
	}
	
	public List init()
	{
		List s = new ArrayList();
		
//		s.add(SearchDefine.define());
//		s.add(SearchOptionDefine.define());
//		s.add(SearchItemDefine.define());
//		s.add(SearchUrlDefine.define());
//		s.add(SearchLinkDefine.define());
		
//		s.add(SearchLinkDefine.define());		
		s.add(TempDefine.define());
		
		return s;
	}
	
	public static SearchVO init_aobj(String searchid, String entity, String[] cnames, String[] names, String types[], String[] pkeys, String tableid)
	{
		SearchVO s = new SearchVO();
		s.searchid = searchid;
		s.entity = entity;
		s.cnames = cnames;
		s.names = names;
		s.types = types;
		s.pkeys = pkeys;
		s.tableid = tableid;
		return s;
	}
	
	public void make(List s)
	{
		for(int i=0;i<s.size();i++)
		{
			SearchVO vo = (SearchVO)s.get(i);
			cmake(vo);
		}
	}
	
	public void cmake(SearchVO vo)
	{
//		cmake_object(vo);
//		cmake_browse(vo);
		//cmake_locate(vo);
		cmake_input(vo);
//		cmake_locateby(vo);
	}
	
	public void cmake_object(SearchVO vo)
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into t_sys_object (id, cname, pname, ctype, ref, supid, attrctype, pkey, skey, length)");
        sql.append(" values('" + vo.searchid + "', '', '" + vo.entity + "', 'C', null, 'R0', 'STRING', null, null, 0)");
        System.out.println(sql.toString());
	}
	
	public void cmake_browse(SearchVO vo)
	{
		String searchid = vo.searchid + "01";
		
		StringBuffer sql = new StringBuffer();
        sql = new StringBuffer();
		sql.append(" insert into t_sys_search (searchid, searchname, title, entityname, macro, pagesize)");
		sql.append(" values('" + searchid + "', '" + vo.entity + ".browse', '', '" + vo.clazz + "', '" + vo.macro + "browse.ftl', 20)");
		sql.append(";");
		System.out.println(sql.toString());
		
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
			
			sql.append(" values('" + (searchid + FormatKey.format(i+1, 2)) + "', '" + searchid + "', '" + vo.names[i] + "', '" + fieldtype + "', " + pkey + ", '" + vo.cnames[i] + "', 1, 'label')");
			sql.append(";");
			System.out.println(sql.toString());
		}
		
		for(int i=0;i<vo.names.length;i++)
		{
			sql = new StringBuffer();
			sql.append(" insert into t_sys_searchitem (searchitemid, searchid, caption, field, htmlfield, qfield)");
			sql.append(" values('" + (searchid + FormatKey.format(i+1, 2)) + "', '" + searchid + "', '" + vo.cnames[i] + "', '" + vo.names[i] + "', '" + vo.names[i] + "','" + vo.names[i] + "')");
			sql.append(";");
			System.out.println(sql.toString());
		}
		

		sql = new StringBuffer();
		sql.append(" insert into t_sys_searchurl (searchurlid, searchid, title, pname)");
		sql.append(" values('" + (searchid + FormatKey.format(1, 2)) + "', '" + searchid + "', '新增', 'input')");
		sql.append(";");
		System.out.println(sql.toString());
		
		sql = new StringBuffer();
		sql.append(" insert into t_sys_searchurl (searchurlid, searchid, title, pname)");
		sql.append(" values('" + (searchid + FormatKey.format(2, 2)) + "', '" + searchid + "', '删除', 'del')");
		sql.append(";");
		System.out.println(sql.toString());
		
		sql = new StringBuffer();
		sql.append(" insert into t_sys_searchurl (searchurlid, searchid, title, pname)");
		sql.append(" values('" + (searchid + FormatKey.format(3, 2)) + "', '" + searchid + "', '查询', 'query')");
		sql.append(";");
		System.out.println(sql.toString());		
		
	}
	
	public void cmake_locate(SearchVO vo)
	{
		String searchid = vo.searchid + "02";
		
		StringBuffer sql = new StringBuffer();

        sql = new StringBuffer();
//		sql.append(" insert into t_sys_search (searchid, searchname, entityname, mysql, formname, pagesize)");
//		
//		String pkeys = "";
//		for(int i=0;i<vo.pkeys.length;i++)
//		{
//			pkeys += (vo.pkeys[i] + " = $" + vo.pkeys[i]);
//		}
//		
//		sql.append(" values('" + searchid + "', '" + vo.entity + ".locate', '" + vo.entity + "', 'select * from " + vo.tableid + " where 1 = 1 and " + pkeys + "','form',20)");
//		sql.append(";");
		
		
		sql.append(" insert into t_sys_search (searchid, searchname, title, entityname, macro, pagesize)");
		sql.append(" values('" + searchid + "', '" + vo.entity + ".locate', '', '" + vo.clazz + "', '" + vo.macro + "locate.ftl', 20)");
		sql.append(";");
		System.out.println(sql.toString());

		
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
			
			sql.append(" values('" + (searchid + FormatKey.format(i+1, 2)) + "', '" + searchid + "', '" + vo.names[i] + "', '" + fieldtype + "', " + pkey + ", '" + vo.cnames[i] + "', 1, 'text' )");
			sql.append(";");
			System.out.println(sql.toString());
		}
		
		for(int i=0;i<vo.pkeys.length;i++)
		{
			sql = new StringBuffer();
			sql.append(" insert into t_sys_searchitem (searchitemid, searchid, caption, field, htmlfield, qfield)");
			sql.append(" values('" + (searchid + FormatKey.format(i+1, 2)) + "', '" + searchid + "', '" + vo.cnames[StringToolKit.getTextInArrayIndex(vo.names, vo.pkeys[i])] + "', '" + vo.names[i] + "', '" + vo.names[i] + "','" + vo.names[i] + "')");
			sql.append(";");
			System.out.println(sql.toString());
		}
		

		sql = new StringBuffer();
		sql.append(" insert into t_sys_searchurl (searchurlid, searchid, title, pname)");
		sql.append(" values('" + (searchid + FormatKey.format(1, 2)) + "', '" + searchid + "', '保存', 'save')");
		sql.append(";");
		System.out.println(sql.toString());
		
		sql = new StringBuffer();
		sql.append(" insert into t_sys_searchurl (searchurlid, searchid, title, pname)");
		sql.append(" values('" + (searchid + FormatKey.format(2, 2)) + "', '" + searchid + "', '关闭', 'close')");
		sql.append(";");
		System.out.println(sql.toString());
	}
	
	public void cmake_locateby(SearchVO vo)
	{
		String searchid = vo.searchid + "04";
		
		StringBuffer sql = new StringBuffer();

        sql = new StringBuffer();
		sql.append(" insert into t_sys_search (searchid, searchname, entityname, mysql, formname, pagesize)");
		
		String pkeys = "";
		for(int i=0;i<vo.pkeys.length;i++)
		{
			pkeys += (vo.pkeys[i] + " = $" + vo.pkeys[i]);
		}
		
		sql.append(" values('" + searchid + "', '" + vo.entity + ".locateby', '" + vo.entity + "', 'select * from " + vo.tableid + " where 1 = 1 and " + pkeys + "','form',20)");
		sql.append(";");
		System.out.println(sql.toString());
		
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
			
			sql.append(" values('" + (searchid + FormatKey.format(i+1, 2)) + "', '" + searchid + "', '" + vo.names[i] + "', '" + fieldtype + "', " + pkey + ", '" + vo.cnames[i] + "', 1, 'text' )");
			sql.append(";");
			System.out.println(sql.toString());
		}
		
		for(int i=0;i<vo.names.length;i++)
		{
			sql = new StringBuffer();
			sql.append(" insert into t_sys_searchitem (searchitemid, searchid, caption, field, htmlfield, qfield)");
			sql.append(" values('" + (searchid + FormatKey.format(i+1, 2)) + "', '" + searchid + "', '" + vo.cnames[i] + "', '" + vo.names[i] + "', '" + vo.names[i] + "','" + vo.names[i] + "')");
			sql.append(";");
			System.out.println(sql.toString());
		}

		sql = new StringBuffer();
		sql.append(" insert into t_sys_searchurl (searchurlid, searchid, title, pname)");
		sql.append(" values('" + (searchid + FormatKey.format(1, 2)) + "', '" + searchid + "', '保存', 'save')");
		sql.append(";");
		System.out.println(sql.toString());
		
		sql = new StringBuffer();
		sql.append(" insert into t_sys_searchurl (searchurlid, searchid, title, pname)");
		sql.append(" values('" + (searchid + FormatKey.format(2, 2)) + "', '" + searchid + "', '关闭', 'close')");
		sql.append(";");
		System.out.println(sql.toString());
	}
	
	public void cmake_input(SearchVO vo)
	{
		String searchid = vo.searchid + "03";
		
		StringBuffer sql = new StringBuffer();

		sql = new StringBuffer();
		sql.append(" insert into t_sys_search (searchid, searchname, entityname, mysql, formname, pagesize)");
		sql.append(" values('" + searchid + "', '" + vo.entity + ".input', '" + vo.entity + "', '', 'form', 20)");
		sql.append(";");
		System.out.println(sql.toString());
		
		for(int i=0;i<vo.names.length;i++)
		{
			sql = new StringBuffer();
			sql.append(" insert into t_sys_searchoption (searchoptionid, searchid, field, fieldtype, pkey, title, visible, edittype)");
			String fieldtype = "char";
			if("1".equals(vo.types[i]))
			{
				fieldtype = "integer";
			}
			sql.append(" values('" + (searchid + FormatKey.format(i+1, 2)) + "', '" + searchid + "', '" + vo.names[i] + "', '" + fieldtype + "', 0, '" + vo.cnames[i] + "', 1, 'text')");
			sql.append(";");
			System.out.println(sql.toString());
		}
		
		for(int i=0;i<vo.names.length;i++)
		{
			sql = new StringBuffer();
			sql.append(" insert into t_sys_searchitem (searchitemid, searchid, caption, field, htmlfield, qfield)");
			sql.append(" values('" + (searchid + FormatKey.format(i+1, 2)) + "', '" + searchid + "', '" + vo.cnames[i] + "', '" + vo.names[i] + "', '" + vo.names[i] + "','" + vo.names[i] + "')");
			sql.append(";");
			System.out.println(sql.toString());
		}
		
		sql = new StringBuffer();
		sql.append(" insert into t_sys_searchurl (searchurlid, searchid, title, pname)");
		sql.append(" values('" + (searchid + FormatKey.format(1, 2)) + "', '" + searchid + "', '保存', 'save')");
		sql.append(";");
		System.out.println(sql.toString());
		
		sql = new StringBuffer();
		sql.append(" insert into t_sys_searchurl (searchurlid, searchid, title, pname)");
		sql.append(" values('" + (searchid + FormatKey.format(2, 2)) + "', '" + searchid + "', '关闭', 'close')");
		sql.append(";");
		System.out.println(sql.toString());
	}
	

}
