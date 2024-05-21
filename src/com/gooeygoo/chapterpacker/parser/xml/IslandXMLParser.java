package com.gooeygoo.chapterpacker.parser.xml;

import com.gooeygoo.chapterpacker.parser.GameFileParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;

public class IslandXMLParser extends XMLParser {
    @Override
    public void parse(File f) {
        Document doc = openFile(f);
        Element root = doc.getDocumentElement();
        GameFileParser.parseLevel(root.getAttribute("map"));
        NodeList children = root.getElementsByTagName("level");
        for (int i=0;i<children.getLength();i++) {
            Element child = (Element)children.item(i);
            GameFileParser.parseLevel(child.getAttribute("id"));
        }
    }
}
