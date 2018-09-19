<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html >
<html>
<head>
	<%@ include file="header.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring App</title>
</head>
<body>
<div class="container">
  <h2>Book MDB</h2>
  <hr />
  <h4>Login Form</h4>

	<form action='<spring:url value="/signin"/>' method="post">
	  <div class="form-group">
	    <label for="username">User Name</label>
	    <input type="text" class="form-control"  name="username" id="username" aria-describedby="username" placeholder="Enter user name">
	  </div>
	  <div class="form-group">
	    <label for="password">Password</label>
	    <input type="password" name="password" class="form-control" id="password" placeholder="Password">
	  </div>
	  <button type="submit" class="btn btn-primary">Submit</button>
	</form>
  	<br/>
  </div>
  
</body>
</html>