package com.gooeygoo.chapterpacker.registries;

import com.gooeygoo.chapterpacker.Main;
import com.gooeygoo.chapterpacker.parser.GameFileParser;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ResourceRegistry extends Registry {
    @Override
    public void register() {
        try {
            for (String resource : items) {
                //skip vanilla assets
                if (GameFileParser.isOirginal(resource)) continue;
                //copy otherwise
                String target = String.format("goomod/override/%s",resource);
                String pathTo = target.substring(0,target.lastIndexOf('/'));
                new File(pathTo).mkdirs();
                new File(target).createNewFile();
                Files.copy(Paths.get(String.format("%s/%s", Main.MOD_PATH,resource)),Paths.get(target), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
