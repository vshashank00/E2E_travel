package org.example;

import Reuse.Resusable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import java.util.List;

public class Search_Flight extends Resusable {
    static  Logger logger= LogManager.getLogger(Search_Flight.class);
    WebDriver driver;
    @FindBy(xpath = "//div[contains(text(),'round trip')]/parent::div/preceding-sibling::div")
    WebElement roundtrip;
    @FindBy(xpath = "//div[@data-testid=\"to-testID-origin\"]")
    WebElement origin;
    @FindBy(css = "div[class='css-1dbjc4n r-1awozwy r-1loqt21 r-18u37iz r-1wtj0ep r-b5h31w r-rnv2vh r-5njf8e r-1otgn73']")
    List<WebElement> cities;
    @FindBy(xpath = "//div[contains(@data-testid,\"undefined-month\")]")
    List<WebElement> selectmonth;
    @FindBy(xpath = "//div[@data-testid=\"undefined-calendar-picker\"]/child::div[1]")
    WebElement nextmonth;
    @FindBy(xpath = "//div[@data-testid=\"home-page-flight-cta\"]/child::div[text()='Search Flight']")
    WebElement searchbutton;
    @FindBy(xpath = "(//div[text()='You are being redirected to the partner website.']/ancestor::div)[1]/following-sibling::div[1]/descendant::div[@class=\"css-1dbjc4n r-1awozwy r-l0gwng\"]")
    WebElement loader;
    @FindBy(xpath = "//div[contains(text(),'Shashank')]")
    WebElement verifyafterlogin;

    Search_Flight(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Book_flight search_Filghts(String source, String sourceairport, String destairport, String destination, String travelDate, String travelYear, String travelMonth, String returnDate, String returnYear, String returnMonth, String name) throws InterruptedException {
        Assert.assertTrue(verifyafterlogin.getText().contains(name));
        roundtrip.click();
        logger.info("hello from search flights");
        origin.click();
        for (WebElement element : cities) {

            if (element.getText().split("\n")[0].equalsIgnoreCase(source) && element.getText().split("\n")[1].contains(sourceairport)) {
                element.click();
                break;

            }
        }

        for (WebElement element : cities) {

            if (element.getText().split("\n")[0].equalsIgnoreCase(destination) && element.getText().split("\n")[1].contains(destairport)) {
                element.click();
                break;
            }
        }
        boolean flag = false;
        visibblelist(selectmonth);
        for (WebElement element : selectmonth) {
            visibble(element);
            int i = selectmonth.indexOf(element);
            if (element.getText().contains(travelMonth + " " + travelYear)) {
               List<WebElement>list= element.findElements(By.cssSelector("div[data-testid*='undefined-calendar-day-']"));
                visibblelist(list);
                for (WebElement date:list){
                    if (date.getText().equalsIgnoreCase(travelDate)){
                        flag=true;
                        Assert.assertEquals(date.getText(),travelDate);
                        date.click();
                        break;
                    }
                    else if (Integer.parseInt(travelDate)>31){
                        System.out.println("date is not valid");
                        logger.info("date is not valid");
                        Assert.fail();
                    }
                }
            } else if (i % 2 == 0 && i != 0) {
                nextmonth.click();
            }else if (flag)
                break;


        }
        Assert.assertTrue(flag, "date is not selected");
        flag = false;
        visibblelist(selectmonth);
        for (WebElement element : selectmonth) {
            if (flag)
                break;
            visibble(element);
            int i = selectmonth.indexOf(element);
            if (element.getText().contains(returnMonth + " " + returnYear)) {
                List<WebElement>list= element.findElements(By.cssSelector("div[data-testid*='undefined-calendar-day-']"));
                visibblelist(list);
                for (WebElement date:list){
                    if (date.getText().equalsIgnoreCase(returnDate)){
                        Assert.assertEquals(date.getText(),returnDate);
                        date.click();
                        flag=true;
                        break;
                    }
                    else if (Integer.parseInt(returnDate)>31){
                        System.out.println("date is not valid");
                        Assert.fail();
                    }
                }

            } else if (i % 2 == 0 && i != 0) {
                nextmonth.click();}

        }
        Assert.assertTrue(flag, "date is not selected");
        movetoelement(searchbutton,driver);
        visibble(loader);
        return new Book_flight(driver);
    }
}
