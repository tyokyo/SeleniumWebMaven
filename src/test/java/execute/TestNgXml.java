package execute;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import model.BaseSelenium;
import util.Log;
import util.Property;

public class TestNgXml extends BaseSelenium {
	public static String reportFolder="test-output/";
	public static String htmlFolder="test-output/html";
	public static String screenshotFolder="test-output/screenshot";
	public static void allFiles(String root,List<File> files){
		File f = new File(root);
		File[] fs = f.listFiles();
		for (File file : fs) {
			if (file.isDirectory()) {
				String path = file.getAbsolutePath();
				allFiles(path, files);
			}else {
				if (file.getName().endsWith("java")) {
					files.add(file);
				}
			}
		}
	}
	public static List<String> getXmlClassList(String folder,String root) throws Exception{
		List<String> xmlName=new ArrayList<String>();
		List<File> files = new ArrayList<File>();
		allFiles(root, files);
		for (File file : files) {
			String fileame=file.getName().replace(".java", "");
			String xmlClassName = file.getAbsolutePath().split("src")[1].replaceAll("\\\\", ".").replaceFirst(".", "").replaceAll(".java", "");
			xmlName.add(xmlClassName);
			buildXML(folder,xmlClassName, fileame);
			System.out.println(xmlClassName);
		}
		return xmlName;
	}
	public static void buildAllClassesInOne(String folder,String root) throws Exception{
		List<String> classNames=new ArrayList<String>();
		List<File> files = new ArrayList<File>();
		allFiles(root, files);
		for (File file : files) {
			String xmlClassName = file.getAbsolutePath().split("src")[1].replaceAll("\\\\", ".").replaceFirst(".", "").replaceAll(".java", "");
			classNames.add(xmlClassName);
			System.out.println(xmlClassName);
		}
		buildAllInOneXML(folder, classNames);
	}
	public static  void buildXML(String rootpath,String className,String xmlName) throws Exception {  
		File rootFile = new File(rootpath);
		if (!rootFile.exists()) {
			rootFile.mkdirs();
		}else {
			if (!rootFile.isDirectory()) {
				rootFile.mkdirs();
			}
		}
		String path =rootpath+File.separator+xmlName+".xml";
		// 创建根节点 list;      
		Element root = new Element("suite");
		root.setAttribute("name", ""+System.currentTimeMillis());
		root.setAttribute("parallel", "classes");
		root.setAttribute("thread-count", "3");

		//根节点添加到文档中；      
		Document Doc = new Document(root);  

		Element portElement = new Element("parameter");
		portElement.setAttribute("name","browser");
		String browser = Property.getValueByKey("properties/config.properties", "BROWSER");
		portElement.setAttribute("value",browser);
		root.addContent(portElement);
		
		Element udidElement = new Element("parameter");
		udidElement.setAttribute("name","username");
		String username = Property.getValueByKey("properties/account.properties", "user_name");
		udidElement.setAttribute("value",username);
		root.addContent(udidElement);
		
		Element addressElement = new Element("parameter");
		addressElement.setAttribute("name","password");
		String password = Property.getValueByKey("properties/account.properties", "user_password");
		addressElement.setAttribute("value",password);
		root.addContent(addressElement);
		
		Element testElement = new Element("test");
		testElement.setAttribute("verbose", "2");
		testElement.setAttribute("preserve-order", "true");
		testElement.setAttribute("name", xmlName);

		Element classsesElement = new Element("classes");
		Element classElement = new Element("class");
		classElement.setAttribute("name", className);
		classsesElement.addContent(classElement);

		testElement.addContent(classsesElement);
		root.addContent(testElement);

		XMLOutputter XMLOut = new XMLOutputter();  
		// 输出company_list.xml文件；     
		XMLOut.output(Doc, new FileOutputStream(path));  
		Thread.currentThread();
		Thread.sleep(11);
	}  
	public static  void buildAllInOneXML(String rootpath,List<String> classNames) throws Exception {  
		File rootFile = new File(rootpath);
		if (!rootFile.exists()) {
			rootFile.mkdirs();
		}else {
			if (!rootFile.isDirectory()) {
				rootFile.mkdirs();
			}
		}
		String path =rootpath+File.separator+"testng"+".xml";
		// 创建根节点 list;      
		Element root = new Element("suite");
		root.setAttribute("name", ""+System.currentTimeMillis());
		root.setAttribute("parallel", "classes");
		root.setAttribute("thread-count", "3");

		//根节点添加到文档中；      
		Document Doc = new Document(root);  

		
		/*<parameter name="port" value="4723" />
		<parameter name="udid" value="HC44VWM04926" />
		<parameter name="address" value="10.120.3.115" />
		*/
		Element portElement = new Element("parameter");
		portElement.setAttribute("name","browser");
		String browser = Property.getValueByKey("properties/config.properties", "BROWSER");
		portElement.setAttribute("value",browser);
		root.addContent(portElement);
		
		Element udidElement = new Element("parameter");
		udidElement.setAttribute("name","username");
		String username = Property.getValueByKey("properties/account.properties", "user_name");
		udidElement.setAttribute("value",username);
		root.addContent(udidElement);
		
		Element addressElement = new Element("parameter");
		addressElement.setAttribute("name","password");
		String password = Property.getValueByKey("properties/account.properties", "user_password");
		addressElement.setAttribute("value",password);
		root.addContent(addressElement);
		
		Element testElement = new Element("test");
		testElement.setAttribute("verbose", "2");
		testElement.setAttribute("preserve-order", "true");
		testElement.setAttribute("name", "Appium-IOS");
		
		Element classsesElement = new Element("classes");
		for (String className : classNames) {
			Element classElement = new Element("class");
			classElement.setAttribute("name", className);
			classsesElement.addContent(classElement);
		}
		testElement.addContent(classsesElement);
		root.addContent(testElement);

		XMLOutputter XMLOut = new XMLOutputter();  
		// 输出company_list.xml文件；     
		XMLOut.output(Doc, new FileOutputStream(path));  
		Thread.currentThread();
		Thread.sleep(11);
	}  
	public static void fileFile(String root,String desctination) throws Exception{
		File f = new File(root);
		File[] fs = f.listFiles();
		for (File file : fs) {
			String toPath = desctination+"/"+file.getName();
			//String content = Inspector.readFile(file.getAbsolutePath());
			//Inspector.writeTxtFile(content, new File(toPath));
		}
	}
	public static void main(String args[]) throws Exception{
		String root = "src\\cn\\testcase";
		String folder = "xml";
		getXmlClassList(folder,root);
		//buildAllClassesInOne(folder, root);
	}
	public static void makeDir(String path){
		File rootFile = new File(path);
		if (!rootFile.exists()) {
			rootFile.mkdirs();
		}else {
			if (!rootFile.isDirectory()) {
				rootFile.mkdirs();
			}
		}
	}
	public static void startMakeReport() throws Exception{
		reportFolder= "report/"+System.currentTimeMillis();
		htmlFolder = reportFolder+"/html";
		screenshotFolder = reportFolder+"/screenshot";
		makeDir(reportFolder);
		makeDir(htmlFolder);
		makeDir(screenshotFolder);
		Log.info(reportFolder);
		Log.info(htmlFolder);
		Log.info(screenshotFolder);
	}
	public static void endMakeReport() throws Exception{
		System.out.println("report dirctory : "+reportFolder);
		//copy html
		//fileFile("test-output/html",htmlFolder);
		//fileCopyDir("test-output/screenshot",screenshotFolder);
		//copy screenshot
		//deleteFile(new File("test-output/screenshot"));
		//makeDir("test-output/screenshot");
		//del screenshot

	}
}
