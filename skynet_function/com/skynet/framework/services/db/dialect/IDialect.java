package com.skynet.framework.services.db.dialect;

import java.util.Calendar;

public interface IDialect
{
	public static String database_class_db2 = "DB2";
	public static String database_class_db2_400 = "DB2_400";
	public static String database_class_sqlserver = "SQLSERVER";
	public static String database_class_oracle = "ORACLE";
	
	
	public static String format_date_default = "yyyy-mm-dd";
	public static String format_time_default = "yyyy-mm-dd hh:mi:ss";
	
	public String get_database_class();
	
	//求长度
	public String func_length(String fieldname);
	
	//转换为字符串函数
	public String func_varchar(String fieldname);	
	
	//转换为日期函数
	public String func_date(String fieldname);	

	//转换为日期格式
	public String func_date(String fieldname, String dateformat);	
	
	//转换为字符串函数
	public String func_char(String fieldname);

	//转换为数值函数
	public String func_number(String fieldname);	
	
	//转换为数值函数
	public String func_substring(String fieldname, int startindex, int length);

	//转换为数值函数	
	public String func_substring(String fieldname, String startindex, String length);	
	
	//判断是否为空函数
	public String func_isnull(String fieldname, String fieldvalue);
	
	//求系统当前日期
	public String func_currenttime();
	
	//计算系统当前时间值
	public String value_currenttime();
	
    //日期函数值
	public String value_date(String value, String dateformat);
	
	//计算系统当前时间值
	public String value_currenttime(Calendar ctime);
	
	//转换时间格式
	public String trans_format_date(String format);
	
	//判断字段为空表达式
	public String exp_null(String fieldname);
	
    //判断字段不为空表达式
	public String exp_not_null(String fieldname);
	
}