<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" 
           uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="navigation.jsp"/>


<c:if test="${user==null or 'Admin' ne user.DTYPE }">
      	<%response.sendRedirect("./Home"); %>         		
	</c:if>

</br>
</br></br></br>
<div class="row">
<div class="container">
<div class="col-md-12">
<form action="./RegisterServlet" method="post">
	<button type="submit" class="btn btn-success btn-md btn-block"><span class="glyphicon glyphicon-plus"></span> Add new seller</button>
	<input type="hidden" value="REG" name="type">
	</form>
	
	<c:choose>
    	<c:when test="${fn:length(sellers) gt 0}">
      
	
	<p> </p>
		<div class="panel panel-success">
			<div class="panel-heading" ><b><font size="5">Sellers</font></b></div>
		 <table class="table myTable table-striped table-hover table-bordered table-condensed table-responsive col-lg-4 col-md-4 col-sm-4 tablesorter">
				<thead>
				<tr class="success">
					<th width="20%" class="startSort">First name</th>
					<th width="20%">Last name</th>
					<th width="20%">Username</th>
					<th width="20%">Password</th>
					<th width="20%">Email</th>
					<th width="20%">Phone</th>
					<th width="20%">Edit</th>
					<th width="20%">Delete</th>
				</tr>
				</thead>				
				<tbody>
				<c:forEach items="${sellers}" var="sellers">
				<tr>
					<td>${sellers.firstName }</td>
					<td>${sellers.lastName }</td>
					<td>${sellers.username }</td>
					<td>${sellers.password }</td>
					<td>${sellers.email }</td>
					<td>${sellers.phoneNumber }</td>
					<td> <form action="./RegisterServlet?type=CH" method="post">
								<button type="submit" class="btn btn-info btn-md"><span class="glyphicon glyphicon-pencil"></span></button>
								<input type="hidden" value="${sellers.id }" name="id">
						</form>
					</td>	        					
						<td> 
						<button data-toggle="modal" data-target="#${sellers.id}" type="submit" class="btn btn-danger btn-md"><span class="glyphicon glyphicon-trash"></span></button>
						</td>			
				</tr>
				</c:forEach>
				</tbody>			
			</table>		 
    </div>
    	</c:when>
    <c:otherwise>
       <h2><b>We don't have sellers right now.</b></h2>	
    </c:otherwise>
</c:choose>
    </div>
    </div>
    </div>
    
    
    
 <c:forEach items="${sellers}" var="sellers">
<!-- Modal -->
<div class="modal fade" id="${sellers.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">${sellers.username}</h4>
      </div>

     	<c:set var="catOffer" value="${sellers.id}"/>
     	<c:set  var="haveOffer" value="H"></c:set>
		<c:forEach items="${offers}" var="offers">
			<c:if test="${catOffer==offers.manager.id}">
				<c:set  var="haveOffer" value="A"></c:set>
			</c:if>
		</c:forEach>  	
     <c:if test="${haveOffer == 'A' }">
     	<div class="modal-body">
     	This seller can't be deleted. Seller has the following offers.  
      </div>
      <div class="modal-footer">     
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
      </c:if>
      <c:if test="${haveOffer == 'H' }">
     	<div class="modal-body">
     		This seller don't have following offers. 
      </div>
      <div class="modal-footer">     
        <form method="post" action="./RemoveServlet?cont=SEL">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <input type="hidden" value="${sellers.id}" name="sellersID">
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
</script> 

<jsp:include page="end.jsp"/>