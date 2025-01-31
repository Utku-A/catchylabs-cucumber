package stepDefinitions;

import io.cucumber.java.en.*;
import pages.BankPage;
import utils.DriverFactory;

public class BankSteps {

    private final BankPage bankPage;

    public BankSteps() {
        this.bankPage = new BankPage(DriverFactory.getDriver());
    }

    @And("Test modülü acılır")
    public void open_test_module() {
        bankPage.open_test_module();
    }

}
