package stepdefinitions;

import Login.Login;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import selenium.LoginAction;

public class Hooks {

    //public static WebDriver driver;

    @Before//("The scenario begins")
    public void setUp() {
        System.out.println("starting off");
        //driver = Login.initiateBrowser(browser);
    }

    @After//("The browser is closed")
    public static void tearDown() { //Method must not be placed in step definitions file.
        System.out.println("after scenario");
        //LoginAction.quit(driver);
    }
}
