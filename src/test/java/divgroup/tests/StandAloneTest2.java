package divgroup.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import divgroup.TestComponents.BaseTest;
import divgroup.pageobjects.CartPage;
import divgroup.pageobjects.CheckoutPage;
import divgroup.pageobjects.ConfirmationPage;
import divgroup.pageobjects.LandingPage;
import divgroup.pageobjects.OrderPage;
import divgroup.pageobjects.productcatelog;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest2 extends BaseTest {
	String productName = "ADIDAS ORIGINAL";	
@Test (dataProvider = "getData",groups = {"Purchase"})
// public void StandAlone(String email,String password,String productName) throws IOException, InterruptedException 
public void StandAlone(HashMap<String,String> input) throws IOException, InterruptedException 

{
productcatelog prodcatelog=landingpage.loginApplication(input.get("email"),input.get("password"));
List<WebElement>products = prodcatelog.getProductList();
prodcatelog.addProductToCart(input.get("product"));
Thread.sleep(5000);
CartPage cartPage = prodcatelog.goToCartPage();
Thread.sleep(5000);
Boolean match = cartPage.VerifyProductDisplay(productName);
Assert.assertTrue(match);
CheckoutPage checkoutPage = cartPage.goToCheckout();
checkoutPage.selectCountry("India");
ConfirmationPage confirmationPage = checkoutPage.submitOrder();
String confirmMessage = confirmationPage.getConfirmationMessage();
Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
@Test (dependsOnMethods = {"StandAlone"})
public void OrderHistoryTest()
{
	productcatelog prodcatelog=landingpage.loginApplication("vdivyaja.s@gmail.com","vidisha@2013");
	OrderPage orderPage = prodcatelog.goToOrdersPage();
	Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
}




@DataProvider
public Object[][] getData() throws IOException
{ 
List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//divgroup//data//Purchaseorder.json");
return new Object[][] {{data.get(0)},{data.get(1)}};
}
}

// @DataProvider
//public Object[[] getData()
   //           { return new Object[][] { { "vdivyaja.s2gmail.com","vidisha@2013"}, { "divyajavanjarapu@gmail.com","Divya@1982"}}
	
              

/* HashMap<String, String> map = new HashMap<String,String>();
map.put("email", "vdivyaja.s@gmail.com");
map.put("password", "vidisha@2013");
map.put("product", "ADIDAS ORIGINAL");

HashMap<String, String> map1 = new HashMap<String,String>();
map.put("email", "divyajavanjarapu@gmail.com");
map.put("password", "Divya@1982");
map.put("product", "ZARA COAT 3");*/

