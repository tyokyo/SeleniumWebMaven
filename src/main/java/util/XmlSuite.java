package util;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XmlSuite {
	public static final  String folder = "xml";
	public static final  String source  = "testng.xml";
	public static final  String config= "config.properties";
	public static void main(String[] args) {
		generateXmlSuites();
	}
	public static String getPackageName(){
		String pkgName=Property.getValueByKey(config,"package");
		System.out.println("package-:"+pkgName);
		return pkgName;
	}
	public static String getRuntime(){
		String runtime=Property.getValueByKey(config,"runtime");
		System.out.println("runtime-:"+runtime);
		return runtime;
	}
	public static void generateXmlSuites(){
		//删除旧的xml文件
		File f = new File(folder);
		File[] files = f.listFiles();
		for (File file : files) {
			file.deleteOnExit();
		}
		//写入新的xml文件
		String runtimeStr = getRuntime();
		int runtime = Integer.parseInt(runtimeStr);
		for (int i = 0; i < runtime; i++) {
			writeTestNgXml(i+1);
		}
	}
	public static void writeTestNgXml(int index){
		File xmlFile = new File(source);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			//update Element value
			updateElementValue(doc);
			//write the updated document to file or console
			doc.getDocumentElement().normalize();
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			String destination = String.format(folder+"/testng%d.xml",index);
			System.out.println(destination);
			StreamResult result = new StreamResult(new File(destination));
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);
			System.out.println("XML file updated successfully");

		} catch (Exception  e1) {
			e1.printStackTrace();
		}
	}
	private static void updateElementValue(Document doc) {
		Element root = doc.getDocumentElement();  
		root.setAttribute("name", System.currentTimeMillis()+"");
	}
}
