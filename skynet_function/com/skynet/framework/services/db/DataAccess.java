package com.skynet.framework.services.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataAccess
{
	public static void rollback(Connection conn) throws Exception
	{
		if(conn!=null)
		{
			conn.rollback();
		}
	}
	
	public static void commit(Connection conn) throws Exception
	{
		if(conn!=null)
		{
			conn.commit();
		}
	}	
	
	public static void cleanup(Connection con, Statement ps, ResultSet rs) throws Exception
	{
		try
		{
			try
			{
				if (rs != null)
					rs.close();
				rs = null;
			}
			finally
			{
				try
				{
					if (ps != null)
						ps.close();
					ps = null;
				}
				finally
				{
					if (con != null)
						con.close();
				}
			}
		}
		catch (SQLException se)
		{
			throw se;
		}
	}

	public static void cleanup(Statement ps, ResultSet rs) throws Exception
	{
		try
		{
			try
			{
				if (rs != null)
					rs.close();
				rs = null;
			}
			finally
			{
				if (ps != null)
					ps.close();
				ps = null;
			}
		}
		catch (SQLException se)
		{
			throw se;
		}
	}

	public static void cleanup(Connection con, Statement ps) throws Exception
	{
		try
		{
			try
			{
				if (ps != null)
					ps.close();
				ps = null;
			}
			finally
			{
				if (con != null)
					con.close();
			}
		}
		catch (SQLException se)
		{
			throw se;
		}
	}

	public static void cleanup(Connection con, ResultSet rs) throws Exception
	{
		try
		{
			try
			{
				if (rs != null)
					rs.close();
				rs = null;
			}
			finally
			{
				if (con != null)
					con.close();
			}
		}
		catch (SQLException se)
		{
			throw se;
		}
	}

	public static void cleanup(Connection con) throws Exception
	{
		try
		{
			if (con != null)
				con.close();
		}
		catch (SQLException se)
		{
			throw se;
		}
	}

	public static void cleanup(Statement ps) throws Exception
	{
		try
		{
			if (ps != null)
				ps.close();
			ps = null;
		}
		catch (SQLException se)
		{
			throw se;
		}
	}

	public static void cleanup(ResultSet rs) throws Exception
	{
		try
		{
			if (rs != null)
				rs.close();
			rs = null;
		}
		catch (SQLException se)
		{
			throw se;
		}
	}
}
