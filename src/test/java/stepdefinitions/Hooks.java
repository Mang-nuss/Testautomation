package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import selenium.LoginAction;

public class Hooks {

    @Before//("The scenario begins")
    public void setUp() {
        System.out.println("starting off");
    }

    @After//("The browser is closed")
    public static void tearDown() { //Method must not be placed in step definitions file.
        System.out.println("after scenario");
        LoginAction.quit();
    }
}
