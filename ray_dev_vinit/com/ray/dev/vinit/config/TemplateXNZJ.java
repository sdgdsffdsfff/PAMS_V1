package com.ray.dev.vinit.config;

import java.util.ArrayList;
import java.util.List;

public class TemplateXNZJ
{
	public static List get_xnzj()
	{
		List<String[]> items = new ArrayList();
		items.addAll(get_xnzj_jbsx_all());
		items.addAll(get_xnzj_gjsx_all());
		items.addAll(get_xnzj_glsx_all());
		return items;
	}
	
	
	// 获取全部基本属性
	public static List get_xnzj_jbsx_all()
	{
		List<String[]> items = new ArrayList();
		items.addAll(get_xnzj_jbsx());
		
		
		return items;
	}
	
	// 获取基本属性(简单属性)
	public static List get_xnzj_jbsx()
	{
		List<String[]> items = new ArrayList();
		

		items.add(new String[]{"虚拟主机名称","xnzjmc","","",""});
		items.add(new String[]{"俗称","sc","","",""});	
		items.add(new String[]{"隶属虚拟化平台","lsxnhpt","","",""});
		items.add(new String[]{"投运日期","tyrq","","",""});			
		items.add(new String[]{"安全等级","aqdj","","",""});
		items.add(new String[]{"服务级别","fwjb","","",""});
		items.add(new String[]{"备注","bz","","",""});			
		items.add(new String[]{"附件","fj","","",""});
		
		return items;
	}
	
	// 获取全部固件属性
	public static List<String[]> get_xnzj_gjsx_all()
	{
		List<String[]> items = new ArrayList();
		items.addAll(get_xnzj_gjsx());
		
		return items;
	}
	
	// 获取固件属性（简单属性）
	public static List get_xnzj_gjsx()
	{
		List<String[]> items = new ArrayList();
		
		// 固件属性
		items.add(new String[]{"虚拟资源属性","xnzysx","1","",""});

		items.add(new String[]{"内存","nc","2","",""});
		items.add(new String[]{"硬盘容量","cprl","2","",""});
		items.add(new String[]{"CPU","cpu","2","",""});
		items.add(new String[]{"操作系统","cuxt","2","",""});
		items.add(new String[]{"IP地址","ipdz","2","",""});
		
		
		return items;
	}
	
	// 获取全部管理属性
	public static List get_xnzj_glsx_all()
	{
		List<String[]> items = new ArrayList();
		items.addAll(get_xnzj_glsx());
		items.addAll(get_xnzj_glsx_glzhxx());
		
		return items;
	}

	
	//获取管理属性（简单属性）
	public static List get_xnzj_glsx()
	{
		List<String[]> items = new ArrayList();
		
		// 管理属性
		items.add(new String[]{"维护单位","whdw","","",""});
		items.add(new String[]{"维护人","whr","","",""});
		items.add(new String[]{"联系电话","lxdh","","",""});
	
		return items;
	}
	//获取管理属性（管理帐号信息子项）
	public static List get_xnzj_glsx_glzhxx()
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
