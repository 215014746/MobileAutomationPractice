package Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;

public class AdminPanelPage {

    AppiumDriver driver;
    Properties config;
    WebDriverWait wait;

    public AdminPanelPage(AppiumDriver driver, Properties config){
        this.driver = driver;
        this.config = config;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    private By topLeftButtonNative = By.xpath("(//android.view.View[@clickable='true'])[1]");
    private By topLeftButtonWeb = By.xpath("//button[contains(@class,'admin-burger-btn')]");

    private By adminPanelLabelNative = By.xpath("//android.view.View[@content-desc='Admin Panel']");
    private By adminPanelLabelWeb = By.xpath("//h2[contains(text(),'Admin Panel')]");

    private By coursesBtnNative = By.xpath("//android.widget.Button[@content-desc='Courses']");
    private By coursesBtnWeb = By.xpath("//button[contains(text(),'Courses')]");

    private By manageCoursesTitleNative = By.xpath("//android.view.View[@content-desc='Manage Courses']");
    private By manageCoursesTitleWeb = By.xpath("//button[contains(text(),'Manage Courses')]");

    private By courseManagementtitleNative = By.xpath("//android.view.View[@content-desc='Manage Courses']");
    private By courseManagementtitleWeb = By.xpath("//h1[contains(text(),'Course Management')]");

    private By createNewCourseBtnNative = By.xpath("//android.widget.Button[@content-desc='+ Create New Course']");
    private By createNewCourseBtnWeb = By.xpath("//button[contains(text(),'Add Course')]");

    private By createCourseTitleNative = By.xpath("//android.view.View[@content-desc='Create New Course']");
    private By createCourseTitleWeb = By.xpath("//h2[contains(text(),'Create New Course')]");

    private By courseTitleFieldNative = By.xpath("//android.widget.EditText[@hint='Course Title *']");
    private By courseTitleFieldWeb = By.xpath("//input[@placeholder='Course Title']");

    private By descriptionFieldNative = By.xpath("//android.widget.EditText[@hint='Description *']");
    private By descriptionFieldWeb = By.xpath("//textarea[@placeholder='Description']");

    private By durationFieldNative = By.xpath("//android.widget.EditText[@hint='Duration']");
    private By durationFieldWeb = By.xpath("//input[@placeholder='Duration']");

    private By priceFieldNative = By.xpath("//android.widget.EditText[@hint='Price (R)']");
    private By priceFieldWeb = By.xpath("//input[contains(@placeholder,'Price')]");

    private By levelDropdownNative = By.xpath("//android.widget.Button[contains(@content-desc,'Level')]");
    private By levelDropdownWeb = By.xpath("//select[contains(@name,'level')]");

    private By publishedCheckboxNative = By.xpath("//android.widget.CheckBox");
    private By publishedCheckboxWeb = By.xpath("//input[@type='checkbox']");

    private By createCourseBtnNative = By.xpath("//android.widget.Button[@content-desc='Create Course']");
    private By createCourseBtnWeb = By.xpath("//button[contains(text(),'Create Course')]");

    private By courseCreatedToastNative = By.xpath("//android.view.View[@content-desc='Course created.']");
    private By courseCreatedToastWeb = By.xpath("//div[contains(text(),'Course created')]");

    private WebElement getElement(By nativeLocator, By webLocator) {
        String execType = config.getProperty("executionType").trim();
        if (execType.equalsIgnoreCase("nativeApp")) {
            return wait.until(ExpectedConditions.elementToBeClickable(nativeLocator));
        } else if (execType.equalsIgnoreCase("mobileWeb")) {
            return wait.until(ExpectedConditions.elementToBeClickable(webLocator));
        } else {
            throw new RuntimeException("Unsupported executionType: " + execType);
        }
    }

    public void clickTopLeftButton() {
        getElement(topLeftButtonNative, topLeftButtonWeb).click();
    }

    public void verifyAdminPanelTitle() {
        getElement(adminPanelLabelNative, adminPanelLabelWeb).isDisplayed();
    }

    public void clickCourses() {
        getElement(coursesBtnNative, coursesBtnWeb).click();
    }


    public boolean isManageCoursesPageDisplayed() {
        return getElement(courseManagementtitleNative, courseManagementtitleWeb).isDisplayed();
    }

    // ===== CREATE COURSE FLOW =====
    public void clickCreateNewCourse() {
        getElement(createNewCourseBtnNative, createNewCourseBtnWeb).click();
    }

    public boolean isCreateCoursePageDisplayed() {
        return getElement(createCourseTitleNative, createCourseTitleWeb).isDisplayed();
    }

    public void enterCourseTitle(String title) {
        WebElement element = getElement(courseTitleFieldNative, courseTitleFieldWeb);
        element.clear();
        element.sendKeys(title);
    }

    public void enterDescription(String description) {
        WebElement element = getElement(descriptionFieldNative, descriptionFieldWeb);
        element.clear();
        element.sendKeys(description);
    }

    public void enterDuration(String duration) {
        WebElement element = getElement(durationFieldNative, durationFieldWeb);
        element.clear();
        element.sendKeys(duration);
    }

    public void enterPrice(String price) {
        WebElement element = getElement(priceFieldNative, priceFieldWeb);
        element.clear();
        element.sendKeys(price);
    }

    private By levelOptionNative(String level) {
        return By.xpath("//*[@text='" + level + "' or @content-desc='" + level + "']");
    }

    private By levelOptionWeb(String level) {
        return By.xpath("//option[normalize-space()='" + level + "'] | //li[normalize-space()='" + level + "']");
    }

    public void selectLevel(String level) {
        getElement(levelDropdownNative, levelDropdownWeb).click();
        getElement(levelOptionNative(level), levelOptionWeb(level)).click();
    }
   /* public void clickLevelDropdown() {
        getElement(levelDropdownNative, levelDropdownWeb).click();
    }

    public void togglePublishedCheckbox() {
        getElement(publishedCheckboxNative, publishedCheckboxWeb).click();
    }*/

    public void ensurePublishedCheckboxChecked() {
        WebElement checkbox = getElement(publishedCheckboxNative, publishedCheckboxWeb);

        // For native Android, the "checked" attribute indicates the state
        String checkedAttribute = checkbox.getAttribute("checked"); // returns "true" or "false"

        if (!Boolean.parseBoolean(checkedAttribute)) {
            checkbox.click(); // only click if not already checked
        }
    }

    public void clickCreateCourse() {
        getElement(createCourseBtnNative, createCourseBtnWeb).click();
    }

    // ===== COMPLETE FLOW METHOD (VERY USEFUL) =====
    public void createCourse(String title, String description, String duration, String price) {
        clickCreateNewCourse();
        enterCourseTitle(title);
        enterDescription(description);
        enterDuration(duration);
        enterPrice(price);
        clickCreateCourse();
    }

    // ===== VALIDATION =====
    public boolean isCourseCreatedToastDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(courseCreatedToastNative)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
