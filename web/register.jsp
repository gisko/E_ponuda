<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="navigation.jsp"/>

<c:if test="${'Seller' eq user.DTYPE or 'Buyer' eq user.DTYPE}">
      	<%response.sendRedirect("./Home"); %>         		
	</c:if>
</br>
</br></br></br>
<div class="row">
		 <c:if test="${user==null}">
		<div class="col-md-4 col-md-offset-4 bg-success">
	      <form class="form-signin" id="form1" method="POST" action="./RegisterServlet?control=BUYER" >
	        <h2 class="form-signin-heading alert alert-info">Create new buyer account</h2>
	       <input id="username" type="text" class="form-control" placeholder="Username" name="korisnickoIme" required autofocus>
	          <input id="pass1" type="password" class="form-control" placeholder="Password" name="lozinka" required>
	            <input id="pass2" type="password" class="form-control" placeholder="Confirm password" name="ponoviLozinku" required>
	          <input type="text" class="form-control" placeholder="First Name" name="ime" required> 
	         <input  type="text" class="form-control" placeholder="Last Name" name="prezime" required>	        
	           <input  type="text" class="form-control" placeholder="Telephone" name="telefon" required>
	           <input  type="text" class="form-control" placeholder="Address" name="adresa" required>	           
	         <div class="input-group">     	       
	       <input type="email" class="form-control" placeholder="Email" name="email" aria-describedby="basic-addon2" required>
						<span class="input-group-addon" id="basic-addon2">@example.com</span>	       
	       </div>
	       </br>
	        <input  class="btn btn-lg btn-success btn-block" type="submit" value="Register"> </input>
	        </br></br>
	        <c:if test="${messageREG != null}">
	        <div class="alert alert-danger message">
			  			<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			  			${messageREG}
			 </br></div>
			</c:if> 
			<div id="div1" class="alert alert-danger message">
			  			<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			  			<span id="mes"></span>
			 </br></div>
	      </form>	    
     	 </div>
     	 </c:if>
     	  <c:if test="${user!=null}">
     	  	 <c:if test="${'Admin' eq user.DTYPE }">
     	  	 <div class="col-md-4 col-md-offset-4 bg-success">
	      <form class="form-signin" id="form1" method="POST" action="./RegisterServlet?control=SELLER" >
	        <h2 class="form-signin-heading alert alert-info">Create new seller account</h2>
	       <input id="username" type="text" class="form-control" placeholder="Username" name="korisnickoIme" required autofocus>
	          <input id="pass1" type="password" class="form-control" placeholder="Password" name="lozinka" required>
	            <input id="pass2" type="password" class="form-control" placeholder="Confirm password" name="ponoviLozinku" required>
	          <input type="text" class="form-control" placeholder="First Name" name="ime" required> 
	         <input  type="text" class="form-control" placeholder="Last Name" name="prezime" required>	        
	           <input  type="text" class="form-control" placeholder="Telephone" name="telefon" required>         
	         <div class="input-group">     	       
	       <input type="email" class="form-control" placeholder="Email" name="email" aria-describedby="basic-addon2" required>
						<span class="input-group-addon" id="basic-addon2">@example.com</span>	       
	       </div>
	       </br>
	        <input  class="btn btn-lg btn-success btn-block" type="submit" value="Create"> </input>
	        </br></br>
			<c:if test="${messageREG != null}">
	        <div class="alert alert-danger message">
			  			<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			  			${messageREG}
			 </br></div>
			</c:if> 
			<div id="div1" class="alert alert-danger message">
			  			<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			  			<span id="mes"></span>
			 </br></div>
	      </form>	    
     	 </div>
     	  	 </c:if>
     	  </c:if>
    </div>
<script>
$(document).ready(function(){
	$("#div1").hide();
	$("#mes").hide();
});
$("#form1").submit(function()
		{
	if($.trim($("#username").val())=='') {
		$("#div1").show();
		 $( "#mes" ).text( "Username can't be empty string!" ).show();
		     
		 return false;
	}
	
	if($("#pass1").val() != $("#pass2").val())
		    {
		$("#div1").show();
		 $( "#mes" ).text( "You must enter SAME password in both field's!" ).show();
		     
		 return false;
		    }
	
});

</script>
<jsp:include page="end.jsp"/>