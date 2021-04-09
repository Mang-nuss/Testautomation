package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before//("The scenario begins")
    public void setUp() {
        System.out.println("--------------- STARTING SCENARIO ---------------");
    }

    @After//("The browser is closed")
    public static void tearDown() { //Method must not be placed in step definitions file.
        System.out.println("--------------- ENDING SCENARIO ---------------");
    }
}
