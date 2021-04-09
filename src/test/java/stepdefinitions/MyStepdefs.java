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
    public static String address;
    public static String username;
    public static String password;

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
        address = LoginAction.generateStringFrom(email);
        field.sendKeys(address);
        System.out.println("Email step done ->");
    }

    @And("I submit {string} as username")
    public void iSubmitAsUsername(String usr) {
        wait = new WebDriverWait(driver, 15);
        field = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[id^='new_user']")));
        field.click();
        username = LoginAction.generateStringFrom(usr);
        field.sendKeys(username);
        System.out.println("Username step done ->");
    }

    @And("I submit {string} as password")
    public void iSubmitAsPassword(String pwd) {
        wait = new WebDriverWait(driver, 15);
        field = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input#new_password")));
        field.click();
        password = LoginAction.generateStringFrom(pwd);
        field.sendKeys(password);
        System.out.println("Password step done ->");
    }

    @When("I click on sign-in")
    public void iClickOnSignIn() {
        LoginAction.clickByActions(driver, By.cssSelector("button[id=create-account]"));
        System.out.println("Clicked on sign-in ->");
    }

    @Then("Input data should generate {string}")
    public void inputDataShouldGenerate(String message) {

        System.out.print("Outcome: ");
        boolean error = false;
        wait = new WebDriverWait(driver, 15);
        try {
            if(message.equals("success")) { assertTrue(LoginAction.registrationSucceeds(wait)); }
            else if(message.equals("overlong username error")) { assertTrue(LoginAction.overlongUsernameErrorOccurs(wait)); }
            else if(message.equals("existing username error")) { assertTrue(LoginAction.existingUsernameErrorOccurs(wait)); }
            else if(message.equals("missing email error")) { assertTrue(LoginAction.emailErrorOccurs(wait)); }
            System.out.print("Success.\n");
        }
        catch (OverlongUsernameException o) { error = true; }
        catch (ExistingUsernameException e) { error = true; }
        catch (EmailException e) { error = true; }
        catch (Exception e) {
            System.out.println("Caught: " + e.getMessage() +
                    "\nDid the input data match the requirements:" +
                    "\n- Email = " + address +
                    "\n- Username = " + username +
                    "\n- Password = " + password);
            error = true;
        }
        finally {
            if(error) {
                assertFalse(error); //to force the test to fail
            }
        }

        LoginAction.quit(driver);
    }
}
