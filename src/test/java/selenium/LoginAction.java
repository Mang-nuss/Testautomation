package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginAction {

    public static String output;

    public static String lowercases = "abcdefghijklmnopqrstuvwxyz";
    public static String uppercases = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static String numbers = "0123456789";
    public static String specials = "!#&|=?+-_*";

    public static String usernameChars = lowercases + uppercases + numbers;
    public static String emailChars = usernameChars + specials;
    public static String Chars = usernameChars + specials + "/@'^¨%()€"; //these last chars seem to be invalid in emails


    public static String randomise(String set, int nrOfChars) {

        output = "";

        for(int n=0; n<nrOfChars; n++) {
            double nr = ( Math.random() * (set.length()-1) ) + 1;
            output+=set.charAt((int)nr);
        }

        return output;
    }

    public static WebDriver initiateBrowser(String browser) throws Exception {

        WebDriver driver;

        switch (browser) {
            case "Chrome":
                System.setProperty("webdriver.chrome.driver", "/Users/magnusjohansson/chromedriver");
                driver = new ChromeDriver();
                break;
            case "Safari":
                driver = new SafariDriver();
                break;
            case "FF":
                System.setProperty("webdriver.gecko.driver", "/Users/magnusjohansson/geckodriver");
                driver = new FirefoxDriver();
                break;
            default:
                throw new Exception("Invalid browser name");
        }

        System.out.println("Driver was instantiated ->");
        return driver;
    }

    public static void acceptCookies(WebDriver driver) {

        clickByWait(driver, By.cssSelector("button#onetrust-accept-btn-handler"),15);
        System.out.println("Cookies was accepted ->");
    }

    public static void clickByWait(WebDriver driver, By by, int seconds) { //includes waiting

        (new WebDriverWait(driver,seconds)).
                until(ExpectedConditions.presenceOfElementLocated(by));
        driver.findElement(by).click(); //can also be used for sending keys.
    }

    public static String generateStringFrom(String str) {

        output = "";

        switch (str) {
            case "a randomised password":
                output += randomise(Chars,30);
                break;
            case "a randomised address":
                output += randomise(emailChars,30) + "@gmail.com";
                break;
            case "a randomised username of uppercases, lowercases, and ints":
                output += randomise(usernameChars,30);
                break;
            case "an overlong username":
                output += randomise(usernameChars,101);
                break;
            case "a username already in use":
                output = "johanssonmagnus86";
                break;
            case "nothing":
                break;
            default:
                output = str;
                break;
        }

        return output;

    }

    public static boolean existingUsernameErrorOccurs(WebDriverWait wait) throws ExistingUsernameException {

        String selector = "input[class^='invalid']";
        WebElement field;
        try {
            field = wait.until(ExpectedConditions.
                    visibilityOfElementLocated(By.cssSelector(selector)));
        }
        catch (Exception e) { throw new ExistingUsernameException("Existing username error: " +
                "the selector " + selector + " may be erroneous, " +
                "\nor the element was not found in expected time."); }

        return field.isDisplayed();
    }

    public static boolean emailErrorOccurs(WebDriverWait wait) throws EmailException {

        String selector = "label.invalid";
        WebElement field;
        try {
            field = wait.until(ExpectedConditions.
                    visibilityOfElementLocated(By.cssSelector(selector)));
        }
        catch (Exception e) {
            throw new EmailException("Email error: " +
                    "the selector " + selector + " may be erroneous, " +
                    "\nor the element was not found in expected time.");
        }
        return field.isDisplayed();
    }

    public static boolean overlongUsernameErrorOccurs(WebDriverWait wait) throws OverlongUsernameException {

        String selector = "label.invalid";
        WebElement field;
        try {
            field = wait.until(ExpectedConditions.
                    visibilityOfElementLocated(By.cssSelector(selector)));
        }
        catch (Exception e) {
            throw new OverlongUsernameException("Overlong username error: " +
                    "the selector " + selector + " may be erroneous, " +
                    "\nor the element was not found in expected time.");
        }
        return field.isDisplayed();
    }

    public static boolean registrationSucceeds(WebDriverWait wait) {

        String selector = "//*[contains(text(),'Check your email')]";
        WebElement field = wait.until(ExpectedConditions.
                    visibilityOfElementLocated(By.xpath(selector)));

        return field.isDisplayed();
    }

    public static void quit(WebDriver driver) { driver.quit(); }

}
