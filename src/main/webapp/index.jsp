<!DOCTYPE html>
<%@page import= "java.util.*" %>
<%@page import="csci310.HomePageController"%>
<html>
<head>
<title>Login</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
.full-height {
  height: 100%;
}
html, body{
    height:100%;
    font-family: Optima, Helvetica, sans-serif;
}
body{
	background-color:#379683;
}
#title{
	height:10%;
	width:100%;
	text-align:center;
}
#content{
	width:100%;
	height:70%;
	text-align:center;
	color:#EDF5E1;
	font-size:200%;
}
#footer{
	width:100%;
	height:12%;
}
#navbar{
	width:90%;
	height:100%;
	margin-left:5%;
	margin-right:5%;
}
#link{
	width:20%;
	display: block;
	text-align:center;
	height:100%;
	float:left;
	margin-left:2.5%;
	margin-right:2.5%;
	margin-top:3%;
	color:#EDF5E1;
	text-decoration:none;
}
h1{
	color:#EDF5E1;
	font-size:350%;
}
#innercontent{
	width:50%;
	margin-left:25%;
	margin-right:25%;
	height:100%;
}
#inputs{
	width:100%;
	height:100%;
}
#searchbar{
	width:100%;
	height:40%;
	border-radius:25px;
	border:none;
}
#inputBox{
	width:70%;
	height:30%;
	border-radius:25px;
	border:none;
	padding-left:3%;
	padding-right:3%;
	margin-left:12%;
	margin-right:12%;
}
#result{
	height:70%;
}
#inputs{
	height:20%;
}
#searchaction{
	float: center;
	width:40%;
	background-color:#8EE4AF;
	color:#EDF5E1;
	border:none;
	height:40%;
	border-radius:25px;
}
#inputform{
	height:100%;
}
#resultsinner{
	height:30%;
}
#graphic{
	width:50%;
	height:100%;
	float:left;
}
#temp{
	float:left;
	width:50%;
	margin-top:7%;
	text-align:center;
}


#temp1{
	color:#EDF5E1;
}

#temp2{
	color:#EDF5E1;
}

#temp1{
	color:#EDF5E1;
}
#temp2{
	color:#EDF5E1;
}

::placeholder { /* Chrome, Firefox, Opera, Safari 10.1+ */
  color: #C0C0C0;
  opacity: 1; /* Firefox */
}
:-ms-input-placeholder { /* Internet Explorer 10-11 */
  color: #C0C0C0;
}
::-ms-input-placeholder { /* Microsoft Edge */
  color: #C0C0C0;
}
/* The switch - the box around the slider */
.switch {
  position: relative;
  display: inline-block;
  width: 60px;
  height: 34px;
}
/* Hide default HTML checkbox */
.switch input {
  opacity: 0;
  width: 0;
  height: 0;
}
/* The slider */
.slider {
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #ccc;
  -webkit-transition: .4s;
  transition: .4s;
}
.slider:before {
  position: absolute;
  content: "";
  height: 26px;
  width: 26px;
  left: 4px;
  bottom: 4px;
  background-color: white;
  -webkit-transition: .4s;
  transition: .4s;
}
input:checked + .slider {
  background-color: #2196F3;
}
input:focus + .slider {
  box-shadow: 0 0 1px #2196F3;
}
input:checked + .slider:before {
  -webkit-transform: translateX(26px);
  -ms-transform: translateX(26px);
  transform: translateX(26px);
}
.topnav {
  overflow: hidden;
  right: 0px;
  position: absolute;
 // background-color: #333;
  padding-right: 18px;
}

.topnav a {
  float: left;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}

.topnav a:hover {
  background-color: #ddd;
  color: black;
}

.topnav a.active {
  background-color: #4CAF50;
  color: white;
}
ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: #05386B;
  border-radius:25px;
}

li {
  float: left;
}

li a {
  display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

li a:hover:not(.active) {
  background-color: #8EE4AF;
}

.active {
  background-color:#5CDB95;
}
li > #floatright{
	float:right;
}



</style>
</head>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- If temperature scale is not declared yet, set it to farenheit. -->
<script>
//checks browser support
if (typeof(Storage) !== "undefined") {
	//check if tempscale is null, if so, set to f
  	if(localStorage.getItem("tempscale") == null){
  		localStorage.setItem("tempscale", "f");
  	}
} else {
  alert("Sorry, your browser does not support Web Storage...");
}
<% 
List<Cookie> history = HomePageController.getCookies();
%>

var loggedIn = "<%= request.getAttribute("loggedIn") %>";
var loggedUser = "<%= request.getAttribute("loggedUser") %>";
if(loggedIn == "true"){
	localStorage.setItem("loggedIn","true");
	localStorage.setItem("loggedUser", loggedUser);
	alert("login successful");
}

var loginMessage = "<%= request.getAttribute("loginMessage") %>";
if(loginMessage != "null"){
	alert(loginMessage);
}

</script>

<body onload = "javascript:checkCookies()">

<div id=footer>
	<div id=navbar>
		<ul>
			<li><a href="Home.jsp" id=floatleft>Homepage</a></li>
			<li><a href="VacationPlanning.jsp" id=floatleft>Vacation Planning</a></li>
			<li><a href="ActivitiesPlanning.jsp" id=floatleft>Activities Planning</a></li>
			<li><a href="AnalyticalInformation.jsp" id=floatleft>Analytical Information</a></li>
			<li style="float:right"><a class="active"  href="javascript:logoutCheck()" id=floatright>Log In</a></li>
			<li style="float:right"><a href="Register.jsp" id=registerButton>Register</a></li>
		</ul>
	</div>
</div>


<div id=content>
	<div id=innercontent>
		<div id=inputs>
			<form name=login id=inputform action="LoginController" method="post">
	  			<br>
            Username<input required type="text" id= "inputBox" name="username" placeholder="Username"><br/><br/> 
            Password<input required type="password" id= "inputBox" name ="password" placeholder="Password"><br/> 
            <br>
            <span id=temp2></span>
  			<input id=searchaction type="submit" value="Login"> 
			</form> 
		</div>
	</div>
</div>

<script>
	if(localStorage.getItem("loggedIn") == "true"){
		document.getElementById("floatright").innerHTML = "Log Out";
		document.getElementById("content").innerHTML = "Logged In";
		var element = document.getElementById("registerButton");
	    element.style.visibility = "hidden";
	}
	
	function logoutCheck(){
		if(localStorage.getItem("loggedIn") == "true"){
			localStorage.setItem("loggedIn", "false");
			localStorage.setItem("loggedUser", null);
			alert("Log out successful.");
			document.getElementById("floatright").innerHTML = "Log In";
			document.getElementById("content").innerHTML = "Logged out";
			var element = document.getElementById("registerButton");
		    element.style.visibility = "visible";
			document.getElementById("floatright").href = "index.jsp";
		}
	}
</script>


</body>
</html>