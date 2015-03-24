package com.ray.dev.vinit.query.define;

import com.ray.dev.vinit.query.vo.SearchVO;

public class SearchLinkDefine
{
	public static SearchVO define()
	{
		SearchVO s = new SearchVO();
		
		s.searchid = "000005";		
		s.entity = "searchlink";
		s.clazz = "com.ray.app.query.entity.SearchLink";

		s.names = new String[]{"searchlinkid","link","url","linkfield","linksearchname","oorder","linkname","userflag"};
		s.types = new String[]{"0","0","0","0","0","1","0","0"};
		s.cnames = new String[]{"标识", "链接", "链接地址", "链接字段", "链接查询名称", "排序字段", "链接名", "用户标记"};
		s.pkeys = new String[]{"searchlinkid"};
		s.tableid = "t_sys_searchlink";
		s.macro = "/com/ray/app/searchlink/view/";
		
		s.parentkey = "search.searchid";
		s.parenthtmlkey = "searchid";
		
		return s;
	}
}
