package src.test.java.com.kayak.testrunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/kayak/features/FlightFeature.feature"},
        glue = {"src/test/java/com/kayak/stepdefs","src/test/java/com/kayak/apphooks"},
        plugin = {"pretty",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
public class RunFightSearchPage {
}
