package com.ray.dev.vinit.vdata;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.headray.framework.services.db.SQLParser;
import com.headray.framework.services.function.StringToolKit;


public class SQLBatchUpdate
{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	String user = "pams";
	String password = "pams123";
	String infile = "sql_in.txt";
	String outfile = "sql_out.txt";

	public static void main(String[] args) throws Exception
	{
		SQLBatchUpdate client = new SQLBatchUpdate();

		
		System.out.println("-- begin.");
		
		if(args!=null && args.length == 6)
		{
			System.out.println("--" + args[0]);
			System.out.println("--" + args[1]);
			System.out.println("--" + args[2]);	
			System.out.println("--" + args[3]);
			System.out.println("--" + args[4]);	
			System.out.println("--" + args[5]);	
			System.out.println("length:" + args.length);
			
			
			client.infile = args[0];
			client.outfile = args[1];
			client.driver = args[2];
			client.url = args[3];
			client.user = args[4];
			client.password = args[5];
		}
		
		List txtin = client.txtin();
		List txtout = client.sql(txtin);
		client.txtout(txtout);
		
		System.out.println("-- end.");
	}

	public List txtin() throws Exception
	{
		List txtin = new ArrayList();
		DataInputStream in = null;
		try
		{

			in = new DataInputStream(new FileInputStream(infile));

			String aline = null;
			while ((aline = in.readLine()) != null)
			{
				// 发现空行，什么也不执行；
				if(StringToolKit.isBlank(aline))
				{
					continue;
				}
				txtin.add(aline);
			}

			in.close();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			if(in!=null)
			{
				in.close();
			}
		}		
		return txtin;
	}
	
	public void txtout(List txtout) throws Exception
	{
		PrintWriter out  = null;
		try
		{
			
			if(txtout.size()==0)
			{
				return ;
			}
			
			out = new PrintWriter(new FileOutputStream(outfile));
			
			for(int i=0;i<txtout.size();i++)
			{
				
				String txt = (String)txtout.get(i);
				out.println(txt);
			}

		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			if(out!=null)
			{
				out.close();
			}
		}

	}
	
	public List sql(List txtin) throws Exception
	{
		ArrayList txtout = new ArrayList();
		
		if(txtin.size()==0)
		{
			return txtout;
		}
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSetMetaData meta = null;
		
		try
		{

			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();

			for (int j = 0; j < txtin.size(); j++)
			{
				rs = null;
				meta = null;
				
				String txt = (String) txtin.get(j);
				
				if(txt.startsWith("--"))
				{
					continue;
				}
				
				String[] atxt = txt.split("#");
				String table = atxt[0];
				String key = atxt[1];
				String op = atxt[2];
				String sql = atxt[3];
				String demo = atxt[4];
				
				System.out.println("-- " + sql);
				
				rs = stmt.executeQuery(sql);
				meta = rs.getMetaData();
				
				String demos = "--  解决问题 " + j + "：" + new String(demo.getBytes("ISO-8859-1"));
				txtout.add(demos);
				
				System.out.println(demos);
				
				while (rs.next())
				{
					
					int num = meta.getColumnCount();
					String updatesql = "";
					String insertsql = "";
					String keyvalue = "";
					
					String insertname = "";
					String insertvalue = "";

					for (int i = 0; i < num; i++)
					{
						String value = "";
						String name = "";
						
						name = meta.getColumnName(i + 1).toLowerCase();
						
//						if(meta.getColumnType(i + 1)==Types.BLOB)
//						{
//							Blob bvalue = rs.getBlob(meta.getColumnName(i + 1));
//							value = new String(bvalue.getBytes(1, Long.valueOf(bvalue.length()).intValue()));
//						}

						value = rs.getString(meta.getColumnName(i + 1));
						
						
						int type = meta.getColumnType(i + 1);
						
						if(type==Types.DATE || type==Types.TIME || type==Types.TIMESTAMP)
						{
							if(!StringToolKit.isBlank(value))
							{
								value = value.substring(0,19);
							}
						}
						else
						if(type==Types.BIGINT||type==Types.DECIMAL||type==Types.DOUBLE||type==Types.FLOAT||type==Types.INTEGER||type==Types.NUMERIC||type==Types.REAL||type==Types.SMALLINT||type==Types.TINYINT)
						{
							
						}
						else
						{
							if(!StringToolKit.isBlank(value))
							{
								value = value.replaceAll("'", "''");
							}
							
							// 替换转义字符
							if(!StringToolKit.isBlank(value))
							{
								value = value.replaceAll("&","'||chr(38)||'");
							}
							
						}

						if (key.toLowerCase().equals(meta.getColumnName(i + 1).toLowerCase()))
						{
							keyvalue = rs.getString(key);
						}
						else
						{
							if(type==Types.DATE || type==Types.TIME || type==Types.TIMESTAMP)
							{
								updatesql += " " + name + " = to_date(" + SQLParser.charValue(value) + ", 'YYYY-MM-DD hh24:mi:ss')";
							}
							else
							if(type==Types.BIGINT||type==Types.DECIMAL||type==Types.DOUBLE||type==Types.FLOAT||type==Types.INTEGER||type==Types.NUMERIC||type==Types.REAL||type==Types.SMALLINT||type==Types.TINYINT)
							{
								updatesql += " " + name + " = " + SQLParser.numberValue(value);
							}
							else
							{
								updatesql += " " + name + " = " + SQLParser.charValue(value);
							}

							if (i < num - 1)
							{
								updatesql += ",";
							}

						}
						
						insertname += name;
						if (i < num - 1)
						{
							insertname += ",";
						}
						
						if(type==Types.DATE || type==Types.TIME || type==Types.TIMESTAMP)
						{
							insertvalue += "to_date(" + SQLParser.charValue(value) + ", 'YYYY-MM-DD hh24:mi:ss')";
						}
						else
						if(type==Types.BIGINT||type==Types.DECIMAL||type==Types.DOUBLE||type==Types.FLOAT||type==Types.INTEGER||type==Types.NUMERIC||type==Types.REAL||type==Types.SMALLINT||type==Types.TINYINT)
						{
							insertvalue += SQLParser.numberValue(value);
						}						
						else
						{
							insertvalue += SQLParser.charValue(value);
						}
						
						if (i < num - 1)
						{
							insertvalue += ",";
						}
						
					}
					
					insertsql = "insert into " + table + "(" + insertname + ") values(" + insertvalue + ")";
					insertsql += ";";

					updatesql = " update " + table + " set " + updatesql + " where 1 = 1 and " + key + " = " + SQLParser.charValue(keyvalue);
					updatesql += ";";
					
					if(op.indexOf("INSERT")>=0)
					{
						System.out.println(insertsql);
						txtout.add(insertsql);
					}
					
					if(op.indexOf("UPDATE")>=0)
					{
						System.out.println(updatesql);
						txtout.add(updatesql);
					}
				}
				rs.close();
				System.out.println("commit;");
				txtout.add("commit;");
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			if (rs != null)
			{
				rs.close();
			}
			if (stmt != null)
			{
				stmt.close();
			}
			if (conn != null)
			{
				conn.close();
			}
		}
		
		return txtout;
	}
	

}
