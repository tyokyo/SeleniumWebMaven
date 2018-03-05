package cn.testcase;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import cn.pages.AccountPage;
import cn.pages.DiscoverPage;
import cn.pages.HomePage;
import cn.pages.PromotionPage;
import cn.pages.WatchPage;
import cn.provider.TestDataProvider;
import model.VP;
import model.WaitCondition;
import util.TakeScreen;

/** 
 * @ClassName: HomePageCase 
 * @Description: https://live.sioeye.cn/ 未登录账号
 * @author qiang.zhang@ck-telecom.com
 * @date 2017年8月21日 下午3:16:58 
 *  
 */
@Test
public class HomePageCase  extends VP{
	@Parameters({"browser","username","password"})
	@BeforeMethod
	public void beforeTest(String browser,String username,String password){
		initialize(browser,username,password);
		startSioeye();
	}
	/** 
	 * @Title: testPlayWonderfulLive 
	 * @Date:2017年9月26日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 视频观看-播放第一个视频
	 */
	@Test(description="播放视频",dataProvider="offlinevideo",dataProviderClass=TestDataProvider.class)
	public void testWatchVideo(String videoDomain){
		WatchPage.clickDiscover();
		List<WebElement> divs = getDriver().findElements(By.cssSelector(".live-box>div"));
		for (WebElement webElement : divs) {
			List<WebElement> spans = webElement.findElement(By.className("live-type")).findElements(By.tagName("span"));
			for (WebElement webElement2 : spans) {
				String domain = webElement2.getText();
				if (videoDomain.equals(domain)) {
					webElement.findElements(By.cssSelector(".live-video>a")).get(0).click();
					WaitCondition.waitInvisibilityOfElementLocated(By.tagName("video"), 60);
					TakeScreen.takeScreenShotWithDraw(domain);
					break;
				}
			}
		}
	}
	/** 
	 * @Title: testWatchVideoThenFollow 
	 * @Date:2017年10月11日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 未登录账号，观看视频，添加关注，跳转到登录界面
	 * @param videoDomain void
	 */
	@Test(description="播放视频+Follow",dataProvider="offlinevideo",dataProviderClass=TestDataProvider.class)
	public void testWatchVideoThenFollow(String videoDomain){
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
					PromotionPage.clickFollow();
					WaitCondition.waitElementToBeClickable(AccountPage.username, 60);
					TakeScreen.takeScreenShotWithDraw("account_page");
					find=true;
					Assert.assertEquals(isElementExist(AccountPage.password,5), true,"login page");
					break;
				}
			}
		}
	}
	/** 
	 * @Title: testWatchVideoThenReport 
	 * @Date:2017年10月11日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 未登录账号，观看视频，举报，跳转到登录界面
	 * @param videoDomain void
	 */
	@Test(description="播放视频+举报",dataProvider="offlinevideo",dataProviderClass=TestDataProvider.class)
	public void testWatchVideoThenReport(String videoDomain){
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
					WaitCondition.waitElementToBeClickable(AccountPage.username, 60);
					TakeScreen.takeScreenShotWithDraw("account_page");
					find=true;
					Assert.assertEquals(isElementExist(AccountPage.password,5), true,"login page");
					break;
				}
			}
		}
	}

	/** 
	 * @Title: testWatchVideoThenLike 
	 * @Date:2017年10月11日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 未登录账号，观看视频，点赞，跳转到登录界面
	 * @param videoDomain void
	 */
	@Test(description="播放视频+点赞",dataProvider="offlinevideo",dataProviderClass=TestDataProvider.class)
	public void testWatchVideoThenLike(String videoDomain){
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
					WaitCondition.waitElementToBeClickable(AccountPage.username, 60);
					TakeScreen.takeScreenShotWithDraw("account_page");
					find=true;
					Assert.assertEquals(isElementExist(AccountPage.password,5), true,"login page");
					break;
				}
			}
		}
	}
	/** 
	 * @Title: testWatchVideoThenLike 
	 * @Date:2017年10月11日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 未登录账号，观看视频，评论，跳转到登录界面
	 * @param videoDomain void
	 */
	@Test(description="播放视频+评论",dataProvider="offlinevideo",dataProviderClass=TestDataProvider.class)
	public void testWatchVideoThenComment (String videoDomain){
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
					HomePage.commentInput("来了");
					HomePage.clickSendComments();
					WaitCondition.waitElementToBeClickable(AccountPage.username, 60);
					TakeScreen.takeScreenShotWithDraw("account_page");
					find=true;
					Assert.assertEquals(isElementExist(AccountPage.password,5), true,"login page");
					break;
				}
			}
		}
	}
	/** 
	 * @Title: testSearchVideo 
	 * @Date:2017年10月11日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 搜索
	 */
	public void testSearchVideo(){
		HomePage.clickSearchbtn();
		HomePage.searchInput("成都");
		HomePage.clickSearchbtns();
		List<WebElement> results = HomePage.searchVideoResult();
		TakeScreen.takeScreenShot();
		if (results.size()==0) {
			Assert.assertEquals(true, false,"No result searched");
		}

	}
	/** 
	 * @Title: testSearchUser 
	 * @Date:2017年10月17日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 搜索用户
	 */
	public void testSearchUser(){
		HomePage.clickSearchbtn();
		HomePage.searchInput("成都");
		HomePage.clickSearchbtns();
		HomePage.clickSearchuser();
		HomePage.searchInput("a");
		HomePage.clickSearchbtns();
		List<WebElement> results = HomePage.searchUserResult();
		TakeScreen.takeScreenShot();
		if (results.size()==0) {
			Assert.assertEquals(true, false,"No result searched");
		}
	}

	/** 
	 * @Title: testNavToWatch 
	 * @Date:2017年10月17日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 点击关注-导航到登录页面
	 */
	public void testNavToWatch(){
		HomePage.clickFollow();
		WaitCondition.waitElementToBeClickable(AccountPage.username, 60);
		TakeScreen.takeScreenShotWithDraw("account_page");
		Assert.assertEquals(isElementExist(AccountPage.password,5), true,"login page");
	}
	/** 
	 * @Title: testNavToNotifications 
	 * @Date:2017年10月17日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 点击消息-导航到登录页面
	 */
	public void testNavToNotifications(){
		HomePage.clickNotifacations();;
		WaitCondition.waitElementToBeClickable(AccountPage.username, 60);
		TakeScreen.takeScreenShotWithDraw("account_page");
		Assert.assertEquals(isElementExist(AccountPage.password,5), true,"login page");
	}
	/** 
	 * @Title: testRefresh 
	 * @Date:2017年10月17日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description:推荐达人-换一批
	 */
	public void testRefresh(){
		scrollToElement(HomePage.recommand_user, 10);
		List<String> before = DiscoverPage.getAllRecommandUserHref();
		HomePage.clickRefresh();
		List<String> after = DiscoverPage.getAllRecommandUserHref();
		before.removeAll(after);
		after.remove(before);
		Assert.assertEquals(before.size(), after.size());
	}
	@AfterMethod
	public void afterTest(){
		quiteSelenium();
	}
}
