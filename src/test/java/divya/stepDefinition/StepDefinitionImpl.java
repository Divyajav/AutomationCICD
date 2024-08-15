package divya.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import divgroup.TestComponents.BaseTest;
import divgroup.pageobjects.CartPage;
import divgroup.pageobjects.CheckoutPage;
import divgroup.pageobjects.ConfirmationPage;
import divgroup.pageobjects.LandingPage;
import divgroup.pageobjects.productcatelog;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {
	public LandingPage landingpage;
	public productcatelog prodcatelog;
	public ConfirmationPage confirmationPage;
	@Given ("Given I landed on Ecommerce Page\n")
	
	public void I_landed_on_Ecommerce_Page() throws IOException
	
	{
		landingpage = launchApplication();

    }
	//regular expression
	
	@Given("^Logged in with username (.+)  and password  (.+)$")
	public void logged_in_username_and_password(String username, String password)
	{
	 prodcatelog=landingpage.loginApplication(username,password);

    }
	@When("^When I add product (.+) to Cart$")
	public void i_add_product_to_cart(String productName)
	{
		List<WebElement>products = prodcatelog.getProductList();
		prodcatelog.addProductToCart(productName);
	}
	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) throws InterruptedException
	{
		
		CartPage cartPage = prodcatelog.goToCartPage();
		Thread.sleep(5000);
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		confirmationPage = checkoutPage.submitOrder();
		
	}
	@Then("{string} message is displayed  on ConfirmationPage")
	public void message_displayed_ConfirmationPage(String string)
	{
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("string"));
		driver.close();
		}
	@Then (("{string} message is displayed "))
	public void something_message_is_displayed(String strAgr1)
	{
		Assert.assertEquals(strAgr1,landingpage.getErrorMessage());
		driver.close();
	}
			
	
			
	
}
