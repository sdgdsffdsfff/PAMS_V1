package com.skynet.framework.services.db.dialect;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.skynet.framework.common.generator.FormatKey;

public class DialectDB2 implements IDialect
{
	public DialectDB2()
	{
		DialectContext.idialect = this;
	}
	
	public String func_isnull(String fieldname, String fieldvalue)
	{
		return " case when " + fieldname + " + is null then " + fieldvalue + " else " + fieldname + " end ";
	}

	public String func_length(String fieldname)
	{
		return " length(" + fieldname + ") ";
	}

	public String func_currenttime()
	{
		return " current timestamp ";
	}
	
	public String func_varchar(String fieldname)
	{
		return " varchar(" + fieldname + ") "; 
	}	
	
	public String func_date(String fieldname)
	{
		return null;
	}
	
	public String func_date(String fieldname, String fieldformat)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String get_database_class()
	{
		return IDialect.database_class_db2;
	}
	
	public String value_currenttime()
	{
		String timeStr = new String();
		Calendar ctime = new GregorianCalendar();
		
		timeStr = (ctime.get(Calendar.YEAR))
		+ "-"
		+ FormatKey.format(String.valueOf(ctime.get(Calendar.MONTH) + 1), 2)
		+ "-"
		+ FormatKey.format(String.valueOf(ctime.get(Calendar.DATE)), 2)
		+ " "
		+ FormatKey.format(String.valueOf(ctime.get(Calendar.HOUR_OF_DAY)), 2)
		+ ":"
		+ FormatKey.format(String.valueOf(ctime.get(Calendar.MINUTE)), 2)
		+ ":"
		+ FormatKey.format(String.valueOf(ctime.get(Calendar.SECOND)), 2);
		
		return timeStr;
	}
	
	public String value_currenttime(Calendar ctime)
	{
		String timeStr = new String();
		
		timeStr = (ctime.get(Calendar.YEAR))
		+ "-"
		+ FormatKey.format(String.valueOf(ctime.get(Calendar.MONTH) + 1), 2)
		+ "-"
		+ FormatKey.format(String.valueOf(ctime.get(Calendar.DATE)), 2)
		+ " "
		+ FormatKey.format(String.valueOf(ctime.get(Calendar.HOUR_OF_DAY)), 2)
		+ ":"
		+ FormatKey.format(String.valueOf(ctime.get(Calendar.MINUTE)), 2)
		+ ":"
		+ FormatKey.format(String.valueOf(ctime.get(Calendar.SECOND)), 2);
		
		return timeStr;
	}

	public String value_date(String value, String dateformat)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public String trans_format_date(String format)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String exp_not_null(String fieldname)
	{
		return "(" + fieldname + " is null or " + fieldname + " = '')" ;
	}

	public String exp_null(String fieldname)
	{
		return "(" + fieldname + " is not null and " + fieldname + " <> '')" ;
	}

	public String func_char(String fieldname)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String func_number(String fieldname)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String func_substring(String fieldname, int startindex, int length)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String func_substring(String fieldname, String startindex, String length)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
