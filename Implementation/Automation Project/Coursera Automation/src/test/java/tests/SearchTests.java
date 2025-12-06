package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SearchPage;

import java.time.Duration;

public class SearchTests {
    WebDriver driver;
    SearchPage searchPage;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        driver.get("https://www.coursera.org/");
        searchPage = new SearchPage(driver);
    }

    @Test
    public void verifySearchBarVisible(){
        Assert.assertTrue(searchPage.isSearchBarVisible(), "Search bar should be visible and enabled");
    }

    @Test
    public void verifySearchInputFunctionality(){
        searchPage.typeKeyword("python");
        Assert.assertEquals(searchPage.searchInput.getAttribute("value"), "python");
    }

    @Test
    public void verifyAutoSuggestion(){
        searchPage.typeKeyword("py");
        Assert.assertTrue(searchPage.areSuggestionsVisible(), "Suggestions should appear for 'py'");
    }

//    @Test
//    public void verifyClickSuggestion(){
//        searchPage.typeKeyword("python");
//        searchPage.clickFirstSuggestion();
//        Assert.assertTrue(searchPage.getCurrentURL().contains("python"), "Should navigate to search results for Python");
//    }

    @Test
    public void verifyEnterTriggersSearch(){
        searchPage.typeKeyword("java");
        searchPage.pressEnter();
        Assert.assertTrue(searchPage.getCurrentURL().contains("java"));
    }

//    @Test
//    public void verifyIconClickTriggersSearch(){
//        searchPage.typeKeyword("data science");
//        searchPage.clickSearchButton();
//        Assert.assertTrue(searchPage.getCurrentURL().contains("data+science"));
//    }

    @Test
    public void verifyNoResults(){
        searchPage.typeKeyword("asdfghjkl");
        searchPage.pressEnter();
        Assert.assertTrue(searchPage.isNoResultsMessageVisible(), "No results message should appear");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
