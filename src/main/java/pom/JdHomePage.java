package pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JdHomePage {
	
	// 元素定位
	//登录链接
    @FindBy (id="ttbar-login")
	WebElement login_link;
    
    //账户登录
    @FindBy (xpath="//*/div[@class='login-form']/div[2]/a")
   	WebElement login_withAccount;
    
    //输入用户名框
    @FindBy (id="loginname")
    WebElement inputBox_username;
    
    //输入密码
    @FindBy (id="nloginpwd")
    WebElement inputBox_password;
    
    //登录按钮
    @FindBy (id="loginsubmit")
    WebElement login_submitBtn;
	
	// 业务逻辑和操作方法
    
    //登录方法
    public void login(String username, String password){
    	login_link.click();
    	login_withAccount.click();
    	inputBox_username.sendKeys(username);
    	inputBox_password.sendKeys(password);
    	login_submitBtn.click();
    }
    
}

