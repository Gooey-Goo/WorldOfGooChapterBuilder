import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class GameFileParsing {
    public static void parseIsland(int num) throws IOException {
        //assert file exists
        File f = new File(String.format("island%d.xml.xml",num));
        if (f.exists()) {
            //copy into goomod path
            String copyPath = "goomod/compile/res/islands/"+f.getName();
            new File(copyPath).mkdirs();
            Files.copy(Paths.get(f.getPath()), Paths.get(copyPath), StandardCopyOption.REPLACE_EXISTING);

            IslandXMLParser.parse(f);
        } else {
            System.out.printf("%s doesn't exist, skipping...\n",f.getName());
        }
    }

    public static void parseLevel(String levelname) {
        //assert file exists
        File f = new File(String.format("%s\\res\\levels\\%s",Main.MOD_PATH,levelname));
        if (f.exists()) {

        } else {
            System.out.printf("%s doesn't exist, skipping...\n",f.getName());
        }
    }
}
