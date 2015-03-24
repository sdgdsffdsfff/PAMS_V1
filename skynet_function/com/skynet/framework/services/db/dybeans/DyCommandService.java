package com.skynet.framework.services.db.dybeans;
import java.util.List;

import com.skynet.framework.services.db.SQLBean;
import com.skynet.framework.services.db.beans.CommandService;
public class DyCommandService extends CommandService
{
	//
	public List queryobj(String sql) throws Exception
	{
		setStatus("error");
		try
		{
			SQLBean bean = new SQLBean();
			bean.setStmt(stmt);
			bean.executeQuery(sql);
			
			List sdata = DyDBToolKit.packObjData(bean.getRs());
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
	public List queryobj(String sql, int startIndex, int endIndex) throws Exception
	{
		setStatus("error");
		try
		{
			SQLBean bean = new SQLBean();
			bean.setStmt(stmt);
			bean.executeQuery(sql);
			DyDBToolKit dbtool = new DyDBToolKit();
			List sdata = dbtool.cpackObjData(bean.getRs(), startIndex, endIndex);
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
	
	//���н���¼
	public DynamicObject queryoneobj(String sql) throws Exception
	{
		DynamicObject tdata = new DynamicObject();
		setStatus("error");
		try
		{
			SQLBean bean = new SQLBean();
			bean.setStmt(stmt);
			bean.executeQuery(sql);
			
			List sdata = DyDBToolKit.packObjData(bean.getRs());
			if (sdata.size() > 0)
			{
				tdata = (DynamicObject) sdata.get(0);
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
	
	public DynamicObject queryemptyoneobj(String sql) throws Exception
	{
		DynamicObject tdata = new DynamicObject();
		setStatus("error");
		try
		{
			SQLBean bean = new SQLBean();
			bean.setStmt(stmt);
			bean.executeQuery(sql);
			
			List sdata = DyDBToolKit.packEmptyObjData(bean.getRs());
			if (sdata.size() > 0)
			{
				tdata = (DynamicObject) sdata.get(0);
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
	
}
