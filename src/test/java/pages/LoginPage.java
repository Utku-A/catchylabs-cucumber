package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    // Locators
    private final By usernameInput = By.cssSelector("[placeholder=\"Username\"]");
    private final By passwordInput = By.cssSelector("[placeholder=\"Password\"]");
    private final By submitButton = By.xpath("//div[contains(text(), 'Login')]");
    private final By errorMessage = By.xpath("//div[contains(text(), 'Username or Password Invalid!')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        click(submitButton);
        isDisplayed(errorMessage);
        pause(4);
        type(usernameInput, username);
        type(passwordInput, password);
        click(submitButton);
    }

    public void checkLoginWithInvalidCredentials() {
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

}