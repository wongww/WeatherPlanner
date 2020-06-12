package features.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertTrue;

/**
 * Step definitions for Cucumber tests.
 */

public class StepDefinitionsNavBar {
	private static final String ROOT_URL = "https://localhost:8443/"; //Start on any page

	ChromeOptions options = new ChromeOptions();
	private WebDriver driver = new ChromeDriver();

	@Given("I am on any of the pages, using https")
	public void i_am_on_any_page() {
		options.addArguments("--ignore-certificate-errors");
		driver = new ChromeDriver(options);
		driver.get(ROOT_URL);
	}

	@When("I click the {string} link")
	public void i_click_home_link(String string) {
		driver.findElement(By.linkText(string)).click();
	}

	@Then("I should see the header {string}")
	public void i_should_see_correct_page(String string) {
		assertTrue(driver.findElement(By.tagName("title")).getAttribute("innerText").contains(string));
	}
	
	@After()
	public void after() {
		driver.quit();
	}
}