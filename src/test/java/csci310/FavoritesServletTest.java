package csci310;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import csci310.FavoritesServlet;
public class FavoritesServletTest {
	@Before
	public void setUp() throws IOException{
		String city_name = "Los Angeles";
		String zipcode = "90001";
		
		FavoritesServlet.addFavorites(city_name, zipcode);
	}
	@Test
	public void addFavoriteTest() throws IOException{ 
		String city_name = "Los Angeles";
		String zipcode = "90001";
		Assert.assertEquals(FavoritesServlet.getFavorites().size(), 1);
		Assert.assertEquals(FavoritesServlet.getFavorite(city_name, zipcode).getName(), city_name);
		Assert.assertEquals(FavoritesServlet.getFavorite(city_name, zipcode).getZipCode(), zipcode);
		Assert.assertEquals(FavoritesServlet.getFavorite(city_name, zipcode).getCountry(), "US");
		
		FavoritesServlet.addFavorites(city_name, zipcode);
		Assert.assertEquals(FavoritesServlet.getFavorites().size(), 1);
	}

	@Test
	public void removeFavoriteTest() throws IOException{ 
		String city_name = "Los Angeles";
		String zipcode = "90001";
		FavoritesServlet.addFavorites(city_name, zipcode);
		Assert.assertEquals(FavoritesServlet.getFavorites().size(), 1);
		FavoritesServlet.removeFavorite(city_name, zipcode);
		Assert.assertEquals(FavoritesServlet.getFavorites().size(), 0);
	}
	
	@Test
	public void removeFavoriteFromEmptyListTest() throws IOException{ 
		String city_name = "Los Angeles";
		String zipcode = "90001";
		FavoritesServlet.getFavorites().clear();
		Assert.assertEquals(FavoritesServlet.getFavorites().size(), 0);
		FavoritesServlet.removeFavorite(city_name, zipcode);
		Assert.assertEquals(FavoritesServlet.getFavorites().size(), 0);
	}
	@Test
	public void getFiveDayTest() {
		String city_name = "Los Angeles";
		String zipcode = "90001";
		
		Assert.assertEquals(FavoritesServlet.getFavorite(city_name, zipcode).getFiveDay().size(),  5);
		
		for(int i = 0; i < FavoritesServlet.getFavorite(city_name, zipcode).getFiveDay().size(); i++) {
			String date = FavoritesServlet.getFavorite(city_name, zipcode).getFiveDay().get(i).getDate();
			char[] arr = date.toCharArray();
			//Checks format of the date
			for(int j = 0; j < arr.length; j++) {
				if(j == 2 || j == 5) {
					Assert.assertTrue(arr[j] == '/');
				}
				else {
					int y = Character.getNumericValue(arr[j]);
					Assert.assertTrue(y >= 0);
				}
			}
			//Check that the High Temp is >= Low Temp
			Assert.assertTrue( FavoritesServlet.getFavorite(city_name, zipcode).getFiveDay().get(i).getHighF() >= FavoritesServlet.getFavorite(city_name, zipcode).getFiveDay().get(i).getLowF());
			//Check that the icon code is in the right format
			Assert.assertEquals(FavoritesServlet.getFavorite(city_name, zipcode).getFiveDay().get(i).getWeatherIcon().length(), 3);
		}
	}
	
	@Test
	public void checkFavoriteTest()
	{
		Assert.assertEquals(FavoritesServlet.checkFavorite("Los Angeles") , true);
	}
	
	@Test
	public void notInFavoritesTest() {
		//Right name, wrong zip
		String city_name = "Los Angeles";
		String zipcode = "1234";
		Assert.assertEquals(null, FavoritesServlet.getFavorite(city_name, zipcode));
		
		//Right zip, wrong name
		String city_name_ = "Los Feliz";
		String zipcode_ = "90001";
		Assert.assertEquals(null, FavoritesServlet.getFavorite(city_name_, zipcode_));
		
	}
	
}
