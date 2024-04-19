package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
    @FindBy(xpath = "//img[contains(@src,'https://www.spicejet.com/public/loader.gif')]/ancestor::div[3]")
    WebElement loader1;
    @FindBy(xpath = "(//div[@class=\"css-1dbjc4n\"]/child::div[text()='Direct'])[1]")
    WebElement Depdirect_radio;
    @FindBy(xpath = "(//div[@class=\"css-1dbjc4n\"]/child::div[text()='Direct'])[2]")
    WebElement Retdirect_radio;
    @FindBy(xpath = "//div[@class=\"css-1dbjc4n r-1wtj0ep r-1f1sjgu\"]/descendant::div[text()='Continue']")
    WebElement Continue;



    void selectflight(){
        int c=0;
        Boolean flag=false;
        invisible(loader);
        Depdirect_radio.click();
       List<WebElement> searched_flights=driver.findElements(By.xpath(selectflightpath+c+"\"]/child::div[@class=\"css-1dbjc4n r-1xdf14d\"]/div"));
       visibblelist(searched_flights);
        selectflightwithtime("19:30",driver);//self made function
        visibble(loader1);
        invisible(loader1);
        scrollIntoView(Retdirect_radio,driver);//this is js code you can find this in resusable class
        Retdirect_radio.click();
        selectflightwithtime("16:35",driver);
        invisible(loader1);
        movetoelement(Continue,driver);






    }
}
