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

import com.headray.framework.common.generator.FormatKey;
import com.headray.framework.services.db.SQLParser;
import com.headray.framework.services.function.StringToolKit;

public class SQLBatchUpdateCheck
{
	String driver = "oracle.jdbc.driver.OracleDriver";

	String url = "jdbc:oracle:thin:@192.168.1.60:1521:irm";

	String user = "irmadmin";

	String password = "irmadmin123";

	public static void main(String[] args) throws Exception
	{
		SQLBatchUpdateCheck client = new SQLBatchUpdateCheck();
		List txtin = client.txtin();
		client.sql(txtin);
	}

	public List txtin() throws Exception
	{
		List txtin = new ArrayList();
		DataInputStream in = null;
		try
		{

			in = new DataInputStream(new FileInputStream("sql_out.txt"));

			String aline = null;
			while ((aline = in.readLine()) != null)
			{
				// 发现空行，什么也不执行；
				if (StringToolKit.isBlank(aline))
				{
					continue;
				}
				txtin.add(new String(aline.getBytes("ISO-8859-1")));
			}

			in.close();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			if (in != null)
			{
				in.close();
			}
		}
		return txtin;
	}

	public void txtout(List txtout) throws Exception
	{
		PrintWriter out = null;
		try
		{

			if (txtout.size() == 0)
			{
				return;
			}

			out = new PrintWriter(new FileOutputStream("sql_out.txt"));

			for (int i = 0; i < txtout.size(); i++)
			{

				String txt = (String) txtout.get(i);
				out.println(txt);
			}

		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			if (out != null)
			{
				out.close();
			}
		}

	}

	public List sql(List txtin) throws Exception
	{
		ArrayList txtout = new ArrayList();

		if (txtin.size() == 0)
		{
			return txtout;
		}

		Connection conn = null;
		Statement stmt = null;
		
		int loop = 0;

		try
		{

			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();

			for (int j = 0; j < txtin.size(); j++)
			{

				String txt = (String) txtin.get(j);

				if (txt.startsWith("--"))
				{
					continue;
				}
				
				String sql = txt;
				stmt.executeUpdate(sql);
				loop ++;
			}
		}
		catch (Exception e)
		{
			System.out.println("出错行" + FormatKey.format(loop, 4) + "：" + txtin.get(loop));
			System.out.println("出错行" + FormatKey.format(loop, 4) + "：" + e);
		}
		finally
		{
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
