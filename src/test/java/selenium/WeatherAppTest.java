package selenium;

import org.openqa.selenium.By;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;


public class WeatherAppTest extends BaseTest{
 private final By PRIVACY_ACCEPTANCE=By.className("policy-accept");

 private final By SEARCHING_BAR=By.className("search-input");
 private final String CITY_NAME="New York";
 private final By FIRST_RESULT=By.xpath("//div[@class='search-bar-result search-result source-accuweather'][1]");
 private final By SEARCH_HEADER=By.className("header-loc");
 private final String CITY_REGEX="\\b" + CITY_NAME + "\\b";

 @Test
 public void weatherAppTest(){

     //Step 1.- Consent data usage.
     driver.findElement(PRIVACY_ACCEPTANCE).click();

     //Step 2.- Input "New York" in the search field.
     driver.findElement(SEARCHING_BAR).sendKeys(CITY_NAME);
     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
     WebElement firstResult = wait.until(ExpectedConditions.elementToBeClickable(FIRST_RESULT));

     //Checking a result list is displayed
     Assert.assertTrue(anyResults(firstResult),"there aren't any results for the search");

     //Step 3.- Click on the first search result.
     firstResult.click();

     wait.until(ExpectedConditions.elementToBeClickable(SEARCH_HEADER));
     //Checking the header contains city name from search
     Assert.assertTrue(driver.findElement(SEARCH_HEADER).getText().matches(".*" + CITY_REGEX + ".*"),"No match found");

 }
    private boolean anyResults(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        }
        catch (TimeoutException exception){
            return false;
        }
        return true;
    }


}

