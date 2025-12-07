package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ExternalLinksPage;

import java.time.Duration;
import java.util.List;

public class ExternalLinksTests {

    WebDriver driver;
    ExternalLinksPage externalLinksPage;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        driver.get("https://www.coursera.org/");
        externalLinksPage = new ExternalLinksPage(driver);
    }

    @Test
    public void verifyFooterLinksExist(){
        List<WebElement> links = externalLinksPage.getAllFooterLinks();
        Assert.assertTrue(links.size() > 0, "Footer links should exist");
    }

    @Test
    public void verifyAllExternalLinksAreClickable(){
        List<WebElement> links = externalLinksPage.getAllFooterLinks();

        Assert.assertTrue(links.size() > 0, "No footer links found");

        for(WebElement link : links){
            String text = externalLinksPage.getLinkText(link);
            String url = externalLinksPage.getHref(link);

            Assert.assertNotNull(url, "Link [" + text + "] has no href attribute");
            Assert.assertFalse(url.isEmpty(), "Link [" + text + "] contains an empty URL");
        }
    }

    @Test
    public void verifyExternalLinksOpenAndLoadCorrectly() throws InterruptedException {

        List<WebElement> links = externalLinksPage.getAllFooterLinks();
        Assert.assertTrue(links.size() > 0, "No footer links present");

        WebElement firstLink = links.get(0);
        String linkText = externalLinksPage.getLinkText(firstLink);
        String href = externalLinksPage.getHref(firstLink);

        firstLink.click();

        //Thread.sleep(5000);

        //// Switch to new tab
        //externalLinksPage.switchToTab(1);

        String newURL = driver.getCurrentUrl();

        Assert.assertTrue(newURL.contains(href.split("//")[1].split("/")[0]),
                "Opened link URL should match original domain");

        //// Close tab and return
        //externalLinksPage.closeTab();
        //externalLinksPage.switchToTab(0);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}