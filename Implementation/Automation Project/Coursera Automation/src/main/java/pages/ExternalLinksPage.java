package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ExternalLinksPage {

    WebDriver driver;

    // Footer links
    By footerLinksLocator = By.cssSelector("footer a");

    public ExternalLinksPage(WebDriver driver){
        this.driver = driver;
    }

    // Return all footer link elements
    public List<WebElement> getAllFooterLinks(){
        return driver.findElements(footerLinksLocator);
    }

    // Get link URL from element
    public String getHref(WebElement link){
        return link.getAttribute("href");
    }

    // Get link text
    public String getLinkText(WebElement link){
        return link.getText();
    }

    // Click link and switch to new tab
    public void openLinkInNewTab(WebElement link){
        link.click();
    }

    // Returns list of open tabs IDs
    public List<String> getTabs(){
        return new ArrayList<>(driver.getWindowHandles());
    }

    // Switch to tab by index
    public void switchToTab(int index){
        List<String> tabs = getTabs();
        driver.switchTo().window(tabs.get(index));
    }

    // Close current tab
    public void closeTab(){
        driver.close();
    }
}