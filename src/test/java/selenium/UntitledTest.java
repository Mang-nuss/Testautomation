package selenium;// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class UntitledTest {
  public static WebDriver driver;
  public static WebElement element;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    //System.setProperty("webdriver.chrome.driver", "/Users/magnusjohansson/chromedriver");
    //driver = new ChromeDriver();
    initiateBrowser("FF");
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void untitled() {
    driver.get("https://www.marshu.com/articles/calculate-addition-calculator-add-two-numbers.php");
    driver.findElement(By.name("n01")).sendKeys("10");
    driver.findElement(By.name("n02")).sendKeys("10");
    //driver.findElement(By.cssSelector("p:nth-child(4) > input")).click();
    WebElement e = driver.findElement(By.cssSelector("p:nth-child(4) > input"));
    js.executeScript("arguments[0].click();", e);
    //assertTrue
  }

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

  public static void click(By by, int seconds) { //includes waiting

    //driver = new ChromeDriver();

    (new WebDriverWait(driver,seconds)).until(ExpectedConditions.elementToBeClickable(by)); //explicit wait
    driver.findElement(by).click(); //can also be used for sending keys.
  }

  public static void exploreTheManifesto() throws InterruptedException {

    driver = initiateBrowser("Chrome");
    driver.get("https://agilemanifesto.org/iso/sv/manifesto.html");
    //body > center > p:nth-child(11) > font:nth-child(16) >
    By by = By.cssSelector("a:nth-child(83)");
    element = driver.findElement(by);
    Actions actions = new Actions(driver);
    actions.moveToElement(element);

    ArrayList<WebElement> elements = new ArrayList<WebElement>();
    elements = (ArrayList<WebElement>) driver.findElements(by.cssSelector("a[href*='manifesto.html']"));

    for(WebElement e : elements) { System.out.println(e.getText()); }

    Thread.sleep(2000);
    click(by,10);

    element = driver.findElement(by.tagName("h1"));
    System.out.println("text: " + element.getText());
    assertTrue(element.isDisplayed());
    //assertEquals(driver.getTitle(), "Manifestet for smidig programvareutvekling");
    Thread.sleep(2000);

    //body > center > p:nth-child(10) > font:nth-child(2)
    element = driver.findElement(by.cssSelector("font:nth-child(2)"));
    element.sendKeys(Keys.CONTROL);
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //implicit wait

    //driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    driver.quit();

  }

  //exploreTheManifesto();
  @Test
  public void manifestoTest() throws InterruptedException {
    exploreTheManifesto();
  }
}
