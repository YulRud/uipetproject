package com.yulrud.uiproject.utils;

import io.qameta.allure.Attachment;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Reporter;

public class TestLogger {

    public static final Logger LOGGER = LogManager.getLogger(TestLogger.class.getName());

    public static void log(String info){
        LOGGER.info(info);
        Reporter.log(info);
        saveTextLog(info);
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    private TestLogger() {
    }
}
