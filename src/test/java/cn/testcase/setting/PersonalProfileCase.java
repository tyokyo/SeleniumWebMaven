package cn.testcase.setting;

import java.util.Hashtable;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import util.Log;
import cn.pages.AccountPage;
import cn.pages.setting.PersonalProfilePage;
import cn.provider.TestDataProvider;
import model.VP;
import model.WaitCondition;

/** 
 * https://live.sioeye.cn/settings#info
 * @ClassName: PersonalProfileCase 
 * @Description: 个人资料
 * @author qiang.zhang@ck-telecom.com
 * @date 2017年9月14日 上午11:26:01 
 *  
 */
public class PersonalProfileCase  extends VP{
	@Parameters({"browser","username","password"})
	@BeforeMethod
	public void beforeTest(String browser,String username,String password){
		initialize(browser,username,password);
		startSioeye();
		AccountPage.loginAccount();
	}
	/*@BeforeMethod
	public void BeforeMethod(){
		initialize("chrome","tyokyo@126.com","123456789");
		startSioeye();		
		AccountPage.loginAccount();
	}*/
	@AfterMethod
	public void afterTest(){
		quiteSelenium();
	}

	/** 
	 * @Title: testModifyNickName 
	 * @Date:2017年9月14日
	 * @author qiang.zhang@ck-telecom.com
	 * @Description: 修改昵称
	 */
	@Test(description="昵称修改",dataProvider="nickname",dataProviderClass=TestDataProvider.class)
	public void testModifyNickName(String nickname){
		PersonalProfilePage.navToInfo();
		
		PersonalProfilePage.clearNickname();
		String expectNickName = nickname;
		PersonalProfilePage.inputNickname(expectNickName);
		PersonalProfilePage.clickSavePersonalProfile();

		//刷新页面验证
		getDriver().navigate().refresh();

		WaitCondition.waitTextToBePresentInElementValue(PersonalProfilePage.nickname, expectNickName, 20);
		String actual = PersonalProfilePage.getNicknameElement().getAttribute("value");
		Assert.assertEquals(actual, expectNickName,"nickname");
	}
	/** 
	* @Title: testSexSetting 
	* @Date:2017年9月28日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 修改性别
	* @param sex void
	*/
	@Test(description="性别设置",dataProvider="sex",dataProviderClass=TestDataProvider.class)
	public void testSexSetting(String sex){
		PersonalProfilePage.navToInfo();

		PersonalProfilePage.chooseSex(sex);
		PersonalProfilePage.clickSavePersonalProfile();

		//刷新页面验证
		getDriver().navigate().refresh();

		String active = PersonalProfilePage.getSexStatus();
		Assert.assertEquals(active, sex,sex);
	}
	/** 
	* @Title: testLocation 
	* @Date:2017年9月28日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 修改位置
	*/
	@Test
	public void testLocation(){
		PersonalProfilePage.navToInfo();
		
		PersonalProfilePage.inputArea("四川-宜宾");
		PersonalProfilePage.clickSavePersonalProfile();

		//刷新页面验证
		getDriver().navigate().refresh();

		String actual = PersonalProfilePage.getAreaElement().getAttribute("value");
		Assert.assertEquals(actual, "四川-宜宾", "修改位置");
	}
	/** 
	* @Title: testPersonalizedSignature 
	* @Date:2017年9月28日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 修改个性签名 
	* @param value void
	*/
	@Test(description="个性签名",dataProvider="motoo",dataProviderClass=TestDataProvider.class)
	public void testPersonalizedSignature(String value){
		PersonalProfilePage.navToInfo();
		
		String expect = value;
		PersonalProfilePage.inputMotto(expect);;
		PersonalProfilePage.clickSavePersonalProfile();

		//刷新页面验证
		getDriver().navigate().refresh();

		String actual = PersonalProfilePage.getMottoElement().getAttribute("value");
		Assert.assertEquals(actual, expect, "motto");

	}
	/** 
	* @Title: testAddCustomHobby 
	* @Date:2017年9月28日
	* @author qiang.zhang@ck-telecom.com
	* @Description: 添加自定义爱好
	*/
	@Test
	public void testAddCustomHobby(){
		PersonalProfilePage.navToInfo();
		
		String hobby = getRandomString(10);
		PersonalProfilePage.addHobby(hobby);
		PersonalProfilePage.clickSavePersonalProfile();

		//当前页面验证
		boolean addSuccess = PersonalProfilePage.getAllHobbys().containsKey(hobby);
		Assert.assertEquals(addSuccess, true,"add custom hobby success");
		
		//刷新页面验证
		getDriver().navigate().refresh();
		addSuccess = PersonalProfilePage.getAllHobbys().containsKey(hobby);
		Assert.assertEquals(addSuccess, true,"add custom hobby success");
	}

	@Test(description="添加删除所有爱好",dataProvider="allHobby",dataProviderClass=TestDataProvider.class)
	public void testAddDelAllHobby(boolean all){
		PersonalProfilePage.navToInfo();
		
		PersonalProfilePage.hobbyAllSelected(all);
		PersonalProfilePage.clickSavePersonalProfile();

		//刷新页面验证
		getDriver().navigate().refresh();

		Hashtable<String, Boolean> hobbys = PersonalProfilePage.getAllHobbys();
		Set<String> ahobbysnames= hobbys.keySet();
		for (String hobby : ahobbysnames) {
			boolean actual = hobbys.get(hobby);
			Assert.assertEquals(actual, all,hobby+" check selected");
		}
	}
	
	@Test
	public void testAddRecommandHobby(){
		PersonalProfilePage.navToInfo();
		//选择全部爱好
		PersonalProfilePage.hobbyAllSelected(true);
		PersonalProfilePage.clickSavePersonalProfile();
		Hashtable<String, Boolean> hobbymap= PersonalProfilePage.getAllHobbys();

		Set<String> hobbysnames= hobbymap.keySet();
		String hobbyFirstName = hobbysnames.iterator().next();
		//第一个设置为未选中
		Log.info("hobbyFirstName-"+hobbyFirstName);
		PersonalProfilePage.hobbySetting(hobbyFirstName, false);
		PersonalProfilePage.clickSavePersonalProfile();

		//刷新页面验证
		getDriver().navigate().refresh();

		//验证
		Hashtable<String, Boolean> hobbys = PersonalProfilePage.getAllHobbys();
		Set<String> ahobbysnames= hobbys.keySet();
		for (String hobby : ahobbysnames) {
			if (hobbyFirstName.equals(hobby)) {
				boolean actual = hobbys.get(hobby);
				Assert.assertEquals(actual, false,hobby+" check not selected");
			}else {
				boolean actual = hobbys.get(hobby);
				Assert.assertEquals(actual, true,hobby+" check not selected");
			}
		}
	}
}
