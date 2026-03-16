package Utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.net.MalformedURLException;

import java.net.URI;
import java.util.Properties;
public class DriverFactory  {
    static AppiumDriver driver; //static because we run only one driver instance at a time to avoid conflicts

    public static void initialiseDriver(Properties config) throws  MalformedURLException {
        if (driver != null) return;

        //The type of execution you want to run: mobileWeb or NativeApp
       String execType = config.getProperty("executionType").trim();
        UiAutomator2Options options = new UiAutomator2Options()
                .setAutomationName(config.getProperty("automationName"))
                .setPlatformName(config.getProperty("platformName"));

//if execution type is native app, we need to set the browser name capability to null and set the app capability
        if (execType.equalsIgnoreCase("mobileWeb")) {
            //setting the browser to launch
            options.withBrowserName(config.getProperty("browserName"));

        }//if execution type is native app, we will install and launch the app on the device or emulator
        else if (execType.equalsIgnoreCase("nativeApp")) {
            //we set the app capability
            String appPath = System.getProperty("user.dir") + config.getProperty("appPath");
            options.setApp(appPath);
        }
        driver = new AppiumDriver(
                URI.create(config.getProperty("appiumServer")).toURL(),
                options);

        if (execType.equalsIgnoreCase("mobileWeb")) {
            String webURL = config.getProperty("webURL");
            driver.get(webURL);
        }

    }

    public static AppiumDriver getDriver() {
            return driver;
        }

    public static void quitDriver(){
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
 }

