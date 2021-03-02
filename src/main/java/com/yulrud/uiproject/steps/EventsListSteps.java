package com.yulrud.uiproject.steps;

import com.yulrud.uiproject.pageObject.pages.EventsListPage;
import com.yulrud.uiproject.pageObject.pages.HomePage;
import com.yulrud.uiproject.utils.DateUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Date;
import java.util.List;

public class EventsListSteps extends BaseSteps {
    HomePage homePage = new HomePage(this.driver);
    EventsListPage eventsListPage = new EventsListPage(this.driver);

    public EventsListSteps(WebDriver driver) {
        super(driver);
    }

    @Step
    public void openHomePage() {
        homePage.openPage();
    }

    @Step
    public void navigateToEventsPage() {
        homePage.navigateToEventsPage();
        checkTitleIs(eventsListPage.getExpectedPageTitle());
    }

    @Step
    public void openEventsPage() {
        eventsListPage.navigateToPage();
        checkTitleIs(eventsListPage.getExpectedPageTitle());
    }

    @Step
    public void navigateToUpcomingEventsTab() {
        eventsListPage.openUpcomingEventsTab();
        eventsListPage.checkUpcomingEventsTabIsActive();
    }

    @Step
    public void navigateToPastEventsTab() {
        eventsListPage.openPastEventsTab();
        eventsListPage.checkPastEventsTabIsActive();
    }

    @Step
    public void filterByLocation(String location) {
        eventsListPage.filterByLocationFilter(location);
    }

    @Step
    public void checkFirstEventCardFieldsOrder() {
        String firstEventCardLocator = eventsListPage.getFirstEventCardLocator();
        eventsListPage.checkEventCardFieldsOrder(firstEventCardLocator);
    }

    @Step
    public void checkFirstEventCardFieldsInformation(String location, String language, String name, String date,
                                                     List<String> speakers) {
        String firstEventCardLocator = eventsListPage.getFirstEventCardLocator();
        eventsListPage.checkEventLocationValue(location, firstEventCardLocator);
        eventsListPage.checkEventLanguageValue(language, firstEventCardLocator);
        eventsListPage.checkEventNameValue(name, firstEventCardLocator);
        eventsListPage.checkEventDateValue(date, firstEventCardLocator);
        eventsListPage.checkEventSpeakersValue(speakers, firstEventCardLocator);
    }

    @Step
    public void checkUpcomingEventsQtyEqualToCounterQty() {
        Assert.assertEquals(eventsListPage.getUpcomingEventsCounterValueOnTab(), eventsListPage.getVisibleEventsQtyOnActiveTab(),
                "The upcoming events qty on counter is not equal to real qty of events");
    }

    @Step
    public void checkAllPastEventsQtyEqualToCounterQty() {
        Assert.assertEquals(eventsListPage.getPastEventsCounterValueOnTab(), eventsListPage.getAllPastEventsQtyOnTab(),
                "The past events qty on counter is not equal to real qty of events");
    }

    @Step
    public void checkVisiblePastEventsQtyEqualToCounterQty() {
        Assert.assertEquals(eventsListPage.getPastEventsCounterValueOnTab(), eventsListPage.getVisibleEventsQtyOnActiveTab(),
                "The past events qty on counter is not equal to real qty of events");
    }

    @Step
    public void checkUpcomingEventsDatesAreAfterCurrentDate() {
        List<Date> actualEventDates = eventsListPage.getActiveEventsDate();
        for (Date actualDate : actualEventDates) {
            Assert.assertTrue(actualDate.after(DateUtil.getCurrentDate()), String.format("Upcoming event date: %s is not after the current date", actualDate));
        }
    }

    @Step
    public void checkPastEventsDatesAreBeforeCurrentDate() {
        List<Date> actualEventDates = eventsListPage.getActiveEventsDate();
        for (Date actualDate : actualEventDates) {
            Assert.assertTrue(actualDate.before(DateUtil.getCurrentDate()), String.format("Past event date: %s is not before the current date", actualDate));
        }
    }
}
