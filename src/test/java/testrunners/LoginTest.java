package testrunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
//@CucumberOptions() //-- works without specs
@CucumberOptions(
        features= "src/test/resources/features",
        glue= "stepdefinitions",
        //tags= {"@Tag2, not @Tag1"},
        tags= "@Tag1",
        //plugin= {"pretty"},
        monochrome= true
)

public class LoginTest { /*public static void main(String[] args) {}*/ }
