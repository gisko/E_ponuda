<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="navigation.jsp"/>


<c:if test="${user==null or 'Buyer' ne user.DTYPE }">
      	<%response.sendRedirect("./Home"); %>         		
	</c:if>
	
</br>
</br></br></br>
<div class="row">
<div class="container">
<div class="col-md-12">
<p> </p>
	<c:choose>
	<c:when test="${fn:length(couponBranch) gt 0}">
		 <div class="panel panel-success">
			<div class="panel-heading" ><b><font size="5">Coupone is available in these branches:</font></b></div>
		 <table class="table myTable table-striped table-hover table-bordered table-condensed table-responsive col-lg-4 col-md-4 col-sm-4 tablesorter">
				<thead>
				<tr class="success">
					<th width="30%" class="startSort">Name</th>
					<th width="30%">Phone</th>
					<th width="30%">Address</th>
				</tr>
				</thead>				
				<tbody>
				<c:forEach items="${couponBranch}" var="branches">
				<tr>
					<td>${branches.name}</td>
					<td>${branches.phoneNumber }</td>
					<td>${branches.address }</td>		
				</tr>	
				</c:forEach>
				</tbody>			
			</table>				
				 
    </div>
    </c:when>
    <c:otherwise>
    	<h2><b>We don't have it in our branches right now.</b></h2>
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