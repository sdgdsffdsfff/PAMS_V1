package com.ray.dev.vinit.config;

import java.util.ArrayList;
import java.util.List;

public class TemplateZJ
{
	public static List get_zj()
	{
		List<String[]> items = new ArrayList();
		items.addAll(get_zj_jbsx_all());
		items.addAll(get_zj_gjsx_all());
		items.addAll(get_zj_glsx_all());
		return items;
	}
	
	// 获取全部基本属性
	public static List get_zj_jbsx_all()
	{
		List<String[]> items = new ArrayList();
		

		items.add(new String[]{"设备序列号","sbxlh","","",""});
		items.add(new String[]{"设备名称","sbxlh","","",""});	
		items.add(new String[]{"ERP设备号","sbxlh","","",""});
		items.add(new String[]{"俗称","sbxlh","","",""});			
		items.add(new String[]{"资产编号","sbxlh","","",""});
		items.add(new String[]{"国网编号","sbxlh","","",""});			
		items.add(new String[]{"内外网标识","sbxlh","","",""});
		items.add(new String[]{"设备类型","sbxlh","","",""});	
		items.add(new String[]{"设备品牌","sbxlh","","",""});
		items.add(new String[]{"设备型号","sbxlh","","",""});			
		items.add(new String[]{"生产厂商","sbxlh","","",""});
		items.add(new String[]{"供应商","sbxlh","","",""});
		items.add(new String[]{"服务商","sbxlh","","",""});
		items.add(new String[]{"所属项目","sbxlh","","",""});		
		items.add(new String[]{"购买日期","gmrq","","",""});
		items.add(new String[]{"合同金额","htje","","",""});			
		items.add(new String[]{"投运日期","tyrq","","",""});
		items.add(new String[]{"出保日期","cbrq","","",""});	
		items.add(new String[]{"安全等级","aqdj","","",""});
		items.add(new String[]{"服务级别","fwdj","","",""});	
		items.add(new String[]{"所属地域","ssdy","","",""});
		items.add(new String[]{"所属机房","ssjf","","",""});
		items.add(new String[]{"机柜位置","jgwz","","",""});
		items.add(new String[]{"运行状态","yyzt","","",""});
		items.add(new String[]{"备注","bz","","",""});
		items.add(new String[]{"附件","fj","","",""});
		
		return items;
	}

	// 获取全部固件属性
	public static List<String[]> get_zj_gjsx_all()
	{
		List<String[]> items = new ArrayList();
		items.addAll(get_zj_gjsx());
		items.addAll(get_zj_gjsx_cpu());
		items.addAll(get_zj_gjsx_nck());
		items.addAll(get_zj_gjsx_yp());
		items.addAll(get_zj_gjsx_dy());
		items.addAll(get_zj_gjsx_wk());
		items.addAll(get_zj_gjsx_hba());

		return items;
	}
	
		
	// 获取全部管理属性
	public static List<String[]> get_zj_glsx_all()
	{
		List<String[]> items = new ArrayList();
		items.addAll(get_zj_glsx());
		items.addAll(get_zj_glsx_glzhxx());

		return items;
	}
	
	// 获取管理属性（简单属性）
	public static List get_zj_glsx()
	{
		List<String[]> items = new ArrayList();
		
		// 管理属性	
		items.add(new String[]{"设备领用单位","sblydw","","",""});	
		items.add(new String[]{"设备领用人","sblyr","","",""});
		items.add(new String[]{"领用人联系电话","lyrlxdh","","",""});	
		items.add(new String[]{"设备维护单位","sbwhdw","","",""});
		items.add(new String[]{"设备维护人","sbwhr","","",""});
		items.add(new String[]{"维护人联系电话","wxrlxdh","","",""});	
		
		return items;
	}	
	
	// 获取管理属性(管理账号信息子项)
	public static List get_zj_glsx_glzhxx()
	{
		List<String[]> items = new ArrayList();
		
		// 管理属性	.管理账号信息
		items.add(new String[]{"管理账号信息","glzhxx","1","",""});
		
		items.add(new String[]{"账号名称","zhmc","2","",""});	
		items.add(new String[]{"账号说明","zhsm","2","",""});
		items.add(new String[]{"账号使用人","zhsyr","2","",""});	
		items.add(new String[]{"备注","bz","2","",""});

		return items;
	}
	
	// 获取固件属性（简单属性）
	public static List get_zj_gjsx()
	{
		List<String[]> items = new ArrayList();
		
		// 固件属性
		items.add(new String[]{"CPU插槽数量","cpuccsl","","",""});
		items.add(new String[]{"内存插槽数量","ncccsl","","",""});		
		items.add(new String[]{"硬盘插槽数量","ypccsl","","",""});
		items.add(new String[]{"电源插槽数量","dyccsl","","",""});
		items.add(new String[]{"RAID卡型号","raidkxh","","",""});
		items.add(new String[]{"RAID类型","raidlx","","",""});	
		items.add(new String[]{"机架高度","jjgd","","",""});
		items.add(new String[]{"设备规格","sbgg","","",""});	
		items.add(new String[]{"设备净重","sbjz","","",""});
		
		return items;
	}
	
	
	// 获取固件属性（CPU子项）
	public static List get_zj_gjsx_cpu()
	{
		List<String[]> items = new ArrayList();
		
		// 固件属性.CPU
		items.add(new String[]{"CPU","cpu","1","",""});
		
		items.add(new String[]{"插槽编号","ccbh","2","",""});
		items.add(new String[]{"CPU型号","cpuxh","2","",""});
		items.add(new String[]{"CPU主频","cpuzp","2","",""});
		
		return items;
	}
	
	// 获取固件属性（内存卡子项）
	public static List get_zj_gjsx_nck()
	{
		List<String[]> items = new ArrayList();
		
		// 固件属性.内存卡
		items.add(new String[]{"内存卡","nck","1","",""});		
		
		items.add(new String[]{"插槽编号","ccbh","2","",""});	
		items.add(new String[]{"容量","rl","2","",""});
		items.add(new String[]{"类型","lx","2","",""});	
		
		return items;
	}
	
	// 获取固件属性（硬盘子项）
	public static List get_zj_gjsx_yp()
	{
		List<String[]> items = new ArrayList();
		
		// 固件属性.硬盘	
		items.add(new String[]{"硬盘","yp","1","",""});	
		
		items.add(new String[]{"插槽编号","ccbh","2","",""});	
		items.add(new String[]{"容量","rl","2","",""});
		items.add(new String[]{"类型","lx","2","",""});		
		
		return items;
	}
	
	// 获取固件属性（电源子项）
	public static List get_zj_gjsx_dy()
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
	
	// 获取固件属性（网卡子项）	
	public static List get_zj_gjsx_wk()
	{
		List<String[]> items = new ArrayList();
		
		// 固件属性.网卡
		items.add(new String[]{"网卡","wk","1","",""});		
		
		items.add(new String[]{"编号","bh","2","",""});	
		items.add(new String[]{"型号","xh","2","",""});
		items.add(new String[]{"速率","sl","2","",""});	
		items.add(new String[]{"IP地址","ipdz","2","",""});
		items.add(new String[]{"虚IP地址","xipdz","2","",""});
		
		return items;
	}
	
	// 获取固件属性（HBA卡）	
	public static List get_zj_gjsx_hba()
	{
		List<String[]> items = new ArrayList();
		
		// 固件属性.HBA卡	
		items.add(new String[]{"HBA卡","hbak","1","",""});		
		
		items.add(new String[]{"编号","bh","2","",""});	
		items.add(new String[]{"型号","xh","2","",""});
		items.add(new String[]{"wwn号","wwnh","2","",""});	
		items.add(new String[]{"端口数量","dksl","2","",""});
		items.add(new String[]{"速率","sl","2","",""});
		
		return items;
	}
	

		
	
}
