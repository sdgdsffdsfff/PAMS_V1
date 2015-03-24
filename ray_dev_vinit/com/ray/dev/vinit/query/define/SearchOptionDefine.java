package com.ray.dev.vinit.query.define;

import com.ray.dev.vinit.query.vo.SearchVO;

public class SearchOptionDefine
{
	public static SearchVO define()
	{
		SearchVO s = new SearchVO();
		
		s.searchid = "000002";		
		s.entity = "searchoption";
		s.clazz = "com.ray.app.query.entity.SearchOption";

		s.names = new String[]{"searchoptionid","title","visible","field","fieldtype","required","edittype","dstype","defvalue","param1","param2","oorder","displayformat","displaywidth","printable","calctype","url","linkfield","fieldkey","pkey","trattr","readonly","issum","help","userflag"};
		s.types =  new String[]{"0","0","0","1","0","0","0","0","1","0","0","0","0","0","0","0","1","0","0","0","0","0","0","0","0"};
		s.cnames = new String[]{"标识", "标题", "是否显示", "字段名", "字段类型", "必需字段", "编辑类型", "数据源类型", "缺省值", "参数1", "参数2", "排序", "显示格式", "显示宽度", "允许打印", "计算类型", "链接地址", "链接字段", "字段主键", "主键标识", "行属性", "只读字段", "合计字段", "帮助", "用户标记"};
		s.pkeys = new String[]{"searchoptionid"};
		s.tableid = "t_sys_searchoption";
		s.macro = "/com/ray/app/searchoption/view/";
		
		s.parentkey = "search.searchid";
		s.parenthtmlkey = "searchid";
		
		return s;

	}
}
