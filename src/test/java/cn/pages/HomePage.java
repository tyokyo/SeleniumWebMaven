package cn.pages;

import java.util.List;

import model.VP;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
//https://live.sioeye.cn/watch  -  账号未登录时候
public class HomePage  extends VP{
	//Sioeye喜爱直播相机官网
	static By logo = By.className("ttbar-login");
	//发现
	static By discover = By.cssSelector("a[class='discover active']");
	//关注
	static By follow = By.className("follow");
	//消息
	static By notifications = By.className("notifications");
	//搜索按钮
	static By btn_search = By.className("btn-search");
	//输入搜索内容
	static By search_input = By.id("search-box-input");
	//搜索
	static By btns_search = By.className("btns-search");
	//搜索视频
	static By btns_search_video = By.className("resource-video");
	//搜索用户
	static By btns_search_user =By.cssSelector("a[class='resource-user active']");
	//登录
	static By btn_login = By.cssSelector("[class='btn-login avatar']");
	//头像
	static By avatar = By.cssSelector("[class='btn-login avatar log-on']");
	//个人主页
	static By personalPage = By.linkText("个人主页");
	//设置
	static By settings = By.linkText("设置");
	//退出登录
	static By logout = By.linkText("退出登录");
	//输入评论内容
	public static By comment_input = By.id("comment-input");
	//发送评论
	public static By comment_send = By.id("comment-send");
	//推荐达人
	public static By recommand_user = By.className("master-recommend");
	
	/** 
	 * @Title: clickLogo 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 点击相机官网
	 * @return void    返回类型 
	 */
	public static void clickLogo(){
		clickElement(logo);
	}

	/** 
	 * @Title: clickDiscover 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 点击发现 
	 * @return void    返回类型 
	 */
	public static void clickDiscover(){
		clickElement(discover);
	}

	/** 
	 * @Title: clickFollow 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 点击关注
	 * @return void    返回类型 
	 */
	public static void clickFollow(){
		clickElement(follow);
	}

	/** 
	 * @Title: clickNotifacations 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 点击消息   
	 * @return void    返回类型 
	 */
	public static void clickNotifacations(){
		clickElement(notifications);
	}

	/** 
	 * @Title: clickSearchbtn 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 点击搜索
	 * @return void    返回类型 
	 */
	public static void clickSearchbtn(){
		clickElement(btn_search);
	}
	/** 
	 * @Title: clickSearchbtn 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 点击搜索
	 * @return void    返回类型 
	 */
	public static void clickSearchbtns(){
		clickElement(btns_search);
	}

	/** 
	 * @Title: clickSearchvideo 
	 * @Date:2017年10月11日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 搜索视频
	 */
	public static void clickSearchvideo(){
		clickElement(btns_search_video);
	}

	/** 
	 * @Title: clickSearchuser 
	 * @Date:2017年10月11日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 搜索用户
	 */
	public static void clickSearchuser(){
		getElement(By.className("resource-btn-box")).findElements(By.tagName("a")).get(1).click();
	}
	/** 
	 * @Title: searchInput 
	 * @Date:2017年10月11日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 输入搜索内容
	 * @param content void
	 */
	public static void searchInput(String content){
		sendKeys(search_input, content);
	}
	/** 
	 * @Title: searchVideoResult 
	 * @Date:2017年10月11日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 搜索视频结果
	 * @return List<WebElement>
	 */
	public static List<WebElement> searchVideoResult(){
		List<WebElement> resultsElements=getElement(By.className("live-video")).findElements(By.tagName("a"));
		return resultsElements;
	}

	/** 
	 * @Title: searchUserResult 
	 * @Date:2017年10月11日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 搜索用户结果
	 * @return List<WebElement>
	 */
	public static List<WebElement> searchUserResult(){
		List<WebElement> resultsElements= getElement(By.className("search-user-list")).findElements(By.tagName("div"));
		return resultsElements;
	}
	/** 
	 * @Title: clickLoginbtn 
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 主页，点击登录，进入登陆页面    
	 * @return void    返回类型 
	 */
	public static void clickLoginbtn(){
		clickElement(btn_login);
	}
	/** 
	 * @Title: clickPersonalPage 
	 * @Date:2017年9月14日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 点击-个人主页
	 */
	public static void clickPersonalPage(){
		clickElement(personalPage);
	}

	/** 
	 * @Title: clickSetting 
	 * @Date:2017年9月14日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 点击-设置
	 */
	public static void clickSetting(){
		clickElement(settings);
	}
	/** 
	 * @Title: clickLogout 
	 * @Date:2017年9月14日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 点击-退出登录
	 */
	public static void clickLogout(){
		clickElement(logout);
		waitUntilByGone(logout, 10);
	}

	/** 
	 * @Title: clickAavtar 
	 * @Date:2017年9月14日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 点击-头像
	 */
	public static void clickAavtar(){
		clickElement(avatar);
	}
	/** 
	* @Title: commentInput 
	* @Date:2017年10月13日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 输入评论内容
	* @param comments void
	*/
	public static void commentInput(String comments){
		wait(5);
		sendKeys(comment_input, comments);
		wait(3);
	}
	/** 
	* @Title: clickSendComments 
	* @Date:2017年10月13日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 发送评论
	*/
	public static void clickSendComments(){
		clickElement(comment_send);
		wait(3);
	}
	
	
	/** 
	* @Title: clickRefresh 
	* @Date:2017年10月17日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 推荐达人-刷新
	*/
	public static void clickRefresh(){
		WebElement element =getElement(By.className("master-recommend")).findElement(By.className("refresh"));
		clickElement(element);
		wait(5);
	}
	/** 
	* @Title: clickMore 
	* @Date:2017年10月19日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 更多
	*/
	public static void clickMore(){
		WebElement element =getElement(By.cssSelector("div.live-types.recent-live")).findElement(By.className("refresh"));
		clickElement(element);
		wait(5);
	}
}
