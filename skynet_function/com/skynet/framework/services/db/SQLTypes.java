package com.skynet.framework.services.db;

import java.sql.Types;

import com.skynet.framework.services.function.StringToolKit;

public class SQLTypes
{
	public static String getTypeValue(int column_type, String value)
	{
		switch (column_type)
		{
			case Types.BIGINT:
			{
				value = SQLParser.numberValue(StringToolKit.formatText(value, "0"));
				break;
			}
			case Types.BIT:
			{
				value = SQLParser.numberValue(StringToolKit.formatText(value, "0"));
				break;
			}
			case Types.BOOLEAN:
			{
				value = SQLParser.numberValue(StringToolKit.formatText(value, "0"));
				break;
			}
			case Types.DECIMAL:
			{
				value = SQLParser.numberValue(StringToolKit.formatText(value, "0"));
				break;
			}
			case Types.DOUBLE:
			{
				value = SQLParser.numberValue(StringToolKit.formatText(value, "0"));
				break;
			}
			case Types.FLOAT:
			{
				value = SQLParser.numberValue(StringToolKit.formatText(value, "0"));
				break;
			}
			case Types.INTEGER:
			{
				value = SQLParser.numberValue(StringToolKit.formatText(value, "0"));
				break;
			}
			case Types.NUMERIC:
			{
				value = SQLParser.numberValue(StringToolKit.formatText(value, "0"));
				break;
			}
			case Types.REAL:
			{
				value = SQLParser.numberValue(StringToolKit.formatText(value, "0"));
				break;
			}
			case Types.SMALLINT:
			{
				value = SQLParser.numberValue(StringToolKit.formatText(value, "0"));
				break;
			}
			case Types.TINYINT:
			{
				value = SQLParser.numberValue(StringToolKit.formatText(value, "0"));
				break;
			}
			case Types.DATE:
			{
				if(!StringToolKit.isBlank(value))
				{
					value = "'" + value + "'";
				}
				break;
			}
			case Types.TIME:
			{
				if(!StringToolKit.isBlank(value))
				{
					value = "'" + value + "'";
				}
				break;
			}
			case Types.TIMESTAMP:
			{
				if(!StringToolKit.isBlank(value))
				{
					value = "'" + value + "'";
				}
				break;
			}

			default:
			{
				value = SQLParser.charValue(value.replaceAll("'", "''"));
				break;
			}
			
		}
		
		return value;
	}
	
	public static String getPType(String column_type)
	{
		if("char".equals(column_type))
		{
			return SQLParser.ctype_char;
		}
		else
		if("varchar".equals(column_type))
		{
			return SQLParser.ctype_char;
		}
		else
		if("integer".equals(column_type))
		{
			return SQLParser.ctype_number;
		}
		else
		if("number".equals(column_type))
		{
			return SQLParser.ctype_number;
		}
		
		return SQLParser.ctype_char;
	}
	
	public static String[] getPType(String[] column_types)
	{
		if(column_types==null)
		{
			return null;
		}
		
		String[] n_column_types = new String[column_types.length];
		for(int i=0;i<column_types.length;i++)
		{
			n_column_types[i] = getPType(column_types[i]);
		}
		return n_column_types;
	}
}
