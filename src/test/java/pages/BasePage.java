package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.DriverFactory;

import java.time.Duration;

public class BasePage extends DriverFactory {
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
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
        find(element).clear();
        find(element).sendKeys(input);
        String input_value = find(element).getAttribute("value");
        Assert.assertEquals(input_value, input);
    }

    public void slow_type(By element, String input) {
        for (char c : input.toCharArray()) {
            find(element).sendKeys(String.valueOf(c));
            try {
                Thread.sleep(Duration.ofMillis(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void type_non_control(By element, String input) {
        find(element).sendKeys(input);
    }

    public void isDisplayed(By element) {
        find(element).isDisplayed();
    }

    public void control_element_input_value(By element, Object object) {
        String element_text = find(element).getAttribute("value");
        Assert.assertEquals(element_text, object);
    }

    public void control_element_text_value(By element, Object object) {
        String element_text = find(element).getText();
        Assert.assertEquals(element_text, object);
    }

    public void pause(long second) {
        try {
            Thread.sleep(Duration.ofSeconds(second));
        }catch (Exception e) {
            System.out.println("Second method error");
        }
    }


}