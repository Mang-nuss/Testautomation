package stepdefinitions;

import Login.Login;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import selenium.LoginAction;

import static org.junit.Assert.assertTrue;

public class MyStepdefs {

    /*
    * Learned: all steps must be compilable before running a single scenario s, even though they are not
    * all included in s
    * */

    @Given("I am up to register at website, using {string}")
    public void i_am_up_to_register_at_website(String browser) {
        LoginAction.enterSite(browser);
        LoginAction.acceptCookies();
        System.out.println("entering site");
    }

    @And("I click on sign-in")
    public void iClickOnSignIn() {
        LoginAction.clickByActions(By.cssSelector("button[id=create-account]"));
        System.out.println("clicking on sign in");
    }

    @Then("The registration is completed")
    public void theRegistrationIsCompleted() {
        System.out.println("complete");
        LoginAction.registrationSucceeds();
    }

    @When("I submit valid {string} as username and {string} as password")
    public void iSubmitValidUsernameAndPassword(String username, String pwd) {
        LoginAction.fillIn("username",username);
        LoginAction.fillIn("password",pwd);
    }

    @When("I submit valid {string} as email and {string} as password")
    public void iSubmitValidEmailAndPassword(String email, String pwd) {
        LoginAction.fillIn("email",email);
        LoginAction.fillIn("password",pwd);
    }

    @But("I submit a username containing {int} characters")
    public void iSubmitAUsernameTooExtensive(int nrOfChars) {
        String usr = Login.generateUniqueUsername(nrOfChars,"none");
        LoginAction.fillIn("username",usr);
    }

    @Then("There is an error saying that email is missing")
    public void thereIsAnErrorSayingThatEmailIsMissing() {
        assertTrue(LoginAction.emailErrorOccurs());
        System.out.println("email error outprint");
    }

    @Then("There is an error saying username is taken")
    public void thereIsAnErrorSayingUsernameIsTaken() {
        //LoginAction.acceptCookies();
        assertTrue(LoginAction.existingUsernameErrorOccurs());
        System.out.println("username duplicate check");
    }

    @Then("There is an error message saying that {int} characters are at least one too many")
    public void overlongError(int nrOfChars) {
        //LoginAction.acceptCookies();
        LoginAction.overlongUsernameErrorOccurs(nrOfChars);
        System.out.println("overlong username check");
    }

    @But("I submit a {string} that is taken since before")
    public void iSubmitAUsernameThatIsTakenSinceBefore(String username) {
        LoginAction.fillIn("username",username);
        System.out.println("username taken");
    }

    @But("I forget to submit an email")
    public void iForgetToSubmitAnEmail() { System.out.println("forgot!"); }

    @When("I submit an email of {int} characters, using {string}")
    public void iSubmitAnEmailOfIntCharactersUsing(int nrOfChars, String premise) {
        LoginAction.conditionedFillIn("email",nrOfChars,premise);
    }

    @And("I submit a username of {int} characters, using {string}")
    public void iSubmitAUsernameOfIntCharactersUsing(int nrOfChars, String premise) {
        LoginAction.conditionedFillIn("username",nrOfChars,premise);
    }

    @And("I submit a password of {int} characters, using {string}")
    public void iSubmitAPasswordOfIntCharactersUsing(int nrOfChars, String premise) {
        LoginAction.conditionedFillIn("password",nrOfChars,premise);
    }

    @Then("If the nr {int} of characters is larger than 99, there is an error message")
    public void ifTheNrOfCharactersAreOrLargerThereIsAnErrorMessage(int nrOfChars) {
        LoginAction.overlongUsernameErrorOccurs(nrOfChars);
    }
}
