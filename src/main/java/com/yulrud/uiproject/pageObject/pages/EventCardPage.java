package com.yulrud.uiproject.pageObject.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class EventCardPage extends BasePage {
    public static final String EVENT_TITLE_MULTILINE_CSS = "span.evnt-title.multiline";
    public static final String EVENT_TITLE_CSS = "section#home div.evnt-content-text h1";
    public static final String EVENT_SHORT_DESCRIPTION_CSS = "section#home div.evnt-content-text h2";
    public static final String EVENT_DATE_CSS = "section#home span.date";
    public static final String EVENT_LANGUAGE_CSS = "section#home div.evnt-details.language-wrapper span";
    public static final String EVENT_WATCH_RECORDING_BUTTON_CSS = "button.evnt-button.btn.sky.reg-button.watch-recording";

    public EventCardPage(WebDriver driver) {
        super(driver);
    }

    public void checkEventCardPageOpened() {
        Assert.assertTrue(isEventCardPageOpened(), "The Event Card Page was not opened");
    }

    private boolean isEventCardPageOpened() {
        try {
            getElement(EVENT_TITLE_MULTILINE_CSS);
        } catch (NoSuchElementException ex) {
            return false;
        }
        return true;
    }
    public void checkTitle(String expectedTitle) {
        Assert.assertEquals(getText(EVENT_TITLE_CSS), expectedTitle, "The event title is not equal to expected");
    }

    public void checkDescription(String expectedDescription) {
        Assert.assertEquals(getText(EVENT_SHORT_DESCRIPTION_CSS), expectedDescription, "The event short description is not equal to expected");
    }

    public void checkLanguage(String expectedLanguage) {
        Assert.assertEquals(getText(EVENT_LANGUAGE_CSS), expectedLanguage, "The event language is not equal to expected");
    }

    public void checkDates(String expectedDates) {
        Assert.assertEquals(getText(EVENT_DATE_CSS), expectedDates, "The dates is not equal to expected");
    }

    public void checkWatchRecordingButtonIsDisplayed() {
        Assert.assertTrue(isWatchRecordingButtonExist(), "There is no the Watch recording button on the page");
    }

    private boolean isWatchRecordingButtonExist() {
        try {
            getElement(EVENT_WATCH_RECORDING_BUTTON_CSS);
        } catch (NoSuchElementException ex) {
            return false;
        }
        return true;
    }
}
