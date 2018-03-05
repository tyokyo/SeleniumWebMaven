package execute;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.uncommons.reportng.HTMLReporter;
import org.uncommons.reportng.JUnitXMLReporter;
import listener.TestngListener;

public class XmlsRunner {
	private static final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
	public static void main(String[] args) throws Exception {
		System.setProperty(ESCAPE_PROPERTY, "false"); 
		
		System.out.println("start to calculate runtme in config.properties files.");
		//XmlSuite.generateXmlSuites();
		
		TestNgXml.startMakeReport();
		System.out.println("start to load testng.xml in the xml folder");
		// TODO Auto-generated method stub
		TestNG testNG = new TestNG();
		testNG.setUseDefaultListeners(false);
		testNG.addListener(new HTMLReporter());
		testNG.addListener(new JUnitXMLReporter());
		testNG.addListener(new TestngListener());
		//testNG.addListener(new IProgressTracker());
		testNG.setOutputDirectory(TestNgXml.reportFolder);
		List<String> suits = new ArrayList<String>();
		//添加测试套件-读取XML文件夹下所有的.xml文件（testng 配置文件）
		File xmlDir=new File("xml");
		File[] xmlFiles=xmlDir.listFiles();
		for (File file: xmlFiles) {
			if (file.getName().toLowerCase().endsWith(".xml")){
				System.out.println(file.getAbsolutePath());
				suits.add(file.getAbsolutePath());
			}
		}
		System.out.println("load testng.xml finished");
		testNG.setTestSuites(suits);
		testNG.run();
		
		TestNgXml.endMakeReport();
	}
}


