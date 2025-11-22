package pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage extends PageBase {

	public RegisterPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(name="name")
	WebElement nameTxt;
	
	@FindBy(name="email")
	List<WebElement> emailsTxt; 
	
	@FindBy(css="#form > div > div > div:nth-child(3) > div > form > button")
	WebElement submitBtn;
	
	@FindBy(id="id_gender1")
	WebElement genderBtn;
	
	@FindBy(id="password")
	WebElement passwordTxt;
	
	@FindBy(id="days")
	WebElement daysList;
	
	@FindBy(id="months")
	WebElement monthsList;
	
	@FindBy(id="years")
	WebElement yearsList;
	
	@FindBy(id="newsletter")
	WebElement newsLetterCheckBox;
	
	@FindBy(id="optin")
	WebElement specialOffersCheckBox;
	
	@FindBy(id="first_name")
	WebElement firstNameTxt;
	
	@FindBy(id="last_name")
	WebElement lastNameTxt;
	
	@FindBy(id="company")
	WebElement companyTxt;
	
	@FindBy(id="address1")
	WebElement addressTxt1;
	
	@FindBy(id="address2")
	WebElement addressTxt2;
	
	@FindBy(id="country")
	WebElement countryList;
	
	@FindBy(id="state")
	WebElement stateTxt;
	
	@FindBy(id="city")
	WebElement cityTxt;
	
	@FindBy(id="zipcode")
	WebElement zipcodeTxt;
	
	@FindBy(id="mobile_number")
	WebElement mobileNumberTxt;
	
	@FindBy(xpath="//*[@id=\"form\"]/div/div/div/div[1]/form/button")
	WebElement createAccountBtn;	
	
	@FindBy(linkText = "Continue")
	WebElement continueBtn;
	
	@FindBy(linkText = "Delete Account")
	WebElement deleteAccoutBtn;
	
	@FindBy(xpath="//*[@id=\"form\"]/div/div/div[3]/div/h2")
	public WebElement newUserMessage;
	
	@FindBy(xpath="//*[@id=\"form\"]/div/div/div/div[1]/h2/b")
	public WebElement enterAccountMessage;
	
	@FindBy(xpath="//*[@id=\"form\"]/div/div/div/h2/b")
	public WebElement successMessage;
	
	@FindBy(linkText = "Logout")
	public WebElement logoutBtn;
	
	@FindBy(xpath="//*[@id=\"form\"]/div/div/div/h2/b")
	public WebElement deleteSuccessMessage;
	
	@FindBy(css="#form > div > div > div:nth-child(3) > div > form > p")
	public WebElement errorMessage;
	
	public void userCanSignUpNewUser(String name,String email) {
		nameTxt.sendKeys(name);
		emailsTxt.get(1).sendKeys(email);
		submitBtn.click();
	}
	
	public void userCanEnterAccountInformation(String password,String day,int month,String year,
			String firstName,String lastName,String company,
			String address1, String address2,String country,String state,
			String city,String zipCode,String telephoneNumber) {
		genderBtn.click();
		passwordTxt.sendKeys(password);
		
		Select makeDaysList = new Select(daysList);
		Select makeMonthsList = new Select(monthsList);
		Select makeYearsList = new Select(yearsList);
		
		makeDaysList.selectByVisibleText(day);
		makeMonthsList.selectByIndex(month);
		makeYearsList.selectByValue(year);
		
		newsLetterCheckBox.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("const newsLetter = document.getElementById('newsletter');\r\n"
				+ "newsLetter.scrollIntoView(\r\n"
				+ "    {\r\n"
				+ "        behaviour : \"smooth\"\r\n"
				+ "    }\r\n"
				+ ");");
		

		String title = (String) js.executeScript("return document.title");
		System.out.println(title);
		
		specialOffersCheckBox.click();
		
		firstNameTxt.sendKeys(firstName);
		lastNameTxt.sendKeys(lastName);
		companyTxt.sendKeys(company);
		
		addressTxt1.sendKeys(address1);
		addressTxt2.sendKeys(address2);
		
		Select makeCountryList = new Select(countryList);
		makeCountryList.selectByContainsVisibleText(country);
		
		stateTxt.sendKeys(state);
		cityTxt.sendKeys(city);
		
		zipcodeTxt.sendKeys(zipCode);
		mobileNumberTxt.sendKeys(telephoneNumber);
		
		createAccountBtn.click();
	}
	
	public void useCanContinueAccount() {
		continueBtn.click();
	}
	
	public void userCanDeleteAccount() {
		deleteAccoutBtn.click();
	}
	
	public void userCanLogout() {
		logoutBtn.click();
	}
}
