package model;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.remote.DesiredCapabilities;

import util.Log;
import util.ParameterBean;
import util.Property;

import org.apache.log4j.PropertyConfigurator; 
/** 
 * @ClassName: BaseSelenium 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author qiang.zhang@ck-telecom.com
 * @date 2017年9月14日 下午2:52:37 
 *  
 */
public class BaseSelenium {
	public static  WebDriver driver;
	private static  ParameterBean  bean;
	public static ParameterBean getBean() {
		return bean;
	}
	public static WebDriver getDriver() {
		return driver;
	}
	public static void setDriver(WebDriver driver) {
		BaseSelenium.driver=driver;
	}

	/** 
	 * @Title: startSioeye 
	 * @Date:2017年9月13日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 启动被测页面
	 */
	public static void launchUrl(String url){
		String browser = getBean().getBrowser().toUpperCase();
		Log.info("start browser-"+browser);
		String browserUpper = browser.toUpperCase();
		if ("FIREFOX".equals(browserUpper)) {
			initFireFoxDriver(url);
		}
		if ("CHROME".equals(browserUpper)) {
			initChromeDriver(url);
		}
		if ("IE".equals(browserUpper)) {
			initIE(url);;
		}
		driver.manage().deleteAllCookies();
	}
	public static void startSioeye(){
		String URL = Property.getValueByKey("properties/config.properties", "URL");
		launchUrl(URL);
	}
	/**
	 * 初始化driver 参数
	 */
	public  static void initialize(String browser,String username,String password){
		PropertyConfigurator.configure(".\\Log4j.properties");  
		bean = new ParameterBean();
		bean.setBrowser(browser);
		bean.setUsername(username);
		bean.setPassword(password);
	}
	private static void initFireFoxDriver(String url){
		WindowsUtils.killByName("firefox.exe");
		WindowsUtils.killByName("geckodriver.exe");
		
		Log.info("init firefox browser");
		String driver_path = System.getProperty("user.dir")+"\\driver\\firefox\\64\\geckodriver.exe";
		System.setProperty("webdriver.gecko.driver", driver_path);
		
		/*FirefoxOptions options = new FirefoxOptions();
		String firefox=System.getProperty("user.dir")+"\\browser\\firefox49.0\\bin\\firefox.exe";
		//C:\Program Files\Mozilla Firefox
		options.setBinary(firefox);
		options.addArguments("--headless");
		driver = new FirefoxDriver(options);*/
		
		ProfilesIni pi=new ProfilesIni();
		FirefoxProfile profile=pi.getProfile("default");
		driver=new FirefoxDriver(new FirefoxOptions().setProfile(profile));
		
		driver.manage().window().maximize();    
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);    
		startTestAddress(url);
	}
	public static void startTestAddress(String url){
		//driver.manage().window().maximize();  
		driver.manage().deleteCookieNamed("JSESSIONID");
		driver.get(url);
		driver.manage().deleteAllCookies();
		//# 定位对象时给3s的时间 # 如果3s内还定位不到则抛出异常 
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		//# 页面加载超时时间设置为5s 
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS); 
		//# 异步脚本的超时时间设置成3s 
		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
	}
	/** 
	 * @Title: initChromeDriver 
	 * @Date:2017年8月22日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: http://chromedriver.storage.googleapis.com/2.31/notes.txt    注意版本匹配
	 * @return void    返回类型 
	 */
	private static void initChromeDriver(String url){
		WindowsUtils.killByName("chromedriver.exe");
		WindowsUtils.killByName("chrome.exe");

		Log.info("init chrome browser");
		String driver_path = System.getProperty("user.dir")+"\\driver\\chrome\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", driver_path);    
		ChromeOptions o = new ChromeOptions();
		o.addArguments("disable-extensions");
		o.addArguments("--start-maximized");
		o.addArguments("download.default_directory","E:\\Goluk");
		o.setBinary(System.getProperty("user.dir")+"\\browser\\chrome\\chrome.exe");
		//初始化一个chrome浏览器实例，实例名称叫driver    
		driver = new ChromeDriver(o);
		//String URL = Property.getValueByKey("properties/config.properties", "URL");
		startTestAddress(url);
	}

	/**
	 *建议 IE11
	 *Internet选项->安全; 把Internet站点，
	 *本地Intrant,受信任站点 三个地方的安全界面都设置相同等级
	 *例如都设置中；
	 */
	private static  void initIE(String url){
		Log.info("init ie browser");
        String IEPath = "C:\\Program Files\\Internet Explorer\\iexplore.exe";
		String driver_path = System.getProperty("user.dir")+"\\driver\\ie\\32\\IEDriverServer.exe";
		
		System.setProperty("webdriver.ie.driver", driver_path);    
		System.setProperty("webdriver.ie.bin", IEPath);

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		capabilities.setCapability("ignoreProtectedModeSettings", true);
		
		driver = new  InternetExplorerDriver(capabilities); 
		driver.manage().window().maximize();    
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);    
		startTestAddress(url);
	}

	/** 
	 * @Title: quiteSelenium 
	 * @Date:2017年9月13日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 关闭并退出浏览器,清除Cookies
	 */
	public static void quiteSelenium(){
		try {
			Log.info("quit  browser");
			driver.manage().deleteAllCookies();
			//关闭所有的窗口
			Set<String> handles = getDriver().getWindowHandles();
			for (String h : handles) {
				getDriver().switchTo().window(h).close();;
			}
			driver.close();
			driver.quit();
			WindowsUtils.killByName("chromedriver.exe");
			WindowsUtils.killByName("geckodriver.exe");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/** 
	 * @Title: restart 
	 * @Date:2017年9月14日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 关闭并退出浏览器,清除Cookies，再启动测试页面
	 */
	public static void restart(){
		String url = driver.getCurrentUrl();
		driver.manage().deleteAllCookies();
		driver.close();
		driver.quit();
		launchUrl(url);
	}
}
