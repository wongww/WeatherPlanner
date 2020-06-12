package csci310;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import csci310.jdbcController;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class jdbcControllerTest {
	

	public void testConnection() throws SQLException, ClassNotFoundException {
		Connection connection;
		jdbcController controller = new jdbcController();
		connection = controller.connect();
		Statement stmt = connection.createStatement();
	    stmt.execute("select * from users");
	    ResultSet rs = stmt.getResultSet();
		Assert.assertEquals(true, rs.next());
		connection.close();
	}
	public void testHashPassword() throws SQLException, ClassNotFoundException {
		Connection connection;
		jdbcController controller = new jdbcController();
		connection = controller.connect();
		Statement stmt = connection.createStatement();
		stmt.execute("select passwordHash from users where username='test'");
		ResultSet rs = stmt.getResultSet();
		rs.next();
		Assert.assertNotEquals("test", rs.getString("passwordHash"));
	}
	
}
