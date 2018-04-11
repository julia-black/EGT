package com.egt.qa.common.gui;

import com.egt.qa.common.gui.annotations.TestCaseID;
import com.egt.qa.common.gui.webdriver.BaseSelenium;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.lang.reflect.Method;

public class ScreenListener implements ITestListener {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Mentioning in log about the beggining of the new test with unique testCaseID
     *
     * @param iTestResult Automated test result
     * @see TestCaseID
     */
    @Override
    public void onTestStart(ITestResult iTestResult) {
        TestCaseID tcID;
        Method method = iTestResult.getMethod().getMethod();
        if (method.isAnnotationPresent(TestCaseID.class)) {
            tcID = method.getAnnotation(TestCaseID.class);
            if (tcID != null)
                log.info("TestCase " + tcID.value() + " is began");
        }
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] makeScreenshot() {
        return ((TakesScreenshot) BaseSelenium.getSeleniumDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        makeScreenshot();
        log.info("Test FAILED: " + result.getName());
        if (result.getThrowable() != null) {
            result.getThrowable().printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
