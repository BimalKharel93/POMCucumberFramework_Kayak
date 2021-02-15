package src.main.java.com.kayak.utilities;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class BrowserUtilities {
    /*
    @Author: Bimal Kharel
    @Application: Kayak
    @Assignment: 02/09/2021
    @Framework: POM Design pattern
     */
    Logger log = Logger.getLogger(getClass().getSimpleName());

    @SuppressWarnings("deprecation")
    public boolean waitForElementToBeVisible(WebDriver driver, WebElement ele, int iTimeSeconds) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver).withTimeout(iTimeSeconds, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
        wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                if (ele.isDisplayed()) {
                    log.info("Element Displayed: " + ele);
                    return ele;
                } else {
                    log.info("******* Element NOT Displayed: " + ele);
                    return null;
                }
            }
        });
        return ele.isDisplayed();
    }

    public boolean waitForElementDisable(WebDriver driver, WebElement ele, int iTimeSeconds) {
        @SuppressWarnings("deprecation")
        FluentWait<WebDriver> wait = new FluentWait<>(driver).withTimeout(iTimeSeconds, TimeUnit.SECONDS).pollingEvery(50, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                if (!ele.isDisplayed()) {
                    log.info("Element Not Displayed: " + ele);
                    return ele;
                } else {
                    log.info("*******Element is Still Displaying :" + ele);
                    return null;
                }
            }
        });
        return !ele.isDisplayed();
    }

    public boolean isDisplayed(WebElement ele) throws Exception {
        boolean bRes_flag = false;
        try {
            if (ele.isDisplayed()) {
                log.info("Displayed " + ele);
                bRes_flag = true;
            }
        } catch (Exception e) {
            bRes_flag = false;
        }
        return bRes_flag;
    }

    public void screenShotBrowser(WebDriver driver, String classname) throws Exception {
        String destDir = "screenshots";
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        File scrFile = scrShot.getScreenshotAs(OutputType.FILE);
        String destFile = classname + ".png";

        try {
            FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "\\test-output" + destDir + " \\" + destFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void screenShotBrowserForWebElement(WebDriver driver, WebElement eleScreenArea, String className) {
        String destDir = "screenshots";
        String destFile = className + ".png";
        log.info("ScreenShot");

        Screenshot fpScreenShot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
                .takeScreenshot(driver, eleScreenArea);
        try {
            ImageIO.write(fpScreenShot.getImage(), "PNG", new File(System.getProperty("user.dir") + "\\target\\surefire-reposts\\"
                    + destDir + "\\" + destFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean scrollToView(WebDriver driver, WebElement ele) {
        boolean bRes_Flag = false;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView;", ele);
        bRes_Flag = true;
        return bRes_Flag;
    }

    public void clickUsingJs(WebElement ele) {
        JavascriptExecutor executor = (JavascriptExecutor) ApplicationHooks.driver;
        executor.executeScript("argument[0].click();", ele);
    }

    public void ufClick(WebElement ele) throws Exception {
        ele.click();
    }

    public void ufSendKeys(WebElement ele, String keysToSend) throws Exception {
        ele.sendKeys(keysToSend);
    }

    public String ufGetText(WebElement ele) throws Exception {
        String actualText = "";
        if (!ele.getText().isEmpty()) {
            actualText = ele.getText();
        } else if (!ele.getAttribute("value").isEmpty()) {
            actualText = ele.getAttribute("value");
        } else {
            actualText = "text not found";
        }
        return actualText;
    }

    public boolean waitForElementVisible_old(WebDriver driver, WebElement ele, int iTimeSeconds) throws Exception {
        boolean bRes_Flag = false;
        Wait<WebDriver> wait = new FluentWait<>(driver).pollingEvery(Duration.ofMillis(5000))
                .withTimeout(Duration.ofSeconds(iTimeSeconds)).ignoring(NoSuchElementException.class);
        Function<WebDriver, WebElement> function = new Function<WebDriver, WebElement>() {
            float isSecCount = 0;

            public WebElement apply(WebDriver arg0) {
                boolean bFlag = ele.isDisplayed();
                if (!bFlag) {
                    try {
                        log.info("******Failed at initial check. Hence handing with hard wait 1 second...." + ele);
                        Thread.sleep(100);
                    } catch (Exception e) {
                    }
                    bFlag = ele.isDisplayed();
                }
                isSecCount++;
                if (bFlag) {
                    log.info("Took " + isSecCount * 500 / 1000 + " seconds to find element " + ele);
                }
                return ele;
            }
        };
        wait.until(function);
        return bRes_Flag;
    }

    public boolean waitForElementDisable_old(WebDriver driver, WebElement ele, int inTimeSeconds) {
        boolean bRes_Flag = false;
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(inTimeSeconds)).pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);

        Function<WebDriver, WebElement> function = new Function<WebDriver, WebElement>() {
            float isSecCount = 0;

            public WebElement apply(WebDriver driver) {
                boolean bFlag = !ele.isDisplayed();
                isSecCount++;
                if (bFlag) {
                    log.info("Took: " + isSecCount * 500 / 1000 + " Seconds to Disable Element: " + ele);
                }
                return ele;
            }
        };
        wait.until(function);
        return bRes_Flag;
    }

    public void switchToWindowByIndex(WebDriver driver, int windowIndex) {
        Set<String> getAllWindow = driver.getWindowHandles();
        String[] getWindow = getAllWindow.toArray(new String[getAllWindow.size()]);
        driver.switchTo().window(getWindow[windowIndex]);
    }

    public WebElement noSuchElementFoundExceptionHandle(WebElement element) {
        try {
            if (element.isDisplayed())
                return element;
        } catch (org.openqa.selenium.NoSuchElementException noSuchElementException) {

        }
        return null;
    }
}
