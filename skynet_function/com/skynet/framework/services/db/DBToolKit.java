package com.skynet.framework.services.db;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skynet.framework.services.function.StringToolKit;

public class DBToolKit
{
	// ��ݴ��
	public static List packData(ResultSet rs) throws SQLException
	{
		List data = new ArrayList();
		if (rs != null)
		{
			int columns = rs.getMetaData().getColumnCount();
			while (rs.next())
			{
				String[] row = new String[columns];
				for (int i = 0; i < columns; i++)
				{
					row[i] = StringToolKit.formatText(rs.getString(i + 1));
				}
				data.add(row);
			}
		}
		return data;
	}
	
	// ��ݴ��
	public static List packData(ResultSet rs, int startIndex, int endIndex) throws SQLException
	{
		List data = new ArrayList();
		if (rs != null)
		{
			int columns = rs.getMetaData().getColumnCount();
			int current = 0;
			while (rs.next())
			{
				if ((current >= startIndex) && (current < endIndex))
				{
					String[] row = new String[columns];
					for (int i = 0; i < columns; i++)
					{
						row[i] = StringToolKit.formatText(rs.getString(i + 1));
					}
					data.add(row);
				}
				else
				{
					return data;
				}
				current++;
			}
		}
		return data;
	}
	
	
	// ��ݴ��
	public static List cpackData(ResultSet rs, int startIndex, int endIndex) throws SQLException
	{
		List data = new ArrayList();
		if (rs != null)
		{
			int columns = rs.getMetaData().getColumnCount();
			int current = 0;
			while (rs.next())
			{
				if ((current >= startIndex) && (current < endIndex))
				{
					String[] row = new String[columns];
					for (int i = 0; i < columns; i++)
					{
						row[i] = StringToolKit.formatText(rs.getString(i + 1));
					}
					data.add(row);
				}
				current++;
			}
		//	numRows = current;
		}
		return data;
	}
}
