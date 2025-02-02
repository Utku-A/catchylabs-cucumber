package stepDefinitions;

import api.ApiModel;
import io.cucumber.java.en.*;

public class ApiSteps {
    ApiModel apiModel = new ApiModel();


    @Given("Login isteği atılır ve token bilgisi kaydedilir")
    public void login_request() {
        apiModel.login("halisutku.aladag", "test123321");
    }

    @When("Session isteği atılır ve kullanıcının aktif olduğu doğrulanır")
    public void session_request() {
        apiModel.check_session();
    }

    @And("Account isteği ile bakiye ve diğer bilgiler kaydedilir")
    @Then("Account isteğinde başarılı sonuç geldiği kontrol edilir")
    public void accounts_request() {
        apiModel.get_accounts();
    }

    @And(" Hesapa {string} tutarında para yüklemesi için istek atılır")
    public void add_account_money(String amount) {

    }


}
