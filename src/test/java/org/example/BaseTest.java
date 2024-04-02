package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
   public WebDriver driver;
   String url="https://www.spicejet.com/";
   WebDriver driverInitialize() throws IOException {
       Properties properties=new Properties();
       FileInputStream fileInputStream=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/BrowserProperties/Browser.properties");
       properties.load(fileInputStream);
       if (properties.getProperty("Browser").equalsIgnoreCase("chrome")){
           ChromeOptions chromeOptions=new ChromeOptions();
           chromeOptions.addArguments("disable-notifications");
           WebDriverManager.chromedriver().setup();
           driver=new ChromeDriver(chromeOptions);
       }else if(properties.getProperty("Browser").equalsIgnoreCase("firefox")){
           WebDriverManager.firefoxdriver().setup();
           driver=new FirefoxDriver();
       } else if (properties.getProperty("Browser").equalsIgnoreCase("edge")) {
           WebDriverManager.edgedriver().setup();
           driver=new EdgeDriver();
       }else
           System.out.println("driver config is not setup");
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
       driver.manage().window().maximize();
       return driver;
   }
   @BeforeTest(alwaysRun = true)
   void setupBrowser() throws IOException {

       driver=driverInitialize();
       driver.get(url);

   }
  @AfterTest
    void close(){
//       driver.quit();
    }
}
