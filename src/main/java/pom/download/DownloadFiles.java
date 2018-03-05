package pom.download;

//自动化下载某个文件
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class DownloadFiles {
	public static String DownloadFilepath = "D:\\DownloadFiles";
	WebDriver driver;
	String url;
	JavascriptExecutor js;
	@Test
	public void test() throws Exception
	{
		String driver_path = System.getProperty("user.dir")+"\\driver\\firefox\\64\\geckodriver.exe";
		System.setProperty("webdriver.gecko.driver", driver_path);
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);
		@SuppressWarnings("deprecation")
		FirefoxDriver driver = new FirefoxDriver(capabilities);
		driver.get(url); 
		driver.findElement(By.partialLinkText("Stub")).click();

		try 
		{
			Thread.sleep(3000);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public void beforeMethod() 
	{
		url="http://ftp.mozilla.org/pub/firefox/releases/35.0b8/win32/zh-CN/";
	}

	@AfterMethod
	public void afterMethod() 
	{
		driver.quit();
	}

	public static FirefoxProfile FilefoxDriverProfile () throws Exception
	{
		//声明一个profile对象
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("browser.download.folderList",2);
		profile.setPreference("browser.download.manager.showWhenStarting", false);
		profile.setPreference("browser.download.dir", DownloadFilepath);
		profile.setPreference("browser.helperApps.neverAsk.openFile",
				"application/xhtml+xml,application/xml,application/x-msdownload,application/octet/octet-stream,application/exe,txt/csv,application/pdf,application/x-msexcl,application/x-excel,application/excel,image/png,image/jpeg,text/html,text/plain,text/x-c");
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
				"application/xhtml+xml,application/xml,application/x-msdownload,application/octet/octet-stream,application/exe,txt/csv,application/pdf,application/x-msexcl,application/x-excel,application/excel,image/png,image/jpeg,text/html,text/plain,text/x-c");
		//不会打开未知MIMe类型
		profile.setPreference("browser.helperApps.alwaysAsk.force", false);
		//不会弹出警告框
		profile.setPreference("browser.download.manager.alertOnEXEopen", false);
		profile.setPreference("browser.download.manager.focusWhenStarting", false);
		profile.setPreference("browser.download.manager.useWindow", false);
		profile.setPreference("browser.download.manager.showAlertOnComplete", false);
		profile.setPreference("browser.download.manager.closewhenDone", false);
		return profile;
	}
} 
