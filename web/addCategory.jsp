<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="navigation.jsp"/>

<c:if test="${user==null or 'Admin' ne user.DTYPE}">
      	<%response.sendRedirect("./Home"); %>         		
	</c:if>
</br>
</br></br></br>
<div class="row">

<div class="col-md-4 col-md-offset-4 bg-success">
<p> </p>
	<form  id="form1" action="./CategoryServlet?cont=unesi" method="POST" role="form">			
			 <h2 class="form-signin-heading alert alert-info">Add new category</h2>
			  <div class="form-group">
			   <label>Name:</label>
			   <input type="text" class="form-control" id="name" name="naziv" placeholder="Enter Name(15 char)" maxlength="15" required>			  
			  </div>
			  <div class="form-group">
			   <label>Description:</label>
			    <textarea style="resize:none" class="form-control" id="description" name="opis" rows="5" placeholder="Enter Description(100 char)" maxlength="100" required></textarea>			
			  </div>			
		<input  class="btn btn-lg btn-success btn-block" type="submit" value="Add"></input>
</br></br>
 <c:if test="${messageCAT != null}">
	       			 <div class="alert alert-danger message">
			  			<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			  			${messageCAT}
			 </br></div>
			</c:if>
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
		 $( "#mes" ).text( "Name can't be empty string!" ).show();
		     
		 return false;
	}
		});

</script>

<jsp:include page="end.jsp"/>