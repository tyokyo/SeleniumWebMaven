package cn.testcase.setting;

import java.io.File;
import java.util.List;

import model.VP;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import cn.pages.AccountPage;
import cn.pages.setting.LiveConfigPage;
import cn.pages.setting.NavToSetting;
import cn.provider.TestDataProvider;
//https://live.sioeye.cn/settings#live-config 直播配置
public class LiveConfigCase extends VP{
	@Parameters({"browser","username","password"})
	@BeforeMethod
	public void beforeTest(String browser,String username,String password){
		initialize(browser,username,password);
		startSioeye();
		AccountPage.loginAccount();
	}
	@AfterMethod
	public void AfterMethod(){
		quiteSelenium();
	}
	/** 
	 * @Title: testLiveTitle 
	 * @Date:2017年9月21日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 修改直播标题
	 */
	@Test(description="直播标题",dataProvider="livetitle",dataProviderClass=TestDataProvider.class)
	public void testLiveTitle(String title){
		LiveConfigPage.navToLiveConfig();

		String  expected  = title;
		LiveConfigPage.inputLiveTitle(expected);
		LiveConfigPage.clickLiveSavebtn();

		NavToSetting.navToEditInfo("串码流");
		NavToSetting.navToEditInfo("个人资料");
		NavToSetting.navToEditInfo("直播配置");

		String actual = LiveConfigPage.getLiveTitle();

		Assert.assertEquals(actual, expected,"verify title is "+title);
	}
	@Test(description="直播权限",dataProvider="privilege",dataProviderClass=TestDataProvider.class)
	public void testSettingMyLivePrivilege(String privilege,String setting_privilege){
		LiveConfigPage.navToLiveConfig();
		String expect = privilege;
		LiveConfigPage.setLivePrivilege(expect);
		LiveConfigPage.clickLiveSavebtn();

		getDriver().navigate().refresh();

		String actual = LiveConfigPage.getLivePrivilegeSettings();
		Assert.assertEquals(actual, setting_privilege, "直播权限");
	}
	/** 
	 * @Title: testWhoCanViewMyLive 
	 * @Date:2017年9月21日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 设置可以看我的直播的好友
	 */
	@Test
	public void testSettingMyLiveViewers(){
		LiveConfigPage.navToLiveConfig();
		LiveConfigPage.setLivePrivilege("someone");

		List<WebElement> beforenotSelect =LiveConfigPage .watchersOKKO(false);
		List<WebElement> beforeisSelect =LiveConfigPage .watchersOKKO(true);
		if (beforenotSelect.size()>=1) {
			for (WebElement webElement : beforenotSelect) {
				webElement.click();
				wait(2);
			}
			for (WebElement webElement : beforeisSelect) {
				webElement.click();
				wait(2);
			}

			LiveConfigPage.clickSelectSomeOne_Save();
			wait(2);
			LiveConfigPage.clickLiveSavebtn();
			wait(2);

			getDriver().navigate().refresh();

			LiveConfigPage.setLivePrivilege("someone");

			List<WebElement>  viewers =  LiveConfigPage.watchsHasbeenSelected();
			List<WebElement> afterisSelect =LiveConfigPage .watchersOKKO(true);
			List<WebElement> afternotSelect =LiveConfigPage .watchersOKKO(false);

			Assert.assertEquals(beforenotSelect.size(), afterisSelect.size(),"推荐好友已经选择的");
			Assert.assertEquals(beforeisSelect.size(), afternotSelect.size(),"推荐好友未选择的");

			LiveConfigPage.clickSelectSomeOne_Close();
		}

	}
	/** 
	 * @Title: testModifyLivethumb 
	 * @Date:2017年9月21日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 直播封面
	 */
	@Test
	public void testModifyLivethumb(){
		LiveConfigPage.navToLiveConfig();
		LiveConfigPage.clickEditThumb();
		LiveConfigPage.clickSelectThumb();
		File f = new File("resource/picture/1.jpg");
		LiveConfigPage.inputThumb(f.getAbsolutePath());
		LiveConfigPage.clickSaveThumb();
		wait(60);
	}
}
