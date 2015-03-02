<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" 
           uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle basename="messages.messages"/>

<c:if test="${'Seller' eq user.DTYPE or 'Admin' eq user.DTYPE}">
      	<%response.sendRedirect("./Home"); %>         		
	</c:if>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="navigation.jsp"/>
<div class="container" >	
	<div class="container-fluid">
		</br></br></br>
		
		<div class="jumbotron" style="height: 345px;" >
		<p>&nbsp;</p>
		<p>&nbsp;</p>
  		<p><a  style="position: relative; margin-top:130px;" id="jumbo" class="btn btn-success btn-lg" href="Contact.jsp" role="button">Find us</a></p>
		</div>
	<c:choose>
    	<c:when test="${fn:length(offers) gt 0}">
      		<c:choose>     			
      			<c:when test="${managerName!=null}">
      				<h2><b>${managerName }</b> </h2>
      			</c:when>
      			 <c:otherwise>	
      				<h2><b>Active offers:</b></h2>	
    			 </c:otherwise>
    		</c:choose>
    	</c:when>
    <c:otherwise>
       <h2><b>We don't have active offers right now.</b></h2>	
    </c:otherwise>
</c:choose>
		<div class="row">
		
		<c:forEach items="${offers}" var="offers">
		
		<div class="col-sm-6 col-md-4 col-lg-3" style="background-color:transparent;">		
		<table  class="table table-responsive table-condensed  col-lg-1 col-md-1 col-sm-1" bgcolor="white" style="float:left;  margin-top:20px; margin-bottom:20px;  border: 2px solid green">	
		<tr>
			<td  style="border:0 none; text-transform: uppercase;" colspan="3" align="center" bgcolor="white"><font size="4"><b>${offers.name}
			</b></font></td>
		<tr>
		<tr>
			<td style="border:0 none; text-transform: uppercase;" colspan="3" align="center" bgcolor="white"><font size="2">${offers.category.name}
			</font></td>
		<tr>
		<tr>
			<td colspan="3"  bgcolor="white"  style="border:0 none;" align="center">
			<a href="./ShowOfferServlet?offerID=${offers.id}">
			
			<c:choose>  
			<c:when test="${offers.headImageID!=null}">
			<img src="img/${offers.headImageID}" class="img-rounded"  width="200" height="150" style="margin:10px">
			</c:when>
      		<c:otherwise>	
      			<img src="img/noImage.png" class="img-rounded"  width="150" height="150" style="margin:10px">
    		</c:otherwise>
    		</c:choose>
			</a> </td>		
		</tr>
		<tr></tr>
		<tr></tr>
		<tr>
			<td colspan="2" style="border:0 none;" align="center"  bgcolor="white" ><font size="4"><blink><fmt:formatDate value="${offers.expirationDate}" pattern="dd/MM/yyyy" var="expirationDate" />${expirationDate}</blink></font></td>
					<c:set var="max" value="${offers.maxOffers}"/>
					<c:set var="kupljeno" value="${offers.purchasedOffers}"/>
			<td bgcolor="white" style="border:0 none;" align="center" ><span class="badge"><font size="3"><b>${max-kupljeno}</b></font></span></td>
		
		</tr>
		
		<c:if test="${user!=null}">
               		<c:if test="${'Buyer' eq user.DTYPE }">
		<tr>
			<td style="border:0 none;" bgcolor="white"  align="center"  > <font  color="red" size="5"><strike>${offers.regularPrice }</strike></font></td>
			<td style="border:0 none; "  bgcolor="white" align="center"><font  color="green" size="5"> <b>${offers.salePrice }</b></font></td>
			<td style="border:0 none;" bgcolor="white"  align="center" >  <button  data-toggle="modal" data-target="#${offers.id}" type="submit" style="margin:2px" class="btn btn-success btn-md"><span class="glyphicon glyphicon-shopping-cart"></span></button></td>
		</tr>								
		</c:if>
		</c:if>
		<c:if test="${user==null}">

		<tr>
			<td style="border:0 none;" bgcolor="white"  align="center"  > <font  color="red" size="5"><strike>${offers.regularPrice }</strike></font></td>
			<td style="border:0 none;" bgcolor="white"  align="center" ></td>
			<td style="border:0 none; "  bgcolor="white" align="center"><font  color="green" size="5"> <b>${offers.salePrice }</b></font></td>
		</tr>
		</c:if>
	
	
	</table>
	</div>
	
	</c:forEach>	
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
      <div class="modal-body">
      <fmt:formatDate value="${offers.expirationDate}" pattern="dd/MM/yyyy" var="expirationDate" />
        Are you sure that u want to buy offer "${offers.name}" for ${offers.salePrice} RSD until ${expirationDate}?
      </div>
      <div class="modal-footer">
       <form method="post" action="./PaymentServlet?cont=ADD">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
        <input type="hidden" value="${offers.id}" name="offerID">
        <button type="submit" class="btn btn-success"> Buy </button>
        </form>
      </div>
    </div>
  </div>
</div>
</c:forEach>


 

<jsp:include page="end.jsp"/>