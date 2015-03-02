<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="messages.messages"/>
<jsp:include page="navigation.jsp"/>
</br></br></br>
<div class="container" >	
	<div class="container-fluid">
		<h1><fmt:message key="greskaNaslov"/></h1>
		<h2><fmt:message key="tekstGreske"/>:</h2>
		<textarea rows="15" cols="80" >${pageContext.errorData.throwable}:
		<c:forEach var="stackTraceElement" items="${pageContext.errorData.throwable.stackTrace}">
			${stackTraceElement}
		</c:forEach> 		
		</textarea>
		</div>
		</div>
		
<jsp:include page="end.jsp"/>