package model;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
 
public class SearchChineseCharacters extends VP {
  private static WebDriver driver;
  static final int MAX_TIMEOUT_IN_SECONDS = 5;
 
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    System.setProperty("webdriver.chrome.driver",
        System.getProperty("user.dir") + File.separator
            + "chromedriver.exe");
    driver = new ChromeDriver();
    String url = "http://cn.bing.com/";
    driver.manage().window().maximize();
    driver.manage().timeouts()
        .implicitlyWait(MAX_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
    driver.get(url);
  }
 
  @AfterClass
  public static void tearDownAfterClass() throws Exception {
    if (driver != null) {
      System.out.println("运行结束！");
      driver.quit();
    }
  }
 
  @Test
  public void test() {
    String queryString = "Selenium自动化测试";
    WebElement element = driver.findElement(By
        .xpath("//input[@id='sb_form_q']"));
    // 直接输入查询字符串
    // element.sendKeys(queryString);
 
    // 下面的语句模拟复制粘贴功能、copy & paste
    // 向粘贴板中存放数据，还可以注释掉下面的语句，进行手工复制一些东西到粘贴板
    setClipboardData(queryString);
    // 模拟Ctrl+V，进行粘贴
    Robot robot = null;
    try {
      robot = new Robot();
    } catch (AWTException e1) {
      e1.printStackTrace();
    }
    robot.keyPress(KeyEvent.VK_CONTROL);
    robot.keyPress(KeyEvent.VK_V);
    robot.keyRelease(KeyEvent.VK_V);
    robot.keyRelease(KeyEvent.VK_CONTROL);
    // 点击查询按钮
    driver.findElement(By.xpath("//input[@id='sb_form_go']")).click();
    // 截图函数
    captureScreenshot("截图测试JUnit");
 
  }
 
  private void captureScreenshot(String fileName) {
    String imagePath = System.getProperty("user.dir") + File.separator
        + fileName + ".png";
    try {
      byte[] decodedScreenshot = ((TakesScreenshot) driver)
          .getScreenshotAs(OutputType.BYTES);
      FileOutputStream fos = new FileOutputStream(new File(imagePath));
      fos.write(decodedScreenshot);
      fos.close();
      System.out.println("截图保存至" + imagePath);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
 
  public static void setClipboardData(String string) {
    StringSelection stringSelection = new StringSelection(string);
    Toolkit.getDefaultToolkit().getSystemClipboard()
        .setContents(stringSelection, null);
  }
}