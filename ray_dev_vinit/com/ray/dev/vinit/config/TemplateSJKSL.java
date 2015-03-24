package com.ray.dev.vinit.config;

import java.util.ArrayList;
import java.util.List;

public class TemplateSJKSL
{
	public static List get_sjksl()
	{
		List<String[]> items = new ArrayList();
		items.addAll(get_sjksl_jbsx_all());
		items.addAll(get_sjksl_glsx_all());
		return items;
	}

	// 获取全部基本属性
	public static List<String[]> get_sjksl_jbsx_all()
	{
		List<String[]> items = new ArrayList();
		items.addAll(get_sjksl_jbsx());

		return items;
	}

	// 获取全部管理属性
	public static List<String[]> get_sjksl_glsx_all()
	{
		List<String[]> items = new ArrayList();
		items.addAll(get_sjksl_glsx());
		items.addAll(get_sjksl_glsx_glzhxx());

		return items;
	}

	// 获取基本属性(简单属性)
	public static List get_sjksl_jbsx()
	{
		List<String[]> items = new ArrayList();

		items.add(new String[]{ "俗称", "sc", "", "", "" });
		items.add(new String[]{ "数据库名", "sjkm", "", "", "" });
		items.add(new String[]{ "运行环境", "yxhj", "", "", "" });
		items.add(new String[]{ "本地备份方式", "bdbffs", "", "", "" });
		items.add(new String[]{ "异地容灾方式", "ydrzfs", "", "", "" });
		items.add(new String[]{ "备份周期", "bfzq", "", "", "" });
		items.add(new String[]{ "备份方式", "bffs", "", "", "" });
		items.add(new String[]{ "备注", "bz", "", "", "" });
		items.add(new String[]{ "附件", "fj", "", "", "" });

		return items;
	}

	// 获取管理属性（简单属性）
	public static List get_sjksl_glsx()
	{
		List<String[]> items = new ArrayList();

		// 管理属性
		items.add(new String[]{ "维护单位", "whdw", "", "", "" });
		items.add(new String[]{ "维护人", "whr", "", "", "" });
		items.add(new String[]{ "联系电话", "lxdh", "", "", "" });

		return items;
	}

	// 获取管理属性(管理账号信息子项)
	public static List get_sjksl_glsx_glzhxx()
	{
		List<String[]> items = new ArrayList();

		// 管理属性.管理账号信息
		items.add(new String[]{ "管理账号信息", "glzhxx", "1", "", "" });

		items.add(new String[]{ "账号名称", "zhmc", "2", "", "" });
		items.add(new String[]{ "账号说明", "zhsm", "2", "", "" });
		items.add(new String[]{ "账号使用人", "zhsyr", "2", "", "" });

		return items;
	}
}
