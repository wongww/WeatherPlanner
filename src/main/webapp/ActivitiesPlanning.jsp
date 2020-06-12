<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="csci310.CityList"%>
<%@ page import ="csci310.CityResult"%>
<%@page import= "java.util.*" %>
<%@ page import ="csci310.ActivityPlanningController"%>
<%@page import="csci310.FavoritesServlet"%>
<!DOCTYPE html>
<html>
<head>
<title>Activities Planning</title>
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
	height:60%;
	text-align:center;
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
#contentright{
	width:45%;
	height:100%;
	float:left;
	margin-left:2.5%;
	margin-right:2.5%;
}
#contentleft{
	width:45%;
	height:100%;
	float:left;
	margin-left:2.5%;
	margin-right:2.5%;
	text-align:left;
}
#range1{
	display: inline-block;
}
#range2{
	display: inline-block;
}
.inputbox{
	float:right;
	clear:right;
	border-radius:25px;
	width:30%;
	padding-left:2%;
	padding-right:2%;
}
#bodytext{
	font-size:130%;
	color:#EDF5E1;
}
form{
	height:100%;
}
p.likes {
  line-height: .5;
}
label.arrowup {
	font-size: 20px; 
	-webkit-user-select: none; /* Chrome/Safari */        
	-moz-user-select: none; /* Firefox */
	-ms-user-select: none; /* IE10+ */
}
label.arrowdown {
	font-size: 18px;
	-webkit-user-select: none; /* Chrome/Safari */        
	-moz-user-select: none; /* Firefox */
	-ms-user-select: none; /* IE10+ */ 
}
#row{
	height:10%;
}
#toggletext{
	float:right;
}
temp{
	float:left;
	width:50%;
	margin-top:7%;
	text-align:center;
	
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
#temp{
	color:#EDF5E1;
	font-size:130%;
	bottom:0;
	vertical-align:middle;

}
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
td{
	border: 1px solid black;
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
#searchaction{
	float:left;
	width:100%;
	background-color:#8EE4AF;
	color:#EDF5E1;
	border:none;
	height:60%;
	border-radius:25px;
	
}
#toggletext{
	color:#EDF5E1;
}
pagination{
	margin: 10px;
	padding-left: 40px;
}
.pg-normal, .pg-selected{
	border: 2px solid #8EE4AF;
	background: #8EE4AF;  
	text-align: center; 
	border-radius: 50%;
	height: 10px;
	width: 10px; 
	padding: 10px; 
}

.pg-selected{
 background-color: #EDF5E1;
  border-radius: 50%;
  height: 10px;
  width: 10px;
  background: #fff;
  border: 2px solid #8EE4AF; 
  text-align: center;
  padding: 10px;
  margin: 5px; 


} .page span:hover{
  border-radius: 50%;
  height: 10px;
  background: #fff;
  border: 2px solid #8EE4AF; 
  text-align: center;
  padding: 10px;
  background-color:#8EE4AF ; 
  margin: 5px; 
}

.page .clicked {
  background-color: #EDF5E1;
  border-radius: 50%;
  height: 10px;
  width: 10px;
  background: #fff;
  border: 2px solid #8EE4AF; 
  text-align: center;
  padding: 10px;
} 
th{
	color:#EDF5E1;
}
#insert{
	color:#EDF5E1;
	font-size:130%;
}
#contentinner{
	height:100%;
	width:90%;
	margin-left:auto;
	margin-right:auto;
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

#myTable{
	width: 100%;
}

</style>	
</head>

<script type="text/javascript">
// Pager function 
function Pagination(tableName, numPerPage) {
    this.tableName = tableName;
    this.numPerPage = numPerPage;
    this.currentPage = 1;
    this.pages = 0;
    this.created = false;
    this.pagerName = 'pager';
    // display table
    this.display = function(from, to) {
    	let rows = document.getElementById("myTable").rows;
        for (var i = 1; i < rows.length; i++) {
            if (i < from || i > to) rows[i].style.display = 'none';
            else rows[i].style.display = '';
        }
     }
     this.showPage = function(pageNumber) {
        if (! this.created) {
         	return;
       	}
        var currClicked = document.getElementById('pg'+this.currentPage);
        currClicked.className = 'pg-normal';
        this.currentPage = pageNumber;
        var clicked = document.getElementById('pg'+this.currentPage);
        clicked.className = 'clicked';
        var from = (pageNumber - 1) * numPerPage + 1;
        var to = from + numPerPage - 1;
        this.display(from, to);
	}
     
     this.created = function() {
         var rows = document.getElementById(tableName).rows;
         this.pages = Math.ceil((rows.length - 1) / numPerPage);
         this.created = true;
     }
     
     
     this.nextPage = function() {
    	var start,
  	    limit,
  	    pagesCutOff = 5, 
  	    ceiling = Math.ceil(pagesCutOff / 2),
  	    floor = Math.floor(pagesCutOff / 2);
    	console.log("THIS.PAGES: " + this.pages);
		console.log("CURRENT PAGES:" + this.currentPage);
		if (this.currentPage == this.pages){
			//do nothing
		}
		else if (this.currentPage < this.pages) {
         	this.createPager(this.currentPage, this.currentPage+4);
			this.showPage(this.currentPage + 1);
         }
    	
    	else if(this.pages < pagesCutOff) {
  	        start = 1;
  	        limit = this.pages;
         	this.createPager(start, limit);
			this.showPage(this.currentPage + 1);
  	        
  	    } else if(this.currentPage >= 1 && this.currentPage <= ceiling) {
 
  	        start = 1;
  	        limit = pagesCutOff; 	
  	        this.createPager(start, limit);
			this.showPage(this.currentPage + 1);
			
  	    } else if((this.currentPage + floor) >= this.pages) {
  	  
  	        start = (this.pages - pagesCutOff+1);
  	        limit = this.pages;
	  	   	this.createPager(start, limit);
			this.showPage(this.currentPage + 1);
  	    } else {
  
  	        start = (this.currentPage - ceiling+1);
  	        limit = (this.currentPage + floor);
	  	   	this.createPager(start, limit);
			this.showPage(this.currentPage + 1);
  	    }
         
     }
    this.prevPage = function() {
    	var start,
  	    limit,
  	    pagesCutOff = 5, 
  	    ceiling = Math.ceil(pagesCutOff / 2),
  	    floor = Math.floor(pagesCutOff / 2);
    	if (this.currentPage == 1){
    		// do nothing
    	}
    	else if (this.currentPage > 1){
        	this.createPager(this.currentPage-4, this.currentPage);
        	this.showPage(this.currentPage - 1);
        }
    	else if(this.pages < pagesCutOff) {
  	    	
  	        start = 1;
  	        limit = this.pages;
         	this.createPager(start, limit);
			this.showPage(this.currentPage - 1);
  	        
  	    } else if(this.currentPage >= 1 && this.currentPage <= ceiling) {
  	        start = 1;
  	        limit = pagesCutOff; 	
  	        this.createPager(start, limit);
			this.showPage(this.currentPage - 1);
			
  	    } else if((this.currentPage + floor) >= this.pages) {
  	        start = (this.pages - pagesCutOff+1);
  	        limit = this.pages;
	  	   	this.createPager(start, limit);
			this.showPage(this.currentPage - 1);
  	    } else {
  	        start = (this.currentPage - ceiling+1);
  	        limit = (this.currentPage + floor);
	  	   	this.createPager(start, limit);
			this.showPage(this.currentPage - 1);
  	    }
    }
    
    this.createPager = function(start, end){
   	 if (end <= this.pages && start >0){
   		 var pagerName = 'pager'; 
   		 var pagerHtml = '<span id="' + pagerName + 'pgPrev" onclick="' + pagerName + '.prevPage();" class="pg-normal">&#171</span>&nbsp;';    
   		 var element = document.getElementById('pagination'); 
   		 for (var page = start; page <= end; page++)
   			   pagerHtml += '<span id="pg' + page + '" class="pg-normal" onclick="' + pagerName + '.showPage(' + page + ');">' + page + '</span>&nbsp;';        
   			   pagerHtml += '<span id="' + pagerName + 'pgNext" onclick="'+ pagerName+'.nextPage();" class="pg-normal">&#187;</span>';               
   			   element.innerHTML = pagerHtml;
   	 }
    }
    
    this.pagerDisplay = function(pagerName, positionId) {
        if (! this.created) {
        return;
    	}
	    let element = document.getElementById(positionId);
	    var pagerHtml = ' <span id="' + this.pagerName + 'pgPrev" onclick="' + pagerName + '.prevPage();" class="pg-normal">&#171</span>&nbsp;';	    
	    
	    var start,
	    limit,
	    pagesCutOff = 5, 
	    ceiling = Math.ceil(pagesCutOff / 2),
	    floor = Math.floor(pagesCutOff / 2);
	    if(this.pages < pagesCutOff) {
	        start = 1;
	        limit = this.pages;
	    } else if(this.currentPage >= 1 && this.currentPage <= ceiling) {

	        start = 1;
	        limit = pagesCutOff;
	    } else if((this.currentPage + floor) >= this.pages) {
	        start = (this.pages - pagesCutOff);
	        limit = this.pages;
	    } else {
	        start = (this.currentPage - ceiling);
	        limit = (this.currentPage + floor);
	    }
	    
	    for (var page = start; page <=limit; page++)
	       pagerHtml += '<span id="pg' + page + '" class="pg-normal" onclick="' + pagerName + '.showPage(' + page + ');">' + page + '</span> ';
	       pagerHtml += ' <span id="   '   + pagerName + 'pgNext" onclick="'+ pagerName+'.nextPage();" class="pg-normal"> &#187;</span> '; 	        
	       element.innerHTML = pagerHtml; 
	     /*   pagerHtml += '<span id="pg' + page + '" class="pg-normal" onclick="' + pagerName + '.showPage(' + page + ');">' + page + '</span> ';
	       pagerHtml += '<span onclick="'+ pagerName+'.nextPage();" class="pg-normal"> Next »</span>'; */
	       //element.innerHTML = pagerHtml;
	}
}
</script>


<!-- gets location data -->
<script>

  


if(localStorage.getItem("loggedIn") == null || localStorage.getItem("loggedIn") == "false"){
	window.location.replace("https://localhost:8443");
}


</script>

<body onload = "javascript:sortTable(0)">


<div id=footer>
	<div id=navbar>
		<ul>
			<li><a href="Home.jsp" id=floatleft>Homepage</a></li>
			<li><a href="VacationPlanning.jsp" id=floatleft>Vacation Planning</a></li>
			<li><a class="active" href="ActivitiesPlanning.jsp" id=floatleft>Activities Planning</a></li>
			<li><a href="AnalyticalInformation.jsp" id=floatleft>Analytical Information</a></li>
			<li style="float:right"><a href="index.jsp" id=floatright>Log In</a></li>
		
		</ul>
	</div>
</div>

<div id=content>
	<div id=contentinner>
	<div id=contentleft>
		<form id="activityform" action="ActivityPlanningController" method="post">
			<div id=row>
				<span id=bodytext>Activity</span>
				<input class="inputbox" type=text required id=activity name=activity>
			</div>
			<div id=row>
				<span id=bodytext>Radius (mi)</span>
				<input class=inputbox type=text required id=radius name=radius>
			</div>
			<input type="hidden" id="custId" name="custId" value="3487">
			<input type="hidden" id="lat" name="lat">
			<input type="hidden" id="long" name="long">
			<input type="hidden" id="username" name= "username">
			<div id=row style="vertical-align:middle;display:inline-block; width:100%">
			<div style="float: left; vertical-align:middle; height:100%; display:inline-block;">
				<input id="searchaction" type="submit" value="Find my Activity Spot">
				<br> <br>
			</div>
			<div style="float:right; display:inline-block;">
			<span id=temp>&deg;C</span>
			<label class="switch">
  				<input id=units name=units type="checkbox" onclick="toggle()" checked>
  				<span class="slider"></span>
				</label>
				<span id=temp>&deg;F</span>
			</div>
			</div>
		</form>
	</div>
	
	<div id=insert></div>
 <%
 
 
 	CityList list = (CityList) request.getAttribute("results");
 	String units = (String) request.getAttribute("units");
 	
 	if (list != null && list.getCities().size() > 0)
 	{
 		out.println("<div id=contentright>");
 		out.println("<table id=\"myTable\">");
 		out.println("<tr>");
 		out.println("<th id=\"likeSort\" onclick=\"sortTable(0)\">Likes</th>");
 		out.println("<th onclick=\"sortTable(1)\">City</th>");
 		out.println("<th onclick=\"sortTable(2)\">Country</th>");
 		out.println("<th onclick=\"sortTable(3)\">Current temp</th>");
 		out.println("<th onclick=\"sortTable(4)\">Distance</th>");
 		out.println("</tr>");
 		
 		for (int i = 0; i < list.getCities().size(); i++)
 		{
 			CityResult city = list.getCities().get(i);
 			out.println("<tr>");
 			out.println("<td><p class=\"likes\"> <label id=\"upButton" + i + "\" class=\"arrowup\" onclick=\"likeCity(" + i + ")\">⇧</label> </p>");
 			
 			out.println("<p class=\"likes\" id=\"likeCount" + i + "\">" + city.getLikes() + "</p>");
 			
 			out.println("<p class=\"likes\"> <label id=\"downButton" + i + "\" class=\"arrowdown\" onclick=\"unlikeCity(" + i + ")\">⇩</label></td> </p>");
 			
 			out.println("<td>" + city.getName() + "</td>");
 			out.println("<td>" + city.getCountry() + "</td>");
 			if (units.equals("f"))
 			{
 				System.out.println("f");
 				out.println("<td>" + city.getCurrentTempF() + "</td>");
 			}
 			else
 			{
 				out.println("<td>" + city.getCurrentTempC() + "</td>");
 			}
 			out.println("<td name=dist>" + city.getDistance() + "</td>");
 			//out.println("<td id=\"likeCount" + i + "\">" + city.getLikes() + "</td>");
 			
 			
 			if (!FavoritesServlet.checkFavorite(city.getName()))
 			{
 				out.println("<td><button id=\"but" + i + "\" type=\"button\" onclick=\"addFav(\'" + city.getName() + "\', " + i + ")\">Add to Favorites</button></td>");
 			}
 			else
 			{
 				out.println("<td><button id=\"but" + i + "\" type=\"button\" onclick=\"removeFav(\'" + city.getName() + "\', " + i + ")\">Remove from Favorites</button></td>");
 			}
 			
 			
 			//out.println("<td><button id=\"likeButton" + i + "\" type=\"button\" onclick=\"likeCity(" + i + ")\">Like</button></td>");
 			out.println("</tr>");
 			
 		}
 		
 		out.println("</table>");
 		out.println("<br>");
 		out.println("<div id=pagination class=\"page\">");
 		out.println("</div>");
 		
 		out.println("</div>");
 	}
 	else if (list == null)
 	{
 		System.out.println("LIST IS NULL");
 	}
 
 
 
 
 
 %>
 
	</div>	 
</div>
<script type="text/javascript">
        var pager = new Pagination('myTable',5);
        pager.created();
        pager.pagerDisplay('pager', 'pagination');
        pager.showPage(1);
</script>

<script>
function setupPosition(position) {
	console.log("getting position");
	document.getElementById("lat").value = position.coords.latitude;
	document.getElementById("long").value = position.coords.longitude;
}

function errorCallback() {
	console.log("errrrr");
}

console.log(localStorage.getItem("loggedUser"));
document.getElementById("username").value = localStorage.getItem("loggedUser");

	if (navigator.geolocation) {
		console.log("getting here1");
    	navigator.geolocation.getCurrentPosition(setupPosition,errorCallback,{timeout:10000});

    	
  	} else { 
    	alert("Geolocation is not supported by this browser.");
  	}
	
if(<%= request.getAttribute("noresults") %> == true){
	document.getElementById("insert").innerHTML = "<div id=contentright></div>";
	document.getElementById("contentright").innerHTML = "No Cities Found";
}
if(<%= request.getAttribute("numErr") %> == true){
	document.getElementById('numresults').style.borderColor = "#ff6347";
}
if(<%= request.getAttribute("radErr") %> == true){
	document.getElementById('radius').style.borderColor = "#ff6347";
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
function addFav(name, id){
	var xhttp = new XMLHttpRequest();
	xhttp.open("POST", "ActivityPlanningController?cname=" + name, false);
	xhttp.send();
	
	document.getElementById("but" + id).childNodes[0].nodeValue = "Remove from Favorites";
	document.getElementById("but" + id).onclick = function() { removeFav(name, id); }
	
	//window.location.replace("VacationPlanning.jsp?clicked=" + name);
}
function removeFav(name, id){
	var xhttp = new XMLHttpRequest();
	xhttp.open("POST", "ActivityPlanningController?rname=" + name, false);
	xhttp.send();
	
	document.getElementById("but" + id).childNodes[0].nodeValue = "Add to Favorites";
	document.getElementById("but" + id).onclick = function() { addFav(name, id); }
	
}
function likeCity(id){
	var xhttp = new XMLHttpRequest();
	xhttp.open("POST", "ActivityPlanningController?like=" + id, false);
	xhttp.send();
	
	var count = parseInt(document.getElementById("likeCount" + id).innerText, 10);
	//document.getElementById("likeButton" + id).childNodes[0].nodeValue = "Unlike";
	document.getElementById("likeCount" + id).innerText = count+1;
	//document.getElementById("likeButton" + id).onclick = function() { unlikeCity(id); }
}
function unlikeCity(id){
	
	var count = parseInt(document.getElementById("likeCount" + id).innerText, 10);
	
	if (count > 0)
		{
	var xhttp = new XMLHttpRequest();
	xhttp.open("POST", "ActivityPlanningController?unlike=" + id, false);
	xhttp.send();
	
	
	//document.getElementById("likeButton" + id).childNodes[0].nodeValue = "Like";
	document.getElementById("likeCount" + id).innerText = count-1;
	//document.getElementById("likeButton" + id).onclick = function() { likeCity(id); }
		}
}
function submit()
{
	console.log("hello");
}
function sortTable(n) {
  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
  table = document.getElementById("myTable");
  switching = true;
  //Set the sorting direction to ascending:
  dir = "asc"; 
  /*Make a loop that will continue until
  no switching has been done:*/
  
  while (switching) {
    //start by saying: no switching is done:
    switching = false;
    
  
    rows = table.rows;
    /*Loop through all table rows (except the
    first, which contains table headers):*/
    for (i = 1; i < (rows.length - 1); i++) {
      //start by saying there should be no switching:
      shouldSwitch = false;
      /*Get the two elements you want to compare,
      one from current row and one from the next:*/
      x = rows[i].getElementsByTagName("TD")[n];
      y = rows[i + 1].getElementsByTagName("TD")[n];
      /*check if the two rows should switch place,
      based on the direction, asc or desc:*/
      
      numX = x.getElementsByTagName("P")[1];
      numY = y.getElementsByTagName("P")[1];
      
      if (dir == "asc") {
    	  if(n == 0){
		      var num_1 = Number(numX.innerHTML);
		      var num_2 = Number(numY.innerHTML);
		      
		      if(num_1 > num_2){
		    	  shouldSwitch = true;
		    	  break;
		      }
	      }
    	  else if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
          //if so, mark as a switch and break the loop:
          shouldSwitch= true;
          break;
        }
      } else if (dir == "desc") {
    	  if(n == 0){
    		  var num_1 = Number(numX.innerHTML);
		      var num_2 = Number(numY.innerHTML);
		      if(num_1 < num_2){
		    	  shouldSwitch = true;
		    	  break;
		      }
	      }
    	  else if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
          //if so, mark as a switch and break the loop:
          shouldSwitch = true;
          break;
        }
      }
    }
    if (shouldSwitch) {
      /*If a switch has been marked, make the switch
      and mark that a switch has been done:*/
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
      //Each time a switch is done, increase this count by 1:
      switchcount ++;      
    } else {
      /*If no switching has been done AND the direction is "asc",
      set the direction to "desc" and run the while loop again.*/
      if (switchcount == 0 && dir == "asc") {
        dir = "desc";
        switching = true;
      }
    }
  }
  colorTable();
}
function colorTable(){
	  var table, rows = 0;
	  table = document.getElementById("myTable");
	  rows = table.rows;
	  for (i = 1; i <  rows.length ; i++){
		  if(i % 2 == 0){
			  rows[i].getElementsByTagName("TD")[0].style.background = "lightgray"; 
			  rows[i].getElementsByTagName("TD")[1].style.background = "lightgray"; 
			  rows[i].getElementsByTagName("TD")[2].style.background = "lightgray"; 
			  rows[i].getElementsByTagName("TD")[3].style.background = "lightgray"; 
			  rows[i].getElementsByTagName("TD")[4].style.background = "lightgray";
			  rows[i].getElementsByTagName("TD")[5].style.background = "lightgray"; 
		  }
		  else{
			  rows[i].getElementsByTagName("TD")[0].style.background = "snow";
			  rows[i].getElementsByTagName("TD")[1].style.background = "snow";
			  rows[i].getElementsByTagName("TD")[2].style.background = "snow";
			  rows[i].getElementsByTagName("TD")[3].style.background = "snow";
			  rows[i].getElementsByTagName("TD")[4].style.background = "snow";
			  rows[i].getElementsByTagName("TD")[5].style.background = "snow"; 
		  
		  }
	  }
}
</script>
<script>

function prevSearch(){
	var myList = document.getElementById("Searches");
 	var str = myList.options[myList.selectedIndex].text;
	var indexOfComma = [];
 	for(var i = 0; i < str.length; i++){
		if(str[i] == ','){
			indexOfComma.push(i);
			if(indexOfComma.length == 3){
				break;
			}
		}
	}  
	document.getElementById("activity").value = str.substring(0,indexOfComma[0]); 
	document.getElementById("numresults").value = str.substring(indexOfComma[0] + 1, indexOfComma[1]);
	document.getElementById("location").value = str.substring(indexOfComma[1] + 1, indexOfComma[2]);
	document.getElementById("radius").value = str.substring(indexOfComma[2] + 1, str.length);
 	document.getElementById("activityform").submit();  
}
</script>

<script>
	if(localStorage.getItem("loggedIn") == "true"){
		document.getElementById("floatright").innerHTML = "Log Out";
	}
</script>
</body>
</html>