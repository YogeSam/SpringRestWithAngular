<%@page import="service.IBookList"%>
<%@page import="model.BookConcrete"%>
<%@page import="uri.IBookURI"%>
<%@page import="java.util.ArrayList"%>



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="header.jsp" %>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div class="container">
 <div class="panel panel-default">
 
<div class="panel-heading">List Books</div>


<div class="panel-body">
<% 
		IBookList b = (IBookList)request.getSession().getAttribute("Book");
		long bookcount = b.bookCount();
%>
		
		
<%
		if(b == null){
%>		
			<h1>No books entered</h1>
<%
		}else{

%>
		<table class="table table-striped table-hover">
	<thead>
	    <tr>
	      <th scope="col"></th>
	      <th scope="col">Name</th>
	      <th scope="col">Author</th>
	      <th scope="col"></th>
	    </tr>
	  </thead>		
<%			
			ArrayList <BookConcrete> list = b.findBookById(-1); //get all books
			for(BookConcrete b1 : list){
%>
			<tr><td><a href="<%=IBookURI.CTRL_UPDATEBOOKURI%>?bookid=<%=b1.getBookId()%>"><span class="glyphicon glyphicon-envelope" data-toggle="tooltip" title="Update"></span></a></td>	
			<td> <%=b1.getBookName()%> </td>
			<td> <%=b1.getAuthorName()%> </td>
			<td><a href="<%=IBookURI.CTRL_DELBOOKURI%>?bookid=<%=b1.getBookId()%>"><span class="glyphicon glyphicon-remove-circle"></span></a></td></tr>
<%
			}
%>

		</table>
	
<%			
		}
%>

</div>


<div class="panel-footer">
<a href="<%=IBookURI.CTRL_ADDBOOKURI%>">Click on this link to add new books. Total books added are <%=bookcount%></a>
</div>
</div>
</div>

</body>
</html>