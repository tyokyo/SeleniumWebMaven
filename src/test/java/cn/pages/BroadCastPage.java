package cn.pages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;

import util.Log;
import util.Property;
import model.VP;

/** 
* @ClassName: BroadCastPage 
* @Description: https://live.sioeye.cn/****#broadcast
* @author qiang.zhang@ck-telecom.com
* @date 2017年10月17日 下午4:59:47 
*  
*/
public class BroadCastPage extends VP{
	static By follow = By.className("profile-follow");
	
	public static void clickFollow(){
		clickElement(follow);
	}
	public static String getBroadcastSioeyeid(){
		String id = "";
		String url = getDriver().getCurrentUrl();
		String URL = Property.getValueByKey("properties/config.properties", "URL");
		String regex = URL+"(.*?)#"; //正则表达式  
		Pattern pattern = Pattern.compile(regex);   
		Matcher m = pattern.matcher(url);  
		while(m.find()){  
		    id=m.group(1);
		    Log.info(id);
		}  
		return id;
	}
}
