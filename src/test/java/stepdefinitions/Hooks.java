package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.LoginAction;

public class Hooks {

    //public static WebDriver driver;
    //public static WebDriverWait wait;

    @Before//("The scenario begins")
    public void setUp() {
        System.out.println("--------------- STARTING SCENARIO ---------------");
        //driver = Login.initiateBrowser(browser);
    }

    @After//("The browser is closed")
    public static void tearDown() { //Method must not be placed in step definitions file.
        System.out.println("--------------- ENDING SCENARIO ---------------");
        //LoginAction.quit(driver);
    }
}
