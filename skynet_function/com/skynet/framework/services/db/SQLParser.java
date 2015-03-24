package com.skynet.framework.services.db;

import com.skynet.framework.services.function.StringToolKit;
import com.skynet.framework.spec.GlobalConstants;

public class SQLParser
{
	public static int type_char = 0;
	public static int type_number = 1;

	public static String ctype_char = "0";
	public static String ctype_number = "1";

	public static String ctrrt(String value)
	{
		return value + "\n";
	}

	public static String ctr(String value)
	{
		return value + ",";
	}

	public static String ctrend(String value)
	{
		return value + ",";
	}
	
	public static String charValueRT(String value)
	{
		return charValue(value) + "\n";
	}

	public static String charValueEnd(String value)
	{
		return charValue(value) + ",";
	}

	public static String expressValue(String value)
	{
		return numberValue(value);
	}

	public static String expressValueRT(String value)
	{
		return numberValueRT(value);
	}

	public static String expressValueEnd(String value)
	{
		return numberValueEnd(value);
	}
	
	public static String numberValueRT(String value)
	{
		return numberValue(value) + "\n";
	}

	public static String expEnd(String value)
	{
		return numberValueEnd(value);
	}
	
	public static String exp(String value)
	{
		return numberValue(value);
	}

	public static String numberValueEnd(int value)
	{
		return numberValue(String.valueOf(value)) + ",";
	}
	
	public static String numberValueEnd(float value)
	{
		return numberValue(String.valueOf(value)) + ",";
	}	
	
	public static String numberValueEnd(String value)
	{
		return numberValue(value) + ",";
	}

	public static String charLikeValue(String value)
	{
		if (StringToolKit.isBlank(value))
		{
			return " null ";
		}
		
		return "'%" + format(value) + "%'";
	}

	public static String charLikeRightValue(String value)
	{
		if (StringToolKit.isBlank(value))
		{
			return " null ";
		}
		
		return "'" + format(value) + "%'";
	}
	
	public static String charLikeLeftValue(String value)
	{
		if (StringToolKit.isBlank(value))
		{
			return " null ";
		}
		
		return "'%" + format(value) + "'";
	}
	
	public static String charValue(String value)
	{
		if (StringToolKit.isBlank(value))
		{
			return " null ";
		}
		return "'" + format(value) + "'";
	}
	
	public static String numberValue(String value)
	{
		if (StringToolKit.isBlank(value))
		{
			return " null ";
		}
		return value;
	}
	
	public static String numberValue(int value)
	{
		return String.valueOf(value);
	}
		
	
	public static String numberValue(float value)
	{
		return String.valueOf(value);
	}
	
	public static String setParamValue(String fieldname, String fieldvalue, int fieldtype)
	{
		if ((fieldvalue == null) || (fieldvalue.trim().equals("")))
		{
			return " " + fieldname + " = null, ";
		}

		if (fieldtype == SQLParser.type_char)
		{
			return " " + fieldname + " = " + charValue(fieldvalue) + ", ";
		}
		else
		{
			return " " + fieldname + " = " + numberValue(fieldvalue) + ", ";
		}
	}
	//
	public static String setLastParamValue(String fieldname, String fieldvalue, int fieldtype)
	{
		if ((fieldvalue == null) || (fieldvalue.trim().equals("")))
		{
			return " " + fieldname + " = null ";
		}

		if (fieldtype == SQLParser.type_char)
		{
			return " " + fieldname + " = " + charValue(fieldvalue) + " ";
		}
		else
		{
			return " " + fieldname + " = " + numberValue(fieldvalue) + " ";
		}
	}
	//
	public static String addWhere()
	{
		return " where 1 = 1 ";
	}

	public static String addCond(String fieldname, String fieldvalue, int fieldtype)
	{
		if ((fieldvalue == null) || (fieldvalue.trim().equals("")))
		{
			return " ";
		}

		if (fieldtype == SQLParser.type_char)
		{
			return " and " + fieldname + " = " + charValue(fieldvalue) + " ";
		}
		else
		{
			return " and " + fieldname + " = " + numberValue(fieldvalue) + " ";
		}
	}
	
	public static String addCond(String fieldname, String fieldvalue, String fieldtype, String symbol)
	{
		if(format(fieldtype).equals("1"))
		{
			return addCond(fieldname, fieldvalue, 1, symbol);
		}
		else
		{
			return addCond(fieldname, fieldvalue, 0, symbol);
		}
	}	
	
	public static String addCondRT(String fieldname, String fieldvalue, String fieldtype, String symbol)
	{
		return addCond(fieldname, fieldvalue, fieldtype, symbol) + "\n";
	}	
	
	public static String addCond(String fieldname, String fieldvalue, int fieldtype, String symbol)
	{
		if(format(symbol).equals(""))
		{
			symbol = "=";	
		}
		
		if ((fieldvalue == null) || (fieldvalue.trim().equals("")))
		{
			return " ";
		}

		if (fieldtype == SQLParser.type_char)
		{
			return " and " + fieldname + symbol + charValue(fieldvalue) + " ";
		}
		else
		{
			return " and " + fieldname + symbol + numberValue(fieldvalue) + " ";
		}
	}
	
	
	public static String addCondParserRT(String htmlfieldname, String fieldvalue, String fieldtype, String symbol,String fieldname)
	{
		return addCondParser(htmlfieldname, fieldvalue, fieldtype, symbol,fieldname) + "\n";
	}
	
	public static String addCondParserRT(String tablealias, String htmlfieldname, String fieldvalue, String fieldtype, String symbol,String fieldname)
	{
		return addCondParser(tablealias, htmlfieldname, fieldvalue, fieldtype, symbol,fieldname) + "\n";
	}
	
	
	public static String addCondParser(String htmlfieldname, String fieldvalue, String fieldtype, String symbol,String fieldname)
	{
		if(format(fieldtype).equals("1"))
		{
			return addCondParser(fieldname, fieldvalue, 1, symbol);
		}
		else
		{
			return addCondParser(fieldname, fieldvalue, 0, symbol);
		}
	}
	
	public static String addCondParser(String tablealias, String htmlfieldname, String fieldvalue, String fieldtype, String symbol,String fieldname)
	{
		if(format(fieldtype).equals(SQLParser.ctype_char))
		{
			return addCondParser(tablealias, fieldname, fieldvalue, SQLParser.type_char, symbol);
		}
		else
		{
			return addCondParser(tablealias, fieldname, fieldvalue, SQLParser.type_number, symbol);
		}
	}
	
	
	public static String addCondParser(String fieldname, String fieldvalue, int fieldtype, String symbol)
	{
		/*
		if(format(symbol).equals(""))
		{
			symbol = "eq";
		}
		else
		{
			symbol = parserSymbol((symbol));
		}
		*/
		
		if ((fieldvalue == null) || (fieldvalue.trim().equals("")))
		{
			return " ";
		}

		if (fieldtype == SQLParser.type_char)
		{
			return " and " + fieldname + symbol + charValue(fieldvalue) + " ";
		}
		else
		{
			return " and " + fieldname + symbol + numberValue(fieldvalue) + " ";
		}
	}
	
	public static String addCondParser(String tablealias, String fieldname, String fieldvalue, int fieldtype, String symbol)
	{
		/*
		if(format(symbol).equals(""))
		{
			symbol = "eq";	
		}
		else
		{
			symbol = parserSymbol((symbol));
		}
		
		if ((fieldvalue == null) || (fieldvalue.trim().equals("")))
		{
			return " ";
		}
		*/
		
		if (fieldtype == SQLParser.type_char)
		{
			if("like".equals(symbol))
			{
				return " and " + tablealias + "." + fieldname + " like " + charValue("%" + fieldvalue + "%");
			}
			else
			{
				return " and " + tablealias + "." + fieldname + symbol + charValue(fieldvalue) + " ";
			}
		}
		else
		{
			return " and " + tablealias + "." + fieldname + symbol + numberValue(fieldvalue) + " ";
		}
	}
	
	
	public static String addCondRT(String fieldname, String fieldvalue, int fieldtype, String symbol)
	{
		return addCondRT(fieldname, fieldvalue, fieldtype, symbol) + " \n";
	}


	public static String addCondRT(String fieldname, String fieldvalue, int fieldtype)
	{
		return addCond(fieldname, fieldvalue, fieldtype) + " \n";
	}
	
	public static String parserSymbol(String symbol)
	{
		if(symbol.equals("lt"))
		{
			return "<"; 
		}
		if(symbol.equals("bt"))
		{
			return ">"; 
		}
		if(symbol.equals("be"))
		{
			return ">="; 
		}
		if(symbol.equals("le"))
		{
			return "<="; 
		}
		if(symbol.equals("eq"))
		{
			return "="; 
		}
		if(symbol.equals("ne"))
		{
			return "<>"; 
		}
		if(symbol.equals("div"))
		{
			return "/"; 
		}
		if(symbol.equals("squ"))
		{
			return "'"; 
		}
		if(symbol.equals("dot"))
		{
			return "."; 
		}
		return "=";
	}
	
	public static String format(String text)
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
	
	
	public static String sqlInsert(String tableid, String[] names, String[] types, String[] values)
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into ");
		sql.append(tableid);
		sql.append(" ( ");
		int len = names.length;
		for(int i=0;i<len;i++)
		{
			sql.append(names[i]);
			if(i<len-1)
			{
				sql.append(",");
			}
		}
		sql.append(" ) values ( ");
		
		for(int i=0;i<len;i++)
		{
			if(GlobalConstants.data_type_string.equals(types[i]))
			{
				sql.append(SQLParser.charValue(values[i]));
			}
			else
			{
				sql.append(SQLParser.numberValue(values[i]));
			}

			if(i<len-1)
			{
				sql.append(",");
			}
		}
		
		sql.append(" ) ");
		
		return sql.toString();
	}

	public static String sqlUpdate(String tableid, String[] names, String[] types, String[] values)
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" update ");
		sql.append(tableid);
		sql.append(" set ");
		int len = names.length;
		for(int i=0;i<len;i++)
		{
			sql.append(names[i]);
			sql.append(" = ");
			
			if(GlobalConstants.data_type_string.equals(types[i]))
			{
				sql.append(SQLParser.charValue(values[i]));
			}
			else
			{
				sql.append(SQLParser.numberValue(values[i]));
			}
			
			if(i<len-1)
			{
				sql.append(",");
			}
		}
		
		return sql.toString();
	}

	public static String sqlAllUpdate(String tableid, String[] names, String[] types, String[] values)
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" update ");
		sql.append(tableid);
		sql.append(" set ");
		int len = names.length;
		for(int i=1;i<len;i++)
		{
			sql.append(names[i]);
			sql.append(" = ");
			
			if(GlobalConstants.data_type_string.equals(types[i]))
			{
				sql.append(SQLParser.charValue(values[i]));
			}
			else
			{
				sql.append(SQLParser.numberValue(values[i]));
			}
			
			if(i<len-1)
			{
				sql.append(",");
			}
		}
		
		sql.append(" where 1 = 1 ");
		sql.append("   and " + names[0] + " = " + SQLParser.charValue(values[0]));
		
		return sql.toString();
	}
	
	
}
