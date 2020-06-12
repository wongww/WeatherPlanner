package csci310;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class City {
	
	private String name;
	private String country;
	private String zipCode;
	private int curTemp;
	private String weatherDesc;
	private String weatherIcon;
	private int likes;
	
	public City(String name, String country, String zipCode)
	{
		this.name = name;
		this.country = country;
		this.zipCode = zipCode;
		this.likes = 0;
	}
	
	public String getName()
	{
		return name;
	}

	public String getCountry()
	{
		return country;
	}
	
	public String getZipCode()
	{
		return zipCode;
	}
	
	public int getCurTemp()
	{
		return curTemp;
	}
	
	public String getWeatherDesc()
	{
		return weatherDesc;
	}
	
	public String getWeatherIcon()
	{
		return weatherIcon;
	}
	
	public void setCurTemp(int temp)
	{
		curTemp = temp;
	}
	
	public void setWeatherDesc(String desc)
	{
		weatherDesc = desc;
	}
	
	public void setWeatherIcon(String icon)
	{
		weatherIcon = icon;
	}
	
	public int getLikes()
	{
		return likes;
	}
	
	public void setLikes(int likes)
	{
		this.likes = likes;
	}
	
	public void addLike()
	{
		likes++;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = jdbcController.connect();
			
			Statement stmt = connection.createStatement();
			String username = LoginController.curUser;
			
			ResultSet rs = stmt.executeQuery("select * from likes where username='" + username + "' and city='" + name + "'");
			
			if (!rs.next())
			{
				System.out.println("INSERT INTO likes (username, city, count) value ('" + username + "', '"+ name + "'," + likes + ")");
				stmt.executeUpdate("INSERT INTO likes (username, city, count) value ('" + username + "', '"+ name + "'," + likes + ")");
			}
			else
			{
				System.out.println("UPDATE likes SET count = " + likes + " WHERE username='" + username 
						+ "' city='" + name + "'");
				stmt.executeUpdate("UPDATE likes SET count = " + likes + " WHERE username='" + username 
						+ "' and city='" + name + "'" );
			}
			

		}
		catch(Exception e)
		{
			
			System.out.println("error in adding likes");
			e.printStackTrace();
		}
	}
	
	public void removeLike()
	{
		likes--;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = jdbcController.connect();
			
			Statement stmt = connection.createStatement();
			String username = LoginController.curUser;
			
			
			stmt.executeUpdate("UPDATE likes SET count = " + likes + " WHERE username='" + username 
					+ "' and city='" + name +"'" );
			
		}
		catch(Exception e)
		{
			System.out.println("error in removing likes");
			e.printStackTrace();
		}
	}
}
