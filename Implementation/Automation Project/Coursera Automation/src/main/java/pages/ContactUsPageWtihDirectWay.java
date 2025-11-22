package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactUsPageWtihDirectWay extends PageBase {

	public ContactUsPageWtihDirectWay(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(name="name")
	WebElement nameTxt;
	
	@FindBy(name="email")
	WebElement emailTxt;
	
	@FindBy(name="subject")
	WebElement subjectTxt;
	
	@FindBy(id="message")
	WebElement messageTxt;
	
	@FindBy(name="upload_file")
	WebElement uploadFileBtn;
	
	@FindBy(name="submit")
	WebElement submitBtn;
	
	@FindBy(xpath="//*[@id=\"contact-page\"]/div[2]/div[1]/div/h2")
	public WebElement contactUsMessage;
	
	@FindBy(css="#contact-page > div.row > div.col-sm-8 > div > div.status.alert.alert-success")
	public WebElement successMessage;
	
	public void userCanContactUs(String name,String email,String subject
			,String message) throws InterruptedException {
		nameTxt.sendKeys(name);
		emailTxt.sendKeys(email);
		subjectTxt.sendKeys(subject);
		messageTxt.sendKeys(message);
		
		String filePath = System.getProperty("user.dir")+"\\Images\\Mohamed Salah.jpg";
		
		uploadFileBtn.sendKeys(filePath);
		Thread.sleep(2000);
		
		submitBtn.click();
		System.out.println(filePath);
	}
	
}
