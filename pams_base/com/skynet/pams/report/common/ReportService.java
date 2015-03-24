package com.skynet.pams.report.common;

import org.nutz.dao.Dao;

import com.skynet.framework.dao.SkynetDao;

public class ReportService {
	protected Dao dao;

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	public Dao dao() {
		return getDao();
	}

	public SkynetDao sdao() {
		return (SkynetDao) getDao();
	}
}
