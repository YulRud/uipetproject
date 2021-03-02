package com.yulrud.uiproject.EventListPage;

import com.yulrud.uiproject.BaseTest;
import com.yulrud.uiproject.steps.EventsListSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EventCounterTest extends BaseTest {
    EventsListSteps eventsListSteps;

    @BeforeMethod
    public void before() {
        eventsListSteps = new EventsListSteps(this.driver);
    }

    //Task1
    @Test(description = "Check event counter on the tab shows correct values for the Upcoming Events tab")
    public void checkUpcomingEventsQtyEqualToCounterQty() {
        eventsListSteps.openHomePage();
        eventsListSteps.navigateToEventsPage();
        eventsListSteps.navigateToUpcomingEventsTab();
        eventsListSteps.checkUpcomingEventsQtyEqualToCounterQty();
    }

    //Task1 added by me
    @Test(description = "Check event counter on the tab shows correct values for the Past Events tab")
    public void checkPastEventsQtyEqualToCounterQty() {
        eventsListSteps.openHomePage();
        eventsListSteps.navigateToEventsPage();
        eventsListSteps.navigateToPastEventsTab();
        eventsListSteps.checkAllPastEventsQtyEqualToCounterQty();
    }

    //Task 4
    @Test(description = "Check event counter on the tab shows correct values for the Past Events tab happened in Canada")
    public void checkPastEventsInCanadaEqualQtyCounterQty() {
        eventsListSteps.openEventsPage();
        eventsListSteps.navigateToPastEventsTab();
        eventsListSteps.filterByLocation("Canada");
        eventsListSteps.checkVisiblePastEventsQtyEqualToCounterQty();
    }
}
