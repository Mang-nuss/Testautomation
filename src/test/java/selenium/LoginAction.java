package selenium;

import Login.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

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

    public static void enterSite(String browser) {

        driver = Login.initiateBrowser(browser);
        driver.get("https://login.mailchimp.com/signup/");
        //Thread.sleep(3000); //not preferable
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //implicit wait
    }

    public static void acceptCookies() {

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //implicit wait
        //button = driver.findElement(By.cssSelector("button#onetrust-accept-btn-handler"));
        //button.click();
        //clickByWait(By.cssSelector("button#onetrust-accept-btn-handler"),3);
        clickByActions(By.cssSelector("button#onetrust-accept-btn-handler"));
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //implicit wait
    }

    public static void clickByWait(By by, int seconds) { //includes waiting

        (new WebDriverWait(driver,seconds)).
                until(ExpectedConditions.presenceOfElementLocated(by)); //explicit wait
        driver.findElement(by).click(); //can also be used for sending keys.
    }

    public static void clickByActions(By by) {

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //implicit wait
        element = driver.findElement(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //implicit wait
    }

    public static void fillIn(String attribute, String input) {

        //driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //implicit wait
        //wait = new WebDriverWait(driver, 2);
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
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //implicit wait
    }

    public static void conditionedFillIn(String attribute, int nrOfChars, String premise) {

        //driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //implicit wait
        //wait = new WebDriverWait(driver, 2);
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

    public static boolean existingUsernameErrorOccurs() {
        //field = driver.findElement(By.className("invalid-error"));
        field = driver.findElement(By.cssSelector("input[class^='invalid']"));
        //field = driver.findElement(By.xpath("//*[contains(text(),'Another user with this username already exists')]"));
/*        field = (new WebDriverWait(driver,10)).until(ExpectedConditions.
                visibilityOfElementLocated(By.cssSelector("input[class^='invalid']")));*/
        return field.isDisplayed();
    }

    public static boolean emailErrorOccurs() {
        field = driver.findElement(By.cssSelector("label.invalid"));
        //field = driver.findElement(By.xpath("//*[contains(text(),'Please enter a value')]"));
        //field = driver.findElement(By.cssSelector("input[class='invalid av-email']"));
/*        field = (new WebDriverWait(driver,10)).until(ExpectedConditions.
                visibilityOfElementLocated(By.cssSelector("label.invalid")));*/
        return field.isDisplayed();
    }

    public static void overlongUsernameErrorOccurs(int nrOfChars) {
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS); //implicit wait

        if(nrOfChars <= 100) { //registrationSucceeds();
            assertTrue(driver.findElement(By.xpath("//*[contains(text(),'Check your email')]")).isDisplayed());
        }
        //the info given at site is flawed. In fact, the page accepts a nr equalling 100 chars.
        else { assertTrue(driver.findElement(By.className("invalid-error")).isDisplayed()); }

        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS); //implicit wait
    }

    public static void registrationSucceeds() {
        System.out.println("registration succeeds method");
        field = (new WebDriverWait(driver,10)).until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//*[contains(text(),'Check your email')]")));
        //field = driver.findElement(By.className("!margin-bottom--lv3 no-transform center-on-medium"));
        //*[@id="signup-content"]/div/div/div/h1

/*        field = (new WebDriverWait(driver,10)).until(ExpectedConditions.
                visibilityOfElementLocated(By.cssSelector("span[]")));*/
/*        field = (new WebDriverWait(driver,10)).until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//*[@id='signup-content']/div/div/div/h1")));*/
        assertTrue(field.isDisplayed());

        //assertEquals(driver.getTitle(),"Success | Mailchimp");

/*        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS); //implicit wait
        String url = driver.getCurrentUrl();
        System.out.println(url);
        String successString = "success";
        boolean success = url.contains(successString);
        assertTrue(success);*/
    }

    public static void quit() { driver.quit(); }
}
