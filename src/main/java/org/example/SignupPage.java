package org.example;

import Reuse.Resusable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.Set;

public class SignupPage extends Resusable {
    WebDriver driver;
    String Superxpath = "//input[@id=";
    @FindBy(xpath = "//div[@role=\"dialog\"]//descendant::div[@data-testid=\"app-loader\"]")
    WebElement toast;
    @FindBy(xpath = "//div[@data-testid='app-loader']//img")
    WebElement toast1;
    @FindBy(xpath = "//div[contains(text(),\"Sign\")]")
    WebElement signup;
    @FindBy(xpath = "//label[contains(text() ,\"Title\")]//following-sibling::select")
    WebElement title;
    @FindBy(xpath = "//label[text()='Country/Territory of Residence']//following-sibling::select")
    WebElement con;
    @FindBy(xpath = "//label[text()='Date of Birth']//following-sibling::div//child::a")
    WebElement calender;
    @FindBy(xpath = "//div[@data-provide=\"datepicker\"]//descendant::select[@class='react-datepicker__month-select']")
    WebElement monthdob;
    @FindBy(xpath = "//div[@data-provide=\"datepicker\"]//descendant::select[@class='react-datepicker__year-select']")
    WebElement yeardob;
    @FindBy(xpath = "//input[@type='tel']")
    WebElement tel;
    @FindBy(xpath = "//div[@class=\"react-datepicker__week\"]")
    List<WebElement> elements;
    @FindBy(xpath = "//button[@type='button']")
    WebElement submit;
    @FindBy(xpath = "//label[contains(text(),'OTP Verification')]")
    WebElement verify_window;
    @FindBy(xpath = "//div[@id='otp']/child::input")
    List<WebElement> otpsection;
    @FindBy(xpath = "//div/child::button[contains(text(),'Verify')]")
    WebElement verify_button;
    SignupPage(WebDriver driver) {
        super();
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public void gotoSignuppage_filldetail(String firstName, String lastName, String country, String mobilenumber, String email, String password, String tittle, String month, String year, String date) throws InterruptedException {
        if (toast1.isDisplayed()) {
            invisible(toast1);
        }
        signup.click();
        invisible(toast);
        Set<String> set = driver.getWindowHandles();
        for (String s : set) {
            driver.switchTo().window(s);
            if (driver.getTitle().contains("SpiceClub")) {
                invisible(toast);
                byvisibletext(title, tittle);
                driver.findElement(By.xpath(Superxpath + "'first_name']")).isDisplayed();
                driver.findElement(By.xpath(Superxpath + "'first_name']")).sendKeys(firstName);
                textpresent(driver.findElement(By.xpath(Superxpath + "'first_name']")), firstName);
                driver.findElement(By.xpath(Superxpath + "'last_name']")).isDisplayed();
                driver.findElement(By.xpath(Superxpath + "'last_name']")).sendKeys(lastName);
                textpresent(driver.findElement(By.xpath(Superxpath + "'last_name']")), lastName);
                byvisibletext(con, country);
                calender.click();
                byvisibletext(monthdob, month);
                byvisibletext(yeardob, year);
//                datepicker.findElement(By.xpath("//div[text()='"+date+"']")).click();
                for (int i = 0; i < elements.size(); i++) {
                    List<WebElement> dates = elements.get(i).findElements(By.tagName("div"));
                    for (int j = 0; j < dates.size(); j++) {
                        String b = dates.get(j).getAttribute("aria-disabled");
                        if (b.equalsIgnoreCase("true")) {
                            System.out.println("date is not enabled");
                            Assert.assertEquals(b, "false");
                        }
                        if (i == 0 && (Integer.parseInt(date) >= 7)) {
                            break;

                        } else if (dates.get(j).getText().equals(date)) {
                            dates.get(j).click();
                            break;
                        }
                    }
                }
                tel.sendKeys(mobilenumber);
                driver.findElement(By.xpath(Superxpath + "'email_id']")).sendKeys(email);
                if (toast.isDisplayed()) {
                    invisible(toast);
                    driver.findElement(By.xpath(Superxpath + "'email_id']")).sendKeys(email);


                }
                textpresent(driver.findElement(By.xpath(Superxpath + "'email_id']")), email);
                driver.findElement(By.xpath(Superxpath + "'new-password']")).sendKeys(password);
                if (toast.isDisplayed()) {
                    invisible(toast);
                    driver.findElement(By.xpath(Superxpath + "'new-password']")).sendKeys(password);
                }
                textpresent(driver.findElement(By.xpath(Superxpath + "'new-password']")), password);
                driver.findElement(By.xpath(Superxpath + "'c-password']")).sendKeys(password);
                textpresent(driver.findElement(By.xpath(Superxpath + "'c-password']")), password);
                Actions action = new Actions(driver);
                WebElement check = driver.findElement(By.xpath(Superxpath + "'defaultCheck1']"));
                action.moveToElement(check).click().build().perform();
                action.moveToElement(submit).click().build().perform();
            }
        }
    }

    void otp_Verfication() throws InterruptedException {
        visibble(verify_window);
        int c = 0;
        System.out.println("pause a program and apply otp");
        for (WebElement element : otpsection) {
//temporary method because we dont't have otp now
            for (int j = 0; j < 10; j++) {
                if (element.getAttribute("value").equals(String.valueOf(j))) {
                    c++;
                    break;
                }
            }
        }
        if (c == otpsection.size()) {
            verify_button.click();
        } else {
            System.out.println("incorrect otp");
            Assert.fail();
        }
    }

}
