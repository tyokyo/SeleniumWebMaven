package cn.pages;
import java.util.List;

import model.VP;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import util.Log;
import cn.bean.FollowBean;
/**
 * 
 * @ClassName: promotion_page 
 * @Description: url=https://live.sioeye.cn/promotion/sioeyeid/conversionid
 * @author xuefan.liao
 * @date 2017年9月5日 下午5:14:22 
 *
 */
public class PromotionPage extends VP{
	//Sioeye喜爱直播promotion页面
	//主播头像
	static By Anchor_avatar = By.xpath("//*[@id='promotion-wrap']/div/div[1]/div[1]/a/img");	
	//关注或取消按钮
	public static By follow = By.cssSelector("#promotion-wrap .btn-follow");
	//举报别人的视频
	static By report =  By.className("report-box");
	//点赞别人的视频
	static By like = By.cssSelector("div.count-box.bottom-box-right");
	//分享别人的视频
	static By share = By.xpath("//*[@id='promotion-wrap']/div/div[2]/div[3]/div[2]/div[3]");
	//点赞自己的视频
	static By like1 = By.xpath("//*[@id='promotion-wrap']/div/div[2]/div[3]/div[2]/div[1]");
	//分享自人的视频
	static By share1 = By.xpath("//*[@id='promotion-wrap']/div/div[2]/div[3]/div[2]/div[2]");
	//下载自人的视频
	static By download = By.xpath("//*[@id='promotion-wrap']/div/div[2]/div[3]/div[2]/div[3]");
	//XX的直播
	static By live1 = By.xpath("//*[@id='live-list']/div/div[2]/a[1]");
	static By live2 = By.xpath("//*[@id='live-list']/div/div[2]/a[2]");
	static By live3 = By.xpath("//*[@id='live-list']/div/div[2]/a[3]");
	static By live4 = By.xpath("//*[@id='live-list']/div/div[2]/a[4]");
	//推荐视频
	static By recommend1 = By.xpath("//*[@id='recommend-list']/div/div[2]/a[1]");
	static By recommend2 = By.xpath("//*[@id='recommend-list']/div/div[2]/a[2]");
	static By recommend3 = By.xpath("//*[@id='recommend-list']/div/div[2]/a[3]");
	static By recommend4 = By.xpath("//*[@id='recommend-list']/div/div[2]/a[4]");
	//评论
	static By comment = By.id("comment-input");
	//发表评论
	static By comment_send = By.id("comment-send");

	/** 
	 * @Title: clickAnchor_avatar 
	 * @author xuefan.liao
	 * @Description: 点击主播头像
	 * @return void    返回类型 
	 */
	public static void clickAnchor_avatar(){
		clickElement(Anchor_avatar);
	}

	/** 
	 * @Title: clickFollow
	 * @author xuefan.liao
	 * @Description: 点击关注按钮
	 * @return void    返回类型 
	 */
	public static void clickFollow(){
		clickElement(follow);	
	}

	/** 
	 * @Title: clickReport
	 * @author xuefan.liao
	 * @Description: 举报别人的视频
	 * @return void    返回类型 
	 */
	public static void clickReport(){
		clickElement(report);	
		waitUntilByFind(By.cssSelector("div.pop-up-box.report"), 10);
		wait(3);
	}

	/** 
	 * @Title: clickLike
	 * @author xuefan.liao
	 * @Description: 点赞别人的视频
	 * @return void    返回类型 
	 */
	public static void clickLike(){
		WebElement likeElement = getElement(like).findElement(By.className("like-box"));
		unHighlightElement(likeElement);
		wait(1);
		highlightElement(likeElement);
		likeElement.click();
	}

	/** 
	 * @Title: clickShare
	 * @author xuefan.liao
	 * @Description: 分享别人的
	 * @return void    返回类型 
	 */
	public static void clickShare(){
		clickElement(share);	
	}

	/** 
	 * @Title: clickLike1
	 * @author xuefan.liao
	 * @Description: 点赞自己的视频
	 * @return void    返回类型 
	 */
	public static void clickLike1(){
		clickElement(like1);	
	}

	/** 
	 * @Title: clickShare1
	 * @author xuefan.liao
	 * @Description: 分享自己的视频
	 * @return void    返回类型 
	 */
	public static void clickShare1(){
		clickElement(share1);	
	}

	/** 
	 * @Title: clickDownload
	 * @author xuefan.liao
	 * @Description: 下载自己的视频
	 * @return void    返回类型 
	 */
	public static void clickDownload(){
		clickElement(download);	
	}	

	/** 
	 * @Title: clickLive1
	 * @author xuefan.liao
	 * @Description: 点击直播视频视频
	 * @return void    返回类型 
	 */
	public static void clickLive1(){
		clickElement(live1);	
	}	

	/** 
	 * @Title: clickreCommend1
	 * @author xuefan.liao
	 * @Description: 点击推荐视频视频
	 * @return void    返回类型 
	 */
	public static void clickreCommend1(){
		clickElement(recommend1);	
	}		

	/** 
	 * @Title: getCommentElement 
	 * @author xuefan.liao
	 * @Description: 获取用户名对象
	 * @return WebElement    返回类型 
	 */
	public static WebElement getCommentElement(){
		return getElement(comment);
	}

	/** 
	 * @Title: clearComment
	 * @author xuefan.liao
	 * @Description: 清除评论框文本框内容
	 * @return void    返回类型 
	 */
	public static void clearComment(){
		getCommentElement().clear();
	}

	/** 
	 * @Title: inputComment 
	 * @author xuefan.liao
	 * @Description: 输入评论
	 * @return void    返回类型 
	 */
	public static void inputComment (String keys){
		sendKeys(comment , keys);
	}	

	/** 
	 * @Title: clickComment_send
	 * @author xuefan.liao
	 * @Description: 点击发表评论
	 * @return void    返回类型 
	 */
	public static void clickComment_send(){
		clickElement(comment_send);	
	}			
	public static boolean hasValue(List<FollowBean> infos ,String id){
		boolean hasFind = false;
		for (FollowBean followBean : infos) {
			if (id.equals(followBean.getSioeyeid())) {
				hasFind=true;
				String msg =String.format("%s contains %s = %s",followBean.toString(),id,hasFind); 
				Log.info(msg);
				break;
			}else {
				String msg =String.format("%s contains %s = %s",followBean.toString(),id,hasFind); 
				Log.info(msg);
			}
		}
		return hasFind;
	}
}
