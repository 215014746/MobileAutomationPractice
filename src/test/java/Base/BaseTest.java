package Base;

import Pages.AdminPanelPage;
import Pages.DashboardPage;
import Pages.LoginPage;
import Utils.DriverFactory;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    protected AppiumDriver driver;
    protected Properties config;
    protected LoginPage loginPage;
    protected DashboardPage dashboardPage;
    protected AdminPanelPage adminPanelPage;


    @BeforeClass
    public void setUp() throws IOException {
        config = new Properties();
        FileInputStream fileInputStream = new FileInputStream(
                System.getProperty("user.dir") + "/src/test/resources/configs/config.properties"
        );
        config.load(fileInputStream);

        // Initialise Appium driver
        DriverFactory.initialiseDriver(config);
        driver = DriverFactory.getDriver();

        // Initialize Page Objects
        loginPage = new LoginPage(driver, config);
        dashboardPage = new DashboardPage(driver, config);
        adminPanelPage = new AdminPanelPage(driver, config);
    }

    @AfterClass
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
