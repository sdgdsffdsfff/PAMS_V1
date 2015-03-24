package com.skynet.framework.common.generator;
public class FormatKey
{
	public static String format(String value, int length) 
	{
		String fvalue = "";
		if(value!=null)
		{
			value = value.trim();
			for(int i=0;i<length - value.length(); i++)			
			{
				fvalue += "0";
			}
			fvalue = fvalue + value;
		}
		return fvalue;
	}
	
	public static String format(int intvalue, int length) 
	{
		String fvalue = "";
		String value = String.valueOf(intvalue);
		fvalue = format(value, length);
		return fvalue;
	}

}
