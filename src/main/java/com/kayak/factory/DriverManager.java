package src.main.java.com.kayak.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverManager {

    public WebDriver driver;
    public static ThreadLocal<WebDriver> driverTL = new ThreadLocal<>();

    @SuppressWarnings("deprecation")
    public WebDriver initializeDiver (String browser) throws Exception {
        if (browser.equals("ch")) {
            WebDriverManager.chromedriver().setup();
            driverTL.set(new ChromeDriver());

        } else if (browser.equalsIgnoreCase("ff") || browser.toLowerCase().startsWith("fi")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("permission.default.desktop-notification", 1);
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            capabilities.setCapability(FirefoxDriver.PROFILE, profile);
            driverTL.set( new FirefoxDriver(capabilities));

        } else if (browser.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            driverTL.set(new EdgeDriver());

        } else if (browser.equalsIgnoreCase("ie")) {
            WebDriverManager.iedriver().setup();
            driverTL.set(new InternetExplorerDriver());

        } else {
            throw new Exception("Browser is not correct");
        }

        getDriver().manage().window().maximize();
        return getDriver();
    }

    public static synchronized  WebDriver getDriver(){
        return driverTL.get();
    }

}
