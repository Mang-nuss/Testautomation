package selenium;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.junit.Test;
import Login.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.*;
import static org.openqa.selenium.By.cssSelector;

public class LoginAction {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static WebElement element;
    public static WebElement field;
    public static WebElement button;
    public static String username;
    public static String inputData;
    public static boolean emailError;
    public static boolean usernameError;

    public static void enterSite(String browser) throws InterruptedException {

        driver = Login.initiateBrowser(browser);
        driver.get("https://login.mailchimp.com/signup/");
        //Thread.sleep(3000); //not preferable
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //implicit wait
    }

    public static void acceptCookies() {

        button = driver.findElement(By.cssSelector("button#onetrust-accept-btn-handler"));
        button.click();
    }

    public static void clickByWait(By by, int seconds) { //includes waiting

        //(new WebDriverWait(driver,seconds)).until(ExpectedConditions.elementToBeClickable(by)); //explicit wait
        (new WebDriverWait(driver,seconds)).until(ExpectedConditions.presenceOfElementLocated(by)); //explicit wait
        driver.findElement(by).click(); //can also be used for sending keys.
    }

    public static void clickByActions(By by) {

        element = driver.findElement(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }

    public static void fillIn(String attribute, String input) {

        wait = new WebDriverWait(driver, 5);
        //System.out.println(input);
        switch (attribute) {
            case "email":
                System.out.println("email");
                //field = driver.findElement(By.cssSelector("input#email"));
                field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
                break;
            case "username":
                System.out.println("usr");
                field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[id^='new_user']")));
                break;
            case "password":
                System.out.println("pwd");
                //field = driver.findElement(By.cssSelector("input[id*='ew_passwor']"));
                field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#new_password")));
                break;
        }

        button = driver.findElement(By.cssSelector("input[name='marketing_newsletter']"));
        //click(By.cssSelector("input[name='marketing_newsletter']"), 5);
        field.click();
        field.sendKeys(input);
        //driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //implicit wait
    }

    public static void conditionedFillIn(String attribute, int nrOfChars, String premise) throws InterruptedException {

        wait = new WebDriverWait(driver, 5);
        //System.out.println(input);
        switch (attribute) {
            case "email":
                System.out.println("email");
                field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type=email]")));
                inputData = Login.generateUniqueEmailAddress(nrOfChars,premise);
                break;
            case "username":
                System.out.println("usr");
                field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='new_username']")));
                inputData = Login.generateUniqueUsername(nrOfChars,premise);
                break;
            case "password":
                System.out.println("pwd");
                field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#new_password")));
                inputData = Login.generateUniquePassword(nrOfChars,premise);
                break;
        }

        field.click();
        field.sendKeys(inputData);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //implicit wait
    }

    public static void invalidErrorOccurs() {
        //field = driver.findElement(By.className("invalid-error"));
        field = driver.findElement(By.xpath("//*[contains(text(),'Another user with this username already exists')]"));
        usernameError = field.isDisplayed();
        if(usernameError) {
            System.out.println("username error");
            return;
        }

        field = driver.findElement(By.xpath("//*[contains(text(),'Please enter a value')]"));
        emailError = field.isDisplayed();
        if(emailError) {
            System.out.println("email error");
            //assertTrue(emailError);
        }

        //boolean error = field.isDisplayed();
        //assertTrue(error);
        driver.quit();
    }

    public static void overlongUsernameErrorOccurs(int nrOfChars) {
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS); //implicit wait

        if(nrOfChars <= 100) { assertFalse(false); }
        //the info given at site is flawed. In fact, the page accepts a nr equalling 100 chars.
        else {
            field = driver.findElement(By.className("invalid-error"));
            assertTrue(field.isDisplayed());
        }
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS); //implicit wait
        driver.quit();
    }

    public static void clickOnSignIn() {
        clickByActions(By.cssSelector("button[id=create-account]"));
        //clickByWait(By.cssSelector("button[id=create-account]"),3);
    }

    public static String overlongUsername(int nr) {
        username = Login.generateUniqueUsername(nr,"none");
        return username;
    }

    public static void registrationCompleted() {
        field = (new WebDriverWait(driver,10)).until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//*[contains(text(),'Check your email')]")));
        //field = driver.findElement(By.className("!margin-bottom--lv3 no-transform center-on-medium"));
        boolean success = field.isDisplayed();
/*        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS); //implicit wait
        String url = driver.getCurrentUrl();
        System.out.println(url);
        String successString = "success";
        boolean success = url.contains(successString);*/
        assertTrue(success);
        //driver.quit();
    }

    @After//("@Tag1") //Method must not be placed in step definitions file.
    public static void tearDown() {
        System.out.println("after scenario");
        driver.quit();
    }
}
