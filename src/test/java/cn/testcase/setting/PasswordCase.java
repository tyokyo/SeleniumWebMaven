package cn.testcase.setting;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import cn.pages.AccountPage;
import model.VP;
//https://live.sioeye.cn/settings#password 修改密码
public class PasswordCase extends VP{
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
}
