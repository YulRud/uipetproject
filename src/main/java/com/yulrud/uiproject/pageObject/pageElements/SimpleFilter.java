package com.yulrud.uiproject.pageObject.pageElements;

import com.yulrud.uiproject.pageObject.BaseWebObject;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.yulrud.uiproject.utils.TestLogger.log;

public class SimpleFilter extends BaseWebObject {
    public static final String CATEGORY_FILTER_ID = "category";
    public static final String LOCATION_FILTER_ID = "location";
    public static final String LANGUAGE_FILTER_ID = "language";
    private static final String KEY_WORD_FILTER_CSS = "div.evnt-search-filter input";
    private static final String LOCATION_FILTER_CSS = "div#filter_%s";
    private static final String FILTER_TEXT_FIELD_CSS = "+div input.evnt-text-fields";
    private static final String FILTER_FIRST_CHECKBOX_CSS = "+div  input[type='checkbox']+label";
    private static final String FILTER_CHECKBOX_BY_DATA_VALUE_CSS = "+div  input[type='checkbox']+label[data-value='%s']";
    private static final String FILTER_CHECKBOXES_CSS = "+div  input[type='checkbox']+label[class='form-check-label']";
    private static final String MORE_FILTERS_BUTTON_CSS = "div.evnt-toggle-filters-button span";

    public SimpleFilter(WebDriver driver) {
        super(driver);
    }

    public void filterByKeyWord(String keyWord) {
        enterText(KEY_WORD_FILTER_CSS, keyWord);
        waitDataIsLoaded();
        log(String.format("the data was filtered by %s key word", keyWord));
    }

    public void filterByExactValueFromList(String filterId, String value) {
        String filterLocator = getFilterLocator(filterId);
        clickElement(filterLocator);
        clickElement(filterLocator.concat(String.format(FILTER_CHECKBOX_BY_DATA_VALUE_CSS, value)));
        waitDataIsLoaded();
        clickElement(KEY_WORD_FILTER_CSS);
        log(String.format("the data was filtered with %s filter by value %s", filterId, value));
    }

    public void filterByFirstMatchingValue(String filterId, String value) {
        String filterLocator = getFilterLocator(filterId);
        clickElement(filterLocator);
        enterText(filterLocator.concat(FILTER_TEXT_FIELD_CSS), value);
        clickElement(filterLocator.concat(FILTER_FIRST_CHECKBOX_CSS));
        waitDataIsLoaded();
        clickElement(KEY_WORD_FILTER_CSS);
        log(String.format("the data was filtered with %s filter by value %s", filterId, value));
    }

    public void filterByAllMatchingValues(String filterId, String value) {
        WebElement checkbox;
        String filterLocator = getFilterLocator(filterId);
        clickElement(filterLocator);
        enterText(filterLocator.concat(FILTER_TEXT_FIELD_CSS), value);
        List<WebElement> checkboxes = getElements(filterLocator.concat(FILTER_CHECKBOXES_CSS));
        for (int i = 0; i <= checkboxes.size() - 1; i++) {
            checkbox = checkboxes.get(i);
            try {
                checkbox.click();
            } catch (ElementClickInterceptedException ex) {
                scrollHalfHeight();
                i--;
            }
        }
        waitDataIsLoaded();
        clickElement(KEY_WORD_FILTER_CSS);
        log(String.format("the data was filtered with %s filter by all matching options to %s", filterId, value));
    }

    public void clickMoreFiltersButton() {
        clickElement(MORE_FILTERS_BUTTON_CSS);
    }

    private String getFilterLocator(String filterId) {
        return String.format(LOCATION_FILTER_CSS, filterId);
    }
}
