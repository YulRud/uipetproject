package com.yulrud.uiproject.steps;

import com.yulrud.uiproject.pageObject.BaseWebObject;
import com.yulrud.uiproject.pageObject.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class BaseSteps extends BaseWebObject {
    public BaseSteps(WebDriver driver) {
        super(driver);
    }

    BasePage basePage = new BasePage(this.driver);

    @Step
    public void checkTitleIs(final String expectedTitle) {
        Assert.assertEquals(basePage.getPageTitle(), expectedTitle, "Title is not as expected!");
    }
}
