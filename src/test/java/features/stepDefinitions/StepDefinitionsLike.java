package features.stepDefinitions;
import io.cucumber.java.After;
import java.util.List;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

public class StepDefinitionsLike{
	ChromeOptions options = new ChromeOptions();
	WebDriver driver;
	String URL = "https://localhost:8443/";
	int likesBefore;
	int likesAfter;
	WebElement element;
	@Given("I am on the Activity Planning Page and I have cities from the search results")
	public void loadResultsOnActivityPage() {
		options.addArguments("--ignore-certificate-errors");
		options.setHeadless(true);
		driver = new ChromeDriver(options);
		driver.get("https://localhost:8443");
		driver.findElement(By.name("username")).sendKeys("test2");
		driver.findElement(By.name("password")).sendKeys("test");
		driver.findElement(By.name("password")).submit();
		driver.switchTo().alert().accept();
		driver.get(URL + "ActivitiesPlanning.jsp");
		driver.findElement(By.name("activity")).sendKeys("running");
	    driver.findElement(By.name("radius")).sendKeys("10");
	    
	    JavascriptExecutor jse = (JavascriptExecutor)driver;
	    jse.executeScript("document.getElementsByName('lat')[0].setAttribute('type', 'text');");
	    jse.executeScript("document.getElementsByName('long')[0].setAttribute('type', 'text');");
	    driver.findElement(By.name("lat")).sendKeys("37.773972");
	    driver.findElement(By.name("long")).sendKeys("-122.431297");
	    driver.findElement(By.name("radius")).submit();
	    
	}
    @When("I click the like button of a city on the Activity Planning Page")
    public void clickLikeOnActivityPage() {
    	likesBefore = Integer.valueOf(driver.findElement(By.id("likeCount0")).getText());
    	driver.findElement(By.id("upButton0")).click();
    	element = driver.findElement(By.id("upButton0"));
    	
    }
    
    @When("I click the dislike button of a city")
    public void clickdisLikeOnActivityPage() {
    	driver.findElement(By.id("upButton0")).click();
    	likesBefore = Integer.valueOf(driver.findElement(By.id("likeCount0")).getText());
    	driver.findElement(By.id("downButton0")).click();
    	element = driver.findElement(By.id("downButton0"));
    	
    }
    
    @Then("the number of likes for that city should increase by one on the Activity Planning Page")
    public void checkLikesOnActivityPage() {
    	likesAfter = Integer.valueOf(driver.findElement(By.id("likeCount0")).getText());
    	Assert.assertEquals(likesAfter, likesBefore + 1);
    }
    
    @Then("the number of likes for that city should decrease by one")
    public void checkDecreaseLikesOnActivityPage() {
    	likesAfter = Integer.valueOf(driver.findElement(By.id("likeCount0")).getText());
    	Assert.assertEquals(likesAfter + 1, likesBefore);
    }
    @And("the like button changes to an unlike button for that city on the Activity Planning Page")
    public void checkUnlikeOnActivityPlanning() {
    	Assert.assertEquals("Unlike", element.getText());
    }
    
    @When("I click likes")
    public void clickLikes()
    {
    	//driver.findElement(By.id("likeSort")).click();
    	
    	((JavascriptExecutor)driver).executeScript("sortTable(0);");
    }
    
    @Then("cities should be displayed in decreasing order of likes")
    public void cities_should_be_displayed_in_decreasing_order_of_likes() {
    	List<WebElement> trs = driver.findElements(By.tagName("tr"));
    	
    	int prev = Integer.MAX_VALUE;
    	boolean first = true;
    	for (WebElement tr : trs)
    	{
    		if (!first && !tr.getAttribute("style").equals("display: none;"))
    		{
    			int val = 0;
    			if (!tr.findElement(By.cssSelector("[id^=likeCount]")).getText().equals(""))
    			{
    				val = Integer.valueOf(tr.findElement(By.cssSelector("[id^=likeCount]")).getText());
    			}
	    		
    			System.out.println("the value is " + val);
	    		
	    		if (val > prev)
	    		{
	    			Assert.assertTrue(false);
	    		}
	    		
	    		prev = val;
    		}
    		first = false;
    	}
    	
    	
    	Assert.assertTrue(true);
    }
    
    @Given("I am on the Vacation Planning Page and I have cities from the search results")
    public void i_am_on_the_Vacation_Planning_Page_and_I_have_cities_from_the_search_results() {
    	System.out.println("hereee");
    	options.addArguments("--ignore-certificate-errors");
		options.setHeadless(true);
		driver = new ChromeDriver(options);
		System.out.println("created here");
		driver.get("https://localhost:8443");
		driver.findElement(By.name("username")).sendKeys("test2");
		driver.findElement(By.name("password")).sendKeys("test");
		driver.findElement(By.name("password")).submit();
		driver.switchTo().alert().accept();
		
		driver.get(URL + "VacationPlanning.jsp");
		
		driver.findElement(By.name("lowrange")).sendKeys("0");
		driver.findElement(By.name("highrange")).sendKeys("100");
	    
	    JavascriptExecutor jse = (JavascriptExecutor)driver;
	    jse.executeScript("document.getElementsByName('lat')[0].setAttribute('type', 'text');");
	    jse.executeScript("document.getElementsByName('long')[0].setAttribute('type', 'text');");
	    
	    driver.findElement(By.name("lat")).sendKeys("34.052235");
	    driver.findElement(By.name("long")).sendKeys("-118.243683");
	    driver.findElement(By.name("radius")).sendKeys("20");
	    
	    driver.findElement(By.name("radius")).submit();
       // throw new io.cucumber.java.PendingException();
    }
    
    @When("I click the like button of a city on the Vacation Planning Page")
    public void clickLikeOnVacationPage() {
    	if (driver == null)
    	{
    		System.out.println("why is this null");
    		i_am_on_the_Vacation_Planning_Page_and_I_have_cities_from_the_search_results();
    	}
    	WebElement element;
    	WebDriverWait e = new WebDriverWait(driver, 30);
	    e.until(ExpectedConditions.visibilityOfElementLocated(By.id("likeCount0")));
    	likesBefore = Integer.valueOf(driver.findElement(By.id("likeCount0")).getText());
    	driver.findElement(By.id("upButton0")).click();
    	element = driver.findElement(By.id("upButton0"));
    	
    }
    
    @Then("the number of likes for that city should increase by one on the Vacation Planning Page")
    public void checkLikesOnVacationPage() {
    	likesAfter = Integer.valueOf(driver.findElement(By.id("likeCount0")).getText());
    	Assert.assertEquals(likesAfter, likesBefore + 1);
    }
    @And("the like button changes to an unlike button for that city on the Vacation Planning Page")
    public void checkUnlikeOnVacationPlanning() {
    	Assert.assertEquals("Unlike", element.getText());
    }
    
	@Given ("I am on the Activity Planning Page and I have results")
	public void searchResultsActivity() {
		options.addArguments("--ignore-certificate-errors");
		options.setHeadless(true);
		driver = new ChromeDriver(options);
		driver.get(URL + "ActivitiesPlanning.jsp");
		driver.findElement(By.name("activity")).sendKeys("running");
		driver.findElement(By.name("numresults")).sendKeys("5");
		driver.findElement(By.name("location")).sendKeys("San Francisco");
		driver.findElement(By.name("radius")).sendKeys("10");
		driver.findElement(By.id("activityform")).submit();
	}
	@Then ("there are like buttons next to each location matching each activity")
	public void checkLikeActivity() {
		Assert.assertNotEquals(null, driver.findElement(By.id("likeButton0")));
	}

	@Given ("I am on the Vacation Planning Page and I have results")
	public void searchResultsVacation() {
		options.addArguments("--ignore-certificate-errors");
		options.setHeadless(true);
		driver = new ChromeDriver(options);
		driver.get(URL + "VacationPlanning.jsp");
		driver.findElement(By.name("lowrange")).sendKeys("0");
		driver.findElement(By.name("highrange")).sendKeys("100");
		driver.findElement(By.name("numresults")).sendKeys("10");
		driver.findElement(By.name("location")).sendKeys("Los Angeles");
		driver.findElement(By.name("radius")).sendKeys("20");
		driver.findElement(By.id("vacationform")).submit();
		
	}
	@Then ("there are like buttons next to each location matching each vacation")
	public void checkLikeVacation() {
		Assert.assertNotEquals(null, driver.findElement(By.id("likeButton0")));
	}
	
    @After()
    public void after() {
    	if(driver != null) {
    		driver.quit();
    	}
    	
    }
}