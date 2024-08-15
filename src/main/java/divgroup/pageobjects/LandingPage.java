package divgroup.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import divgroup.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
@FindBy(id="userEmail")
WebElement userEmail;

@FindBy(id="userPassword")
WebElement PasswordEle;

@FindBy(id="login")
WebElement submit;
@FindBy(css ="[class*='flyInOut']" )
WebElement errorMessage;
public productcatelog loginApplication(String email,String password)
{ 
	userEmail.sendKeys(email);
    PasswordEle.sendKeys(password);
    submit.click();
    productcatelog prodcatelog = new productcatelog(driver);
    return prodcatelog;
}
public String getErrorMessage() {
	waitForWebElementToAppear(errorMessage);
	return errorMessage.getText();
}

public void goTo()
{
	driver.get("https://rahulshettyacademy.com/client/");
}

}