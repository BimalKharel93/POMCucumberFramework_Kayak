package src.main.java.com.kayak.utilities;
import org.apache.log4j.Logger;
import java.util.Properties;

public class PropertiesReader {

    Logger log = Logger.getLogger(getClass().getSimpleName());
    public CommonUtilities oCommUtil = new CommonUtilities();
    private Properties prop;

    public Properties initializeProperty() throws Exception {
        prop = new Properties();
            oCommUtil.loadPropertyFiles(System.getProperty("user.dir") + "\\src\\test\\resources\\kayak\\properties\\config.properties");
            oCommUtil.loadLog4jProperty(System.getProperty("user.dir") + "\\src\\test\\resources\\kayak\\properties\\log4j.properties");
            oCommUtil.loadPropertyFiles(System.getProperty("user.dir") + "\\src\\test\\resources\\kayak\\properties\\testData.properties");
        return prop;
    }

}
