package cn.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import util.Log;
import model.VP;

/** 
* @ClassName: DiscoverPage 
* @Description: https://live.sioeye.cn/discovery
* @author qiang.zhang@ck-telecom.com
* @date 2017年10月17日 下午4:57:51 
*  
*/
public class DiscoverPage extends VP{
	public static By report_reason=By.className("report-reason");
	public static By report_reason_item=By.className("report-reason-box");
	static By report_false = By.className("btn_false");
	static By report_true = By.className("btn_true");
	public static By recommand_user_name=By.className("recommend-user-name");
	public static By recommand_user_name_first=By.cssSelector(".recommend-user-name:first-child");
	//关注或取消按钮
	public static By follow = By.cssSelector("#promotion-wrap .btn-follow");
	/** 
	* @Title: clickReportTrue 
	* @Date:2017年10月16日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 发送举报
	*/
	public static void clickReportTrue(){
		clickElement(report_true);
	}
	
	/** 
	* @Title: clickReportFalse 
	* @Date:2017年10月16日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 取消举报
	*/
	public static void clickReportFalse(){
		wait(3);
		waitUntilByFind(report_false, 10);
		clickElement(report_false);
	}
	
	/** 
	* @Title: getReportReasonList 
	* @Date:2017年10月16日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 获取所有的举报理由
	* @return List<WebElement>
	*/
	public static List<WebElement> getReportReasonList() {
		return getElements(report_reason_item);
	}
	/** 
	* @Title: clickRecportIndex 
	* @Date:2017年10月16日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 举报理由 
	* @param index 索引值
	*/
	public static void clickRecportIndex(int index){
		WebElement element = DiscoverPage.getReportReasonList().get(index);
		String text = element.findElement(By.tagName("label")).getText();
		Log.info("report-reason : "+text);
		element.click();
	}
	/** 
	* @Title: clickFollow 
	* @Date:2017年10月16日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 点击关注按钮
	*/
	public static void clickFollow(){
		clickElement(follow);	
	}
	public static String getFollowStatus(){
		WebElement webElement=getElement(follow);
		String attributename="data-follow-status";
		String status =getAttribute(webElement, attributename);
		Log.info("video status="+status);
		return status;
	}
	
	/** 
	* @Title: getViewSioeyeId 
	* @Date:2017年10月16日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 当前观看的视频的sioeyeid
	* @return String
	*/
	public static String getViewSioeyeId(){
		String id = "";
		String url = getDriver().getCurrentUrl();
		String regex = "promotion/(.*?)/"; //正则表达式  
		Pattern pattern = Pattern.compile(regex);   
		Matcher m = pattern.matcher(url);  
		while(m.find()){  
		    id=m.group(1);
		    Log.info(id);
		}  
		return id;
	}
	
	/** 
	* @Title: getAllRecommandUser 
	* @Date:2017年10月17日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 所有推荐达人的名字
	* @return List<String>
	*/
	public static List<String> getAllRecommandUser(){
		List<String> user = new ArrayList<String>();
		List<WebElement> elements = getElements(By.cssSelector("span.name.ellipsis"));
		for (WebElement webElement : elements) {
			user.add(webElement.getText());
		}
		return user;
	}
	/** 
	* @Title: getAllRecommandUserHref 
	* @Date:2017年10月31日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 所有推荐达人的href
	* @return List<String>
	*/
	public static List<String> getAllRecommandUserHref(){
		List<String> user = new ArrayList<String>();
		List<WebElement> elements = getElements(By.cssSelector("div.recommend-user.recommend-user-name"));
		for (WebElement webElement : elements) {
			user.add(webElement.getAttribute("href"));
		}
		return user;
	}
	/** 
	* @Title: clickFirstBigFunRecommand 
	* @Date:2017年10月17日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 点击-第一个推荐达人
	*/
	public static void clickFirstBigFunRecommand(){
		scrollToElement(recommand_user_name_first, 10);
		clickElement(recommand_user_name_first);
		wait(5);
	}
	public static void main(String args[]){
		String id = "";
		String url = "https://live.sioeye.cn/zjbbi#broadcast";
		String regex = "promotion/(.*?)/"; //正则表达式  
		Pattern pattern = Pattern.compile(regex);   
		Matcher m = pattern.matcher(url);  
		while(m.find()){  
		    id=m.group(1);
		    System.out.println(id);
		}  
	}
}
