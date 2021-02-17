package src.test.java.com.kayak.testrunners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"src/test/java/com/kayak/stepdefs","src/test/java/com/kayak/apphooks"},
        features = {"src/test/resources/kayak/features/FlightFeature.feature"}
)

public class MyTestSerenityRunner {
}
