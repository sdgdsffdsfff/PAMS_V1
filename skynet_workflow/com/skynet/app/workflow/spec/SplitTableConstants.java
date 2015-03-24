package com.skynet.app.workflow.spec;

public class SplitTableConstants
{
	public static String config_split = "Y";
	public static String config_nosplit = "N";
	
	public static String getSplitTable(String prefixtable, String tableid)
	{
		String table = new String();
		if(getSplit().equals(config_split))
		{
			table = prefixtable + "_" + getTable(tableid);
		}
		else
		{
			table = prefixtable;
		}
		return table.toLowerCase();
	}
	
	public static String getTable(String tableid)
	{
		return tableid.substring(tableid.indexOf("T") + 2);
	}
	
	// 是否存储分表
	public static String getSplit()
	{
		return config_nosplit;
	}
	
	public static void main(String[] args)
	{
		System.out.println(getTable("T_SENDFILE"));
	}
}
