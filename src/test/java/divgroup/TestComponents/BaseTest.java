package divgroup.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import divgroup.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver; //global variable
	public LandingPage landingpage; // global variable declared as public so other classes can access
	public WebDriver initializeDriver() throws IOException
	
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream
(System.getProperty("user.dir")+"//src//main//java//divgroup//resources//GlobalData.properties");
		prop.load(fis);
		// to read from maven command if null take from global properties file
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
		 //String browserName = prop.getProperty("browser");
		if (browserName.contains("chrome"))
		{ChromeOptions options = new ChromeOptions();
		//WebDriverManager.chromedriver().setup();
		if(browserName.contains("headless")) 
		{
		options.addArguments("headless");
		}
	    driver = new ChromeDriver(options);
		driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if(browserName.equalsIgnoreCase  ("Firefox")){
			 driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	
		public  List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
		{   // read json to string
			@SuppressWarnings("deprecation")
			String jsonContent = FileUtils.readFileToString(new File(filePath));
			// convert string to hashMap , need Jackson DataBind dependencies
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){
		});
		return data;
		
				}
		public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
		{
			TakesScreenshot ts = (TakesScreenshot)driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File file = new File(System.getProperty("user.dir")+"//reports//" + testCaseName + ".png");
			FileUtils.copyFile(source,file);
			return System.getProperty("user.dir")+"//reports//" + testCaseName + ".png";
		}

	@BeforeMethod(alwaysRun =true)
	public LandingPage launchApplication() throws IOException
	{
		driver = initializeDriver();
		 landingpage = new LandingPage(driver);
		landingpage.goTo();
		return landingpage;
	}
	@AfterMethod(alwaysRun =true)
	
	public void tearDown()
	{
		driver.close();

	}
}
