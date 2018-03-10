package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    public static final Logger LOGGER = LoggerFactory.getLogger(PropertyReader.class);
    Properties prop = new Properties();
    InputStream input = null;

    public String getProperty(String key) {
        try {
            input = new FileInputStream("config.properties");
            // load a properties file
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        LOGGER.info("Reading property with key:" + key + " and value:" + prop.getProperty(key));
        return prop.getProperty(key);

    }


}
