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

@WebServlet("/LoginController")
public class LoginController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	static Connection connection = null;
	
	static String curUser;
	
	public LoginController() {
    	super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//checks if user exists
		boolean usernameExists = false;
		//check for username
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = jdbcController.connect();
			
			Statement myStmt = connection.createStatement();
			ResultSet myRs = myStmt.executeQuery("select * from user where username='" + username + "'");
			while(myRs.next()){
				if(myRs.getString("username").equals(username)){
					//if username exists, then we check password
					usernameExists = true;
					System.out.println("username exists");
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		if(!usernameExists){
			request.setAttribute("loginMessage", "Username doesn't exists. Login Failed.");
			System.out.println("username doesn't exist yet");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		
		//if username exists hash the password and comapare with passwordHash
		if(usernameExists){
			//hash password
			
			String passwordHash = Integer.toString(password.hashCode());
			boolean passwordCorrect = false;
			
			try{
				Class.forName("com.mysql.jdbc.Driver");
				connection = jdbcController.connect();
				
				Statement myStmt2 = connection.createStatement();
				ResultSet myRs2 = myStmt2.executeQuery("select * from user where username='" + username + "'");
				while(myRs2.next()){
					if(myRs2.getString("passwordHash").equals(passwordHash)){
						//if passwordHash exists, then we check password
						passwordCorrect = true;
						request.setAttribute("loggedIn", "true");
						request.setAttribute("loggedUser", username);
						curUser = username;
						System.out.println("password is correct");
						request.getRequestDispatcher("index.jsp").forward(request, response);
						return;
					}
					else{
						request.setAttribute("loginMessage", "Password doesn't match. Login Failed.");
						System.out.println("incorrect password");
						request.getRequestDispatcher("index.jsp").forward(request, response);
						return;
					}
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	
}

