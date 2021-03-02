package com.yulrud.uiproject;

import com.yulrud.uiproject.pageObject.BaseWebObject;
import com.yulrud.uiproject.webDriverUtils.Browsers;
import com.yulrud.uiproject.webDriverUtils.WebDriverFactory;
import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static com.yulrud.uiproject.TimeConstants.IMPLICIT_WAIT_TIMEOUT;
import static com.yulrud.uiproject.TimeConstants.PAGE_LOAD_TIMEOUT;

public abstract class BaseTest {
    protected WebDriver driver = null;

    @BeforeClass
    public void init() {
        BasicConfigurator.configure();
    }


    @BeforeMethod
    @Parameters("browser")
    public void beforeMethod(@Optional String browser) {
        this.driver = WebDriverFactory.create(browser == null ? Browsers.FIREFOX : Browsers.valueOf(browser));
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
        BaseWebObject.setTdriver(driver);
    }

    @AfterMethod
    public void thisMethod() {
        this.driver.quit();
    }
}
