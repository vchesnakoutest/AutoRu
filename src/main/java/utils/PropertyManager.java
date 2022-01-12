
package utils;

import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Log4j2
public class PropertyManager {

    private static PropertyManager instance;
    private static final Object lock = new Object();
    private static String propertyFilePath;
    private static Properties prop;

    public static PropertyManager getInstance () {
        propertyFilePath = System.getProperty("user.dir") + "/src/main/resources/application.properties";
        log.debug(String.format("Reading properties from %s", propertyFilePath));
        if (instance == null) {
            synchronized (lock) {
                instance = new PropertyManager();
                instance.loadData();
            }
        }
        return instance;
    }


    private void loadData() {
        prop = new Properties();
        try {
            prop.load(new FileInputStream(propertyFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String propertyName) {
        log.debug(String.format("Getting property by name %s. Value: %s", propertyName, prop.getProperty(propertyName)));
        return prop.getProperty(propertyName);
    }
}

