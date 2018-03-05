package util;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


public class Test1 {
	public static int  add(int x,int y){
		int sum = 0;
		for (int i = 0; i < y; i++) {
			sum = sum +x+i;
		}
		return sum;
	}
	public static void main(String[] args) {
		/*String driver_path = System.getProperty("user.dir")+"\\driver\\ie\\64\\IEDriverServer.exe";
		System.setProperty("webdriver.ie.driver", driver_path);    

		//初始化一个IE浏览器实例，实例名称叫driver    
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("ignoreProtectedModeSettings", true);
		WebDriver driver = new  InternetExplorerDriver(capabilities); */

		/*String firefox=System.getProperty("user.dir")+"\\browser\\firefox49.0\\bin\\firefox.exe";
		System.setProperty("webdriver.firefox.bin", firefox);*/

		String driver_path = System.getProperty("user.dir")+"\\driver\\firefox\\32\\geckodriver.exe";
		System.setProperty("webdriver.gecko.driver", driver_path);

		ProfilesIni pi=new ProfilesIni();
		FirefoxProfile profile=pi.getProfile("default");
		WebDriver  driver=new FirefoxDriver(new FirefoxOptions().setProfile(profile));
		//最大化窗口    
		//driver.manage().window().maximize();    
		//设置隐性等待时间    
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);    

		// get()打开一个站点    
		driver.get("https://www.baidu.com");    
		//getTitle()获取当前页面title的值    
		System.out.println("当前打开页面的标题是： "+ driver.getTitle());    

		//关闭并退出浏览器    
		driver.quit();    
	}

}
