package com.skynet.framework.services.db.beans;
import java.util.List;

import com.skynet.framework.services.db.DBToolKit;
import com.skynet.framework.services.db.SQLBean;
public class QueryService extends Service
{
	
	// �����ƽ���¼��Ŀ��ѯ	
	public List run(String sql) throws Exception
	{
		try
		{
			SQLBean bean = new SQLBean();
			bean.setStmt(stmt);
			bean.executeQuery(sql);
			List sdata = DBToolKit.packData(bean.getRs());
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
	
	//������н���¼��Ŀ��
	public List run(String sql, int startIndex, int endIndex) throws Exception
	{
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
}
