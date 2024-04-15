package Reuse;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;
import java.util.List;

public class Resusable {
    WebDriver driver;

    public WebDriverWait wait;
    Actions actions;
    Select sc;
    public void invisible(WebElement element){
       wait =new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
    public void visibble(WebElement element)
    {wait =new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void visibblelist(List<WebElement> element)
    {wait =new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }
    public void clickable(WebElement element){
        wait =new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(element));

    }
    public void movetoelement(WebElement element,WebDriver driver){
        actions=new Actions(driver);
        actions.moveToElement(element).click().build().perform();
    }

    protected void byvisibletext(WebElement element, String getdropdowntext){
        sc = new Select(element);
        sc.selectByVisibleText(getdropdowntext);
    }
    protected void textpresent(WebElement element,String text){
        wait =new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementValue(element,text));
    }

}
