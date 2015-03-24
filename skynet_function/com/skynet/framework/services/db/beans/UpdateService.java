package com.skynet.framework.services.db.beans;
import com.skynet.framework.services.db.SQLBean;
public class UpdateService extends Service
{
	public void run(String sql) throws Exception
	{
		try
		{
			SQLBean bean = new SQLBean();
			bean.setStmt(stmt);
			bean.executeUpdate(sql);
			conn.commit();
			setStatus("default");
		}
		catch (Exception e)
		{
			setStatus("error");
			System.out.println(e);
			try
			{
				conn.rollback();
			}
			catch (Exception e1)
			{
				System.out.println(e1);
			}
			throw e;
		}
	}
}
