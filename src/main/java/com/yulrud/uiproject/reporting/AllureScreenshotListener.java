package com.yulrud.uiproject.reporting;

import com.yulrud.uiproject.pageObject.BaseWebObject;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class AllureScreenshotListener implements IInvokedMethodListener {

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] getScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (!testResult.isSuccess()) {
            getScreenshot(BaseWebObject.getTdriver());
        }
    }
}
