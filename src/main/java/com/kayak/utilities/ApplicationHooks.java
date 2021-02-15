package src.main.java.com.kayak.utilities;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import src.main.java.com.kayak.factory.DriverManager;

import java.util.HashMap;


public class ApplicationHooks extends DriverManager {

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

    @Before
    public void TriggerDependencies() throws Exception {
        oCommUtil.deleteScreenShotDirectory();
        oCommUtil.loadPropertyFiles(System.getProperty("user.dir") + "\\src\\main\\java\\com\\properties\\config.properties");
        oCommUtil.loadLog4jProperty(System.getProperty("user.dir") + "\\src\\main\\java\\com\\properties\\log4j.properties");
        oCommUtil.loadPropertyFiles(System.getProperty("user.dir") + "\\src\\main\\java\\com\\properties\\testData.properties");
        oCommUtil.deleteCreateScreenShotDirectorySureFireReports();

        if (System.getProperty("Environment") == null || System.getProperty("Environment").isEmpty())
            System.setProperty("Environment", System.getProperty("EnvironmentConfig"));

        log.info(System.getProperty("AutomationRunning"));
        log.info(System.getProperty("Environment"));

        if (System.getProperty("Environment").equalsIgnoreCase(Constants.sAutomationAPI)) {
            if (System.getProperty("Environment").equalsIgnoreCase("staging"))
                sHost = System.getProperty("StageHost");
            else
                throw new Exception("Environment is not found OR Give RIGHT Environment.");
            log.info(sHost);
        }
        if (System.getProperty("AutomationRunning").equalsIgnoreCase(Constants.sAutomationWeb.toLowerCase())) {
            driverManager.lunchBrowser(System.getProperty("Browser"));
        }
    }

    @After
    public void shuttingDownAllDependencies() {
        if (System.getProperty("AutomationRunning").equalsIgnoreCase(Constants.sAutomationWeb.toLowerCase())) {
            driver.quit();
        }
    }
}
