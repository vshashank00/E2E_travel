package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Bookingpage extends Reuse.Resusable {
    WebDriver driver;
    Bookingpage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }
    String check="//input[@data-testid='first-inputbox-contact-details']";
    @FindBy(xpath = "//input[@data-testid=\"first-inputbox-contact-details\"]")
    WebElement Check_name;
    @FindBy(xpath = "(//div[@class=\"css-1dbjc4n r-1awozwy r-18u37iz r-1wtj0ep\"])[3]/div[1]")
    WebElement Country;
    @FindBy(xpath = "//img[contains(@src,'https://www.spicejet.com/public/loader.gif')]/ancestor::div[3]")
    WebElement loader1;
    @FindBy(css = "input[data-testid=\"city-inputbox-contact-details\"]")
    WebElement fillcity;
    @FindBy(xpath = "//div[contains(text(),'WhatsApp')]")
    WebElement checkbox1;
    @FindBy(xpath = "//div[contains(text(),'primary passenger')]")
    WebElement checkbox2;
    @FindBy(css = "input[data-testid=\"traveller-0-first-traveller-info-input-box\"]")
    WebElement fill_first_name;
    @FindBy(css = "input[data-testid=\"traveller-0-last-traveller-info-input-box\"]")
    WebElement fill_last_name;
    @FindBy(xpath = "//div[@data-testid=\"traveller-info-continue-cta\"]/child::div[contains(text(),'Continue')]")
    WebElement Continue;
    @FindBy(css = "input[data-testid=\"sc-member-mobile-number-input-box\"]")
    WebElement fill_mobile_number;

  public Addon setCheck(String name, String id, String country, String lastname, String number, String city){

        valuecheck(driver,"'first-inputbox-contact-details']",name);
        valuecheck(driver,"'last-inputbox-contact-details']",lastname);
        valuecheck(driver,"'contact-number-input-box']",number);
        valuecheck(driver,"'emailAddress-inputbox-contact-details']",id);
        Assert.assertEquals(Country.getText(),country);
        fillcity.sendKeys(city);
        checkbox1.click();
        checkbox2.click();
        visibble(fill_first_name);
        Assert.assertEquals(fill_first_name.getAttribute("value").toLowerCase(),name.toLowerCase());
        Assert.assertEquals(fill_last_name.getAttribute("value").toLowerCase(),lastname.toLowerCase());
        Assert.assertEquals(fill_mobile_number.getAttribute("value"),number);
        scrollIntoView(fill_mobile_number,driver);
        clickable(Continue,driver);
        movetoelement(Continue,driver);
        visibble(loader1);
        invisible(loader1);
        return new Addon(driver);



    }
}
