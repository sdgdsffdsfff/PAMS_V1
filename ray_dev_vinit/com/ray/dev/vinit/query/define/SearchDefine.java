package com.ray.dev.vinit.query.define;

import com.ray.dev.vinit.query.vo.SearchVO;

public class SearchDefine
{
	public static SearchVO define()
	{
		SearchVO s = new SearchVO();
		
		s.searchid = "000001";		
		s.entity = "search";
		s.clazz = "com.ray.app.query.entity.Search";

		s.names = new String[]{"searchid","searchname","title","macro","pagesize","ischeck","isno","divnav","divbutton","macro1","macro2","entityname","mysql","orderby","groupby","ds","fieldkey","templateid","uiid","formname","formaction","mykey","listtableid","help","positive","userflag"};
		s.types = new String[]{"0","0","0","0","0","0","0","0","0","1","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
		s.cnames = new String[]{"查询标识","查询名称","标题","宏定义页面","页行数","多选","行号","导航栏","功能栏","宏定义页面1","宏定义页面2","实体类名称","查询语句","排序字段","分组字段","数据源","字段主键","模板标识","界面标识","表单名称","表单地址","自定义主键","视图标识","帮助说明","正向","排序标志"};
		s.pkeys = new String[]{"searchid"};
		s.tableid = "t_sys_search";
		s.macro = "/com/ray/app/search/view/";
		
		return s;
	}
}
