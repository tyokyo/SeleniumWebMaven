package cn.pages;

import org.openqa.selenium.By;
import model.VP;

/** 
* @ClassName: NotificationsPage 
* @Description: https://live.sioeye.cn/notifications
* @author qiang.zhang@ck-telecom.com
* @date 2017年10月17日 下午4:57:42 
*  
*/
public class NotificationsPage extends VP{
	//系统消息
	static By news_system = By.cssSelector("div.radius-box.sys-news");
	//小秘书
	static By news_vip = By.cssSelector("div.radius-box.vip-news");
	//赞
	static By news_fabulous = By.cssSelector("div.radius-box.fabulous-news");
	//通知
	static By news_notifications = By.cssSelector("div.radius-box.notifications-news");
	//弹出框
	public static By news_pop_up = By.cssSelector("div.pop-up-con");
	
	//关闭
	static By close = By.className("close");
	
	static By reply = By.className("btn-reply");
	static By reply_send_input = By.className("send-input");
	public static By reply_btn_send = By.className("btn-send");
	public static void clickNews_system(){
		clickElement(news_system);
	}
	public static void clickNews_vip(){
		clickElement(news_vip);
	}
	public static void clickNews_fabulous(){
		clickElement(news_fabulous);
	}
	public static void clickNews_notifications(){
		clickElement(news_notifications);
	}
	public static void clickCloseBtn(){
		wait(5);
		clickElement(close);
	}
	public static void clickSendReplyBtn(){
		clickElement(reply_btn_send);
	}
	public static void inputReply(String text){
		sendKeys(reply_send_input, text);
	}
	public static void clickReplay(){
		clickElement(reply);
	}

}
