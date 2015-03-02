<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" 
           uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="navigation.jsp"/>

<c:if test="${user==null or 'Seller' eq user.DTYPE}">
      	<%response.sendRedirect("./Home"); %>         		
	</c:if>
</br>
</br></br></br>
<div class="row">
<div class="container">
<div class="col-md-12">

	<c:choose>
    	<c:when test="${fn:length(coupons) gt 0}">
      <c:if test="${messageCOUP != null}">
	        <div class="alert alert-danger message">
			  			<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			  			${messageCOUP}
			 </br></div>
			</c:if> 
  
	<p> </p>
		<div class="panel panel-success">
			<div class="panel-heading" ><b><font size="5">Coupons</font></b></div>
		 <table class="table myTable table-striped table-hover table-bordered table-condensed table-responsive col-lg-4 col-md-4 col-sm-4 tablesorter">
				<thead>
				<tr class="success">
					<th width="47%">Coupon ID</th>
					<th width="47%">Valid To</th>
					<c:if test="${'Buyer' eq user.DTYPE }">
					<th width="47%">Branches</th>
					</c:if>
					<c:if test="${'Admin' eq user.DTYPE }">
					<th width="8%" bgcolor="yellow">Used</th>
					<th width="8%" bgcolor="yellow">Active</th>				
					</c:if>
				</tr>
				</thead>				
				<tbody>
				<c:forEach items="${coupons}" var="coupons">
				<tr>
					<td>${coupons.couponIdentifies }</td>					
					<td><fmt:formatDate value="${coupons.validTo}" pattern="dd/MM/yyyy" var="validTo" />${validTo}</td>                    
                    <c:if test="${'Admin' eq user.DTYPE }">
	
					<c:choose>
    				<c:when  test="${'false' eq coupons.active and 'false' eq coupons.used}">				
						<td align="center">
								<button type="submit" style="margin:2px" class="btn btn-danger btn-md" disabled><span class="glyphicon glyphicon-minus-sign"></span></button>											
						</td>
						<td align="center">
								<form action="./CouponsServlet?actCH=true" method="post">	
									<button  style="margin:2px" class="btn btn-danger btn-md"><span class="glyphicon glyphicon-minus-sign"></span></button>					
									<input type="hidden" name="activna" value="${coupons.id}">										
								</form>		
						</td>				
					</c:when>
					<c:when test="${'true' eq coupons.used}">
					<td align="center">
								<button type="submit" style="margin:2px" class="btn btn-success btn-md" disabled><span class="glyphicon glyphicon-ok-sign"></span></button>											
						</td>
						<td align="center">
								<button  style="margin:2px" class="btn btn-danger btn-md" disabled><span class="glyphicon glyphicon-minus-sign"></span></button>												
						</td>
					</c:when>
					
					<c:when test="${'false' eq coupons.used and 'true' eq coupons.active}">
					<td align="center">
								<form action="./CouponsServlet?usdCH=true" method="post">	
								<button type="submit" style="margin:2px" class="btn btn-danger btn-md"><span class="glyphicon glyphicon-minus-sign"></span></button>											
						<input type="hidden" name="used" value="${coupons.id}">										
					</form>	
						</td>
						<td align="center">
							<form action="./CouponsServlet?actCH=true" method="post">	
								<button  style="margin:2px" class="btn btn-success btn-md"><span class="glyphicon glyphicon-ok-sign"></span></button>												
									<input type="hidden" name="activna" value="${coupons.id}">										
								</form>
						</td>
					</c:when>
					</c:choose>
	
								
					
									
				</c:if>
				<c:if test="${'Buyer' eq user.DTYPE }">
					<td align="center">
					<form action="./ShowServlet?cont=branches" method="post">
							<button type="submit" class="btn btn-info btn-md"><span class="glyphicon glyphicon-eye-open"></span></button>
							<input type="hidden" value="${coupons.id}" name="couponID">
						</form>
					</td>
				
				</c:if>
				</tr>
				</c:forEach>		
				</tbody>			
			</table>		 
    </div>
    </c:when>
    <c:otherwise>
       <h2><b>You don't have any coupons right now.</b></h2>	
    </c:otherwise>
</c:choose>
    </div>
    </div>
    </div>  
    
<script>
$(function(){
	
	$('.table:not(.donotsort)').find('.startSort').trigger('sort');
	
	
});
</script> 

<jsp:include page="end.jsp"/>