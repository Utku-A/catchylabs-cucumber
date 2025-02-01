package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import utils.DriverFactory;

public class LoginSteps {
    LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

    @Given("Catchylabs web adresi açılır")
    public void visit_website() {
        loginPage.goToWebsite("https://catchylabs-webclient.testinium.com/signIn");
        loginPage.pause(4);
    }

    @And("Hatalı kullanıcı bilgiler ile login olmadığı kontrol edilir")
    public void incorrect_login() {
        loginPage.incorrect_login_control();
    }

    @When("Kullanıcı girişi yapılır")
    public void login_user() {
        loginPage.login("halisutku.aladag", "7RiMx3mD");
    }

    @Then("Başarılı giriş yapıldığı doğrulanır.")
    public void check_login() {
        loginPage.check_login_user();
    }


}