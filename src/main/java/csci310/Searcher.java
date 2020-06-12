package csci310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class Searcher {
	
	private static final double EARTH_RAD = 6378137;
	
	
	//using haversine formula returns in km
	public static double getDistance(double lat1, double lon1, double lat2, double lon2)
	{
		double R = EARTH_RAD;
		double dLat = convertToRad(lat2 - lat1);
		double dLong = convertToRad(lon2 - lon1);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
			    Math.cos(convertToRad(lat1)) * Math.cos(convertToRad(lat2)) *
			    Math.sin(dLong / 2) * Math.sin(dLong / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));;
		double d = R*c;
		return d/1000;
	}
	
	private static double convertToRad(double val)
	{
		return val * Math.PI / 180;
	}
	public static Pair getLatLong(String city) throws IOException
	{
		String u = "https://api.openweathermap.org/data/2.5/weather?q=" + URLEncoder.encode(city, "UTF-8") + "&appid=e7c34c987487b3afc299eb93974772e9";
		URL url = new URL(u);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) 
		{
			return null;
			//throw new RuntimeException("Failed : HTTP error code : "
			//		+ conn.getResponseCode());
		}
		

		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));

		String output;
		String full = "";
		
		
		while ((output = br.readLine()) != null) {
			full += output;				
		}
		
		JsonObject json = new JsonParser().parse(full).getAsJsonObject();
		
		JsonObject latLong = json.get("coord").getAsJsonObject();
		Pair p = new Pair(latLong.get("lon").getAsDouble(), latLong.get("lat").getAsDouble());

		return p;
	}
	
	public static Pair getAvgMinMax(String city) throws IOException
	{
		double avgMin = 0;
		double avgMax = 0;
		
		String u = "http://api.openweathermap.org/data/2.5/forecast?q=" + URLEncoder.encode(city, "UTF-8") + "&appid=e7c34c987487b3afc299eb93974772e9";
		
		URL url = new URL(u);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) 
		{
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));

		String output;
		String full = "";
		
		
		while ((output = br.readLine()) != null) {
			full += output;				
		}
		

		JsonObject json = new JsonParser().parse(full).getAsJsonObject();
		
		JsonArray list = json.get("list").getAsJsonArray();
		
		for (int i = 0; i < list.size(); i++)
		{
			JsonObject mainObj = list.get(i).getAsJsonObject();
			JsonObject details = mainObj.get("main").getAsJsonObject();
			avgMin += details.get("temp_min").getAsDouble();
			avgMax += details.get("temp_max").getAsDouble();
		}
		
		avgMin = avgMin/list.size();
		avgMax = avgMax/list.size();
			
		Pair p = new Pair(avgMin, avgMax);
		return p;
	}
	
	
	public static CityList vacationSearch(int rangeLow, int rangeHigh, int radius, double latC, double longC) throws IOException
	{
		

		List<CityResult> list = new ArrayList<CityResult>();
	
		Pair coord = new Pair(longC, latC);
		
		if(coord == null){
			return null;
		}
		
		String u = "https://api.openweathermap.org/data/2.5/find?lat=" + coord.getY() + "&lon=" + coord.getX() + "&cnt=50&appid=e7c34c987487b3afc299eb93974772e9";

		URL url = new URL(u);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) 
		{
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));

		String output;
		String full = "";
		
		
		while ((output = br.readLine()) != null) {
			full += output;				
		}
		
		JsonObject json = new JsonParser().parse(full).getAsJsonObject();
		
		JsonArray cities = json.get("list").getAsJsonArray();
		CityList results = new CityList(list);
		int count = 0;
		for (int i = 0; i < cities.size(); i++)
		{
			JsonObject city = cities.get(i).getAsJsonObject();
			
			String name = city.get("name").getAsString();
			JsonObject sys = city.get("sys").getAsJsonObject();
			String country = sys.get("country").getAsString();
			String zipcode = "-----";
			
			Pair minMax = getAvgMinMax(name);
			
			if (minMax.getX() > rangeLow && minMax.getX() < rangeHigh  &&
					minMax.getY() > rangeLow && minMax.getY() < rangeHigh)
			{
				JsonObject c = city.get("coord").getAsJsonObject();
				
				double dist = getDistance(coord.getY(), coord.getX(), c.get("lat").getAsDouble(), c.get("lon").getAsDouble());
				
				if (((int)dist) <= radius)
				{
					list.add(new CityResult(name, country, zipcode, (int)minMax.getX(),(int) minMax.getY(), (int)dist));			
					count++;
				}
			}
				
			
			
		}
		
		return results;
	}
	
	// Takes in user activity and returns integer corresponding to one of three temperature ranges
		public static int activityToTempRange(String activity) {
			int rangeInt = -1;
			
			if (activity.toLowerCase().matches("skiing") || 
				activity.toLowerCase().matches("snowboarding") ||
				activity.toLowerCase().matches("ice hockey")) {
				rangeInt = 1;
			} else if (activity.toLowerCase().matches("soccer") || 
						activity.toLowerCase().matches("hiking") ||
						activity.toLowerCase().matches("football") ||
						activity.toLowerCase().matches("basketball")) {
				rangeInt = 2;
			} else if (activity.toLowerCase().matches("surfing") || 
					activity.toLowerCase().matches("diving") ||
					activity.toLowerCase().matches("swimming") ||
					activity.toLowerCase().matches("snorkling")) {
				rangeInt = 3;
			} else {
				// Error case
				rangeInt = -1;
			}
			return rangeInt;
		}
		
		// Build a cityList using activity planning page specific constructor
		public static CityList activitySearch(String activity, int radius, double latC, double longC) throws IOException {
			
			// Instantiate city list for resulting activities
			List<CityResult> activityCityList = new ArrayList<CityResult>();
			
			Pair currLatLong = new Pair(longC, latC);
			
			// Call Open Weather API for cities in cycle functionality (HARDCODED COUNT AS 50)
			String apiCallString = "https://api.openweathermap.org/data/2.5/find?lat=" + currLatLong.getY() + "&lon=" + currLatLong.getX() + "&cnt=50&appid=c4fb6f66da7ad5edfab4d688b037ccfb";
			
			URL url = new URL(apiCallString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			// Parse all 50 results of cities returned in cycle from API
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			String full = "";
				
			while ((output = br.readLine()) != null) {
				full += output;				
			}
			
			JsonObject json = new JsonParser().parse(full).getAsJsonObject();
			
			JsonArray cities = json.get("list").getAsJsonArray();
			CityList results = new CityList(activityCityList);
			int count = 0;
			for (int i = 0; i < cities.size(); i++)
			{
				JsonObject city = cities.get(i).getAsJsonObject();
				
				String name = city.get("name").getAsString();
				JsonObject sys = city.get("sys").getAsJsonObject();
				String country = sys.get("country").getAsString();
				// Grab current temperature;
				JsonObject main = city.get("main").getAsJsonObject();
				double remoteTemp = main.get("temp").getAsDouble();
				
				JsonObject c = city.get("coord").getAsJsonObject();
				
				double dist = getDistance(currLatLong.getY(), currLatLong.getX(), c.get("lat").getAsDouble(), c.get("lon").getAsDouble());
				
				if (((int)dist) <= radius)
				{
					activityCityList.add(new CityResult(name, country, remoteTemp, (int) dist));
					count++;
				}
				
			}
			
			// PROCEED to now filter results, call activityToTempRange
			if (activityToTempRange(activity) == 1) {
				for (int i = 0; i < results.getCities().size(); i++) {
					if (results.getCities().get(i).getCurrentTemp() > 277.594) {
						// Remove cities w/temperature > 40F for winter sports
						results.getCities().remove(i);
					}
				}
			} else if (activityToTempRange(activity) == 2) {
				for (int i = 0; i < results.getCities().size(); i++) {
					// Remove cities w/temperature < 40F || > 80F for outdoor activities
					if (results.getCities().get(i).getCurrentTemp() < 277.594 || results.getCities().get(i).getCurrentTemp() > 299.817) {
						results.getCities().remove(i);
					}
				}
			} else if (activityToTempRange(activity) == 3) {
				// System.out.println("In 3");
				for (int i = 0; i < results.getCities().size(); i++) {
					// Remove cities w/temperature > 80F for water sports
					if (results.getCities().get(i).getCurrentTemp() < 299.817) {
						results.getCities().remove(i);
					}
				}
			}
			
			// Finally sort the cities before returning
			
			return results;
		}
	
	
	public static class Pair
	{
		private double x;
		private double y;
		
		public Pair(double x, double y)
		{
			this.x = x;
			this.y = y;
		}
		
		public double getX()
		{
			return x;
		}
		
		public double getY()
		{
			return y;
		}
	}
	
	
	

}
