package com.yulrud.uiproject.pageObject.pages;

import com.yulrud.uiproject.pageObject.BaseWebObject;
import org.openqa.selenium.*;

public class BasePage extends BaseWebObject {
    protected static final String BASE_URL = System.getProperty("base.url");
    private static final String ACCEPT_COOKIE_BUTTON_CSS = "button#onetrust-accept-btn-handler";

    public BasePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToPage(String url) {
        driver.get(BASE_URL.concat(url));
        acceptCookies();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void acceptCookies(){
        try{
            clickElement(ACCEPT_COOKIE_BUTTON_CSS);
        }catch (NoSuchElementException ex){

        }
    }
}
