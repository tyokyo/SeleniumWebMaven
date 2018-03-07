package cn.pages.setting;

import model.VP;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import cn.pages.HomePage;

public class MyAccountPage  extends VP{
	//Sioeye喜爱直播设置页面_我的账号
	//SioeyeID
	static By  sioeyeid = By.cssSelector("div[class='radius-box me-content'] .me-input input[readonly]");
	//绑定手机号
	static By bind_phone = By.id("btn-bind-phone");
	//绑定邮箱
	static By bind_email = By.id("btn-bind-email");
	//修改手机号
	static By edit_phone = By.id("btn-edit-phone");
	//修改邮箱
	static By edit_email = By.id("btn-edit-email");
	//绑定输入密码
	static By bind_password = By.id("bind-input-password");
	//确认输入密码
	static By verify_password = By.id("verify-password");
	//关闭输入密码
	static By close = By.className("close");
	
	public static void navToMyAccount(){
		HomePage.clickAavtar();
		HomePage.clickSetting();
		NavToSetting.navToEditInfo("我的账号");
	}
	public static WebElement getSioeyeID(){
		return getElements(sioeyeid).get(0);
	}
}
