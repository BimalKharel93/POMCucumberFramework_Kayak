package src.test.java.com.kayak.apphooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import src.main.java.com.kayak.factory.DriverManager;
import src.main.java.com.kayak.utilities.PropertiesReader;

import java.io.IOException;
import java.util.Properties;

public class ApplicationHooks {

    public DriverManager driverManager;
    private WebDriver driver;
    private PropertiesReader propertiesReader;
    Properties prop;

    Logger log = Logger.getLogger(getClass().getSimpleName());

    @Before(order = 0)
    public void getProperty() throws Exception {
        propertiesReader = new PropertiesReader();
        prop = propertiesReader.initializeProperty();
    }

    @Before(order = 1)
    public void lunchBrowser() throws Exception {

            String browserName = System.getProperty("Browser");
            driverManager = new DriverManager();
            driver = driverManager.initializeDiver(browserName);
    }


    @After (order = 0)
    public void shuttingDownAllDependencies() {
        driver.quit();

    }
    @After(order = 1)
    public void tearDown(Scenario scenario) throws IOException {
        if(scenario.isFailed()){

            String screenshotName = scenario.getName().replace("","_");
            byte[] sourcePath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(sourcePath,"image/png",screenshotName);
        }

    }
}
