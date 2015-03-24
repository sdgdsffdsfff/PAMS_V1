package com.ray.dev.vinit.query.define;

import com.ray.dev.vinit.query.vo.SearchVO;

public class SearchItemDefine
{
	public static SearchVO define()
	{
		SearchVO s = new SearchVO();
		
		s.searchid = "000003";		
		s.entity = "searchitem";
		s.clazz = "com.ray.app.query.entity.SearchItem";

		s.names = new String[]{"searchitemid","caption","htmlfield","field","qfield","matchcode","operator","usertag","oorder","searchflag","clickevent","param1","param2","mutiselect","fieldtype","edittype","dstype","defvalue","sfield","sparam1","sparam2","help","userflag"};
		s.types = new String[]{"0","0","0","0","0","0","0","0","1","1","0","0","0","1","0","0","0","0","0","0","0","0","0"};
		s.cnames = new String[]{"标识", "标题", "页面字段名称", "字段名", "查询参数名", "匹配符", "操作符", "用户标签", "排序字段", "查询标记", "点击事件", "参数1", "参数2", "是否多选", "字段类型", "编辑类型", "数据源类型", "缺省值", "数据源字段", "数据源参数1", "数据源参数2", "帮助", "用户标记"};
		s.pkeys = new String[]{"searchitemid"};
		s.tableid = "t_sys_searchitem";
		s.macro = "/com/ray/app/searchitem/view/";
		
		s.parentkey = "search.searchid";
		s.parenthtmlkey = "searchid";
		
		return s;
	}
}
