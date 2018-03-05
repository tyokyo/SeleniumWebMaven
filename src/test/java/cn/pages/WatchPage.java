package cn.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import util.Log;
import cn.bean.FollowBean;
import cn.bean.WatchBean;
import model.VP;

/**
 * 
 * @ClassName: WatchPage 
 * @Description: https://live.sioeye.cn/watch
 * @author jianbin.zhong
 * @date 2017年9月3日 下午2:51:43 
 *
 */
public class WatchPage extends VP{
	
	//Sioeye喜爱直播相机官网
	private static By logo = By.className("logo");
	//发现(home page页面discover的classname为discover active)
	private static By discover = By.className("discover");
	//关注
	private static By follow = By.cssSelector("a[class='follow active']");
	private static By follow1 = By.className("follow");
	//消息
	private static By notifications = By.className("notifications");
	//搜索
	private static By search = By.id("header-search-btn");
	
	//用户个人信息User
	private static By userAvatar = By.cssSelector("[class='user-avatar avatar']");
	private static By userNickname = By.className("ellipsis");
	private static By userSioeyeId = By.cssSelector(".sioeye-id>span:last-child");
	private static By userVipIcon = By.className("vip");
	private static By userSioeyeIDIcon = By.xpath("/html/body/div[2]/div/div[1]/div[1]/p/span[1]");
	private static By userSioeyeIDValue = By.xpath("/html/body/div[2]/div/div[1]/div[1]/p/span[2]");
	
	private static By userVideoValue = By.className("video-count");
	private static By userFollowValue = By.className("follow-count");
	private static By userFansValue = By.className("fans-count");
	private static By userZanValue = By.className("like-count");
	
	//用户个人信息User,直播达人
	private static By userCameraBtn = By.xpath("/html/body/div[2]/div/div[1]/div[2]/div[1]/div[1]/a/svg/use");
	private static By userDaRenTxt = By.xpath("/html/body/div[2]/div/div[1]/div[2]/div[1]/div[1]/span");
	private static By userZanIcon = By.xpath("/html/body/div[2]/div/div[1]/div[2]/div[1]/div[2]/svg/use");
	
	private static By userLocationValue = By.cssSelector(".intro-group.address>span");
	private static By userHobbyValue =  By.cssSelector(".intro-group.hobby>span");
	private static By userSignatureValue =  By.cssSelector(".intro-group.individual-signature>span");
	
	//推荐达人 rec for recommend 
	static By recTitle = By.className("title");
	static By recRefresh = By.className("refresh");
	static By recRefreshButton = By.className("refresh");
	public static By userAddFirstRecommandFollow = By.cssSelector(".user-recom>div:last-child>div:first-child");
	
	/**
	 * 
	 * @Method: clickLogo 
	 * @param: 
	 * @Description: 点击官网logo
	 * @return: void
	 * @author: jianbin.zhong
	 */
	public static void clickLogo(){
		clickElement(logo);
	}
	
	/**
	 * 
	 * @Method: clickDiscover 
	 * @param: 
	 * @Description: 点击发现
	 * @return: void
	 * @author: jianbin.zhong
	 */
	public static void clickDiscover(){
		clickElement(discover);
	}
	
	/**
	 * 
	 * @Method: clickWatch 
	 * @param: 
	 * @Description: 点击关注（watch）
	 * @return: void
	 * @author: jianbin.zhong
	 */
	public static void clickWatch(){
		if (isElementExist(follow,5)) {
			getElement(follow).click();
		}
		//promotion 页面
		if (isElementExist(follow1,5)) {
			getElement(follow1).click();
		}
	}
	
	/**
	 * 
	 * @Method: clickNoification 
	 * @param: 
	 * @Description: 点击消息button
	 * @return: void
	 * @author: jianbin.zhong
	 */
	public static void clickNoification(){
		getElement(notifications);
	}
	/**
	 * 
	 * @Method: clickSearch 
	 * @param: 
	 * @Description: 点击search button
	 * @return: void
	 * @author: jianbin.zhong
	 */
	public static void clickSearch(){
		getElement(search).click();
	}
	//用户个人信息User
	/**
	 * 
	 * @Method: clickUserAvatar 
	 * @param: 
	 * @Description: 点击左侧用户一栏的用户头像
	 * @return: void
	 * @author: jianbin.zhong
	 */
	public static void clickUserAvatar(){
		getElement(userAvatar).click();
	}
	
	/**
	 * 
	 * @Method: clickUserNickname 
	 * @param: 
	 * @Description: 点击左侧用户一栏的用户nickname
	 * @return: void
	 * @author: jianbin.zhong
	 */
	public static void clickUserNickname(){
		getElement(userNickname).click();
	}
	public static void clickUserVideo(){
		getElement(userVideoValue).click();
	}
	public static void clickUserFollow(){
		getElement(userFollowValue).click();
		wait(5);
	}
	public static List<FollowBean> getAllFollowInfo(){
		List<FollowBean> infos = new ArrayList<FollowBean>();
		WebElement table = getElement(By.cssSelector(".follow-wrapper.radius-box>table"));
		List<WebElement> follows = table.findElements(By.tagName("tr"));
		for (WebElement webElement : follows) {
			highlightElement(webElement);
			FollowBean info = new FollowBean();
			String follow =webElement.findElement(By.cssSelector(".follow-btn")).getAttribute("data-follow-status");
			info.setFollow(follow);
			String nickname = webElement.findElement(By.cssSelector(".follow-name.ellipsis>a")).getAttribute("title");
			info.setNickname(nickname);
			String sioeyeid=webElement.findElement(By.className("follow-id")).getText().replaceAll("SioeyeID:", "").trim();
			info.setSioeyeid(sioeyeid);
			infos.add(info);
		}
		return infos;
	}
	public static WebElement getFirstFollow(){
		WebElement table = getElement(By.cssSelector(".follow-wrapper.radius-box>table"));
		List<WebElement> follows = table.findElements(By.tagName("tr"));
		return follows.get(0);
	}
	public static void clickUserFans(){
		getElement(userFansValue).click();
	}
	/** 
	* @Title: getWatchBean 
	* @Date:2017年10月11日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 用户个人信息数据bean
	* @return WatchBean
	*/
	public static WatchBean getWatchBean(){
		WatchBean watchBean =new WatchBean();
		String nickname = getElement(userNickname).getText();
		String sioeyeid=getElement(userSioeyeId).getText();
		String videocount=getElement(userVideoValue).getText();
		String followcount=getElement(userFollowValue).getText();
		String fanscount=getElement(userFansValue).getText();
		String zancount=getElement(userZanValue).getText();
		String location = getElement(userLocationValue).getText();
		String hobby=getElement(userHobbyValue).getText();
		String signature=getElement(userSignatureValue).getText();
		watchBean.setNickname(nickname);
		watchBean.setSioeyeid(sioeyeid);
		watchBean.setVideocount(doKString(videocount));
		watchBean.setFollowcount(doKString(followcount));
		watchBean.setFanscount(doKString(fanscount));
		watchBean.setZancount(doKString(zancount));
		watchBean.setLocation(location);
		watchBean.setHobby(hobby);
		watchBean.setSignature(signature);
		System.out.println(watchBean.toString());
		return watchBean;
	}
	public static int doKString(String count){
		int total = 0;
		if (count.toLowerCase().contains("k")) {
			total=(int)Double.parseDouble(count.replaceAll("k", "").trim())*1000;
		}else {
			total=Integer.parseInt(count);
		}
		return total;
	}
	
	/** 
	* @Title: clickFirstRecommandFollowbtn 
	* @Date:2017年10月11日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 第一个推介达人关注按钮
	*/
	public static void clickFirstRecommandFollowbtn(){
		getElement(userAddFirstRecommandFollow).findElement(By.className("follow-box")).click();;
		wait(5);
	}
	
	/** 
	* @Title: getFirstRecommandFollowId 
	* @Date:2017年10月11日
	* @author qiang.zhang@ck-telecom.com
	* @Description:第一个推介达人sioeye id
	* @return String
	*/
	public static String getFirstRecommandFollowId(){
		String id =getElement(userAddFirstRecommandFollow).findElement(By.cssSelector(".ellipsis>span:last-child")).getText(); 
		System.out.println("["+id+"]");
		return id;
	}
	public static WebElement getFansElement(boolean isfollow){
		WebElement fansElement = null;
		WebElement table = getElement(By.cssSelector(".follow-wrapper.radius-box>table"));
		List<WebElement> follows = table.findElements(By.tagName("tr"));
		for (WebElement webElement : follows) {
			String follow =webElement.findElement(By.cssSelector(".follow-btn")).getAttribute("data-follow-status");
			if (isfollow) {
				if ("follow".equals(follow)) {
					fansElement=webElement;
					break;
				}
			}else {
				if ("unfollow".equals(follow)) {
					fansElement=webElement;
					break;
				}
			}
		}
		return fansElement;
	}
	
	/** 
	* @Title: getVideoElement 
	* @Date:2017年10月12日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 获取第一个直播视频
	* @return WebElement
	*/
	public static WebElement getVideoElement(){
		WebElement videoElement = null;
		List<WebElement> ems = getElements(By.cssSelector(".profile-tab-content>div"));
		videoElement=ems.get(0);
		return videoElement;
	}
	
	/** 
	* @Title: deleteVideo 
	* @Date:2017年10月12日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 删除视频
	* @param element void
	*/
	public static void clickDeleteVideo(WebElement element){
		element.findElement(By.className("icon-delete")).click();
	}
	public static void clickDownloadVideo(WebElement element){
		element.findElement(By.cssSelector(".icon-download>svg")).click();
	}
	public static String getVideosrc(WebElement element){
		String time = element.findElement(By.className("icon-download")).getAttribute("data-url");
		Log.info("data-url"+time);
		return time;
	}
	public static String getVideoFilename(WebElement element){
		String time = element.findElement(By.className("icon-download")).getAttribute("data-filename");
		Log.info("filename=:"+time);
		return time;
	}
	public static List<String> getAllVideosrc(){
		List<String> times=new ArrayList<String>();
		List<WebElement> timeElements = getElement(By.cssSelector(".profile-tab-content")).findElements(By.className("icon-download"));
		for (WebElement webElement : timeElements) {
			String url = webElement.getAttribute("data-url");
			times.add(url);
		}
		return times;
	}
	/** 
	* @Title: clickMore 
	* @Date:2017年10月19日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 更多
	*/
	public static void clickMore(){
		WebElement element =getElement(By.cssSelector("div.live-types.recent-live")).findElement(By.className("refresh"));
		element.click();
		windowHandleForword();
	}
}
