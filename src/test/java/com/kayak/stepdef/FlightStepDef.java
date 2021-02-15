package src.test.java.com.kayak.stepdef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import src.main.java.com.kayak.utilities.ApplicationHooks;
import src.main.java.com.kayak.flights_po.FlightPO;

import static org.junit.Assert.assertTrue;

public class FlightStepDef extends ApplicationHooks {
    FlightPO flight;
    Logger log = Logger.getLogger(getClass().getSimpleName());

    @Given("flight search page of kayak web site")
    public void flight_search_page_of_kayak_web_site() throws Exception {
        sErrorMessage = "";
        sClassNameForScreenShot = getClass().getSimpleName();
        driver.get(oCons.getKayakUrl());
    }

    @Given("name of the cities and dates")
    public void nameOfTheCitiesAndDates() throws Exception {
        flight.navigateToFlightPage();
    }

    @When("user enters the  {string} and {string} cities")
    public void userEntersTheAndCities(String sOriginCity, String sDestinationCity) throws Exception {
        flight.enterOriginAndDestinationCities(sOriginCity, sDestinationCity);
    }

    @When("chooses nearby airports")
    public void chooses_nearby_airports() throws Exception {
        flight.selectNearByAirportsSuggestionOriginCity();
        flight.selectNearByAirportsSuggestionDestinationCity();
    }

    @When("selects {string} and {string} for departure and arrivals:")
    public void selectsAndForDepartureAndArrivals(String departureDay, String arrivalDay) throws Exception {
        flight.enterDepartureDate(departureDay);
        flight.enterArrivalDate(arrivalDay);

    }

    @When("clicks on search button")
    public void clicks_on_search_button() throws Exception {
        // click search
        flight.clickSearchButton();
        // close any html tab
        flight.closeAnyHtmlPopUp();

    }

    @And("selects {string} and {string}one airport from available options")
    public void selectsAndOneAirportFromAvailableOptions(String sOriginAirport, String sDestinationAir) throws Exception {
        // select only one origin airport
        flight.selectOnlyOneOriginAirport(sOriginAirport);
        // select only one destination airport
        flight.selectOnlyOneDestinationAirport(sDestinationAir);
    }

    @Then("user should be able to see the search result that contains entered cities {string} {string}")
    public void userShouldBeAbleToSeeTheSearchResultThatContainsEnteredCities(String originCity, String destCity) throws InterruptedException {
        assertTrue(flight.verifyResultsByOriginAirportsNames(originCity).contains(System.getProperty("originAirport")));
        assertTrue(flight.verifyResultsByDestAirportNames(destCity).contains(System.getProperty("destinationAirport")));
    }
}
