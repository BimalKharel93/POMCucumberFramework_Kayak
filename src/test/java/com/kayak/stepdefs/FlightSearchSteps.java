package src.test.java.com.kayak.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import src.main.java.com.kayak.factory.DriverManager;
import src.main.java.com.kayak.pages.flights_po.FlightPO;
import src.main.java.com.kayak.utilities.ConfigPages;

import java.awt.*;

import static org.junit.Assert.assertTrue;

public class FlightSearchSteps extends ConfigPages{

    private FlightPO flight = new FlightPO(DriverManager.getDriver());
    public FlightSearchSteps() throws AWTException {
    }

    @Given("flight search page of kayak web site")
    public void flight_search_page_of_kayak_web_site() throws Exception {
        sErrorMessage = "";
        sClassNameForScreenShot = getClass().getSimpleName();
        DriverManager.getDriver().get(oCons.getKayakUrl());
    }

    @Given("name of the cities and dates")
    public void name_of_the_cities_and_dates() throws Exception {
        flight.navigateToFlightPage();
    }

    @When("user enters the  {string} and {string} cities")
    public void user_enters_the_and_cities(String sOriginCity, String sDestinationCity) throws Exception {
        flight.enterOriginAndDestinationCities(sOriginCity, sDestinationCity);
    }

    @When("chooses nearby airports")
    public void chooses_nearby_airports() throws Exception {
        flight.selectNearByAirportsSuggestionOriginCity();
        flight.selectNearByAirportsSuggestionDestinationCity();
    }

    @When("selects {string} and {string} for departure and arrivals:")
    public void selects_and_for_departure_and_arrivals(String departureDay, String arrivalDay) throws Exception {
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

    @When("selects {string} and {string} one airport from available options")
    public void selects_and_san_francisco_one_airport_from_available_options(String sOriginAirport, String sDestinationAir) throws Exception {
        // select only one origin airport
        flight.selectOnlyOneOriginAirport(sOriginAirport);
        // select only one destination airport
        flight.selectOnlyOneDestinationAirport(sDestinationAir);
    }

    @Then("user should be able to see the search result that contains entered cities {string} {string}")
    public void user_should_be_able_to_see_the_search_result_that_contains_entered_cities(String originCity, String destCity) throws Exception {
        assertTrue(flight.verifyResultsByOriginAirportsNames(originCity).contains(System.getProperty("originAirport")));
        assertTrue(flight.verifyResultsByDestAirportNames(destCity).contains(System.getProperty("destinationAirport")));
        flight.refreshDom();
    }
}
