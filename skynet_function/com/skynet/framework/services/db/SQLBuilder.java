package com.skynet.framework.services.db;
public class SQLBuilder
{
	// ��SQL�����������
	public static String sqlvalues(String[] values, int[] types)
	{
		String sql = new String();
		try
		{
			if (values != null)
			{
				int cols = values.length;
				for (int i = 0; i < cols; i++)
				{
					if (types[i] == 0)
					{
						// ������ֵΪ�ջ���ַ���Ϊ��NULL
						if ((values[i] == null) || (values[i].trim().equals("")))
						{
							sql += "null";
						}
						else
						{
							sql += ("'" + values[i] + "'");
						}
					}
					else
					{
						sql += values[i];
					}
					if (i < cols - 1)
					{
						sql += " , ";
					}
				}
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		return sql;
	}
	// ��̬�����ֵ
	public static String sqlparams(String sql, String[] values, int types[])
	{
		int length = 0;
		String newsql = new String();
		if ((values == null) || (types == null))
		{
			newsql = sql;
		}
		else
		{
			try
			{
				char[] chars = sql.toCharArray();
				length = chars.length;
				String[] strs = new String[length];
				int count = 0;
				for (int i = 0; i < length; i++)
				{
					if (chars[i] == '?')
					{
						if (types[count] == 0)
						{
							// ������ֵΪ�ջ���ַ���Ϊ��NULL
							if ((values[count] == null) || (values[count].trim().equals("")))
							{
								strs[i] = "null";
							}
							else
							{
								strs[i] = ("'" + values[count] + "'");
							}
							count++;
							/*
							strs[i] = "'" + values[count] + "'";
							count++;
							*/
						}
						else
						{
							// ������ֵΪ�ջ���ַ���Ϊ��NULL
							if ((values[count] == null) || (values[count].trim().equals("")))
							{
								strs[i] = "null";
							}
							else
							{
								strs[i] = values[count];
							}
							count++;
							/*
							strs[i] = values[count];
							count++;
							*/
						}
					}
					else
					{
						strs[i] = String.valueOf(chars[i]);
					}
				}
				for (int i = 0; i < length; i++)
				{
					newsql += strs[i];
				}
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
		}
		return newsql;
	}
	// ��̬�����ֵ������Ƿ��滻ֵ��Ǿ��Ƿ��滻
	public static String sqlparams(String sql, String[] values, int[] types, int[] replace)
	{
		int length = 0;
		String newsql = new String();
		if ((values == null) || (types == null) || (replace == null))
		{
			newsql = sql;
		}
		else
		{
			try
			{
				char[] chars = sql.toCharArray();
				length = chars.length;
				String[] strs = new String[length];
				int count = 0;
				for (int i = 0; i < length; i++)
				{
					if (chars[i] == '?')
					{
						if (replace[count] == 0)
						{
							if (types[count] == 0)
							{
								strs[i] = "'" + values[count] + "'";
							}
							else
							{
								strs[i] = values[count];
							}
						}
						else
						{
							strs[i] = String.valueOf(chars[i]);
						}
						count++;
					}
					else
					{
						strs[i] = String.valueOf(chars[i]);
					}
				}
				for (int i = 0; i < length; i++)
				{
					newsql += strs[i];
				}
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
		}
		return newsql;
	}
	/*
	 * ����˵���̬��Ӳ���ֵ	
	 * ����˵���̬��䣬������ƣ�����ֵ������ֵ���ͣ������
	 * ����˵����ز���ֵ�����
	 */
	public static String sqlparams(String sql, String[] names, String[] values, int types[], String[] operates)
	{

		String newsql = sql;
		if ((names == null) || (values == null) || (types == null) || (operates == null))
		{
			newsql = sql;
		}
		else
		{
			try
			{
				for (int i = 0; i < names.length; i++)
				{
					// ������ֵ��Ϊ�գ���Ϊ�ò�����Ч����
					//				if(!((values[i]==null) || (values[i].trim().equals(""))))
					//				if(!((values[i]==null) || (values[i].trim().length()==0)))
					if ((values[i] == null) || (values[i].trim().length() == 0))
					{
					}
					else
					{
						if (types[i] == 0)
						{
							newsql += (" and " + names[i] + " " + operates[i] + " '" + values[i] + "' ");
						}
						else
						{
							newsql += (" and " + names[i] + " " + operates[i] + " " + values[i] + " ");
						}
					}
				}
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
		}
		return newsql;
	}
}
