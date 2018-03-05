package cn.provider;

import java.lang.reflect.Method;

import model.VP;

import org.testng.annotations.DataProvider;

public class TestDataProvider extends VP{
	private static Object[][] obj;
	@DataProvider()
	public static Object[][] sex(Method method) {
		obj= new Object[][] { 
				{ "male"}, 
				{ "female"}, 
				{ "secret"},
		};  
		return obj;
	}
	@DataProvider()
	public static Object[][] allHobby(Method method) {
		obj= new Object[][] { 
				{true}, 
				{false}
		};  
		return obj;
	}
	@DataProvider()
	public static Object[][] motoo(Method method) {
		obj= new Object[][] { 
				{getRandomString(1)}, 
				{getRandomString(30)}
		};  
		return obj;
	}
	@DataProvider()
	public static Object[][] nickname(Method method) {
		obj= new Object[][] { 
				{getRandomString(5)}, 
				{getRandomString(15)}
		};  
		return obj;
	}
	@DataProvider()
	public static Object[][] addHobby(Method method) {
		obj= new Object[][] { 
				{getRandomString(2)}, 
				{getRandomString(8)}
		};  
		return obj;
	}
	@DataProvider()
	public static Object[][] livetitle(Method method) {
		obj= new Object[][] { 
				{getRandomString(10)}, 
				{getRandomString(35)}
		};  
		return obj;
	}
	@DataProvider()
	public static Object[][] privilege(Method method) {
		obj= new Object[][] { 
				{"public","公开"}, 
				{"secret","仅自己可见"}
		};  
		return obj;
	}
	@DataProvider()
	public static Object[][] followunfollow(Method method) {
		obj= new Object[][] { 
				{"follow"}, 
				{"unfollow"}
		};  
		return obj;
	}
	@DataProvider()
	public static Object[][] offlinevideo(Method method) {
		obj= new Object[][] { 
				{"精彩"}, 
				{"运动"},
				{"赛事"}, 
				{"会议"}, 
				{"最近"}
		};  
		return obj;
	}
	
}
