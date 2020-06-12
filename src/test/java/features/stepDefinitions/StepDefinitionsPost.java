package features.stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionsPost {
	ChromeOptions options = new ChromeOptions();
	WebDriver driver;
	String URL = "https://localhost:8443/";
	@Given("I am on the Activity Page")
	public void givenOnHomePage() {
		options.addArguments("--ignore-certificate-errors");
		options.setHeadless(true);
		driver = new ChromeDriver(options);
		driver.get("https://localhost:8443");
		driver.findElement(By.name("username")).sendKeys("test2");
		driver.findElement(By.name("password")).sendKeys("test");
		driver.findElement(By.name("password")).submit();
		driver.switchTo().alert().accept();
		driver.get(URL + "ActivitiesPlanning.jsp");
	}
    @When("I search for the weather")
    public void searchWeather() {
		driver.findElement(By.name("activity")).sendKeys("running");
		driver.findElement(By.name("radius")).sendKeys("10");
		driver.findElement(By.id("activityform")).submit();
    }
    @Then("I do not see my parameters passed in the URL")
    public void checkURL() {
    	Assert.assertEquals("https://localhost:8443/ActivityPlanningController", driver.getCurrentUrl());
    }
    @After()
    public void after(){
    	if(driver != null) {
    		driver.quit();
    	}
    }
	
	
}
