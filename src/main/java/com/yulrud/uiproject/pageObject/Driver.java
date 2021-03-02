package com.yulrud.uiproject.pageObject;

import org.openqa.selenium.WebDriver;

import static com.yulrud.uiproject.utils.TestLogger.log;

public class Driver {

    protected WebDriver driver;

    public Driver(WebDriver driver) {
        this.driver = driver;
        log("Driver initialized");
    }
}
