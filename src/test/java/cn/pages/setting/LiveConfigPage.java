package cn.pages.setting;

import java.awt.AWTException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import model.VP;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import cn.pages.AccountPage;
import cn.pages.HomePage;
import cn.pages.NotificationsPage;
import util.Log;

/** 
 * @ClassName: setting_page
 * @Description: url=https://live.sioeye.cn/settings
 * 设置相关页面 
 * @author xuefan.liao@163.com
 * @date 2017年8月29日 上午11:27:10 
 *  
 */
public class LiveConfigPage  extends VP{
	//Sioeye喜爱直播设置页面_直播配置
	//默认直播标题
	static By title = By.id("live-title");
	//公开
	static By all = By.linkText("公开");
	//仅自己可见
	static By myself = By.linkText("仅自己可见");
	//部分可见
	static By someone = By.linkText("部分可见");

	//输入搜索谁可以看我的直播
	public static WebElement inputSearchContentElement(){
		return getElement(By.className("watch-list-search")).findElement(By.tagName("input"));
	}
	//点击搜索
	public static void  clickSearchBtn(){
		getElement(By.className("watch-list-search")).findElement(By.tagName("span")).click();;
	}
	public static WebElement searchbtn;
	//谁可以看我的直播列表
	/** 
	 * @Title: watchersOKKO 
	 * @Date:2017年9月22日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 返回已经选择，未选择的观看列表
	 * @param isOk = true 代表已经选择的   false 表示未选择的
	 * @return List<WebElement>
	 */
	public static List<WebElement> watchersOKKO(boolean isOk){
		List<WebElement> notOkWatcher = new ArrayList<WebElement>();
		List<WebElement> allWatcher = getElements(By.cssSelector(".watch-list-box>ul>li"));
		for (WebElement webElement : allWatcher) {
			boolean isDisplay = webElement.findElement(By.cssSelector("div>span")).isDisplayed();
			if (isDisplay==isOk) {
				notOkWatcher.add(webElement);
			}
		}
		Log.info(isOk+"",notOkWatcher.size()+"");
		return notOkWatcher;
	}

	/** 
	 * @Title: watchersCanBeSelect 
	 * @Date:2017年9月22日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 显示的所有用户 
	 * @return List<WebElement>
	 */
	public static List<WebElement> watchersCanBeSelect(){
		List<WebElement> allWatcher = getElements(By.cssSelector(".watch-list-box>ul>li"));
		Log.info(allWatcher.size()+"");
		return allWatcher;
	}
	/** 
	* @Title: watchsHasbeenSelected 
	* @Date:2017年10月18日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 右边列表，已选择的好友
	* @return List<WebElement>
	*/
	public static List<WebElement> watchsHasbeenSelected(){
		List<WebElement> watchsHasbeenSelected = getElements(By.cssSelector(".watch-right>ul>li"));
		Log.info(watchsHasbeenSelected.size()+"");
		return watchsHasbeenSelected;
	}

	//选择谁可以看我的直播-确定
	static By whocanviewlive_ok = By.cssSelector(".watch-list-submit>button");
	public static void clickSelectSomeOne_Save(){
		clickElement(whocanviewlive_ok);
	}

	//选择谁可以看我的直播-关闭
	static By whocanviewlive_close = By.cssSelector(".close");
	public static void clickSelectSomeOne_Close(){
		clickElement(whocanviewlive_close);
	}

	//修改封面
	static By edit_thumb = By.cssSelector(".me-live-edit");
	public static void clickEditThumb(){
		clickElement(edit_thumb);
	}

	//保存封面
	static By save_thumb=By.cssSelector(".cover-submit-box->button");
	public static void clickSaveThumb(){
		clickElement(save_thumb);
	}
	//选择封面
	static By select_thumb=By.cssSelector(".pop-cover-left");
	public static void clickSelectThumb(){
		clickElement(select_thumb);
	}
	//输入-选择封面文件
	public static void inputThumb(String location){
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();  //得到系统剪贴板
		String text =location;
		StringSelection selection = new StringSelection(text);
		clipboard.setContents(selection, null);

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
		wait(3);
		
		//robot.keyPress(KeyEvent.VK_ENTER);
		//robot.keyRelease(KeyEvent.VK_ENTER);
		
		Actions action = new Actions(getDriver());
		
		action.sendKeys(Keys.ENTER).perform();
	}

	//直播配置保存

	static By me_submit=By.className("me-submit");
	static By save_live_config=By.cssSelector("#save-live-config");

	public static void clickLiveSavebtn(){
		getElement(save_live_config).click();
		wait(10);
	}
	/** 
	 * @Title: getLiveTitle 
	 * @Date:2017年9月21日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 获取直播标题内容
	 * @return String
	 */
	public static String getLiveTitle(){
		return getElement(title).getAttribute("value");
	}
	public static void inputLiveTitle(String value){
		sendKeys(title, value);
	}
	/** 
	 * @Title: setLivePrivilege 
	 * @Date:2017年9月21日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 谁可以看我的直播
	 * @param privilege public| myself|someone
	 */
	public static void setLivePrivilege(String privilege){
		if ("public".equals(privilege)) {
			clickElement(all);
		}
		if ("secret".equals(privilege)) {
			clickElement(myself);
		}
		if ("someone".equals(privilege)) {
			clickElement(someone);
			waitUntilByFind(NotificationsPage.news_pop_up, 10);
		}
	}
	
	/** 
	* @Title: getLivePrivilegeSettings 
	* @Date:2017年9月22日
	* @author qiang.zhang@ck-telecom.com
	* @Description:  谁可以看我的直播  | 公开 | 部分可见 |仅自己可见
	* @return String
	*/
	public static String getLivePrivilegeSettings(){
		String privilege="";
		List<WebElement> live_permissions = getElements(By.cssSelector(".me-live-permission>a"));
		for (WebElement webElement : live_permissions) {
			if (hasAttribute(webElement, "class")) {
				privilege = webElement.getText();
				break;
			}
		}
		return privilege;
	}
	public static void navToLiveConfig(){
		HomePage.clickAavtar();
		HomePage.clickSetting();
		NavToSetting.navToEditInfo("直播配置");
	}
}
