package Tests;

import Base.BaseTest;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    //Log in page  - enter email, password, click login button
    @Test()
    public void loginCredentialsTest() {
        loginPage.clickBurgerMenu();
        loginPage.clickSignInButton();
        loginPage.verifyLoginPageTitle();
        loginPage.enterEmail(config.getProperty("email"));
        loginPage.enterPassword(config.getProperty("password"));
        loginPage.clickLoginButton();
    }

    //Dashboard page - verify welcome text, click on burger menu, click on admin panel button
    @Test(dependsOnMethods = "loginCredentialsTest")
    public void verifyDashboardPage(){
        dashboardPage.verifyWelcomeText();
    }

    @Test(dependsOnMethods = "verifyDashboardPage")
    public void clickOnBurgerMenu(){
        dashboardPage.clickTopRightButton();
    }

    @Test(dependsOnMethods = "clickOnBurgerMenu")
    public void clickOnAdminPanelButton(){
        dashboardPage.clickAdminPanel();
    }

    //Admin panel page - verify admin panel page, click on courses button, verify manage courses page, click on create new course button, verify create course page, enter course details, click create course button, verify course created toast message

    @Test(dependsOnMethods = "clickOnAdminPanelButton")
    public void clickOnAdminBurgerMenu(){
        adminPanelPage.clickTopLeftButton();
    }

    @Test(dependsOnMethods = "clickOnAdminBurgerMenu")
    public void verifyAdminPanelPage(){
        adminPanelPage.verifyAdminPanelTitle();
    }

    @Test(dependsOnMethods = "verifyAdminPanelPage")
    public void clickOnCoursesButton(){
        adminPanelPage.clickCourses();
    }

    @Test(dependsOnMethods = "clickOnCoursesButton")
    public void verifyManageCoursesPage(){
        adminPanelPage.isManageCoursesPageDisplayed();
    }

    @Test(dependsOnMethods = "verifyManageCoursesPage")
    public void clickOnCreateNewCourseButton(){
        adminPanelPage.clickCreateNewCourse();
    }

    @Test(dependsOnMethods = "clickOnCreateNewCourseButton")
    public void verifyCreateCoursePage(){
        adminPanelPage.isCreateCoursePageDisplayed();
    }

    @Test(dependsOnMethods = "verifyCreateCoursePage")
    public void enterCourseDetails(){
        adminPanelPage.enterCourseTitle("Test Course");
        adminPanelPage.enterDescription("This is a test course created for automation testing purposes.");
        adminPanelPage.enterDuration("4 weeks");
        adminPanelPage.selectLevel("Intermediate");
        adminPanelPage.enterPrice("900");
        adminPanelPage.ensurePublishedCheckboxChecked();
    }
     @Test(dependsOnMethods = "enterCourseDetails")
    public void clickCreateCourseButton(){
        adminPanelPage.clickCreateCourse();
    }

    @Test(dependsOnMethods = "clickCreateCourseButton")
    public void verifyCourseCreated(){
        adminPanelPage.isCourseCreatedToastDisplayed();
    }

    @Test(dependsOnMethods = "verifyCourseCreated")
    public void tearDown() {
        super.tearDown();
    }


}
