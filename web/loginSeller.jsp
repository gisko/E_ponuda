<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="navigation.jsp"/>
<c:if test="${user!=null}">
      	<%response.sendRedirect("./Home"); %>         		
	</c:if>
</br>

</br></br></br>
<div class="row">
		<div class="col-md-4 col-md-offset-4 bg-success bg-rounded">
	      <form class="form-signin" role="form" method="POST" action="./LoginServlet?cont=seller">
	        <h2 class="form-signin-heading alert alert-info">Seller log in</h2>
	        <input type="text" class="form-control" placeholder="Username" name="korisnickoIme" required autofocus>
	        <input type="password" class="form-control" placeholder="Password" name="lozinka" required> 
	        </br>       
	        <input  class="btn btn-lg btn-primary btn-block" type="submit" value="Login"> </input>
	        </br></br>
	      <c:if test="${messageSeller != null}">
	        <div class="alert alert-danger message">
			  			<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			  			${messageSeller}
			 </br></div>
			</c:if> 
	      </form>
	    
     	 </div>
      </div>

    </div> 
<jsp:include page="end.jsp"/>
	
	
	
	