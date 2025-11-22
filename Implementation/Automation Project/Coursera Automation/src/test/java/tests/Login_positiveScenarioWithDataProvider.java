package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;

public class Login_positiveScenarioWithDataProvider extends TestBase{
	HomePage homeObject;
	LoginPage loginObject;
	
	@DataProvider(name="loginHappyData")
	public Object[][] getLoginHappyData(){
		Object[][] loginPositiveData = new Object[][]{
				{"tenixe9065@etramay.com","8$xX9LRVJF.!3yK"},
		};
		return loginPositiveData;
	}
	
  @Test(dataProvider = "loginHappyData")
  public void testLogin_CorrectUsernameAndPassword(String email,String password) throws InterruptedException {
	  homeObject = new HomePage(driver);
	  loginObject = new LoginPage(driver);
	  
	  Thread.sleep(3000);
	  homeObject.openLoginPage();
	  Thread.sleep(3000);
	  Assert.assertEquals(loginObject.loginMessage.getText(), "Log in or create account");
	  
	  Thread.sleep(3000);
	  loginObject.userCanLogin(email,password);
	  
//	  Assert.assertTrue(loginObject.logoutBtn.isDisplayed());
	  Thread.sleep(60000);
	  
//	  loginObject.userCanLogout();
  }

}
