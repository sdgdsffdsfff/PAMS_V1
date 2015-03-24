package com.skynet.framework.services.db.beans;

import java.util.List;

import com.skynet.framework.services.db.DBToolKit;
import com.skynet.framework.services.db.SQLBean;

public class CommandService extends Service
{
	protected boolean auto = false;
	
	// �ж	
	public boolean isempty(String sql) throws Exception
	{
		List data = query(sql);
		if(data.size()==0)
		{
			return true;
		}
		return false;
	}

	//
	public List query(String sql) throws Exception
	{
		setStatus("error");
		try
		{
			SQLBean bean = new SQLBean();
			bean.setStmt(stmt);
			bean.executeQuery(sql);
			List sdata = DBToolKit.packData(bean.getRs());
			setNumRows(sdata.size());
			bean.closeRs();
			setStatus("default");			
			return sdata;
		}
		catch (Exception e)
		{
			setStatus("error");
			System.out.println(e);
			throw e;
		}
	}
	
	// ������ѯ���н���¼��Ŀ����	
	public List query(String sql, int startIndex, int endIndex) throws Exception
	{
		setStatus("error");		
		try
		{
			SQLBean bean = new SQLBean();			
			bean.setStmt(stmt);
			bean.executeQuery(sql);
			List sdata = DBToolKit.cpackData(bean.getRs(), startIndex, endIndex);
			setNumRows(sdata.size());
			bean.closeRs();
			setStatus("default");			
			return sdata;
		}
		catch (Exception e)
		{
			setStatus("error");
			System.out.println(e);
			throw e;
		}
	}
	// ��ѯ���н���¼
	public String[] queryone(String sql) throws Exception
	{
		String[] tdata = null;
		setStatus("error");
		try
		{
			SQLBean bean = new SQLBean();
			bean.setStmt(stmt);
			bean.executeQuery(sql);
			List sdata = DBToolKit.packData(bean.getRs());
			if(sdata.size()>0)
			{
				tdata = (String[])sdata.get(0);
				setNumRows(1);
			}
			bean.closeRs();
			setStatus("default");			
			return tdata;
		}
		catch (Exception e)
		{
			setStatus("error");
			System.out.println(e);
			throw e;
		}
	}
	//���е��н���¼
	public String querysingle(String sql) throws Exception
	{
		String tdata = null;
		setStatus("error");
		try
		{
			SQLBean bean = new SQLBean();
			bean.setStmt(stmt);
			bean.executeQuery(sql);
			
			List sdata = DBToolKit.packData(bean.getRs());
			if(sdata.size()>0)
			{
				tdata = ((String[])sdata.get(0))[0];
				setNumRows(1);
			}
			bean.closeRs();
			setStatus("default");			
			return tdata;
		}
		catch (Exception e)
		{
			setStatus("error");
			System.out.println(e);
			throw e;
		}
	}
	
	
	//	���²���
	public void update(String sql) throws Exception
	{
		setStatus("error");		
		try
		{
			SQLBean bean = new SQLBean();
			bean.setStmt(stmt);
			bean.executeUpdate(sql);
			setStatus("default");
		}
		catch (Exception e)
		{
			setStatus("error");
			System.out.println(e);
			throw e;
		}
	}
	
	public void commit() throws Exception
	{
		if(conn!=null)
		{
			conn.commit();
		}
	}
	
	public void rollback() throws Exception
	{
		if(conn!=null)
		{
			conn.rollback();
		}
	}

	public void setAuto(boolean auto) throws Exception
	{
		this.auto = auto;
		this.conn.setAutoCommit(auto);
	}
}
