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

public class FavoritesServlet {
	
	private static List<FavoriteCity> favoritesList = new ArrayList<FavoriteCity>();
	
	
	public static List<FavoriteCity> getFavorites()
	{
		return favoritesList;
	}
	
	public static FavoriteCity getFavorite(String name, String zip) {
		if(favoritesList.isEmpty()) {
			return null;
		}
		for(FavoriteCity fc: favoritesList) {
			if(zip.length() != 5) {
				return null;
			}
			if(fc.getName().equals(name)) {
				return fc;
			}
			else if(fc.getZipCode().equals(zip)) {
				if(fc.getName().equals(name)) {
					return fc;
				}
			}
		}
		return null;
	}
	public static void removeFavorite(String name, String zip) {
		if(favoritesList.isEmpty()) {
			return;
		}
		else {
			FavoriteCity toRemove = getFavorite(name,zip);
			if(toRemove != null) {
				favoritesList.remove(toRemove);
			}
		}
	}
	public static void addFavorites(String name, String zip) throws IOException {
		if(favoritesList != null) {
			for(FavoriteCity fc: favoritesList) {
				if(fc.getName().equals(name)) {
					return;
				}
			}
		}
		String u_fiveDay = "http://api.openweathermap.org/data/2.5/forecast?q=" + URLEncoder.encode(name, "UTF-8") + "&appid=e7c34c987487b3afc299eb93974772e9";
		URL url_fiveDay = new URL(u_fiveDay);
		
		HttpURLConnection conn_fiveDay = (HttpURLConnection) url_fiveDay.openConnection();
		
		conn_fiveDay.setRequestMethod("GET");
		conn_fiveDay.setRequestProperty("Accept", "application/json");
		

		if (conn_fiveDay.getResponseCode() != 200) 
		{
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn_fiveDay.getResponseCode());
		}
		

		BufferedReader br_fiveDay = new BufferedReader(new InputStreamReader(
			(conn_fiveDay.getInputStream())));

		String output_fiveDay;
		String full_fiveDay = "";		
		
		while ((output_fiveDay = br_fiveDay.readLine()) != null) {
			full_fiveDay += output_fiveDay;				
		}		
		

		JsonObject json_fiveDay = new JsonParser().parse(full_fiveDay).getAsJsonObject();
		
		JsonArray forecast = json_fiveDay.get("list").getAsJsonArray();
		
		List<Weather> fiveDay = new ArrayList<Weather>();
		List<String> images = new ArrayList<String>();
		
		String nextDate = ""; //Used to compare the dates
		int day_num = 0; //counter for number of days in fiveDay
		JsonObject city = json_fiveDay.get("city").getAsJsonObject();
		String country = city.get("country").getAsString(); //string for country
		String date = ""; //string for date
		String icon = ""; //string for icon
		//Go through list, but only add fiveDays
		for(int i = 0; i < forecast.size(); i++) {
			if(day_num != 5 && i != forecast.size())
			{
				JsonObject Obj = forecast.get(i).getAsJsonObject();
				JsonObject nextObj = forecast.get(i+1).getAsJsonObject();
				JsonObject main = Obj.get("main").getAsJsonObject();
				date = Obj.get("dt_txt").getAsString();				
				date = date.substring(0, 10);
				nextDate = nextObj.get("dt_txt").getAsString();
				nextDate = nextDate.substring(0,10);
				//Only create a Weather object when the dates are different
				if(date.equals(nextDate) == false || i == 0) {
					int avgMin = main.get("temp_min").getAsInt();
					int avgMax = main.get("temp_max").getAsInt();
					//Convert Kelvin to Farenheit
					double minF = (avgMin - 273.15) * 9/5 + 32;
					double maxF = (avgMax - 273.15) * 9/5 + 32;
					minF = Math.round(minF);
					maxF = Math.round(maxF);
					int minTemp = (int)minF; //int for low temp
					int maxTemp = (int) maxF; //int for high temp
					JsonArray arr = Obj.get("weather").getAsJsonArray();
					JsonObject weatherIcon = arr.get(0).getAsJsonObject();
					icon = weatherIcon.get("icon").getAsString();
					if(i != 0) {
						date = nextDate;
					}
					String year = date.substring(0, 4); //String for year
					String month = date.substring(5, 7); //String for month
					String day = date.substring(8); //String for day
					String weatherDate = month + "/" + day + "/" + year; //Re-format the date to match the date formatting
					fiveDay.add(new Weather(weatherDate, maxTemp, minTemp, icon)); //Once created add it to the list
					day_num++;
				}
			if(day_num == 5) {break;}
			}
			
		}
		favoritesList.add(new FavoriteCity(name, country, zip, fiveDay, images)); //Once you get everything, create the object and add to the list
	}
	public static boolean checkFavorite(String name)
	{
		if(favoritesList != null) {
			for(FavoriteCity fc: favoritesList) {
				if(fc.getName().equals(name)) {
					return true;
				}
			}
		}
		return false;
	}
	public void updateFavorite() {
		
	}
}
