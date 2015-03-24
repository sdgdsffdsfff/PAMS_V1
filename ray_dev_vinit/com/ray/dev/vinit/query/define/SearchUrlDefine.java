package com.ray.dev.vinit.query.define;

import com.ray.dev.vinit.query.vo.SearchVO;

public class SearchUrlDefine
{
	public static SearchVO define()
	{
		SearchVO s = new SearchVO();
		
		s.searchid = "000004";		
		s.entity = "searchurl";
		s.clazz = "com.ray.app.query.entity.SearchUrl";

		s.names = new String[]{"searchurlid","title","code","pname","url","nullflag","oorder","parentmenu","help","visible","userflag"};
		s.types = new String[]{"0","0","0","1","1","0","0","0","0","1","0"};
		s.cnames = new String[]{"标识", "标题", "名称", "编码", "地址", "空标记", "排序", "上级菜单", "帮助", "是否显示", "用户标记"};
		s.pkeys = new String[]{"searchurlid"};
		s.tableid = "t_sys_searchurl";
		s.macro = "/com/ray/app/searchurl/view/";
		
		s.parentkey = "search.searchid";
		s.parenthtmlkey = "searchid";
		
		return s;
	}
}
