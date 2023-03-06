package dataprovider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    public static String getProperty(String key){
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(new File(System.getProperty("user.dir")+"/config/config.properties")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String val = prop.getProperty(key);
        return val;
    }
}
