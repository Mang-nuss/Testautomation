package stepdefinitions;

import Login.*;
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
    public static WebElement field;
    public static String emailInputData;
    public static String passwordInputData;
    public static String usernameInputData;

    /*
    * Learned: all steps must be compilable before running a single scenario s, even though they are not
    * all included in s
    * */
    @Given("I am up to register at website, using {string}")
    public void iAmUpToRegisterAtWebsiteUsing(String browser) {
        driver = Login.initiateBrowser(browser);
        driver.get("https://login.mailchimp.com/signup/");
        driver.manage().window().maximize();

        LoginAction.acceptCookies(driver);
        System.out.println("step 1 done");
        System.out.println(driver.getCurrentUrl());
    }

    @And("I submit {string} as email")
    public void iSubmitAsEmail(String email) {

        //System.out.println(driver.getCurrentUrl());
        wait = new WebDriverWait(driver, 15);
/*        field = wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//*[contains(text(),'Welcome to Mailchimp')]")));*/

        field = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input#email")));
        field.click();
        field.sendKeys(LoginAction.generateStringFrom(email));
    }

    @And("I submit {string} as username")
    public void iSubmitAsUsername(String usr) {
        wait = new WebDriverWait(driver, 15);
        field = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[id^='new_user']")));
        field.click();
        field.sendKeys(LoginAction.generateStringFrom(usr));
    }

    @And("I submit {string} as password")
    public void iSubmitAsPassword(String pwd) {
        wait = new WebDriverWait(driver, 15);
        field = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input#new_password")));
        field.click();
        field.sendKeys(LoginAction.generateStringFrom(pwd));
    }

    @When("I click on sign-in")
    public void iClickOnSignIn() { LoginAction.clickByActions(driver, By.cssSelector("button[id=create-account]")); }

    @Then("Input data should generate {string}")
    public void inputDataShouldGenerate(String message) {
        wait = new WebDriverWait(driver, 15);
        if(message.equals("success")) { LoginAction.registrationSucceeds(wait); }
        else if(message.equals("overlong username error")) { assertTrue(LoginAction.overlongUsernameErrorOccurs(wait)); }
        else if(message.equals("existing username error")) { assertTrue(LoginAction.existingUsernameErrorOccurs(wait)); }
        else if(message.equals("missing email error")) { assertTrue(LoginAction.emailErrorOccurs(wait)); }

        LoginAction.quit(driver);
    }
}
