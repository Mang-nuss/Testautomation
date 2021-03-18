package selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;
import static org.openqa.selenium.By.cssSelector;

public class LoginAction {

    public static WebDriver driver;
    public static WebElement field;
    public static WebElement emailField;
    public static WebElement usernameField;
    public static WebElement passwordField;


    public static void enterSite() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Users/magnusjohansson/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://login.mailchimp.com/signup/");
        Thread.sleep(3000);
        //driver.quit();

    }

    public static void fillIn(String title, String input) throws InterruptedException {

        //System.out.println(input);
        switch (title) {
            case "email":
                field = driver.findElement(By.xpath("//*[@id='email']"));
            case "username":
                field = driver.findElement(By.xpath("//*[@id='new_username']"));
            case "password":
                field = driver.findElement(By.xpath("//*[@id='new_password']"));
        }

        field.click();
        field.sendKeys(input);
        Thread.sleep(3000);
    }

    public static void registrationCompleted() {
        assertTrue(true);
        driver.quit();
    }
}
