package com.skynet.framework.common.finance;

import java.text.DecimalFormat;

public class ChangeRMB
{
	public static String Number[] =
	{ "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
	
	public static String MonetaryUnit[] =
	{ "万", "仟", "佰", "拾", "亿", "仟", "佰", "拾", "万", "仟", "佰", "拾", "元", "角", "分" };
	
	public static String getBigMoney(double eSender)
	{
		double NewMoney;
		int Index, len, flag;
		String StrTemp = null;
		char[] p;
		NewMoney = Math.abs(eSender);
		System.out.println(NewMoney);
		DecimalFormat deciformat;
		deciformat = (DecimalFormat) DecimalFormat.getInstance();
		deciformat.applyPattern("#######");
		String m = java.lang.String.valueOf(deciformat.format(NewMoney * 100));
		int i;
		if ((i = m.indexOf('.')) != -1)
			m = m.substring(0, i);
		p = new char[m.length()];
		// p = new char[40];
		m.getChars(0, m.length(), p, 0);
		// len=m.length();
		if (NewMoney > 100000000000.00)
		{
			StrTemp = ""; // FloatAsComma( NewMoney, False );
			return StrTemp;
		}
		if (NewMoney < 0.01)
		{
			StrTemp = "零";
			return StrTemp;
		}
		if (eSender < 0)
		{
			StrTemp = "负";
		}
		else
		{
			StrTemp = "";
		}
		flag = 1;
		len = p.length;
		System.out.println("1111");
		System.out.println(len);
		for (Index = (15 - len); Index < 15; Index++)
		{
			if (p[Index - 15 + len] != '0')
			{
				StrTemp = StrTemp + Number[Integer.parseInt(String.valueOf(p[Index - 15 + len]))];
				StrTemp = StrTemp + MonetaryUnit[Index];
			}
			else
			{
				if (Index == 5)
				{
					if ((p[Index - 14 + len] != '0') || (p[Index - 13 + len] != '0'))
					{
						StrTemp = StrTemp + MonetaryUnit[Index + 3];
						flag = 0;
					}

				}
				else
				{
					if ((Index == 12) || ((Index == 8) && (flag == 1)) || (Index == 4))
					{
						StrTemp = StrTemp + MonetaryUnit[Index];
					}
					if ((p[Index - 15 + len] != '0') && (Index != 14))
					{
						StrTemp = StrTemp + Number[Integer.parseInt(String.valueOf(p[Index - 15 + len]))];
						;
					}
				}
			}
		}
		if (p[m.length() - 1] == '0')
		{
			StrTemp = StrTemp + "整";
		}
		return StrTemp;
	}
	
	public static String getBigChar(int a)
	{
		if(a<0&&a>9)
		{
			a = 0;
		}
		
		return Number[a];
	}
}
