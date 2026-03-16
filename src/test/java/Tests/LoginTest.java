package Tests;

import Base.BaseTest;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test()
    public void setUp(){
        setUp();
    }

    @Test(dependsOnMethods = "setUp")
    public void loginCredentialsTest() {
        loginPage.clickBurgerMenu();
        loginPage.clickSignInButton();
        loginPage.enterEmail(config.getProperty("email"));
        loginPage.enterPassword(config.getProperty("password"));
        loginPage.clickLoginButton();
    }

    @Test(dependsOnMethods = "loginCredentialsTest")
     public void tearDown() {
       super.tearDown();
    }
}
