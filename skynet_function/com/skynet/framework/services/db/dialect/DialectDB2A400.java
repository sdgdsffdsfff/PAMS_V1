package com.skynet.framework.services.db.dialect;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.skynet.framework.common.generator.FormatKey;

public class DialectDB2A400 extends DialectDB2
{
	public DialectDB2A400()
	{
		DialectContext.idialect = this;
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
		+ "-"
		+ FormatKey.format(String.valueOf(ctime.get(Calendar.HOUR_OF_DAY)), 2)
		+ "."
		+ FormatKey.format(String.valueOf(ctime.get(Calendar.MINUTE)), 2)
		+ "."
		+ FormatKey.format(String.valueOf(ctime.get(Calendar.SECOND)), 2);
		
		System.out.println(timeStr);
		
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
		+ "-"
		+ FormatKey.format(String.valueOf(ctime.get(Calendar.HOUR_OF_DAY)), 2)
		+ "."
		+ FormatKey.format(String.valueOf(ctime.get(Calendar.MINUTE)), 2)
		+ "."
		+ FormatKey.format(String.valueOf(ctime.get(Calendar.SECOND)), 2);
		
		return timeStr;
	}	

}
