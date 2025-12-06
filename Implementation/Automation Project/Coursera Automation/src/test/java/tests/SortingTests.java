package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SortingPage;

import java.time.Duration;

public class SortingTests {
    WebDriver driver;
    SortingPage sortingPage;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.coursera.org/search?query=java");
        sortingPage = new SortingPage(driver);
    }

    @Test
    public void testSortByBestMatch() throws InterruptedException {
        Thread.sleep(2000);
        sortingPage.selectSortOption("Best Match");
        Thread.sleep(2000);
        sortingPage.applySorting();
        Thread.sleep(2000);
        
    }

    @Test
    public void testSortByNewest() throws InterruptedException {
        Thread.sleep(2000);
        sortingPage.selectSortOption("Newest");
        Thread.sleep(2000);
        sortingPage.applySorting();
        Thread.sleep(2000);
       
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
