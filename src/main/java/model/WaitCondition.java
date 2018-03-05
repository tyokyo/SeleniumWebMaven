package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.Log;

public class WaitCondition extends VP {
	
	/** 
	* @Title: waitTitleContains 
	* @Date:2017年9月13日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 等待页面title包含标题
	* @param title
	* @param timeOutInSeconds 
	*/
	public static void waitTitleContains(String title,int timeOutInSeconds){
		WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
		wait.until(ExpectedConditions.titleContains(title));
	}
	public static WebElement waitElementToBeClickable(By by,int timeOutInSeconds){
		WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
		return wait.until(ExpectedConditions.elementToBeClickable(by));
	}
	public static void waitElementToBeSelected(WebElement element,int timeOutInSeconds){
		WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeSelected(element));
	}
	public static WebElement waitPresenceOfElementLocated(By by,int timeOutInSeconds){
		WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
		return wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}
	public static void waitTextToBePresentInElementValue(By locator,String text,int timeOutInSeconds){
		Log.info(locator.toString(), "-text="+text,"timeOutInSeconds="+timeOutInSeconds);
		WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
		wait.until(ExpectedConditions.textToBePresentInElementValue(locator, text));
	}
	public static void waitAertIsPresent(int timeOutInSeconds){
		WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
		wait.until(ExpectedConditions.alertIsPresent());
	}
	public static void waitAertIsNotPresent(int timeOutInSeconds){
		WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
		wait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
	}
	public static void waitInvisibilityOfElementLocated(By locator ,int timeOutInSeconds){
		WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	public static void waitTextToBePresentInElementLocated(By locator,String text,int timeOutInSeconds){
		Log.info(locator.toString(), "-text="+text,"timeOutInSeconds="+timeOutInSeconds);
		WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
	}
	public static void waitInvisibilityOfElementWithText(By locator,String text,int timeOutInSeconds){
		Log.info(locator.toString(), "-text="+text,"timeOutInSeconds="+timeOutInSeconds);
		WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
		wait.until(ExpectedConditions.invisibilityOfElementWithText(locator, text));
	}
	public static void waitInvisibilityOf(WebElement element,int timeOutInSeconds){
		Log.info("wati element in timeOutInSeconds="+timeOutInSeconds);
		WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	public static void waitInvisibilityOf(By locator,int timeOutInSeconds){
		Log.info(locator.toString(),"timeOutInSeconds="+timeOutInSeconds);
		WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
}
