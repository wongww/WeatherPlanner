package csci310;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CityListTest {
	
	CityList list;
	
	@Before
	public void setUp()
	{
		CityResult c1 = new CityResult("Burbank", "USA", "90007", 50, 100, 200);
		CityResult c2 = new CityResult("Los Angeles", "USA", "90089", 50, 100, 100);
		CityResult c3 = new CityResult("San Francisco", "USA", "95070", 50, 100, 300);
		CityResult c4 = new CityResult("San Jose", "USA", "95070", 50, 100, 300);
		
		List<CityResult> cities = new ArrayList<CityResult>();
		cities.add(c1);
		cities.add(c2);
		cities.add(c3);
		cities.add(c4);
		
		list = new CityList(cities);
	}
	
	@Test
	public void nearToFarTest()
	{
		list.sortDistance(true);
		
		List<CityResult> cities = list.getCities();
		
		Assert.assertEquals(cities.get(0).getName(), "Los Angeles");
		Assert.assertEquals(cities.get(1).getName(), "Burbank");
		Assert.assertEquals(cities.get(2).getZipCode(), "95070");
		Assert.assertEquals(cities.get(3).getName(), "San Jose");
		
	}
	
	@Test
	public void farToNearTest()
	{
		list.sortDistance(false);
		
		List<CityResult> cities = list.getCities();
		
		Assert.assertEquals(cities.get(0).getName(), "San Jose");
		Assert.assertEquals(cities.get(1).getZipCode(), "95070");
		Assert.assertEquals(cities.get(2).getName(), "Burbank");
		Assert.assertEquals(cities.get(3).getName(), "Los Angeles");
	}
	
	
	
	
	

}
