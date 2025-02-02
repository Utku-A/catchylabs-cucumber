package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.DriverFactory;

public class Hooks {

    @Before("@web")
    public void setup_desktop(Scenario scenario) {
        String browser = System.getProperty("browser", "chrome");
        String device = System.getProperty("device", "desktop");
        DriverFactory.setDriver(browser, device);
    }

    @Before("@mobile")
    public void setup_mobile(Scenario scenario) {
        String browser = System.getProperty("browser", "chrome");
        String device = System.getProperty("device", "mobile");
        DriverFactory.setDriver(browser, device);
    }

    @After("@web or @mobile")
    public void tearDown (Scenario scenario) {
        if(scenario.isFailed()) {
            System.out.println();
        }
        DriverFactory.quitDriver();
    }

}
