package com.ray.dev.vinit.query;

import java.lang.reflect.Field;

public class Temp
{
	public static void main(String[] args) throws Exception
	{
		StringBuffer sql = new StringBuffer();

		Field[] fields = Class.forName("com.pams.entity.InfoShare").getDeclaredFields();
		for (int i = 0; i < fields.length; i++)
		{
			sql.append("\"" + fields[i].getName() + "\",");
		}

		System.out.println(sql);

		sql = new StringBuffer();
		for (int i = 0; i < fields.length; i++)
		{
			if (fields[i].getType().equals(String.class))
			{
				sql.append("\"0\",");
			}
			else
			{
				sql.append("\"1\",");
			}
		}

		System.out.println(sql);
	}

}
