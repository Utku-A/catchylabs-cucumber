package stepDefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.BankPage;
import pages.BasePage;
import utils.DriverFactory;

public class BankSteps {

    private final BankPage bankPage;
    int account_amount;

    public BankSteps() {
        this.bankPage = new BankPage(DriverFactory.getDriver());
    }

    @And("Test modülü acılır")
    public void open_test_module() {
        bankPage.open_test_module();
    }

    @Then("Hesap bilgileri doğrulanır")
    public void account_page_control() {
        bankPage.display_account();
    }

    @When("Hesap adı değiştirme {string}")
    public void update_account_name(String account_name) {
        bankPage.update_account_name(account_name);
    }

    @Then("Hesap adı doğrulama {string}")
    public void check_account_name(String account_name) {
         bankPage.check_account_name(account_name);
    }

    @When("Para yatırma gerçekleştirilir {string}")
    public void add_account_money(String amount) {
        this.account_amount = bankPage.get_account_amount();
        bankPage.add_money(amount);
    }

    @Then("Hesap bakiyesi {string} artırğı doğrulanır")
    public void check_account_money(String amount) {
        int int_amount = Integer.parseInt(amount);
        bankPage.check_add_amount(this.account_amount,int_amount);
    }

    @Then("Para yatırma validasyon alanları kontrol edilir")
    public void card_payment_area_control() {
        bankPage.card_area_validation_control();
    }


    @And("Para transferi {string} hesabı için {string} tutarında yapılır")
    public void transfer_money(String account_name, String amount) {
        bankPage.transfer_money("",account_name,amount);
    }

    @When("Son işlemlerde para transferi {string} hesabı için {string} tutarında doğrulanır")
    public void last_transaction_check(String receiver_account, String amount) {
        String account_name = bankPage.get_account_name();
        bankPage.check_last_transactions(account_name,receiver_account, amount);
    }

    @Then("Hesap bakiyesi {string} azaldığı doğrulanır")
    public void account_money_decrease_check(String amount) {
        int amount_int = Integer.parseInt(amount);
        bankPage.check_degrease_amount(this.account_amount, amount_int);
    }

    @And("Mevcut hesap bakiyesi kaydedilir")
    public void save_account_money() {
        this.account_amount = bankPage.get_account_amount();
    }


}
