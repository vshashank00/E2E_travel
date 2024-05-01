package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.swing.*;
import java.time.Duration;
import java.util.List;

public class Book_flight extends Reuse.Resusable {
    WebDriver driver;
    Book_flight(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    String selectflightpath="//div[@id=\"list-results-section-";
    @FindBy(xpath = "(//div[text()='You are being redirected to the partner website.']/ancestor::div)[1]/following-sibling::div[1]/descendant::div[@class=\"css-1dbjc4n r-1awozwy r-l0gwng\"]")
    WebElement loader;
    @FindBy(css = "div[class='css-1dbjc4n r-1awozwy r-l0gwng']")
    WebElement loader1;
    @FindBy(xpath = "(//div[@class=\"css-1dbjc4n\"]/child::div[text()='Direct'])[1]")
    WebElement Depdirect_radio;
    @FindBy(xpath = "(//div[@class=\"css-1dbjc4n\"]/child::div[text()='Direct'])[2]")
    WebElement Retdirect_radio;
    @FindBy(xpath = "//div[@class=\"css-1dbjc4n r-1wtj0ep r-1f1sjgu\"]/descendant::div[text()='Continue']")
    WebElement Continue;
    String actualflightime;


    Bookingpage selectflight(String timeOfDepartureFlight, String timeOfReturnFlight) throws InterruptedException {
        int c=0;
        invisible(loader);
        visibble(Depdirect_radio);
        Depdirect_radio.click();
        Depdirect_radio.isSelected();
        List<WebElement> searched_flights=driver.findElements(By.xpath(selectflightpath+c+"\"]/child::div[@class=\"css-1dbjc4n r-1xdf14d\"]/div"));
        visibblelist(searched_flights);
//        JavascriptExecutor js = (JavascriptExecutor)driver;
//        js.executeScript("document.person= prompt('Please enter the time of flight on which you want to go');");
//       Thread.sleep(10000);
//        String timeofflightdep=(String)  js.executeScript("return document.person");
        actualflightime=selectflightwithtime(timeOfDepartureFlight,driver);//self made function
        Assert.assertEquals(actualflightime,timeOfDepartureFlight);
        visibble(loader1);
        invisible(loader1);
        scrollIntoView(Retdirect_radio,driver);//this is js code you can find this in resusable class
        Retdirect_radio.click();
        Retdirect_radio.isSelected();
//        JFrame jFrame=new JFrame();
//        jFrame.setAlwaysOnTop(true);//will show you the dialog box
//       String timeofslightre= JOptionPane.showInputDialog(jFrame,"Please enter the time of flight on which you want to go");
        actualflightime=selectflightwithtime(timeOfReturnFlight,driver);
        Assert.assertEquals(actualflightime,timeOfReturnFlight,"if fails time of flght is mismatched");
        invisible(loader1);
        movetoelement(Continue,driver);
        visibble(loader1);
        invisible(loader1);
        return new Bookingpage(driver);






    }
}
