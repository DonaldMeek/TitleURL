<%@ page language="java" 
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Page Titles</title>
	<link rel="stylesheet" type="text/css" href="url_input_format.css"/>
</head>
<body>
	<div class="page_title" >
		Web Page Titles
		<br />
		<br />
		<div class="page_list">
			<c:forEach var="title" items="${titles}">
				<c:out value="${title}" />
				<br />
			</c:forEach>
		</div>
	</div>	
</body>
</html>