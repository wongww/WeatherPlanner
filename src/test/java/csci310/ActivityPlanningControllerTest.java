package csci310;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ActivityPlanningControllerTest {
	
	@Test
	public void populateLikesTest() throws ClassNotFoundException, SQLException
	{
		
		List<CityResult> cities = new ArrayList<CityResult>();
		
		cities.add(new CityResult("Los Angeles","USA", "90089", 5, 5, 5));
		CityList list = new CityList(cities);
		
		ActivityPlanningController.results = list;
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection;
		jdbcController controller = new jdbcController();
		connection = controller.connect();
		Statement stmt = connection.createStatement();


		stmt.executeUpdate("UPDATE likes set count=5 where username='likestest'");
		
		LoginController.curUser = "likestest";
		
		ActivityPlanningController.populateLikes();
		
		Assert.assertEquals(list.getCities().get(0).getLikes(), 5);
		
		
	}
	

}
