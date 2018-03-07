package cn.testcase.setting;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import cn.pages.AccountPage;
import cn.pages.setting.MyAccountPage;
import model.VP;

/** 
* @ClassName: MyAccountCase 
* @Description: //https://live.sioeye.cn/settings#account 我的账号
* @author qiang.zhang@ck-telecom.com
* @date 2017年10月18日 下午3:05:14 
*  
*/
public class MyAccountCase extends VP{
	@Parameters({"browser","username","password"})
	@BeforeMethod
	public void beforeTest(String browser,String username,String password){
		initialize(browser,username,password);
		startSioeye();
		AccountPage.loginAccount();
	}
	@AfterTest
	public void afterTest(){
		quiteSelenium();
	}
	@Test
	public void testSioeyeID_Disable(){
		MyAccountPage.navToMyAccount();
		WebElement idEmt = MyAccountPage.getSioeyeID();
		String expect = idEmt.getAttribute("value");
		getDriver().navigate().refresh();
		try {
			MyAccountPage.getSioeyeID().sendKeys(getRandomString(5));
			idEmt = MyAccountPage.getSioeyeID();
			String actual = idEmt.getAttribute("value");
			Assert.assertEquals(actual, expect,"disable for modify sioeyeid");
		} catch (ElementNotVisibleException e) {
			// TODO: handle exception
		}
	}
}
