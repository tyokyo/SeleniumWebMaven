package cn.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import model.VP;

/** 
* @ClassName: RecentLivePage 
* @Description: https://live.sioeye.cn/recent-live
* @author qiang.zhang@ck-telecom.com
* @date 2017年10月17日 下午4:56:39 
*  
*/
public class RecentLivePage  extends VP{
	public static By live_video = By.className("live-video");
	public static By live_video_box = By.className("live-video-box");
	public static By page_prev = By.className("paging-btn-prev");
	public static By page_next = By.className("paging-btn-next");
	public static By page_active = By.cssSelector(".paging-active>a");
	public static By page_disable = By.cssSelector("a.paging-btn-prev.paging-btn-disabled");
	
	public static boolean  isPageDisable(){
		return isElementExist(page_disable, 5);
	}
	public static String getActivePage(){
		return getAttribute(getElement(page_active), "href");
	}
	public static String getNextPage(){
		return getAttribute(getElement(page_next), "href");
	}
	public static String getPrevPage(){
		return getAttribute(getElement(page_prev), "href");
	}
	/** 
	* @Title: clickPageNext 
	* @Date:2017年10月19日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 下一页
	*/
	public static void clickPageNext(int iteration){
		for (int i = 0; i < iteration; i++) {
			clickElement(page_next);
			wait(3);
		}
	}
	
	/** 
	* @Title: clickPagePrev 
	* @Date:2017年10月19日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 上一页
	*/
	public static void clickPagePrev(int iteration){
		for (int i = 0; i < iteration; i++) {
			clickElement(page_prev);
			wait(3);
		}
	}
	/** 
	* @Title: clickFirstVideo 
	* @Date:2017年10月19日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 播放第一视频
	*/
	public static void clickFirstVideo(){
		WebElement firstElement = getElements(live_video_box).get(0);
		firstElement.click();
		windowHandleForword();
	}
	
}
