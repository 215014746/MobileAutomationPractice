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

public class DashboardPage {

    AppiumDriver driver;
    Properties config;
    WebDriverWait wait;

    public DashboardPage(AppiumDriver driver, Properties config){
        this.driver = driver;
        this.config = config;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    // Top Right Button (likely burger/profile/menu based on position)
    private By welcomeMessageNative = By.xpath("//android.view.View[contains(@content-desc,'Welcome back')]");
    //private By welcomeMessageWeb = By.xpath("//p[contains(text(),'Welcome back')]");
    private By welcomeMessageWeb = By.xpath("//h2[contains(text(),'Welcome back')]");

    private By topRightButtonNative = By.xpath("//android.widget.Button[@bounds='[901,105][1028,231]']");
    //private By topRightButtonWeb = By.xpath("//*[@id=\"app-root\"]/nav/div/button[2]");
    private By topRightButtonWeb = By.xpath("//button[contains(@class,'nav-burger')]");

    private By adminPanelButtonNative = By.xpath("//android.widget.Button[@content-desc='Admin Panel']");
    private By adminPanelButtonWeb = By.xpath("(//span[contains(text(),'Admin Panel')])[2]");

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

    public void verifyWelcomeText(){
        getElement(welcomeMessageNative, welcomeMessageWeb).isDisplayed();
    }
    /** Clicks the top-right menu button (burger/profile/menu) */
    public void clickTopRightButton() {
        getElement(topRightButtonNative, topRightButtonWeb).click();
    }

    /** Clicks the Admin Panel button from the dashboard menu */
    public void clickAdminPanel() {
        getElement(adminPanelButtonNative, adminPanelButtonWeb).click();
    }

    /** Combines opening the menu and clicking Admin Panel */
    public void navigateToAdminPanel() {
        verifyWelcomeText();
        clickTopRightButton();
        clickAdminPanel();
    }
}

