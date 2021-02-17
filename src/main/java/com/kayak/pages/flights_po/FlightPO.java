package src.main.java.com.kayak.pages.flights_po;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import src.main.java.com.kayak.factory.DriverManager;
import src.main.java.com.kayak.utilities.BrowserUtilities;
import src.main.java.com.kayak.utilities.CommonUtilities;
import src.main.java.com.kayak.utilities.ConfigPages;
import src.main.java.com.kayak.utilities.SeleniumUtilities;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightPO extends ConfigPages {
    Robot robot = new Robot();
    public WebDriver driver;
    private  BrowserUtilities oBroUti = new BrowserUtilities();
    private SeleniumUtilities oSeUtil = new SeleniumUtilities();


    public FlightPO(WebDriver driver) throws AWTException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    Actions actions = new Actions(DriverManager.getDriver());

    @FindBy(xpath = "//div[contains(text(),'Flights')]")
    WebElement eFlightPageLink;

    @FindBy(xpath = "//span[@class='svg logo-image']//*[local-name()='svg']//*[name()='path'][1]")
    WebElement eFightPage;

    @FindBy(xpath = "//div[contains(@id,'-origin-airport-display') and @data-placeholder='From?']")
    WebElement eOriginCity;

    @FindBy(xpath = "//div//input[contains(@id,'-origin-airport') and @placeholder='From?']")
    WebElement eTxtBoxOriginCity;

    @FindBy(xpath = "//form[@name='rtow-searchform']/descendant::div[@data-placeholder='To?']")
    WebElement eDestinationCity;

    @FindBy(xpath = "//div//input[contains(@id,'destination-airport') and @placeholder='To?']")
    WebElement eTxtBoxDestinationCity;

    @FindBy(xpath = "//div[contains(@id,'destination-airport-smartbox-dropdown')]//ul//li[@role='option']")
    List<WebElement> eDestDrpDownAirports;

    @FindBy(xpath = "//div[contains(@id,'-origin-airport-smartbox-dropdown')]//li[@role='option']")
    List<WebElement> eOriginDrpDownAirports;

    @FindBy(xpath = "//form[@name='rtow-searchform']/descendant::div[@data-placeholder='To?']")
    WebElement eDestTab;

    @FindBy(xpath = "//form[@name='rtow-searchform']/descendant::div[@aria-label='Departure date input']")
    WebElement departureCal;

    @FindBy(xpath = "//form[@name='rtow-searchform']/descendant::div[@aria-label='Return date input']")
    WebElement eArrivalCal;

    @FindBy(xpath = "//div[contains(@id,'-col-button-wrapper')]//button")
    WebElement searchButton;

    @FindBy(xpath = "//div[@class='features-and-alert']/ancestor::div[contains(@id,'dialog-body')]/preceding-sibling::button")
    WebElement htmlPopUP;

    @FindBy(xpath = "//div[@data-name='airports-section']")
    WebElement eAirportSection;

    @FindBy(xpath = "//div[@class='inner-grid keel-grid']//div[@class='mainInfo']")
    List<WebElement> resultsTabs;

    @FindBy(xpath = "//div[@class='Base-Results-ResultsListHeader Flights-Results-FlightResultsListHeader']")
    WebElement resultSortBox;

    @FindBy(xpath = "//div[contains(@class,'-departure-row')]//div[contains(@class,'location-block')]/span")
    List<WebElement> departureRows;

    @FindBy(xpath = "//div[contains(@class,'-arrival-row')]//div[contains(@class,'location-block')]/span")
    List<WebElement> arrivalRows;

    @FindBy(xpath = "//div[contains(@id,'-destination') and contains(@id,'destination-input-wrapper')]")
    WebElement toWarperBox;

    // dynamic date picker for departure
    public WebElement departureDay(String sendDate) throws InterruptedException {
        Thread.sleep(5000);
        WebElement element = driver.findElement(By.xpath("//div[@aria-label='" + sendDate + "']"));
        return element;
    }

    // dynamic date picker for arrival
    public WebElement arrivalDay(String sendDate) throws InterruptedException {
        Thread.sleep(5000);
        WebElement element = driver.findElement(By.xpath("//div[@aria-label='" + sendDate + "']"));
        return element;
    }

    // dynamic origin airport selection
    public List<WebElement> eCheckBoxOfOriginAirports(String originAirport) {
        List<WebElement> webElements = driver.findElements(By.xpath("//div[@data-heading='" + originAirport + "']/div[2]/li/div/div/div/div/div"));
        return webElements;
    }

    public List<WebElement> eOriginAirportsNames(String originAirport) {
        List<WebElement> webElements = driver.findElements(By.xpath("//div[@data-heading='" + originAirport + "']/div[2]/li"));
        return webElements;
    }

    // dynamic destination airport selection
    public List<WebElement> eDestinationAirportName (String destinationAirport) {
        List<WebElement> webElements = driver.findElements(By.xpath("//div[@data-heading='" + destinationAirport + "']/div[2]/li"));
        return webElements;
    }

    public List<WebElement> eCheckBoxOfDestinationAirports(String destinationAirport) {
        List<WebElement> webElements = driver.findElements(By.xpath("//div[@data-heading='" + destinationAirport + "']/div[2]/li/div/div/div/div/div"));
        return webElements;
    }

    // dynamic name verification
    public List<WebElement> result_OriginAirports(String sOriginAirport) {
        List<WebElement> webElements = driver.findElements(By.xpath("//section[contains(@id,'-details-leg-details') and @data-tab='overview']//span[contains(text(),'" + sOriginAirport + "')]"));
        return webElements;
    }

    public List<WebElement> result_DestAirports(String sDestAirport) {
        List<WebElement> webElements = driver.findElements(By.xpath("//section[contains(@id,'-details-leg-details') and @data-tab='overview']//span[contains(text(),'" + sDestAirport + "')]"));
        return webElements;
    }

    public void navigateToFlightPage() throws Exception {
        oBroUti.ufClick(eFlightPageLink);
    }

    public boolean pageIsDisplayed() throws Exception {
        return oBroUti.isDisplayed(eFightPage);

    }

    public void enterOriginAndDestinationCities(String sOriginCity, String sDestinationCity) throws Exception {
        // enter city for origin airport
        oBroUti.waitForElementToBeVisible(driver, eOriginCity, 7);
        oBroUti.ufClick(eOriginCity);
        oSeUtil.pressBackSpaceByTimes(eOriginCity, 3);

        oBroUti.waitForElementToBeVisible(driver, eTxtBoxOriginCity, 5);
        oSeUtil.ufSendKeysAndPressEnter(eTxtBoxOriginCity, sOriginCity);

        // enter city for destination airport
        oBroUti.waitForElementToBeVisible(driver, eDestinationCity, 7);
        oBroUti.ufClick(eDestinationCity);

        oBroUti.waitForElementToBeVisible(driver, eTxtBoxDestinationCity, 15);
        oSeUtil.pressBackSpaceByTimes(eTxtBoxDestinationCity,3);
        oBroUti.ufSendKeys(eTxtBoxDestinationCity, sDestinationCity);

        Thread.sleep(9000);
        WebElement eFirstSuggestion = oSeUtil.getElementByContainsTxt(eDestDrpDownAirports, sDestinationCity);
        oBroUti.ufClick(eFirstSuggestion);

    }

    public void selectNearByAirportsSuggestionOriginCity() throws Exception {
        oBroUti.waitForElementToBeVisible(driver, eOriginCity, 10);
        oBroUti.ufClick(eOriginCity);
        oBroUti.waitForElementToBeVisible(driver, eOriginCity, 5);
        Thread.sleep(5000);
        oBroUti.ufSendKeys(eOriginCity, "");

        Thread.sleep(5000);
        WebElement eNearByElement = oSeUtil.getElementByContainsTxt(eOriginDrpDownAirports, "nearby");
        oBroUti.ufClick(eNearByElement);
        robot.keyPress(KeyEvent.VK_ENTER);
    }

    public void selectNearByAirportsSuggestionDestinationCity() throws Exception {
        oBroUti.waitForElementToBeVisible(driver, eDestinationCity, 15);
        oBroUti.ufClick(eDestinationCity);

        Thread.sleep(5000);
        WebElement eNearByElement = oSeUtil.getElementByContainsTxt(eDestDrpDownAirports, "nearby");
        oBroUti.ufClick(eNearByElement);
        robot.keyPress(KeyEvent.VK_ENTER);
    }

    public void enterDepartureDate(String departureDate) throws Exception {
        oBroUti.waitForElementToBeVisible(driver, departureCal, 15);
        oBroUti.ufClick(departureCal);
        oBroUti.ufClick(departureDay(departureDate));
        robot.keyPress(KeyEvent.VK_ENTER);
    }

    public void enterArrivalDate(String arrivalDate) throws Exception {
        oBroUti.waitForElementToBeVisible(driver, eArrivalCal, 15);
        oBroUti.ufClick(eArrivalCal);
        oBroUti.ufClick(arrivalDay(arrivalDate));
    }

    public void clickSearchButton() throws Exception {
        oBroUti.waitForElementToBeVisible(driver, searchButton, 15);
        oBroUti.ufClick(searchButton);
    }

    public void closeAnyHtmlPopUp() throws Exception {
        Thread.sleep(7000);

        try {
            oBroUti.waitForElementToBeVisible(driver, htmlPopUP, 15);
            oBroUti.ufClick(htmlPopUP);
        } catch (NoSuchElementException nse) {

        }
    }

    public void selectOnlyOneOriginAirport(String sOriginAirport) throws Exception {
        oBroUti.waitForElementToBeVisible(driver, eAirportSection, 20);
        oBroUti.scrollToView(driver, eAirportSection);

//        WebElement forShowOnly = eOriginAirportsNames(sOriginAirport).get(0);
//        oBroUti.scrollToView(driver,forShowOnly);

        HashMap<WebElement,WebElement> originAirportMap = new HashMap<>();
        for (int i = 0; i < eOriginAirportsNames(sOriginAirport).size(); i++) {
            originAirportMap.put(eOriginAirportsNames(sOriginAirport).get(i),eCheckBoxOfOriginAirports(sOriginAirport).get(i));
            oBroUti.ufClick(eCheckBoxOfDestinationAirports(sOriginAirport).get(i));
        }

//        HashMap<WebElement, WebElement> originAirportMap = oSeUtil.pairOfElements(eOriginAirportsNames(sOriginAirport), eCheckBoxOfOriginAirports(sOriginAirport));
//        Thread.sleep(5000);
//        for (Map.Entry<WebElement, WebElement> we : originAirportMap.entrySet()) {
//            Thread.sleep(2000);
//            oBroUti.ufClick(we.getValue());
//        }

        for (Map.Entry<WebElement, WebElement> we : originAirportMap.entrySet()) {
            if (we.getKey().getText().contains(sOriginAirport)) {
                oBroUti.ufClick(we.getValue());
                break;
            }
        }
    }

    public void selectOnlyOneDestinationAirport(String sDestinationAirport) throws Exception {
        HashMap<WebElement, WebElement> destinationAirportMap = new HashMap<>();
        for (int i = 0; i < eDestinationAirportName(sDestinationAirport).size(); i++) {
            destinationAirportMap.put(eDestinationAirportName(sDestinationAirport).get(i),eCheckBoxOfDestinationAirports(sDestinationAirport).get(i));
            oBroUti.ufClick(eCheckBoxOfDestinationAirports(sDestinationAirport).get(i));
        }

//        HashMap<WebElement, WebElement> destinationAirportMap = oSeUtil.pairOfElements(eDestinationAirportName(sDestinationAirport), eCheckBoxOfDestinationAirports(sDestinationAirport));
//        Thread.sleep(5000);
//        for (Map.Entry<WebElement, WebElement> wes : destinationAirportMap.entrySet()) {
//            Thread.sleep(2000);
//            oBroUti.ufClick(wes.getValue());
//        }

//        WebElement forShowOnly = eDestinationAirportName(sDestinationAirport).get(0);
//        oBroUti.scrollToView(driver,forShowOnly);

        for (Map.Entry<WebElement, WebElement> wes : destinationAirportMap.entrySet()) {
            if (wes.getKey().getText().contains(sDestinationAirport)) {
                oBroUti.ufClick(wes.getValue());
                break;
            }
        }
    }

    public String verifyResultsByOriginAirportsNames(String sOriginAirport) throws Exception {
        oBroUti.waitForElementToBeVisible(driver,resultSortBox,10);
        oBroUti.scrollToView(driver,resultSortBox);
        Thread.sleep(5000);
        for (int i = 0; i < resultsTabs.size(); i++) {
            WebElement firstTab = resultsTabs.get(i);
            oBroUti.scrollToView(driver,firstTab);
            actions.moveToElement(firstTab).click().build().perform();
            break;
        }


        Thread.sleep(7000);
        String expectedName = "";
        for (int i = 0; i < departureRows.size(); i++) {
            Thread.sleep(3000);
            expectedName = departureRows.get(i).getText();
            System.out.println(expectedName);
            if(expectedName.contains(sOriginAirport))
            break;
        }
        return expectedName;
    }

    public String verifyResultsByDestAirportNames(String sDestAirport) throws InterruptedException {
        String expectedName = "";
        for (int i = 0; i < arrivalRows.size(); i++) {
            Thread.sleep(3000);
            expectedName = arrivalRows.get(i).getText();
            System.out.println(expectedName);
            if(expectedName.contains(sDestAirport))
            break;
        }
        return expectedName;

    }
    public void refreshDom() throws InterruptedException{
        Thread.sleep(9000);
        oSeUtil.refreshPage(driver);
        Thread.sleep(9000);
    }

}