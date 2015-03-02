<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:setBundle basename="messages.messages"/>
<jsp:include page="navigation.jsp"/>
</br></br></br>
<div class="container" >	
	<div class="container-fluid">		
		<h1>404: <fmt:message key="stranicaNijePronadjenaNaslov"/></h1>
		<p><fmt:message key="stranicaNijePronadjenaPoruka"/></p>
		</div>
		
<jsp:include page="end.jsp"/>