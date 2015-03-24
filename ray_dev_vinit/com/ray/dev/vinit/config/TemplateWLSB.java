package com.ray.dev.vinit.config;

import java.util.ArrayList;
import java.util.List;

public class TemplateWLSB
{
	public static List get_wlsb()
	{
		List<String[]> items = new ArrayList();
		items.addAll(get_wlsb_jbsx_all());
		items.addAll(get_wlsb_gjsx_all());
		items.addAll(get_wlsb_glsx_all());
		return items;
	}
	
	// 全体基本属性
	public static List get_wlsb_jbsx_all()
	{
		List<String[]> items = new ArrayList();
		

		items.add(new String[]{"设备序列号","sbxlh","","",""});
		items.add(new String[]{"设备名称","sbmc","","",""});	
		items.add(new String[]{"俗称","sc","","",""});
		items.add(new String[]{"ERP设备号","erpsbh","","",""});
		items.add(new String[]{"资产编号","zcbh","","",""});			
		items.add(new String[]{"国网编号","gwbh","","",""});
		items.add(new String[]{"设备类型","sblx","","",""});
		items.add(new String[]{"内外网标识","nwwbs","","",""});	
		items.add(new String[]{"设备品牌","sbpp","","",""});			
		items.add(new String[]{"设备型号","sbxh","","",""});
		items.add(new String[]{"生产厂商","sccs","50","",""});
		items.add(new String[]{"供应商","gys","50","",""});
		items.add(new String[]{"服务商","fws","50","",""});
		items.add(new String[]{"所属项目","ssxm","50","",""});
		items.add(new String[]{"合同金额","htje","","",""});
		items.add(new String[]{"购买日期","gmrq","","",""});
		items.add(new String[]{"投运日期","tyrq","","",""});
		items.add(new String[]{"出保日期","cbrq","","",""});
		items.add(new String[]{"安全等级","aqdj","","",""});
		items.add(new String[]{"服务级别","fwjb","","",""});
		items.add(new String[]{"所属地域","ssdy","","",""});
		items.add(new String[]{"所属机房","ssjf","","",""});
		items.add(new String[]{"机柜位置","jgwz","","",""});
		items.add(new String[]{"运行状态","yxzt","","",""});
		items.add(new String[]{"备注","bz","","",""});
		items.add(new String[]{"附件","fj","","",""});
		
		return items;
	}

	// 获取全部固件属性
	public static List<String[]> get_wlsb_gjsx_all()
	{
		List<String[]> items = new ArrayList();
		items.addAll(get_wlsb_gjsx());
		items.addAll(get_wlsb_gjsx_jkipdz());
		items.addAll(get_wlsb_gjsx_ywb());
		items.addAll(get_wlsb_gjsx_dy());

		return items;
	}
	// 获取固件属性（简单属性）
	public static List get_wlsb_gjsx()
	{
		List<String[]> items = new ArrayList();
		
		// 固件属性
		
		items.add(new String[]{"CPU板数量","cpubsl","","",""});
		items.add(new String[]{"交换板数量","jhbsl","","",""});		
		items.add(new String[]{"电源插槽数量","dyccsl","","",""});
		items.add(new String[]{"业务版数量","ywbsl","","",""});
		items.add(new String[]{"机架高度","jjgd","","",""});
		items.add(new String[]{"设备规格","sbgg","","",""});	
		items.add(new String[]{"设备净重","sbjz","","",""});
		items.add(new String[]{"IOS版本","iosbb","","",""});	
		
		return items;
	}
	// 获取固件属性（接口IP地址子项）
	public static List get_wlsb_gjsx_jkipdz()
	{
		List<String[]> items = new ArrayList();
		
		// 固件属性.接口IP地址
		items.add(new String[]{"接口IP地址","jkipdz","1","",""});

		items.add(new String[]{"端口号","dkh","2","",""});
		items.add(new String[]{"IP地址","ipdz","2","",""});		
		items.add(new String[]{"子网掩码","zwym","2","",""});
		items.add(new String[]{"备注","bz","2","",""});
		
		return items;
	}
	// 获取固件属性（业务板子项）
	public static List get_wlsb_gjsx_ywb()
	{
		List<String[]> items = new ArrayList();
		
		// 固件属性.业务板
		items.add(new String[]{"业务板","ywb","1","",""});

		items.add(new String[]{"插槽编号","ccbh","2","",""});
		items.add(new String[]{"端口类型","dklx","2","",""});		
		items.add(new String[]{"端口速率","dksl","2","",""});
		items.add(new String[]{"数量","sl","2","",""});
		
		return items;
	}
	// 获取固件属性（电源子项）
	public static List get_wlsb_gjsx_dy()
	{
		List<String[]> items = new ArrayList();
		
		// 固件属性.电源
		items.add(new String[]{"电源","dy","1","",""});

		items.add(new String[]{"插槽编号","ccbh","2","",""});
		items.add(new String[]{"型号","xh","2","",""});		
		items.add(new String[]{"功率","gl","2","",""});
		items.add(new String[]{"接口类型","jklx","2","",""});
		
		return items;
	}
	
	// 获取全部管理属性
	public static List get_wlsb_glsx_all()
	{
		List<String[]> items = new ArrayList();
		items.addAll(get_wlsb_glsx());
		items.addAll(get_wlsb_glsx_glzhxx());
		
		return items;
	}
	//获取管理属性（简单属性）
	public static List get_wlsb_glsx()
	{
		List<String[]> items = new ArrayList();
		
		// 管理属性
		items.add(new String[]{"设备领用单位","sblydw","","",""});
		items.add(new String[]{"设备领用人","sblyr","","",""});
		items.add(new String[]{"联系电话","lyrlxdh","","",""});
		items.add(new String[]{"设备维护单位","xtwhdw","","",""});
		items.add(new String[]{"设备维护人","sbwhr","","",""});
		items.add(new String[]{"联系电话","whrlxdh","","",""});
		
		return items;
	}
	//获取管理属性（管理帐号信息子项）
	public static List get_wlsb_glsx_glzhxx()
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
