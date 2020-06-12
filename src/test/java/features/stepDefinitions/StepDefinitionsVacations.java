package features.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
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
public class StepDefinitionsVacations {
	private static final String ROOT_URL = "https://localhost:8443/VacationPlanning.jsp";
	
	ChromeOptions options = new ChromeOptions();
	
	private WebDriver driver = null; //= new ChromeDriver();
	
	@Before
	public void setup()
	{
		options.addArguments("--disable-gpu", "--ignore-certificate-errors"); //"--headless", 
		options.setHeadless(true);
		driver = new ChromeDriver(options);
	}
	
	@Given("I am on the vacation page using distance criteria")
	public void i_am_on_the_vacation_page() {
		driver.get("https://localhost:8443");
		driver.findElement(By.name("username")).sendKeys("test2");
		driver.findElement(By.name("password")).sendKeys("test");
		driver.findElement(By.name("password")).submit();
		driver.switchTo().alert().accept();
		driver.get(ROOT_URL);
	}
	
	@When("I vacation search with inputs {string} {string} {string} {string} {string}")
	public void i_click_the_button_with_inputs(String low, String high, String lat, String lon, String radius) {
	    // Write code here that turns the phrase above into concrete actions
	    driver.findElement(By.name("lowrange")).sendKeys(low);
	    driver.findElement(By.name("highrange")).sendKeys(high);
	    
	    JavascriptExecutor jse = (JavascriptExecutor)driver;
	    jse.executeScript("document.getElementsByName('lat')[0].setAttribute('type', 'text');");
	    jse.executeScript("document.getElementsByName('long')[0].setAttribute('type', 'text');");
	    
	    driver.findElement(By.name("lat")).sendKeys(lat);
	    driver.findElement(By.name("long")).sendKeys(lon);
	    driver.findElement(By.name("radius")).sendKeys(radius);
	    
	    driver.findElement(By.name("radius")).submit();
	    
	}
	@Then("I should see the cities that are no farther than {string}")
	public void i_should_see_the_cities_that_are_no_farther_than(String string) {
		List<WebElement> elements = driver.findElements(By.name("dist"));
		int dist = Integer.parseInt(string);
		
		for (WebElement e : elements){
			int found = Integer.parseInt(e.getAttribute("innerHTML"));
			if (found > dist){
				Assert.assertTrue(false);
			}
		}
		Assert.assertTrue(true);
	    
	}

	

	@After()
	public void after() {
		driver.quit();
	}
}
