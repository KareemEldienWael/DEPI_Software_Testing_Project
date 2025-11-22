package tests;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;

public class Login_negativeScenarioWithDataProvider extends TestBase{
	
	HomePage homeObject;
	LoginPage loginObject;
	
	@DataProvider(name="LoginNegativeData")
	public Object[][] getLoginNegativeData(){
		Object[][] loginNegativeData = new Object[][] {
			{"tenixe9065@etramay.com","8$xX9LRVJF.!3yKf"}
		};
		return loginNegativeData;
	}
	
	@Test(priority = 1,dataProvider = "LoginNegativeData")
  public void testLogin_IncorrectEmailOrPassword(String email,String password) throws InterruptedException {
	  homeObject = new HomePage(driver);
	  loginObject = new LoginPage(driver);
	  
	  Thread.sleep(3000);
	  homeObject.openLoginPage();
	  Thread.sleep(3000);
	  Assert.assertEquals(loginObject.loginMessage.getText(), "Log in or create account");
	  
	  Thread.sleep(3000);
	  loginObject.userCanLogin(email,password);

	  Thread.sleep(60000);

	  Assert.assertEquals(loginObject.errorMessage.getText(), "Sorry, we don't recognize that username or password. You can try again or reset your password");
	  Thread.sleep(3000);	  
  }
//  
//  @Test(priority = 2)
//  public void testLogin_InCorrectUsername() throws InterruptedException {
//	  homeObject = new HomePage(driver);
//	  loginObject = new LoginPage(driver);
//	  
//	  Thread.sleep(3000);
//	  homeObject.openLoginPage();
//	  Assert.assertEquals(loginObject.loginMessage.getText(), "Login to your account");
//	  
//	  Thread.sleep(3000);
//	  loginObject.userCanLogin("abdelrahmanosama76511@gmail.com","1234567");
//	  
//	  Assert.assertEquals(loginObject.errorMessage.getText(), "Your email or password is incorrect!");
//	  Thread.sleep(3000);
//  }
}
