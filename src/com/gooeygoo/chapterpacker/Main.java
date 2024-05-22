package com.gooeygoo.chapterpacker;

import com.gooeygoo.chapterpacker.parser.GameFileParser;
import com.gooeygoo.chapterpacker.registries.Registries;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Main {

    public static String VANILLA_PATH;
    public static String MOD_PATH;

    public static void main(String[] args) throws IOException {
        //load properties
        Properties prop = new Properties();
        boolean validConfig = false;
        try {
            //load the config file
            prop.load(new FileInputStream("config.properties"));
            VANILLA_PATH = prop.getProperty("world_of_goo_vanilla_path");
            MOD_PATH = prop.getProperty("world_of_goo_modified_path");

            //ensure paths are valid
            if (wogPathExists(VANILLA_PATH)&&wogPathExists(MOD_PATH)) {
                validConfig = true;
            } else {
                System.out.println("One or both of your world of goo paths are invalid. Ensure that backslashes are escaped and that both directories contain a \"WorldOfGoo.exe\" file.");
            }

        } catch (FileNotFoundException e) {
            //file not found, create one for the user to modify
            prop.setProperty("world_of_goo_vanilla_path","");
            prop.setProperty("world_of_goo_modified_path","");
            //save file
            prop.store(new FileOutputStream("config.properties"),"");
            System.out.println("config.properties is missing, please initialize the newly create file before running again.");
        }

        if (validConfig) {
            for (int i=1;i<=5;i++) {
                GameFileParser.parseIsland(i);
            }

            Registries.TEXT_REGISTRY.register();
            Registries.GOOBALL_REGISTRY.register();
        }
    }

    private static boolean wogPathExists(String path) {
        return Files.exists(Paths.get(path+"\\WorldOfGoo.exe"));
    }


}