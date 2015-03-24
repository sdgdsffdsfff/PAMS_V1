package com.skynet.framework.services.db.beans;
import java.sql.Connection;
import java.sql.Statement;
public abstract class Service
{
	protected Connection conn;
	protected Statement stmt;
	protected String status;
	protected int numRows;
	public void setConn(Connection conn) throws Exception
	{
		this.conn = conn;
		this.stmt = this.conn.createStatement();
	}
	public String getStatus()
	{
		return this.status;
	}
	protected void setStatus(String status)
	{
		this.status = status;
	}
	public void setStmt(Statement stmt)
	{
		this.stmt = stmt;
	}
	public int getNumRows()
	{
		return numRows;
	}
	public void setNumRows(int numRows)
	{
		this.numRows = numRows;
	}
}
