package csci310;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Assert;

public class RegisterControllerTest {
	static Connection connection = null;

	public void testRegister() throws SQLException {
		jdbcController controller = new jdbcController();
		try {
			connection = controller.connect();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Statement stmt = connection.createStatement();
		stmt.execute("select * from user");
		ResultSet rs0 = stmt.getResultSet();
		int sizeBefore = rs0.getFetchSize();
		stmt.executeUpdate("INSERT INTO user " + "VALUES ('usertest', 'passwordtest', 'Rochester', 'Rochester Hills', 'Los Angeles', 'Pasadena', 1 )");
		
		//After execute check to see if entry increased by one
		stmt.execute("select * from user");
		ResultSet rs = stmt.getResultSet();
	    int sizeAfter = stmt.getFetchSize();
		Assert.assertEquals(sizeAfter, sizeBefore+1);
		connection.close();
	}
}
