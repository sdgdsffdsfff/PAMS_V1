package com.ray.dev.vinit.config;

import java.util.ArrayList;
import java.util.List;

public class TemplateYYXT
{
	public static List get_yyxt()
	{
		List<String[]> items = new ArrayList();
		items.addAll(get_yyxt_jbsx_all());
		items.addAll(get_yyxt_glsx_all());
		return items;
	}
	
	// 全体基本属性
	public static List get_yyxt_jbsx_all()
	{
		List<String[]> items = new ArrayList();
		

		items.add(new String[]{"系统中文名称","xtzwmc","","",""});
		items.add(new String[]{"系统英文名称","xtywmc","","",""});	
		items.add(new String[]{"系统俗称","xtsc","","",""});
		items.add(new String[]{"内外网标识","nwwbs","","",""});
		items.add(new String[]{"建设开始日期","jsksrq","","",""});			
		items.add(new String[]{"建设完成日期","jswcrq","","",""});
		items.add(new String[]{"建设模式","jsms","","",""});
		items.add(new String[]{"部署模式","bsms","","",""});
		items.add(new String[]{"上线日期","sxrq","","",""});	
		items.add(new String[]{"下线日期","xxrq","","",""});	
		items.add(new String[]{"出保日期","cbrq","","",""});
		items.add(new String[]{"限停序位","xtxw","","",""});
		items.add(new String[]{"版本","bb","","",""});
		items.add(new String[]{"版本发布时间","bbfbsj","","",""});
		items.add(new String[]{"版本更新内容描述","bbgxnrms","","",""});
		items.add(new String[]{"版本更新内容附件","bbgxnrfj","","",""});
		items.add(new String[]{"开发厂商名称","kfcsmc","","",""});
		items.add(new String[]{"开发厂商地址","kfcsdz","","",""});
		items.add(new String[]{"开发厂商主要负责人","kfcszyfzr","","",""});
		items.add(new String[]{"开发厂商联系方式","kfcslxfs","","",""});
		items.add(new String[]{"供应商名称","gysmc","","",""});
		items.add(new String[]{"供应商地址","gysdz","","",""});
		items.add(new String[]{"供应商主要负责人","gyszyfzr","","",""});
		items.add(new String[]{"供应商联系方式","gyslxfs","","",""});
		items.add(new String[]{"服务商名称","fwsmc","","",""});
		items.add(new String[]{"服务商地址","fwsdz","","",""});
		items.add(new String[]{"服务商主要负责人","fwszyfzr","","",""});
		items.add(new String[]{"服务商联系方式","fwslxfs","","",""});
		items.add(new String[]{"所属项目","ssxm","","",""});
		items.add(new String[]{"合同金额","htje","","",""});
		items.add(new String[]{"系统等级","xtdj","","",""});
		items.add(new String[]{"安全等级","aqdj","","",""});			
		items.add(new String[]{"服务等级","fwdj","","",""});
		items.add(new String[]{"开发平台","kfpt","","",""});
		items.add(new String[]{"系统说明","xtsm","","",""});
		items.add(new String[]{"系统覆盖范围","xtfgfw","","",""});
		items.add(new String[]{"备注","bz","","",""});
		items.add(new String[]{"附件","fj","","",""});
		
		return items;
	}
	
	// 获取全部管理属性
	public static List get_yyxt_glsx_all()
	{
		List<String[]> items = new ArrayList();
		items.addAll(get_yyxt_glsx_glxx());
		
		return items;
	}
	//获取管理属性（管理信息子项）
	public static List get_yyxt_glsx_glxx()
	{
		List<String[]> items = new ArrayList();
		
		// 管理属性.管理信息
		items.add(new String[]{"管理信息","glxx","1","",""});
		
		items.add(new String[]{"阶段名称","jdmc","2","",""});
		items.add(new String[]{"起始时间","qssj","2","",""});
		items.add(new String[]{"责任部门","zrbm","2","",""});
		items.add(new String[]{"责任人","zrr","2","",""});
		items.add(new String[]{"联系电话","lxdh","2","",""});
		
		return items;
	}


		
	
}
