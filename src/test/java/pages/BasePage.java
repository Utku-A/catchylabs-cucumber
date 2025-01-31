package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected WebElement find(By element) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public void click(By element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void type(By element, String input) {
        find(element).sendKeys(input);
        String input_area_text = find(element).getText();
        Assert.assertEquals(input_area_text, input);
    }

    public void isDisplayed(By element) {
        find(element).isDisplayed();
    }

    public void pause(int second) {
        try {
            Thread.sleep(Duration.ofSeconds(second));
        }catch (Exception e) {
            System.out.println("Second method error");
        }
    }


}