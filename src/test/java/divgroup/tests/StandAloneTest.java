package divgroup.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import divgroup.pageobjects.LandingPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
//WebDriverManager.chromedriver().setup();
		String productName = "ADIDAS ORIGINAL";
WebDriver driver = new ChromeDriver();
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
driver.get("https://rahulshettyacademy.com/client/");
LandingPage landingpage = new LandingPage(driver);
driver.findElement(By.id("userEmail")).sendKeys("vdivyaja.s@gmail.com");
driver.findElement(By.id("userPassword")).sendKeys("vidisha@2013");
driver.findElement(By.id("login")).click();
WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
// filter stream
WebElement prod = products.stream().filter(product->
product.findElement(By.cssSelector("b")).getText().equals("ADIDAS ORIGINAL")).findFirst().orElse(null);
prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
// image rotating 
//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));

wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
Thread.sleep(5000);
driver.findElement(By.cssSelector(".btn.btn-custom[routerlink='/dashboard/cart']")).click();
List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));
// anyMatch to return boolean - stream
Boolean match = cartproducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productName));
Assert.assertTrue(match);
driver.findElement(By.cssSelector(".totalRow button")).click();
Actions a = new Actions(driver);
// a.sendKeys(identifywebelement sendtext)
a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
driver.findElement(By.xpath("//button[contains(@class, 'ta-item')] [2]")).click();
driver.findElement(By.cssSelector(".action__submit")).click();
String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
driver.close();
 



	}

}
