package com.skynet.app.workflow.common;

import java.sql.Timestamp;

public class WFTimeDebug
{

	public static void log(String text)
	{
		Timestamp ctime = new Timestamp(System.currentTimeMillis());
		String timeStr = (ctime.getYear() + 1900) + "-" + (ctime.getMonth() + 1)  + "-" + ctime.getDate() + " " + ctime.getHours() + ":" + ctime.getMinutes() + ":" + ctime.getSeconds() + "." + ctime.getNanos()/1000000;
		System.out.println(text + " " + timeStr);
	}
	
	public static void log()
	{
		Timestamp ctime = new Timestamp(System.currentTimeMillis());
		String timeStr = (ctime.getYear() + 1900) + "-" + (ctime.getMonth() + 1)  + "-" + ctime.getDate() + " " + ctime.getHours() + ":" + ctime.getMinutes() + ":" + ctime.getSeconds() + "." + ctime.getNanos()/1000000;
		System.out.println("time: " + timeStr);
	}
	
	public static void log(long time)
	{
		Timestamp ctime = new Timestamp(time);
		System.out.println((ctime.getYear() + 1900) + "-" + (ctime.getMonth() + 1)  + "-" + ctime.getDate() + " " + ctime.getHours() + ":" + ctime.getMinutes() + ":" + ctime.getSeconds() + "." + ctime.getNanos()/1000000);
	}
	
	public static void main(String[] args)
	{
		WFTimeDebug.log();
	}
}
