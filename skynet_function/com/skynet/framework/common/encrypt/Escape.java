package com.skynet.framework.common.encrypt;

import com.skynet.framework.services.function.StringToolKit;

public class Escape
{
	public static String escape(String src)
	{
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++)
		{
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256)
			{
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			}
			else
			{
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	public static String unescape(String src)
	{
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length())
		{
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos)
			{
				if (src.charAt(pos + 1) == 'u')
				{
					ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				}
				else
				{
					ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			}
			else
			{
				if (pos == -1)
				{
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				}
				else
				{
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}
	
	public static String escapechar(String src)
	{
		if(StringToolKit.isBlank(src))
		{
			return src;
		}
		
		char c;   
        StringBuffer n = new StringBuffer();   
        for (int i = 0; i < src.length(); i++) {   
            c = src.charAt(i);   
            switch (c) {   
                case '&':   
                    {   
                        n.append("&amp;");   
                        break;   
                    }   
                case '<':   
                    {   
                        n.append("&lt;");   
                        break;   
                    }   
                case '>':   
                    {   
                        n.append("&gt;");   
                        break;   
                    }   
                case '"':   
                    {   
                        n.append("&quot;");   
                        break;   
                    }   
                case '\'':   
                    {   
                        n.append("&apos;");   
                        break;   
                    }   
                default :   
                    {   
                        n.append(c);   
                        break;   
                    }   
            }   
        }   
        return new String(n);   
	}	
	
	public static void main(String[] args)
	{
		String a = "中文";
		String a_escape = Escape.escape(a);
		String a_unescape = Escape.unescape(a_escape);
		System.out.println(a);
		System.out.println(a_escape);
		System.out.println(a_unescape);
	}
}
