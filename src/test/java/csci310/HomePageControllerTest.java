package csci310;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.*;

public class HomePageControllerTest {

	HomePageController h1;
	HomePageController h2;
	HomePageController h3;
	HomePageController h4;
	HomePageController h5;
	
	@Before
	public void setUp()
	{
		h1 = new HomePageController("90007");
		h2 = new HomePageController("19888");
		h3 = new HomePageController("los angeles");
		h4 = new HomePageController("notacity");
		h5 = new HomePageController("90a07");
	}
	
	@Test
	public void isZipTest()
	{	
		Assert.assertEquals(h1.isZip(h1.getSearchbar()), true);
		Assert.assertEquals(h2.isZip(h2.getSearchbar()), true);
		Assert.assertEquals(h3.isZip(h3.getSearchbar()), false);
		Assert.assertEquals(h4.isZip(h4.getSearchbar()), false);
		Assert.assertEquals(h5.isZip(h5.getSearchbar()), false);
	}
	
	@Test
	public void generateUrlTest()
	{	
		Assert.assertEquals(h1.generateUrl(h1.getSearchbar(),true,h1.getKey()), "http://api.openweathermap.org/data/2.5/weather?zip=90007&appid=e7c34c987487b3afc299eb93974772e9");
		Assert.assertEquals(h2.generateUrl(h2.getSearchbar(),true,h2.getKey()), "http://api.openweathermap.org/data/2.5/weather?zip=19888&appid=e7c34c987487b3afc299eb93974772e9");
		Assert.assertEquals(h3.generateUrl(h3.getSearchbar(),false,h3.getKey()), "http://api.openweathermap.org/data/2.5/weather?q=los angeles&appid=e7c34c987487b3afc299eb93974772e9");
		Assert.assertEquals(h4.generateUrl(h4.getSearchbar(),false,h4.getKey()), "http://api.openweathermap.org/data/2.5/weather?q=notacity&appid=e7c34c987487b3afc299eb93974772e9");
	}
	
	@Test
	public void getInfoTest()
	{
		JsonObject json1 = h1.getJson(h1.generateUrl(h1.getSearchbar(),true,h1.getKey()));
		JsonObject json2 = h2.getJson(h2.generateUrl(h2.getSearchbar(),true,h2.getKey()));
		JsonObject json3 = h3.getJson(h3.generateUrl(h3.getSearchbar(),false,h3.getKey()));
		JsonObject json4 = h4.getJson(h4.generateUrl(h4.getSearchbar(),false,h4.getKey()));
		
		String[] info1 = new String[5];
		String[] info2 = new String[5];
		String[] info3 = new String[5];
		String[] info4 = new String[5];
		
		info1[0] = "Los Angeles";
		info2[0] = "No weather data found";
		info3[0] = "Los Angeles";
		info4[0] = "No weather data found";
		
		Assert.assertEquals(h1.getInfo(json1)[0], info1[0]);
		Assert.assertEquals(h2.getInfo(json2)[0], info2[0]);
		Assert.assertEquals(h3.getInfo(json3)[0], info3[0]);
		Assert.assertEquals(h4.getInfo(json4)[0], info4[0]);
	}
	
	@Test
	public void getJsonTest()
	{
		String url1 = h1.generateUrl(h1.getSearchbar(),true,h1.getKey());
		String url2 = h2.generateUrl(h2.getSearchbar(),true,h2.getKey());
		String url3 = h3.generateUrl(h3.getSearchbar(),false,h3.getKey());
		String url4 = h4.generateUrl(h4.getSearchbar(),false,h4.getKey());
		
		JsonObject jsonempty = new JsonObject();
		
		Assert.assertNotEquals(h1.getJson(url1), jsonempty);
		Assert.assertEquals(h2.getJson(url2), jsonempty);
		Assert.assertNotEquals(h3.getJson(url3), jsonempty);
		Assert.assertEquals(h4.getJson(url4), jsonempty);
	}
}
