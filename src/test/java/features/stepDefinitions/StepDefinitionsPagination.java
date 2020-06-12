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
import org.junit.Assert;
/**
* Step definitions for Cucumber tests.
*/
public class StepDefinitionsPagination {
	private static final String URL = "https://localhost:8443";
	
	ChromeOptions options = new ChromeOptions();
	
	private WebDriver driver = null; //= new ChromeDriver();
	private String pageNum = null; 
	@Before
	public void setup()
	{
		options.addArguments("--disable-gpu", "--ignore-certificate-errors"); //"--headless",
		//options.setHeadless(true);
		driver = new ChromeDriver(options);
	}
	
	@Given("I am on the vacation page")
	public void i_am_on_the_vacation_page() {
		driver.get(URL);
				driver.findElement(By.name("username")).sendKeys("test2");
				driver.findElement(By.name("password")).sendKeys("test");
				driver.findElement(By.name("password")).submit();
				driver.switchTo().alert().accept();
				driver.switchTo().defaultContent();
				driver.get("https://localhost:8443/VacationPlanning.jsp");				
	}
	@When("I vacations search with inputs {string} {string} {string} {string} {string}")
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
	@Then("I should see paginated results")
	public void paginationTest() {
		WebElement temp = driver.findElement(By.id("pagination"));
		Assert.assertNotEquals(null, temp);
	}
	@And("I click on another button")
	public void numberTest(){
		pageNum = driver.findElement(By.className("clicked")).getText(); 
		driver.findElement(By.id("pagerpgNext")).click();
		//Assert.assertEquals(driver.findElement(By.className("clicked")).getText(),driver.findElement(By.className("pg-normal") )
		
	}
	@Then("I see the next page")
	public void nextPg() {
		int i = Integer.parseInt(pageNum);
		pageNum = driver.findElement(By.className("clicked")).getText(); 
		int j = Integer.parseInt(pageNum);
		Assert.assertEquals(i+1,j);
	}
	@After()
	public void after() {
		driver.quit();
	}
}