package com.skynet.framework.common.generator;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.skynet.framework.spec.GlobalConstants;
public class TimeGenerator
{
	public static String getTimeStr()
	{
		String timeStr = new String();
		
		Calendar ctime = new GregorianCalendar();
		
		timeStr = getTimeStr(ctime);
		
		return timeStr;
	}
	
	public static String getTimeStr(Calendar ctime)
	{
		String timeStr = new String();
		
		if(GlobalConstants.database_type_sqlserver.equals(GlobalConstants.database_type_sqlserver))
		{
			
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
			+ FormatKey.format(String.valueOf(ctime.get(Calendar.SECOND)), 2)
			+ "."
			+ (ctime.get(Calendar.MILLISECOND));
		}
		else
		if(GlobalConstants.database_type_db2.equals(GlobalConstants.database_type_sqlserver))
		{
			
		}
		
		return timeStr;
	}
	
	
	public static String getTimeStr(String tablename)
	{
		String timeStr = getTimeStr();
		return timeStr;
	}
	

	public static String getDateStr()
	{
		Calendar ctime = new GregorianCalendar();
		String timeStr = getDateStr(ctime);
		return timeStr;
	}

	public static String getSimpleDateStr()
	{
		Calendar ctime = new GregorianCalendar();
		String timeStr = getSimpleDateStr(ctime);
		return timeStr;
	}
	
	public static String getSimpleShortDateStr()
	{
		Calendar ctime = new GregorianCalendar();
		String timeStr = getSimpleDateStr(ctime);
		return timeStr.substring(2);
	}
	
	
	public static String getSimpleDateStr(Calendar ctime)
	{
		String timeStr = (ctime.get(Calendar.YEAR)) + FormatKey.format(String.valueOf(ctime.get(Calendar.MONTH) + 1), 2) + FormatKey.format(String.valueOf(ctime.get(Calendar.DATE)), 2);
		return timeStr;
	}
	
	public static String getDateStr(Calendar ctime)
	{
		String timeStr = (ctime.get(Calendar.YEAR)) + "-" + FormatKey.format(String.valueOf(ctime.get(Calendar.MONTH) + 1), 2) + "-" + FormatKey.format(String.valueOf(ctime.get(Calendar.DATE)), 2);
		return timeStr;
	}
	

	public static String getDateStr(int days)
	{
		Calendar ctime = new GregorianCalendar();
		ctime.add(Calendar.DATE, days);

		String timeStr = (ctime.get(Calendar.YEAR)) + "-" + FormatKey.format(String.valueOf(ctime.get(Calendar.MONTH) + 1), 2) + "-" + FormatKey.format(String.valueOf(ctime.get(Calendar.DATE)), 2);
		return timeStr;

	}
	
	public static String getDateStr(String date, int days)
	{
		Calendar ctime = new GregorianCalendar();
		ctime.setTime(new Date(Date.parse(date)));
		ctime.add(Calendar.DATE, days);

		String timeStr = (ctime.get(Calendar.YEAR)) + "-" + FormatKey.format(String.valueOf(ctime.get(Calendar.MONTH) + 1), 2) + "-" + FormatKey.format(String.valueOf(ctime.get(Calendar.DATE)), 2);
		return timeStr;

	}
	
	//获取周的开始日期
	public static String pub_week_begin_date(String cdate, int nums) throws Exception
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date adate = df.parse(cdate);
		
		System.out.println("day:" + adate.getDay());
		
		Date bdate = new Date(adate.getYear(), adate.getMonth(), adate.getDate() - adate.getDay() + 1);
		bdate = new Date(bdate.getYear(), bdate.getMonth(), bdate.getDate() + (nums * 7));
		
		return df.format(bdate);
	}
	
	//获取周的结束日期
	public static String pub_week_end_date(String cdate, int nums) throws Exception
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date adate = df.parse(cdate);
		
		Date bdate = new Date(adate.getYear(), adate.getMonth(), adate.getDate() + (6 - adate.getDay()) + 1);
		bdate = new Date(bdate.getYear(), bdate.getMonth(), bdate.getDate() + (nums * 7));	
		
		return df.format(bdate);
	}
	
	public static String pub_work_week_begin_date(String cdate, int sys_week, int nums) throws Exception
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date adate = df.parse(cdate);
		
		if(adate.getDay() < sys_week)
		{
			int days = adate.getDate() - adate.getDay() - 2;
			adate.setDate(days);
		}
		else
		{
			int days = adate.getDate() - adate.getDay() + 5;
			adate.setDate(days);
		}
		
		adate = new Date(adate.getYear(), adate.getMonth(), adate.getDate() + (nums * 7));	

		return df.format(adate);	
	}
	
	public static String pub_work_week_end_date(String cdate, int sys_week, int nums) throws Exception
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date adate = df.parse(cdate);
		
		if(adate.getDay() < sys_week)
		{
			int days = adate.getDate() + (6 - adate.getDay()) - 2;
			adate.setDate(days);
		}
		else
		{
			int days = adate.getDate() + (6 - adate.getDay()) + 5;
			adate.setDate(days);
		}
		
		adate = new Date(adate.getYear(), adate.getMonth(), adate.getDate() + (nums * 7));	
		
		return df.format(adate);	
	}
	
	public static void main(String[] args) throws Exception
	{
		System.out.println(pub_work_week_begin_date("2011-09-21", 5, 0));
		System.out.println(pub_work_week_end_date("2011-09-21", 5, 1));
	}	

}
