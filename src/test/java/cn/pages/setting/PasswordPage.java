package cn.pages.setting;

import model.VP;

import org.openqa.selenium.By;

import cn.pages.AccountPage;
import cn.pages.HomePage;

public class PasswordPage  extends VP{
	//Sioeye喜爱直播设置页面_修改密码
		//旧密码
		static By password_old = By.id("password-old");
		//新密码
		static By password_new = By.id("password-new");
		//重复输入密码
		static By password_confirm = By.id("password-confirm");
		//保存
		static By save_password = By.id("save-password");
		
		//Sioeye喜爱直播设置页面_串码流
		//设备名称
		static By  device_name = By.id("device-name");
		//申请
		static By rtmp_apply = By.xpath("/html/body/div[2]/div/div[2]/div[5]/div[2]/button");
		//串码流id
		static By rtmp_id = By.id("rtmp-id");
		//停止
		static By rtmp_stop = By.xpath("/html/body/div[2]/div/div[2]/div[5]/div[3]/button");
		
		public static void navToModifyPassword(){
			HomePage.clickAavtar();
			HomePage.clickSetting();
			NavToSetting.navToEditInfo("修改密码");
		}
}
