package com.skynet.framework.services.db.dybeans;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skynet.framework.services.db.DBToolKit;
import com.skynet.framework.services.function.StringToolKit;

public class DyDBToolKit extends DBToolKit
{
	// ��ݴ��
	public static List packObjData(ResultSet rs) throws SQLException
	{
		List data = new ArrayList();
		if (rs != null)
		{
			ResultSetMetaData meta = rs.getMetaData();
			int columns = meta.getColumnCount();
			while (rs.next())
			{
				DynamicObject row = new DynamicObject();
				for (int i = 0; i < columns; i++)
				{
					row.setAttr(meta.getColumnName(i + 1).toLowerCase(), StringToolKit.formatText(rs.getString(i + 1)));
				}
				data.add(row);
			}
		}
		return data;
	}

	public static List packEmptyObjData(ResultSet rs) throws SQLException
	{
		List data = new ArrayList();

		if (rs != null)
		{
			int empty = 0;
			ResultSetMetaData meta = rs.getMetaData();
			int columns = meta.getColumnCount();
			while (rs.next())
			{
				empty++;
				DynamicObject row = new DynamicObject();
				for (int i = 0; i < columns; i++)
				{
					row.setAttr(meta.getColumnName(i + 1).toLowerCase(), StringToolKit.formatText(rs.getString(i + 1)));
				}
				data.add(row);
			}
			if(empty==0)
			{
				DynamicObject row = new DynamicObject();
				for (int i = 0; i < columns; i++)
				{
					row.setAttr(meta.getColumnName(i + 1).toLowerCase(), "");
				}
				data.add(row);
			}
		}
		
		
		return data;
	}
	
	// ��ݴ��
	public static List packObjData(ResultSet rs, int startIndex, int endIndex) throws SQLException
	{
		List data = new ArrayList();
		if (rs != null)
		{
			ResultSetMetaData meta = rs.getMetaData();
			int columns = meta.getColumnCount();
			int current = 0;
			while (rs.next())
			{
				if ((current >= startIndex) && (current < endIndex))
				{
					DynamicObject row = new DynamicObject();
					for (int i = 0; i < columns; i++)
					{
						row.setAttr(meta.getColumnName(i).toLowerCase(), rs.getString(i + 1));
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
	public List cpackObjData(ResultSet rs, int startIndex, int endIndex) throws SQLException
	{
		List data = new ArrayList();
		if (rs != null)
		{
			ResultSetMetaData meta = rs.getMetaData();
			int columns = meta.getColumnCount();
			String[]  colnames = new String[columns];
			for(int i=0;i<columns;i++)
			{
				colnames[i] = meta.getColumnName(i + 1).toLowerCase();			
			}
			int current = 0;
			int length = endIndex - startIndex;
			rs.absolute(startIndex+1);
			
			for(int j=0;j<length;j++)
			{
				DynamicObject row = new DynamicObject();
				for (int i = 0; i < columns; i++)
				{
					row.setAttr(colnames[i], rs.getString(i + 1));
				}
				data.add(row);
				current++;
				
				if(!rs.next())
				{
					break;
				}
			}
			// numRows = current;			
		}
		return data;
	}
	
}
