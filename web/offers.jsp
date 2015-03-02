<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="navigation.jsp"/>

<c:if test="${user==null or 'Buyer' eq user.DTYPE }">
      	<%response.sendRedirect("./Home"); %>         		
	</c:if>
	
</br>
</br></br></br>
<div class="row">
<div class="container">
<div class="col-md-12">

 <c:choose>
    	<c:when test="${fn:length(branches) gt 0}">
	 <c:if test="${'Seller' eq user.DTYPE }">  
	<form action="./OfferServlet?cont=ADD" method="post">
	<button type="submit" class="btn btn-success btn-md btn-block"><span class="glyphicon glyphicon-plus"></span> Add new offer</button>
	<input type="hidden" value="REG" name="type">
	</form>
	</c:if>
	<c:choose>
    	<c:when test="${fn:length(offers) gt 0}">
      
	<p> </p>
	<c:if test="${messageOFF != null}">
	        <div class="alert alert-danger message">
			  			<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			  			${messageOFF}
			 </br></div>
			</c:if> 
	<div class="panel panel-success">
			<div class="panel-heading" >
			 <c:if test="${'Admin' eq user.DTYPE }">  
			
			<form method="post" action="./ShowServlet?cont=seller">
				<li style="list-style:none; color:green;" class="dropdown" id="navigacija">
						<a style="text-decoration: none; color:green;" href="#" class="dropdown-toggle"   data-toggle="dropdown" role="button" aria-expanded="false"><b><font size="5">Offers</font></b><span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li id="userHover"><a href="./ShowServlet?cont=seller&sellersID=All" color="black" >All</a></li>					
						<li class="divider"></li>
						<c:forEach items="${sellers}" var="sellers">
							<li id="userHover"><a href="./ShowServlet?cont=seller&sellersID=${sellers.id }" color="black" >${sellers.username}</a></li>					
						</c:forEach>
							</ul>
				</li>
					
				</form>
				</c:if>
				<c:if test="${'Admin' ne user.DTYPE }">  
				<b><font size="5">Offers</font></b>
				</c:if>
			</div>
		 <table class="table myTable table-striped table-hover table-bordered table-condensed table-responsive col-lg-4 col-md-4 col-sm-4 tablesorter">
				<thead>
				<tr class="success">
					<th width="20%" class="startSort">Name</th>
					<th width="20%">Description</th>
					<th width="30%">Created</th>
					<th width="30%">Expired</th>
					<th width="8%">Max</th>
					<th width="8%">Purchased</th>
					<th width="10%">Regular price</th>
					<th width="10%">Sale price</th>
					<th width="30%">Valid from</th>
					<th width="30%">Valid to</th>
					<th width="20%">Category</th>
				<c:if test="${'Admin' eq user.DTYPE }">
					<th width="10%" bgcolor="yellow">Active</th>
				</c:if>
				<c:if test="${'Seller' eq user.DTYPE }">
					<th width="7%" >Active</th>
					<th width="10%" >Images</th>
					<th width="10%">Edit</th>
					<th width="10%" >Delete</th>
				</c:if>
				</tr>
				</thead>				
				<tbody>
				<c:forEach items="${offers}" var="offers">
				
				<tr>
				
					<td class='clickable-row' data-href='./ShowOfferServlet?offerID=${offers.id}'>${offers.name }</td>
					<td class='clickable-row' data-href='./ShowOfferServlet?offerID=${offers.id}'>${offers.description }</td>
					 <td class='clickable-row' data-href='./ShowOfferServlet?offerID=${offers.id}'>  <fmt:formatDate value="${offers.dateCreated}" pattern="dd/MM/yyyy" var="dateCreated" />${dateCreated}</td>
                       <td class='clickable-row' data-href='./ShowOfferServlet?offerID=${offers.id}'>  <fmt:formatDate value="${offers.expirationDate}" pattern="dd/MM/yyyy" var="expirationDate" />${expirationDate}</td>
                     
					<td class='clickable-row' data-href='./ShowOfferServlet?offerID=${offers.id}'>${offers.maxOffers }</td>
					<td class='clickable-row' data-href='./ShowOfferServlet?offerID=${offers.id}'>${offers.purchasedOffers }</td>	
					<td class='clickable-row' data-href='./ShowOfferServlet?offerID=${offers.id}'>${offers.regularPrice }</td>
					<td class='clickable-row' data-href='./ShowOfferServlet?offerID=${offers.id}'>${offers.salePrice }</td>
					<td class='clickable-row' data-href='./ShowOfferServlet?offerID=${offers.id}'> <fmt:formatDate value="${offers.validFrom}" pattern="dd/MM/yyyy" var="validFrom" /> ${validFrom}</td>
                    <td class='clickable-row' data-href='./ShowOfferServlet?offerID=${offers.id}'> <fmt:formatDate value="${offers.validTo}" pattern="dd/MM/yyyy" var="validTo" /> ${validTo}</td>
               
					<td class='clickable-row' data-href='./ShowOfferServlet?offerID=${offers.id}'>${offers.category.name }</td>
			 <c:if test="${'Admin' eq user.DTYPE }">  
					<td align="center" bgcolor="yellow">
					<c:if test="${'true' eq offers.active }">
					<form action="./OfferServlet?actCH=true" method="post">	
						<input type="checkbox" name="activna1" value="${offers.id}" checked="checked" onclick="this.form.submit();"><br>					
						<input type="hidden" name="activna" value="${offers.id}">				
						
					</form>
					</c:if>
					<c:if test="${'false' eq offers.active }">
					<form action="./OfferServlet?actCH=true" method="post">	
						<input type="checkbox" name="activna1" value="${offers.id}" onclick="this.form.submit();"><br>
						<input type="hidden" name="activna" value="${offers.id}">					
					</form>
					</c:if>								
					</td>
			</c:if>
			<c:if test="${'Seller' eq user.DTYPE }">  
				<c:if test="${'false' eq offers.active }"><td class='clickable-row' data-href='./ShowOfferServlet?offerID=${offers.id}' bgcolor="red" margin="20px"></td></c:if>
				<c:if test="${'true' eq offers.active }"><td class='clickable-row' data-href='./ShowOfferServlet?offerID=${offers.id}' bgcolor="green" margin="20px"></td></c:if>
				
				<td> 
					<form action="./DownloadImageServlet?cont=ADD" method="post">
							<button type="submit" class="btn btn-primary btn-md"><span class="glyphicon glyphicon-picture"></span></button>
							<input type="hidden" value="${offers.id}" name="id">
						</form></td>
						
			<td>
			 <form action="./OfferServlet?cont=CH" method="post">
								<button type="submit" class="btn btn-info btn-md"><span class="glyphicon glyphicon-pencil"></span></button>
								<input type="hidden" value="${offers.id}" name="id">
			 </form>
			
				
			</td>	        					
						<td> 
						<button data-toggle="modal" data-target="#${offers.id}" type="submit" class="btn btn-danger btn-md"><span class="glyphicon glyphicon-trash"></span></button>
							</td>
			</c:if>
				</tr>
		
				</c:forEach>
				
						
				</tbody>			
			</table>		 
    </div>    
    	</c:when>
    <c:otherwise>
       <h2><b>Don't have active offers right now.</b></h2>	
    </c:otherwise>
</c:choose>


	</c:when>
	<c:otherwise>
		<h2><b>You can't add new offer becouse u dont have branches.</b></h2>
	</c:otherwise>
</c:choose>   
    </div>
    </div>
    </div>
    
    
    <c:forEach items="${offers}" var="offers">
<!-- Modal -->
<div class="modal fade" id="${offers.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">${offers.name}</h4>
      </div>

     	<c:set var="myOffer" value="${offers.id}"/>
     	<c:set  var="paymentOffer" value="H"></c:set>
     	<c:set  var="commentOffer" value="H"></c:set>
		<c:forEach items="${payments}" var="payments">
			<c:if test="${myOffer==payments.offer.id}">
				<c:set  var="paymentOffer" value="A"></c:set>
			</c:if>
		</c:forEach> 
		<c:forEach items="${comments}" var="comments">
			<c:if test="${myOffer==comments.offer.id}">
				<c:set  var="commentOffer" value="A"></c:set>
			</c:if>
		</c:forEach>  	
     <c:if test="${paymentOffer == 'A' or commentOffer == 'A'}">
     	<div class="modal-body">
     	This offer can't be deleted! Offer has the following payments/comments.  
      </div>
      <div class="modal-footer">     
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
      </c:if>
      <c:if test="${paymentOffer == 'H' and commentOffer == 'H'}">
     	<div class="modal-body">
     	This offer don't have following payments/comments.
      </div>
      <div class="modal-footer">     
        <form method="post" action="./RemoveServlet?cont=OFF">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <input type="hidden" value="${offers.id}" name="offerID">
        <button type="submit" class="btn btn-danger"> Delete </button>
        </form>
      </div>
      </c:if>
      
    </div>
  </div>
</div>
</c:forEach>
    
    
    
<script>
$(function(){
	
	$('.table:not(.donotsort)').find('.startSort').trigger('sort');
	
	
});
jQuery(document).ready(function($) {
    $(".clickable-row").click(function() {
        window.document.location = $(this).data("href");
    });
    $(".clickable-row").css('cursor', 'pointer');
});


</script> 

<jsp:include page="end.jsp"/>