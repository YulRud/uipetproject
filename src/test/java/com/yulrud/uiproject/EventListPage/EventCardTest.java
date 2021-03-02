package com.yulrud.uiproject.EventListPage;

import com.yulrud.uiproject.BaseTest;
import com.yulrud.uiproject.steps.EventsListSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;

public class EventCardTest extends BaseTest {
    EventsListSteps eventsListSteps;

    @BeforeMethod
    public void before() {
        eventsListSteps = new EventsListSteps(this.driver);
    }

    //Task 2
    @Test(description = "Check upcoming card's fields are located in the correct order")
    public void checkUpcomingEventCardFieldsOrder() {
        eventsListSteps.openEventsPage();
        eventsListSteps.navigateToUpcomingEventsTab();
        eventsListSteps.checkFirstEventCardFieldsOrder();
    }

    @Test(description = "Check first upcoming card's fields have correct values")
    public void checkUpcomingEventCardFieldsValues() {
        eventsListSteps.openEventsPage();
        eventsListSteps.navigateToUpcomingEventsTab();
        eventsListSteps.checkFirstEventCardFieldsInformation("Online only", "En",
                "Check-in app guide", "1 Feb - 25 Aug 2025",
                Collections.singletonList("SpeakerInformation about the speaker will be available soon"));
    }
}
