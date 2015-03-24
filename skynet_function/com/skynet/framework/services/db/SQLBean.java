package com.skynet.framework.services.db;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SQLBean
{
	private static final Logger log = LoggerFactory.getLogger(SQLBean.class);
	
	protected ResultSet rs;
	protected Statement stmt;
	protected Connection conn;
	protected boolean status = false;
	public boolean isStatus()
	{
		return status;
	}
	public void setConn(Connection conn) throws SQLException
	{
		this.conn = conn;
		this.stmt = conn.createStatement();
	}
	public void setStmt(Statement stmt) throws SQLException
	{
		this.stmt = stmt;
	}
	public void executeUpdate(String sql) throws SQLException
	{
		log.info(sql);
		status = false;
		stmt.executeUpdate(sql);
		status = true;
	}
	public void executeQuery(String sql) throws SQLException
	{
		log.info(sql);
		status = false;
		rs = stmt.executeQuery(sql);
		status = true;
	}
	public void closeRs() throws SQLException
	{
		if (rs != null)
		{
			rs.close();
			rs = null;
		}
	}
	public void closeStmt() throws SQLException
	{
		if (stmt != null)
		{
			stmt.close();
		}
	}
	public void closeConn() throws SQLException
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
	public void closeAll() throws SQLException
	{
		closeRs();
		closeStmt();
	}
	public ResultSet getRs()
	{
		return rs;
	}
}
