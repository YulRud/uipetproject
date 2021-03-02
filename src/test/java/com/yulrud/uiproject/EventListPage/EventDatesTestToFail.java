package com.yulrud.uiproject.EventListPage;

import com.yulrud.uiproject.BaseTest;
import com.yulrud.uiproject.steps.EventsListSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EventDatesTestToFail extends BaseTest {
    EventsListSteps eventsListSteps;

    @BeforeMethod
    public void before() {
        eventsListSteps = new EventsListSteps(this.driver);
    }

    //Task 3
    @Test(description = "TO FAIL: Check all upcoming events happens after current date")
    public void checkUpcomingEventsDatesAreAfterCurrentDate() {
        eventsListSteps.openEventsPage();
        eventsListSteps.navigateToUpcomingEventsTab();
        eventsListSteps.checkPastEventsDatesAreBeforeCurrentDate();
        eventsListSteps.checkPastEventsDatesAreBeforeCurrentDate();
    }

    //Task 4
    @Test(description = "TO FAIL: Check all past events in Canada happened before current date")
    public void checkPastEventsInCanadaDatesAreBeforeCurrentDate() {
        eventsListSteps.openEventsPage();
        eventsListSteps.navigateToUpcomingEventsTab();
        eventsListSteps.filterByLocation("Canada");
        eventsListSteps.checkUpcomingEventsDatesAreAfterCurrentDate();
    }
}
