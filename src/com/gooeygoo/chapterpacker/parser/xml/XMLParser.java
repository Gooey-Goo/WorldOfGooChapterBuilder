package com.gooeygoo.chapterpacker.parser.xml;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public abstract class XMLParser {
    public static Document openFile(File f) {
        Document doc = null;
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = builder.parse(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }

    public abstract void parse(File f);
}
