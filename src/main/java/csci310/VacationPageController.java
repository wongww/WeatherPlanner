package csci310;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;


public class VacationPageController extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static CityList results;

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
    	
    	if (LoginController.curUser == null)
    	{
    		LoginController.curUser = username;
    	}
		if (request.getParameter("cname") != null)
		{
			String name = request.getParameter("cname");
			FavoritesServlet.addFavorites(name, "-----");
			for(CityResult cr : results.getCities()) {
				if(cr.getName().equals(name)) {
					FavoritesServlet.getFavorite(name, "-----").setLikes(cr.getLikes());;
				}
			}
		}
		else if (request.getParameter("rname") != null)
		{
			String name = request.getParameter("rname");
			FavoritesServlet.removeFavorite(name, "-----");
		}	
		else if (request.getParameter("like") != null)
		{
			int id = Integer.parseInt(request.getParameter("like"));
			results.getCities().get(id).addLike();
			if (FavoritesServlet.getFavorite(results.getCities().get(id).getName(), "-----") != null)
			{
				FavoritesServlet.getFavorite(results.getCities().get(id).getName(), "-----").addLike();
			}
		}
		else if (request.getParameter("unlike") != null)
		{
			int id = Integer.parseInt(request.getParameter("unlike"));
			results.getCities().get(id).removeLike();
			if (FavoritesServlet.getFavorite(results.getCities().get(id).getName(), "-----") != null)
			{
				FavoritesServlet.getFavorite(results.getCities().get(id).getName(), "-----").removeLike();
			}
		}
		else
		{
			System.out.println("here");
			int lowRange = -1;
			int highRange = -1;
			int numResults = -1;
			int radius = -1;
			try
			{
				lowRange = Integer.parseInt(request.getParameter("lowrange"));
				request.setAttribute("lowErr", false);
			}
			catch(Exception e)
			{
				request.setAttribute("lowErr", true);
			}
			try
			{
				highRange = Integer.parseInt(request.getParameter("highrange"));
				request.setAttribute("highErr", false);
			}
			catch(Exception e)
			{
				request.setAttribute("highErr", true);
			}
			try
			{
				numResults = Integer.parseInt(request.getParameter("numresults"));
				request.setAttribute("numErr", false);
			}
			catch(Exception e)
			{
				request.setAttribute("numErr", true);
			}
			
			try
			{
				radius = Integer.parseInt(request.getParameter("radius"));
				request.setAttribute("radErr", false);
			}
			catch(Exception e)
			{
				request.setAttribute("radErr", true);
			}
			
			String curLoc =  request.getParameter("location");
			double latC = Double.parseDouble(request.getParameter("lat"));
			double longC = Double.parseDouble(request.getParameter("long"));
			
			
			String unitVal = request.getParameter("units");
			
			
			if (lowRange != -1 && highRange != -1 && radius !=-1)
			{
				System.out.println(unitVal);
				
				// Create cookie 
			
				// Set expiry date after 24 Hrs
	 

				// Add cookie when it doesn't exist in the list
				
				
				if (unitVal != null && unitVal.equals("on"))
				{
					lowRange = (int) ((lowRange-32.0)*(5.0/9.0) + 273.15);
					highRange = (int) ((highRange-32.0)*(5.0/9.0) + 273.15);	
					System.out.println(lowRange + " " + highRange);
					request.setAttribute("units", "f");
				}
				else
				{
					lowRange += 273.15;
					highRange += 273.15;
					request.setAttribute("units", "c");
				}
				
				
				
				vacationSearch(lowRange, highRange, radius, latC, longC);
				populateLikes();
				request.setAttribute("results", results);
				
				if(results == null){
					request.setAttribute("error", "error");
				}
				
			}
			request.getRequestDispatcher("/VacationPlanning.jsp").forward(request, response);
			
		}
		
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
	
	
	public static CityList getResults()
	{
		return results;
	}
	
	public static void populateLikes()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = jdbcController.connect();
			
			Statement stmt = connection.createStatement();
			
			String username = LoginController.curUser;
			
			for (CityResult c : results.getCities())
			{
				ResultSet result = stmt.executeQuery("select * from likes where username='" + username + "' and city='" + c.getName() + "'");
	
				if (result.next())
				{
					c.setLikes(result.getInt("count"));
				}
				
			}
		}
		catch(Exception e)
		{
			System.out.println("error populating likes");
		}
	}
	

	
	public static void vacationSearch(int rangeLow, int rangeHigh, int radius, double latC, double longC) throws IOException
	{
		List<CityResult> list = new ArrayList<CityResult>();
		
		/*for (int i = 0; i < 10; i++)
		{
			String name = "city" + i;
			list.add(new CityResult(name, "usa", "95070", 10, 10, 10));
		}
		*/
		results = Searcher.vacationSearch(rangeLow, rangeHigh,  radius, latC, longC);
		
	}
	
	
	
 
    

	

}
