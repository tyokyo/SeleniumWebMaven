package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaiduTest {
    public static void main(String[] args) {
    	String driver_path = System.getProperty("user.dir")+"\\driver\\chrome\\chromedriver.exe";
    	 System.setProperty("webdriver.chrome.driver", driver_path);
         ChromeOptions o = new ChromeOptions();
         o.addArguments("disable-extensions");
         o.addArguments("--start-maximized");
         o.setBinary("D:\\Browser\\Chrome\\chrome.exe");
         WebDriver driver = new ChromeDriver(o);
        driver.get("https://www.baidu.com/");
        driver.close();
    }

}