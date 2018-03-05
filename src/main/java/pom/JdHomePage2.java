package pom;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JdHomePage2 {
	
	WebDriver driver;
	// 元素定位
	//登录链接
	By login_link = By.id("ttbar-login");
   
    //账户登录
	By login_withAccount = By.xpath("//*/div[@class='login-form']/div[2]/a");
   
    //输入用户名框
    By inputBox_username = By.id("loginname");
    
    //输入密码
    By inputBox_password = By.id("nloginpwd");
    
    //登录按钮
    By login_submitBtn = By.id("loginsubmit");
	
	// 业务逻辑和操作方法
    // 构造方法
    public JdHomePage2(WebDriver driver){
    	this.driver = driver;
    }
    
    //点击登录链接
    public void clickLoginLink(){
    	driver.findElement(login_link).click();
    	// 点击账户登录，不点击的话默认是二维码扫描登录
    	driver.findElement(login_withAccount).click();
    }
    
    // 登录步骤拆分-输入用户名
    public void inputUsername(String username){
    	driver.findElement(inputBox_username).sendKeys(username);
    	
    }
    
    // 登录步骤拆分-输入密码
    public void inputPassword(String password){
    	driver.findElement(inputBox_password).sendKeys(password);
    	
    }
    
    // 点击登录按钮
    public void clickLoginBtn(){
    	driver.findElement(login_submitBtn).click();
    	driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }
    
    //重构一个登录方法
    public void login(String name, String pass){
    	driver.findElement(login_link).click();
    	// 点击账户登录，不点击的话默认是二维码扫描登录
    	driver.findElement(login_withAccount).click();
    	
    	driver.findElement(inputBox_username).sendKeys(name);
    	driver.findElement(inputBox_password).sendKeys(pass);
    	driver.findElement(login_submitBtn).click();
    	driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    	
    }

}
