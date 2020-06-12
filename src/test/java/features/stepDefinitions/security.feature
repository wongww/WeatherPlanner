Feature: The Weather Planning web application uses only HTTPS

	Scenario: Visit https://localhost:8443
		When the user enters the URL
		Then the user is connected to the web application using HTTPS
	Scenario: User cannot access any functionality without being logged in
		Given the user connects to home page and is not logged in
		Then the user is connected to the login page through HTTPS
	Scenario: Connect to the the web application using HTTP
		Given the user enters the URL http://localhost:8443
		Then the user is not connected to the web application
	Scenario: Login Success
		Given the user logs in with valid credentials
		Then the user is logged in 
		And is on the login page
	Scenario: Login Unccessful
		Given the user logs in with invalid credentials
		Then the user is not logged in
		And is on the login page
	Scenario: User can register for an account
		Given the user is on the register page
		Then the user registers with valid credentials
		And the user is redirected to the home page
	Scenario: User registers with mismatch password
		Given the user is on the register page
		And registers with different passwords
		Then the user is not redirected to the home page
		And the user did not register
	Scenario: Reject SQL attak
		Given the user is on the login page
		And the user performs an SQL injection attack
		Then the user is not logged in
	Scenario: Missing input
		Given the user is on the register page
		And at least one input is blank
		Then the user did not register
	Scenario: password hash
		Given the user logs in with valid credentials
		Then the password in the database is hashed
	Scenario: user can log out
		Given the user logs in with valid credentials
		Then the user can log out
	
	
	