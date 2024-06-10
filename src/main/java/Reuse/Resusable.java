package Reuse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.swing.*;
import java.time.Duration;
import java.util.List;

public class Resusable {
    Logger logger= LogManager.getLogger(Resusable.class);
    static int c = 0;
    public WebDriverWait wait;
    WebDriver driver;
    String check = "//input[@data-testid=";
    String cssofseat = "svg[data-testid='svg-img']>g>g>path:nth-child(1)";
    int a = 40;

    //span[text()='₹']/parent::div/preceding-sibling::div[@data-testid="spiceflex-flight-select-radio-button-1"]
    Actions actions;
    String selectflightpath = "//div[@id=\"list-results-section-";
    String actualflighttime;
    Select sc;
   static boolean flag=false;
    @FindBy(css = "div[data-testid=\"expandableList-header\"]")
    List<WebElement> add;
    @FindBy(xpath = "//div[@data-testid=\"seat-map-columns\"]/following-sibling::div/descendant::div[@class=\"css-1dbjc4n r-13ubf1n r-1mnahxq\"]")
    List<WebElement> seatno;
    @FindBy(css = "div[class='css-1dbjc4n r-14lw9ot r-13awgt0 r-18u37iz r-1fviwye']")
    WebElement planewin;
    @FindBy(xpath = "(//div[@class=\"css-1dbjc4n r-13awgt0 r-18u37iz r-1wtj0ep\"])[2]")
    WebElement retfood;
    JavascriptExecutor js;

    public void invisible(WebElement element) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(a));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
public  void  scroll(int x,int y,WebDriver driver){
    js = (JavascriptExecutor) driver;
    js.executeScript("window.scrollBy("+x+","+y+")");
}
    public void visibble(WebElement element) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(a));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void visibblelist(List<WebElement> element) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(a));
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }

    public void clickable(WebElement element, WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.elementToBeClickable(element));

    }

    public void movetoelement(WebElement element, WebDriver driver) {
        actions = new Actions(driver);
        actions.moveToElement(element).click(element).build().perform();
    }

    public void movetoelement(WebElement element, WebDriver driver, int x, int y) {
        actions = new Actions(driver);
        actions.moveToElement(element, x, y).click().build().perform();
    }


    protected void byvisibletext(WebElement element, String getdropdowntext) {
        sc = new Select(element);
        sc.selectByVisibleText(getdropdowntext);
    }

    protected void textpresent(WebElement element, String text) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementValue(element, text));
    }

    protected void scrollIntoView(WebElement element, WebDriver driver) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);//moving to that element then clicking
    }

    protected String selectflightwithtime(String timeofflight, WebDriver driver) {
        List<WebElement> searched_flights = driver.findElements(By.xpath(selectflightpath + c + "\"]/child::div[@class=\"css-1dbjc4n r-1xdf14d\"]/div"));
        visibblelist(searched_flights);
        for (WebElement element : searched_flights) {
            Boolean flag = false;
            int i = 0;
            if (element.findElements(By.cssSelector("div[class='css-1dbjc4n r-13awgt0']>div")).size()>2) {
                List<WebElement>list=element.findElements(By.cssSelector("div[class='css-1dbjc4n']>div[class='css-76zvg2 r-homxoj r-1i10wst r-1kfrs79']"));
                visibblelist(list);
                for (WebElement time:list){
                    if (time.getText().equals(timeofflight) && i % 2 == 0) {
                        flag = true;
                        actualflighttime=time.getText();
                        c++;
                        break;
                    }
                    i++;


                }

            } else {
//           List<WebElement> time=element.findElements(By.xpath("//div[@class=\"css-1dbjc4n r-y3rmyz\"]/child::div[@class=\"css-76zvg2 r-homxoj r-1i10wst r-1kfrs79\"][1]"));
                List<WebElement> time = element.findElements(By.cssSelector("div[class=\"css-1dbjc4n\"] div[class=\"css-1dbjc4n r-y3rmyz\"]>div[class=\"css-76zvg2 r-homxoj r-1i10wst r-1kfrs79\"]"));
                visibblelist(time);
                for (WebElement t : time) {

                    if (t.getText().equals(timeofflight) && i % 2 == 0) {
                        flag = true;
                        actualflighttime=t.getText();
                        c++;
                        break;
                    }
                    i++;
                }
            }

            if (flag) {
                WebElement element1 = element.findElement(By.cssSelector("div[data-testid='spiceflex-flight-select-radio-button-1']>div>svg"));
                clickable(element1, driver);
                scrollIntoView(element1, driver);
                element1.click();
                logger.info("flight is selected");

                if (!element.findElement(By.cssSelector("div[data-testid='spiceflex-flight-select-radio-button-1']>div>svg>g")).isDisplayed()) {
                    Assert.fail("Spice flex button is not selected");
                    logger.error("Spice flex button is not selected");
                }

                break;

            }
        }

        return actualflighttime;
    }

    protected void valuecheck(WebDriver driver, String remainingxpath, String expected) {
        visibble(driver.findElement(By.xpath(check + remainingxpath)));
        Assert.assertEquals(driver.findElement(By.xpath(check + remainingxpath)).getAttribute("value").toLowerCase(), expected.toLowerCase());

    }
    protected void Choose_Your_Seat(WebDriver driver){
        for (WebElement element : add) {
            if (element.getText().contains("Choose Your Seat"))
                element.click();
        }
        visibble(planewin);
        String seat = "3E";
        for (int i = 0; i < seatno.size(); i++) {
            if (seatno.get(i).getText().equals(seat)) {
                String s = seatno.get(i).findElement(By.cssSelector(cssofseat)).getAttribute("fill");
                if (!s.contains("unavailable")) {
                    seatno.get(i).click();
                    break;
                } else {
                    JFrame jFrame = new JFrame();
                    jFrame.setAlwaysOnTop(true);//will show you the dialog box
                    seat = JOptionPane.showInputDialog(jFrame, "seat is unavailable book");
                    System.out.println("seat is already book");
                    i = -1;
                }

            }

        }
    }
    protected void fetchfare(WebElement fetchingfaretoast,WebElement promobox,WebElement promocheck,WebElement terms ,WebDriver driver
    ){
        try {
            if(fetchingfaretoast.isDisplayed()){
                invisible(fetchingfaretoast);
                if (promobox.isDisplayed()) {
                    promocheck.click();
                    clickable(terms, driver);
                    movetoelement(terms, driver);
                    promobox.findElement(By.xpath("(//div[text()='Continue'])[4]")).click();
                }

            }
        }catch (NoSuchElementException e){
            System.out.println(e);

        }


    }
    protected void  meal(WebElement done,WebElement meal,List<WebElement> foodoption,List<WebElement> getFoodoption,WebDriver driver){

        visibble(done);
        clickable(done, driver);
        if (done.getText().contains("Select Meal")) {
            movetoelement(done, driver);

            if (meal.getText().contains("A beverage will be served from the on-board available options.")) {
                if(flag){
                    visibble(retfood);
                    clickable(retfood,driver);
                    retfood.click();}
                visibblelist(foodoption);
                for (WebElement food : foodoption) {
                    if (food.getText().contains("Paneer masala")) {
                        food.click();
                        flag=true;

                    }
                }
                movetoelement(done,driver);
            } else if (meal.getText().contains("You are entitled for an eatable. Please choose your selection from the below options")) {
                if(flag){
                    visibble(retfood);
                    clickable(retfood,driver);
                    retfood.click();}
                visibblelist(getFoodoption);
                for (WebElement food : getFoodoption) {
                    if (food.getText().contains("Vegetable Biryani") || food.getText().contains("Gluten-free dhokla"))
                        food.findElement(By.cssSelector("div[class*=\"r-1g7fiml r-1777fci\"]>div[class*='r-ubezar r-1kfrs79']")).click();
                    flag=true;


                }
                movetoelement(done,driver);
                invisible(done);

            }
            invisible(planewin);
        }else {
        movetoelement(done, driver);
        invisible(done);}
    }

}
