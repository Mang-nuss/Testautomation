package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.LoginAction;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyStepdefs {

    /*
    * Learned: all steps must be compilable before running a single scenario s, even though they are not
    * all included in s
    * */

/*    @Before
    public void beforeAll() {
        System.out.println("starting off");
    }*/

    @Given("I am up to register at website, using {string}")
    public void i_am_up_to_register_at_website(String browser) throws InterruptedException {
        LoginAction.enterSite(browser);
        System.out.println("entering site");
    }

    @Given("I am up to register at website")
    public void iAmUpToRegisterAtWebsite() throws InterruptedException {
        LoginAction.enterSite("Chrome");
        System.out.println("entering site");
    }

    @When("I submit email address")
    public void ISubmitEmailAddress(io.cucumber.datatable.DataTable dataTable) {

        List<String> data = dataTable.asList(String.class);
        for(String item: data) {
            System.out.println(item);
        }
    }

    @And("I submit <username{string}>")
    public void iSubmitAUsername(String username) {

        System.out.println(username);
        //throw new io.cucumber.java.PendingException();
    }

    @And("I click on sign-in")
    public void iClickOnSignIn() throws InterruptedException {
        LoginAction.clickOnSignIn();
        System.out.println("clicking on sign in");
        //throw new io.cucumber.java.PendingException();
    }

    @Then("The registration is completed")
    public void theRegistrationIsCompleted() {

        System.out.println("complete");
        LoginAction.registrationCompleted();
        //throw new io.cucumber.java.PendingException();
    }

    @When("I submit valid {string} as username and {string} as password")
    public void iSubmitValidUsernameAndPassword(String username, String pwd) throws InterruptedException {
        LoginAction.fillIn("username",username);
        Thread.sleep(2000);
        LoginAction.fillIn("password",pwd);
    }

    @But("I submit a username too extensive, containing {int} characters")
    public void iSubmitAUsernameTooExtensive(int nrOfChars) throws InterruptedException {
        String usr = LoginAction.overlongUsername(nrOfChars);
        LoginAction.fillIn("username",usr);
    }

    @Then("There is an error saying that email is missing")
    public void thereIsAnErrorSayingThatEmailIsMissing() throws InterruptedException {
        LoginAction.invalidErrorOccurs();
        System.out.println("email error outprint");
    }

    @Then("There is an error saying username is taken")
    public void thereIsAnErrorSayingUsernameIsTaken() throws InterruptedException {
        LoginAction.invalidErrorOccurs();
        System.out.println("username duplicate error outprint");
    }

    @Then("There is an error message saying that {int} characters are at least one too many")
    public void overlongError(int nrOfChars) throws InterruptedException {
        LoginAction.overlongUsernameErrorOccurs(nrOfChars);
        System.out.println("overlong username error outprint");
    }

    @But("I submit a {string} that is taken since before")
    public void iSubmitAUsernameThatIsTakenSinceBefore(String username) throws InterruptedException {
        LoginAction.fillIn("username",username);
        System.out.println("username taken");
    }

    @But("I forget to submit an email")
    public void iForgetToSubmitAnEmail() {

        System.out.println("forgot!");
    }

    @And("I submit <password{string}>")
    public void iSubmitAPasswordAsPassword(String string) {

        System.out.println(string);
    }

    @When("I submit an email of {int} characters, using {string}")
    public void iSubmitAnEmailOfIntCharactersUsing(int nrOfChars, String premise) throws InterruptedException {
        LoginAction.conditionedFillIn("email",nrOfChars,premise);
    }

    @And("I submit a username of {int} characters, using {string}")
    public void iSubmitAUsernameOfIntCharactersUsing(int nrOfChars, String premise) throws InterruptedException {
        LoginAction.conditionedFillIn("username",nrOfChars,premise);
    }

    @And("I submit a password of {int} characters, using {string}")
    public void iSubmitAPasswordOfIntCharactersUsing(int nrOfChars, String premise) throws InterruptedException {
        LoginAction.conditionedFillIn("password",nrOfChars,premise);
    }

    @When("I submit valid {string} as email and {string} as password")
    public void iSubmitValidEmailAndPassword(String email, String pwd) throws InterruptedException {
        LoginAction.fillIn("email",email);
        Thread.sleep(2000);
        LoginAction.fillIn("password",pwd);
    }

    @Then("If the nr {int} of characters is larger than 99, there is an error message")
    public void ifTheNrOfCharactersAreOrLargerThereIsAnErrorMessage(int nrOfChars) throws InterruptedException {
        LoginAction.overlongUsernameErrorOccurs(nrOfChars);
        System.out.println("overlong username error outprint");
    }

/*    @After
    public void quitDriver(WebDriver driver) {
        driver.quit();
    }*/
}
