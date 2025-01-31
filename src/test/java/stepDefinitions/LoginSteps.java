package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import utils.DriverFactory;

public class LoginSteps {
    LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

    @Given("Catchylabs web adresi açılır")
    public void openWebsite() {
        DriverFactory.getDriver().get("https://catchylabs-webclient.testinium.com/signIn");
        loginPage.pause(4);
    }

    @When("Hatalı kullanıcı bilgiler ile login olmadığı kontrol edilir")
    public void checkInvalidLogin() {
        loginPage.checkLoginWithInvalidCredentials();
    }

    @Then("Başarılı kullanıcı girişi yapılır")
    public void performLogin() {
        loginPage.login("halisutku.aladag", "7RiMx3mD");
    }

}