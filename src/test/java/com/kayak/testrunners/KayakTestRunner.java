package src.test.java.com.kayak.testrunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/kayak/features/FlightFeature.feature", glue = {"stepdef"}
)
public class KayakTestRunner {
}
