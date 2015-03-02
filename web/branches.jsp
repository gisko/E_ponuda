<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" 
           uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="navigation.jsp"/>

<c:if test="${user==null or 'Seller' ne user.DTYPE}">
      	<%response.sendRedirect("./Home"); %>         		
	</c:if>
</br>
</br></br></br>
<div class="row">
<div class="container">
<div class="col-md-12">
<form action="./BranchServlet?cont=ADD" method="post">
	<button type="submit" class="btn btn-success btn-md btn-block"><span class="glyphicon glyphicon-plus"></span> Add new branch</button>
	<input type="hidden" value="REG" name="type">
	</form>
<c:choose>
    	<c:when test="${fn:length(branches) gt 0}">
      	
    	
	<p> </p>
	<div class="panel panel-success">
			<div class="panel-heading" ><b><font size="5">Branches</font></b></div>
		 <table class="table myTable table-striped table-hover table-bordered table-condensed table-responsive col-lg-4 col-md-4 col-sm-4 tablesorter">
				<thead>
				<tr class="success">
					<th width="30%" class="startSort">Name</th>
					<th width="30%">Phone</th>
					<th width="30%">Address</th>
					<th width="8%" align="center">Edit</th>
					<th width="8%">Delete</th>
				</tr>
				</thead>				
				<tbody>
				<c:forEach items="${branches}" var="branches">
				<tr>
					<td>${branches.name}</td>
					<td>${branches.phoneNumber }</td>
					<td>${branches.address }</td>
				<td align="center"> <form action="./BranchServlet?cont=CH" method="post">
								<button type="submit" class="btn btn-info btn-md"><span class="glyphicon glyphicon-pencil"></span></button>
								<input type="hidden" value="${branches.id}" name="id">
						</form>
					</td>	        					
						<td align="center"> 
						<button data-toggle="modal" data-target="#${branches.id}" type="submit" class="btn btn-danger btn-md"><span class="glyphicon glyphicon-trash"></span></button>
						</td>			
				</tr>	
				</c:forEach>
				</tbody>			
			</table>		 
    </div>
     </c:when>
    <c:otherwise>
       <h2><b>You don't have any branches right now.</b></h2>	
    </c:otherwise>
</c:choose>
    </div>
    </div>
    </div>
   
   
   <c:forEach items="${branches}" var="branches">
<!-- Modal -->
<div class="modal fade" id="${branches.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">${branches.name}</h4>
      </div>
     	<div class="modal-body">
     		Are u sure that u want to delete this branch?
      </div>
      <div class="modal-footer">     
        <form method="post" action="./RemoveServlet?cont=BR">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <input type="hidden" value="${branches.id}" name="branchesID">
        <button type="submit" class="btn btn-danger"> Delete </button>
        </form>
      </div>
      
      
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