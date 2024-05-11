package Reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reports {
    public static ExtentReports reports_travel(){
        ExtentSparkReporter extentSparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/src/main/java/Reporting/index.html");
        extentSparkReporter.config().setReportName("Spicejet Automation detail");
        extentSparkReporter.config().setDocumentTitle("Test Result");
        ExtentReports extentReporter=new ExtentReports();
        extentReporter.attachReporter(extentSparkReporter);
        extentReporter.setSystemInfo("SDET","Shashank");
        return extentReporter;

    }
}
