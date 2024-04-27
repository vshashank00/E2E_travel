package org.example;

import Reuse.Resusable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.swing.*;
import java.util.List;

public class Addon extends Resusable {
    WebDriver driver;
    Addon(WebDriver driver){
       this.driver=driver;
        PageFactory.initElements(driver,this);

    }
    String cssofseat="svg[data-testid='svg-img']>g>g>path:nth-child(1)";

    @FindBy(css = "div[class*='r-lrvibr']>svg[viewBox=\"0 0 12 12\"]>g[fill-rule=\"evenodd\"]>path[fill=\"#000\"]:nth-child(2)")
    WebElement cross;
    @FindBy(css = "div[id='at_addon_modal']")
    WebElement lowpricesbox;
    @FindBy(id = "at_addon_close_icon")
    WebElement lowpricecros;
    @FindBy(css = "div[data-testid=\"expandableList-header\"]")
   List <WebElement> add;
    @FindBy(xpath="//div[text()='Done']")
    WebElement done;
    @FindBy(css ="div[class='css-1dbjc4n r-14lw9ot r-13awgt0 r-18u37iz r-1fviwye']")
    WebElement planewin;
    @FindBy(xpath = "//div[@data-testid=\"seat-map-columns\"]/following-sibling::div/descendant::div[@class=\"css-1dbjc4n r-13ubf1n r-1mnahxq\"]")
    List<WebElement>seatno;
    void addonpage(){
        visibble(cross);
        cross.click();
        for(WebElement element:add){
            if(element.getText().contains("Choose Your Seat"))
                element.click();
        }
        String seat="3F";
        for(int i=0;i<seatno.size();i++){
            if(seatno.get(i).getText().equals(seat)){
                String s=seatno.get(i).findElement(By.cssSelector(cssofseat)).getAttribute("fill");
                if (!s.contains("unavailable")){
                    seatno.get(i).click();
                    break;
                }else {
                    JFrame jFrame=new JFrame();
        jFrame.setAlwaysOnTop(true);//will show you the dialog box
        seat= JOptionPane.showInputDialog(jFrame,"seat is unavailable book");
                    System.out.println("seat is already book");
                   i=-1;
                }

            }

        }
        visibble(planewin);
        visibble(done);
        clickable(done);
        movetoelement(done,driver);
        visibble(lowpricesbox);
        lowpricecros.click();
    }
}
