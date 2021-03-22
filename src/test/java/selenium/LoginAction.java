package selenium;

import org.junit.Test;
import Login.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;
import static org.openqa.selenium.By.cssSelector;

public class LoginAction {

    public static WebDriver driver;
    public static WebElement field;
    public static WebElement button;
    public static String username;
    public static String inputData;


    public static void enterSite() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Users/magnusjohansson/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://login.mailchimp.com/signup/");
        Thread.sleep(3000);
        //driver.quit();

    }

    public static void fillIn(String attribute, String input) throws InterruptedException {

        //System.out.println(input);
        switch (attribute) {
            case "email":
                System.out.println("email");
                field = driver.findElement(By.xpath("//*[@id='email']"));
                break;
            case "username":
                System.out.println("usr");
                field = driver.findElement(By.xpath("//*[@id='new_username']"));
                break;
            case "password":
                System.out.println("pwd");
                field = driver.findElement(By.xpath("//*[@id='new_password']"));
                break;
        }

        field.click();
        field.sendKeys(input);
        Thread.sleep(3000);
    }

    public static void conditionedFillIn(String attribute, int nrOfChars, String premise) throws InterruptedException {

        //System.out.println(input);
        switch (attribute) {
            case "email":
                System.out.println("email");
                field = driver.findElement(By.cssSelector("input[type=email]"));
                inputData = Login.generateUniqueEmailAddress(nrOfChars,premise);
                break;
            case "username":
                System.out.println("usr");
                field = driver.findElement(By.xpath("//*[@id='new_username']"));
                inputData = Login.generateUniqueUsername(nrOfChars,premise);
                break;
            case "password":
                System.out.println("pwd");
                field = driver.findElement(By.xpath("//*[@id='new_password']"));
                inputData = Login.generateUniquePassword(nrOfChars,premise);
                break;
        }

        field.click();
        field.sendKeys(inputData);
        Thread.sleep(3000);
    }

    public static void invalidErrorOccurs() throws InterruptedException {
        field = driver.findElement(By.className("invalid-error"));
        boolean error = field.isDisplayed();
        assertTrue(error);
        Thread.sleep(2000);
        driver.quit();
    }

    public static void overlongUsernameErrorOccurs(int nrOfChars) throws InterruptedException {
        field = driver.findElement(By.className("invalid-error"));
        boolean error = field.isDisplayed();

        if(nrOfChars <= 100) { assertFalse(error); }
        else { assertTrue(error); }
        Thread.sleep(2000);
        driver.quit();
    }

    public static void clickOnSignIn() throws InterruptedException {
        button = driver.findElement(By.id("create-account"));
        button.click();
        Thread.sleep(4000);
    }

    public static String overlongUsername(int nr) {
        username = Login.generateUniqueUsername(nr,"none");
        return username;
    }

    public static void registrationCompleted() {
        field = driver.findElement(By.xpath("//*[contains(text(),'Check your email')]"));
        //field = driver.findElement(By.className("!margin-bottom--lv3 no-transform center-on-medium"));
        boolean success = field.isDisplayed();
        assertTrue(success);
        driver.quit();
    }
}
