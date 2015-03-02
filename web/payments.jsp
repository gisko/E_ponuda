<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" 
           uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="navigation.jsp"/>


<c:if test="${user==null or 'Seller' eq user.DTYPE }">
      	<%response.sendRedirect("./Home"); %>         		
	</c:if>
	
</br>
</br></br></br>
<div class="row">
<div class="container">
<div class="col-md-12">	
	<c:choose>
    	<c:when test="${fn:length(payments) gt 0}">
       <c:if test="${messagePAY != null}">
	        <div class="alert alert-danger message">
			  			<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			  			${messagePAY}
			 </br></div>
			</c:if> 
	
	<p> </p>
		<div class="panel panel-success">
			<div class="panel-heading" ><b><font size="5">Payments</font></b></div>
		 <table class="table myTable table-striped table-hover table-bordered table-condensed table-responsive col-lg-4 col-md-4 col-sm-4 tablesorter">
				<thead>
				<tr class="success">
					<th width="35%">Offer</th>					
					<th width="10%">Price</th>
					<c:if test="${'Admin' eq user.DTYPE }">
					<th width="35%">User</th>
					<th width="8%" bgcolor="yellow">Payments made</th>
					<th width="8%" bgcolor="yellow">Canceled</th>				
					
				</c:if>
				</tr>
				</thead>				
				<tbody>
				<c:forEach items="${payments}" var="payments">
				<tr>
					<td>${payments.offer.name }</td>
										
					<td>${payments.price }</td>
                    <c:if test="${'Admin' eq user.DTYPE }">
                    <td>${payments.buyer.username }</td>
					
					
					<c:choose>
    						<c:when test="${'true' eq payments.paymentsMade }">
      						<td align="center">
								<button  data-toggle="modal" data-target="#${payments.id}" type="submit" style="margin:2px" class="btn btn-success btn-md" disabled><span class="glyphicon glyphicon-ok-sign"></span></button>											
							</td>
							<td align="center">
								<form method="post" action="./PaymentServlet?usdCH=true">
								<input type="hidden" name="used" value="${payments.id}">
								<button type="submit" style="margin:2px" class="btn btn-danger btn-md" disabled><span class="glyphicon glyphicon-minus-sign"></span></button>												
							</form>
							</td>
							</c:when>
      						<c:when test="${'true' eq payments.canceled }">
      						<td align="center">
								<button  data-toggle="modal" data-target="#${payments.id}" type="submit" style="margin:2px" class="btn btn-danger btn-md" disabled><span class="glyphicon glyphicon-minus-sign"></span></button>					
							</td>
							<td align="center">
							<form method="post" action="./PaymentServlet?usdCH=true">
								<input type="hidden" name="used" value="${payments.id}">
							<button type="submit" style="margin:2px" class="btn btn-success btn-md" disabled><span class="glyphicon glyphicon-ok-sign"></span></button>												
							</form>
							</td>
      						</c:when>
      						<c:otherwise>
      						<td align="center">
								<button  data-toggle="modal" data-target="#${payments.id}" type="submit" style="margin:2px" class="btn btn-danger btn-md"><span class="glyphicon glyphicon-minus-sign"></span></button>					
							</td>
							<td align="center">
							<form method="post" action="./PaymentServlet?usdCH=true">
								<input type="hidden" name="used" value="${payments.id}">
								<button type="submit" style="margin:2px" class="btn btn-danger btn-md"><span class="glyphicon glyphicon-minus-sign"></span></button>												
							</form>
							</td>
      						</c:otherwise>
      				</c:choose>

					
					</c:if>
				</tr>
				</c:forEach>
	
				</tbody>			
			</table>		 
    </div>
    	
    	
    	</c:when>
    <c:otherwise>
       <h2><b>You don't have any payment right now.</b></h2>	
    </c:otherwise>
</c:choose>
    </div>
    </div>
    </div>
    



    
 <c:forEach items="${payments}" var="payments">
<!-- Modal -->
<div class="modal fade" id="${payments.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Coupon for ${payments.offer.name}</h4>
      </div>
      <c:set var="max" value="${payments.offer.maxOffers}"/>
		<c:set var="kupljeno" value="${payments.offer.purchasedOffers}"/>
      <c:choose>
    	<c:when test="${max-kupljeno gt 0}">
      <form method="post" action="./PaymentServlet?actCH=true">	
      <div class="modal-body">	
      	 <div class="form-group">
      	 <fmt:formatDate value="${payments.offer.validTo}" pattern="dd/MM/yyyy" var="validTo" />
			  Buyer is paid his offer. Click send to create coupon!!! Coupon will be valid until ${validTo }.
		</div>
      </div>
      <div class="modal-footer">      
        <button type="button" id="modalClose" class="btn btn-danger modalClose" data-dismiss="modal">Close</button>
        <input type="hidden" value="${payments.id}" name="payID">
        <button type="submit" class="btn btn-success"> Send </button>     
      </div>
        </form>
        </c:when>
        <c:otherwise>
		  <div class="modal-body">	
      	 	We don't have any ${payments.offer.name} in stock.
      	</div>
      <div class="modal-footer">      
        <button type="button" id="modalClose" class="btn btn-danger modalClose" data-dismiss="modal">Close</button>
      </div>
        
        </c:otherwise>
        </c:choose>
    </div>
  </div>
</div>
</c:forEach>
    
    
<script>
$(function(){
	
	$('.table:not(.donotsort)').find('.startSort').trigger('sort');	
});

</script> 

<jsp:include page="end.jsp"/>