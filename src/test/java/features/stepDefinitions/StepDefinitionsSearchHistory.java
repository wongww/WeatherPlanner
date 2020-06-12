package features.stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step definitions for Cucumber tests.
 */
public class StepDefinitionsSearchHistory {
	private static final String ROOT_URL = "https://localhost:8443";
	ChromeOptions options = new ChromeOptions();
	private WebDriver driver = new ChromeDriver();

	@Given("I am on the HomePage, using https")
	public void i_am_on_a_page_search_history_tests() {
		options.addArguments("--ignore-certificate-errors");
		options.setHeadless(true);
		driver = new ChromeDriver(options);
		driver.get(ROOT_URL);
		driver.findElement(By.name("username")).sendKeys("test2");
		driver.findElement(By.name("password")).sendKeys("test");
		driver.findElement(By.name("password")).submit();
		driver.switchTo().alert().accept();
		driver.switchTo().defaultContent();
		driver.findElement(By.linkText("Homepage")).click();
	}

	@When("I search {string}")
	public void i_search_home(String string) {
		driver.findElement(By.name("searchbar")).sendKeys(string);
		driver.findElement(By.name("searchbar")).submit();
	}
	
	@When("I search {string} {string} {string} {string}")
	public void i_search_home_full(String string, String string_, String _string, String stRing) {
		driver.findElement(By.name("searchbar")).sendKeys(string);
		driver.findElement(By.name("searchbar")).submit();
		driver.findElement(By.linkText("Vacation Planning")).click();
		driver.findElement(By.linkText("Homepage")).click();
		driver.findElement(By.name("searchbar")).sendKeys(string_);
		driver.findElement(By.name("searchbar")).submit();
		driver.findElement(By.linkText("Vacation Planning")).click();
		driver.findElement(By.linkText("Homepage")).click();
		driver.findElement(By.name("searchbar")).sendKeys(_string);
		driver.findElement(By.name("searchbar")).submit();
		driver.findElement(By.linkText("Vacation Planning")).click();
		driver.findElement(By.linkText("Homepage")).click();
		driver.findElement(By.name("searchbar")).sendKeys(stRing);
		driver.findElement(By.name("searchbar")).submit();
	}

	@When("I close the browser")
	public void close_open(){
		driver.close();
		options.addArguments("--ignore-certificate-errors");
		options.setHeadless(true);
		driver = new ChromeDriver(options);
		driver.get(ROOT_URL);
		driver.findElement(By.name("username")).sendKeys("test2");
		driver.findElement(By.name("password")).sendKeys("test");
		driver.findElement(By.name("password")).submit();
		driver.switchTo().alert().accept();
		driver.findElement(By.linkText("Homepage")).click();
		driver.findElement(By.name("searchbar")).sendKeys("notacity");
		driver.findElement(By.name("searchbar")).submit();
	}
	@When("I click {string}")
	public void click_button(String string) {
		driver.findElement(By.name("searchbar")).sendKeys(string);
		driver.findElement(By.name("searchbar")).submit();
		driver.manage().window().maximize();
		if(driver.findElement(By.id("prevSearch1city")).getText().equals(string)) {
			driver.findElement(By.id("prevSearch1city")).click();
		}
		else if(driver.findElement(By.id("prevSearch2city")).getText().equals(string)) {
			driver.findElement(By.id("prevSearch2city")).click();
		}
		else if(driver.findElement(By.id("prevSearch3city")).getText().equals(string)) {
			driver.findElement(By.id("prevSearch3city")).click();
		}
		else{
			driver.findElement(By.id("prevSearch4city")).click();
		}
	}
	@Then("I should see {string} in the list")
	public void history_home(String string) {
		int i = 0;
		if(driver.findElement(By.id("prevSearch1city")).getText().equals(string)) {
			i = 1;
		}
		else if(driver.findElement(By.id("prevSearch2city")).getText().equals(string)) {
			i = 1;
		}
		else if(driver.findElement(By.id("prevSearch3city")).getText().equals(string)) {
			i = 1;
		}
		else if(driver.findElement(By.id("prevSearch4city")).getText().equals(string)){
			i = 1;
		}
		assertEquals(1, i);
	}
	
	@Then("I should see {string} in result")
	public void result_after_clicking(String string) {
		System.out.println(driver.findElement(By.id("dateandtime")).getText());
		assertTrue(driver.findElement(By.id("dateandtime")).getText().contains(string));
	}
	
	@Then("I should see {string} {string} {string} {string} in the list")
	public void history_home_full(String string, String string_, String _string, String stRing) {
		assertEquals(stRing, driver.findElement(By.id("prevSearch1city")).getText());
		assertEquals(_string, driver.findElement(By.id("prevSearch2city")).getText());
		assertEquals(string_, driver.findElement(By.id("prevSearch3city")).getText());
		assertEquals(string, driver.findElement(By.id("prevSearch4city")).getText());
	}
	
	@Then("I should not see {string} in the list")
	public void history_home_notacity(String string) {
		assertNotEquals(string, driver.findElement(By.id("prevSearch1city")).getText());
		assertNotEquals(string, driver.findElement(By.id("prevSearch2city")).getText());
		assertNotEquals(string, driver.findElement(By.id("prevSearch3city")).getText());
		assertNotEquals(string, driver.findElement(By.id("prevSearch4city")).getText());
	}
	
	@After()
	public void after() {
		driver.quit();
	}
}
