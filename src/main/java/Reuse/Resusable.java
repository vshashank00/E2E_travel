package Reuse;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class Resusable {
    WebDriver driver;
    String check="//input[@data-testid=";

    public WebDriverWait wait;
    static int c=0;
    int a=40;

    //span[text()='₹']/parent::div/preceding-sibling::div[@data-testid="spiceflex-flight-select-radio-button-1"]
    Actions actions;
    String selectflightpath="//div[@id=\"list-results-section-";
    String actualflighttime;
    Select sc;
    public void invisible(WebElement element){
       wait =new WebDriverWait(driver, Duration.ofSeconds(a));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
    public void visibble(WebElement element)
    {wait =new WebDriverWait(driver, Duration.ofSeconds(a));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void visibblelist(List<WebElement> element)
    {wait =new WebDriverWait(driver, Duration.ofSeconds(a));
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }
    public void clickable(WebElement element){
        wait =new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.elementToBeClickable(element));

    }
    public void movetoelement(WebElement element,WebDriver driver){
        actions=new Actions(driver);
        actions.moveToElement(element).click().build().perform();
    }
    public void movetoelement(WebElement element,WebDriver driver,int x,int y){
        actions=new Actions(driver);
        actions.moveToElement(element,x,y).click().build().perform();
    }


    protected void byvisibletext(WebElement element, String getdropdowntext){
        sc = new Select(element);
        sc.selectByVisibleText(getdropdowntext);
    }
    protected void textpresent(WebElement element,String text){
        wait =new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementValue(element,text));
    }
    protected void scrollIntoView(WebElement element,WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);//moving to that element then clicking
    }
    protected String selectflightwithtime(String timeofflight, WebDriver driver){
        List<WebElement> searched_flights=driver.findElements(By.xpath(selectflightpath+c+"\"]/child::div[@class=\"css-1dbjc4n r-1xdf14d\"]/div"));
        visibblelist(searched_flights);
        for(WebElement element:searched_flights){
            Boolean flag=false;
            int i=0;
//           List<WebElement> time=element.findElements(By.xpath("//div[@class=\"css-1dbjc4n r-y3rmyz\"]/child::div[@class=\"css-76zvg2 r-homxoj r-1i10wst r-1kfrs79\"][1]"));
            List<WebElement> time  = element.findElements(By.cssSelector("div[class=\"css-1dbjc4n\"] div[class=\"css-1dbjc4n r-y3rmyz\"]>div[class=\"css-76zvg2 r-homxoj r-1i10wst r-1kfrs79\"]"));
            for (WebElement t:time){

                if (t.getText().equals(timeofflight)&&i%2==0){
                    flag=true;
                    c++;
                    break;
                }
                i++;
            }
            if (flag){
                WebElement element1=element.findElement(By.cssSelector("div[data-testid='spiceflex-flight-select-radio-button-1']>div>svg"));
                clickable(element1);
                scrollIntoView(element1,driver);
                element1.click();
                if(!element.findElement(By.cssSelector("div[data-testid='spiceflex-flight-select-radio-button-1']>div>svg>g")).isDisplayed()){
                    Assert.fail("Spice flex button is not selected");
                }
                actualflighttime=element.getText().split("\n")[0];
                break;

            }
        }

return actualflighttime;
    }
     protected void valuecheck(WebDriver driver,String remainingxpath,String expected){
        visibble(driver.findElement(By.xpath(check+remainingxpath)));
        Assert.assertEquals(driver.findElement(By.xpath(check+remainingxpath)).getAttribute("value").toLowerCase(),expected.toLowerCase());

    }

}
