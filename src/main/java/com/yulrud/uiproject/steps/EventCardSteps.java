package com.yulrud.uiproject.steps;

import com.yulrud.uiproject.pageObject.pages.EventCardPage;
import com.yulrud.uiproject.pageObject.pages.EventsListPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class EventCardSteps extends BaseSteps {
    private static final int FIRST_CARD_NUMBER = 1;

    EventsListPage eventsListPage = new EventsListPage(this.driver);
    EventCardPage eventCardPage = new EventCardPage(this.driver);

    public EventCardSteps(WebDriver driver) {
        super(driver);
    }

    @Step
    public void openFirstPastEventCard(){
        eventsListPage.navigateToPage();
        eventsListPage.openPastEventsTab();
        eventsListPage.checkPastEventsTabIsActive();
        eventsListPage.navigateToEventCard(FIRST_CARD_NUMBER);
        eventCardPage.checkEventCardPageOpened();
    }

    @Step
    public void checkEventCardPageHasValidFieldsValues(String title, String description, String language, String dates) {
        eventCardPage.checkTitle(title);
        eventCardPage.checkDescription(description);
        eventCardPage.checkLanguage(language);
        eventCardPage.checkDates(dates);
        eventCardPage.checkWatchRecordingButtonIsDisplayed();
    }
}
