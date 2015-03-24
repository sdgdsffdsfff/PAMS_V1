package com.skynet.framework.services.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class StringToolKit
{
	// 获取字符串(text)在数组(texts)中出现的位置。
	public static int getTextInArrayIndex(String[] texts, String text)
	{
		int index = -1;
		for (int i = 0; i < texts.length; i++)
		{
			if (texts[i].equals(text))
			{
				index = i;
				break;
			}
		}
		return index;
	}

	// 获取字符串(text)在数组(texts)中出现的所有位置。
	public static int[] getTextInArrayIndexs(String[] texts, String text)
	{
		List list = new ArrayList();
		for (int i = 0; i < texts.length; i++)
		{
			if (texts[i].equals(text))
			{
				list.add(String.valueOf(i));
			}
		}

		int count = list.size();
		int[] indexs = new int[count];
		for (int i = 0; i < count; i++)
		{
			indexs[i] = Types.parseInt((String) list.get(i), 0);
		}

		return indexs;
	}

	// 根据给定的顺序(indexs)抽取数组(texts)的元素组成新数组
	public static String[] getArrayFromArray(String[] texts, int[] indexs)
	{
		int count = indexs.length;
		String[] values = new String[count];

		for (int i = 0; i < count; i++)
		{
			values[i] = texts[indexs[i]];
		}

		return values;
	}

	// 
	public static int[] getArrayInArray(String[] stexts, String[] ttexts)
	{
		int scount = stexts.length;
		int tcount = ttexts.length;

		int[] values = new int[scount];
		for (int i = 0; i < scount; i++)
		{
			values[i] = 0;
		}

		for (int i = 0; i < tcount; i++)
		{
			for (int j = 0; j < scount; j++)
			{
				if (ttexts[i].equals(stexts[j]))
				{
					values[j] = 1;
					break;
				}
			}
		}

		return values;
	}

	public static String splitFileExtName(String filename)
	{
		String filename_ext = new String();
		int index = 0;
		if ((index = filename.lastIndexOf(".")) > 0)
		{
			filename_ext = filename.substring(index + 1);
		}
		return filename_ext;
	}

	public static String splitFilePreName(String filename)
	{
		String filename_begin = new String();
		int index = 0;
		if ((index = filename.lastIndexOf(".")) > 0)
		{
			filename_begin = filename.substring(0, index);
		}
		return filename_begin;
	}

	public static String formatText(String text)
	{
		String webText = new String();
		if (text == null)
		{
			webText = new String();
		}
		else
		{
			webText = text.trim();
		}
		return webText;
	}

	public static String formatText(String text, String defaultText)
	{
		String webText = new String();
		if (text == null || text.trim().length() == 0)
		{
			webText = defaultText;
		}
		else
		{
			webText = text.trim();
		}
		return webText;
	}

	public static String trim(String data)
	{
		if (data != null)
		{
			data = data.trim();
		}
		return data;
	}

	public static String[] split(String key, String sign)
	{
		try
		{
			int index = 0;

			Vector v = new Vector();
			if ((key == null) || (key.length() == 0))
			{
				return null;
			}
			else
			{
				key = key.trim();
				int slen = 0;
				if (sign != null)
				{
					sign = sign.trim();
					slen = sign.length();
				}
				if (!key.endsWith(sign))
				{
					key += sign;
				}
				while ((index = key.indexOf(sign)) != -1)
				{
					String str = key.substring(0, index);
					v.addElement(str);
					key = (key.substring(index + slen)).trim();
				}
			}
			int size = v.size();
			if (size > 0)
			{
				String[] keys = new String[size];
				for (int i = 0; i < size; i++)
				{
					keys[i] = (String) v.elementAt(i);
				}
				return keys;
			}
			else
			{
				return null;
			}
		}
		catch (Exception e)
		{
			return null;
		}
	}

	public static String join_left(int[] values, String sign)
	{
		String value = new String();
		int count = values.length;
		for (int i = 0; i < count; i++)
		{
			value += values[i];
			if (i < (count - 1))
			{
				value += sign;
			}

		}
		return value;
	}

	public static String join_left(String[] values, String sign)
	{
		String value = new String();
		for (int i = 0; i < values.length; i++)
		{
			value += formatText(values[i]) + sign;
		}
		return value;
	}

	public static String join_left(String[] values)
	{
		String value = new String();
		for (int i = 0; i < values.length; i++)
		{
			value += formatText(values[i]);
		}
		return value;
	}

	public static String join_right(String[] values)
	{
		String value = new String();
		for (int i = values.length - 1; i >= 0; i--)
		{
			value += formatText(values[i]);
		}
		return value;
	}

	public static boolean isBlank(String value)
	{
		if (value == null)
		{
			return true;
		}
		else if (value.trim().equals(""))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public static String replaceStr(String str, String s, String d)
	{
		if ((s != null) && (s.equals(d)))
		{
			return str;
		}
		String newstr = new String();
		int pos = -1;
		while ((pos = str.indexOf(s)) != -1)
		{
			newstr = str.substring(0, pos) + d + str.substring(str.length());
		}
		return newstr;
	}

	public static String replaceNStr(String str, String s, String d)
	{
		if ((s != null) && (s.equals(d)))
		{
			return str;
		}
		String newstr = new String();
		newstr = str;
		int pos = -1;
		while ((pos = newstr.indexOf(s)) != -1)
		{
			String s0 = newstr.substring(0, pos);
			String s1 = newstr.substring(pos + s.length());
			newstr = s0 + d + s1;
		}
		return newstr;
	}

	public static String firstUpper(String str)
	{
		String sstr = str;
		String dstr = str;
		if ((str != null) && (str.length() > 0))
		{
			String schar = String.valueOf(sstr.charAt(0)).toUpperCase();
			dstr = schar + sstr.substring(1);
		}
		return dstr;
	}

	// 右对齐字符串
	public static String alignLeftText(String str, String blank, int size)
	{
		String value = formatText(str);
		int len = value.length();
		int i = len;
		while (i < size)
		{
			value += blank;
			i++;
		}
		return value;
	}

	//
	public static String[] getListToArray(List list)
	{
		int count = list.size();

		if (count == 0)
		{
			return null;
		}

		String[] value = new String[count];
		for (int i = 0; i < list.size(); i++)
		{
			value[i] = list.get(i).toString();
		}

		return value;
	}

	//
	public static String[] joinArrays(String[][] arrays)
	{
		int count = arrays.length;
		String value = new String();
		for (int i = 0; i < count; i++)
		{
			String[] carray = (String[]) arrays[i];
			if (carray == null)
			{
				continue;
			}
			value += join_left((String[]) arrays[i], ",");
		}
		return split(value, ",");
	}

	public static String[] splitbysize(String key, String sign, int n)
	{
		String strs[] = new String[n];
		for (int i = 0; i < n - 1; i++)
		{
			int index = key.indexOf(sign);
			if (index == 0)
			{
				strs[i] = new String();
			}
			else
			{
				strs[i] = key.substring(0, index);
			}
			key = key.substring(index + 1);
		}
		strs[n - 1] = key;
		return strs;
	}

	public static List sortKey(String[] keys)
	{
		List newkeys = new ArrayList();
		if (keys != null)
		{
			for (int i = 0; i < keys.length; i++)
			{
				boolean sign = true;
				int pos = -1;
				for (int j = 0; j < newkeys.size(); j++)
				{
					String ckey = (String) newkeys.get(j);
					if ((keys[i] != null) && (keys[i].equals(ckey)))
					{
						sign = false;
						break;
					}
				}
				if (sign)
				{
					newkeys.add(keys[i]);
				}
			}
		}
		return newkeys;
	}

	public static List sortKey(List keys)
	{
		List newkeys = new ArrayList();
		if (keys != null)
		{
			for (int i = 0; i < keys.size(); i++)
			{
				boolean sign = true;
				int pos = -1;
				for (int j = 0; j < newkeys.size(); j++)
				{
					String ckey = (String) newkeys.get(j);
					if (((String) keys.get(i) != null) && (((String) keys.get(i)).equals(ckey)))
					{
						sign = false;
						break;
					}
				}
				if (sign)
				{
					newkeys.add((String) keys.get(i));
				}
			}
		}
		return newkeys;
	}
	
	public static int findKeyPos(String key, List keys)
	{
		int pos = -1;
		if(key!=null)
		{
			for(int i=0;i<keys.size();i++)
			{
				if(key.equals((String)keys.get(i)))
				{
					pos = i;
					break;
				}
			}
		}
		return pos;
	}	

}
