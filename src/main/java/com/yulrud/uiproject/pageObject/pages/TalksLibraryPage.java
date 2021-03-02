package com.yulrud.uiproject.pageObject.pages;

import com.yulrud.uiproject.pageObject.pageElements.SimpleFilter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class TalksLibraryPage extends BasePage {
    private static final String TALKS_LIBRARY_PAGE_TITLE = "Events Portal - Talks";
    private static final String TALKS_EVENT_TITLES_CSS = "div.evnt-card-cell h1";

    private SimpleFilter simpleFilter;


    public TalksLibraryPage(WebDriver driver) {
        super(driver);
        simpleFilter = new SimpleFilter(driver);
    }

    public String getExpectedPageTitle() {
        return TALKS_LIBRARY_PAGE_TITLE;
    }

    public void openMoreFilters() {
        waitDataIsLoaded();
        simpleFilter.clickMoreFiltersButton();
    }

    public void filterByLocation(String locationValue) {
        simpleFilter.filterByFirstMatchingValue(SimpleFilter.LOCATION_FILTER_ID, locationValue);
    }

    public void filterByCategory(String categoryValue) {
        simpleFilter.filterByAllMatchingValues(SimpleFilter.CATEGORY_FILTER_ID, categoryValue);
    }

    public void filterByLanguage(String languageValue) {
        simpleFilter.filterByExactValueFromList(SimpleFilter.LANGUAGE_FILTER_ID, languageValue);
    }

    public void searchByKeyWord(String keyWord) {
        simpleFilter.filterByKeyWord(keyWord);
    }

    public List<String> getTalksTitles() {
        return getElements(TALKS_EVENT_TITLES_CSS)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
