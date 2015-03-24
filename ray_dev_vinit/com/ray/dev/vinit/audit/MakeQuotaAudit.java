package com.ray.dev.vinit.audit;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.headray.framework.common.generator.UUIDGenerator;

public class MakeQuotaAudit
{

	public static void main(String[] args) throws Exception
	{
		mockdata();
	}
	
	// 模拟数据
	public static void mockdata() throws Exception
	{
		String begindate = "2012-11-1";
		String enddate = "2012-11-30";
		String cimsid = "4028811b3a9bd32c013a9be853930001";
		String[] inspectionid = "402881df3ad440df013ad4b65a6d0022,402881df3ad440df013ad4b65a6d0024,402881df3ad440df013ad4b65a6d0023,402881df3ad440df013ad4ae64760021".split(",");
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar calbegin = new GregorianCalendar();
		Calendar calend = new GregorianCalendar();
		
		calbegin.setTime(sf.parse(begindate));
		calend.setTime(sf.parse(enddate));
		
		StringBuffer sql = new StringBuffer();
		
		int i=0;
		while(calbegin.before(calend))
		{
			sql = new StringBuffer();
			String cdate = sf.format(calbegin.getTime());
			String auditid = UUIDGenerator.getInstance().getNextValue();
			sql.append(" insert into t_app_audit(id, begintime, endtime, ctype, state, cimsid, cno, createtime)");
			sql.append(" values ( ");
			sql.append(" '" + auditid + "', ");
			sql.append(" to_date('" + cdate + "', 'YYYY-MM-DD'), ");	
			sql.append(" to_date('" + cdate + "', 'YYYY-MM-DD'), ");	
			sql.append(" '系统巡检', ");
			sql.append(" '生效', ");
			sql.append("'" + cimsid + "', ");
			sql.append("'" + cdate + "01', ");
			sql.append(" to_date('" + cdate + "', 'YYYY-MM-DD') ");	
			sql.append(" ) ;").append("\n");
			
			System.out.println(sql.toString());
			
			// 新增明细
			for(int j=0;j<inspectionid.length;j++)
			{
				double pvalue = (i % (j + 1) * 1.5) + Math.random() * 1.5;
				
				sql = new StringBuffer();
				sql.append(" insert into t_app_auditinspecvalue(id, auditid, inspectionid, pvalue) ");
				sql.append(" values ( ");
				sql.append(" sys_guid(), ");
				sql.append(" '" + auditid + "', ");
				sql.append(" '" + inspectionid[j] + "', ");
				sql.append(new BigDecimal(pvalue).setScale(2, RoundingMode.UP).doubleValue());
				sql.append(" ) ;").append("\n");
				
				System.out.println(sql.toString());
			}
			
			calbegin.add(Calendar.DATE, 1);
			
			i++;
		}

	}

}
