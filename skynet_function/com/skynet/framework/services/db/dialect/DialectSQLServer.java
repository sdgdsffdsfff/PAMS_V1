package com.skynet.framework.services.db.dialect;

import java.util.Calendar;

public class DialectSQLServer implements IDialect
{
	public String func_char(String fieldname)
	{
		return "convert(char, " + fieldname + ")";
	}

	public String func_number(String fieldname)
	{
		return "convert(int, " + fieldname + ")";
	}
	
	public String func_substring(String fieldname, int startindex, int length)
	{
		return "substring(" + fieldname + ", " + startindex + ", " + length + ")";
	}

	public String func_substring(String fieldname, String startindex, String length)
	{
		return "substring(" + fieldname + ", " + startindex + ", " + length + ")";
	}

	public DialectSQLServer()
	{
		DialectContext.idialect = this;
	}
	
	public String func_isnull(String fieldname, String fieldvalue)
	{
		return "isnull(" + fieldname + ", " + fieldvalue + ")";
	}

	public String func_length(String fieldname)
	{
		return "len(" + fieldname + ")";
	}

	public String func_currenttime()
	{
		return "current_timestamp";
	}
	
	public String get_database_class()
	{
		return IDialect.database_class_sqlserver;
	}

	public String value_currenttime()
	{
		// TODO Auto-generated method stub
		return "current_datetime";
	}

	public String func_varchar(String fieldname)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public String func_date(String fieldname)
	{
		return null;
	}
	
	public String func_date(String fieldname, String dateformat)
	{
		return fieldname;
	}

	public String value_currenttime(Calendar ctime)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public String value_date(String value, String dateformat)
	{
		return "'" + value + "'";
	}

	public String trans_format_date(String format)
	{
		return format;
	}
	
	public String exp_null(String fieldname)
	{
		return "(" + fieldname + " is null or " + fieldname + " = '')" ;
	}	
	
	public String exp_not_null(String fieldname)
	{
		return "(" + fieldname + " is not null and " + fieldname + " <> '')" ;
	}	
	

}
