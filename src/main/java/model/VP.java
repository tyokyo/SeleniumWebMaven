package model;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.uncommons.reportng.Reporters;

import execute.TestNgXml;
import util.Log;
import util.TakeScreen;

public class VP  extends BaseSelenium{
	public static String accountPath = "properties/account.properties";
	private static String SEPERATE="/";

	/**
	 * 点击操作
	 * @author qiang.zhang@ck-telecom.com
	 * @param by
	 */
	public static void clickElement(By by){
		getElement(by).click();
		wait(1);
		Log.info("click by - "+by.toString());
	}

	/** 
	 * @Title: sendKeys 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 输入字符
	 *  @param by       定位元素
	 *  @param keys    String  
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void sendKeys(By by,String keys){
		Log.info(String.format("sendKeys by=%s   keys=%s", by.toString(),keys));
		WebElement element = getElement(by);
		element.clear();
		element.sendKeys(keys);
	}
	/**
	 * 获取对象
	 * @author qiang.zhang@ck-telecom.com
	 * @param by
	 * @return WebElement
	 */
	public static WebElement getElement(By by){
		Log.info(String.format("find element by=%s", by.toString()));
		waitUntilByFind(by, 5);
		WebElement element = getDriver().findElement(by);
		if (element.isDisplayed()) {
			Log.info("isDisplayed=true");
		}else {
			Log.info("isDisplayed=false");
		}
		//元素截图
		TakeScreen.takeElementScreen(element);
		highlightElement(element);
		return element;
	}

	/** 
	 * @Title: getElements 
	 * @Date:2017年9月19日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 获取对象列表
	 * @param by
	 * @return List<WebElement>
	 */
	public static List<WebElement> getElements(By by){
		Log.info(String.format("find elements by=%s", by.toString()));
		waitUntilByFind(by, 5);
		List<WebElement> elements = getDriver().findElements(by);
		return elements;
	}
	public static String getText(WebElement element){
		Log.info(element.getText()+"");
		return element.getText();
	}
	public static void clickElement(WebElement element){
		Log.info("Action-click ");
		highlightElement(element);
		element.click();
	}
	/** 
	 * @Title: clickById 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description:  id 定位点击元素 
	 *  @param id    
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void clickById(String id){
		By by = By.id(id);
		clickElement(by);
	}
	public static void clickByClassName(String className){
		By by = By.className(className);
		clickElement(by);
	}
	/** 
	 * @Title: clickByCssSelector 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: cssSelector 定位点击元素 
	 *  @param cssSelector    
	 * @return void    
	 * @throws 
	 */
	public static void clickByCssSelector(String cssSelector){
		By by = By.cssSelector(cssSelector);
		clickElement(by);
	}
	//By.linkText
	/** 
	 * @Title: clickByLinkText 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 *  @param linkText    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void clickByLinkText(String linkText){
		By by = By.linkText(linkText);
		clickElement(by);
	}
	//By.name
	/** 
	 * @Title: clickByName 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 通过name 点击元素
	 *  @param name    
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void clickByName(String name){
		By by = By.name(name);
		clickElement(by);
	}
	//By.xpath
	/** 
	 * @Title: clickByXpath 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 通过xpath 点击元素
	 *  @param xpathExpression    
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void clickByXpath(String xpathExpression){
		By by = By.xpath(xpathExpression);
		clickElement(by);
	}
	//By.partialLinkText
	/** 
	 * @Title: clickByPartialLinkText 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 *  @param linkText    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void clickByPartialLinkText(String linkText){
		By by = By.partialLinkText(linkText);
		clickElement(by);
	}
	//click By.id
	/** 
	 * @Title: getElementById 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 *  @param id    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 */
	public static WebElement getElementById(String id){
		By by = By.id(id);
		return getElement(by);
	}
	//By.cssSelector
	/** 
	 * @Title: getElementByCssSelector 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 *  @param id    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 */
	public static WebElement getElementByCssSelector(String selector){
		By by = By.cssSelector(selector);
		return getElement(by);
	}
	//By.linkText
	/** 
	 * @Title: getElementByLinkText 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 通过linkText 点击元素
	 *  @param linkText   
	 * @return void    返回类型 
	 * @throws 
	 */
	public static WebElement getElementByLinkText(String linkText){
		By by = By.linkText(linkText);
		return getElement(by);
	}
	//By.name
	/** 
	 * @Title: getElementByName 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 获取元素by name
	 *  @param name    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 */
	public static WebElement getElementByName(String name){
		By by = By.name(name);
		return getElement(by);
	}
	//By.xpath
	/** 
	 * @Title: getElementByXpath 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 获取元素by xpath
	 *  @param xpathExpression    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 */
	public static WebElement getElementByXpath(String xpathExpression){
		By by = By.xpath(xpathExpression);
		return getElement(by);
	}
	//By.partialLinkText
	/** 
	 * @Title: getElementByPartialLinkText 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 获取元素by linkText
	 *  @param linkText    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 */
	public static WebElement getElementByPartialLinkText(String linkText){
		By by = By.partialLinkText(linkText);
		return getElement(by);
	}
	/** 
	 * @Title: takeScreenShot 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 截取屏幕
	 *  @param tr    ITestResult
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void takeScreenShot(ITestResult tr) {
		if (getDriver()!=null){
			Log.info("takeScreenShot-ITestResult-Fail");
			Log.info(tr.getTestClass().getName()+SEPERATE+tr.getMethod().getMethodName());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			String mDateTime = formatter.format(new Date());
			//File location = new File("test-output/screenshot");
			File location = new File(TestNgXml.screenshotFolder);
			String screenName = tr.getTestClass().getName()+SEPERATE+tr.getMethod().getMethodName()+SEPERATE+mDateTime+".png";
			String screenShotPath = location.getAbsolutePath()+SEPERATE+screenName;
			File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(srcFile, new File(screenShotPath));
				Log.info(screenShotPath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Reporter.setCurrentTestResult(tr);
			//Reporters.logDebug(true,"<img src=../screenshot/" + screenName + " onclick='window.open(\"../screenshot/"+screenName+"\")' height='50' width='50'/>");
			Reporters.logDebug(true,("<img src=../screenshot/" + screenName + " onmousewheel=\"return bbimg(this)\""+ " onclick='window.open(\"../screenshot/"+screenName + "\")\'/>"));
		}else {
			Reporters.logDebug(true,"getDriver() is NULL, screenshot Skipped");
		}
	}

	/** 
	 * @Title: wait 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 等待方法 
	 *  @param time    int
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void wait(int time){
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // sleeping for 4 minutes
	}
	/** 
	 * @Title: rightClickMouse 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 模拟鼠标操作
	 *  @param by    By 过滤器
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void rightClickMouse(By by)
	{
		Actions action = new Actions(getDriver());
		action.contextClick(getDriver().findElement(by)).perform();    
	}

	/** 
	 * @Title: killProcess 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 杀掉Windows浏览器进程  
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void killProcess(String browser)
	{
		String browserUpperCase = browser.toUpperCase();
		if ("FIREFOX".equals(browserUpperCase)) {
			WindowsUtils.killByName("firefox.exe");
		}
		if ("CHROME".equals(browserUpperCase)) {
			WindowsUtils.killByName("chrome.exe");
		}
		if ("IE".equals(browserUpperCase)) {
			WindowsUtils.killByName("iexplore.exe");
		}
	}

	/** 
	 * @Title: moveTo 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 滚动条滚动到元素位置
	 *  @param by    元素
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void moveTo(By by){
		Actions action = new Actions(getDriver()); 
		WebElement toFindElement = getDriver().findElement(by);
		action.moveToElement(toFindElement).build().perform(); 
	}
	// 操作弹出窗口
	public static void multipleWindowsTitle() throws Exception
	{
		String url="E:\\StashFolder\\huoli_28@hotmail.com\\Stash\\Tank-MoneyProject\\Selenium Webdriver\\AllUIElement.html";
		getDriver().get(url);
		// 获取当前窗口的句柄
		String parentWindowId = getDriver().getWindowHandle();
		System.out.println("driver.getTitle(): " + getDriver().getTitle());

		WebElement button = getDriver().findElement(By.xpath("//input[@value='打开窗口']"));
		button.click();

		Set<String> allWindowsId = getDriver().getWindowHandles();
		// 获取所有的打开窗口的句柄
		for (String windowId : allWindowsId) {
			if (getDriver().switchTo().window(windowId).getTitle().contains("博客园")) {
				getDriver().switchTo().window(windowId);
				break;
			}
		}
		System.out.println("driver.getTitle(): " + getDriver().getTitle());
		// 再次切换回原来的父窗口
		getDriver().switchTo().window(parentWindowId);
		System.out.println("parentWindowId: " + getDriver().getTitle());
	}


	/** 
	 * @Title: highlightElement 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 高亮显示元素
	 * @param by    参数 
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void highlightElement(WebElement element) {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) getDriver();
			jse.executeScript("arguments[0].style.border='3px solid red'", element);
		} catch (Exception e) {
			TakeScreen.takeScreenShotWithDraw("NotFindBy");
			e.printStackTrace();
		}
	}
	/** 
	 * @Title: highlightElement 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 高亮显示元素
	 * @param by    参数 
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void unHighlightElement(WebElement element) {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) getDriver();
			jse.executeScript("arguments[0].style.border='3px solid white'", element);
		} catch (Exception e) {
			TakeScreen.takeScreenShotWithDraw("NotFindBy");
			e.printStackTrace();
		}
	}
	public static void waitUntilByFind(By by,int seconds){
		Log.info(String.format("waitUntilFind  %s in  %d seconds",by.toString(),seconds ));
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), seconds);
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
			Log.info(by.toString() + "  waitUntilFind = success");
		} catch (Exception e) {
			// TODO: handle exception
			Log.info(by.toString() + " waitUntilFind = Not find ");
		}
	}
	public static void waitUntilByFindAttribute(By by,String attributeName,String attributeValue,int seconds){
		boolean find = false;
		for (int i = 0; i < seconds; i++) {
			try {
				String value = getElement(by).getAttribute(attributeName);
				if (attributeValue.equals(value)) {
					find=true;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			if(find){
				break;
			}else {
				wait(2);
			}
		}
	}
	public static boolean waitUntilByFindText(By by,String text,int seconds){
		boolean find = false;
		for (int i = 0; i < seconds; i++) {
			try {
				String value = getElement(by).getText();
				if (text.equals(value)) {
					find=true;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			if(find){
				break;
			}else {
				wait(2);
			}
		}
		return find;
	}
	public static void waitUntilByGone(By by,int seconds){
		Log.info(String.format("waitUntilByNotFind in %d secods",seconds));
		boolean exit=false;
		for (int i = 0; i < seconds; i++) {
			try {
				getDriver().findElement(by);
			} catch (Exception e) {
				Log.info("now exit");
				exit=true;
			}
			if (exit) {
				break;
			}
		}
	}
	public static WebElement waitAuto(final By by, final int time) {
		try {
			return new WebDriverWait(getDriver(), time).until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver webDriver) {
					Log.info("查找结果-success-" + time + " 秒之内找到元素 [" + by.toString() + "]");
					return (WebElement) webDriver.findElement(by);
				}
			});
		} catch (TimeoutException e) {
			Log.info("查找元素失败-超时- " + time + " 秒之后还没找到元素 [" + by.toString() + "]");
			return null;
		}
	}
	/**
	 * 获得随机字符
	 */
	public static String getRandomString(int length) {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(62);
			sb.append(str.charAt(number));
		}
		return sb.toString();
	}
	/**
	 * 判断控件时候存在
	 *
	 * @param by By
	 * @param timeout 等待的事件
	 * @return
	 */
	public static boolean isElementExist(By by, int timeout) {
		try {
			WebElement element = waitAuto(by, timeout);
			if (element == null) {
				return false;
			}else {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}
	/** 
	 * @Title: isElementExist 
	 * @Date:2017年10月17日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 判定指定对象是否存在
	 * @param by
	 * @param arrributeName
	 * @param attributeValue
	 * @return boolean
	 */
	public static boolean isElementExist(By by,String arrributeName,String attributeValue) {
		boolean exists = false;
		try {
			String value = getElement(by).getAttribute(arrributeName);
			if (attributeValue.equals(value)) {
				exists=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return exists;
	}
	/** 
	 * @Title: hasAttribute 
	 * @Date:2017年10月17日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 是否存在属性
	 * @param webElement
	 * @param attributename
	 * @return boolean
	 */
	public static boolean hasAttribute(WebElement webElement,String attributename){
		boolean has = false;
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), 5);
			wait.until(ExpectedConditions.attributeToBeNotEmpty(webElement, attributename));
			has=true;
		} catch (Exception e) {
			// TODO: handle exception

		}
		return has;
	}
	/** 
	 * @Title: getAttribute 
	 * @Date:2017年10月17日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description:获取 属性值 
	 * @param webElement
	 * @param attributename
	 * @return String
	 */
	public static String getAttribute(WebElement webElement,String attributename){
		String  value =webElement.getAttribute(attributename);
		Log.info(value);
		return value;
	}
	/** 
	 * @Title: waitFileDownload 
	 * @Date:2017年10月17日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 等待下载完成
	 * @param fileName
	 * @param time
	 * @return boolean
	 */
	public static boolean waitFileDownload(String fileName,int time){
		boolean result = false;
		String path ="C:\\Users\\DELL\\Downloads\\";
		boolean finished = false;
		for (int i = 0; i < time; i++) {
			if (new File(path+fileName).exists()) {
				finished=true;
			}
			if (finished) {
				Log.info("Download success");
				result=true;
				File file = new File(path);
				File[] files = file.listFiles();
				for (File file2 : files) {
					if (file2.getAbsolutePath().endsWith("mp4")) {
						file2.delete();
					}
				}
				break;
			}else {
				wait(2);
			}
		}
		return result;
	}
	public static void switchWindowHandle(){
		String  oldhandles = getDriver().getWindowHandle();
		Set<String> newhandles = getDriver().getWindowHandles();
		newhandles.remove(oldhandles);
		setDriver(getDriver().switchTo().window(newhandles.iterator().next()));
	}
	/** 
	 * @Title: windowHandleForword 
	 * @Date:2017年10月17日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 后一个页面作为driver的所有者
	 */
	public static void windowHandleForword(){
		String  current = getDriver().getWindowHandle();
		Set<String> allhandles = getDriver().getWindowHandles();
		int size = allhandles.size();
		for (int i = 0; i < size; i++) {
			String iterator = (String)allhandles.toArray()[i];
			if (iterator.equals(current)) {
				if (i+1<size) {
					String next =  (String)allhandles.toArray()[i+1];
					setDriver(getDriver().switchTo().window(next));
				}else {
					setDriver(getDriver().switchTo().window(iterator));
				}
				break;
			}
		}
	}
	/** 
	 * @Title: windowHandleBack 
	 * @Date:2017年10月17日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 前一个页面作为driver所有者
	 */
	public static void windowHandleBack(){
		String  current = getDriver().getWindowHandle();
		Set<String> allhandles = getDriver().getWindowHandles();
		int size = allhandles.size();
		for (int i = 0; i < size; i++) {
			String iterator = (String)allhandles.toArray()[i];
			if (iterator.equals(current)) {
				if (0<=i-1) {
					String back =  (String)allhandles.toArray()[i-1];
					setDriver(getDriver().switchTo().window(back));
				}else {
					setDriver(getDriver().switchTo().window(iterator));
				}
				break;
			}
		}
	}
	/** 
	 * @Title: scrollToElement 
	 * @Date:2017年10月17日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 滑动到某一个By
	 * @param by
	 * @param timeOfSeconds 等待时间
	 */
	public static void scrollToElement(By by,int timeOfSeconds){
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", getElement(by)); 
		wait(timeOfSeconds);
	}
	/** 
	 * @Title: scrollToElement 
	 * @Date:2017年10月17日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 滑动到某一个WebElement
	 * @param element
	 * @param timeOfSeconds 等待时间
	 */
	public static void scrollToElement(WebElement element ,int timeOfSeconds){
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element); 
		wait(timeOfSeconds);
	}
	/** 
	 * @Title: waitForNewWindowHandle 
	 * @Date:2017年10月17日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 等待新的窗体加载完成
	 * @param elementToClick
	 * @param waitTime
	 * @return String
	 */
	public  String clickForNewWindowHandle(WebElement elementToClick,int waitTime){
		Set<String> afterPopUp;
		int timeOut = waitTime*2;
		Set<String> beforePopUp = getDriver().getWindowHandles();
		do{
			elementToClick.click();
			wait(1);
			afterPopUp = getDriver().getWindowHandles();
			afterPopUp.removeAll(beforePopUp);
			timeOut -= 1;
		}while(afterPopUp.size() != 1 && timeOut != 0);

		if(afterPopUp.size() == 1){
			return (String)afterPopUp.toArray()[0];
		}
		else{
			return null;
		}
	}
	public static String getRandomChinese(int length) {
		String result = "";
		for (int i = 1; i <=length; i++) {
			result=result+getRandomChar();
		}
		return result;
	}
	private static String getRandomChar() {
		String str = "";
		int hightPos;
		int lowPos;
		Random random = new Random();
		hightPos = (176 + Math.abs(random.nextInt(39)));
		lowPos = (161 + Math.abs(random.nextInt(93)));
		byte[] b = new byte[2];
		b[0] = (Integer.valueOf(hightPos)).byteValue();
		b[1] = (Integer.valueOf(lowPos)).byteValue();
		try {
			str = new String(b, "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.out.println("错误");
		}
		return String.valueOf(str.charAt(0));
	}
	public static void deleteAllVideoInDownloadFolder(){
		String folder = "C:\\Users\\DELL\\Downloads";
		File f = new File(folder);
		File[] filses = f.listFiles();
		for (File file : filses) {
			if (file.getAbsolutePath().toLowerCase().endsWith(".mp4")) {
				file.delete();
			}
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
		
		System.out.println(p.getX()+"-"+p.getY());
		System.out.println(width+"-"+height);
		
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

}