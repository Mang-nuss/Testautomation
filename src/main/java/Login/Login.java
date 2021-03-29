package Login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Login {

    private static WebDriver driver;
    private static String output;
    private static String suffix;
    private static String lowercases = "abcdefghijklmnopqrstuvwxyz";
    private static String uppercases = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String numbers = "0123456789";
    private static String specials = "!#€%&|()=?+-_*";

    private static String usernameChars = lowercases + uppercases + numbers;
    private static String emailChars = usernameChars + specials;
    private static String Chars = usernameChars + specials + "/@'^¨"; //these chars seem to be invalid in emails

    public static WebDriver initiateBrowser(String browser) {

        switch (browser) {
            case "Chrome":
                System.setProperty("webdriver.chrome.driver", "/Users/magnusjohansson/chromedriver");
                driver = new ChromeDriver();
                break;
            case "Safari":
                driver = new SafariDriver();
                break;
            case "FF":
                System.setProperty("webdriver.gecko.driver", "/Users/magnusjohansson/geckodriver");
                driver = new FirefoxDriver();
                break;
        }

        return driver;
    }

    public static String randomise(String set, int nrOfChars) {

        output = "";

        for(int n=0; n<nrOfChars; n++) {
            double nr = ( Math.random() * (set.length()-1) ) + 1;
            output+=set.charAt((int)nr);
        }

        return output;
    }

/*    public static void main(String[] args) {

        System.out.println(generateUniqueUsername());
        System.out.println(generateUniqueEmailAddress());
    }*/

    //Only letters and numbers, or the email address
    public static String generateUniqueUsername(int nrOfChars, String premise) {

        output = "";

        switch (premise) {
            case "none":
                output = randomise(usernameChars,nrOfChars);
                break;

            case "upper+int":
                output = randomise(uppercases,nrOfChars/2);
                output+=randomise(numbers,nrOfChars/2);
                break;

            case "lower+int":
                output = randomise(lowercases,nrOfChars/2);
                output+=randomise(numbers,nrOfChars/2);
                break;

            case "lower+upper+int":
                output = randomise(lowercases,nrOfChars/2);
                output = randomise(uppercases,nrOfChars/2);
                output+=randomise(numbers,nrOfChars/2);
                break;
        }

        return output;
    }

    /*    One lowercase character
    One uppercase character
    One number
    One special character
    8 characters minimum*/
    public static String generateUniquePassword(int nrOfChars, String premise) {

        output = "";

        switch (premise) {
            case "random choice":
                output+=randomise(Chars,nrOfChars);
                break;
        }

        return output;
    }

    public static String generateUniqueEmailAddress(int nrOfChars, String premise) {

        output = "";

        output+=randomise(emailChars,nrOfChars);

        switch (premise) {
            case "none": //using standard suffix, gmail.com
                output = output + "@gmail.com";
                break;
            case "twisted email suffix":
                suffix = randomise(emailChars,nrOfChars);
                output = output + "@" + suffix + "mail.com";
                break;
            case "no email suffix": //should fail
                break;
        }

        return output;
    }
}
