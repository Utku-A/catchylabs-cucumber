package runners;



import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepDefinitions",
        tags = "@web",
        // plugin = {"pretty", "html:target/cucumber-api-report.html"},
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json"
        }
)
public class WebRunner extends AbstractTestNGCucumberTests {

}

