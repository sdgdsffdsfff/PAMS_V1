package com.ray.dev.vinit.config;

import java.util.ArrayList;
import java.util.List;

public class TemplateRJZC
{
	public static List get_rjzc()
	{
		List<String[]> items = new ArrayList();
		items.addAll(get_rjzc_jbsx_all());
		return items;
	}
	// 全体基本属性
	public static List get_rjzc_jbsx_all()
	{
		List<String[]> items = new ArrayList();
		

		items.add(new String[]{"软件名称","rjmc","","",""});
		items.add(new String[]{"软件中文名称","rjzwmc","","",""});	
		items.add(new String[]{"版本","bb","","",""});
		items.add(new String[]{"序列号","xlh","","",""});
		items.add(new String[]{"软件类型","rjlx","","",""});			
		items.add(new String[]{"数据位数","sjws","","",""});
		items.add(new String[]{"国家语言","gjyy","","",""});	
		items.add(new String[]{"适用操作系统","syczxt","","",""});
		items.add(new String[]{"软件厂商","rjcs","","",""});			
		items.add(new String[]{"供应商","gys","","",""});
		items.add(new String[]{"服务商","fws","50","",""});
		items.add(new String[]{"所属项目","ssxm","","",""});
		items.add(new String[]{"合同金额","htje","","",""});
		items.add(new String[]{"介质形式","jzxs","","",""});
		items.add(new String[]{"购买日期","gmrq","","",""});
		items.add(new String[]{"出保日期","cbrq","","",""});
		items.add(new String[]{"授权说明","sqsm","","",""});
		items.add(new String[]{"保管位置","bgwz","","",""});
		items.add(new String[]{"保管单位","bgdw","","",""});
		items.add(new String[]{"保管人","bgr","","",""});
		items.add(new String[]{"备注","bz","","",""});
		items.add(new String[]{"附件","fj","","",""});
		
		return items;
	}
		
	
}
