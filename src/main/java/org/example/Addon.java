package org.example;

import Reuse.Resusable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class Addon extends Resusable {
    WebDriver driver;
    String cssofseat = "svg[data-testid='svg-img']>g>g>path:nth-child(1)";
    @FindBy(css = "div[class*='r-lrvibr']>svg[viewBox=\"0 0 12 12\"]>g[fill-rule=\"evenodd\"]>path[fill=\"#000\"]:nth-child(2)")
    WebElement cross;
    @FindBy(css = "div[id='at_addon_modal']")
    WebElement lowpricesbox;
    @FindBy(id = "at_addon_close_icon")
    WebElement lowpricecros;
    @FindBy(css = "div[data-testid=\"expandableList-header\"]")
    List<WebElement> add;
    @FindBy(xpath = "//div[text()='Total Add-ons']/parent::div/following-sibling::div")
    WebElement done;
    @FindBy(css = "div[class='css-1dbjc4n r-14lw9ot r-13awgt0 r-18u37iz r-1fviwye']")
    WebElement planewin;
    @FindBy(xpath = "//div[@data-testid=\"seat-map-columns\"]/following-sibling::div/descendant::div[@class=\"css-1dbjc4n r-13ubf1n r-1mnahxq\"]")
    List<WebElement> seatno;
    @FindBy(xpath = "//div[contains(text(),'Promo')]/parent::div")
    WebElement promobox;
    @FindBy(css = " div:nth-child(2) > div.css-1dbjc4n.r-1p0dtai.r-zso239.r-ipm5af > svg")
    WebElement promocheck;
    @FindBy(css = "div[class=\"css-1dbjc4n r-1loqt21 r-1otgn73\"] > div.css-1dbjc4n > svg")
    WebElement terms;
    @FindBy(xpath = "(//div[text()='Continue'])[4]")
    WebElement Continue;
    @FindBy(css = "div[class=\"css-76zvg2 r-1862ga2 r-poiln3 r-n6v787 r-15zivkp r-100vyta\"]")
    WebElement meal;
    @FindBy(css = "div[class*='r-1jkjb r-edyy15']")
    List<WebElement> foodoption;
    @FindBy(css = "div[class*='r-1oqcu8e r-13qz1uu']")
    List<WebElement> getFoodoption;
    @FindBy(css = "div[class='css-1dbjc4n r-1awozwy r-l0gwng']")
    WebElement fetchingfaretoast;
    @FindBy(xpath = "//div[@data-testid='spice-max-card-add-cta']/child::div[text()='Modify']")
    WebElement modify;
    @FindBy(xpath = "//*[@id='addons-container']/div/div[2]/div/div[3]/div/div/div/div[2]/div[2]/div[2]/div/div/div")
    WebElement edit;

    Addon(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    void addonpage() {
        visibble(cross);
        cross.click();
        Choose_Your_Seat(driver);
        fetchfare(fetchingfaretoast, promobox, promocheck, terms, driver);
        meal(done, meal, foodoption, getFoodoption, driver);
        if (modify.getText().equalsIgnoreCase("Modify")) {
            modify.click();
            visibble(edit);
            clickable(edit, driver);
            movetoelement(edit, driver);
            Choose_Your_Seat(driver);
            fetchfare(fetchingfaretoast, promobox, promocheck, terms, driver);
            meal(done, meal, foodoption, getFoodoption, driver);
        } else {
            Assert.fail();
        }


//        scrollIntoView(driver.findElement(By.cssSelector("div[data-testid=\"bookingFlow-meals-add-cta\"]")),driver);
//        clickable(driver.findElement(By.xpath("//div[text()='SpiceCafé']")),driver);
//        driver.findElement(By.xpath("//div[text()='SpiceCafé']")).click();
//        if(meal.getText().contains("You are entitled for an eatable. Please choose your selection from the below options")){
//            visibblelist(getFoodoption);
//            for(WebElement food:getFoodoption){
//                if(food.getText().contains("Vegetable Biryani")||food.getText().contains("Gluten-free dhokla"))
//                    food.findElement(By.cssSelector("div[class*=\"r-1g7fiml r-1777fci\"]>div[class*='r-ubezar r-1kfrs79']")).click();
//
//
//            }
//            movetoelement(done,driver);
//        }

//        visibble(lowpricesbox);
//        lowpricecros.click();
    }
}
