package pom;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CookieOperation {

    public static void main(String[] args) {

    	String driver_path = System.getProperty("user.dir")+"\\driver\\firefox\\64\\geckodriver.exe";
		System.out.println(driver_path);
		System.setProperty("webdriver.gecko.driver", driver_path);
		
        WebDriver driver = new FirefoxDriver();
        driver.get("http://www.baidu.com");
        //driver.manage().window().maximize();

        // 获取所有cookie个数
        System.out.println(driver.manage().getCookies().size());

        // 增加cookie
        Cookie cookie = new Cookie("username", "name", "/", null);
        driver.manage().addCookie(cookie);
        driver.manage().addCookie(new Cookie("password", "ppppwwww", "/", null));
        
        // 以name获取cookie
        String name = driver.manage().getCookieNamed("username").getValue();
        String info = "用户名是： " + name;
        String js = "alert(\"" + info + "\");";
        System.out.println(js);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript(js);
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        driver.switchTo().alert().dismiss();
        
        // 以name删除cookie
        driver.manage().deleteCookieNamed("password");

        // 再次获取所有cookie个数，应该比之前多一个
        System.out.println(driver.manage().getCookies().size());

        driver.quit();

    }
}