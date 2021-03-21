package stepdefinitions;

import io.cucumber.java.en.*;
import selenium.LoginAction;

import java.util.List;

public class MyStepdefs {

    /*
    * Learned: all steps must be compilable before running a single scenario s, even though they are not
    * all included in s
    * */

    @Given("I am up to register at website")
    public void i_am_up_to_register_at_website() throws InterruptedException {
        LoginAction.enterSite();
        System.out.println("step taken");
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

    @When("I submit valid {string} as email and {string} as password")
    public void iSubmitValidEmailAndPassword(String email, String pwd) throws InterruptedException {
        LoginAction.fillIn("email",email);
        Thread.sleep(2000);
        LoginAction.fillIn("password",pwd);
    }

    @But("I submit a username too extensive, containing {int} characters")
    public void iSubmitAUsernameTooExtensive(int nrOfChars) throws InterruptedException {
        String usr = LoginAction.overlongUsername(nrOfChars);
        LoginAction.fillIn("username",usr);
    }

    @Then("There is an error saying username is taken")
    public void thereIsAnErrorSayingUsernameIsTaken() {
        LoginAction.invalidErrorOccurs();
        System.out.println("username duplicate error outprint");
    }

    @Then("There is an error message saying that {int} characters are at least one too many")
    public void overlongError(int nrOfChars) {
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

    @When("I submit valid {string} as username and {string} as password")
    public void iSubmitValidUsernameAndPassword(String username, String pwd) throws InterruptedException {
        LoginAction.fillIn("username",username);
        Thread.sleep(2000);
        LoginAction.fillIn("password",pwd);
    }

    @And("I submit <password{string}>")
    public void iSubmitAPasswordAsPassword(String string) {

        System.out.println(string);
    }

    @Then("There is an error saying that email is missing")
    public void thereIsAnErrorSayingThatEmailIsMissing() {
        LoginAction.invalidErrorOccurs();
        System.out.println("email error outprint");
    }

    @When("I submit an email using {string}")
    public void iSubmitAnEmailUsing(int nrOfChars, String premise) throws InterruptedException {
        LoginAction.conditionedFillIn("email",nrOfChars,premise);
    }

    @And("I submit a username using {string}")
    public void iSubmitAUsernameUsing(int nrOfChars, String premise) throws InterruptedException {
        LoginAction.conditionedFillIn("username",nrOfChars,premise);
    }

    @And("I submit a password using {string}")
    public void iSubmitAPasswordUsing(int nrOfChars, String premise) throws InterruptedException {
        LoginAction.conditionedFillIn("password",nrOfChars,premise);
    }
}
