<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="navigation.jsp"/>

<c:if test="${user==null or 'Seller' ne user.DTYPE}">
      	<%response.sendRedirect("./Home"); %>         		
	</c:if>

</br>
</br></br></br>
<div class="row">

<div class="col-md-4 col-md-offset-4 bg-success">
<p> </p>
<h2 class="form-signin-heading alert alert-info">Edit ${zaIzmenuOffer.name }</h2>
	<form  id="form1" action="./OfferServlet?cont=edit&idOffer=${zaIzmenuOffer.id}" method="POST" role="form">			
		
			  	 
			  <div class="form-group">
			   <label>Name:</label>
			   <input type="text" class="form-control" value="${zaIzmenuOffer.name }" id="name" name="name" placeholder="Enter Name(15 char)" maxlength="15" required>			  
			  </div>
			  
			   <div class="form-group">
			  <label>Date Created:</label>			 	
			  <fmt:formatDate value="${zaIzmenuOffer.dateCreated}" pattern="yyyy-MM-dd" var="dateCreated" />						
						<input type="date" id="dateCreated" name="dateCreated" class="form-control" value="${dateCreated }" required>
			  </div>
			  
			  <div class="form-group">
			  <label>Expiration Date:</label>
			  <fmt:formatDate value="${zaIzmenuOffer.expirationDate}" pattern="yyyy-MM-dd" var="expirationDate" />
			 	 <input type="date" id="expirationDate" name="expirationDate" class="form-control" value="${expirationDate }" required/>
			  </div>
			  
			  <div class="form-group">
			 	 <label>Valid From:</label>
			 	  <fmt:formatDate value="${zaIzmenuOffer.validFrom}" pattern="yyyy-MM-dd" var="validFrom" />
			 	 <input  type="date" id="validFrom" name="validFrom" class="form-control"  value="${validFrom }" required>
			  </div>
			  
			  <div class="form-group">
			   <label>Valid To:</label>
			    <fmt:formatDate value="${zaIzmenuOffer.validTo}" pattern="yyyy-MM-dd" var="validTo" />
			 	 <input type="date" id="validTo" name="validTo" class="form-control" value="${validTo }" required>
			  </div>
			  
			  <div class="form-group">
			   <label>Regular price:</label>
			 	 <input type="number"  step="0.01" min="0" value="${zaIzmenuOffer.regularPrice}" class="form-control" id="regularPrice" name="regularPrice" placeholder="Enter regular price"  data-bind="value:replyNumber" required> 		  
			 </div>
			  
			<div class="form-group">
			 <label>Sale price:</label>
			 <input type="number"  step="0.01" min="0" value="${zaIzmenuOffer.salePrice}" class="form-control" id="salePrice" name="salePrice" placeholder="Enter sale rice"  data-bind="value:replyNumber" required> 		  
			 </div>
			  
			  <div class="form-group">
			   <label>Max Offers:</label>
			 <input type="number"  step="1" min="0" value="${zaIzmenuOffer.maxOffers}" class="form-control" id="maxOffers" name="maxOffers" placeholder="Enter max offers"  data-bind="value:replyNumber" required> 		  
			 </div>
			<div class="form-group">
			   <label>Category:</label>
			  <select name="categoryID" class="form-control" >
						<c:set var="offerCategory" value="${zaIzmenuOffer.category.id}"/>
						<c:forEach items="${category}" var="categ">
							<c:set var="categoryID" value="${categ.id}"/>													
							<option value="${categ.id}" <c:if test="${categoryID eq offerCategory}"> selected</c:if>>
							${categ.name}</option>						
						</c:forEach>
						
				</select>		  
			</div>
			  <div class="form-group">
			   <label>Description:</label>
			    <textarea style="resize:none" class="form-control" id="description" name="description" rows="5" placeholder="Enter Description(100 char)" maxlength="100" required>${zaIzmenuOffer.description}</textarea>			
			  </div>			
		<input  class="btn btn-lg btn-success btn-block" type="submit" value="Change"></input>
</br></br>
 <div id="div1" class="alert alert-danger message">
			  			<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			  			<span id="mes"></span>
			 </br></div>
</form>

</div>
</div>
<script>
$(document).ready(function(){
	$("#div1").hide();
	$("#mes").hide();
});
$("#form1").submit(function()
		{
	if($.trim($("#name").val())=='') {
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