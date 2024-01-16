package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

abstract class BaseTest {
    protected WebDriver driver;
    protected final String URL = "https://www.accuweather.com/";
    protected static final int WAIT_VALUE=10;
    protected WebDriverWait wait;
    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        wait =  new WebDriverWait(driver, Duration.ofSeconds(WAIT_VALUE));

    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
