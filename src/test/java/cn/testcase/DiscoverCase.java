package cn.testcase;

import java.util.List;
import java.util.Set;

import model.VP;
import model.WaitCondition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import util.TakeScreen;
import cn.bean.FollowBean;
import cn.bean.WatchBean;
import cn.pages.AccountPage;
import cn.pages.BroadCastPage;
import cn.pages.DiscoverPage;
import cn.pages.HomePage;
import cn.pages.PromotionPage;
import cn.pages.WatchPage;
import cn.provider.TestDataProvider;

public class DiscoverCase extends VP{
	/*@Parameters({"browser","username","password"})
	@BeforeMethod
	public void beforeTest(String browser,String username,String password){
		initialize(browser,username,password);
		startSioeye();
		AccountPage.loginAccount();
	}*/
	
	@BeforeMethod
	public void BeforeMethod(){
		initialize("chrome","tyokyo@126.com","123456789");
		startSioeye();		
		AccountPage.loginAccount();
	}
	/** 
	* @Title: testWatchVideoThenLike 
	* @Date:2017年10月11日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 未登录账号，观看视频，评论，跳转到登录界面
	* @param videoDomain void
	*/
	@Test(description="播放视频-评论",dataProvider="offlinevideo",dataProviderClass=TestDataProvider.class)
	public void testDiscoverVideoThenComment (String videoDomain){
		WatchPage.clickDiscover();
		Set<String> oldhandles = getDriver().getWindowHandles();
		List<WebElement> divs = getDriver().findElements(By.cssSelector(".live-box>div"));
		boolean find = false;
		for (WebElement webElement : divs) {
			if (find) {
				break;
			}
			List<WebElement> spans = webElement.findElement(By.className("live-type")).findElements(By.tagName("span"));
			for (WebElement webElement2 : spans) {
				String domain = webElement2.getText();
				if (videoDomain.equals(domain)) {
					webElement.findElements(By.cssSelector(".live-video>a")).get(0).click();
					WaitCondition.waitInvisibilityOfElementLocated(By.tagName("video"), 60);
					Set<String> newhandles = getDriver().getWindowHandles();
					newhandles.removeAll(oldhandles);
					setDriver(getDriver().switchTo().window(newhandles.iterator().next()));
					TakeScreen.takeScreenShotWithDraw(domain);
					
					waitUntilByFindAttribute(HomePage.comment_input, "placeholder", "文明上网理性发言", 30);
					String commentString = getRandomString(10);
					HomePage.commentInput(commentString);
					HomePage.clickSendComments();
					boolean actival = waitUntilByFindText(By.cssSelector(".user-box>p"), commentString, 20);
					TakeScreen.takeScreenShotWithDraw("discover_page");
					find=true;
					Assert.assertEquals(actival, true,"send comment");
					break;
				}
			}
		}
	}
	/** 
	* @Title: testWatchVideoThenLike 
	* @Date:2017年10月11日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 登录账号，观看视频，点赞
	* @param videoDomain void
	*/
	@Test(description="播放视频-点赞",dataProvider="offlinevideo",dataProviderClass=TestDataProvider.class)
	public void testDiscoverVideoThenLike(String videoDomain){
		WatchPage.clickDiscover();
		Set<String> oldhandles = getDriver().getWindowHandles();
		List<WebElement> divs = getDriver().findElements(By.cssSelector(".live-box>div"));
		boolean find = false;
		for (WebElement webElement : divs) {
			if (find) {
				break;
			}
			List<WebElement> spans = webElement.findElement(By.className("live-type")).findElements(By.tagName("span"));
			for (WebElement webElement2 : spans) {
				String domain = webElement2.getText();
				if (videoDomain.equals(domain)) {
					webElement.findElements(By.cssSelector(".live-video>a")).get(0).click();
					WaitCondition.waitInvisibilityOfElementLocated(By.tagName("video"), 60);
					Set<String> newhandles = getDriver().getWindowHandles();
					newhandles.removeAll(oldhandles);
					setDriver(getDriver().switchTo().window(newhandles.iterator().next()));
					TakeScreen.takeScreenShotWithDraw(domain);
					PromotionPage.clickLike();
					wait(2);
					find=true;
					break;
				}
			}
		}
	}
	/** 
	* @Title: testWatchVideoThenReport 
	* @Date:2017年10月11日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 登录账号，观看视频，举报-取消
	* @param videoDomain void
	*/
	@Test(description="播放视频-举报-取消",dataProvider="offlinevideo",dataProviderClass=TestDataProvider.class)
	public void testDiscoverVideoThenReportFalse(String videoDomain){
		WatchPage.clickDiscover();
		Set<String> oldhandles = getDriver().getWindowHandles();
		List<WebElement> divs = getDriver().findElements(By.cssSelector(".live-box>div"));
		boolean find = false;
		for (WebElement webElement : divs) {
			if (find) {
				break;
			}
			List<WebElement> spans = webElement.findElement(By.className("live-type")).findElements(By.tagName("span"));
			for (WebElement webElement2 : spans) {
				String domain = webElement2.getText();
				if (videoDomain.equals(domain)) {
					webElement.findElements(By.cssSelector(".live-video>a")).get(0).click();
					WaitCondition.waitInvisibilityOfElementLocated(By.tagName("video"), 60);
					Set<String> newhandles = getDriver().getWindowHandles();
					newhandles.removeAll(oldhandles);
					setDriver(getDriver().switchTo().window(newhandles.iterator().next()));
					TakeScreen.takeScreenShotWithDraw(domain);
					
					PromotionPage.clickReport();
					List<WebElement> reasons=DiscoverPage.getReportReasonList();
					DiscoverPage.clickReportFalse();
					
					int size = reasons.size();
					for (int i = 0; i < size; i++) {
						PromotionPage.clickReport();
						DiscoverPage.getReportReasonList().get(i).click();
						DiscoverPage.clickReportFalse();
						wait(3);
					}
					find=true;
					break;
				}
			}
		}
	}
	/** 
	* @Title: testWatchVideoThenReport 
	* @Date:2017年10月11日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 登录账号，观看视频，举报-确定
	* @param videoDomain void
	*/
	@Test(description="播放视频-举报-确定",dataProvider="offlinevideo",dataProviderClass=TestDataProvider.class)
	public void testDiscoverVideoThenReportTrue(String videoDomain){
		WatchPage.clickDiscover();
		Set<String> oldhandles = getDriver().getWindowHandles();
		List<WebElement> divs = getDriver().findElements(By.cssSelector(".live-box>div"));
		boolean find = false;
		for (WebElement webElement : divs) {
			if (find) {
				break;
			}
			List<WebElement> spans = webElement.findElement(By.className("live-type")).findElements(By.tagName("span"));
			for (WebElement webElement2 : spans) {
				String domain = webElement2.getText();
				if (videoDomain.equals(domain)) {
					webElement.findElements(By.cssSelector(".live-video>a")).get(0).click();
					WaitCondition.waitInvisibilityOfElementLocated(By.tagName("video"), 60);
					Set<String> newhandles = getDriver().getWindowHandles();
					newhandles.removeAll(oldhandles);
					setDriver(getDriver().switchTo().window(newhandles.iterator().next()));
					TakeScreen.takeScreenShotWithDraw(domain);
					
					PromotionPage.clickReport();
					List<WebElement> reasons=DiscoverPage.getReportReasonList();
					DiscoverPage.clickReportFalse();
					int size = reasons.size();
					for (int i = 0; i < size; i++) {
						PromotionPage.clickReport();
						DiscoverPage.clickRecportIndex(i);
						DiscoverPage.clickReportTrue();
						wait(3);
					}
					find=true;
					break;
				}
			}
		}
	}
	/** 
	* @Title: testDiscoverVideoFollow 
	* @Date:2017年10月17日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 播放视频，添加关注
	* @param videoDomain void
	*/
	@Test(description="播放视频-Follow-UnFollow",dataProvider="offlinevideo",dataProviderClass=TestDataProvider.class)
	public void testDiscoverVideoFollow(String videoDomain){
		WatchBean beforeBean = WatchPage.getWatchBean();
		WatchPage.clickDiscover();
		Set<String> oldhandles = getDriver().getWindowHandles();
		List<WebElement> divs = getDriver().findElements(By.cssSelector(".live-box>div"));
		boolean find = false;
		for (WebElement webElement : divs) {
			if (find) {
				break;
			}
			List<WebElement> spans = webElement.findElement(By.className("live-type")).findElements(By.tagName("span"));
			for (WebElement webElement2 : spans) {
				String domain = webElement2.getText();
				if (videoDomain.equals(domain)) {
					webElement.findElements(By.cssSelector(".live-video>a")).get(0).click();
					WaitCondition.waitInvisibilityOfElementLocated(By.tagName("video"), 60);
					Set<String> newhandles = getDriver().getWindowHandles();
					newhandles.removeAll(oldhandles);
					setDriver(getDriver().switchTo().window(newhandles.iterator().next()));
					TakeScreen.takeScreenShotWithDraw(domain);
					
					String status =DiscoverPage.getFollowStatus();
					String id = DiscoverPage.getViewSioeyeId();
					DiscoverPage.clickFollow();
					wait(5);
					waitUntilByFindAttribute(DiscoverPage.follow, "data-follow-status", "follow", 5);
					WatchPage.clickWatch();
					wait(5);
					WatchBean afterBean = WatchPage.getWatchBean();
					WatchPage.clickUserFollow();
					wait(5);
					
					List<FollowBean> infos = WatchPage.getAllFollowInfo();
					if ("follow".equals(status)) {
						boolean actual = PromotionPage.hasValue(infos, id);
						Assert.assertEquals(afterBean.getFollowcount(), beforeBean.getFollowcount()+1,"follow++");
						Assert.assertEquals(actual, true);
					}else {
						boolean actual = PromotionPage.hasValue(infos, id);
						Assert.assertEquals(afterBean.getFollowcount(), beforeBean.getFollowcount()-1,"follow--");
						Assert.assertEquals(actual, false);
					}
					find=true;
					break;
				}
			}
		}
	}
	
	/** 
	* @Title: testBigFunRecommand 
	* @Date:2017年10月17日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 达人 推荐 添加关注
	*/
	@Test
	public void testBigFunRecommand(){
		WatchPage.clickDiscover();
		System.out.println(getDriver().getWindowHandle());
		DiscoverPage.clickFirstBigFunRecommand();
		windowHandleForword();
		String id = BroadCastPage.getBroadcastSioeyeid();
		BroadCastPage.clickFollow();
		wait(5);
		WatchPage.clickWatch();
		WatchPage.clickUserFollow();
		List<FollowBean> infos = WatchPage.getAllFollowInfo();
		boolean actual = PromotionPage.hasValue(infos, id);
		Assert.assertEquals(actual, true,id);
	}
	/** 
	* @Title: testRefresh 
	* @Date:2017年10月17日
	* @author qiang.zhang@ck-telecom.com
	* @Description:推荐达人-换一批
	*/
	@Test
	public void testRefresh(){
		WatchPage.clickDiscover();
		scrollToElement(HomePage.recommand_user, 5);
		List<String> before = DiscoverPage.getAllRecommandUser();
		HomePage.clickRefresh();
		List<String> after = DiscoverPage.getAllRecommandUser();
		before.removeAll(after);
		after.remove(before);
		Assert.assertEquals(before.size(), after.size());
	}
	@AfterMethod
	public void AfterMethod(){
		quiteSelenium();
	}
}
