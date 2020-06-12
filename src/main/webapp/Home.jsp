<!DOCTYPE html>
<%@page import= "java.util.*" %>
<%@page import="csci310.HomePageController"%>
<html>
<head>
<title>Homepage</title>
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
h2{
	margin:0px;
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
	width:40%;
	height:40%;
	border-radius:25px;
	border:none;
	padding-left:5%;
	padding-right:5%;
	font-size:50%;
}
#result{
	height:50%;
}
#inputs{
	height:20%;
}
#prevSearchbar{
	height:30%;
	width:100%;
}
#searchaction{
	width:30%;
	background-color:#8EE4AF;
	color:#EDF5E1;
	border:none;
	height:40%;
	border-radius:25px;
	vertical-align:middle;
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
#aRow{
vertical-align:middle;
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
  vertical-align:middle;
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
  border-radius:25px;
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
  border-radius:50%;
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
#prevSearch1, #prevSearch2, #prevSearch3, #prevSearch4{
	height:100%;
	width:20%;
	margin-right:2%;
	margin-left:2%;
	float:left;
	font-size:75%;
	border-style:solid;
	border-radius:25px;
	color:#EDF5E1;
}
#prevSearch1:hover, #prevSearch2:hover, #prevSearch3:hover, #prevSearch4:hover{
  	color: #5CDB95;
}
</style>
</head>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- If temperature scale is not declared yet, set it to farenheit. -->
<script>
//checks browser support
if (typeof(Storage) !== "undefined") {
	//check if tempscale is null, if so, set to f
  	if(localStorage.getItem("tempscale") != "f" && localStorage.getItem("tempscale") !="c"){
  		localStorage.setItem("tempscale", "f");
  	}
} else {
  alert("Sorry, your browser does not support Web Storage...");
}
<% 
List<Cookie> history = HomePageController.getCookies();
%>
if(localStorage.getItem("loggedIn") == null || localStorage.getItem("loggedIn") == "false"){
	window.location.replace("https://localhost:8443");
}

var fromServlet = "false";
if("<%= request.getAttribute("fromServlet") %>" == "true"){
	fromServlet = "true";
}

</script>

<body onload = "javascript:checkCookies()">

<div id=footer>
	<div id=navbar>
		<ul>
			<li><a class="active" href="Home.jsp" id=floatleft>Homepage</a></li>
			<li><a href="VacationPlanning.jsp" id=floatleft>Vacation Planning</a></li>
			<li><a href="ActivitiesPlanning.jsp" id=floatleft>Activities Planning</a></li>
			<li><a href="AnalyticalInformation.jsp" id=floatleft>Analytical Information</a></li>
			<li style="float:right"><a href="index.jsp" id=floatright>Log In</a></li>

		</ul>
	</div>
</div>


<div id=content>
	<div id=innercontent>
		<div id=prevSearchbar>
			<a href="javascript:selectPrev1()" id = link1>
				<div id=prevSearch1>
						<div id=prevSearch1city></div>
				</div>
			</a>
			<a href="javascript:selectPrev2()" id = link2>
				<div id=prevSearch2>
						<div id=prevSearch2city></div>
				</div>
			</a>
			<a href="javascript:selectPrev3()" id = link3>
				<div id=prevSearch3>
						<div id=prevSearch3city></div>
				</div>
			</a>
			<a href="javascript:selectPrev4()" id = link4>
				<div id=prevSearch4>
						<div id=prevSearch4city></div>
				</div>
			</a>
		</div>
		<br>
		<div id=result>
			<h2><div id=dateandtime></div></h2>
			<div id=resultsinner>
				<div id=graphic></div>
				<h2><div id=temp></div></h2>
			</div>
			<h2><div id=precipitation></div></h2>
		</div>
		<div id=inputs>
			<form class=in-line name=myform id=inputform action="HomePageController" method="post">
	  			<input type="text" required id="searchbar" name="searchbar" placeholder="Enter location (city or zip)"><br><br>
	  			<span id=temp1>&degC</span>
	  			<label class="switch">
  					<input id="units" type="checkbox" onclick="toggle()">
  					<span class="slider"></span>
				</label>
				<span id=temp2>&degF</span>
				<span id=usernameInput></span>
	  			<input id=searchaction type="submit" value="Show me the Weather">
			</form> 
		</div>
	</div>
</div>

<!-- for loading the previous 4 searches -->
<script>
//get number of previous searches to display
var numPrev = <%= request.getAttribute("numPrev") %>;

//display is dependent on how many prevSearches to display since we want to center them
//modify the #prevSearchbar
if(numPrev == 1){
	
}

else if(numPrev == 2){
	
}

else if(numPrev == 3){
	
}

else if(numPrev == 4){
	
}

</script>

<script>
if (localStorage.getItem("tempscale") == "f")
{
	document.getElementById("units").checked = true;

}
else if (localStorage.getItem("tempscale") == "c")
{
document.getElementById("units").checked = false;
}

function toggle(){
	if(localStorage.getItem("tempscale") == "f"){
		localStorage.setItem("tempscale","c");
	}
	else{
		localStorage.setItem("tempscale","f");
	}
}
</script>

<script>
var city = "<%= request.getAttribute("city") %>";
var f = "<%= request.getAttribute("f") %>";
var c = "<%= request.getAttribute("c") %>";
var precipitation = "<%= request.getAttribute("precipitation") %>";
var graphic = "<%= request.getAttribute("graphic") %>";
if(city == "null"){
	
}
else if(f == "empty"){
	document.getElementById("dateandtime").innerHTML = "No weather data found";
	document.getElementById("temp").innerHTML = "";
	document.getElementById("precipitation").innerHTML = "";
	document.getElementById('searchbar').style.borderColor = "#ff6347";
	
}
else{
	document.getElementById("dateandtime").innerHTML += city;
	document.getElementById("dateandtime").innerHTML += ", ";
	var dt = new Date();
	document.getElementById("dateandtime").innerHTML += dt.toLocaleDateString();
	
	if(localStorage.getItem("tempscale") == "f"){
		document.getElementById("temp").innerHTML += f;
		document.getElementById("temp").innerHTML += "&deg;F";
	}
	else if(localStorage.getItem("tempscale") == "c"){
		document.getElementById("temp").innerHTML += c;
		document.getElementById("temp").innerHTML += "&deg;C";
	}
	
	document.getElementById("precipitation").innerHTML += precipitation;
	
	var iconid = graphic;
	var iconstring = "http://openweathermap.org/img/wn/" + iconid + "@2x.png";
	document.getElementById("graphic").innerHTML += "<img src=\"" + iconstring + "\" id=icon alt=\"icon\">";
}
</script>
<script>
function checkCookies(){
	if(<%= history.size() == 0 %>){
		document.getElementById("Searches").style.display = "none";
	}
	else{
		document.getElementById("Searches").style.display -= "none";
	}
}
function prevSearch(){
	var myList = document.getElementById("Searches");
	document.getElementById("searchbar").value = myList.options[myList.selectedIndex].text;
	document.getElementById("inputform").submit();
}
</script>

<script>
	if(localStorage.getItem("loggedIn") == "true"){
		document.getElementById("floatright").innerHTML = "Log Out";
		var username = localStorage.getItem("loggedUser");
		
		var inject = "<input type=\"hidden\" id=\"usernameInput\" name=\"usernameInput\" value=" + username + ">"; 
		
		document.getElementById("usernameInput").innerHTML += inject;
		
		//<input type="hidden" id="usernameInput" name="usernameInput" value=username>
	}
</script>

<script>
var loggedUser = localStorage.getItem("loggedUser");

var loggedUsercity1 = loggedUser + "query1city";
var loggedUserf1 = loggedUser + "query1f";
var loggedUserc1 = loggedUser + "query1c";
var loggedUsergraphic1 = loggedUser + "query1graphic";

if(fromServlet == "true"){
	var query1city = "<%= request.getAttribute("query1city") %>";
	var query1f = "<%= request.getAttribute("query1f") %>";
	var query1c = "<%= request.getAttribute("query1c") %>";
	var query1graphic = "<%= request.getAttribute("query1graphic") %>";
	
	localStorage.setItem(loggedUsercity1, query1city);
	localStorage.setItem(loggedUserf1, query1f);
	localStorage.setItem(loggedUserc1, query1c);
	localStorage.setItem(loggedUsergraphic1, query1graphic);
}
else{
	var query1city = localStorage.getItem(loggedUsercity1);
	var query1f = localStorage.getItem(loggedUserf1);
	var query1c = localStorage.getItem(loggedUserc1);
	var query1graphic = localStorage.getItem(loggedUsergraphic1);
	
	localStorage.setItem(loggedUsercity1, query1city);
	localStorage.setItem(loggedUserf1, query1f);
	localStorage.setItem(loggedUserc1, query1c);
	localStorage.setItem(loggedUsergraphic1, query1graphic);
}

if(localStorage.getItem("query1city") != "null" && localStorage.getItem("query1city") != "No weather data found"
		&& localStorage.getItem(loggedUsercity1) != "No weather data found" && localStorage.getItem(loggedUsercity1) != "null"){	
	document.getElementById("prevSearch1city").innerHTML += localStorage.getItem(loggedUsercity1);
	
	if(localStorage.getItem("tempscale") == "f"){
		document.getElementById("prevSearch1").innerHTML += localStorage.getItem(loggedUserf1);
		document.getElementById("prevSearch1").innerHTML += "&deg;F";
	}
	else if(localStorage.getItem("tempscale") == "c"){
		document.getElementById("prevSearch1").innerHTML += localStorage.getItem(loggedUserc1);
		document.getElementById("prevSearch1").innerHTML += "&deg;C";
	}
	
	var query1iconid = localStorage.getItem(loggedUsergraphic1);
	var query1iconstring = "http://openweathermap.org/img/wn/" + query1iconid + "@2x.png";
	document.getElementById("prevSearch1").innerHTML += "<img src=\"" + query1iconstring + "\" id=icon alt=\"icon\">";
}
else
	{
	document.getElementById("prevSearch1").style.display = "None";
	}

var loggedUsercity2 = loggedUser + "query2city";
var loggedUserf2 = loggedUser + "query2f";
var loggedUserc2 = loggedUser + "query2c";
var loggedUsergraphic2 = loggedUser + "query2graphic";

if(fromServlet == "true"){
	var query2city = "<%= request.getAttribute("query2city") %>";
	var query2f = "<%= request.getAttribute("query2f") %>";
	var query2c = "<%= request.getAttribute("query2c") %>";
	var query2graphic = "<%= request.getAttribute("query2graphic") %>";
	
	localStorage.setItem(loggedUsercity2, query2city);
	localStorage.setItem(loggedUserf2, query2f);
	localStorage.setItem(loggedUserc2, query2c);
	localStorage.setItem(loggedUsergraphic2, query2graphic);
}
else{
	var query2city = localStorage.getItem(loggedUsercity2);
	var query2f = localStorage.getItem(loggedUserf2);
	var query2c = localStorage.getItem(loggedUserc2);
	var query2graphic = localStorage.getItem(loggedUsergraphic2);
	
	localStorage.setItem(loggedUsercity2, query2city);
	localStorage.setItem(loggedUserf2, query2f);
	localStorage.setItem(loggedUserc2, query2c);
	localStorage.setItem(loggedUsergraphic2, query2graphic);
}

if(localStorage.getItem("query2city") != "null" && localStorage.getItem("query2city") != "No weather data found"
	&& localStorage.getItem(loggedUsercity2) != "No weather data found" && localStorage.getItem(loggedUsercity2) != "null"){	
	document.getElementById("prevSearch2city").innerHTML += localStorage.getItem(loggedUsercity2);
	
	if(localStorage.getItem("tempscale") == "f"){
		document.getElementById("prevSearch2").innerHTML += localStorage.getItem(loggedUserf2);
		document.getElementById("prevSearch2").innerHTML += "&deg;F";
	}
	else if(localStorage.getItem("tempscale") == "c"){
		document.getElementById("prevSearch2").innerHTML += localStorage.getItem(loggedUserc2);
		document.getElementById("prevSearch2").innerHTML += "&deg;C";
	}
	
	var query2iconid = localStorage.getItem(loggedUsergraphic2);
	var query2iconstring = "http://openweathermap.org/img/wn/" + query2iconid + "@2x.png";
	document.getElementById("prevSearch2").innerHTML += "<img src=\"" + query2iconstring + "\" id=icon alt=\"icon\">";
}
else
{
document.getElementById("prevSearch2").style.display = "None";
}

var loggedUsercity3 = loggedUser + "query3city";
var loggedUserf3 = loggedUser + "query3f";
var loggedUserc3 = loggedUser + "query3c";
var loggedUsergraphic3 = loggedUser + "query3graphic";

if(fromServlet == "true"){
	var query3city = "<%= request.getAttribute("query3city") %>";
	var query3f = "<%= request.getAttribute("query3f") %>";
	var query3c = "<%= request.getAttribute("query3c") %>";
	var query3graphic = "<%= request.getAttribute("query3graphic") %>";
	
	localStorage.setItem(loggedUsercity3, query3city);
	localStorage.setItem(loggedUserf3, query3f);
	localStorage.setItem(loggedUserc3, query3c);
	localStorage.setItem(loggedUsergraphic3, query3graphic);
}
else{
	var query3city = localStorage.getItem(loggedUsercity3);
	var query3f = localStorage.getItem(loggedUserf3);
	var query3c = localStorage.getItem(loggedUserc3);
	var query3graphic = localStorage.getItem(loggedUsergraphic3);
	
	localStorage.setItem(loggedUsercity3, query3city);
	localStorage.setItem(loggedUserf3, query3f);
	localStorage.setItem(loggedUserc3, query3c);
	localStorage.setItem(loggedUsergraphic3, query3graphic);
}

if(localStorage.getItem("query3city") != "null" && localStorage.getItem("query3city") != "No weather data found"
	&& localStorage.getItem(loggedUsercity3) != "No weather data found" && localStorage.getItem(loggedUsercity3) != "null"){	
	document.getElementById("prevSearch3city").innerHTML += localStorage.getItem(loggedUsercity3);
	
	if(localStorage.getItem("tempscale") == "f"){
		document.getElementById("prevSearch3").innerHTML += localStorage.getItem(loggedUserf3);
		document.getElementById("prevSearch3").innerHTML += "&deg;F";
	}
	else if(localStorage.getItem("tempscale") == "c"){
		document.getElementById("prevSearch3").innerHTML += localStorage.getItem(loggedUserc3);
		document.getElementById("prevSearch3").innerHTML += "&deg;C";
	}
	
	var query3iconid = localStorage.getItem(loggedUsergraphic3);
	var query3iconstring = "http://openweathermap.org/img/wn/" + query3iconid + "@2x.png";
	document.getElementById("prevSearch3").innerHTML += "<img src=\"" + query3iconstring + "\" id=icon alt=\"icon\">";
}
else
{
document.getElementById("prevSearch3").style.display = "None";
}

var loggedUsercity4 = loggedUser + "query4city";
var loggedUserf4 = loggedUser + "query4f";
var loggedUserc4 = loggedUser + "query4c";
var loggedUsergraphic4 = loggedUser + "query4graphic";

if(fromServlet == "true"){
	var query4city = "<%= request.getAttribute("query4city") %>";
	var query4f = "<%= request.getAttribute("query4f") %>";
	var query4c = "<%= request.getAttribute("query4c") %>";
	var query4graphic = "<%= request.getAttribute("query4graphic") %>";
	
	localStorage.setItem(loggedUsercity4, query4city);
	localStorage.setItem(loggedUserf4, query4f);
	localStorage.setItem(loggedUserc4, query4c);
	localStorage.setItem(loggedUsergraphic4, query4graphic);
}
else{
	var query4city = localStorage.getItem(loggedUsercity4);
	var query4f = localStorage.getItem(loggedUserf4);
	var query4c = localStorage.getItem(loggedUserc4);
	var query4graphic = localStorage.getItem(loggedUsergraphic4);
	
	localStorage.setItem(loggedUsercity4, query4city);
	localStorage.setItem(loggedUserf4, query4f);
	localStorage.setItem(loggedUserc4, query4c);
	localStorage.setItem(loggedUsergraphic4, query4graphic);
}

if(localStorage.getItem("query4city") != "null" && localStorage.getItem("query4city") != "No weather data found"
	&& localStorage.getItem(loggedUsercity4) != "No weather data found" && localStorage.getItem(loggedUsercity4) != "null"){
	
	console.log(localStorage.getItem(loggedUsercity4));
	document.getElementById("prevSearch4city").innerHTML += localStorage.getItem(loggedUsercity4);
	
	if(localStorage.getItem("tempscale") == "f"){
		document.getElementById("prevSearch4").innerHTML += localStorage.getItem(loggedUserf4);
		document.getElementById("prevSearch4").innerHTML += "&deg;F";
	}
	else if(localStorage.getItem("tempscale") == "c"){
		document.getElementById("prevSearch4").innerHTML += localStorage.getItem(loggedUserc4);
		document.getElementById("prevSearch4").innerHTML += "&deg;C";
	}
	
	var query4iconid = localStorage.getItem(loggedUsergraphic4);
	var query4iconstring = "http://openweathermap.org/img/wn/" + query4iconid + "@2x.png";
	document.getElementById("prevSearch4").innerHTML += "<img src=\"" + query4iconstring + "\" id=icon alt=\"icon\">";
}
else
{
document.getElementById("prevSearch4").style.display = "None";
}
</script>

<script>
	function selectPrev1(){
		var prevSearch1 = document.getElementById("prevSearch1city").innerHTML;
		document.getElementById("searchbar").value = prevSearch1;
		document.getElementById("inputform").submit();
	}
	function selectPrev2(){
		var prevSearch2 = document.getElementById("prevSearch2city").innerHTML;
		document.getElementById("searchbar").value = prevSearch2;
		document.getElementById("inputform").submit();
	}
	function selectPrev3(){
		var prevSearch3 = document.getElementById("prevSearch3city").innerHTML;
		document.getElementById("searchbar").value = prevSearch3;
		document.getElementById("inputform").submit();
	}
	function selectPrev4(){
		var prevSearch4 = document.getElementById("prevSearch4city").innerHTML;
		document.getElementById("searchbar").value = prevSearch4;
		document.getElementById("inputform").submit();
	}
</script>

<script>
	var prevSearch1 = document.getElementById("prevSearch1city").innerHTML;
	var prevSearch2 = document.getElementById("prevSearch2city").innerHTML;
	var prevSearch3 = document.getElementById("prevSearch3city").innerHTML;
	var prevSearch4 = document.getElementById("prevSearch4city").innerHTML;
	if(prevSearch1 == "null" || prevSearch1 == "No weather data found"){
		document.getElementById("prevSearch1").innerHTML = "";
	}
	if(prevSearch2 == "null" || prevSearch2 == "No weather data found"){
		document.getElementById("prevSearch2").innerHTML = "";
	}
	if(prevSearch3 == "null" || prevSearch3 == "No weather data found"){
		document.getElementById("prevSearch3").innerHTML = "";
	}
	if(prevSearch4 == "null" || prevSearch4 == "No weather data found"){
		document.getElementById("prevSearch4").innerHTML = "";
	}
</script>
</body>
</html>