package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SortingPage extends PageBase {
    WebDriverWait wait;
    Actions actions;
    JavascriptExecutor js;

    public SortingPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
    }

    @FindBy(xpath = "//button/div[contains(text(),'Filter & Sort')]")
    WebElement sortDropdown;

    @FindBy(xpath = "//button/span[contains(text(),'View')]")
    WebElement applySortingButton;

    public void openSortDropdown(){
        sortDropdown.click();

//        js.executeScript("arguments[0].scrollIntoView(true);", sortDropdown);
//        actions.moveToElement(sortDropdown).click().perform();
    }

    public void selectSortOption(String option){
        openSortDropdown();
        By sortOption = By.xpath("//span[contains(text(),'" + option + "')]");
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(sortOption));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        actions.moveToElement(element).click().perform();
    }

    public void applySorting(){
        applySortingButton.click();
    }
}
