package pom.download;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DownTest {

	public static void main(String[] args) {
		String driver_path = System.getProperty("user.dir")+"\\driver\\firefox\\64\\geckodriver.exe";
		System.out.println(driver_path);
		System.setProperty("webdriver.gecko.driver", driver_path);

		ProfilesIni allprofiles = new ProfilesIni(); 
		// TODO Auto-generated method stub
		//启动平时用的firefox浏览器，可以把上面"WebDriver"替换成"default"
		FirefoxProfile profile = allprofiles.getProfile("default");

		//browser.download.folderList 设置Firefox的默认 下载 文件夹。0是桌面；1是“我的下载”；2是自定义
		profile.setPreference("browser.download.folderList", "2");
		profile.setPreference("browser.download.dir", "E:\\Goluk");
		//使用默认下载路径？总是询问下载位置
		profile.setPreference("browser.download.useDownloadDir", true);
		//当一个下载开始时显示下载管理器。true为显示，false为不显示，缺省我true
		profile.setPreference("browser.download.manager.showWhenStarting",false);
		//指定无需确认即可下载的文件格式
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream,"
				+ " application/vnd.ms-excel, text/csv, application/zip,application/exe");

		//把profile作为参数传入
		DesiredCapabilities caps = new FirefoxOptions().setProfile(profile).addTo(DesiredCapabilities.firefox());
		WebDriver driver = new FirefoxDriver(caps);
		
		driver.get("http://ftp.mozilla.org/pub/firefox/releases/56.0b5/win64/zh-CN/");
		driver.findElement(By.partialLinkText("Sub")).click();;

	}

}
