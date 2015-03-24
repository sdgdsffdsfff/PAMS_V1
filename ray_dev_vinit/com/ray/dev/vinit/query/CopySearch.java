package com.ray.dev.vinit.query;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.headray.framework.common.generator.FormatKey;
import com.ray.app.query.entity.Search;
import com.ray.app.query.entity.SearchItem;
import com.ray.app.query.entity.SearchLink;
import com.ray.app.query.entity.SearchOption;
import com.ray.app.query.entity.SearchUrl;
import com.ray.app.search.service.SearchService;
import com.ray.app.searchitem.service.SearchItemService;
import com.ray.app.searchlink.service.SearchLinkService;
import com.ray.app.searchoption.service.SearchOptionService;
import com.ray.app.searchurl.service.SearchUrlService;

public class CopySearch
{
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	public static void main(String[] args) throws Exception
	{
		CopySearch client = new CopySearch();
		client.copy("12010101","12010107");
	}
	
	public void copy(String sourceid, String destionid) throws Exception
	{
		FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext("applicationContext.xml");

		SearchService searchService = (SearchService) ctx.getBean("searchService");
		SearchOptionService searchOptionService = (SearchOptionService) ctx.getBean("searchOptionService");
		SearchItemService searchItemService = (SearchItemService) ctx.getBean("searchItemService");
		SearchUrlService searchUrlService = (SearchUrlService) ctx.getBean("searchUrlService");
		SearchLinkService searchLinkService = (SearchLinkService) ctx.getBean("searchLinkService");		
		
		
		Search search = (Search)BeanUtils.cloneBean(searchService.getSearch(sourceid));
		
		List<SearchOption> searchoptions = searchOptionService.findBy("searchid", search.getSearchid(), Order.asc("oorder"));
		List<SearchItem> searchitems = searchItemService.findBy("searchid", search.getSearchid(), Order.asc("oorder"));
		List<SearchUrl> searchurls = searchUrlService.findBy("searchid", search.getSearchid());
		List<SearchLink> searchlinks = searchLinkService.findBy("searchid", search.getSearchid());

//		search.setSearchid(destionid);
//		search.setSearchname(search.getSearchname() + ".XXX");
//		searchService.saveSearch(search);

		for(int i=0;i<searchoptions.size();i++)
		{
			SearchOption searchoption = (SearchOption)BeanUtils.cloneBean(searchoptions.get(i));
			searchoption.setSearchid(destionid);
			searchoption.setSearchoptionid(destionid + FormatKey.format(i+1, 2));
			searchOptionService.saveSearchOption(searchoption);
		}
		
		for(int i=0;i<searchitems.size();i++)
		{
			SearchItem searchitem = (SearchItem)BeanUtils.cloneBean(searchitems.get(i));
			searchitem.setSearchid(destionid);
			searchitem.setSearchitemid(destionid + FormatKey.format(i+1, 2));
			searchItemService.saveSearchItem(searchitem);
		}
		
		for(int i=0;i<searchurls.size();i++)
		{
			SearchUrl searchurl = (SearchUrl)BeanUtils.cloneBean(searchurls.get(i));
			searchurl.setSearchid(destionid);
			searchurl.setSearchurlid(destionid + FormatKey.format(i+1, 2));
			searchUrlService.saveSearchUrl(searchurl);
		}
		
		for(int i=0;i<searchlinks.size();i++)
		{
			SearchLink searchlink = (SearchLink)BeanUtils.cloneBean(searchlinks.get(i));
			searchlink.setSearchid(destionid);
			searchlink.setSearchlinkid(destionid + FormatKey.format(i+1, 2));
			searchLinkService.saveSearchLink(searchlink);
		}
		
	}
	
//	public void copy(String sourceid, String destionid) throws Exception
//	{
//		FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext("applicationContext.xml");
//		SessionFactory sessionFactory = (SessionFactory)ctx.getBean("sessionFactory");
//		SearchDao searchDao = (SearchDao) ctx.getBean("searchDao");
//		SearchOptionDao searchOptionDao = (SearchOptionDao) ctx.getBean("searchOptionDao");
//		SearchItemDao searchItemDao = (SearchItemDao) ctx.getBean("searchItemDao");
//		SearchUrlDao searchUrlDao = (SearchUrlDao) ctx.getBean("searchUrlDao");
//		SearchLinkDao searchLinkDao = (SearchLinkDao) ctx.getBean("searchLinkDao");
//		
//		SearchService searchService = (SearchService) ctx.getBean("searchService");
//		SearchOptionService searchOptionService = (SearchOptionService) ctx.getBean("searchOptionService");
//		SearchItemService searchItemService = (SearchItemService) ctx.getBean("searchItemService");
//		SearchUrlService searchUrlService = (SearchUrlService) ctx.getBean("searchUrlService");
//		SearchLinkService searchLinkService = (SearchLinkService) ctx.getBean("searchLinkService");		
		
		
//		searchDao.setSessionFactory(sessionFactory);
//		searchOptionDao.setSessionFactory(sessionFactory);
//		searchItemDao.setSessionFactory(sessionFactory);
//		searchUrlDao.setSessionFactory(sessionFactory);
//		searchLinkDao.setSessionFactory(sessionFactory);
		
//		Search search = (Search)BeanUtils.cloneBean(searchDao.get(sourceid));
//		
//		List<SearchOption> searchoptions = searchOptionDao.findBy("searchid", search.getSearchid());
//		List<SearchItem> searchitems = searchItemDao.findBy("searchid", search.getSearchid());
//		List<SearchUrl> searchurls = searchUrlDao.findBy("searchid", search.getSearchid());
//		List<SearchLink> searchlinks = searchLinkDao.findBy("searchid", search.getSearchid());
//
//		search.setSearchid(destionid);
//		search.setSearchname(search.getSearchname() + ".XXX");
//		//searchDao.save(search);
//
//		for(int i=0;i<searchoptions.size();i++)
//		{
//			SearchOption searchoption = (SearchOption)BeanUtils.cloneBean(searchoptions.get(i));
//			searchoption.setSearchid(destionid);
//			searchoption.setSearchoptionid(destionid + FormatKey.format(i, 2));
//			searchOptionDao.save(searchoption);
//		}
//		
//		for(int i=0;i<searchitems.size();i++)
//		{
//			SearchItem searchitem = (SearchItem)BeanUtils.cloneBean(searchitems.get(i));
//			searchitem.setSearchid(destionid);
//			searchitem.setSearchitemid(destionid + FormatKey.format(i, 2));
//			searchItemDao.save(searchitem);
//		}
//		
//		for(int i=0;i<searchurls.size();i++)
//		{
//			SearchUrl searchurl = (SearchUrl)BeanUtils.cloneBean(searchurls.get(i));
//			searchurl.setSearchid(destionid);
//			searchurl.setSearchurlid(destionid + FormatKey.format(i, 2));
//			searchUrlDao.save(searchurl);
//		}
//		
//		for(int i=0;i<searchlinks.size();i++)
//		{
//			SearchLink searchlink = (SearchLink)BeanUtils.cloneBean(searchlinks.get(i));
//			searchlink.setSearchid(destionid);
//			searchlink.setSearchlinkid(destionid + FormatKey.format(i, 2));
//			searchLinkDao.save(searchlink);
//		}
//		
//	}

}
