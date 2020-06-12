package csci310;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class LoginControllerTest  {
	public void LoginTest() throws SQLException, ClassNotFoundException {
		Connection connection;
		jdbcController controller = new jdbcController();
		connection = controller.connect();
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
