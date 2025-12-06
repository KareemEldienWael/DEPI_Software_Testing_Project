package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchPage extends PageBase {

    WebDriverWait wait;
    Actions actions;
    JavascriptExecutor js;

    public SearchPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
    }

    // Locators
    @FindBy(xpath="//input[@placeholder='What do you want to learn?']")
	public
    WebElement searchInput;

    @FindBy(xpath="//button[@aria-label='Search']")
    WebElement searchButton;

    @FindBy(xpath="//ul[contains(@class,'react-autosuggest__suggestions-list')]//li")
    List<WebElement> suggestionList;

    @FindBy(xpath="//h2[contains(text(),'No results found')]")
    WebElement noResultsMessage;

    // Methods
    public void typeKeyword(String keyword){
        wait.until(ExpectedConditions.visibilityOf(searchInput)).clear();
        searchInput.sendKeys(keyword);
    }

    public void pressEnter(){
        searchInput.sendKeys(Keys.ENTER);
    }

    public void clickSearchButton(){
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public void clickFirstSuggestion(){
        wait.until(ExpectedConditions.visibilityOfAllElements(suggestionList));
        if(!suggestionList.isEmpty()){
            suggestionList.get(0).click();
        }
    }

    public boolean isSearchBarVisible(){
        return searchInput.isDisplayed() && searchInput.isEnabled();
    }

    public boolean areSuggestionsVisible(){
        return suggestionList.size() > 0;
    }

    public boolean isNoResultsMessageVisible(){
        try {
            return noResultsMessage.isDisplayed();
        } catch(Exception e){
            return false;
        }
    }

    public String getCurrentURL(){
        return driver.getCurrentUrl();
    }
}
