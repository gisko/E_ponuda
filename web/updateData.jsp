<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="navigation.jsp"/>


<c:if test="${user==null }">
      	<%response.sendRedirect("./Home"); %>         		
	</c:if>
	
</br>
</br></br></br>
<div class="row">
	<c:if test="${user!=null}"> 
		<div class="col-md-4 col-md-offset-4 bg-success">
	      <form class="form-signin" id="form1" method="POST" action="./RegisterServlet?control=CHANGE" >
	       <c:if test="${'Admin' eq user.DTYPE }">
	        <h2 class="form-signin-heading alert alert-info">Edit ${zaIzmenuUser.username}'s account</h2>
	     	</c:if>
	     	<c:if test="${'Seller' eq user.DTYPE }">
	        <h2 class="form-signin-heading alert alert-info">Update your account</h2>
	     	</c:if> 
	     	<c:if test="${'Buyer' eq user.DTYPE }">
	        <h2 class="form-signin-heading alert alert-info">Update your account</h2>
	     	</c:if>
	       <div class="form-group">
			   <label>Username:</label>
	       <input id="username" type="text" class="form-control" placeholder="Username" name="korisnickoIme" value="${zaIzmenuUser.username}" required disabled >
	       <input id="username" type="hidden" class="form-control" placeholder="Username" name="username2" value="${zaIzmenuUser.username}">	       
	       </div>
	      <div class="form-group">
			   <label>Old password:</label>
	       <input id="pass1" type="password" class="form-control" placeholder="Old password" name="lozinka" required autofocus>
	       </div>
	      <div class="form-group">
			   <label>New password:</label>
	       <input id="pass2" type="password" class="form-control" placeholder="New password" name="novaLozinka">
	        </div>
	       <div class="form-group">
			   <label>Confirm new password:</label>
	       <input id="pass3" type="password" class="form-control" placeholder="Confirm new password" name="ponoviLozinku">
	       </div>
	      <div class="form-group">
			   <label>First name:</label>   
	       <input type="text" class="form-control" placeholder="First Name" name="ime" value="${zaIzmenuUser.firstName}" required> 
	       </div>
	      <div class="form-group">
			   <label>Last name:</label>
	       <input  type="text" class="form-control" placeholder="Last Name" name="prezime" value="${zaIzmenuUser.lastName}" required>	        
	       </div>
	      <div class="form-group">
			   <label>Phone number:</label>
	       <input  type="text" class="form-control" placeholder="Phone number" name="telefon" value="${zaIzmenuUser.phoneNumber}" required>
	       </div>
	       <c:if test="${'Buyer' eq user.DTYPE}">	        
	          <div class="form-group">
			   <label>Address:</label>
	           <input  type="text" class="form-control" placeholder="Address" name="adresa" value="${zaIzmenuUser.address}" required>	           
	       		</div>
	        </c:if>
	         
	     <div class="form-group">
			<label>Email:</label>
	      <div class="input-group">     	       
	       <input type="email" class="form-control" placeholder="Email" value="${zaIzmenuUser.email}" name="email" aria-describedby="basic-addon2" required>
						<span class="input-group-addon" id="basic-addon2">@example.com</span>	       
	       </div>
	       </div>
	       </br>	       
	        <input  class="btn btn-lg btn-success btn-block" type="submit" value="Update"> </input>	       
	        </br></br>
			<div id="div1" class="alert alert-danger message">
			  			<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			  			<span id="mes"></span>
			 </br></div>
	      </form>	    
     	 </div>
     	 </c:if>
    </div>
<script>
$(document).ready(function(){
	$("#div1").hide();
	$("#mes").hide();
});
$("#form1").submit(function()
		{
	if($("#pass2").val() != $("#pass3").val())
		    {
		$("#div1").show();
		 $( "#mes" ).text( "You must enter SAME password in both field's!" ).show();
		     
		 return false;
		    }
		});
</script>
<jsp:include page="end.jsp"/>