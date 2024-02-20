package utils;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import framework_constants.FilePath;
import org.testng.Assert;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

public final class ConfigFileManager extends AbstractModule {

    public static Map<String, String> configFileMap = new HashMap();
    private static Properties properties = new Properties();
    private static final Logger logger = Logger.getLogger(ConfigFileManager.class.getSimpleName());

    @Override
    protected void configure(){
        try {
            Properties prop= new Properties();
            prop.load(new FileInputStream(FilePath.COMMON_DATA));
            Names.bindProperties(binder(),prop);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("File not found");
        }
    }

    private ConfigFileManager(String configFile) throws IOException {
        FileInputStream inputStream = new FileInputStream(configFile);
        properties.load(inputStream);
    }

    public static ConfigFileManager getInstance(String configFile) {
        ConfigFileManager instance = null;
        if (instance == null) {
            try {
                if (System.getenv().containsKey("CONFIG_FILE")) {
                    configFile = System.getenv().get("CONFIG_FILE");
                    logger.info("Using config file from " + configFile);
                }
                instance = new ConfigFileManager(configFile);
                Enumeration keys = properties.propertyNames();
//                for(Map.Entry<Object, Object> entry : properties.entrySet()){
//                    configFileMap.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
//                }
                properties.entrySet().forEach(entry -> configFileMap.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue())));
//                while (keys.hasMoreElements()) {
//                    String key = (String) keys.nextElement();
//                    String value = (null == System.getenv(key)) ? properties.getProperty(key) : System.getenv(key);
//                    configFileMap.put(key, value);
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public String getProperty(String object) {
        String env = System.getenv(object);
        if (env != null && !env.isEmpty()) {
            return System.getenv(object);
        } else {
            return configFileMap.get(object);
        }

    }
}
