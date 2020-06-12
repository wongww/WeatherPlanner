package csci310;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class ValidateParameters
 */

public class ActivityPlanningController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    // Instantiate private member for list of cities
    public static CityList results;

       
   
    /**Process the HTTP GET request
     * 
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
			if (FavoritesServlet.getFavorite(results.getCities().get(id).getName(), "-----")!= null)
			{
				FavoritesServlet.getFavorite(results.getCities().get(id).getName(), "-----").removeLike();
			}
		}
		else
		{
			
	        String activity = request.getParameter("activity");
			int numResults = -1;
			int radius = -1;
			try {
				numResults = Integer.parseInt(request.getParameter("numresults"));
			}
			catch(Exception e)
			{
				request.setAttribute("numErr", true);
			}
			try {
				radius = Integer.parseInt(request.getParameter("radius"));
			}
			catch(Exception e)
			{
				request.setAttribute("radErr", true);
			}
			String curLoc =  request.getParameter("location");
			
			double latC = Double.parseDouble(request.getParameter("lat"));
			double longC = Double.parseDouble(request.getParameter("long"));
			
			String unitVal = request.getParameter("units");
			
			
			
			if(radius != -1) {
				// Create cookie
			
				// Set expiry date after 24 Hrs


				// Add cookie when it doesn't exist in the list
			/*	if(cookieExists(apc) == false) {
					cookies.add(apc);
					System.out.println("added " + apc.getValue());
				}
				*/
				
				if (unitVal != null && unitVal.equals("on"))
				{
					request.setAttribute("units", "f");
				}
				else
				{
					request.setAttribute("units", "c");
				}
		
				
				activitySearch(activity, latC, longC, radius);
				// System.out.println(results.getCities().size());
				populateLikes();
				request.setAttribute("results", results);
				if(results == null){
					System.out.println("here now");
					request.setAttribute("noresults", true);
				}
			}
			request.getRequestDispatcher("/ActivitiesPlanning.jsp").forward(request, response);  
		}
    }

    public static CityList getResults() {
		return results;
	}

    public static void activitySearch(String activity, double latC, double longC, int radius) throws IOException {
    	
		results = Searcher.activitySearch(activity, radius, latC, longC);
	}
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request,response);
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
				else
				{
					c.setLikes(0);
					stmt.executeUpdate("INSERT INTO likes (username, city, count) value ('" + username + "', '"+ c.getName() + "', 0);");
				}
			}
    	}
    	catch(Exception e)
    	{
    		System.out.println("error populating likes");
    	}
    	
    }
    
    
    
}
