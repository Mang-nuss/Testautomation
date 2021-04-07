package stepdefinitions;

import Login.Login;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.LoginAction;

import static org.junit.Assert.assertTrue;

public class MyStepdefs {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static WebElement element;
    public static WebElement field;
    //public static WebElement button;
    public static String username;
    public static String inputData;
    public static boolean emailError;
    public static boolean usernameError;

    /*
    * Learned: all steps must be compilable before running a single scenario s, even though they are not
    * all included in s
    * */
    @Given("I am up to register at website, using {string}")
    public void iAmUpToRegisterAtWebsiteUsing(String browser) {
        LoginAction.enterSite(browser);
        LoginAction.acceptCookies();
    }

    @And("I submit {string} as email")
    public void iSubmitAsEmail(String email) {
        LoginAction.explicitWait(15);
        field = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input#email")));
        inputData = LoginAction.generateStringFrom(email);
        field.click();
        field.sendKeys(inputData);
    }

    @And("I submit {string} as username")
    public void iSubmitAsUsername(String usr) {
        LoginAction.explicitWait(15);
        field = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[id^='new_user']")));
        inputData = LoginAction.generateStringFrom(usr);
        field.click();
        field.sendKeys(inputData);
    }

    @And("I submit {string} as password")
    public void iSubmitAsPassword(String pwd) {
        LoginAction.explicitWait(15);
        field = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input#new_password")));
        inputData = LoginAction.generateStringFrom(pwd);
        field.click();
        field.sendKeys(inputData);
    }

    @When("I click on sign-in")
    public void iClickOnSignIn() { LoginAction.clickByActions(By.cssSelector("button[id=create-account]")); }

    @Then("If the requirements are met, the registration is completed")
    public void ifTheRequirementsAreMetTheRegistrationIsCompleted() {
        if(LoginAction.noEmailAddress()) { assertTrue(LoginAction.emailErrorOccurs()); }
        else if(LoginAction.usernameIsTaken()) { assertTrue(LoginAction.existingUsernameErrorOccurs()); }
        else if(LoginAction.overlongUsername()) { assertTrue(LoginAction.overlongUsernameErrorOccurs()); }
        else { LoginAction.registrationSucceeds(); }
    }
}
