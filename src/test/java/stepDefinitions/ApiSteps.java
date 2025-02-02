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

    @And("Hesapa {string} tutarında {string} tarafından para yüklemesi için istek atılır")
    public void add_account_money(String amount, String sender) {
        int amount_int = Integer.parseInt(amount);
        apiModel.add_amount_account(amount_int, sender);
    }


    @When("Son işlemlerde {string} tutarında {string} tarafından para yatırıldığı doğrulanır")
    public void last_transactions_control(String amount, String sender) {
        apiModel.last_transaction_control(sender,apiModel.bank_account_name,amount);
    }

    @Then("Hesap bakiyesi {string} artığı kontrol edilir")
    public void account_add_amount_check(String add_amount) {
        apiModel.add_amount_check(add_amount);
    }

    @When("Hesap adı {string} ismi ile değiştirilir")
    public void edit_account_name(String account_name) {
        apiModel.edit_account_name(account_name);
    }

    @Then("Yeni hesap adının {string} olduğu doğrulanır")
    public void check_account_name(String account_name) {
        apiModel.check_account_name(account_name);
    }

    @And("{string} Hesabına {string} tutarında para transferi için istek atılır")
    public void money_transfer(String account_name, String amount) {
        String account_id = apiModel.get_transfer_bank_id(account_name);
        apiModel.money_transfer(account_id, amount);
    }

    @When("Son işlemlerde {string} tutarında {string} tarafına transfer doğrulanır")
    public void last_money_transfer_transaction_check(String amount, String receiver) {
        apiModel.last_transaction_control(apiModel.bank_account_name, receiver, amount);
    }


    @Then("Hesap bakiyesi {string} azaldığı kontrol edilir")
    public void degrease_account_money_check(String amount) {
        apiModel.degrease_amount_check(amount);
    }


}
