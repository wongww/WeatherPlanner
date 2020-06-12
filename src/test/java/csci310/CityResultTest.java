package csci310;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CityResultTest {
	
	CityResult result;
	CityResult result2;
	
	@Before
	public void setUp()
	{
		result = new CityResult("Los Angeles", "USA", "90089", 50, 100, 200);
		result2 = new CityResult("Los Angeles", "USA", 0, 50);
	}
	
	@Test
	public void testAvgMaxF()
	{
		Assert.assertEquals(result.getAvgMaxTempF(), -279);
	}
	
	@Test
	public void testAvgMaxC()
	{
		Assert.assertEquals(result.getAvgMaxTempC(), -172);
	}
	
	
	@Test
	public void testAvgMinF()
	{
		Assert.assertEquals(result.getAvgMinTempF(), -369);
	}
	
	@Test
	public void testCurrentTempF()
	{
		Assert.assertEquals((int)result.getCurrentTempF(), -459);
	}
	
	@Test
	public void testCurrentTempC()
	{
		Assert.assertEquals((int)result.getCurrentTempC(), -273);
	}
	
	@Test
	public void testAvgMinC()
	{
		Assert.assertEquals(result.getAvgMinTempC(), -222);
	}
	
	@Test
	public void testDistance()
	{
		Assert.assertEquals(result.getDistance(), 200);
	}
	
	
	
}
