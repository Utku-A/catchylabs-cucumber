package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    // Locators
    By usernameInput = By.cssSelector("[placeholder=\"Username\"]");
    By passwordInput = By.cssSelector("[placeholder=\"Password\"]");
    By submitButton = By.xpath("//div[contains(text(), 'Login')]");
    By errorMessage = By.xpath("//div[contains(text(), 'Username or Password Invalid!')]");
    By logout_button_locator = By.xpath("//div[contains(text(), \"Logout\")]");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage goToWebsite(String url) {
        driver.get(url);
        return this;
    }

    public void login(String username, String password) {
        click(submitButton);
        isDisplayed(errorMessage);
        pause(4);
        type(usernameInput, username);
        type(passwordInput, password);
        click(submitButton);
    }

    public void incorrect_login_control() {
        click(submitButton);
        isDisplayed(errorMessage);

        type(usernameInput, "test");
        click(submitButton);
        isDisplayed(errorMessage);

        type(usernameInput, "test");
        type(passwordInput, "test");
        click(submitButton);
        isDisplayed(errorMessage);
    }

    public void check_login_user() {
        isDisplayed(logout_button_locator);
    }

}