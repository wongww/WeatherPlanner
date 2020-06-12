<!DOCTYPE html>
<%@page import="csci310.FavoriteCity"%>
<%@page import="csci310.AnalyticsInfoPageController"%>
<%@page import="java.util.*"%>
<%-- <%@page import="java.util.List"%>
<%@page import= "java.util." %> --%>
<%@page import="csci310.FavoritesServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>Analytical Information</title>
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
th.faves{
	font-weight: normal;
}
td{
	border-radius: 25px;
}
button{
	margin-left:2.5%;
	display: block;
	background-color: #e7e7e7; 
	color: black;
	padding: 3px 58px;
	align: center;
	font-size: 40%;
}
  #cm-popup{
   display:none;
   box-shadow: 0 0 10px #888888;
   position:fixed;
   left:50%;
   transform:translateX(-50%);
    width:50%;
   	height:200px;
   z-index: 999;
   background-color: #FFFAFA;
   
   }
#contentright{
	width:28.33%;
	height:100%;
	float:left;
	margin-left: 2.5%;
	margin-right: 2.5%;
}
#contentleft{
	width:28.33%;
	height:100%;
	float:left;
	margin-left:2.5%;
	margin-right:2.5%;
	text-align:left;
	align: center;
}
#contentmiddle{
	width:28.33%;
	height:100%;
	float:left;
	margin-left: 2.5%;
	margin-right: 2.5%;
}
* {box-sizing: border-box;}
.mySlides {display: none;}
img.slideshow {
	margin-left: 30%;
	height: auto;
	width: 80%;
	margin-left:10%;
	margin-right:10%;
	border-radius: 25px;
}
/* Number text (1/3 etc) */
.numbertext {
  color:#EDF5E1;
  margin-left: 0%;
  font-size: medium;
}
/* Fading animation */
.fade {
  -webkit-animation-name: fade;
  -webkit-animation-duration: 1.5s;
  animation-name: fade;
  animation-duration: 1.5s;
}
@-webkit-keyframes fade {
  from {opacity: .4} 
  to {opacity: 1}
}
@keyframes fade {
  from {opacity: .4} 
  to {opacity: 1}
}
.footerDisable{
	width:100%;
	height:12%;
    position: fixed; 
    background: #000;
    opacity: 0.8;
}
.parentDisable{
    position: fixed;
    top: 0;
    left: 0;
    background: #000;
    opacity: 0.8;
    z-index: 998;
    height: 100%;
    width: 100%;
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
#city{
	width:100%;
	height:10%;
}
#graphicandtemp{
	height:25%;
	width:100%;
}
#grahpic{
	width:50%;
	height:100%;
	float:left;
}
#icon{
	float:left;
	width:50%;
	height:100%;
}
#temp{
	width:50%;
	padding-top:10%;
	float:left;
}
#precipitation{
	width:100%;
	height:10%;
	float:left;
}
#forecast{
	width:100%;
	float:left;
	height: 40%;
	padding-top: 5%;

}
#day1, #day2, #day3, #day4, #day5{
	width:20%;
	height:100%;
	float:left;
}
#day1text, #day2text, #day3text, #day4text, #day5text{
	font-size: 70%;
}
#day1graphic, #day2graphic, #day3graphic, #day4graphic, #day5graphic{
	position: relative;
	left: 25%;
	width: 100%;
}

#remove{
	float:left;
	width:45%;
	background-color: #05386B;
	color:#EDF5E1;
	border:none;
	height:60%;
	border-radius:25px;
}
#table_info{
	float:left;
	width:100%;
	background-color:#8EE4AF;
	color:#EDF5E1;
	border:none;
	height:100%;
	border-radius:25px;
	font-size: 16px;
	
}
pagination{
	margin: 10px;
	padding-left: 20px;
}
.pg-normal, .pg-selected{
	border: 2px solid #8EE4AF;
	background: #8EE4AF;  
	text-align: center; 
	border-radius: 50%;
	height: 5px;
	width: 5px; 
	padding: 10px; 
}
.pg-selected{
 background-color: #EDF5E1;
  border-radius: 50%;
  height: 5px;
  width: 5px;
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
  height: 5px;
  width: 5px;
  background: #8EE4AF;
  border: 2px solid #8EE4AF; 
  text-align: center;
  padding: 10px;
  
} 
#contentinner{
	margin-left:auto;
	margin-right:auto;
	height:100%;
	width:90%
}
td.likes{
	font-size: 70%;
	text-align:center;
}
</style>	
</head>
<script>
<% 
List<FavoriteCity> list = FavoritesServlet.getFavorites();
%>
if(localStorage.getItem("loggedIn") == null || localStorage.getItem("loggedIn") == "false"){
	window.location.replace("https://localhost:8443");
}
</script>

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
/*    	    alert("inside next funtion"); 
 */    	var start,
  	    limit,
  	    pagesCutOff = 5, 
  	    ceiling = Math.ceil(pagesCutOff / 2),
  	    floor = Math.floor(pagesCutOff / 2);
    	
    	if (this.currentPage < this.pages) {
/*     		alert("0");
 */         	this.createPager(this.currentPage, this.currentPage+4);
			this.showPage(this.currentPage + 1);
         }
    	
    	else if(this.pages < pagesCutOff) {
/*   	    	alert("1"); 
 */  	        start = 1;
  	        limit = this.pages;
         	this.createPager(start, limit);
			this.showPage(this.currentPage + 1);
  	        
  	    } else if(this.currentPage >= 1 && this.currentPage <= ceiling) {
/*   	    	 alert("2"); 
 */  	        start = 1;
  	        limit = pagesCutOff; 	
  	        this.createPager(start, limit);
			this.showPage(this.currentPage + 1);
			
  	    } else if((this.currentPage + floor) >= this.pages) {
/*   	    	 alert("3"); 
 */  	        start = (this.pages - pagesCutOff+1);
  	        limit = this.pages;
	  	   	this.createPager(start, limit);
			this.showPage(this.currentPage + 1);
  	    } else {
/*   	    	 alert("4"); 
 */  	        start = (this.currentPage - ceiling+1);
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
    	
        if (this.currentPage > 1){
        	this.createPager(this.currentPage-4, this.currentPage);
        	this.showPage(this.currentPage - 1);
        }
    	else if(this.pages < pagesCutOff) {

  	        start = 1;
  	        limit = this.pages;
         	this.createPager(start, limit);
			this.showPage(this.currentPage - 1);
  	        
  	    } else if(this.currentPage >= 1 && this.currentPage <= ceiling) {
/*   	    	 alert("2"); 
 */  	        start = 1;
  	        limit = pagesCutOff; 	
  	        this.createPager(start, limit);
			this.showPage(this.currentPage - 1);
			
  	    } else if((this.currentPage + floor) >= this.pages) {
/*   	    	 alert("3"); 
 */  	        start = (this.pages - pagesCutOff+1);
  	        limit = this.pages;
	  	   	this.createPager(start, limit);
			this.showPage(this.currentPage - 1);
  	    } else {
/*   	    	alert("4"); 
 */  	        start = (this.currentPage - ceiling+1);
  	        limit = (this.currentPage + floor);
	  	   	this.createPager(start, limit);
			this.showPage(this.currentPage - 1);
  	    }
    }
    
    this.createPager = function(start, end){
   	 if (end <= this.pages && start >0){
   		 var pagerName = 'pager'; 
   		 var pagerHtml = ' <span id="' + pagerName + 'pgPrev" onclick="' + pagerName + '.prevPage();" class="pg-normal">&#171</span>&nbsp;';    
   		 var element = document.getElementById('pagination'); 
   		 for (var page = start; page <= end; page++)
   			   pagerHtml += ' <span id="pg' + page + '" class="pg-normal" onclick="' + pagerName + '.showPage(' + page + ');">' + page + ' </span>&nbsp;';        
   			   pagerHtml += ' <span id="' + pagerName + 'pgNext" onclick="'+ pagerName+'.nextPage();" class="pg-normal">&#187;</span>';               
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
/* 	    alert("this pages" + this.pages + this.currentPage); 
 */	    if(this.pages < pagesCutOff) {
/* 	    	alert("1"); 
 */	        start = 1;
	        limit = this.pages;
	    } else if(this.currentPage >= 1 && this.currentPage <= ceiling) {
/* 	    	 alert("2"); 
 */	        start = 1;
	        limit = pagesCutOff;
	    } else if((this.currentPage + floor) >= this.pages) {
/* 	    	 alert("3"); 
 */	        start = (this.pages - pagesCutOff);
	        limit = this.pages;
	    } else {
/* 	    	 alert("4"); 
 */	        start = (this.currentPage - ceiling);
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
<body onload = "javascript:sortTable(0)">

<div id=footer>
	<div id=navbar>
		<ul>
			<li><a href="Home.jsp" id=floatleft>Homepage</a></li>
			<li><a href="VacationPlanning.jsp" id=floatleft>Vacation Planning</a></li>
			<li><a href="ActivitiesPlanning.jsp" id=floatleft>Activities Planning</a></li>
			<li><a class="active" href="AnalyticalInformation.jsp" id=floatleft>Analytical Information</a></li>
			<li style="float:right"><a href="index.jsp" id=floatright>Log In</a></li>
	
		</ul>
	</div>
</div>

<div id=content>
	<div id = contentinner>
	<div id=contentleft>
	<table id = myTable>
  		<tr>
    		<th class = "faves" onclick = "sortTable(0)">Favorite Cities</th>
    		<th class = "faves" onclick = "sortTable(1)">Likes</th>
		</tr>	
 		<%
  			for (int i = 0; i < list.size(); i++)
  			{
	  				out.println("<tr>");
		  			out.print("<td> <form method=\"POST\" action=\"AnalyticsInfoPageController\"> <input type=\"submit\" id = \"table_info\" name=\"someName\" value=\"");
		  			out.print(list.get(i).getName() + "\"> </form> </td>");
		  			out.println("<td class = \"likes\">" + Integer.toString(list.get(i).getLikes()) + "</td>");
	 				out.println("</tr>");
	  
  			}
  		%>	
	</table>
 		<br> 
	<div id=pagination class="page"></div>
    <br> 
 	<form method="post" action="AnalyticsInfoPageController">
		<button type = submit id = "remove" name = "rcity"> Remove </button>
 	</form> 
 	
</div>

<script type="text/javascript">
        var pager = new Pagination('myTable',10);
        pager.created();
        pager.pagerDisplay('pager', 'pagination');
        pager.showPage(1);
</script>

  <div id = contentmiddle>
  	<div id=cityname></div>
  	<div id=graphicandtemp>
  		<div id=graphic></div>
  		<div id=temp></div>
  	</div>
  	<div id=precipitation></div>
  	<div id=forecast>
  		<div id=day1>
  			<div id=day1text></div>
  			<div id=day1graphic></div>
  			<br>
  			<div id=day1high></div>
  			<div id=day1low></div>
  		</div>
  		<div id=day2>
  			<div id=day2text></div>
  			<div id=day2graphic></div>
  			<br>
  			<div id=day2high></div>
  			<div id=day2low></div>
  		</div>
  		<div id=day3>
  			<div id=day3text></div>
  			<div id=day3graphic></div>
  			<br>
  			<div id=day3high></div>
  			<div id=day3low></div>
  		</div>
  		<div id=day4>
  			<div id=day4text></div>
  			<div id=day4graphic></div>
  			<br>
  			<div id=day4high></div>
  			<div id=day4low></div>
  		</div>
  		<div id=day5>
  			<div id=day5text></div>
  			<div id=day5graphic></div>
  			<br>
  			<div id=day5high></div>
  			<div id=day5low></div>
  		</div>
  	</div>
  </div>
  <div id = contentright>
   			<div class="mySlides fade">
  				<a href="<%= request.getAttribute("myLink1") %>">
  					<img class = "slideshow" src="<%= request.getAttribute("myImages1") %>">
  				</a>
  			<div class="numbertext">1 / 5</div>	
 		</div> 

		<div class="mySlides fade">
  				<a href="<%= request.getAttribute("myLink2") %>">
  					<img class = "slideshow" src="<%= request.getAttribute("myImages2") %>">
  				</a>
  				<div class="numbertext">2 / 5</div>
		</div>

		<div class="mySlides fade">
  				<a href="<%= request.getAttribute("myLink3") %>">
  					<img class = "slideshow" src="<%= request.getAttribute("myImages3") %>">
  				</a>
  				<div class="numbertext">3 / 5</div>
		</div>

		<div class="mySlides fade">
  				<a href="<%= request.getAttribute("myLink4") %>">
  					<img class = "slideshow" src="<%= request.getAttribute("myImages4") %>">
  				</a>
  				<div class="numbertext">4 / 5</div>
		</div> 
		
		<div class="mySlides fade">
  				<a href="<%= request.getAttribute("myLink5") %>">
  					<img class = "slideshow" src="<%= request.getAttribute("myImages5") %>">
  				</a>
  				<div class="numbertext">5 / 5</div>
		</div> 
  </div>
  </div>
</div>
<script>
function sortTable(n) {
	  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
	  var counter = 0;
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
	      if (dir == "asc") {
		      if(n == 1){
			      var num_1 = Number(x.innerHTML);
			      var num_2 = Number(y.innerHTML);
			      if(num_1 > num_2){
			    	  shouldSwitch = true;
			    	  break;
			      }
		      }
		      else if (x.innerHTML > y.innerHTML) {
	          //if so, mark as a switch and break the loop:
	          shouldSwitch= true;
	          break;
	        }
	      } else if (dir == "desc") {
		      if(n == 1){
			      var num_1 = Number(x.innerHTML);
			      var num_2 = Number(y.innerHTML);
			      if(num_1 < num_2){
			    	  shouldSwitch = true;
			    	  break;
			      }
		      }
		      else if (x.innerHTML < y.innerHTML) {
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
	}
//contentmiddle related stuff - tao
var city = "<%= request.getAttribute("someName") %>";
var f = "<%= request.getAttribute("f") %>";
var c = "<%= request.getAttribute("c") %>";
var precipitation = "<%= request.getAttribute("precipitation") %>";
var graphic = "<%= request.getAttribute("graphic") %>";
var forecast = "<%= request.getAttribute("forecast") %>";

if(city == "null"){
	document.getElementById("cityname").innerHTML = "No City Selected";
	document.getElementById("remove").value = null;
 	document.getElementById("forecast").style.border -= "5px solid #EDF5E1";
 	document.getElementById("forecast").style.borderRadius -= "25px";
}
else {
	document.getElementById("remove").value = city;
	document.getElementById("cityname").innerHTML += city;
	document.getElementById("cityname").innerHTML += ", "; 
	var dt = new Date();
	document.getElementById("cityname").innerHTML += dt.toLocaleDateString();
	if(localStorage.getItem('tempscale') == "f"){
		document.getElementById("temp").innerHTML += f;
		document.getElementById("temp").innerHTML += "&deg;F";
	}
	else if(localStorage.getItem('tempscale') == "c"){
		document.getElementById("temp").innerHTML += c;
		document.getElementById("temp").innerHTML += "&deg;C";
	}
	document.getElementById("precipitation").innerHTML += precipitation;
	var iconstring = "http://openweathermap.org/img/wn/" + graphic + "@2x.png";
	document.getElementById("graphic").innerHTML += "<img src=\"" + iconstring + "\" id=icon alt=\"icon\">";
	
	//forecast stuff
 	document.getElementById("forecast").style.border += "5px solid #EDF5E1";
 	document.getElementById("forecast").style.borderRadius += "25px";
	
	document.getElementById("day1text").innerHTML += "day 1";
	document.getElementById("day2text").innerHTML += "day 2";
	document.getElementById("day3text").innerHTML += "day 3";
	document.getElementById("day4text").innerHTML += "day 4";
	document.getElementById("day5text").innerHTML += "day 5";
	
	var iconid1 = "<%= request.getAttribute("day1graphic") %>";
	var iconstring1 = "http://openweathermap.org/img/wn/" + iconid1 + "@2x.png";
	document.getElementById("day1graphic").innerHTML += "<img src=\"" + iconstring1 + "\" id=icon alt=\"icon\">";
	
	var iconid2 = "<%= request.getAttribute("day2graphic") %>";
	var iconstring2 = "http://openweathermap.org/img/wn/" + iconid2 + "@2x.png";
	document.getElementById("day2graphic").innerHTML += "<img src=\"" + iconstring2 + "\" id=icon alt=\"icon\">";
	
	var iconid3 = "<%= request.getAttribute("day3graphic") %>";
	var iconstring3 = "http://openweathermap.org/img/wn/" + iconid3 + "@2x.png";
	document.getElementById("day3graphic").innerHTML += "<img src=\"" + iconstring3 + "\" id=icon alt=\"icon\">";
	
	var iconid4 = "<%= request.getAttribute("day4graphic") %>";
	var iconstring4 = "http://openweathermap.org/img/wn/" + iconid4 + "@2x.png";
	document.getElementById("day4graphic").innerHTML += "<img src=\"" + iconstring4 + "\" id=icon alt=\"icon\">";
	
	var iconid5 = "<%= request.getAttribute("day5graphic") %>";
	var iconstring5 = "http://openweathermap.org/img/wn/" + iconid5 + "@2x.png";
	document.getElementById("day5graphic").innerHTML += "<img src=\"" + iconstring5 + "\" id=icon alt=\"icon\">";
	
	if(localStorage.getItem('tempscale') == "f"){
		document.getElementById("day1high").innerHTML += "<%= request.getAttribute("day1highF") %>";
		document.getElementById("day1low").innerHTML += "<%= request.getAttribute("day1lowF") %>";
		document.getElementById("day2high").innerHTML += "<%= request.getAttribute("day1highF") %>";
		document.getElementById("day2low").innerHTML += "<%= request.getAttribute("day1lowF") %>";
		document.getElementById("day3high").innerHTML += "<%= request.getAttribute("day1highF") %>";
		document.getElementById("day3low").innerHTML += "<%= request.getAttribute("day1lowF") %>";
		document.getElementById("day4high").innerHTML += "<%= request.getAttribute("day1highF") %>";
		document.getElementById("day4low").innerHTML += "<%= request.getAttribute("day1lowF") %>";
		document.getElementById("day5high").innerHTML += "<%= request.getAttribute("day1highF") %>";
		document.getElementById("day5low").innerHTML += "<%= request.getAttribute("day1lowF") %>";
	}
	else if(localStorage.getItem('tempscale') == "c"){
		document.getElementById("day1high").innerHTML += "<%= request.getAttribute("day1highC") %>";
		document.getElementById("day1low").innerHTML += "<%= request.getAttribute("day1lowC") %>";
		document.getElementById("day2high").innerHTML += "<%= request.getAttribute("day1highC") %>";
		document.getElementById("day2low").innerHTML += "<%= request.getAttribute("day1lowC") %>";
		document.getElementById("day3high").innerHTML += "<%= request.getAttribute("day1highC") %>";
		document.getElementById("day3low").innerHTML += "<%= request.getAttribute("day1lowC") %>";
		document.getElementById("day4high").innerHTML += "<%= request.getAttribute("day1highC") %>";
		document.getElementById("day4low").innerHTML += "<%= request.getAttribute("day1lowC") %>";
		document.getElementById("day5high").innerHTML += "<%= request.getAttribute("day1highC") %>";
		document.getElementById("day5low").innerHTML += "<%= request.getAttribute("day1lowC") %>";
	}
}
//contentright related stuff - bernard
if(city == "null"){

}
else{
	var slideIndex = 0;
	showSlides();
	function showSlides() { 
	  var i;
	  var slides = document.getElementsByClassName("mySlides");
	  for (i = 0; i < slides.length; i++) {
	    slides[i].style.display = "none";  
	    //slides[i].src = //Whatever we get from the API
	  }
	  slideIndex++;
	  if (slideIndex > slides.length) {slideIndex = 1}    
	  slides[slideIndex-1].style.display = "block";  
	  setTimeout(showSlides, 3000); // Change image every 3 seconds
	 } 
}
</script>

<script>
	if(localStorage.getItem("loggedIn") == "true"){
		document.getElementById("floatright").innerHTML = "Log Out";
	}
</script>
</body>
</html>

