package Base;

import Utils.DriverFactory;
import io.appium.java_client.AppiumDriver;

import java.io.FileInputStream;
import java.util.Properties;

public class BaseTest {

    protected AppiumDriver driver;
    protected Properties config;

    public void setUp() throws Exception {
    config = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/configs/config.properties");
        config.load(fileInputStream);

        DriverFactory.initialiseDriver(config);
        driver = DriverFactory.getDriver();
     }

    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
