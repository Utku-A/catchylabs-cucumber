package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Objects;

public class DriverFactory {

    public static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            setDriver("chrome", "desktop");
        }
        return driver;
    }

    public static void setDriver(String browser, String device) {
        switch(browser.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("test-type");
                options.addArguments("disable-popup-blocking");
                options.addArguments("--disable-web-security");

                if (device.equals("mobile")) {
                    options.addArguments("--window-size=375,812");
                }

                driver = new ChromeDriver(options);
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Invalid browser: " + browser);
        }

        if (Objects.equals(device, "desktop")) {
            driver.manage().window().maximize();
        }
    }

    public static void quitDriver() {
        if(driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
