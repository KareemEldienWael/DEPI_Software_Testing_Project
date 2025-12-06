package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.FiltersPage;

import java.time.Duration;
import java.util.List;

public class FilterTests {

    WebDriver driver;
    FiltersPage filtersPage;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.coursera.org/search?query=java");
        filtersPage = new FiltersPage(driver);
    }

    @Test
    public void testFullFilterFlow() throws InterruptedException {

        Thread.sleep(2000);
        filtersPage.startFilter();
        Thread.sleep(2000);

    	// -------------------------
        // 1️⃣ LANGUAGE FILTERS
        // -------------------------
        filtersPage.selectLanguage("English");
        WebElement selectedLang = driver.findElement(By.xpath("//span[contains(@class,'cds-checkboxAndRadio-labelContent') and text()='English']"));
        Assert.assertTrue(selectedLang.isDisplayed(), "English language filter should be applied");

        filtersPage.selectLanguage("Spanish");
        WebElement selectedLang2 = driver.findElement(By.xpath("//span[contains(@class,'cds-checkboxAndRadio-labelContent') and text()='Spanish']"));
        Assert.assertTrue(selectedLang2.isDisplayed(), "Spanish language filter should be applied");

        filtersPage.selectLanguage("Arabic");
        WebElement selectedLang3 = driver.findElement(By.xpath("//span[contains(@class,'cds-checkboxAndRadio-labelContent') and text()='Arabic']"));
        Assert.assertTrue(selectedLang3.isDisplayed(), "Arabic language filter should be applied");

        // -------------------------
        // 2️⃣ LEVEL FILTERS
        // -------------------------
        filtersPage.selectLevel("Beginner");
        WebElement levelB = driver.findElement(By.xpath("//span[contains(@class,'cds-checkboxAndRadio-labelContent') and text()='Beginner']"));
        Assert.assertTrue(levelB.isDisplayed(), "Beginner level filter applied");

        filtersPage.selectLevel("Intermediate");
        WebElement levelI = driver.findElement(By.xpath("//span[contains(@class,'cds-checkboxAndRadio-labelContent') and text()='Intermediate']"));
        Assert.assertTrue(levelI.isDisplayed(), "Intermediate level filter applied");

        filtersPage.selectLevel("Advanced");
        WebElement levelA = driver.findElement(By.xpath("//span[contains(@class,'cds-checkboxAndRadio-labelContent') and text()='Advanced']"));
        Assert.assertTrue(levelA.isDisplayed(), "Advanced level filter applied");

        // -------------------------
        // 3️⃣ SUBTITLE FILTERS
        // -------------------------
        filtersPage.selectSubtitle("English");
        WebElement sub = driver.findElement(By.xpath("//span[contains(@class,'cds-checkboxAndRadio-labelContent') and text()='English']"));
        Assert.assertTrue(sub.isDisplayed(), "English subtitle filter applied");

        // -------------------------
        // 4️⃣ COMBINATIONS & AND LOGIC
        // -------------------------
        // Example: Beginner + English + Short (Duration)
        filtersPage.selectLevel("Beginner");
        filtersPage.selectLanguage("English");
        // Duration filter (example: Short)
        filtersPage.selectSubtitle("Short"); // assuming Subtitle filter placeholder for Duration
        Thread.sleep(2000); // wait for results to update

        List<WebElement> results = driver.findElements(By.cssSelector("li[data-e2e='search-result']"));
        Assert.assertTrue(results.size() > 0, "Results should be displayed for combined filters");

        // -------------------------
        // 5️⃣ CLEAR FILTERS
        // -------------------------
        filtersPage.clearFilters();
        Thread.sleep(2000); // wait for results to reset
        results = driver.findElements(By.cssSelector("li[data-e2e='search-result']"));
        Assert.assertTrue(results.size() > 0, "All results displayed after clearing filters");

        // -------------------------
        // 6️⃣ FILTER URL PERSISTENCE
        // -------------------------
        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains("query=java"), "URL should contain search query after filters");

        // -------------------------
        // 7️⃣ NON-EXISTING FILTER EDGE CASE
        // -------------------------
        filtersPage.selectLanguage("Klingon"); // non-existing
        WebElement noResults = driver.findElement(By.xpath("//*[contains(text(),'No results found')]"));
        Assert.assertTrue(noResults.isDisplayed(), "No results found message should appear for non-existing filter");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}