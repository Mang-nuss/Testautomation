package selenium;

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
    //public static ArrayList<WebElement> elements = new ArrayList<WebElement>();

    public static void enterSite(String browser) throws InterruptedException {

        driver = Login.initiateBrowser(browser);
        driver.get("https://login.mailchimp.com/signup/");
        //Thread.sleep(3000); //not preferable
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //implicit wait
        //elements = (ArrayList<WebElement>) driver.findElements(By.cssSelector("input[name='password']"));
        //for(WebElement e : elements) { System.out.println("text: " + (e.getAttribute("name"))); }

        //driver.quit();

    }

    public static void clickByWait(By by, int seconds) { //includes waiting

        //driver = new ChromeDriver();

        //(new WebDriverWait(driver,seconds)).until(ExpectedConditions.elementToBeClickable(by)); //explicit wait
        (new WebDriverWait(driver,seconds)).until(ExpectedConditions.presenceOfElementLocated(by)); //explicit wait
        driver.findElement(by).click(); //can also be used for sending keys.
    }

    public static void clickByActions(By by) {
        element = driver.findElement(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }

    public static void fillIn(String attribute, String input) throws InterruptedException {

        //System.out.println(input);
        switch (attribute) {
            case "email":
                System.out.println("email");
                //field = driver.findElement(By.cssSelector("input#email"));
                wait = new WebDriverWait(driver, 10);
                field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
                break;
            case "username":
                System.out.println("usr");
                field = driver.findElement(By.cssSelector("input[id^='new_user']"));
                break;
            case "password":
                System.out.println("pwd");
                field = driver.findElement(By.cssSelector("input[id*='ew_passwor']"));
                break;
        }

        button = driver.findElement(By.cssSelector("input[name='marketing_newsletter']"));
        //click(By.cssSelector("input[name='marketing_newsletter']"), 5);
        field.click();
        field.sendKeys(input);
        //driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //implicit wait
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
                field = driver.findElement(By.cssSelector("input#new_password"));
                inputData = Login.generateUniquePassword(nrOfChars,premise);
                break;
        }

        field.click();
        field.sendKeys(inputData);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //implicit wait
    }

    public static void invalidErrorOccurs() throws InterruptedException {
        field = driver.findElement(By.className("invalid-error"));
        boolean error = field.isDisplayed();
        assertTrue(error);
        Thread.sleep(2000);
        driver.quit();
    }

    public static void overlongUsernameErrorOccurs(int nrOfChars) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS); //implicit wait

        if(nrOfChars <= 99) { assertFalse(false); }
        else {
            field = driver.findElement(By.className("invalid-error"));
            assertTrue(field.isDisplayed());
        }
        Thread.sleep(2000);
        driver.quit();
    }

    public static void clickOnSignIn() {
/*        button = driver.findElement(By.id("create-account"));
        button.click();
        Thread.sleep(4000);*/
/*        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //implicit wait
        click(By.id("create-account"), 5);*/
/*        wait = new WebDriverWait(driver, 3);
        button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[id=create-account]")));
        button.click();*/
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
        driver.quit();
    }
}
