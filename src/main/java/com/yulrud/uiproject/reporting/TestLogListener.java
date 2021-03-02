package com.yulrud.uiproject.reporting;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static com.yulrud.uiproject.utils.TestLogger.LOGGER;
import static com.yulrud.uiproject.utils.TestLogger.log;

public class TestLogListener implements ITestListener {
    public void onTestStart(ITestResult result) {
        log(result.getMethod().getMethodName() + " Started");
        log(result.getMethod().getDescription());
    }

    public void onTestSuccess(ITestResult result) {
        LOGGER.info(result.getMethod().getMethodName() + " Passed");
    }

    public void onTestFailure(ITestResult result) {
        LOGGER.info("Failed because of - " + result.getThrowable());
    }

    public void onTestSkipped(ITestResult result) {
        LOGGER.info("Skipped because of - " + result.getThrowable());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub
    }

    public void onStart(ITestContext context) {
        LOGGER.info("=========== onStart :-" + context.getName() + "===============");
    }

    public void onFinish(ITestContext context) {
        LOGGER.info("=========== onFinish :-" + context.getName() + "===============");
    }
}
