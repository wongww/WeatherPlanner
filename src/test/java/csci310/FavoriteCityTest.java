package csci310;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FavoriteCityTest {
	List<FavoriteCity> list;
	FavoriteCity LA;
	@Before
	public void setUp()
	{
		List<Weather> weatherBurbank = new ArrayList<Weather>();
		weatherBurbank.add(new Weather("3/10/20", 63, 52, "cloud"));
		weatherBurbank.add(new Weather("3/11/20", 66, 53, "thunder"));
		weatherBurbank.add(new Weather("3/12/20", 69, 53, "rain"));
		weatherBurbank.add(new Weather("3/13/20", 60, 53, "partlycloudy"));
		weatherBurbank.add(new Weather("3/14/20", 80, 72, "sun"));
		
		List<String> imagesBurbank = new ArrayList<String>();
		imagesBurbank.add("Burbank High School");
		imagesBurbank.add("Burroughs High School");
		imagesBurbank.add("Burbank City Hall");
		imagesBurbank.add("Burbank Airport");
		imagesBurbank.add("Walmart");
		
		List<Weather> weatherLosAngeles = new ArrayList<Weather>();
		weatherLosAngeles.add(new Weather("3/10/20", 64, 53, "cloud"));
		weatherLosAngeles.add(new Weather("3/11/20", 66, 53, "thunder"));
		weatherLosAngeles.add(new Weather("3/12/20", 69, 53, "rain"));
		weatherLosAngeles.add(new Weather("3/13/20", 60, 53, "partlycloudy"));
		weatherLosAngeles.add(new Weather("3/14/20", 80, 72, "sun"));
		
		List<String> imagesLosAngeles = new ArrayList<String>();
		imagesLosAngeles.add("USC");
		imagesLosAngeles.add("UCLA");
		imagesLosAngeles.add("The Broad");
		imagesLosAngeles.add("LAX");
		imagesLosAngeles.add("Staples Center");
		
		List<Weather> weatherSanFrancisco= new ArrayList<Weather>();
		weatherSanFrancisco.add(new Weather("3/10/20", 90, 86, "sun"));
		weatherSanFrancisco.add(new Weather("3/11/20", 95, 90, "sun"));
		weatherSanFrancisco.add(new Weather("3/12/20", 87, 81, "sun"));
		weatherSanFrancisco.add(new Weather("3/13/20", 100, 96, "sun"));
		weatherSanFrancisco.add(new Weather("3/14/20", 93, 80, "sun"));
		
		List<String> imagesSanFrancisco = new ArrayList<String>();
		imagesSanFrancisco.add("Golden Gate Bridge");
		imagesSanFrancisco.add("Oracle Arena");
		imagesSanFrancisco.add("Levi's Stadium");
		imagesSanFrancisco.add("UC Berkley");
		imagesSanFrancisco.add("Alcatraz");
		
		List<Weather> weatherSanJose = new ArrayList<Weather>();
		weatherSanJose.add(new Weather("3/10/20", 70, 53, "windy"));
		weatherSanJose.add(new Weather("3/11/20", 56, 50, "hail"));
		weatherSanJose.add(new Weather("3/12/20", 46, 39, "windy"));
		weatherSanJose.add(new Weather("3/13/20", 26, 14, "snow"));
		weatherSanJose.add(new Weather("3/14/20", 32, 22, "snow"));
		
		List<String> imagesSanJose = new ArrayList<String>();
		imagesSanJose.add("SJSU");
		imagesSanJose.add("Happy Hollow Park & Zoo");
		imagesSanJose.add("The Tech Interactive");
		imagesSanJose.add("Winchester Mystery House");
		imagesSanJose.add("Egyptian Museum");
		
		FavoriteCity fc_1 = new FavoriteCity("Burbank", "USA", "90007", weatherBurbank, imagesBurbank);
		FavoriteCity fc_2 = new FavoriteCity("Los Angeles", "USA", "90089", weatherLosAngeles, imagesLosAngeles);
		FavoriteCity fc_3 = new FavoriteCity("San Francisco", "USA", "90210", weatherSanFrancisco, imagesSanFrancisco);
		FavoriteCity fc_4 = new FavoriteCity("San Jose", "USA", "95070", weatherSanJose, imagesSanJose);

		list = new ArrayList<FavoriteCity>();
		list.add(fc_1);
		list.add(fc_2);
		list.add(fc_3);
		list.add(fc_4);
		
		LA = new FavoriteCity("Los Angeles", "USA", "90089", weatherLosAngeles, imagesLosAngeles);
	}
	
	@Test
	public void addFavoriteTest()
	{		
		List<FavoriteCity> cities = new ArrayList<FavoriteCity>();
		int size = list.size();
		
		for(int i = 0; i < size; i++) {
			FavoriteCity fc = list.get(i);
			cities.add(fc);
			Assert.assertTrue(cities.contains(fc));
		}
	}
	
	@Test
	public void removeFavoriteTest()
	{		
		List<FavoriteCity> cities = new ArrayList<FavoriteCity>();
		cities.add(list.get(0));
		cities.add(list.get(1));
		cities.add(list.get(2));
		cities.add(list.get(3));
		int size = cities.size();
		
		for(int i = 0; i < size; i++) {
			FavoriteCity fc = list.get(i);
			cities.remove(0);
			Assert.assertFalse(cities.contains(fc));
		}		
		
	}	
	
	@Test
	public void getFiveDayTest() {
		List<Weather> weatherLosAngeles = new ArrayList<Weather>();
		weatherLosAngeles.add(new Weather("3/10/20", 64, 53, "cloud"));
		weatherLosAngeles.add(new Weather("3/11/20", 66, 53, "thunder"));
		weatherLosAngeles.add(new Weather("3/12/20", 69, 53, "rain"));
		weatherLosAngeles.add(new Weather("3/13/20", 60, 53, "partlycloudy"));
		weatherLosAngeles.add(new Weather("3/14/20", 80, 72, "sun"));
		for(int i = 0; i < LA.getFiveDay().size(); i++) {
			Assert.assertEquals(LA.getFiveDay().get(i).getDate(),  weatherLosAngeles.get(i).getDate());
			Assert.assertEquals(LA.getFiveDay().get(i).getHighF(),  weatherLosAngeles.get(i).getHighF());
			Assert.assertEquals(LA.getFiveDay().get(i).getLowF(),  weatherLosAngeles.get(i).getLowF());
			Assert.assertEquals(LA.getFiveDay().get(i).getWeatherIcon(),  weatherLosAngeles.get(i).getWeatherIcon());
		}
	}
	
	@Test
	public void getImagesTest() {
		List<String> imagesLosAngeles = new ArrayList<String>();
		imagesLosAngeles.add("USC");
		imagesLosAngeles.add("UCLA");
		imagesLosAngeles.add("The Broad");
		imagesLosAngeles.add("LAX");
		imagesLosAngeles.add("Staples Center");
		for(int i = 0; i < LA.getImages().size(); i++) {
			Assert.assertEquals(LA.getImages().get(i), imagesLosAngeles.get(i));
		}
	}
	
	@Test
	public void setFiveDayTest() {
		FavoriteCity nowhere = new FavoriteCity(list.get(1).getName(), list.get(1).getCountry(), list.get(1).getZipCode(), list.get(1).getFiveDay(), list.get(1).getImages());
		List<Weather> weatherNowhere = new ArrayList<Weather>();
		weatherNowhere.add(new Weather("6/23/20", 10, -2, "snow"));
		weatherNowhere.add(new Weather("6/24/20", 50, 30, "thunder"));
		weatherNowhere.add(new Weather("6/25/20", 73, 65, "windy"));
		weatherNowhere.add(new Weather("6/26/20", 89, 75, "partlycloudy"));
		weatherNowhere.add(new Weather("6/27/20", 110, 101, "sun"));
		
		nowhere.setFiveDay(weatherNowhere);
		for(int i = 0; i < nowhere.getFiveDay().size(); i++) {
			Assert.assertEquals(nowhere.getFiveDay().get(i).getDate(),  weatherNowhere.get(i).getDate());
			Assert.assertEquals(nowhere.getFiveDay().get(i).getHighF(),  weatherNowhere.get(i).getHighF());
			Assert.assertEquals(nowhere.getFiveDay().get(i).getLowF(),  weatherNowhere.get(i).getLowF());
			Assert.assertEquals(nowhere.getFiveDay().get(i).getWeatherIcon(),  weatherNowhere.get(i).getWeatherIcon());
		}
		
		List<Weather> notFiveDays = new ArrayList<Weather>();
		notFiveDays.add(new Weather("1/23/45", 70, 65, "clear"));
		nowhere.setFiveDay(notFiveDays);
		Assert.assertNotEquals(nowhere.getFiveDay().get(0).getDate(),  notFiveDays.get(0).getDate());
		Assert.assertNotEquals(nowhere.getFiveDay().get(0).getHighF(),  notFiveDays.get(0).getHighF());
		Assert.assertNotEquals(nowhere.getFiveDay().get(0).getLowF(),  notFiveDays.get(0).getLowF());
		Assert.assertNotEquals(nowhere.getFiveDay().get(0).getWeatherIcon(),  notFiveDays.get(0).getWeatherIcon());
		
		
		
	}
	
	@Test
	public void setImagesTest() {
		FavoriteCity nowhere = new FavoriteCity(list.get(1).getName(), list.get(1).getCountry(), list.get(1).getZipCode(), list.get(1).getFiveDay(), list.get(1).getImages());
		List<String> imagesNowhere = new ArrayList<String>();
		imagesNowhere.add("Nowhere1.jpg");
		imagesNowhere.add("Nowhere2.jpg");
		imagesNowhere.add("Nowhere3.jpg");
		imagesNowhere.add("Nowhere4.jpg");
		imagesNowhere.add("Nowhere5.jpg");
		
		nowhere.setImages(imagesNowhere);
		Assert.assertEquals(5, nowhere.getImages().size());
		for(int i = 0; i < nowhere.getImages().size(); i++) {
				Assert.assertEquals(nowhere.getImages().get(i), imagesNowhere.get(i));
		}
		
		List<String> notImages = new ArrayList<String>();
		notImages.add("Incorrect.jpg");
		nowhere.setImages(notImages);
		Assert.assertNotEquals(nowhere.getImages().get(0),  notImages.get(0));
		
	}
	@Test
	public void addLikesTest()
	{
		LA.setLikes(0);
		LA.addLike();
		Assert.assertEquals(LA.getLikes(), 1);
		
	}
	
	@Test
	public void removeLikesTest()
	{
		LA.setLikes(10);
		LA.removeLike();
		Assert.assertEquals(LA.getLikes(), 9);
		
	}
}
