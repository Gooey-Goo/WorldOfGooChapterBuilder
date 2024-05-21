package com.gooeygoo.chapterpacker.parser.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;

public class LevelXMLParser extends XMLParser {
    @Override
    public void parse(File f) {
        Document doc = openFile(f);
        NodeList children = doc.getDocumentElement().getChildNodes();
        for (int i=0;i<children.getLength();i++) {
            Node child = children.item(i);
            switch (child.getNodeName()) {
                case "BallInstance":
                    //TODO: add gooball to ball deferred registry
                    break;
                case "signpost":
                    //TODO: add text to text deferred registry
                    break;
            }
        }
    }
}
