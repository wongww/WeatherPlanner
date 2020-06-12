package csci310;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


/**
 * Servlet implementation class Book
 */
@WebServlet("/AnalyticsInfoPageController")
public class AnalyticsInfoPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FavoriteCity getter_fc;
	List<FavoriteCity> theList;
	public AnalyticsInfoPageController() {
    	super();
    	getter_fc = new FavoriteCity("", "", "", new ArrayList<Weather>(), new ArrayList<String>());
    	theList = FavoritesServlet.getFavorites();
    }
	public List<FavoriteCity> getFavorites(){
		return theList;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		theList = FavoritesServlet.getFavorites();
		String key = "e7c34c987487b3afc299eb93974772e9";
		String city = request.getParameter("someName");
		String rcity = request.getParameter("rcity");
		if(rcity != null) {
			System.out.println("REMOVE");
			System.out.println(rcity);
			FavoritesServlet.removeFavorite(rcity, "-----");
			request.setAttribute("someName", null);
			request.setAttribute("rcity", null);
		}
	
		else {
			//create url
			String urlString = generateUrl(city, false, key);
			
			//getJson
			JsonObject json = getJson(urlString);
			
			//gets String info from json, if no result then array is empty
			String[] info = getInfo(json);
			
			for(FavoriteCity fc: theList) {
				if(fc.getName().equals(city)) {
					getter_fc = fc;
				}
			}
			request.setAttribute("someName", getter_fc.getName());
			if(getter_fc.getName() != null && !info[0].equals("No weather data found")) 
			{
				request.setAttribute("f", info[1]);
				request.setAttribute("c", info[2]);
				request.setAttribute("precipitation", info[3]);
				request.setAttribute("graphic", info[4]);
				
				//the forecast information
				request.setAttribute("forecast", getter_fc.getFiveDay());
				List<Weather> temp = (List<Weather>)request.getAttribute("forecast");
				Weather day1 = temp.get(0);
				Weather day2 = temp.get(1);
				Weather day3 = temp.get(2);
				Weather day4 = temp.get(3);
				Weather day5 = temp.get(4);
				
				request.setAttribute("day1graphic", day1.getWeatherIcon());
				request.setAttribute("day1highF", Integer.toString(day1.getHighF()));
				request.setAttribute("day1highC", Integer.toString(day1.getHighC()));
				request.setAttribute("day1lowF", Integer.toString(day1.getLowF()));
				request.setAttribute("day1lowC", Integer.toString(day1.getLowC()));
				
				request.setAttribute("day2graphic", day2.getWeatherIcon());
				request.setAttribute("day2highF", Integer.toString(day2.getHighF()));
				request.setAttribute("day2highC", Integer.toString(day2.getHighC()));
				request.setAttribute("day2lowF", Integer.toString(day2.getLowF()));
				request.setAttribute("day2lowC", Integer.toString(day2.getLowC()));
				
				request.setAttribute("day3graphic", day3.getWeatherIcon());
				request.setAttribute("day3highF", Integer.toString(day3.getHighF()));
				request.setAttribute("day3highC", Integer.toString(day3.getHighC()));
				request.setAttribute("day3lowF", Integer.toString(day3.getLowF()));
				request.setAttribute("day3lowC", Integer.toString(day3.getLowC()));

				request.setAttribute("day4graphic", day4.getWeatherIcon());
				request.setAttribute("day4highF", Integer.toString(day4.getHighF()));
				request.setAttribute("day4highC", Integer.toString(day4.getHighC()));
				request.setAttribute("day4lowF", Integer.toString(day4.getLowF()));
				request.setAttribute("day4lowC", Integer.toString(day4.getLowC()));
				
				request.setAttribute("day5graphic", day5.getWeatherIcon());
				request.setAttribute("day5highF", Integer.toString(day5.getHighF()));
				request.setAttribute("day5highC", Integer.toString(day5.getHighC()));
				request.setAttribute("day5lowF", Integer.toString(day5.getLowF()));
				request.setAttribute("day5lowC", Integer.toString(day5.getLowC()));
				
				//the slides information
				
				String imageString = "https://serpapi.com/search?api_key=ce34a954ddab93300ba2d527bc575b64a970a66bdcd3b96f65c954dade3ff930&q=" + URLEncoder.encode(city, "UTF-8") + "&tbm=isch&ijn=0";
				
				//getJson
				JsonObject imageJson = getJson(imageString);
				
				//gets String info from json, if no result then array is empty
				JsonArray images = imageJson.getAsJsonArray("images_results");
				
				JsonObject image1 = (JsonObject) images.get(0);
				JsonObject image2 = (JsonObject) images.get(1);
				JsonObject image3 = (JsonObject) images.get(2);
				JsonObject image4 = (JsonObject) images.get(3);
				JsonObject image5 = (JsonObject) images.get(4);
				
				request.setAttribute("myImages1", image1.get("original").getAsString());
				request.setAttribute("myImages2", image2.get("original").getAsString());
				request.setAttribute("myImages3", image3.get("original").getAsString());
				request.setAttribute("myImages4", image4.get("original").getAsString());
				request.setAttribute("myImages5", image5.get("original").getAsString());
				
				request.setAttribute("myLink1", image1.get("link").getAsString());
				request.setAttribute("myLink2", image2.get("link").getAsString());
				request.setAttribute("myLink3", image3.get("link").getAsString());
				request.setAttribute("myLink4", image4.get("link").getAsString());
				request.setAttribute("myLink5", image5.get("link").getAsString());
				
				List<String> imageList = new ArrayList<String>();
				imageList.add(image1.get("original").getAsString());
				imageList.add(image2.get("original").getAsString());
				imageList.add(image3.get("original").getAsString());
				imageList.add(image4.get("original").getAsString());
				imageList.add(image5.get("original").getAsString());
				
				getter_fc.setImages(imageList);
				
			}
			else {
				request.setAttribute("f", "empty");
				request.setAttribute("c", "empty");
				request.setAttribute("precipitation", "empty");
				request.setAttribute("graphic", "empty");
				request.setAttribute("forecast", "empty");
				request.setAttribute("myImages", "empty");
			}
		}
		request.getRequestDispatcher("AnalyticalInformation.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}		
	//method for checking if the search input is a zip
	public boolean isZip(String searchbar){
		boolean isZip = false;
		if(searchbar.length() == 5){
			for(int i=0; i<5; i++){
				if(Character.isDigit(searchbar.charAt(i))){
					isZip = true;
				}
				else{
					return false;
				}
			}
			return isZip;
		}
		else{
			return false;
		}
	}
	
	//method for generating the url given whether the search is by zip or city
	public String generateUrl(String searchbar, boolean isZip, String key) throws UnsupportedEncodingException{
		String url;
		if(isZip){
			url = "http://api.openweathermap.org/data/2.5/weather?zip=" + URLEncoder.encode(searchbar, "UTF-8") + "&appid=" + key;
		}
		else{
			url = "http://api.openweathermap.org/data/2.5/weather?q=" + URLEncoder.encode(searchbar, "UTF-8")+ "&appid=" + key;
		}
		return url;
	}
	
	//method for getting all the information from the json in An array of Strings
	public String[] getInfo(JsonObject json){
		String[] info = new String[5];
		
		String format = json.toString();
		
		if(format.equals("{}")){
			info[0] = "No weather data found";
			return info;
		}
		
		//add city name to index 0
		info[0] = json.get("name").getAsString();
		
		//add temperature (f or c) to index 1 and 2, respectively
		JsonObject main = json.get("main").getAsJsonObject();
		String kelvin = main.get("temp").getAsString();
		double kelvinDouble = Double.parseDouble(kelvin);
		double f = (kelvinDouble - 273.15) * 9/5 + 32;
		f = Math.round(f);
		info[1] = String.valueOf(f);
		double c = kelvinDouble - 273.15;
		c = Math.round(c);
		info[2] = String.valueOf(c);
		
		//add precipitation to index 3
		JsonArray weather = json.get("weather").getAsJsonArray();
		JsonObject object = weather.get(0).getAsJsonObject();
		info[3] = object.get("main").getAsString();
		
		//add graphic to index 4
		info[4] = object.get("icon").getAsString();
		
		return info;
	}
	
	//method for getting the json
	public JsonObject getJson(String urlString){
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn;
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				JsonObject json = new JsonObject();
				return json;
				//throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
		
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
		
			String output;
			String full = "";
			
			while ((output = br.readLine()) != null) {
				full += output;				
			}
			
			JsonObject json = new JsonParser().parse(full).getAsJsonObject();
			return json;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JsonObject json = new JsonObject();
		return json;
		//JsonObject json = new JsonParser().parse(full).getAsJsonObject();
		//return json;
	}
}
