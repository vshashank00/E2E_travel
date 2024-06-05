package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Login extends Reuse.Resusable{
    WebDriver driver;
    Login(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//div[text()=\"Login\"]")
    WebElement login;
    @FindBy(xpath = "//div[text()='Email']")
    WebElement clickonEmail;
    @FindBy(xpath = "//input[@type='email']")
    WebElement emailtofeed;
    @FindBy(xpath = "//input[@type='password']")
    WebElement passtofeed;
    @FindBy(xpath = "//div[contains(text(),'LOGIN')]")
    WebElement clickonlogin;
    @FindBy(xpath = "//div[contains(text(),'Shashank')]")
    WebElement verifyafterlogin;
    @FindBy(xpath = "//div[@class='css-1dbjc4n r-1wyyakw']/following-sibling::div")
    WebElement toastlogin;
     public static Logger logger= LogManager.getLogger(Login.class);

    public void UserLogin(String id, String pass, String name){
        clickable(login,driver);
        logger.info("hello");
        login.click();
        visibble(driver.findElement(By.xpath("//div[text()='Email']/ancestor::div[contains(@class,'css-1dbjc4n r-14lw9ot r-1p0dtai r-1pcd2l5 r-wk8lta r-u8s1d r-zchlnj r-ipm5af')]")));
        clickonEmail.click();
        emailtofeed.sendKeys(id);
        passtofeed.sendKeys(pass);
        movetoelement(clickonlogin,driver);
        logger.info("Logged in");
        visibble(toastlogin);
        invisible(toastlogin);

    }

}
