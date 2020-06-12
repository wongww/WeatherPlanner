package csci310;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class jdbcController {
	static Connection connection = null;
	public static Connection connect() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://google/project2?cloudSqlInstance=aqueous-radio-273902:us-west1:project2&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false&user=user&password=1234");
		return connection;
	}
	public String hashPassword() throws SQLException, ClassNotFoundException {
		connection = jdbcController.connect();
		String password = "";
		Statement stmt = connection.createStatement();
		stmt.execute("select passwordHash from user where username='test'");
		ResultSet rs = stmt.getResultSet();
		rs.next();
		password = rs.getString("passwordHash");
		return password;
	}
}
