package csci310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.*;


/**
 * Servlet implementation class Book
 */
@WebServlet("/HomePageController")
public class HomePageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//stuff for jdbc
    static Connection connection = null;
	
	public String key = "e7c34c987487b3afc299eb93974772e9";
	public String searchbar;
	protected static List<Cookie> cookies = new ArrayList<Cookie>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	public HomePageController() {
    	super();
        // TODO Auto-generated constructor stub
    }
	
    public HomePageController(String searchbar) {
    	super();
    	this.searchbar = searchbar;
        // TODO Auto-generated constructor stub
    }
    public static List<Cookie> getCookies(){
    	return cookies;
    }
    public static boolean cookieExists(Cookie c) {
    	for(int i = 0; i < cookies.size(); i++) {
    		if(cookies.get(i).getValue().equals(c.getValue())) {
    			return true;
    		}
    	}
    	return false;
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//get searchbar
		searchbar = request.getParameter("searchbar");
		request.setAttribute("fromServlet", "true");
		
		String usernameInput = request.getParameter("usernameInput");
		String key = "e7c34c987487b3afc299eb93974772e9";
		
		// Create cookie      
		Cookie searchBar = new Cookie("searchbar", request.getParameter("searchbar"));

		// Set expiry date after 24 Hrs
		searchBar.setMaxAge(60*60*24);  

		// Add cookie when it doesn't exist in the list
		if(cookieExists(searchBar) == false) {
			cookies.add(searchBar);
			System.out.println("added " + searchBar.getValue());
		}
		
		//create url
		String urlString = generateUrl(searchbar, isZip(searchbar), key);
		
		//getJson
		JsonObject json = getJson(urlString);
		
		//gets String info from json, if no result then array is empty
		String[] info = getInfo(json);
		
		//we have cityname, temp, graphic, username 
		String cityCheck = info[0];
		int nullCounter = 0;
		
		String city1 = "";
		String city2 = "";
		String city3 = "";
		String city4 = "";
		
		//1. check if city already exists
		boolean cityExists = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = jdbcController.connect();
			
			Statement myStmt = connection.createStatement();
			ResultSet myRs = myStmt.executeQuery("select * from user where username='" + usernameInput + "'");
			while(myRs.next()){
				
				city1 = myRs.getString("query1");
				city2 = myRs.getString("query2");
				city3 = myRs.getString("query3");
				city4 = myRs.getString("query4");
				
				if(cityCheck.toLowerCase().equals(city1.toLowerCase())){
					cityExists = true;
				}
				if(cityCheck.toLowerCase().equals(city2.toLowerCase())){
					cityExists = true;			
				}
				if(cityCheck.toLowerCase().equals(city3.toLowerCase())){
					cityExists = true;
				}
				if(cityCheck.toLowerCase().equals(city4.toLowerCase())){
					cityExists = true;
				}
				
				if(city1.equals("null")){
					nullCounter++;
				}
				
				if(city2.equals("null")){
					nullCounter++;
				}
				
				if(city3.equals("null")){
					nullCounter++;
				}
				
				if(city4.equals("null")){
					nullCounter++;
				}
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		//if city doesn't exist yet, we add it
		if(!cityExists && !info[0].equals("No weather data found")){
			
			//store username and passwordHash in database
			try{
				Class.forName("com.mysql.jdbc.Driver");
				connection = jdbcController.connect();
				Statement myStmt3 = connection.createStatement();
				
				String stmt3 = "update user set query1=\'" + cityCheck + "\'"  + " where username=\'" + usernameInput + "\';";
		        myStmt3.executeUpdate(stmt3);
				
				//add to first spot, no need to rotate anything down
		        if(nullCounter != 4){
					//grab 1-3, shift them down
					stmt3 = "update user set query2=\'" + city1 + "\'"  + " where username=\'" + usernameInput + "\';";
			        myStmt3.executeUpdate(stmt3);
			        stmt3 = "update user set query3=\'" + city2 + "\'"  + " where username=\'" + usernameInput + "\';";
			        myStmt3.executeUpdate(stmt3);
			        stmt3 = "update user set query4=\'" + city3 + "\'"  + " where username=\'" + usernameInput + "\';";
			        myStmt3.executeUpdate(stmt3);
				}
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	
		
		String query1url = generateUrl(city1, isZip(city1), key);
		JsonObject query1json = getJson(query1url);
		String[] query1info = getInfo(query1json);
		request.setAttribute("query1city", query1info[0]);
		request.setAttribute("query1f", query1info[1]);
		request.setAttribute("query1c", query1info[2]);
		request.setAttribute("query1graphic", query1info[4]);
		
		String query2url = generateUrl(city2, isZip(city2), key);
		JsonObject query2json = getJson(query2url);
		String[] query2info = getInfo(query2json);
		request.setAttribute("query2city", query2info[0]);
		request.setAttribute("query2f", query2info[1]);
		request.setAttribute("query2c", query2info[2]);
		request.setAttribute("query2graphic", query2info[4]);
		
		String query3url = generateUrl(city3, isZip(city3), key);
		JsonObject query3json = getJson(query3url);
		String[] query3info = getInfo(query3json);
		request.setAttribute("query3city", query3info[0]);
		request.setAttribute("query3f", query3info[1]);
		request.setAttribute("query3c", query3info[2]);
		request.setAttribute("query3graphic", query3info[4]);

		String query4url = generateUrl(city4, isZip(city4), key);
		JsonObject query4json = getJson(query4url);
		String[] query4info = getInfo(query4json);
		request.setAttribute("query4city", query4info[0]);
		request.setAttribute("query4f", query4info[1]);
		request.setAttribute("query4c", query4info[2]);
		request.setAttribute("query4graphic", query4info[4]);
		
		//we grab new list of prevSearches and forward to frontend
		if(!cityExists && !info[0].equals("No weather data found")){
			request.setAttribute("query1city", info[0]);
			request.setAttribute("query1f", info[1]);
			request.setAttribute("query1c", info[2]);
			request.setAttribute("query1graphic", info[4]);
			
			request.setAttribute("query2city", query1info[0]);
			request.setAttribute("query2f", query1info[1]);
			request.setAttribute("query2c", query1info[2]);
			request.setAttribute("query2graphic", query1info[4]);
			
			request.setAttribute("query3city", query2info[0]);
			request.setAttribute("query3f", query2info[1]);
			request.setAttribute("query3c", query2info[2]);
			request.setAttribute("query3graphic", query2info[4]);
			
			request.setAttribute("query4city", query3info[0]);
			request.setAttribute("query4f", query3info[1]);
			request.setAttribute("query4c", query3info[2]);
			request.setAttribute("query4graphic", query3info[4]);
		}
		
		//forward to page
		if(!info[0].equals("No weather data found")){
			request.setAttribute("city", info[0]);
			request.setAttribute("f", info[1]);
			request.setAttribute("c", info[2]);
			request.setAttribute("precipitation", info[3]);
			request.setAttribute("graphic", info[4]);
		}
		else{
			request.setAttribute("city", info[0]);
			request.setAttribute("f", "empty");
			request.setAttribute("c", "empty");
			request.setAttribute("precipitation", "empty");
			request.setAttribute("graphic", "empty");
		}
		
		request.getRequestDispatcher("Home.jsp").forward(request, response);
	}

	//method for checking if the search input is a zip
	public boolean isZip(String searchbar){
		if(searchbar == null){
			return false;
		}
		
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
	public String generateUrl(String searchbar, boolean isZip, String key){
		String url;
		if(isZip){
			url = "http://api.openweathermap.org/data/2.5/weather?zip=" + searchbar + "&appid=" + key;
		}
		else{
			url = "http://api.openweathermap.org/data/2.5/weather?q=" + searchbar + "&appid=" + key;
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
	
	//getSearchbar
	public String getSearchbar(){
		return searchbar;
	}
	
	//getKey
	public String getKey(){
		return key;
	}
	
	
}
