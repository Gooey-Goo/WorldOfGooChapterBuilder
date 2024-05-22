package com.gooeygoo.chapterpacker.registries;

import com.gooeygoo.chapterpacker.Main;
import com.gooeygoo.chapterpacker.parser.GameFileParser;
import com.gooeygoo.chapterpacker.parser.xml.ResourceXMLParser;

import java.io.File;

public class GooballRegistry extends Registry {
    @Override
    public void register() {
        for (String ball : items) {
            //ignore vanilla gooballs
            if (GameFileParser.isOirginal("res/balls/"+ball)) continue;
            //copy files
            String resrcpath = String.format("goomod/compile/res/balls/%s/resources.xml.xml",ball);
            GameFileParser.decryptAndCopy(String.format("%s/res/balls/%s/balls.xml.bin", Main.MOD_PATH,ball),String.format("goomod/compile/res/balls/%s/balls.xml.xml",ball));
            GameFileParser.decryptAndCopy(String.format("%s/res/balls/%s/resources.xml.bin", Main.MOD_PATH,ball),resrcpath);
            //parse resource stuff
            new ResourceXMLParser().parse(new File(resrcpath));

        }
    }
}
