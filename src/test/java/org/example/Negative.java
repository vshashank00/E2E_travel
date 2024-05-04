package org.example;

import org.testng.annotations.Test;

public class Negative extends BaseTest {
    @Test
    void logib(){
    Login login=new Login(driver);
    login.UserLogin("vshashank00@gmail.com","shashank","Shashank");
    }
}
