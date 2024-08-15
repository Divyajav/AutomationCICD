package divgroup.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import divgroup.TestComponents.BaseTest;
import divgroup.TestComponents.Retry;
import divgroup.pageobjects.CartPage;
import divgroup.pageobjects.productcatelog;

public class ErrorValidations extends BaseTest {
@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
public void LoginErrorValidation() throws IOException, InterruptedException {
	
String productName = "ADIDAS ORIGINAL";
landingpage.loginApplication("vdivyaja.s@gmail.com","vdisha@2013");
Assert.assertEquals("Incorrect email  password.",landingpage.getErrorMessage());
	}
@Test
public void ProductErrorValidation() throws InterruptedException {
	String productName = "ADIDAS ORIGINAL";
	productcatelog prodcatelog=landingpage.loginApplication("divyajavanjarapu@gmail.com","Divya@1982");
	List<WebElement>products = prodcatelog.getProductList();
	prodcatelog.addProductToCart(productName);
	Thread.sleep(5000);
	CartPage cartPage = prodcatelog.goToCartPage();
	Thread.sleep(5000);
	Boolean match = cartPage.VerifyProductDisplay("ADIDS ORIGINAL");
	Assert.assertTrue(match);
}
}
