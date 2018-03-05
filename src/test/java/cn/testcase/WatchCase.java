package cn.testcase;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import cn.bean.FollowBean;
import cn.bean.WatchBean;
import cn.pages.AccountPage;
import cn.pages.WatchPage;
import cn.pages.setting.PersonalProfilePage;
import cn.provider.TestDataProvider;
import model.VP;
import model.WaitCondition;

public class WatchCase extends VP{
	@Parameters({"browser","username","password"})
	@BeforeMethod
	public void beforeTest(String browser,String username,String password){
		initialize(browser,username,password);
		startSioeye();
		AccountPage.loginAccount();
	}
	
	/*@BeforeMethod
	public void BeforeMethod(){
		initialize("firefox","tyokyo@126.com","123456789");
		startSioeye();		
		deleteAllVideoInDownloadFolder();
		AccountPage.loginAccount();
	}*/
	
	
	/** 
	* @Title: addRecommandFollow 
	* @Date:2017年10月13日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 添加推荐关注
	*/
	@Test
	public void addRecommandFollow(){
		WaitCondition.waitElementToBeClickable(WatchPage.userAddFirstRecommandFollow, 10);
		String id = WatchPage.getFirstRecommandFollowId();
		WatchPage.clickFirstRecommandFollowbtn();
		WatchPage.clickUserFollow();
		List<FollowBean> infos = WatchPage.getAllFollowInfo();
		boolean actual = false;
		for (FollowBean info : infos) {
			if (id.equals(info.getSioeyeid())) {
				actual=true;
				break;
			}
		}
		Assert.assertEquals(actual, true,"follow success");
	}
	/** 
	* @Title: followDel 
	* @Date:2017年10月13日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 取消关注
	*/
	@Test
	public void followDel(){
		WatchPage.clickUserFollow();
		WebElement first = WatchPage.getFirstFollow();
		String id = first.findElement(By.className("follow-id")).getText().replaceAll("SioeyeID:", "").trim();
		clickElement(first.findElement(By.cssSelector(".follow-btn")));
		wait(5);
		List<FollowBean> infos = WatchPage.getAllFollowInfo();
		boolean actual = false;
		for (FollowBean info : infos) {
			if (id.equals(info.getSioeyeid())) {
				actual=true;
				break;
			}
		}
		Assert.assertEquals(actual, false,"del follow success");
	}
	/** 
	* @Title: fansAddFollow 
	* @Date:2017年10月13日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 添加粉丝关注
	*/
	@Test 
	public void fansAddFollow(){
		WatchPage.clickUserFans();
		WebElement first = WatchPage.getFansElement(true);
		String id = first.findElement(By.className("follow-id")).getText().replaceAll("SioeyeID:", "").trim();
		clickElement(first.findElement(By.cssSelector(".follow-btn")));
		wait(5);
		List<FollowBean> infos = WatchPage.getAllFollowInfo();
		boolean actual = false;
		for (FollowBean info : infos) {
			if (id.equals(info.getSioeyeid())) {
				if (info.getFollow().equals("unfollow")) {
					actual=true;
				}
				break;
			}
		}
		Assert.assertEquals(actual, true,"fans add follow success");
	}
	/** 
	* @Title: fansDelFollow 
	* @Date:2017年10月13日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 删除粉丝
	*/
	@Test
	public void fansDelFollow(){
		WatchPage.clickUserFans();
		WebElement first = WatchPage.getFansElement(false);
		String id = first.findElement(By.className("follow-id")).getText().replaceAll("SioeyeID:", "").trim();
		clickElement(first.findElement(By.cssSelector(".follow-btn")));
		wait(5);
		List<FollowBean> infos = WatchPage.getAllFollowInfo();
		boolean actual = false;
		for (FollowBean info : infos) {
			if (id.equals(info.getSioeyeid())) {
				if (info.getFollow().equals("follow")) {
					actual=true;
				}
				break;
			}
		}
		Assert.assertEquals(actual, true,"fans del follow success");
	}
	/** 
	* @Title: videoDelete 
	* @Date:2017年10月13日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 删除视频
	*/
	@Test
	public void videoDelete(){
		WatchPage.clickUserVideo();
		WebElement videoElement=WatchPage.getVideoElement();
		String url = WatchPage.getVideosrc(videoElement);
		WatchPage.clickDeleteVideo(videoElement);
		WaitCondition.waitPresenceOfElementLocated(By.className("confirm-btn"), 10);
        WaitCondition.waitElementToBeClickable(By.xpath("//div[@class='confirm-btn']//button[1]"), 10).click();
        WaitCondition.waitElementToBeClickable(By.className("app-content-right"), 10);
        wait(10);
        List<String> times = WatchPage.getAllVideosrc();
       Assert.assertEquals( times.contains(url), false,"delete success-"+url);
	}
	/** 
	* @Title: videoDownLoad 
	* @Date:2017年10月13日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 下载视频
	*/
	@Test
	public void videoDownLoad(){
		WatchPage.clickUserVideo();
		WebElement videoElement=WatchPage.getVideoElement();
		String filename = WatchPage.getVideoFilename(videoElement);
		WatchPage.clickDownloadVideo(videoElement);
		boolean actival = waitFileDownload(filename+".mp4", 120);
		 Assert.assertEquals( actival, true,"download success-"+filename);
	}
	
	/** 
	 * @Title: testModifyNickName 
	 * @Date:2017年9月14日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 修改昵称
	 */
	@Test(description="昵称修改",dataProvider="nickname",dataProviderClass=TestDataProvider.class)
	public void testModifyNickName(String nickname){
		PersonalProfilePage.navToInfo();
		
		PersonalProfilePage.clearNickname();
		String expectNickName = nickname;
		PersonalProfilePage.inputNickname(expectNickName);
		PersonalProfilePage.clickSavePersonalProfile();

		//刷新页面验证
		getDriver().navigate().refresh();

		WaitCondition.waitTextToBePresentInElementValue(PersonalProfilePage.nickname, expectNickName, 20);
		
		WatchPage.clickWatch();
		WatchBean bean =WatchPage.getWatchBean();
		Assert.assertEquals(bean.getNickname(), nickname);
	}
	/** 
	* @Title: testLocation 
	* @Date:2017年9月28日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 修改位置
	*/
	@Test
	public void testLocation(){
		PersonalProfilePage.navToInfo();
		
		PersonalProfilePage.inputArea("四川-宜宾");
		PersonalProfilePage.clickSavePersonalProfile();

		//刷新页面验证
		getDriver().navigate().refresh();

		WatchPage.clickWatch();
		WatchBean bean =WatchPage.getWatchBean();
		Assert.assertEquals(bean.getLocation(), "四川-宜宾");
	}
	/** 
	* @Title: testPersonalizedSignature 
	* @Date:2017年9月28日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 修改个性签名 
	* @param value void
	*/
	@Test(description="个性签名",dataProvider="motoo",dataProviderClass=TestDataProvider.class)
	public void testPersonalizedSignature(String value){
		PersonalProfilePage.navToInfo();
		
		String expect = value;
		PersonalProfilePage.inputMotto(expect);;
		PersonalProfilePage.clickSavePersonalProfile();

		//刷新页面验证
		getDriver().navigate().refresh();
		
		WatchPage.clickWatch();
		WatchBean bean =WatchPage.getWatchBean();
		Assert.assertEquals(bean.getSignature(), value);
	}
	@AfterMethod
	public void AfterMethod(){
		quiteSelenium();
	}
}
