package util;

import java.io.File;
import java.io.FileOutputStream;  
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.AWTException;
import java.awt.Color;  
import java.awt.Font;
import java.awt.Graphics;  
import java.awt.HeadlessException;
import java.awt.Image;  
import java.awt.image.BufferedImage;  

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.uncommons.reportng.Reporters;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import execute.TestNgXml;

import java.awt.Rectangle;  
import java.awt.Robot;  
import java.awt.Toolkit;  

import model.VP;

public class TakeScreen extends VP{
	public  static int DISPLAY = 1; 
	public static void takeScreenByRobot(String path) throws HeadlessException, AWTException{
		// 调用截图方法  
		BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));  
		try{
			ImageIO.write(image, "png", new File(path));     
		}catch(IOException e){   
			System.out.println(e.getMessage());  
		}   
	}
	private static String getPrefix() {
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		if (stackTrace == null || stackTrace.length < 4) return "BOGUS";
		String className = stackTrace[3].getClassName();
		String methodName = stackTrace[3].getMethodName();
		return String.format("%s.%s", className, methodName);
	}
	public static void takeScreenShot(Color color){
		// get browser size
		Dimension browser_size = getDriver().manage().window().getSize();
		int width = browser_size.width/DISPLAY/2;
		int height=browser_size.height/DISPLAY/2;

		String folderString = getPrefix();
		folderString=folderString.replaceAll("['.']", "/");
		//File folder = new File("test-output/screenshot/"+folderString);
		File folder = new File(TestNgXml.screenshotFolder+"/"+folderString);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String mDateTime = formatter.format(new Date());
		String screenName = mDateTime+".png";
		String screenShotPath = folder.getAbsolutePath()+File.separator+screenName;
		File screenShot = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenShot, new File(screenShotPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		Log.info(screenName);
		try {
			takeDrawRect(screenShotPath, color);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reporters.logInfo("<br><img src=../screenshot/" + folderString+"/"+screenName + "  onclick='window.open(\"../screenshot/"+folderString+"/"+screenName+")'"+"  height='"+height+"'  width='"+width+"'/>");
	}
	public static String takeScreenShot(){
		// get browser size
		Dimension browser_size = getDriver().manage().window().getSize();
		int width = browser_size.width/DISPLAY;
		int height=browser_size.height/DISPLAY;

		String folderString = getPrefix();
		folderString=folderString.replaceAll("['.']", "/");
		//File folder = new File("test-output/screenshot/"+folderString);
		File folder = new File(TestNgXml.screenshotFolder+"/"+folderString);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String mDateTime = formatter.format(new Date());
		String screenName = mDateTime+".png";
		String screenShotPath = folder.getAbsolutePath()+File.separator+screenName;
		File screenShot = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenShot, new File(screenShotPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		Log.info(screenShotPath);
		try {
			takeDrawRect(screenShotPath, Color.BLACK);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reporters.logInfo("<br><img src=../screenshot/" + folderString+"/"+screenName + "  onclick='window.open(\"../screenshot/"+folderString+"/"+screenName+")'"+"  height='"+height+"'  width='"+width+"'/>");
		return screenShotPath;
	}
	public static void takeScreenShotWithDraw(String drawText){
		Log.info(drawText);
		try {
			// get browser size
			Dimension browser_size = getDriver().manage().window().getSize();
			int width = browser_size.width/DISPLAY;
			int height=browser_size.height/DISPLAY;
			String folderString = getPrefix();
			folderString=folderString.replaceAll("['.']", "/");
			//File folder = new File("test-output/screenshot/"+folderString);
			File folder = new File(TestNgXml.screenshotFolder+"/"+folderString);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			String mDateTime = formatter.format(new Date());
			String screenName = mDateTime+".png";
			String screenShotPath = folder.getAbsolutePath()+File.separator+screenName;
			File screenShot = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(screenShot, new File(screenShotPath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			Log.info(screenName);
			takeDrawRectAndText(screenShotPath, Color.BLACK,drawText);
			Reporters.logInfo("<br><img src=../screenshot/" + folderString+"/"+screenName + "  onclick='window.open(\"../screenshot/"+folderString+"/"+screenName+")'"+"  height='"+height+"'  width='"+width+"'/>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void takeElementScreen(WebElement element){
		try {
			String folderString = getPrefix();
			folderString=folderString.replaceAll("['.']", "/");
			//File folder = new File("test-output/screenshot/"+folderString);
			File folder = new File(TestNgXml.screenshotFolder+"/"+folderString);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			String mDateTime = formatter.format(new Date());
			String screenName = mDateTime+".png";
			String screenShotPath = folder.getAbsolutePath()+File.separator+screenName;
			int width = element.getSize().getWidth();
			int height = element.getSize().getHeight();
			File screenShot = captureElement(element, screenShotPath);
			Log.info(screenName);
			Reporters.logInfo("<br><img src=../screenshot/" + folderString+"/"+screenName + "  onclick='window.open(\"../screenshot/"+folderString+"/"+screenName+")'"+"  height='"+height+"'  width='"+width+"'/>");
		} catch (java.awt.image.RasterFormatException e) {
			// TODO: handle exception
		}
	}
	public static void takeDrawRect(String filePath,Color color) throws IOException{
		File _file = new File(filePath); // 读入文件  
		Image src = ImageIO.read(_file); // 构造Image对象  
		int width = src.getWidth(null); // 得到源图宽  
		int height = src.getHeight(null); // 得到源图长  
		//需要长度  
		int newwidth = width;//width / 2  
		int newheight = height;//height / 2  
		BufferedImage image = new BufferedImage(newwidth, newheight,  
				BufferedImage.TYPE_INT_RGB);  
		Graphics graphics = image.getGraphics();  
		graphics.drawImage(src, 0, 0, newwidth, newheight, null); // 绘制缩小后的图  
		// 画边框,在drawImage后面，下面代码给图片加上两个像素的白边     
		graphics.setColor(color);     
		graphics.drawRect(0, 0, newwidth - 1, newheight - 1);  
		graphics.drawRect(1, 1, newwidth - 1, newheight - 1);  
		graphics.drawRect(0, 0, newwidth - 2, newheight - 2);  
		FileOutputStream out = new FileOutputStream(filePath); // 输出到文件流  
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
		encoder.encode(image); // JPEG编码  
		out.close();  
	}
	private static List<String> splitDrawString(String drawText){
		List<String> rows = new ArrayList<String>();
		int rowCount =30;
		int length =drawText.length();
		if (length<=rowCount) {
			rows.add(drawText);
		}else {
			int startIndex = 0;
			while (startIndex<=length) {
				int endIndex =startIndex+rowCount;
				if (endIndex>length) {
					String substr = drawText.substring(startIndex, length);
					rows.add(substr);
					break;
				}else {
					String substr = drawText.substring(startIndex, endIndex);
					rows.add(substr);
					startIndex = endIndex;
				}
			}
		}
		return rows;
	}
	public static void takeDrawRectAndText(String filePath,Color color,String drawText) throws IOException{
		File _file = new File(filePath); // 读入文件  
		Image src = ImageIO.read(_file); // 构造Image对象  
		int width = src.getWidth(null); // 得到源图宽  
		int height = src.getHeight(null); // 得到源图长  
		//需要长度  
		int newwidth = width;//width / 2  
		int newheight = height;//height / 2  
		BufferedImage image = new BufferedImage(newwidth, newheight,  
				BufferedImage.TYPE_INT_RGB);  
		Graphics graphics = image.getGraphics();  
		graphics.drawImage(src, 0, 0, newwidth, newheight, null); // 绘制缩小后的图  
		// 画边框,在drawImage后面，下面代码给图片加上两个像素的白边     
		graphics.setColor(color);     
		graphics.drawRect(0, 0, newwidth - 1, newheight - 1);  
		graphics.drawRect(1, 1, newwidth - 1, newheight - 1);  
		graphics.drawRect(0, 0, newwidth - 2, newheight - 2);  
		Font font=new Font("Helvetica",Font.BOLD,55);
		graphics.setFont(font);
		List<String> toDrawStrings = splitDrawString(drawText);
		int row=1;
		for (String draw : toDrawStrings) {
			int x =20;
			int y = 60*row+height/20;
			if (y<=height) {
				graphics.drawString(draw,x ,y );
				row=row+1;
			}
		}

		FileOutputStream out = new FileOutputStream(filePath); // 输出到文件流  
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
		encoder.encode(image); // JPEG编码  
		out.close();  
	}
}
