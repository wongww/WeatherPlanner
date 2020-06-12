package features.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;

/**
 * Step definitions for Cucumber tests.
 */
public class StepDefinitionsActivities {
	private static final String ROOT_URL = "https://localhost:8443/ActivitiesPlanning.jsp";
	
	ChromeOptions options = new ChromeOptions();
	
	private WebDriver driver = null; //= new ChromeDriver();
	
	@Before
	public void setup()
	{
		options.addArguments("--disable-gpu", "--ignore-certificate-errors"); //"--headless", 
		options.setHeadless(true);
		driver = new ChromeDriver(options);
	}
	
	@Given("I am on the activities page")
	public void i_am_on_the_activites_page() {
		
		driver.get("https://localhost:8443");
		driver.findElement(By.name("username")).sendKeys("test2");
		driver.findElement(By.name("password")).sendKeys("test");
		driver.findElement(By.name("password")).submit();
		driver.switchTo().alert().accept();
		
		driver.get(ROOT_URL);
		
	}
	
	
	@When("I activity search with inputs {string} {string} {string} {string}")
	public void i_search_with_inputs(String activity, String lat, String lon, String radius) {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.name("activity")).sendKeys(activity);
	    driver.findElement(By.name("radius")).sendKeys(radius);
	    
	    JavascriptExecutor jse = (JavascriptExecutor)driver;
	    jse.executeScript("document.getElementsByName('lat')[0].setAttribute('type', 'text');");
	    jse.executeScript("document.getElementsByName('long')[0].setAttribute('type', 'text');");
	    driver.findElement(By.name("lat")).sendKeys(lat);
	    driver.findElement(By.name("long")).sendKeys(lon);
	    driver.findElement(By.name("radius")).submit();
	}
	

	@After()
	public void after() {
		driver.quit();
	}
	
}
