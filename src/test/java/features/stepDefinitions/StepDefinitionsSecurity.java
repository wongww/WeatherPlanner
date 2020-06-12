package features.stepDefinitions;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import csci310.jdbcController;

import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.junit.Assert;

public class StepDefinitionsSecurity{
	private static final String SOURCES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
	ChromeOptions options = new ChromeOptions();
	WebDriver driver;
	String URL = "https://localhost:8443";
	@When("the user enters the URL")
	public void enterURL() {
		
		options.addArguments("--ignore-certificate-errors");
		options.setHeadless(true);
		driver = new ChromeDriver(options);
		driver.get(URL);
	}
	@Then("the user is connected to the web application using HTTPS")
	public void testURL() {
		Assert.assertEquals("Login",driver.getTitle());
	}
	@Given("the user connects to home page and is not logged in")
	public void loadHomePage() {
		options.addArguments("--ignore-certificate-errors");
		options.setHeadless(true);
		driver = new ChromeDriver(options);
		driver.get(URL);
	}
	@Then("the user is connected to the login page through HTTPS")
	public void testHomePage() {
		Assert.assertEquals("Login",driver.getTitle());
	}
	
	@Given("the user enters the URL http:\\/\\/localhost:8443")
	public void loadBadPage() {
		options.addArguments("--ignore-certificate-errors");
		options.setHeadless(true);
		driver = new ChromeDriver(options);
		driver.get("http://localhost:8443");
	}
	@Then("the user is not connected to the web application")
	public void testBadPage() {
		Assert.assertEquals("", driver.getTitle());
	}
	@Given("the user logs in with valid credentials")
	public void validLogin() {
		options.addArguments("--ignore-certificate-errors");
		options.setHeadless(true);
		driver = new ChromeDriver(options);
		driver.get("https://localhost:8443");
		driver.findElement(By.name("username")).sendKeys("test2");
		driver.findElement(By.name("password")).sendKeys("test");
		driver.findElement(By.name("password")).submit();
		driver.switchTo().alert().accept();
	}
	@Then("the user is logged in")
	public void loggedIn() {
		By locator = By.id("content");
		Assert.assertEquals("Logged In", driver.findElement(locator).getText());
	}

	
	
	@Given("the user logs in with invalid credentials")
	public void invalidLogin() {
		options.addArguments("--ignore-certificate-errors");
		options.setHeadless(true);
		driver = new ChromeDriver(options);
		driver.get("https://localhost:8443");
		driver.findElement(By.name("username")).sendKeys("failme");
		driver.findElement(By.name("password")).sendKeys("invalid password");
		driver.findElement(By.name("password")).submit();
		driver.switchTo().alert().accept();
	}
	@Then("the user is not logged in")
	public void loggedInFail() {
		Assert.assertEquals("Login", driver.getTitle());
	}
	@Then("the user did not register")
	public void registerFail() {
		Assert.assertEquals("Register", driver.getTitle());
	}
	@Given("the user is on the register page")
	public void register() {
		options.addArguments("--ignore-certificate-errors");
		options.setHeadless(true);
		driver = new ChromeDriver(options);
		driver.get("https://localhost:8443/Register.jsp");

	}
	@And ("is on the login page")
	public void checkLoginPageNow(){
		Assert.assertEquals("Login", driver.getTitle());
	}
	@Then("the user registers with valid credentials")
	public void registerWithValid() {
		byte[] array = new byte[7]; // length is bounded by 7
	    new Random().nextBytes(array);
	    String generatedString = new String(array, Charset.forName("UTF-8"));
		driver.findElement(By.name("username")).sendKeys(generatedString);
		driver.findElement(By.name("password")).sendKeys("password");
		driver.findElement(By.name("verifyPassword")).sendKeys("password");
		driver.findElement(By.name("verifyPassword")).submit();
		driver.switchTo().alert().accept();
	}
	@And("the user is redirected to the home page")
	public void checkHome() {
		Assert.assertEquals("Register", driver.getTitle());
	}
	@And("registers with different passwords")
	public void diffPassword() {
		driver.findElement(By.name("username")).sendKeys("fake");
		driver.findElement(By.name("password")).sendKeys("mismatch");
		driver.findElement(By.name("verifyPassword")).sendKeys("match");
		driver.findElement(By.name("verifyPassword")).submit();
		driver.switchTo().alert().accept();
	}
	@Then("the user is not redirected to the home page")
	public void noRedirect() {
		Assert.assertEquals("Register", driver.getTitle());
	}
	@Given("the user is on the login page")
	public void loginPage() {
		options.addArguments("--ignore-certificate-errors");
		options.setHeadless(true);
		driver = new ChromeDriver(options);
		driver.get("https://localhost:8443/index.jsp");

	}
	@And("the user performs an SQL injection attack")
	public void sqlattack() {
		driver.findElement(By.name("username")).sendKeys("'OR1=1-- ");
		driver.findElement(By.name("password")).sendKeys("sqlinjection");
		driver.findElement(By.name("password")).submit();
		driver.switchTo().alert().accept();
	}
	@And("at least one input is blank")
	public void blankInput() {
		byte[] array = new byte[7]; // length is bounded by 7
	    new Random().nextBytes(array);
	    String generatedString = new String(array, Charset.forName("UTF-8"));
		driver.findElement(By.name("username")).sendKeys("generateString");
		driver.findElement(By.name("password")).sendKeys("");
		driver.findElement(By.name("verifyPassword")).sendKeys("adsf");
		driver.findElement(By.name("verifyPassword")).submit();
		driver.switchTo().alert().accept();
	}
	
	@Then("the password in the database is hashed")
	public void hashPassword() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		jdbcController controller = new jdbcController();
		String password = controller.hashPassword();
		Assert.assertNotEquals("password", password);
	}
	@Then("the user can log out")
	public void logOut(){
		driver.findElement(By.linkText("Log Out")).click();
		driver.switchTo().alert().accept();
		Assert.assertEquals("Login", driver.getTitle());
	}
	@After()
	public void after() {
    	if(driver != null) {
    		driver.quit();
    	}
	}
}