<%@page import="uri.IBookURI"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="header.jsp" %>
<%@ page isErrorPage="true" %>  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="alert alert-danger">
<h1>An unkown error occured. Application does not know how to process your request</h1>
</div>
<a href="<%=IBookURI.CTRL_LISTBOOKURI%>">Click on this link to return to the application</a>

</body>
</html>