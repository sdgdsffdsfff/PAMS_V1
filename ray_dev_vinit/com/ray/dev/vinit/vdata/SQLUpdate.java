package com.ray.dev.vinit.vdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import com.headray.framework.services.db.SQLParser;

public class SQLUpdate
{
	public static void main(String[] args) throws Exception
	{
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@192.168.1.20:1521:orcl";
		String user = "itsm2xb";
		String password = "admin";
		
		String sql = "select searchname, macro, searchid from t_sys_search where 1 = 1 and searchid = '2001010101'";
		String table = "t_sys_search";
		String key = "searchid";
	
		Connection conn = null;
		Statement stmt= null;
		ResultSet rs = null;
		ResultSetMetaData meta = null;
		try
		{
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			meta = rs.getMetaData();
			
			while(rs.next())
			{
				int num = meta.getColumnCount();
				String updatesql = "";
				String keyvalue = "";
				
				for(int i=0;i<num;i++)
				{
					String value = "";
					String name = "";

					if(key.equals(meta.getColumnName(i+1).toLowerCase()))
					{
						keyvalue = rs.getString(key);
					}
					else
					{
						name = meta.getColumnName(i+1).toLowerCase();
						value = rs.getString(meta.getColumnName(i+1)) ;
						updatesql += " set " + name + " = " + SQLParser.charValue(value);
						
						if(i<num-1)
						{
							updatesql += ",";
						}

					}
				}
				
				updatesql = " update " + table + " " + updatesql + " where 1 = 1 and " + key + " = " + SQLParser.charValue(keyvalue);
				System.out.println(updatesql);
				
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			if(rs!=null)
			{
				rs.close();
			}
			if(stmt!=null)
			{
				stmt.close();
			}
			if(conn!=null)
			{
				conn.close();
			}
		}
	}

}
