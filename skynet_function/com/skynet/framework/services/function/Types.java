package com.skynet.framework.services.function;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Types
{
	public static Object parse(Class target, String value)
	{
		if(target.equals(String.class))
		{
			return value;
		}
		else
		if(target.equals(Integer.class))
		{
			return new Integer(parseInt(value, 0));
		}
		else
		if(target.equals(Float.class))
		{
			return new Float(parseFloat(value, 0));
		}		
		else
		if(target.equals(Date.class))
		{
			return parseSqlDate(value);
		}
		else
		{
			return new Object();
		}
	}
	
	public static int parseInt(String source, int def_value)
	{
		int value = def_value;
		try
		{
			value = Integer.parseInt(source);
		}
		catch (Exception e)
		{
			value = def_value;
		}
		return value;
	}
	
	public static float parseFloat(String source, float def_value)
	{
		float value = def_value;
		try
		{
			value = Float.parseFloat(source);
		}
		catch (Exception e)
		{
			value = def_value;
		}
		return value;
	}
	
	public static boolean parseBoolean(String source, boolean def_value)
	{
		boolean value = def_value;
		try
		{
			value = Boolean.getBoolean(source);
		}
		catch (Exception e)
		{
			value = def_value;
		}
		return value;
	}
	
	
	public static Date parseSqlDate(String source)
	{
		Date value = null;
		try
		{
			value = Date.valueOf(source);
		}
		catch(Exception e)
		{
			value = null;						
		}
		return value;
	}
	
	
	public static Calendar parseTimeCalendar(String source)
	{
		Calendar value = new GregorianCalendar();	
		try
		{
			int year = Types.parseInt(source.substring(0, 4), 1900);
			int month = Types.parseInt(source.substring(5, 7), 1);
			int date = Types.parseInt(source.substring(8, 10), 1);
			int hour = Types.parseInt(source.substring(11, 13), 0);
			int minute = Types.parseInt(source.substring(14, 16), 0);
			int second = Types.parseInt(source.substring(17, 19), 0);
			
			value = new GregorianCalendar(year, month-1, date, hour, minute, second);
			
			return value;
		}
		catch(Exception e)
		{
			value = new GregorianCalendar();	
		}
		return value;
	}
	
	public static Calendar parseTimeCalendar(String source, int minutes)
	{
		Calendar value = new GregorianCalendar();	
		try
		{
			int year = Types.parseInt(source.substring(0, 4), 1900);
			int month = Types.parseInt(source.substring(5, 7), 1);
			int date = Types.parseInt(source.substring(8, 10), 1);
			int hour = Types.parseInt(source.substring(11, 13), 0);
			int minute = Types.parseInt(source.substring(14, 16), 0);
			int second = Types.parseInt(source.substring(17, 19), 0);
			
			value = new GregorianCalendar(year, month-1, date, hour, minute, second);
			
			value.add(Calendar.MINUTE, minutes);
			
			return value;
		}
		catch(Exception e)
		{
			value = new GregorianCalendar();	
			value.add(Calendar.MINUTE, minutes);
		}
		return value;
	}
	
	
	public static String parseString(Object source)
	{
		String value = null;
		
		if(source == null)
		{
			value = null;
		}
		else
		{
			value = String.valueOf(source);
		}
		return value;
	}
	
}
