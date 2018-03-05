package cn.pages;

import model.VP;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

/** 
 * @ClassName: AccountPage 
 * @Description: url=https://live.sioeye.cn/account
 * 账号登录相关页面 
 * @author qiang.zhang@ck-telecom.com
 * @date 2017年8月21日 下午4:33:10 
 *  
 */
public class AccountPage extends VP{
	//用户名输入框
	public static By username = By.id("username");
	//用户登录-title
	public static By title = By.className("title");
	//密码输入框
	public static By password = By.id("password");
	//登录按钮
	public static By loginBtn = By.id("login-btn");
	//忘记密码
	public static By forgetPassword = By.id("forget-password");
	//微信登录
	public static By loginByWebChat = By.className("we-chat");
	//微博登录
	public static By loginByMicroblog = By.className("microblog");
	//QQ登录
	public static By loginByQQ = By.className("qq");
	//注册账号
	public static By regesterAccount = By.className("tips");
	//注册账号提示信息
	public static By loginMsg = By.id("login-msg-password");


	@FindBys({
		@FindBy(id = "login-msg-password"),
		@FindBy(tagName = "span")
	})
	public WebElement AB;
	/** 
	 * @Title: getUserNameElement 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 获取用户名对象
	 * @return WebElement    返回类型 
	 */
	public static WebElement getUserNameElement(){
		return getElement(username);
	}

	/** 
	 * @Title: clearUserName 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 清除用户名文本框内容
	 * @return void    返回类型 
	 */
	public static void clearUserName(){
		getUserNameElement().clear();
	}

	/** 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 输入密码
	 * @return void    返回类型 
	 */
	public static void inputUserName(String keys){
		sendKeys(username, keys);
	}

	/** 
	 * @Title: inputPassword 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 输入密码
	 * @return void    返回类型 
	 */
	public static void inputPassword(String keys){
		sendKeys(password, keys);
	}
	/** 
	 * @Title: getTitle 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 获取登录界面-用户登录，判定是否处于登录界面
	 * @return WebElement    返回类型 
	 */
	public static WebElement getTitle(){
		return getElement(title);
	}
	/** 
	 * @Title: InLoginPage 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 是否处于登录状态
	 * @return boolean    返回类型 
	 */
	public static boolean InLoginPage(){
		boolean accountLogin = false;
		String title = getTitle().getText();
		if ("用户登录".equals(title)) {
			accountLogin=true;
		}
		return  accountLogin;
	}
	/** 
	 * @Title: clickLoginAccountBtn 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @return void    返回类型 
	 */
	public static void clickLoginAccountBtn(){
		clickElement(loginBtn);
		waitUntilByFind(HomePage.notifications, 10);
	}

	/** 
	 * @Title: clickForgetPasswd 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 点击忘记密码
	 * @return void    返回类型 
	 */
	public static void clickForgetPasswd(){
		clickElement(forgetPassword);
	}
	/** 
	 * @Title: clickLoginByWebChat 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 微信登录
	 * @return void    返回类型 
	 */
	public static void clickLoginByWebChat(){
		clickElement(loginByWebChat);
	}
	/** 
	 * @Title: clickLoginByMicroblog 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 微博登录
	 * @return void    返回类型 
	 */
	public static void clickLoginByMicroblog(){
		clickElement(loginByMicroblog);
	}
	/** 
	 * @Title: clickLoginByQQ 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: QQ登录
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void clickLoginByQQ(){
		clickElement(loginByQQ);
	}

	/** 
	 * 注册账号  
	 * @Title: clickRegesterAccount 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 注册账号 
	 * @return void    返回类型 
	 * @throws 
	 */
	public  static void clickRegesterAccount(){
		clickElement(regesterAccount);
	}
	/** 
	 * @Title: loginAccount 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 登录账号
	 * @return void    返回类型 
	 */
	public static void loginAccount(){
		HomePage.clickLoginbtn();
		inputUserName(getBean().getUsername());
		inputPassword(getBean().getPassword());
		clickLoginAccountBtn();
	}
	
	/** 
	* @Title: logoutAccount 
	* @Date:2017年9月14日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 退出登录
	*/
	public static void logoutAccount(){
		HomePage.clickAavtar();
		HomePage.clickLogout();
		getDriver().manage().deleteAllCookies();
	}
}
