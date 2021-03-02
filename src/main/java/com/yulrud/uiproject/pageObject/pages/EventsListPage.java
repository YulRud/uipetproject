package com.yulrud.uiproject.pageObject.pages;

import com.yulrud.uiproject.pageObject.pageElements.SimpleFilter;
import com.yulrud.uiproject.utils.DateUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventsListPage extends BasePage {
    private static final String EVENTS_PAGE_TITLE = "Events Portal - Events";
    private static final String UPCOMING_EVENTS_TAB_XPATH = "//div[@class='evnt-events-tabs-nav']//span[text()='Upcoming events']/parent::a";
    private static final String PAST_EVENTS_TAB_XPATH = "//div[@class='evnt-events-tabs-nav']//span[text()='Past Events']/parent::a";
    private static final String EVENTS_TAB_COUNTER_XPATH = ("/span[starts-with(@class,'evnt-tab-counter')]");
    private static final String EVENTS_CONTAINER_CSS = "div.evnt-cards-container a";
    private static final String THE_OLDEST_EVENT_XPATH = "//div[@class='evnt-card-body']//span[text()='The Rolling Scopes Meetup 17']";
    private static final String EVENT_CARD_CSS = "div.evnt-events-row>div:nth-child(%s)";
    private static final String EVENT_CARD_LOCATION_CSS = " div.evnt-card-heading div>div.evnt-details-cell:first-child";
    private static final String EVENT_CARD_LANGUAGE_CSS = " div.evnt-details-cell.language-cell span";
    private static final String EVENT_CARD_NAME_CSS = " div.evnt-event-name span";
    private static final String EVENT_CARD_DATE_CSS = " div.evnt-event-dates span.date";
    private static final String EVENT_CARD_SPEAKERS_CSS = " div.speakers-wrapper div.evnt-speaker";
    private static final int FIRST_EVENT_CARD_NUMBER = 1;

    private static final Map<Integer, String> EVENT_CARD_FIELDS_ORDERED = new HashMap<Integer, String>() {{
        put(1, " div.evnt-card-wrapper>div:nth-child(1) div>div.evnt-details-cell:first-child");
        put(2, " div.evnt-card-wrapper>div:nth-child(1) div>div.evnt-details-cell:nth-child(2)");
        put(3, " div.evnt-card-wrapper>div:nth-child(2) div.evnt-card-cell>div:nth-child(1)");
        put(4, " div.evnt-card-wrapper>div:nth-child(2) div.evnt-card-cell>div:nth-child(2)");
        put(5, " div.evnt-card-wrapper>div:nth-child(3)>div");
    }};

    private SimpleFilter simpleFilter;

    public EventsListPage(WebDriver driver) {
        super(driver);
        simpleFilter = new SimpleFilter(driver);
    }

    public void navigateToPage() {
        super.navigateToPage("events");
    }

    public String getExpectedPageTitle() {
        return EVENTS_PAGE_TITLE;
    }

    public String getFirstEventCardLocator() {
        return String.format(EVENT_CARD_CSS, FIRST_EVENT_CARD_NUMBER);
    }

    public int getUpcomingEventsCounterValueOnTab() {
        return Integer.valueOf(getText(UPCOMING_EVENTS_TAB_XPATH.concat(EVENTS_TAB_COUNTER_XPATH)));
    }

    public int getPastEventsCounterValueOnTab() {
        return Integer.valueOf(getText(PAST_EVENTS_TAB_XPATH.concat(EVENTS_TAB_COUNTER_XPATH)));
    }

    public int getVisibleEventsQtyOnActiveTab() {
        return getElements(EVENTS_CONTAINER_CSS).size();
    }

    public int getAllPastEventsQtyOnTab() {
        scrollTillTheLastPageElement(THE_OLDEST_EVENT_XPATH);
        return getVisibleEventsQtyOnActiveTab();
    }

    public void navigateToEventCard(int eventCardNumber) {
        waitDataIsLoaded();
        String firstEventCardLocator = String.format(EVENT_CARD_CSS, eventCardNumber);
        scrollTillElement(firstEventCardLocator);
        waitDataIsLoaded();
        clickElement(firstEventCardLocator.concat(" a div"));
        waitDataIsLoaded();
    }

    public void openUpcomingEventsTab() {
        clickElement(UPCOMING_EVENTS_TAB_XPATH);
        waitDataIsLoaded();
    }

    public void openPastEventsTab() {
        clickElement(PAST_EVENTS_TAB_XPATH);
        waitDataIsLoaded();
    }

    public List<Date> getActiveEventsDate() {
        List<Date> dates = new ArrayList<>();
        List<WebElement> dateWebElements = getElements(EVENT_CARD_DATE_CSS);
        for (WebElement dateWebElement : dateWebElements) {
            String date = dateWebElement.getText();
            date = date.substring(date.length() - DateUtil.DEFAULT_DATE_FORMAT.length() - 1);
            dates.add(DateUtil.getDateFromText(date));
        }
        return dates;
    }

    public void filterByLocationFilter(String locationValue) {
        simpleFilter.filterByFirstMatchingValue(SimpleFilter.LOCATION_FILTER_ID, locationValue);
        waitDataIsLoaded();
    }

    public void checkEventCardFieldsOrder(String eventCardLocator) {
        checkEventCardFieldOrder(eventCardLocator, "location|online", 1);
        checkEventCardFieldOrder(eventCardLocator, "language", 2);
        checkEventCardFieldOrder(eventCardLocator, "name", 3);
        checkEventCardFieldOrder(eventCardLocator, "dates", 4);
        checkEventCardFieldOrder(eventCardLocator, "people", 5);
    }

    private void checkEventCardFieldOrder(String eventCardLocator, String field, int orderNumber) {
        WebElement language = getElement(eventCardLocator.concat(EVENT_CARD_FIELDS_ORDERED.get(orderNumber)));
        String elementClassValue = language.getAttribute("class");
        Pattern pattern = Pattern.compile(String.format("\\b(%s)\\b", field));
        Matcher matcher = pattern.matcher(elementClassValue);
        Assert.assertTrue(matcher.find(), String.format("Field %s is not on the expected location", field));
    }

    public void checkEventLocationValue(String expectedLocation, String eventCardLocator) {
        String actualLocation = getElement(eventCardLocator.concat(EVENT_CARD_LOCATION_CSS)).getText();
        Assert.assertEquals(actualLocation, expectedLocation, "Location is not equal to expected");
    }

    public void checkEventLanguageValue(String expectedLanguage, String eventCardLocator) {
        String actualLocation = getElement(eventCardLocator.concat(EVENT_CARD_LANGUAGE_CSS)).getText();
        Assert.assertEquals(actualLocation, expectedLanguage, "Language is not equal to expected");
    }

    public void checkEventNameValue(String expectedName, String eventCardLocator) {
        String actualLocation = getElement(eventCardLocator.concat(EVENT_CARD_NAME_CSS)).getText();
        Assert.assertEquals(actualLocation, expectedName, "Name is not equal to expected");
    }

    public void checkEventDateValue(String expectedDate, String eventCardLocator) {
        String actualLocation = getElement(eventCardLocator.concat(EVENT_CARD_DATE_CSS)).getText();
        Assert.assertEquals(actualLocation, expectedDate, "Date is not equal to expected");
    }

    public void checkEventSpeakersValue(List<String> expectedSpeakers, String eventCardLocator) {
        List<WebElement> speakers = getElements(eventCardLocator.concat(EVENT_CARD_SPEAKERS_CSS));
        List<String> actualSpeakers = new ArrayList<>();
        for (WebElement speaker : speakers) {
            actualSpeakers.add(speaker.getAttribute("data-name").concat(speaker.getAttribute("data-job-title")));
        }
        Assert.assertEquals(actualSpeakers, expectedSpeakers, "The speakers list is not equal to expected");
    }

    public void checkUpcomingEventsTabIsActive() {
        String classValue = getElement(UPCOMING_EVENTS_TAB_XPATH).getAttribute("class");
        Assert.assertTrue(classValue.contains("active"), "Upcoming Events tab is not opened");
    }

    public void checkPastEventsTabIsActive() {
        String classValue = getElement(PAST_EVENTS_TAB_XPATH).getAttribute("class");
        Assert.assertTrue(classValue.contains("active"), "Upcoming Events tab is not opened");
    }
}
