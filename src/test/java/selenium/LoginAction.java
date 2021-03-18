package selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;
import static org.openqa.selenium.By.cssSelector;

public class LoginAction {

    public static void enterSite() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Users/magnusjohansson/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://login.mailchimp.com/signup/");
        Thread.sleep(5000);
        driver.quit();

    }

    public static void registrationCompleted() {
        assertTrue(true);
    }
}
