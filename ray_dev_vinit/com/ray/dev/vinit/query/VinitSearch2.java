package com.ray.dev.vinit.query;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ray.dev.vinit.make.BrowseMake;
import com.ray.dev.vinit.make.InputMake;
import com.ray.dev.vinit.make.LocateMake;
import com.ray.dev.vinit.query.define.TempDefine;
import com.ray.dev.vinit.query.vo.SearchVO;

public class VinitSearch2
{
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	public static void main(String[] args)
	{
		VinitSearch2 client = new VinitSearch2();
		
		List s = client.init();
		
		client.make(s);
	}
	
	public List init()
	{
		List s = new ArrayList();
		
//		s.add(SearchDefine.define());
//		s.add(SearchOptionDefine.define());
//		s.add(SearchItemDefine.define());
//		s.add(SearchUrlDefine.define());
//		s.add(SearchLinkDefine.define());
		
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
		//cmake_browse(vo);
		//cmake_locate(vo);
		cmake_input(vo);
	}
	
	public void cmake_browse(SearchVO vo)
	{
		BrowseMake.clear(vo);
		BrowseMake.search(vo);
		BrowseMake.searchoption(vo);
		BrowseMake.searchitem(vo);
		BrowseMake.searchurl(vo);
	}
	
	public void cmake_locate(SearchVO vo)
	{
		LocateMake.clear(vo);
		LocateMake.search(vo);
		LocateMake.searchoption(vo);
		LocateMake.searchitem(vo);
		LocateMake.searchurl(vo);
	}
	
	public void cmake_input(SearchVO vo)
	{
		InputMake.clear(vo);
		InputMake.search(vo);
		InputMake.searchoption(vo);
		InputMake.searchitem(vo);
		InputMake.searchurl(vo);
	}
}
