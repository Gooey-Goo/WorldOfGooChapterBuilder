import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;

public class ResourceXMLParser extends XMLParser {
    public static void parse(File f) {
        Document doc = openFile(f);
        NodeList children = doc.getElementsByTagName("Resources").item(0).getChildNodes();
        String pathPrefix = "";
        for (int i=0;i<children.getLength();i++) {
            Node child = children.item(i);
            switch (child.getNodeName()) {
                case "SetDefaults":
                    pathPrefix = child.getAttributes().getNamedItem("path").getNodeValue();
                    if (pathPrefix.equals("./")) pathPrefix = "";
                    break;
                case "Image":
                case "Sound":
                    //TODO: add resources to resource registry
                    break;
            }
        }
    }
}
