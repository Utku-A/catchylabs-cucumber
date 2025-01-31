package runners;


import io.cucumber.junit.platform.engine.Cucumber;

@Cucumber
@org.junit.platform.suite.api.ConfigurationParameter(
        key = "cucumber.junit-platform.naming-strategy",
        value = "long"
)
@org.junit.platform.suite.api.SelectClasspathResource("features")
@org.junit.platform.suite.api.IncludeEngines("cucumber")
public class TestRunner {

}
