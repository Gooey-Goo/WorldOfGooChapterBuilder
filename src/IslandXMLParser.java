import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;

public class IslandXMLParser extends XMLParser{
    public static void parse(File f) {
        Document doc = openFile(f);
        Element root = doc.getDocumentElement();
        GameFileParsing.parseLevel(root.getAttribute("map"));
        NodeList children = root.getChildNodes();
        for (int i=0;i<children.getLength();i++) {
            Element child = (Element)children.item(i);
            GameFileParsing.parseLevel(child.getAttribute("id"));
        }
    }
}
