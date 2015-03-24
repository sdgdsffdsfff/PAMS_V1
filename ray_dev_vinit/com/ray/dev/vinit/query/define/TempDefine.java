package com.ray.dev.vinit.query.define;

import com.ray.dev.vinit.query.vo.SearchVO;

public class TempDefine
{
	public static SearchVO define()
	{
		SearchVO s = new SearchVO();
		
		// 户表管理_信息收集
//		 s.searchid = "01010201";
//		 s.entity = "hbgl.xxsj";
//		 s.clazz = "com.pams.hbgl.main.entity.MeterApply";
//		
//		 s.names = new String[]{"id", "bt","ydrl","bactid","bactcname","ractcreatetime","ractownerctx","ractstate","runactkey"};
//		 s.types = new String[]{"0", "0", "1", "0", "0", "0", "0", "0", "0"};
//		 s.cnames = new String[]{"标识", "标题", "用电容量", "业务节点标识", "业务节点", "受理时间", "受理人", "处理状态", "活动标识"};
//		 s.pkeys = new String[]{"runactkey"};
//		 s.tableid = "T_APP_METERAPPLY";
//		 
//		 s.macro = "/com/pams/hbgl/xxsj/view/";		
		
		
		
	     // 共享管理 共享单维护
//		 s.searchid = "020101";
//		 s.entity = "gxgl.gxwh";
//		 s.clazz = "com.pams.entity.InfoShare";
//		
//		 s.names = new String[]{"id", "sourcename", "title", "cclassname", "filenums", "obtaintime", "bactid","bactcname","ractcreatetime","ractownerctx","ractstate","runactkey"};
//		 s.types = new String[]{"0", "0", "0", "0", "1", "0", "0", "0", "0", "0", "0", "0"};
//		 s.cnames = new String[]{"标识", "信息来源", "标题", "分类", "文件数量", "获取时间","业务节点标识", "业务节点", "受理时间", "受理人", "处理状态", "活动标识"};
//		 s.pkeys = new String[]{"runactkey"};
//		 s.tableid = "T_APP_INFOSHARE";
//		 
//		 s.macro = "/com/pams/gxgl/gxwh/view/";		
		 
		 
		 s.searchid = "020101";
		 s.entity = "gxgl.gxwh";
		 s.clazz = "com.pams.entity.InfoShare";
		
		 s.names = new String[]{"id","deptid","deptname","positionname","sourceid","sourcename","obtaintime","publishtime","title","summary","cclassid","cclassname","sharedauthor","filenums","filetype","memo","creater","creatername","createtime"};
		 s.types = new String[]{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","1","0","0","0","0"};
		 s.cnames = new String[]{"标识", "信息共享部门标识", "信息共享部门名称", "岗位名称", "信息来源标识", "信息来源","信息获取时间", "信息发布时间", "标题", "内容摘要", "分类标识", "分类名称", "共享权限", "文件数量", "文件形式", "备注", "申请人标识","申请人","申请时间"};
		 s.pkeys = new String[]{"runactkey"};
		 s.tableid = "T_APP_INFOSHARE";
		 
		 s.macro = "/com/pams/gxgl/gxwh/view/";
		 
		 System.out.println("names:" + s.names.length);
		 System.out.println("types:" + s.types.length);
		 System.out.println("cnames:" + s.cnames.length);
		 
		
		// 图表
//		 s.searchid = "000201";
//		 s.entity = "chart";
//		 s.clazz = "com.ray.app.chart.chart.entity.Chart";
//		
//		 s.names = new String[]{"id", "chartname","searchname","fx","fxcname","fs","fscname","beadid","ctype","title","subtitle","fxdate1","fxdate2","width","height"};
//		 s.types = new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
//		 s.cnames = new String[]{"标识", "图表名称", "查询名称", "X轴字段", "X轴名称", "分组字段", "分组名称", "报表组件名称", "报表类型", "报表标题", "报表子标题", "X轴时间开始列名", "X轴时间结束列名", "宽度", "高度"};
//		 s.pkeys = new String[]{"id"};
//		 s.tableid = "T_APP_CHART";
//		 s.macro = "/com/ray/app/chart/chart/view/";
		
		 // 图表类型
//		 s.searchid = "000202";
//		 s.entity = "chartoption";
//		 s.clazz = "com.ray.app.chart.chart.entity.ChartOption";
//		
//		 s.names = new String[]{"id", "fy","fycname","ooder","chartid"};
//		 s.types = new String[]{"0", "0", "0", "0", "0"};
//		 s.cnames = new String[]{"标识",  "Y轴字段", "Y轴名称", "排序", "图标标识"};
//		 s.pkeys = new String[]{"id"};
//		 s.tableid = "T_APP_CHARTOPTION";
//	     s.macro = "/com/ray/app/chart/chartoption/view/";

		// 数据字典分类
//		 s.searchid = "00010001";
//		 s.entity = "system.dictionary.dictionaryclass";
//		 s.clazz = "com.ray.app.dictionaryclass.entity.DictionaryClass";
//		
//		 s.names = new String[]{"id", "supid","dname"};
//		 s.types = new String[]{"0", "0", "0"};
//		 s.cnames = new String[]{"标识", "上级分类标识", "分类名称"};
//		 s.pkeys = new String[]{"id"};
//		 s.tableid = "T_SYS_DICTIONARYCLASS";
//		 s.macro = "/com/ray/app/dictionaryclass/view/";
//	
		 // 数据字典分类
//		 s.searchid = "00010002";
//		 s.entity = "system.dictionary.dictionary";
//		 s.clazz = "com.ray.app.dictionary.entity.Dictionary";
//		 
//		 s.names = new String[]{"id", "dclassid","dkey","dtext","dorder","memo"};
//		 s.types = new String[]{"0", "0", "0","0", "0","0"};
//		 s.cnames = new String[]{"标识", "所属分类标识", "字典key值", "名称", "排序", "备注"};
//		 s.pkeys = new String[]{"id"};
//		 s.tableid = "T_SYS_DICTIONARY";
//		 s.macro = "/com/ray/app/dictionary/view/";
		
		
		// 组织机构管理
//		 s.searchid = "01010101";
//		 s.entity = "system.organ.organ";
//		 s.clazz = "com.ray.xj.sgcc.irm.system.organ.organ.entity.Organ";
//				
//		 s.names = new String[]{"id","cno","ctype","shortname","cname","allname","parentorganid","address","email","phone","ordernum","internal","memo"};
//		 s.types = new String[]{"0","0","0","0","0","0","0","0","0","0","0","0","0"};
//		 s.cnames = new String[]{"标识","编号","类型","机构部门简称","机构部门全称","机构部门完整名称","上级标识","地址","电子邮件","电话","排序号","内部编码","备注"};
//		 s.pkeys = new String[]{"id"};
//		 s.tableid = "T_SYS_ORGAN";
//		 s.macro = "com/ray/xj/sgcc/irm/system/organ/organ/view/";

		 //人员管理
//		 s.searchid = "01010102";
//		 s.entity = "system.organ.user";
//		 s.clazz = "com.ray.xj.sgcc.irm.system.organ.user.entity.User";
//		
//		 s.names = new String[]{"id","cno","loginname","cname","ownerorg","owneorgname","birthday","sex","position","password","address","email","phone","mobile","memo"};
//		 s.types = new String[]{"0","0","0","0","0","0","1","0","0","0","0","0","0","0","0"};
//		 s.cnames = new String[]{"标识","编号","登录名","中文名","所属组织机构编号","所属组织机构名称","出生日期","性别","职位","密码","地址","电子邮件","电话","手机","备注"};
//		 s.pkeys = new String[]{"id"};
//		 s.tableid = "T_SYS_USER";
//		 s.macro = "/com/ray/xj/sgcc/irm/system/organ/user/view/";

		 // 角色管理
//		 s.searchid = "01010201";
//		 s.entity = "system.organ.role";
//		 s.clazz = "com.ray.xj.sgcc.irm.system.organ.role.entity.Role";
//		
//		 s.names = new String[]{"id", "cname","name","memo"};
//		 s.types = new String[]{"0", "0", "0","0"};
//		 s.cnames = new String[]{"标识", "角色中文名", "角色名","备注"};
//		 s.pkeys = new String[]{"id"};
//		 s.tableid = "T_SYS_ROLE";
//		 s.macro = "/com/ray/xj/sgcc/irm/system/organ/role/view/";
		
		 // 功能管理
//		 s.searchid = "01010202";
//		 s.entity = "system.author.function";
//		 s.clazz ="com.ray.xj.sgcc.irm.system.author.function.entity.Function";
//		
//		 s.names = new String[]{"id","fnum","cname","ctype","url","memo"};
//		 s.types = new String[]{"0", "0","0","0","0","0"};
//		 s.cnames = new String[]{"标识","编号","功能模块名称","功能模块类型","功能模块地址","备注"};
//		 s.pkeys = new String[]{"id"};
//		 s.tableid = "T_SYS_FUNCTION";
//		 s.macro = "/com/ray/xj/sgcc/irm/system/author/function/view/";
		
		 // 人员角色
//		 s.searchid = "01010204";
//		 s.entity = "system.author.userrole";
//		 s.clazz ="com.ray.xj.sgcc.irm.system.author.userrole.entity.UserRole";
//		
//		 s.names = new String[]{ "id", "uname","rname" };
//		 s.types = new String[]{ "0", "0", "0" };
//		 s.cnames = new String[]{ "标识", "用户名称", "角色名称" };
//		 s.pkeys = new String[]{ "id" };
//		 s.tableid = "T_SYS_USERROLE";
//		 s.macro = "com/ray/xj/sgcc/irm/system/author/userrole/view/";


		 // 角色功能管理
//		 s.searchid = "01010205";
//		 s.entity = "system.author.rolefunction";
//		 s.clazz =
//		 "com.ray.xj.sgcc.irm.system.author.rolefunction.entity.RoleFunction";
//		
//		 s.names = new String[]{"id","functionid","rname"};
//		 s.types = new String[]{"0","0","0"};
//		 s.cnames = new String[]{"标识","权限标识","角色标识"};
//		 s.pkeys = new String[]{"id"};
//		 s.tableid = "T_SYS_ROLEFUNCTION";
//		 s.macro = "/com/ray/xj/sgcc/irm/system/author/rolefunction/view/";

		//栏目
//		s.searchid = "01010301";
//		s.entity = "system.portal.subject";
//		s.clazz = "com.ray.xj.sgcc.irm.system.portal.subject.entity.Subject";
//
//		s.names = new String[]{"id","cname","pname","memo"};
//		s.types = new String[]{"0","0","0","0"};
//		s.cnames = new String[]{"标识","栏目标题","栏目名","备注"};
//		s.pkeys = new String[]{"id"};
//		s.tableid = "T_APP_SUBJECT";
//		s.macro = "/com/ray/xj/sgcc/irm/system/portal/subject/view/";	
		
		//角色栏目
//		s.searchid = "01010302";
//		s.entity = "system.portal.rolesubject";
//		s.clazz = "com.ray.xj.sgcc.irm.system.portal.rolesubject.entity.RoleSubject";
//
//		s.names = new String[]{"id","subjectid","subjectname","roleid","rolename","use","memo"};
//		s.types = new String[]{"0","0","0","0","0","0","0"};
//		s.cnames = new String[]{"标识","栏目标识","栏目名","角色标识","角色名","是否使用中的模板","备注"};
//		s.pkeys = new String[]{"id"};
//		s.tableid = "T_APP_ROLESUBJECT";
//		s.macro = "/com/ray/xj/sgcc/irm/system/portal/rolesubject/view/";	
		
		//快捷项
//		s.searchid = "01010303";
//		s.entity = "system.portal.shortcut";
//		s.clazz = "com.ray.xj.sgcc.irm.system.portal.shortcut.entity.ShortCut";
//
//		s.names = new String[]{"id","cname","pname","url","target","memo"};
//		s.types = new String[]{"0","0","0","0","0","0"};
//		s.cnames = new String[]{"标识","快捷项标题","快捷项名","快捷链接地址","快捷目标窗口","备注"};
//		s.pkeys = new String[]{"id"};
//		s.tableid = "T_APP_SHORTCUT";
//		s.macro = "/com/ray/xj/sgcc/irm/system/portal/shortcut/view/";	
		
		//角色快捷项
//		s.searchid = "01010304";
//		s.entity = "system.portal.roleshortcut";
//		s.clazz = "com.ray.xj.sgcc.irm.system.portal.roleshortcut.entity.RoleShortCut";
//
//		s.names = new String[]{"id","roleid","shortcutid","shortcutname","memo"};
//		s.types = new String[]{"0","0","0","0","0"};
//		s.cnames = new String[]{"标识","角色标识","快捷项标识","快捷项名称","备注"};
//		s.pkeys = new String[]{"id"};
//		s.tableid = "T_APP_ROLESHORTCUT";
//		s.macro = "/com/ray/xj/sgcc/irm/system/portal/roleshortcut/view/";

		 
		 
		 return s;
	}
}
