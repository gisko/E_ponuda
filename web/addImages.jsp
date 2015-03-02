e<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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

<div class="col-md-4 col-md-offset-4 bg-success">
<p> </p>
<h2 class="form-signin-heading alert alert-info">Add images for ${offerImage.name}</h2>
<form action="./DownloadImageServlet?idOFF=${offerImage.id}" enctype="multipart/form-data" method="post"  accept-charset="UTF-8">		
		<div class="form-group">
					 <label>Image:</label>
						<table border="0">
							<tr>
								<td widht="90%">
								<input type="file" class="form-control" name="file" />
								<input type="hidden" id="idOFF" name="idOFF" value="${offerImage.id}">
								</td>
								<td width="2%"> </td>
								<td widht="10%"><button class="btn btn-primary" type="submit" > Upload <span class="glyphicon glyphicon-upload"></span></button></td>
							</tr>
						</table>
					</div>
			</form>


<form action="./OfferServlet?cont=head&idOFF=${offerImage.id}" method="POST" role="form">
		<div class="form-group">
			   <label>Chose head image:</label>
			  <select name="headImage" class="form-control" >
						<option value="noImage.png" >No Image</option>
						<c:set var="offerImg" value="${offerImage.headImageID}"/>
						<c:forEach items="${images}" var="images">
						<c:set var="offerImgs" value="${images.location}"/>		
							<option value="${images.location}" <c:if test="${offerImg eq offerImgs}"> selected</c:if>>${images.location}</option>
						</c:forEach>
						
				</select>		  
			</div>
<input  class="btn btn-lg btn-success btn-block" type="submit" value="Set"></input>		
</br></br>
 <!-- <c:if test="${messageKAT != null}">
	       			 <div class="alert alert-danger message">
			  			<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			  			${messageKAT}
			 </br></div>
			</c:if>-->	
</form>
</div>
</div>
<jsp:include page="end.jsp"/>