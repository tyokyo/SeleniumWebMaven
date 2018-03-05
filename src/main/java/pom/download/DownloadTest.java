package pom.download;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class DownloadTest {

    public static void main(String[] args) {

        FirefoxProfile profile = new FirefoxProfile();

        // 可以在Firefox浏览器地址栏中输入about:config来查看属性
        // 设置下载文件放置路径，注意如果是windows环境一定要用\\,用/不行
        String path = "src\\pom\\";
        String downloadFilePath = path + "\\firefox-55.0.1.en-US.win64.installer.exe";
        File file = new File(downloadFilePath);
        if (file.exists()) {
            file.delete();
        }

        // 配置响应下载参数
        profile.setPreference("browser.download.dir", path);// 下载路径
        profile.setPreference("browser.download.folderList", 2);// 2为保存在指定路径，0代表默认路径
        profile.setPreference("browser.download.manager.showWhenStarting", false);// 是否显示开始
        // 禁止弹出保存框，value是文件格式，如zip文件
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                "application/zip,text/plain,application/vnd.ms-excel,text/csv,text/comma-separated-values,application/octet-stream,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.openxmlformats-officedocument.wordprocessingml.document");
//关于类型：可以参考http://www.w3school.com.cn/media/media_mimeref.asp

        String driver_path = System.getProperty("user.dir")+"\\driver\\firefox\\64\\geckodriver.exe";
		System.out.println(driver_path);
		System.setProperty("webdriver.gecko.driver", driver_path);
		
		WebDriver driver=null;
		//= new FirefoxDriver(profile);
        
        driver.get("http://ftp.mozilla.org/pub/firefox/releases/56.0b5/win64/zh-CN/");

        driver.findElement(By.partialLinkText("Sub")).click();;

        waitTime(20000);
        String js_exist = "alert(\"download successfully\")";
        String js_not_exist = "alert(\"download unsuccessfully\")";

        if (file.exists()) {
            ((JavascriptExecutor) driver).executeScript(js_exist);
        } else {
            ((JavascriptExecutor) driver).executeScript(js_not_exist);
        }

        waitTime(5000);
        // driver.quit();

    }

    static public void waitTime(int time) {

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
