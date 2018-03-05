package cn.testcase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import cn.pages.AccountPage;
import util.Property;
import model.VP;
import model.WaitCondition;

/** 
* @ClassName: AccountCase 
* @Description: https://live.sioeye.cn/account
* @author qiang.zhang@ck-telecom.com
* @date 2017年10月18日 下午3:25:26 
*  
*/
public class AccountCase extends VP{
	By by  = By.xpath("//span[text()=\"用户名或密码错误\"]");
	
/*	@Parameters({"browser","username","password"})
	@BeforeMethod
	public void beforeTest(String browser,String username,String password){
		initialize(browser,username,password);
		startSioeye();
	}*/
	
	
	@BeforeMethod
	public void BeforeMethod(){
		initialize("chrome","tyokyo@126.com","123456789");
		startSioeye();		
	}
	
	/** 
	 * @Title: testLogonSuccessBySioeyeId 
	 * @Date:2017年9月13日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 采用 sioeye id 登录Web
	 */
	@Test
	public void testLoginSuccessBySioeyeId(){
		String username = Property.getValueByKey(accountPath, "sioeye_id");
		String password = Property.getValueByKey(accountPath, "sioeye_password");
		initialize(getBean().getBrowser(),username,password);
		startSioeye();
		AccountPage.loginAccount();
	}
	@Test
	public void testLoginErrorSioeyeId(){
		String username = Property.getValueByKey(accountPath, "sioeye_id");
		String password = Property.getValueByKey(accountPath, "sioeye_password");
		initialize(getBean().getBrowser(),username+"a",password);
		startSioeye();
		AccountPage.loginAccount();
		WaitCondition.waitPresenceOfElementLocated(by, 20);
	}
	@Test
	public void testLoginErrorPassword(){
		String username = Property.getValueByKey(accountPath, "sioeye_id");
		String password = Property.getValueByKey(accountPath, "sioeye_password");
		initialize(getBean().getBrowser(),username,password+"a");
		startSioeye();
		AccountPage.loginAccount();
		WaitCondition.waitPresenceOfElementLocated(by, 20);
	}
	@Test
	public void testLoginEmail(){
		String username = Property.getValueByKey(accountPath, "email");
		String password = Property.getValueByKey(accountPath, "email_password");
		initialize(getBean().getBrowser(),username,password);
		startSioeye();
		AccountPage.loginAccount();
	}
	@Test
	public void testLoginPhone(){
		String username = Property.getValueByKey(accountPath, "phone_number");
		String password = Property.getValueByKey(accountPath, "phone_password");
		initialize(getBean().getBrowser(),username,password);
		startSioeye();
		AccountPage.loginAccount();
		new WebDriverWait(getDriver(), 20).until(ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("login-btn"))));
	}
	@AfterTest
	public void afterTest(){
		quiteSelenium();
	}
}
