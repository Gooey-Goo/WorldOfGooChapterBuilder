package com.gooeygoo.chapterpacker.registries;

import com.gooeygoo.chapterpacker.AESBinFormat;
import com.gooeygoo.chapterpacker.Main;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextRegistry extends Registry {
    @Override
    public void register() {
        try {
            //decrypt the modified text document
            byte[] in = Files.readAllBytes(Paths.get(String.format("%s/properties/text.xml.bin", Main.MOD_PATH)));
            byte[] out = AESBinFormat.decode(in);

            ByteArrayInputStream stream = new ByteArrayInputStream(out);
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document inputDoc = builder.parse(stream);
            stream.close();
            Document textDoc = builder.newDocument();
            Node root = textDoc.createElement("strings");
            textDoc.appendChild(root);

            //copy modified strings
            NodeList children = inputDoc.getElementsByTagName("string");
            for (int i=0;i<children.getLength();i++) {
                Node child = children.item(i);
                if (child.getNodeName().equals("string")&&items.contains(child.getAttributes().getNamedItem("id").getNodeValue())) {
                    Node toCopy = child.cloneNode(true);
                    textDoc.adoptNode(toCopy);
                    root.appendChild(toCopy);
                }
            }

            //copy user defined strings
            File f = new File("text.xml");
            if (f.exists()) {
                inputDoc = builder.parse(f);
                children = inputDoc.getElementsByTagName("string");
                for (int i=0;i<children.getLength();i++) {
                    Node toCopy = children.item(i).cloneNode(true);
                    textDoc.adoptNode(toCopy);
                    root.appendChild(toCopy);
                }
            }

            //write text file to goomod
            Source source = new DOMSource(textDoc);
            File xmlFile = new File("goomod/text.xml");
            StreamResult result = new StreamResult(new OutputStreamWriter(
                    new FileOutputStream(xmlFile), "ISO-8859-1"));
            Transformer xformer = TransformerFactory.newInstance().newTransformer();
            xformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
