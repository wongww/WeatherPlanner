package csci310;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import csci310.Searcher.Pair;

public class SearcherTest {

	
	@Test
	public void vacationSearchTest() throws IOException
	{
		CityList results = Searcher.vacationSearch(50, 75,  10, 51.51, -0.13);
		
		
	//	Assert.assertEquals(results.getCities().get(0).getName(), "Los Angeles");
	//	Assert.assertEquals(results.getCities().get(1).getName(), "Belvedere");
	//	Assert.assertEquals(results.getCities().get(2).getName(), "East Los Angeles");
		
	//	Assert.assertEquals(results.getCities().size(), 10);
		
		
		
	}
	
	/*
	@Test
	public void latLongTest() throws IOException
	{
		Pair results = SearchServlet.getLatLong();
		Assert.assertEquals(results.getX(), -0.13, 0);
		Assert.assertEquals(results.getY(),51.51,  0);
		
	}
	
	@Test
	public void avgMinMaxTest() throws IOException
	{
		Pair results = SearchServlet.getAvgMinMax();
		Assert.assertEquals(results.getX(), 274.38713888888896, 0);
		Assert.assertEquals(results.getY(), 274.74219444444446, 0);
	}
	*/
	@Test
	public void distanceTest()
	{
		double dist = Searcher.getDistance(55.5, 37.5, 57.0333, -2.15);
		Assert.assertEquals(dist, 2418, 10);
	}
	
	@Test
	public void activityToTempRangeTest() throws IOException {
		int testRange1 = Searcher.activityToTempRange("skiing");
		int testRange2 = Searcher.activityToTempRange("soccer");
		int testRange3 = Searcher.activityToTempRange("surfing");
		int testRange4 = Searcher.activityToTempRange("snowboarding");
		int testRange5 = Searcher.activityToTempRange("iCe HoCkEy");
		int testRange6 = Searcher.activityToTempRange("diving");
		int testRange7 = Searcher.activityToTempRange("swimming");
		int testRange8 = Searcher.activityToTempRange("snorkling");
		int testRange9 = Searcher.activityToTempRange("hiking");
		int testRange10 = Searcher.activityToTempRange("football");
		int testRange11 = Searcher.activityToTempRange("basketball");
		
		Assert.assertEquals(testRange1, 1);
		Assert.assertEquals(testRange2, 2);
		Assert.assertEquals(testRange3, 3);
		Assert.assertEquals(testRange4, 1);
		Assert.assertEquals(testRange5, 1);
		Assert.assertEquals(testRange6, 3);
		Assert.assertEquals(testRange7, 3);
		Assert.assertEquals(testRange8, 3);
		Assert.assertEquals(testRange9, 2);
		Assert.assertEquals(testRange10, 2);
		Assert.assertEquals(testRange11, 2);
	}
	
	@Test
	public void activitySearchTest() throws IOException
	{
		CityList testresults = Searcher.activitySearch("soccer",  10, 34.052235, -118.243683);
		
	//	Assert.assertEquals(testresults.getCities().get(0).getName(), "Los Angeles");
	//	Assert.assertEquals(testresults.getCities().get(1).getName(), "Belvedere");
	//	Assert.assertEquals(testresults.getCities().get(2).getName(), "East Los Angeles");
		
		
		
		
		CityList testresults2 = Searcher.activitySearch("Swimming", 10, 34.052235, -118.243683);
		
//		Assert.assertEquals(testresults2.getCities().get(0).getName(), "Belvedere");
//		Assert.assertEquals(testresults2.getCities().get(1).getName(), "Huntington Park");
		
	
		
	}
}
