package csci310;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Assert;
import org.junit.Test;

public class CityTest {
	
	
	@Test
	public void addLikeTest() throws ClassNotFoundException, SQLException
	{
		City c = new City("Los Angeles", "USA", "90089");
		
		c.setLikes(5);
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = jdbcController.connect();
		
		Statement stmt = connection.createStatement();
		String username = LoginController.curUser;
		
		stmt.executeUpdate("UPDATE likes set count=5 where username='likestest'");
		
		LoginController.curUser = "likestest";
		
		c.addLike();
		
		Assert.assertEquals(c.getLikes(), 6);
		
		ResultSet rs = stmt.executeQuery("select * from likes where username='likestest'");
		
		if (rs.next())
		{
			Assert.assertEquals(rs.getInt("count"), 6);
		}
	}
	
	@Test
	public void removeLikeTest() throws ClassNotFoundException, SQLException
	{
		City c = new City("Los Angeles", "USA", "90089");
		
		c.setLikes(6);
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = jdbcController.connect();
		
		Statement stmt = connection.createStatement();
		String username = LoginController.curUser;
		
		stmt.executeUpdate("UPDATE likes set count=6 where username='likestest'");
		
		c.removeLike();
		
		Assert.assertEquals(c.getLikes(), 5);
		
		ResultSet rs = stmt.executeQuery("select * from likes where username='likestest'");
		
		if (rs.next())
		{
			Assert.assertEquals(rs.getInt("count"), 5);
		}
	}

}
