package selenium;

import org.openqa.selenium.By;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


public class WeatherAppTest extends BaseTest {
    private static final By PRIVACY_ACCEPTANCE = By.xpath("//*[contains(@class, 'policy-accept')]");

    private static final By SEARCHING_BAR = By.xpath("//*[contains(@class, 'search-input')]");

    private static final String CITY_NAME = "New York";
    private static final By RESULTS = By.xpath("//div[contains(@class, 'source-accuweather')]");

    private static final By SEARCH_HEADER = By.xpath("//*[contains(@class, 'header-loc')]");
    private static final String CITY_REGEX = "\\b" + CITY_NAME + "\\b";

    @Test
    public void weatherAppTest() {


        driver.findElement(PRIVACY_ACCEPTANCE).click();


        driver.findElement(SEARCHING_BAR).sendKeys(CITY_NAME);
        wait.until(ExpectedConditions.elementToBeClickable(RESULTS));
        List<WebElement> searchResults = driver.findElements(RESULTS);



        Assert.assertTrue(waitForElementVisible(searchResults.get(0)), "there aren't any results for the search");


        searchResults.get(0).click();

        wait.until(ExpectedConditions.elementToBeClickable(SEARCH_HEADER));

        Assert.assertTrue(driver.findElement(SEARCH_HEADER).getText().matches(".*" + CITY_REGEX + ".*"), "No match found");

    }

    private boolean waitForElementVisible(WebElement element) {

        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException exception) {
            return false;
        }
        return true;
    }


}

