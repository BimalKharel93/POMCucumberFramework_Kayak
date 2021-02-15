package src.main.java.com.kayak.utilities;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.HashMap;
import java.util.List;

public class SeleniumUtilities {

    Logger log = Logger.getLogger(getClass().getSimpleName());

    public void pressBackSpaceByTimes(WebElement ele, int iTimes) {
        for (int i = 0; i < iTimes; i++) {
            ele.sendKeys(Keys.BACK_SPACE);
        }
    }

    public void pressEnterByTimes(WebElement ele, int iTimes) {
        for (int i = 0; i < iTimes; i++) {
            ele.sendKeys(Keys.ENTER);
        }
    }

    public void ufSendKeysAndPressEnter(WebElement ele, String keysToSend) {
        ele.sendKeys(keysToSend, Keys.ENTER);
    }

    public Actions mouseAction(WebDriver driver) {
        Actions actions = new Actions(driver);
        return actions;
    }

    public WebElement getElementByContainsTxt(List<WebElement> webElements, String containsText) {
        int atiT = 0;
        for (int i = 0; i < webElements.size(); i++) {
            String temp = webElements.get(i).getText();
            if (temp.contains(containsText)) {
                break;
            }
        }
        return webElements.get(atiT);
    }

    public HashMap<WebElement, WebElement> pairOfElements(List<WebElement> forKey, List<WebElement> forValue) {
        HashMap<WebElement, WebElement> hashMapElements = new HashMap<>();
        for (int i = 0; i < forKey.size(); i++) {
            hashMapElements.put(forKey.get(i), forValue.get(i));
        }
        return hashMapElements;
    }

    public void refreshPage(WebDriver driver){
        driver.navigate().refresh();
    }

}
