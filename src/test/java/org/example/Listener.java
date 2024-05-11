package org.example;


import Reporting.Reports;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listener extends BaseTest implements ITestListener  {


    ExtentReports extentReports=Reports.reports_travel();
    ExtentTest test;
    ThreadLocal<ExtentTest>testThreadLocal=new ThreadLocal<>();
    @Override
    public void onTestStart(ITestResult result) {
       test=extentReports.createTest(result.getMethod().getMethodName());
       testThreadLocal.set(test);

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        testThreadLocal.get().log(Status.PASS,"test passed");

    }

    @Override
    public void onTestFailure(ITestResult result) {


        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        String path="";
        try {
            path=sc(result.getMethod().getMethodName(),driver);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        testThreadLocal.get().addScreenCaptureFromPath(path);
        testThreadLocal.get().fail(result.getThrowable());
        testThreadLocal.get().log(Status.FAIL,"failde");
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
}
