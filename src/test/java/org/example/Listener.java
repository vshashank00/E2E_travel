package org.example;


import Reporting.Reports;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.reporter.ExtentReporter;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listener extends BaseTest implements ITestListener  {


    ExtentReports extentReports=Reports.reports_travel();
    ExtentTest test;
    @Override
    public void onTestStart(ITestResult result) {
       test=extentReports.createTest(result.getMethod().getMethodName());

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS,"test passed");

    }

    @Override
    public void onTestFailure(ITestResult result) {


        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        String path="";
        try {
            path=sc(result.getMethod().getMethodName(),driver);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        test.addScreenCaptureFromPath(path);
        test.fail(result.getThrowable());
        test.log(Status.FAIL,"failde");
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
}
