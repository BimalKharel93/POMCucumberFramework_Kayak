package src.main.java.com.kayak.utilities;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import src.main.java.com.kayak.factory.DriverManager;

import java.util.HashMap;

public class ConfigPages {
    public static CommonUtilities oCommUtil = new CommonUtilities();
    public static BrowserUtilities oBroUti = new BrowserUtilities();
    public static DriverManager driverManager = new DriverManager();
    public static Constants oCons = new Constants();
    public static SeleniumUtilities oSeUtil = new SeleniumUtilities();
    Logger log = Logger.getLogger(getClass().getSimpleName());

    public static HashMap<String, String> sMapAuthorizationHeader = new HashMap<>();
    public static WebDriver driver;
    public static String sHost;
    public static String sScreenShotName;
    public static String sClassNameForScreenShot;
    public static String sErrorMessage = "";
    //git explain

}
