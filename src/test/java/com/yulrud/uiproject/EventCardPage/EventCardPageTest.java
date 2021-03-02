package com.yulrud.uiproject.EventCardPage;

import com.yulrud.uiproject.BaseTest;
import com.yulrud.uiproject.steps.EventCardSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EventCardPageTest extends BaseTest {
    EventCardSteps eventCardSteps;

    @BeforeMethod
    public void before() {
        eventCardSteps = new EventCardSteps(this.driver);
    }

    //Task 5
    @Test(description = "Check event card can be opened and has correct fields' values")
    public void checkEventsCardIsOpenedAndHasValidFieldsValues() {
        eventCardSteps.openFirstPastEventCard();
        eventCardSteps.checkEventCardPageHasValidFieldsValues("Teachers Internship Online Program",
                "Соціальний освітній проєкт від експертів ЕРАМ та IT Ukraine Association",
                "Ua", "19 Jan 2021, 10:00 - 1 Feb 2021, 12:00");
    }
}
