package src.main.java.com.kayak.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import src.main.java.com.kayak.utilities.ApplicationHooks;

public class DriverManager {

    @SuppressWarnings("deprecation")
    public void lunchBrowser(String browser) throws Exception {
        if (browser.toLowerCase().startsWith("ch")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--disable-notifications");
            ApplicationHooks.driver = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("ff") || browser.toLowerCase().startsWith("fi")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("permission.default.desktop-notification", 1);
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            capabilities.setCapability(FirefoxDriver.PROFILE, profile);
            ApplicationHooks.driver = new FirefoxDriver(capabilities);

        } else if (browser.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            ApplicationHooks.driver = new EdgeDriver();

        } else if (browser.equalsIgnoreCase("ie")) {
            WebDriverManager.iedriver().setup();
            ApplicationHooks.driver = new InternetExplorerDriver();

        } else {
            throw new Exception("Browser is not correct");
        }
        ApplicationHooks.driver.manage().window().maximize();
    }

//    public ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
//    public synchronized  WebDriver getDriver(){
//        return driverThreadLocal.get();
//    }

}
