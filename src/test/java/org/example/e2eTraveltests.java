package org.example;

import Data.Datatofeedon;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

public class e2eTraveltests extends BaseTest{
    static Book_flight bookFlight;
@Test(dataProvider = "data",dataProviderClass = Datatofeedon.class)
    void signup(HashMap<String,String>hm) throws  InterruptedException {
    SignupPage signupPage=new SignupPage(driver);
    signupPage.gotoSignuppage_filldetail(hm.get("FirstName"),hm.get("LastName"),hm.get("Country"),hm.get("Mobilenumber"),hm.get("Email"),hm.get("Password"),hm.get("tittle"),hm.get("Month"),hm.get("year"),hm.get("Date"));
    signupPage.otp_Verfication();

}
@Test(dataProvider = "logindata",dataProviderClass = Datatofeedon.class)
    void loginandSearch(HashMap<String,String>hm) throws InterruptedException {
    Login login=new Login(driver);
    login.UserLogin(hm.get("id"), hm.get("password"),hm.get("name"));
    Search_Flight searchFlight=new Search_Flight(driver);
     bookFlight=searchFlight.search_Filghts(hm.get("source"),hm.get("sourceairport"),hm.get("destairport"),hm.get("destination"),hm.get("travelDate"),hm.get("travelYear"),hm.get("travelMonth"),hm.get("returnDate"),hm.get("returnYear"),hm.get("returnMonth"),hm.get("name"));
    Bookingpage bookingpage=bookFlight.selectflight(hm.get("time_of_departure_flight"),hm.get("time_of_return_flight"));
    Addon addon=bookingpage.setCheck(hm.get("name"),hm.get("id"),hm.get("Country"),hm.get("lastname"),hm.get("Number"),hm.get("City"));
    addon.addonpage();
}
@Test(dataProvider = "timeofflight",dataProviderClass = Datatofeedon.class,dependsOnMethods = "loginandSearch" ,enabled = false)
    void Selectflight(HashMap<String,String>hashMap) throws InterruptedException {
    bookFlight.selectflight(hashMap.get("time_of_departure_flight"),hashMap.get("time_of_return_flight"));


    }
}

