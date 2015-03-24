package com.skynet.framework.services.db.dialect;

import java.util.Calendar;

import com.skynet.framework.services.function.StringToolKit;

public class DialectOracle implements IDialect
{
	protected static String format_time = "yyyy-mm-dd hh24:mi:ss";  

	public String func_currenttime()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String func_isnull(String fieldname, String fieldvalue)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String func_length(String fieldname)
	{
		return "length(" + fieldname + ")";
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
		return "to_date(" + fieldname + ",'" + trans_format_date(dateformat) + ")";
	}
	
	public String func_char(String fieldname)
	{
		return "to_char(" + fieldname + ")";
	}
	
	public String func_number(String fieldname)
	{
		return "to_number(" + fieldname + ")";
	}
	
	public String func_substring(String fieldname, int startindex, int length)
	{
		return "substr(" + fieldname + ", " + startindex + ", " + length + ")";
	}
	
	public String func_substring(String fieldname, String startindex, String length)
	{
		return "substr(" + fieldname + ", " + startindex + ", " + length + ")";
	}	

	public String value_date(String value, String dateformat)
	{

		return "to_date('" + value + "','" + trans_format_date(dateformat) + "')";
	}

	public String get_database_class()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String value_currenttime()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String value_currenttime(Calendar ctime)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public String trans_format_date(String format)
	{
		String cformat = format;
		
		if(StringToolKit.isBlank(cformat))
		{
			cformat = IDialect.format_date_default;
			return format;
		}
		
		if(IDialect.format_time_default.equals(cformat))
		{
			cformat = DialectOracle.format_time;
		}
		
		return cformat;
	}
	
	public String exp_null(String fieldname)
	{
		return fieldname + " is null ";
	}	
	
	public String exp_not_null(String fieldname)
	{
		return fieldname + " is not null ";
	}	

}
