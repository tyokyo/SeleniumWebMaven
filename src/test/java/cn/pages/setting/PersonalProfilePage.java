package cn.pages.setting;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import model.VP;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import cn.pages.AccountPage;
import cn.pages.HomePage;
import util.Log;

public class PersonalProfilePage extends VP{
	//昵称
	public static By nickname = By.id("nickname");
	//性别：男
	static By female = By.id("female");
	//性别：女
	static By male = By.id("male");
	//性别：保密
	static By secret = By.id("secret");
	//地址
	static By area = By.id("area");
	//个性签名
	static By motto =By.id("motto");
	//自定义爱好
	static By me_hobby = By.id("me-hobby");
	//添加爱好
	static By  add_hobby = By.cssSelector(".me-hobby-add");
	//保存
	static By save_personal = By.id("save-personal-info");
	
	public static void navToInfo(){
		HomePage.clickAavtar();
		HomePage.clickSetting();
		NavToSetting.navToEditInfo("个人资料");
	}
	
	/** 
	 * @Title: getNicknameElement 
	 * @author xuefan.liao
	 * @Description: 获取用户名对象
	 * @return WebElement    返回类型 
	 */
	public static WebElement getNicknameElement(){
		return getElement(nickname);
	}

	/** 
	 * @Title: clearNickname
	 * @author xuefan.liao
	 * @Description: 清除昵称框文本框内容
	 * @return void    返回类型 
	 */
	public static void clearNickname(){
		getNicknameElement().clear();
	}

	/** 
	 * @Title: inputNickname
	 * @author xuefan.liao
	 * @Description: 输入昵称
	 * @return void    返回类型 
	 */
	public static void inputNickname (String keys){
		sendKeys(nickname , keys);
	}	

	/** 
	 * @Title: clickSavePersonalProfile 
	 * @Date:2017年9月14日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 个人资料-保存
	 */
	public static void clickSavePersonalProfile (){
		clickElement(save_personal);
		wait(10);
	}	

	/** 
	 * @Title: clickFemale
	 * @author xuefan.liao
	 * @Description: 选择男
	 * @return void    返回类型 
	 */
	public static void clickFemale(){
		chooseSex("female");
	}			

	/** 
	 * @Title: clickMale
	 * @author xuefan.liao
	 * @Description: 选择女
	 * @return void    返回类型 
	 */
	public static void clickMale(){
		chooseSex("male");
	}		

	/** 
	 * @Title: clickSecret
	 * @author xuefan.liao
	 * @Description: 选择保密
	 * @return void    返回类型 
	 */
	public static void clickSecret(){
		chooseSex("secret");
	}		

	/** 
	 * @Title: getAreaElement 
	 * @author xuefan.liao
	 * @Description: 获取用户名对象
	 * @return WebElement    返回类型 
	 */
	public static WebElement getAreaElement(){
		return getElement(area);
	}

	/** 
	 * @Title: clearArea
	 * @author xuefan.liao
	 * @Description: 清除地址框文本框内容
	 * @return void    返回类型 
	 */
	public static void clearArea(){
		getAreaElement().clear();
	}

	/** 
	 * @Title: inputArea
	 * @author xuefan.liao
	 * @Description: 输入地址
	 * @return void    返回类型 
	 */
	public static void inputArea (String keys){
		sendKeys(area , keys);
	}	

	/** 
	 * @Title: getMottoElement() 
	 * @author xuefan.liao
	 * @Description: 获取用户名对象
	 * @return WebElement    返回类型 
	 */
	public static WebElement getMottoElement(){
		return getElement(motto);
	}

	/** 
	 * @Title: clearMotto
	 * @author xuefan.liao
	 * @Description: 清除签名框文本框内容
	 * @return void    返回类型 
	 */
	public static void clearMotto(){
		getMottoElement().clear();
	}

	/** 
	 * @Title: inputMotto
	 * @author xuefan.liao
	 * @Description: 输入签名
	 * @return void    返回类型 
	 */
	public static void inputMotto (String keys){
		sendKeys(motto , keys);
	}	

	/** 
	 * @Title: getMe_hobbyElement() 
	 * @author xuefan.liao
	 * @Description: 获取用户名对象
	 * @return WebElement    返回类型 
	 */
	public static WebElement getMe_hobbyElement(){
		return getElement(me_hobby);
	}

	/** 
	 * @Title: clearMe_hobby
	 * @author xuefan.liao
	 * @Description: 清除自定义爱好框文本框内容
	 * @return void    返回类型 
	 */
	public static void clearMe_hobby(){
		getMe_hobbyElement().clear();
	}

	/** 
	 * @Title: inputMe_hobby
	 * @author xuefan.liao
	 * @Description: 输入自定义爱好
	 * @return void    返回类型 
	 */
	public static void inputMe_hobby (String keys){
		sendKeys(me_hobby , keys);
	}	
	
	/** 
	* @Title: clickAddHobbybtn 
	* @Date:2017年9月20日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 添加爱好
	*/
	public static void clickAddHobbybtn(){
		clickElement(add_hobby);
	}
	/** 
	 * @Title: persosnalProfileHobbySetting 
	 * @Date:2017年9月20日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 设置某一hobby选择或者不选择
	 * @param hobbyname
	 * @param isSelected true=选择  false=不选
	 */
	public static void hobbySetting(String hobbyname,boolean isSelected){
		List<WebElement> divList = getElements(By.cssSelector(".me-hobby-box>div"));
		for (WebElement divElement : divList) {
			WebElement attElement=divElement.findElement(By.tagName("input"));
			boolean checked;
			try {
				String attribute = attElement.getAttribute("checked");
				checked=Boolean.parseBoolean(attribute);
			} catch (Exception e) {
				// TODO: handle exception
				checked=false;
			}
			WebElement labElement=divElement.findElement(By.tagName("label"));
			String hobbyName = labElement.getText();
			if (hobbyName.equals(hobbyname)) {
				Log.info("hobbyname:"+hobbyName);
				Log.info("checked:"+checked);
				if (isSelected ==checked) {
					Log.info("do nothing");
				}else {
					Log.info("do click");
					labElement.click();
				}
				break;
			}
		}
	}

	/** 
	 * @Title: persosnalProfileHobbyAllSelected 
	 * @Date:2017年9月20日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: hobbys 全选 或者全不选
	 * @param allSelected true=全选  false=全部不选择
	 */
	public static void hobbyAllSelected(boolean allSelected){
		List<WebElement> divList = getElements(By.cssSelector(".me-hobby-box>div"));
		for (WebElement divElement : divList) {
			WebElement labElement=divElement.findElement(By.tagName("label"));
			String hobbyname = labElement.getText();
			Log.info(hobbyname);
			hobbySetting(hobbyname, allSelected);
		}
	}
	
	/** 
	* @Title: getAllHobbys 
	* @Date:2017年9月20日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 获取当前所有的hobby
	* @return List<String>
	*/
	public static Hashtable<String,Boolean> getAllHobbys(){
		Hashtable<String,Boolean> hobbys = new Hashtable<String,Boolean>();
		List<WebElement> divList = getElements(By.cssSelector(".me-hobby-box>div"));
		for (WebElement webElement : divList) {
			WebElement labElement=webElement.findElement(By.tagName("label"));
			WebElement attElement=webElement.findElement(By.tagName("input"));
			String hobbyname = labElement.getText();
			boolean isChecked = hasAttribute(attElement, "checked");
			hobbys.put(hobbyname,isChecked);
		}
		
		return hobbys;
	}
	public static String getAllHobbyString(){
		String hobbysString="";
		List<WebElement> divList = getElements(By.cssSelector(".me-hobby-box>div"));
		for (int i = 0; i < divList.size(); i++) {
			WebElement webElement=divList.get(i);
			WebElement labElement=webElement.findElement(By.tagName("label"));
			WebElement attElement=webElement.findElement(By.tagName("input"));
			String hobbyname = labElement.getText();
			boolean isChecked = hasAttribute(attElement, "checked");
			if (isChecked) {
				hobbysString=hobbysString+" "+hobbyname;
			}
		}
		return hobbysString.replaceFirst(" ", "");
	}
	/** 
	* @Title: getHobbyStatus 
	* @Date:2017年9月20日
	* @author qiang.zhang@ck-telecom.com
	* @Description: hobby 是否处于选择状态
	* @param name
	* @return boolean
	*/
	public static boolean getHobbyStatus(String name){
		boolean isSelectd= false;
		List<WebElement> divList = getElements(By.cssSelector(".me-hobby-box>div"));
		for (WebElement webElement : divList) {
			WebElement labElement=webElement.findElement(By.tagName("label"));
			String hobbyname = labElement.getText();
			if (name.equals(hobbyname)) {
				if (hasAttribute(webElement, "checked")) {
					isSelectd=true;
				}
				break;
			}
		}
		return isSelectd;
	}

	/** 
	 * @Title: persosnalProfileAddHobby 
	 * @Date:2017年9月20日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 输入hobby名字，新增一个hobby
	 * @param hobbyname void
	 */
	public static void addHobby(String hobbyname){
		clearMe_hobby();
		inputMe_hobby(hobbyname);
		clickAddHobbybtn();
	}
	/** 
	 * @Title: chooseSex 
	 * @Date:2017年9月19日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 个人资料，选择性别
	 * @param value =  male|female|secret
	 */
	public static void chooseSex(String value){
		List<WebElement> ems = getElements(By.cssSelector(".me-sex-box>label"));
		for (WebElement webElement : ems) {
			WebElement svgWebElement=webElement.findElement(By.cssSelector("svg>use"));
			if (svgWebElement.getAttribute("xlink:href").equals("#"+value)) {
				webElement.click();
				break;
			}
		}
	}
	
	/** 
	* @Title: getSexStatus 
	* @Date:2017年9月20日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 当前选择的性别
	* @return String  male | female | secret
	*/
	public static String getSexStatus(){
		List<WebElement> sexList = getElements(By.cssSelector(".me-sex-box>label"));
		String currentSex = "";
		for (WebElement webElement : sexList) {
			if (hasAttribute(webElement, "class")) {
				currentSex = webElement.findElement(By.cssSelector("svg>use")).getAttribute("xlink:href");
				Log.info("currentSex-"+currentSex);
				currentSex=currentSex.replaceAll("#", "");
				break;
			}
		}
		return currentSex;
	}
	
}
