package csci310;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	static Connection connection = null;
	
	public RegisterController() {
    	super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get information from user input
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String verifyPassword = request.getParameter("verifyPassword");
		
		//verify that password and verifyPassword are the same
		//if so, continue. Else, return an error to the front-end
		if(!password.equals(verifyPassword)){
			//return an error to front-end
			System.out.println("passwords arent the same");
			request.setAttribute("registerMessage", "Passwords do not match. Registration Failed.");
			request.getRequestDispatcher("Register.jsp").forward(request, response);
			return;
		}
		
		//if passwords are the same, check if username already exists. If so, return an error to the front-end
		boolean usernameExists = false;
		//check for username
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = jdbcController.connect();
			
			Statement myStmt = connection.createStatement();
			ResultSet myRs = myStmt.executeQuery("select * from user where username='" + username + "'");
			while(myRs.next()){
				//System.out.println(myRs.getString("username"));
				if(myRs.getString("username").equals(username)){
					usernameExists = true;
					System.out.println("username already exists");
					request.setAttribute("registerMessage", "Username already exists. Registration Failed.");
					request.getRequestDispatcher("Register.jsp").forward(request, response);
					return;
				}
				else{
					System.out.println("username doesn't exist yet");
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		//if username does not exist, hash password and store username and password hash and log user in
		if(!usernameExists){
			request.setAttribute("loggedIn", "true");
			request.setAttribute("loggedUser", username);
			//hash password
			String passwordHash = Integer.toString(password.hashCode());
			
			//store username and passwordHash in database
			try{
				Class.forName("com.mysql.jdbc.Driver");
				connection = jdbcController.connect();
				Statement myStmt3 = connection.createStatement();
				
				String stmt3 = "insert into user (username, passwordHash) values ('" + username + "','" + passwordHash + "');";
		        myStmt3.executeUpdate(stmt3);
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		request.getRequestDispatcher("Register.jsp").forward(request, response);
	}
}
