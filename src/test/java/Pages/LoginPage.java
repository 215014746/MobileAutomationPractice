package Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;

public class LoginPage {
    AppiumDriver driver;
    Properties config;
    WebDriverWait webDriverWait;

    public LoginPage(AppiumDriver driver, Properties config) {
        this.driver = driver;
        this.config = config;
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private By burgerMenuButtonNative = By.xpath("android.widget.Button");
    private By burgerMenuButtonWeb = By.xpath("//*[@id='app-root']/nav/div[1]/button");

    private By signInButtonNative = By.xpath("android.widget.Button[@content-desc='Login / Sign Up']");
    private By signInButtonWeb = By.xpath("//*[@id='app-root']/nav/div[2]/div[5]/button/span[2]");

    // Login Page Title
    private By loginTitleNative = By.xpath("//android.view.View[@content-desc='Login to Access Learning Materials']");
    private By loginTitleWeb = By.xpath("//h2[@id = \"login-heading\"]");


    private By emailFieldNative = By.xpath("//android.widget.EditText[@hint='Email']");
    private By emailFieldWeb = By.id("login-email");

    private By passwordFieldNative = By.xpath("//android.widget.EditText[@hint='Password']");
    private By passwordFieldWeb = By.id("login-password");

    private By loginButtonNative = By.xpath("//android.widget.Button[@content-desc='Login']");
    //private By loginButtonWeb = By.id("Login-submit");
    private By loginButtonWeb = By.xpath("//button[contains(text(),'Login')]");

    private WebElement getElement(By nativeLocator, By webLocator) {
        String execType = config.getProperty("executionType").trim();
        if (execType.equalsIgnoreCase("nativeApp")) {
            return webDriverWait.until(ExpectedConditions.elementToBeClickable(nativeLocator));
        } else if (execType.equalsIgnoreCase("mobileWeb")) {
            return webDriverWait.until(ExpectedConditions.elementToBeClickable(webLocator));
        } else {
            throw new RuntimeException("Unsupported execution Type" + execType);
        }

    }

    public void verifyLoginPageTitle(){
        WebElement titleElement = getElement(loginTitleNative, loginTitleWeb);
        if (titleElement.isDisplayed()) {
            System.out.println("Login page title is displayed: " + titleElement.getText());
        } else {
            throw new RuntimeException("Login page title is not displayed");
        }
    }

    public void clickBurgerMenu() {

        getElement(burgerMenuButtonNative, burgerMenuButtonWeb).click();
    }

    public void clickSignInButton() {

        getElement(signInButtonNative, signInButtonWeb).click();
    }

    public void enterEmail(String email) {
        WebElement emailField = getElement(emailFieldNative, emailFieldWeb);
        emailField.click();
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordField = getElement(passwordFieldNative, passwordFieldWeb);
        passwordField.click();
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {

        getElement(loginButtonNative, loginButtonWeb).click();
    }


}
