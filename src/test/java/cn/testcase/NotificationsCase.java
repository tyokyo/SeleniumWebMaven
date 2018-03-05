package cn.testcase;

import model.VP;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import cn.pages.AccountPage;
import cn.pages.HomePage;
import cn.pages.NotificationsPage;

public class NotificationsCase extends VP{
	@Parameters({"browser","username","password"})
	@BeforeMethod
	public void beforeTest(String browser,String username,String password){
		initialize(browser,username,password);
		startSioeye();
		AccountPage.loginAccount();
	}
	@Test
	public void testReplayComments(){
		HomePage.clickNotifacations();
		NotificationsPage.clickReplay();
		NotificationsPage.inputReply("Cool");
		NotificationsPage.clickSendReplyBtn();
		waitUntilByGone(NotificationsPage.reply_btn_send, 10);
		wait(10);
	}
	@Test
	public void testViewSystemNews(){
		HomePage.clickNotifacations();
		NotificationsPage.clickNews_system();
		waitUntilByFind(NotificationsPage.news_pop_up, 10);
		NotificationsPage.clickCloseBtn();
	}
	@Test
	public void testViewVipNews(){
		HomePage.clickNotifacations();
		NotificationsPage.clickNews_vip();
		waitUntilByFind(NotificationsPage.news_pop_up, 10);
		NotificationsPage.clickCloseBtn();
	}
	@Test
	public void testViewFabulousNews(){
		HomePage.clickNotifacations();
		NotificationsPage.clickNews_fabulous();
		waitUntilByFind(NotificationsPage.news_pop_up, 10);
		NotificationsPage.clickCloseBtn();
	}
	@Test
	public void testViewNotificationsNews(){
		HomePage.clickNotifacations();
		NotificationsPage.clickNews_notifications();
		waitUntilByFind(NotificationsPage.news_pop_up, 10);
		NotificationsPage.clickCloseBtn();
	}
	
	@AfterMethod
	public void AfterMethod(){
		quiteSelenium();
	}
}
