package com.ray.dev.vinit.query;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ray.dev.vinit.make.SearchItemMake;
import com.ray.dev.vinit.query.define.TempDefine;
import com.ray.dev.vinit.query.vo.SearchVO;

public class VinitSearchItem
{
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	public static void main(String[] args)
	{
		VinitSearchItem client = new VinitSearchItem();
		
		List s = client.init();
		
		client.make(s);
	}
	
	public List init()
	{
		List s = new ArrayList();
		
		s.add(TempDefine.define());
		
		return s;
	}
		
	public void make(List s)
	{
		for(int i=0;i<s.size();i++)
		{
			SearchVO vo = (SearchVO)s.get(i);
			cmake(vo);
		}
	}
	
	public void cmake(SearchVO vo)
	{
		cmake_browse(vo);
	}
	
	public void cmake_browse(SearchVO vo)
	{
		SearchItemMake.clear(vo);
		SearchItemMake.searchitem(vo);
	}

}
