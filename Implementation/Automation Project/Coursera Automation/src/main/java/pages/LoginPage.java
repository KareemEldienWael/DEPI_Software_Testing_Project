package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageBase {

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(name="email")
	WebElement emailTxt;
	
	@FindBy(name="password")
	WebElement passwordTxt;
	
	@FindBy(xpath="//button[contains(text(),'Continue') or contains(@data-track-component,'login_button')]")
	WebElement continueBtn;
	
	@FindBy(xpath="//button[contains(text(),'Next') or contains(text(),'Sign in')]")
	WebElement nextBtn;
	
	@FindBy(xpath="//h1[contains(text(), 'Log in or create account')]")
	public WebElement loginMessage;
	
	@FindBy(xpath="//form/div[2]/div/div[2]")
	public WebElement errorMessage;
	
//	@FindBy(linkText = "Logout")
//	public WebElement logoutBtn;
	
	public void userCanLogin(String email,String password) throws InterruptedException {
		emailTxt.sendKeys(email);
		continueBtn.click();
		
		Thread.sleep(3000);
		passwordTxt.sendKeys(password);
		nextBtn.click();
		
		
	}
	
//	public void userCanLogout() {
//		logoutBtn.click();
//	}
}
