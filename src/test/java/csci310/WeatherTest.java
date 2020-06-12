package csci310;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WeatherTest {
	
	Weather w;
	
	@Before
	public void setUp()
	{
		w = new Weather("1/1/20",100, 50, "www.link.com");
	}
	
	@Test
	public void testHighF()
	{
		Assert.assertEquals(w.getHighF(), 100);
	}
	
	@Test
	public void testHighC()
	{
		Assert.assertEquals(w.getHighC(), 37);
	}
	
	@Test
	public void testLowF()
	{
		Assert.assertEquals(w.getLowF(), 50);
	}
	
	@Test
	public void testLowC()
	{
		Assert.assertEquals(w.getLowC(), 10);
	}
	
	@Test
	public void testWeatherIcon()
	{
		Assert.assertEquals(w.getWeatherIcon(), "www.link.com");
	}
	
	@Test
	public void testDate()
	{
		Assert.assertEquals(w.getDate(), "1/1/20");
	}
	


}
