package actions;

import browser_manager.BrowserDriverManager;
import enums.Waits;
import framework_constants.Constants;
import framework_constants.FilePath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigFileManager;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class WebElementInteraction {

    private WebDriver webDriver;
    private ConfigFileManager configFileManager;
    int maxTry = 4;

    public WebElementInteraction() {
        webDriver = BrowserDriverManager.getDriver();
        configFileManager = ConfigFileManager.getInstance(FilePath.COMMON_DATA);
    }

    public boolean awaitForElement(WebElement element, Waits wait, long seconds){
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT));
        WebDriverWait driverWait = new WebDriverWait(webDriver, Duration.ofSeconds(seconds));
        int trailCount = 0;
        while (trailCount < maxTry){
            switch (wait){
                case VISIBILITY -> {
                    try {
                        driverWait.until(ExpectedConditions.visibilityOf(element));//Old way
                        return true;
                    } catch (Exception exception){
//                        log.info(exception.getMessage());
                        trailCount++;
                    }
                }
                case CLICKABLE -> {
                    try {
                        driverWait.until(ExpectedConditions.elementToBeClickable(element));
                        return true;
                    } catch (Exception exception){
//                        log.info(exception.getMessage());
                        trailCount++;
                    }
                }
            }
        }
        return false;
    }

    public boolean awaitForElement(By locator, Waits wait, long seconds){
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT));
        WebDriverWait driverWait = new WebDriverWait(webDriver, Duration.ofSeconds(seconds));
        int trailCount = 0;
        while (trailCount < maxTry){
            switch (wait){
                case VISIBILITY -> {
                    try {
//                        driverWait.until(ExpectedConditions.presenceOfElementLocated(locator));//Old way
                        driverWait.until(driver -> driver.findElement(locator).isDisplayed()); //lambda way
                        return true;
                    } catch (Exception exception){
                        System.out.println(exception.getMessage());
                        exception.printStackTrace();
                        trailCount++;
                    }
                }
                case CLICKABLE -> {
                    try {
                        driverWait.until(ExpectedConditions.elementToBeClickable(locator));
                        return true;
                    } catch (Exception exception){
//                        log.info(exception.getMessage());
                        trailCount++;
                    }
                }
            }
        }
        return false;
    }

    public boolean waitForElementToAppearAndClick(WebElement element, String elemDetails) {
        return waitForElementToAppearAndClick(element, elemDetails, Constants.EXPLICIT_WAIT);
    }

    public boolean waitForElementToAppearAndClick(By element, String elemDetails) {
        return waitForElementToAppearAndClick(element, elemDetails, Constants.EXPLICIT_WAIT);
    }

    public boolean waitForElementToAppearAndClick(WebElement element, String elemDetails, long timeOut) {
        boolean result;
        try {
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT));
//            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(timeOut));
//            wait.until(ExpectedConditions.visibilityOf(element));
            awaitForElement(element, Waits.CLICKABLE, timeOut);
            element.click();
//            log.info("Element " + elemDetails + " is clicked");
            result = true;
        } catch (Exception e) {
//            log.info("Element " + elemDetails + " is not clicked , Reason ::: " + e.getMessage());
            result = false;
        } finally {
            webDriver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
        }
        return result;
    }

    public boolean waitForElementToAppearAndClick(By element, String elemDetails, long timeOut) {
        boolean result;
        try {
            webDriver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
            awaitForElement(element, Waits.CLICKABLE, timeOut);
            WebElement webElement = webDriver.findElement(element);
            webElement.click();
//            log.info("Element " + elemDetails + " is clicked");
            result = true;
        } catch (Exception e) {
//            log.info("Element " + elemDetails + " is not clicked , Reason ::: " + e.getMessage());
            result = false;
        } finally {
            webDriver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
        }
        return result;
    }

    public boolean typeInText(WebElement element, String text) {
        boolean result;
        try {
            awaitForElement(element, Waits.VISIBILITY, 20000);
            element.clear();
            element.sendKeys(text);
//            log.info(text + " is entered");
            result = true;
        } catch (Exception exception) {
//            log.info(text+" was not entered by the element, Reason ::: " + exception.getMessage());
            result = false;
        }
        return result;
    }

    public boolean typeInText(By element, String text) {
        boolean result;
        try {
            awaitForElement(element, Waits.VISIBILITY, Constants.IMPLICIT_WAIT);
            WebElement webElement = webDriver.findElement(element);
            webElement.clear();
            webElement.sendKeys(text);
//            log.info(text + " is entered");
            result = true;
        } catch (NoSuchElementException nsee) {
//            log.info("Element was not found, Reason ::: " + nsee.getMessage());
            result = false;
        } catch (Exception exception) {
//            log.info(text+" was not entered by the element, Reason ::: " + exception.getMessage());
            result = false;
        }
        return result;
    }
}
