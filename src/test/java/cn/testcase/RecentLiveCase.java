package cn.testcase;

import model.VP;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import util.Property;
import cn.pages.AccountPage;
import cn.pages.RecentLivePage;
import cn.pages.WatchPage;

public class RecentLiveCase extends VP{
	@BeforeMethod
	public void BeforeMethod(){
		initialize("chrome","tyokyo@126.com","123456789");
		startSioeye();		
		AccountPage.loginAccount();
	}
	
	/** 
	* @Title: testPaly 
	* @Date:2017年10月19日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 最近直播列表，播放视频
	*/
	@Test
	public void testPaly(){
		WatchPage.clickDiscover();
		WatchPage.clickMore();
		RecentLivePage.clickFirstVideo();
		
	}
	@Test
	public void testHomePagePrevDisable(){
		WatchPage.clickDiscover();
		WatchPage.clickMore();
		boolean actual = RecentLivePage.isPageDisable();
		Assert.assertEquals(actual, true);
	}
	@Test
	public void testNextPage(){
		WatchPage.clickDiscover();
		WatchPage.clickMore();
		RecentLivePage.clickPageNext(8);
		String URL = Property.getValueByKey("properties/config.properties", "URL")+"recent-live/";
		String actual = RecentLivePage.getActivePage();
		String expected = getDriver().getCurrentUrl();
		Assert.assertEquals(actual, expected);
		String prev = RecentLivePage.getPrevPage();
		String next = RecentLivePage.getNextPage();
		int page_active = Integer.parseInt(actual.replaceAll(URL, ""));
		int page_prev = Integer.parseInt(prev.replaceAll(URL, ""));
		int page_next = Integer.parseInt(next.replaceAll(URL, ""));
		Assert.assertEquals(page_active*2, page_next+page_prev);
	}
	@Test
	public void testPrevPage(){
		WatchPage.clickDiscover();
		WatchPage.clickMore();
		RecentLivePage.clickPageNext(8);
		RecentLivePage.clickPagePrev(5);
		String URL = Property.getValueByKey("properties/config.properties", "URL")+"recent-live/";
		String actual = RecentLivePage.getActivePage();
		String expected = getDriver().getCurrentUrl();
		Assert.assertEquals(actual, expected);
		String prev = RecentLivePage.getPrevPage();
		String next = RecentLivePage.getNextPage();
		int page_active = Integer.parseInt(actual.replaceAll(URL, ""));
		int page_prev = Integer.parseInt(prev.replaceAll(URL, ""));
		int page_next = Integer.parseInt(next.replaceAll(URL, ""));
		Assert.assertEquals(page_active*2, page_next+page_prev);
	}
	@AfterMethod
	public void AfterMethod(){
		quiteSelenium();
	}
}
