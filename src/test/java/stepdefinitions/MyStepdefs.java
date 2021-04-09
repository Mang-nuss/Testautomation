package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.EmailException;
import selenium.ExistingUsernameException;
import selenium.LoginAction;
import selenium.OverlongUsernameException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MyStepdefs {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static WebElement field;

    /*
    * Learned: all steps must be compilable before running a single scenario s, even though they are not
    * all included in s
    * */
    @Given("I am up to register at website, using {string}")
    public void iAmUpToRegisterAtWebsiteUsing(String browser) {
        try {
            driver = LoginAction.initiateBrowser(browser);
            driver.get("https://login.mailchimp.com/signup/");
            driver.manage().window().maximize();
            LoginAction.acceptCookies(driver);
        }
        catch(Exception e) {
            System.out.println("Caught: " + e.getMessage());
        }
        finally {
            if(driver == null) {
                System.out.println("test session is closed due to fail.");
                System.exit(1);
            }
        }
    }

    @And("I submit {string} as email")
    public void iSubmitAsEmail(String email) {
        wait = new WebDriverWait(driver, 15);

        field = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input#email")));
        field.click();
        field.sendKeys(LoginAction.generateStringFrom(email));
        System.out.println("Email step done ->");
    }

    @And("I submit {string} as username")
    public void iSubmitAsUsername(String usr) {
        wait = new WebDriverWait(driver, 15);
        field = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[id^='new_user']")));
        field.click();
        field.sendKeys(LoginAction.generateStringFrom(usr));
        System.out.println("Username step done ->");
    }

    @And("I submit {string} as password")
    public void iSubmitAsPassword(String pwd) {
        wait = new WebDriverWait(driver, 15);
        field = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input#new_password")));
        field.click();
        field.sendKeys(LoginAction.generateStringFrom(pwd));
        System.out.println("Password step done ->");
    }

    @When("I click on sign-in")
    public void iClickOnSignIn() {
        LoginAction.clickByActions(driver, By.cssSelector("button[id=create-account]"));
        System.out.println("Clicked on sign-in");
    }

    @Then("Input data should generate {string}")
    public void inputDataShouldGenerate(String message) {

        boolean error = false;
        wait = new WebDriverWait(driver, 15);
        try {
            if(message.equals("success")) { LoginAction.registrationSucceeds(wait); }
            else if(message.equals("overlong username error")) { assertTrue(LoginAction.overlongUsernameErrorOccurs(wait)); }
            else if(message.equals("existing username error")) { assertTrue(LoginAction.existingUsernameErrorOccurs(wait)); }
            else if(message.equals("missing email error")) { assertTrue(LoginAction.emailErrorOccurs(wait)); }
        }
        catch (OverlongUsernameException o) {
            System.out.println("Username is overlong: " + o.getMessage());
        }
        catch (ExistingUsernameException e) {
            System.out.println("Existing username: " + e.getMessage());
        }
        catch (EmailException e) {
            System.out.println("Email is missing: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Caught: " + e.getMessage());
            error = true;
        }
        finally {
            if(error) {
                System.out.println("Closed due to an error.");
                assertFalse(error);
                //System.exit(1);
                LoginAction.quit(driver);
            }

            //LoginAction.quit(driver);
        }

        LoginAction.quit(driver);
    }
}
