import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

import static org.openqa.selenium.logging.LogType.BROWSER;

public class ReadFile {

    //throws ParserConfigurationException, IOException, SAXException
    public static String getData(String keyName)  {
        String xmlPath ="site.xml";
        try {
            File configXmlFile = new File(xmlPath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(configXmlFile);

            if (doc != null) {
                doc.getDocumentElement().normalize();
            }
            assert doc != null;
            return doc.getElementsByTagName(keyName).item(0).getTextContent();
        }
        catch (Exception exp)
        {
            System.out.println("Failed reading parameter: " + keyName + " from configuration xml: " + xmlPath);
        }
        return null;
    }
}
