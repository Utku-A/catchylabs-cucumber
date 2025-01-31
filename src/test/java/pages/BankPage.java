package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BankPage extends BasePage {

    By open_test_module_button_locator = By.xpath("//div[contains(text(), \"Open Money Transfer\")]");

    public BankPage(WebDriver driver) {
        super(driver);
    }

    public BankPage open_test_module() {
        click(open_test_module_button_locator);
        return this;
    }




}
