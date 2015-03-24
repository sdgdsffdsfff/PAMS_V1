package com.ray.dev.vinit.config;

import java.util.ArrayList;
import java.util.List;

public class TemplateQTRJ
{
	public static List get_qtrj()
	{
		List<String[]> items = new ArrayList();
		items.addAll(get_qtrj_jbsx_all());
		items.addAll(get_qtrj_gjsx_all());
		items.addAll(get_qtrj_glsx_all());
		return items;
	}
	
	// 获取全部基本属性
	public static List get_qtrj_jbsx_all()
	{
		List<String[]> items = new ArrayList();

		items.add(new String[]{"软件名称","rjmc","","",""});
		items.add(new String[]{"软件中文名称","rjzwmc","","",""});
		items.add(new String[]{"俗称","sc","","",""});
		items.add(new String[]{"版本","bb","","",""});
		items.add(new String[]{"补丁版本","bdbb","","",""});
		items.add(new String[]{"软件类型","rjlx","","",""});
		items.add(new String[]{"数据位数","sjws","","",""});
		items.add(new String[]{"国家语言","gjyy","","",""});
		items.add(new String[]{"软件类别","rjlb","","",""});	
		items.add(new String[]{"序列号","xlh","","",""});
		items.add(new String[]{"软件厂商","rjcs","","",""});
		items.add(new String[]{"供应商","gys","","",""});
		items.add(new String[]{"服务商","fws","","",""});
		items.add(new String[]{"所属项目","ssxm","","",""});			
		items.add(new String[]{"合同金额","htje","","",""});
		items.add(new String[]{"介质形式","jzxs","","",""});
		items.add(new String[]{"购买日期","gmrq","","",""});
		items.add(new String[]{"出保日期","cbrq","","",""});
		items.add(new String[]{"授权说明","sqsm","","",""});
		items.add(new String[]{"运行环境","yxhj","","",""});
		items.add(new String[]{"备注","bz","","",""});			
		items.add(new String[]{"附件","fj","","",""});
		
		return items;
	}
	
	
	// 获取全部固件属性
	public static List get_qtrj_gjsx_all()
	{
		List<String[]> items = new ArrayList();
		
		items.addAll(get_qtrj_gjsx_dk());
		
		return items;
	}
	//获取基本属性（端口子项）
	public static List get_qtrj_gjsx_dk()
	{
		List<String[]> items = new ArrayList();
		
		// 管理属性.端口
		items.add(new String[]{"端口","dk","1","",""});

		items.add(new String[]{"端口号","dkh","2","",""});
		items.add(new String[]{"端口服务名（进程）","dkfwm","2","",""});
		items.add(new String[]{"说明","sm","2","",""});
		
		return items;
	}
	
	// 获取全部管理属性
	public static List get_qtrj_glsx_all()
	{
		List<String[]> items = new ArrayList();
		items.addAll(get_qtrj_glsx());
		items.addAll(get_qtrj_glsx_glzhxx());
		
		return items;
	}

	//获取管理属性（简单属性）
	public static List get_qtrj_glsx()
	{
		List<String[]> items = new ArrayList();
		
		// 管理属性
		items.add(new String[]{"维护单位","whdw","","",""});
		items.add(new String[]{"维护人","whr","","",""});
		items.add(new String[]{"联系电话","lxdh","","",""});
	
		return items;
	}
	//获取管理属性（管理帐号信息子项）
	public static List get_qtrj_glsx_glzhxx()
	{
		List<String[]> items = new ArrayList();
		
		// 管理属性.管理帐号信息
		items.add(new String[]{"管理帐号信息","glzhxx","1","",""});

		items.add(new String[]{"帐号名称","zhmc","2","",""});
		items.add(new String[]{"帐号说明","zhsm","2","",""});
		items.add(new String[]{"帐号使用人","zhsyr","2","",""});
		items.add(new String[]{"备注","bz","2","",""});
		
		return items;
	}
	
}
