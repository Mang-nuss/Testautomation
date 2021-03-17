package stepdefinitions;

import io.cucumber.java.en.*;

public class MyStepdefs {
    @Given("^I am up to register at <website>$")
    public void iAmUpToRegisterAtWebsite() {
        System.out.println("step taken");
    }

    @When("^I submit an <email>$")
    public void iSubmitAnEmail() {
        System.out.println("step taken");
    }

    @And("^I submit a <username>$")
    public void iSubmitAUsername() {
        System.out.println("step taken");
    }

    @And("^I click on sign-in$")
    public void iClickOnSignIn() {
        System.out.println("step taken");
    }

    @Then("^The registration is completed$")
    public void theRegistrationIsCompleted() {
        System.out.println("step taken");
    }

    @When("^I submit valid <email> and <password>$")
    public void iSubmitValidEmailAndPassword() {
        System.out.println("step taken");
    }

    @But("^I submit a <username> too extensive$")
    public void iSubmitAUsernameTooExtensive() {
        System.out.println("step taken");
    }

    @Then("^There is an error$")
    public void thereIsAnError() {
        System.out.println("step taken");
    }

    @But("^I submit a <username> that is taken since before$")
    public void iSubmitAUsernameThatIsTakenSinceBefore() {
        System.out.println("step taken");
    }

    @But("^I forget to submit an <email>$")
    public void iForgetToSubmitAnEmail() {
        System.out.println("step taken");
    }
}
