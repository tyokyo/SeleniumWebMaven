package cn.pages.setting;

import cn.pages.AccountPage;
import cn.pages.HomePage;

public class RtmpConfigPage {
	
	/** 
	* @Title: navRtmp 
	* @Date:2017年9月22日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 设置-串码流
	*/
	public static void navRtmp(){
		HomePage.clickAavtar();
		HomePage.clickSetting();
		NavToSetting.navToEditInfo("串码流");
	}
}
