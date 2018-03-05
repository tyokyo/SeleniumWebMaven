package model;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
/**
 * @author dell
 *
 */
public class VP2 extends BaseSelenium{

	/**
	 * 判断元素是否存在
	 * @param by
	 * @return
	 */
	public static boolean isElementExist(By by){
		boolean isFind = true;
		try {
			driver.findElement(by);
		} catch (Exception e) {
			// TODO: handle exception
			isFind=false;
		}
		System.out.println("is find element returen "+isFind);
		return isFind;
	}

	/**
	 * @param by
	 */
	public static void clickBy(By by){
		WebElement element = driver.findElement(by);
		highlightElement(element);
		element.click();
	}

	/**
	 * 获取页面元素-通过xpath
	 * @param by
	 * @return
	 */
	public static WebElement getElementByXpath(By by){
		WebElement element = driver.findElement(by);
		return element;
	}

	/**字符输入
	 * @param by
	 * @param keys
	 */
	public static void sendChars(By by,String keys){
		WebElement element = driver.findElement(by);
		highlightElement(element);
		element.sendKeys(keys);
	}
	/**
	 * @param element
	 * @param keys
	 */
	public static void sendChars(WebElement element,String keys){
		highlightElement(element);
		element.sendKeys(keys);
	}

	/**等待时间
	 * @param time
	 */
	public static void wait(int time){
		try {
			Thread.currentThread();
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//页面元素截图
	public static File captureElement(WebElement element,String elementFileName){
		WrapsDriver wrapsDriver = (WrapsDriver) element;
		// 截图整个页面
		File screen = ((TakesScreenshot) wrapsDriver.getWrappedDriver()).getScreenshotAs(OutputType.FILE);
		BufferedImage img = null;
		try {
			img = ImageIO.read(screen);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 获得元素的高度和宽度
		int width = element.getSize().getWidth();
		int height = element.getSize().getHeight();
		// 创建一个矩形使面上面的高度，和宽度
		Rectangle rect = new Rectangle(width, height);
		// 得到元素的坐标
		Point p = element.getLocation();
		BufferedImage dest = img.getSubimage(p.getX(),p.getY(),rect.width,rect.height);
		//存为png格式
		try {
			ImageIO.write(dest, "png", new File(elementFileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return screen;
	}

	/**截取屏幕
	 * @param savePath
	 */
	public static void takeScreenShot(String savePath){
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile,new File(savePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 高亮元素
	 * @param element
	 */
	public static void highlightElement(WebElement element){
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].style.border='3px solid red'", element);
		jse.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",element);
	}

	/**
	 * 清除所有Cookies
	 */
	public static void deleteAllCookies(){
		driver.manage().deleteAllCookies();
	}
	public static void waitFluent(By by,int withTimeout,int pollingEvery){
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)  
				.withTimeout(withTimeout, TimeUnit.SECONDS)  
				.pollingEvery(pollingEvery, TimeUnit.SECONDS)  
				.ignoring(NoSuchElementException.class);  

		WebElement ele1 = wait.until(new Function<WebDriver, WebElement>() {  
			@Override
			public org.openqa.selenium.WebElement apply(WebDriver arg0) {
				// TODO Auto-generated method stub
				return driver.findElement(by);  
			}  
		});    
	}

	public static void explicitWaitVisibilityOfElementLocated(By by){
		// 创建一个WebDriverWait类的一个对象 wait，设置5，默认单位是秒  
		WebDriverWait wait=new WebDriverWait(driver,5); 
		WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		if(isElementExist(by)){
			System.out.println("element find");
		}else {
			System.out.println("element not  find");
		}
	}

	/**robot take windows screen
	 * @param savePath
	 */
	public static void takeScreenShotByRobot(String savePath){
		// 调用截图方法  
		BufferedImage image;
		try {
			image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(image, "png", new File(savePath)); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
}
