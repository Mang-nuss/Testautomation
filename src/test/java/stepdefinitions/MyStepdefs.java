package stepdefinitions;

import io.cucumber.java.en.*;
import selenium.LoginAction;

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

    @When("I submit {string} as email")
    public void iSubmitAsEmail(String mail) {

        System.out.println(mail);
    }

    @And("I submit {string} as password")
    public void iSubmitAsPassword(String pwd) {

        System.out.println(pwd);
    }

    @And("I submit {string} as username")
    public void iSubmitAsUsername(String username) {

        System.out.println(username);
    }

    @And("I submit <username{string}>")
    public void iSubmitAUsername(String username) {

        System.out.println(username);
        //throw new io.cucumber.java.PendingException();
    }

    @And("I click on sign-in")
    public void iClickOnSignIn() {

        System.out.println("clicking on sign in");
        //throw new io.cucumber.java.PendingException();
    }

    @Then("The registration is completed")
    public void theRegistrationIsCompleted() {

        System.out.println("complete");
        LoginAction.registrationCompleted();
        //throw new io.cucumber.java.PendingException();
    }

    @When("I submit valid {string} and {string}")
    public void iSubmitValidEmailAndPassword() {

        System.out.println("step taken");
        //throw new io.cucumber.java.PendingException();
    }

    @But("I submit a {string} too extensive")
    public void iSubmitAUsernameTooExtensive() {

        System.out.println("step taken");
    }

    @Then("There is an error")
    public void thereIsAnError() {

        System.out.println("step taken");
    }

    @But("I submit a {string} that is taken since before")
    public void iSubmitAUsernameThatIsTakenSinceBefore() {

        System.out.println("step taken");
    }

    @But("I forget to submit an {string}")
    public void iForgetToSubmitAnEmail() {

        System.out.println("step taken");
    }
/*
    @When("I submit valid {string} and {string}")
    public void iSubmitValidUsernameAndPassword() {

        throw new io.cucumber.java.PendingException();
    }*/

    @And("I submit <password{string}>")
    public void iSubmitAPasswordAsPassword(String string) {

        System.out.println(string);
    }

    @When("I submit <email>")
    public void iSubmitEmail(String string) {

        System.out.println(string);
    }

    @When("I submit <email> as email")
    public void iSubmitEmailAsEmail(String string) {

        System.out.println(string);
    }
}
