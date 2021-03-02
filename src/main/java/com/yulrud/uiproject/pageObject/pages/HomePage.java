package com.yulrud.uiproject.pageObject.pages;

import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private static final String EVENTS_LINK_CSS = "li.nav-item.events-icon a";
    private static final String TALKS_LIBRARY_LINK_CSS = "li.nav-item.talks-library-icon a";

    public HomePage(WebDriver driver){
        super(driver);
    }

    public void openPage(){
        super.navigateToPage("");
    }

    public void navigateToEventsPage(){
        clickElement(EVENTS_LINK_CSS);
        waitDataIsLoaded();
    }

    public void navigateToVideoTab(){
        clickElement(TALKS_LIBRARY_LINK_CSS);
        waitDataIsLoaded();
    }


}
