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

public class FiltersPage extends PageBase {

    WebDriverWait wait;
    Actions actions;
    JavascriptExecutor js;

    public FiltersPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
    }

    // Locators باستخدام @FindBy
    @FindBy(xpath = "//button/div[contains(text(),'Filter & Sort')]")
    WebElement filterDropdown;

    @FindBy(xpath="//button/div/div/span[contains(text(),'Language')]")
    WebElement languageFilterButton;

    @FindBy(xpath="//button[contains(@aria-label,'Level filter')]")
    WebElement levelFilterButton;

    @FindBy(xpath="//button[contains(@aria-label,'Subtitles filter')]")
    WebElement subtitleFilterButton;

    @FindBy(xpath="//button[text()='Clear']")
    WebElement clearFiltersButton;

    @FindBy(xpath = "//button/span[contains(text(),'View')]")
    WebElement applyFilterButton;

    public void startFilter()
    {
        filterDropdown.click();
    }

    // Method لاختيار لغة
    public void selectLanguage(String language){
        languageFilterButton.click();

        By languageOption = By.xpath("//span[contains(@class,'cds-checkboxAndRadio-labelContent') and contains(text(),'" + language + "')]");
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(languageOption));
        option.click();
    }

    // Method لاختيار مستوى
    public void selectLevel(String level){
        wait.until(ExpectedConditions.elementToBeClickable(levelFilterButton));
        js.executeScript("arguments[0].scrollIntoView(true);", levelFilterButton);
        actions.moveToElement(levelFilterButton).click().perform();

        By levelOption = By.xpath("//span[contains(@class,'cds-checkboxAndRadio-labelContent') and contains(text(),'" + level + "')]");
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(levelOption));
        js.executeScript("arguments[0].scrollIntoView(true);", option);
        actions.moveToElement(option).click().perform();
    }

    // Method لاختيار Subtitle
    public void selectSubtitle(String subtitle){
        wait.until(ExpectedConditions.elementToBeClickable(subtitleFilterButton));
        js.executeScript("arguments[0].scrollIntoView(true);", subtitleFilterButton);
        actions.moveToElement(subtitleFilterButton).click().perform();

        By subtitleOption = By.xpath("//span[contains(@class,'cds-checkboxAndRadio-labelContent') and contains(text(),'" + subtitle + "')]");
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(subtitleOption));
        js.executeScript("arguments[0].scrollIntoView(true);", option);
        actions.moveToElement(option).click().perform();
    }

    // Method لمسح كل الفلاتر
    public void clearFilters(){
        wait.until(ExpectedConditions.elementToBeClickable(clearFiltersButton));
        js.executeScript("arguments[0].scrollIntoView(true);", clearFiltersButton);
        actions.moveToElement(clearFiltersButton).click().perform();
    }

    public void applyFilter(){
        applyFilterButton.click();
    }

}
