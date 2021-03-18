package stepdefinitions;

import io.cucumber.java.en.*;
import selenium.LoginAction;

public class MyStepdefs {

    @Given("I am up to register at website")
    public void iAmUpToRegisterAtWebsite() throws InterruptedException {
        LoginAction.enterSite();
        System.out.println("step taken");
        //throw new io.cucumber.java.PendingException();
    }

    @When("I submit an {email}")
    public void iSubmitAnEmail(String email) {

        System.out.println(email);
        //throw new io.cucumber.java.PendingException();
    }

    @And("I submit a {username}")
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

    @When("I submit valid {email} and {password}")
    public void iSubmitValidEmailAndPassword() {

        System.out.println("step taken");
        //throw new io.cucumber.java.PendingException();
    }

    @But("I submit a {username} too extensive")
    public void iSubmitAUsernameTooExtensive() {

        System.out.println("step taken");
    }

    @Then("There is an error")
    public void thereIsAnError() {

        System.out.println("step taken");
    }

    @But("I submit a {username} that is taken since before")
    public void iSubmitAUsernameThatIsTakenSinceBefore() {

        System.out.println("step taken");
    }

    @But("I forget to submit an {email}")
    public void iForgetToSubmitAnEmail() {

        System.out.println("step taken");
    }

    @And("I submit a {password}")
    public void iSubmitAPassword(String password) {

        System.out.println(password);
    }

    @When("I submit valid {username} and {password}")
    public void iSubmitValidUsernameAndPassword() {

        throw new io.cucumber.java.PendingException();
    }

}
