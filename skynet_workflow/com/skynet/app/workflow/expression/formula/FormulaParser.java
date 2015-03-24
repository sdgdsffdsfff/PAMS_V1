package com.skynet.app.workflow.expression.formula;

import java.util.ArrayList;
import java.util.List;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.services.db.dybeans.DynamicObject;

@InjectName("formulaParser")
@IocBean
public class FormulaParser
{
	@Inject
	Dao dao;
	
	DynamicObject swapFlow = new DynamicObject();

	public List parser(String formula_str) throws Exception
	{
		List owners = new ArrayList();
		return owners;
	}
	
	
	public DynamicObject getSwapFlow()
	{
		return swapFlow;
	}
	
	public void setSwapFlow(DynamicObject swapFlow)
	{
		this.swapFlow = swapFlow;
	}


	public Dao getDao() {
		return dao;
	}


	public void setDao(Dao dao) {
		this.dao = dao;
	}
	
	
}
