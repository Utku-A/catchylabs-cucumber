package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class BankPage extends BasePage {
    By open_test_module_button_locator = By.xpath("//div[contains(text(), \"Open Money Transfer\")]");
    By my_account_text_area_locator = By.xpath("//div[contains(text(), \"My account\")]");
    By account_name_text_locator = By.xpath("//div[contains(text(), \"Account name\")]/following-sibling::div[1]");
    By account_amount_text_locator = By.xpath("//div[contains(text(), \"Amount\")]/following-sibling::div/div");

    By last_transactions_sender_text_locator = By.xpath("//div[contains(text(), \"Transactions\")]/following-sibling::div[1]/div/div[1]/div/div/div/div[1]/div[2]");
    By last_transactions_receiver_text_locator = By.xpath("//div[contains(text(), \"Transactions\")]/following-sibling::div[1]/div/div[1]/div/div/div/div[2]/div[2]");
    By last_transactions_amount_text_locator = By.xpath("//div[contains(text(), \"Transactions\")]/following-sibling::div[1]/div/div[1]/div/div/div/div[4]/div[2]/div");

    By transfer_money_button_locator = By.xpath("//div[contains(text(), \"Transfer money\")]");
    By add_money_button_locator = By.xpath("//div[contains(text(), \"Add money\")]");
    By edit_account_locator = By.xpath("//div[contains(text(), \"Edit account\")]");

    By modal_edit_account_input_locator = By.xpath("//div[contains(text(), \"Account name\")]/following-sibling::div/input");
    By modal_update_button_locator = By.xpath("//div[contains(text(), \"UPDATE\")]");

    // Credit card
    By modal_card_number_input_locator = By.xpath("//div[contains(text(), \"Card number\")]/following-sibling::input");
    By modal_card_number_span_locator = By.xpath("//div[contains(text(), \"Card number\")]/following-sibling::div");
    By modal_card_holder_input_locator = By.xpath("//div[contains(text(), \"Card holder\")]/following-sibling::input");
    By modal_card_holder_span_locator = By.xpath("//div[contains(text(), \"Card holder\")]/following-sibling::div");
    By modal_expiry_date_input_locator = By.xpath("//div[contains(text(), \"Expiry date\")]/following-sibling::input");
    By modal_expiry_date_span_locator = By.xpath("//div[contains(text(), \"Expiry date\")]/following-sibling::div");
    By modal_card_cvv_input_locator = By.xpath("//div[contains(text(), \"CVV\") and @dir=\"auto\" ]/following-sibling::input");
    // By modal_card_cvv_input_locator = By.cssSelector(":nth-child(2) >  :nth-child(1) >  [autocapitalize=\"sentences\"] ");

    By modal_card_cvv_span_locator = By.xpath("//div[contains(text(), \"CVV\")]/following-sibling::div");
    By modal_amount_input_locator = By.xpath("//div[contains(text(), \"Amount\")]/following-sibling::input");
    By modal_amount_span_locator = By.xpath("//div[contains(text(), \"Amount\") and @dir=\"auto\"]/following-sibling::div[@dir=\"auto\"]");
    By modal_add_money_close_button_locator = By.xpath("//div[contains(text(), \"Add money\")]/following-sibling::div");

    By modal_add_button_locator = By.xpath("//div[@tabindex=\"0\"][1]/div[contains(text(), \"Add\") and @dir=\"auto\"]");

    public BankPage(WebDriver driver) {
        super(driver);
    }

    public BankPage open_test_module() {
        click(open_test_module_button_locator);
        return this;
    }

    public BankPage display_account() {
        isDisplayed(my_account_text_area_locator);
        return this;
    }

    public BankPage check_last_transactions(Object sender, Object receiver, Object amount) {
        Assert.assertEquals(last_transactions_sender_text_locator,sender);
        Assert.assertEquals(last_transactions_receiver_text_locator, receiver);
        Assert.assertEquals(last_transactions_amount_text_locator,amount);
        return this;
    }

    public BankPage update_account_name(String account_name) {
        click(edit_account_locator);
        pause(4);
        find(modal_edit_account_input_locator).clear();
        type(modal_edit_account_input_locator, account_name);
        click(modal_update_button_locator);
        pause(2);
        return this;
    }

    public BankPage check_account_name(Object update_account_name) {
        Object account_name = find(account_name_text_locator).getText();
        Assert.assertEquals(account_name, update_account_name);
        return this;
    }

    public BankPage add_money(String money) {
        click(add_money_button_locator);
        pause(4);
        slow_type(modal_card_number_input_locator,"1234123412341234");
        control_element_input_value(modal_card_number_input_locator,"1234 1234 1234 1234");

        slow_type(modal_card_holder_input_locator,"Utku Aladag");
        control_element_input_value(modal_card_holder_input_locator,"Utku Aladag");

        slow_type(modal_card_cvv_input_locator,"110");
        control_element_input_value(modal_card_cvv_input_locator,"110");

        type_non_control(modal_expiry_date_input_locator, "1026");
        control_element_input_value(modal_expiry_date_input_locator,"10/26");

        slow_type(modal_amount_input_locator,money);
        control_element_input_value(modal_amount_input_locator,money);

        click(modal_add_button_locator);
        pause(4);
        return this;
    }

    public BankPage card_area_validation_control() {
        click(add_money_button_locator);
        pause(4);
        click(modal_add_button_locator);
        pause(2);

        control_element_text_value(modal_card_number_span_locator,"Required");
        control_element_text_value(modal_card_holder_span_locator,"Required");
        control_element_text_value(modal_expiry_date_span_locator,"Required");
        control_element_text_value(modal_card_cvv_span_locator,"Required");
        control_element_text_value(modal_amount_span_locator,"Required");


        type(modal_card_number_input_locator,"33");
        type(modal_card_holder_input_locator,"f");
        type(modal_expiry_date_input_locator, "1");
        type(modal_card_cvv_input_locator,"1");
        type(modal_amount_input_locator,"d");
        click(modal_add_button_locator);
        pause(2);

        control_element_text_value(modal_card_number_span_locator,"Too Short!");
        control_element_text_value(modal_card_holder_span_locator,"Too Short!");
        control_element_text_value(modal_expiry_date_span_locator,"Wrong date. Please give a correct date");
        control_element_text_value(modal_card_cvv_span_locator,"Too short");
        control_element_text_value(modal_amount_span_locator,"amount must be a `number` type, but the final value was: `NaN` (cast from the value `\"d\"`).");


        slow_type(modal_card_number_input_locator,"33334444222211115555");
        control_element_input_value(modal_card_number_input_locator,"3333 3344 4422 2211 1155 55");

        click(modal_add_button_locator);
        pause(2);
        control_element_text_value(modal_card_number_span_locator,"Too Long!");

        type(modal_card_number_input_locator,"1234 1234 1234 1234");
        type(modal_card_holder_input_locator,"Utku Aladağ");
        type(modal_expiry_date_input_locator, "1026");
        type(modal_card_cvv_input_locator,"110");
        type(modal_amount_input_locator,"400");

        click(modal_add_money_close_button_locator);

        return this;
    }

    public int get_account_amount() {
        String number_str = find(account_amount_text_locator).getText().replace(",", "");
        number_str = number_str.split("\\.")[0];
        return Integer.parseInt(number_str);
    }

    public BankPage check_add_amount(int old_amount, int add_amount) {
        int new_amount = old_amount + add_amount;
        Assert.assertEquals(this.get_account_amount(), new_amount, "Hesap bakiyesi hatalı");
        return this;
    }

}
