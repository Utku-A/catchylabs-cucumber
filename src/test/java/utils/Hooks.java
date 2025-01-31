package utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before(order = 0)
    public void setup(Scenario scenario) {
        String browser = System.getProperty("browser", "chrome");
        DriverFactory.setDriver(browser);
    }

    @After
    public void tearDown(Scenario scenario) throws InterruptedException {
        if(scenario.isFailed()) {
            System.out.println();
        }
        DriverFactory.quitDriver();
    }

}
