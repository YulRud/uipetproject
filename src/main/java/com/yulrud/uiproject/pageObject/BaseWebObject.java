package com.yulrud.uiproject.pageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.yulrud.uiproject.TimeConstants.GET_ELEMENT_TIMEOUT;
import static com.yulrud.uiproject.TimeConstants.SHORT_TIMEOUT;
import static com.yulrud.uiproject.utils.TestLogger.log;

public abstract class BaseWebObject extends Driver {
    private static final int MAX_SCROLL_QTY = 100;
    private static ThreadLocal<WebDriver> tdriver = new ThreadLocal<>();

    public BaseWebObject(WebDriver driver) {
        super(driver);
    }

    public static WebDriver getTdriver() {
        return tdriver.get();
    }

    public static void setTdriver(WebDriver driver) {
        tdriver.set(driver);
    }

    private static By findBy(String loctor) {
        if (loctor.startsWith("//") || loctor.startsWith("./")) {
            return By.xpath(loctor);
        } else {
            return By.cssSelector(loctor);
        }
    }

    private static String waitForElementNotVisible(int timeOutInSeconds, WebDriver driver, String elementLocator) {
        try {
            (new WebDriverWait(driver, timeOutInSeconds))
                    .until(ExpectedConditions.invisibilityOfElementLocated(findBy(elementLocator)));
            return null;
        } catch (TimeoutException e) {
            log(String.format("Element %s is not disappearing.", elementLocator));
            return String.format("Element %s is not disappearing.", elementLocator);
        }
    }

    public WebElement getElement(String locator) {
        return driver.findElement(findBy(locator));
    }

    public List<WebElement> getElements(String locator) {
        return driver.findElements(findBy(locator));
    }

    public boolean isElementExist(String locator) {
        try {
            getElement(locator);
        } catch (NoSuchElementException ex) {
            log(String.format("There is no element with locator %s", locator));
            return false;
        }
        log(String.format("Element with locator %s exists", locator));
        return true;
    }

    public String getText(String locator) {
        WebElement element = new WebDriverWait(driver, GET_ELEMENT_TIMEOUT).until(driver -> getElement(locator));
        String text = element.getText();
        log(String.format("Element %s has text %s", locator, text));
        return text;
    }

    public void clickElement(String locator) {
        WebElement element = new WebDriverWait(driver, GET_ELEMENT_TIMEOUT).until(driver1 -> getElement(locator));
        element.click();
        log(String.format("Element %s was clicked", locator));
    }

    public void enterText(String locator, String text) {
        WebElement input = new WebDriverWait(driver, GET_ELEMENT_TIMEOUT).until(driver1 -> getElement(locator));
        input.clear();
        input.sendKeys(text);
        log(String.format("Element %s has the text %s entered", locator, text));
    }

    public void scrollTillTheLastPageElement(String locator) {
        for (int i = 0; i <= MAX_SCROLL_QTY; i++) {
            if (!isElementExist(locator)) {
                ((JavascriptExecutor) driver)
                        .executeScript("window.scrollTo(0, document.body.scrollHeight)");
            } else {
                log(String.format("The last element %s was found on %d page", locator, i));
                break;
            }
        }
    }

    public void scrollHalfHeight() {
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight/2)");
        log("The page was scrolled till the half of height");
    }

    public void scrollTillElement(String locator) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView();", getElement(locator));
        log(String.format("The page was scrolled till %s element", locator));
    }

    public void waitDataIsLoaded() {
        String spinnerLocator = ".evnt-global-loader";
        waitForElementNotVisible(SHORT_TIMEOUT, driver, spinnerLocator);
        log("New data was loaded on the page");
    }
}
