<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>	
		<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<meta HTTP-EQUIV="Expires" CONTENT="-1">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">		
		<title>E_ponuda</title>
		<link href="LookAndFeel/css/bootstrap.min.css" rel="stylesheet"/>
		<link rel="icon" type="image/ico" href="LookAndFeel/css/favicon.ico"/>
		<link href="LookAndFeel/css/style.css" rel="stylesheet" type="text/css"/>
		<link rel="stylesheet" href="LookAndFeel/js/jquery-ui.css">
		
		<script src="LookAndFeel/js/jquery-1.11.0.js"></script>	
		<script type="text/javascript" src="LookAndFeel/js/jquery.tablesorter.js"></script> 		
	
		<link rel="alternate" type="application/rss+xml" href="./RssLast5Servlet" title="E_ponuda">
<script>
$(document).ready(function() 
	    { 
	        $(".myTable").tablesorter(); 
	    } 
	); 
$(document).ready(function() 
	    { 
	        $(".myTable").tablesorter( {sortList: [[0,0], [1,0]]} ); 
	    }); 


</script>
<title>E_ponuda</title>
</head>
<body>
	<nav id="nav_1" class="navbar navbar-inverse navbar-fixed-top sticky">
		<div class="container">
			<div class="navbar-header">
				<button id="home_button" type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
	  
				<a class="navbar-brand" href="./Home?next=OFFER"><img src="LookAndFeel/css/brandImg.png" width="70" height="19"></a>
			</div>

			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				 <c:if test="${user!=null}">
               		<c:if test="${'Admin' eq user.DTYPE }">
               		<ul class="nav navbar-nav">
						<li  id ="navigacija"><a href="./Home?next=CAT"> Category <span class="sr-only">(current)</span></a></li>						
						<li  id ="navigacija"><a href="./Home?next=SEL"> Sellers <span class="sr-only">(current)</span></a></li>
						<li  id ="navigacija"><a href="./Home?next=PAY"> Payments <span class="sr-only">(current)</span></a></li>
						<li  id ="navigacija"><a href="./Home?next=COUP"> Coupons <span class="sr-only">(current)</span></a></li>
					</ul>
               		</c:if>
               	</c:if>
               	<c:if test="${user!=null}">
               		<c:if test="${'Seller' eq user.DTYPE }">
               			<ul class="nav navbar-nav">
						<li  id ="navigacija"><a href="./Home?next=BR"> Branches <span class="sr-only">(current)</span></a></li>					
               			</ul>
               		</c:if>
               	</c:if>
               	<c:if test="${user!=null}">
               		<c:if test="${'Buyer' eq user.DTYPE }">
               			<ul class="nav navbar-nav navbar-left">
					<li class="dropdown" id="navigacija">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Category<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu" color="black">
							<li id="userHover"><a href="./Home?catID=ALL&next=OFFER" color="black" >All (${fn:length(offersCATEGORY)})</a></li>					
						<li class="divider"></li>
						<c:forEach items="${category}" var="category">
						<c:set var="catOFF" value="${category.id}"/>
						<c:set var="count" value="0"/>
							<c:forEach items="${offersCATEGORY}" var="offers">							
							<c:if test="${catOFF == offers.category.id}">
							<c:set var="count" value="${count+1 }"/>
							</c:if>
							</c:forEach>
							<li id="userHover"><a href="./Home?catID=${category.id}&next=OFFER" color="black" >${category.name} (${count})</a></li>					
						</c:forEach>
							</ul>
					</li>
					
					</ul> 
					
					    <ul class="nav navbar-nav">
               			<li  id ="navigacija"><a href="./Home?next=COUP"> Coupons <span class="sr-only">(current)</span></a></li>					
               			<li  id ="navigacija"><a href="./Home?next=PAY"> Payments <span class="sr-only">(current)</span></a></li>					             	
							</ul>
               			</ul>      			
               		</c:if>
               	</c:if>
               	
               	<c:if test="${user==null}">	
				
				 <ul class="nav navbar-nav navbar-left">
					<li class="dropdown" id="navigacija">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Category<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu" color="black">
							<li id="userHover"><a href="./Home?catID=ALL&next=OFFER" color="black" >All (${fn:length(offersCATEGORY)})</a></li>					
						<li class="divider"></li>
						<c:forEach items="${category}" var="category">
						<c:set var="catOFF" value="${category.id}"/>
						<c:set var="count" value="0"/>
							<c:forEach items="${offersCATEGORY}" var="offers">							
							<c:if test="${catOFF == offers.category.id}">
							<c:set var="count" value="${count+1 }"/>
							</c:if>
							</c:forEach>
							<li id="userHover"><a href="./Home?catID=${category.id}&next=OFFER" color="black" >${category.name} (${count})</a></li>					
						</c:forEach>
							</ul>
					</li>					
				</ul>			 
				 </c:if>
			 
				<ul class="nav navbar-nav navbar-right">
				<c:if test="${user==null}">
					<li id="gost"><a > Guest </a></li>
					<li class="dropdown" id="navigacija">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Login<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu" color="black">
							<li id="userHover"><a href="./loginBuyer.jsp" color="black" >Login as buyer</a></li>
							<li id="userHover"><a href="./loginSeller.jsp" color="black" >Login as seller</a></li>
							<li class="divider"></li>
							<li id ="adminHover"><a href="./loginAdmina.jsp" color="black">Login as administrator</a></li>
						</ul>
					</li>
					<li  id ="navigacija"><a href="./register.jsp">Sign up<span class="sr-only">(current)</span></a></li>
				</c:if>
				<c:if test="${user!=null}"> 
				      							
							<li class="dropdown" id="navigacija">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Hello, ${user.username} <span class="caret"></span></a>
								<ul class="dropdown-menu" role="menu" color="black">
									<c:if test="${'Buyer' eq user.DTYPE or 'Seller' eq user.DTYPE }">  
									<li  id ="userHover"><a  href="./RegisterServlet?type=CH&id=${user.id}"  color="black"> <span class="glyphicon glyphicon-user"></span> Edit Profile</a></li>         
									</c:if>
									<li  id ="userHover"><a href="./LogoutServlet"  color="black"> <span class="glyphicon glyphicon-log-out"></span> Logout</a></li>	
								</ul>
							</li>					
										
				</c:if>
		</ul>
			</div>
		</div>
	</nav>

	
	
	
	
	
	
	