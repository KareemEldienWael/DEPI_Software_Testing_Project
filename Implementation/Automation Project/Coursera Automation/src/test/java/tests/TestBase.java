package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;

public class TestBase {
	WebDriver driver;
	String baseURL = "https://www.coursera.org/";
	
  @BeforeTest
  public void openBrowser() {
	  driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.navigate().to(baseURL);
  }

  @AfterTest
  public void closeBrowser() {
	  driver.quit();
  }

}
