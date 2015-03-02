<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" 
           uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="navigation.jsp"/>


</br>
</br></br></br>

<div class="row">
<div class="container">
	<p> </p>
	<div class="col-md-8 col-md-offset-2 bg-success bg-rounded">
	</br>
		<div class="panel panel-success">
			<div class="panel-heading" ><b><font size="5">${showOffer.name}</font></b></div>
	  <table class="table myTable table-striped table-hover table-bordered table-condensed table-responsive col-lg-4 col-md-4 col-sm-4 tablesorter">
				<tr>
					<th width="20%">Date created</th>
					 <td>  <fmt:formatDate value="${showOffer.dateCreated}" pattern="dd/MM/yyyy" var="dateCreated" />${dateCreated}</td>                 
				</tr>
				<tr>
					<th width="20%">Expiration date</th>
					<td>  <fmt:formatDate value="${showOffer.expirationDate}" pattern="dd/MM/yyyy" var="expirationDate" />${expirationDate}</td>
                </tr>
				<tr>
					<th width="20%">Valid from</th>
					<td> <fmt:formatDate value="${showOffer.validFrom}" pattern="dd/MM/yyyy" var="validFrom" /> ${validFrom}</td>
                </tr>
				<tr>
					<th width="20%">Valid to</th>
					 <td> <fmt:formatDate value="${showOffer.validTo}" pattern="dd/MM/yyyy" var="validTo" /> ${validTo}</td>
               	</tr>
				<tr>
					<th width="20%">Regular price</th>
					<td>${showOffer.regularPrice}</td>
				</tr>
				<tr>
					<th width="20%">Sale price</th>
					<td>${showOffer.salePrice}</td>
				</tr>
				<tr>
					<c:set var="max" value="${showOffer.maxOffers}"/>
					<c:set var="kupljeno" value="${showOffer.purchasedOffers}"/>
					<th width="20%">In stock</th>
					<td>${max-kupljeno}</td>
				</tr>
				<tr>
					<th width="20%">Description</th>
					<td>${showOffer.description}</td>
				</tr>
				
				<c:if test="${user==null or 'Buyer' eq user.DTYPE}">
   
				<tr>
					<th width="20%">Category</th>
					<td><a style="text-decoration: none"  href="./Home?catID=${showOffer.category.id}&next=OFFER">${showOffer.category.name}</a></td>
				</tr>
				<tr>
					<th width="20%">Seller</th>
					<td><a style="text-decoration: none"  href="./Home?sellID=${showOffer.manager.id}&next=OFFER">${showOffer.manager.username}</a></td>
				</tr>
				</c:if>
				
				<tr>
					<th width="20%">Branches</th>
					<c:set var="manager" value="${showOffer.manager.id}"/>
					<td>
					<c:forEach items="${branches}" var="branches">
							<c:set var="branchesID" value="${branches.manager.id}"/>													
							<c:if test="${manager eq branchesID}"> ${branches.name } (${branches.address }, ${branches.phoneNumber })</br>
							</c:if>					
						</c:forEach>
					</td>
				</tr>
			
				
	<div id="myCarousel" class="carousel slide" data-ride="carousel">
 		 <!-- Indicators -->
  		<ol class="carousel-indicators">
    		<c:choose>
    			<c:when test="${fn:length(images) gt 0}">
    		
    		<c:forEach var="i" begin="0" end="${fn:length(images)-1}">	
    		<c:choose>
    		<c:when test="${i gt 0}">
      			<li data-target="#myCarousel" data-slide-to="${i}"></li>   	
    		</c:when>
    		<c:otherwise>   
      			<li data-target="#myCarousel" data-slide-to="${i}" class="active"></li> 	
    		</c:otherwise>
			</c:choose>
    		</c:forEach>
    		</c:when>
    		<c:otherwise>
    			<li data-target="#myCarousel" data-slide-to="0" class="active"></li> 
       		</c:otherwise>
    		</c:choose>
    		
 		 </ol>

  		<!-- Wrapper for slides -->
 		 <div class="carousel-inner" role="listbox">  		
    		<c:choose>
    			<c:when test="${fn:length(images) gt 0}">
    		
    		<c:forEach items="${images}" var="images" varStatus="theCount">	
	<c:choose>
    	<c:when test="${theCount.index gt 0}">
    	<div class="item ">
      			<img src="img/${images.location}" style="height: 225px;">
   		 	</div>
    	</c:when>
    <c:otherwise>
      	<div class="item active">
      			<img src="img/${images.location}" style="height: 225px;">
   		 	</div>
    </c:otherwise>
	</c:choose>

		</c:forEach>
	</c:when>
	<c:otherwise>
		<div class="item active">
      			<img src="img/noImage.png" style="height: 225px;">
   		 	</div>
	
	</c:otherwise>
</c:choose>
  		</div>

 	 <!-- Left and right controls -->
  	<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
    	 <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
   		 <span class="sr-only">Previous</span>
  	</a>
 	 <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
   		 <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    	 <span class="sr-only">Next</span>
  	</a>
  </div>	
  
  
  
  
  
  
		</table>
    	</div>
	
  <c:if test="${user!=null}">
    <c:if test="${'Buyer' eq user.DTYPE }">
    
    <p> &nbsp;</p>
		<button type="submit" data-toggle="modal" data-target="#${showOffer.id}"  class="btn btn-success pull-right"  data-loading-text="Loading..."> <span class="glyphicon glyphicon-shopping-cart"></span> Buy</button>	
	
	<div class="row"><p>&nbsp;</p></br></div>
	
	<form method="post" action="./CommentServlet?cont=add">
	<div class="panel panel-primary">
	<input type="hidden" name="ponuda" value="${ showOffer.id}">		
		<label><b>&nbsp;${user.username }</b></label>
		 <textarea style="resize:none" class="form-control" id="message" name="message" rows="5" placeholder="Enter comment" required></textarea>		
		<input type="submit"  class="btn btn-info pull-right" value="Comment">		
	</div>
	</form>
		</c:if>
	
	</c:if>
		<p>&nbsp;</p>
		
		
	<c:forEach items="${comments}" var="comments">		
	<form method="post" action="./CommentServlet?cont=rem">	
	<div class="panel panel-primary">
		<input type="hidden" name="komentar" value="${ comments.id}">
		<input type="hidden" name="ponuda" value="${ showOffer.id}">	
		<label><b>&nbsp;${comments.buyer.username }</b></label>
		<div><p>&nbsp;${comments.message }</p></div>
	<c:if test="${'Seller' eq user.DTYPE }">
		<input type="submit"  class="btn btn-danger pull-right" value="Remove">			
	</c:if>
	</div>
	<c:if test="${'Seller' eq user.DTYPE }">
	<p>&nbsp;</p>
	</c:if>
	</form>
	</c:forEach>
	
	<!-- Modal -->
<div class="modal fade" id="${showOffer.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">${showOffer.name}</h4>
      </div>
      <div class="modal-body">
      <fmt:formatDate value="${showOffer.expirationDate}" pattern="dd/MM/yyyy" var="expirationDate" />
        Are you sure that u want to buy offer "${showOffer.name}" for ${showOffer.salePrice} RSD until ${expirationDate}?
      </div>
      <div class="modal-footer">
       <form method="post" action="./PaymentServlet?cont=ADD&next=show">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
        <input type="hidden" value="${showOffer.id}" name="offerID">
        <button type="submit" class="btn btn-success"> Buy </button>
        </form>
      </div>
    </div>
  </div>
</div>
	
	
	
	
	 </div>
 </div>
</div>  
<jsp:include page="end.jsp"/>