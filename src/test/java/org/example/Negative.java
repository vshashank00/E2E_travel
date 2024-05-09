package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Negative extends BaseTest {
    Logger logger= LogManager.getLogger(Negative.class);
    @Test
    void Falselogin(){

    Login login=new Login(driver);
    login.UserLogin("vshashank00@gmail.com","shashank","Shashank");
    logger.info("negative");

    }
}
