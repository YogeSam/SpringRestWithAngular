<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
<head>
<%@ include file="/header.jsp" %>
  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of Books</title>
</head>
<body>



<div class="container" ng-controller='BookController as ctrl1'>
 <div class="slide-animate-container">
    <div class="slide-animate" ng-include="ctrl1.url"></div>
</div>
 

<div class="alert alert-info">
  <strong>Welcome</strong> <c:out value="${username}" /> !!!
</div>
 <div class="search-container">
      <input ng-focus="ctrl1.selectSearch($event)"   ng-change="ctrl1.searchBook()" ng-model="ctrl1.searchbookname" type="text" placeholder="Search Book or Author.." id="search" name="search">
      <a href="" ng-click="ctrl1.searchBook()" ><span class="glyphicon glyphicon-search" data-toggle="tooltip" title="Search"></span></a>
  </div>
  
 <div class="panel panel-default">
<div class="panel-body" >
	<bookgrid books="ctrl1.books" onupdate="ctrl1.updateBook(bookid)" ondelete="ctrl1.deleteBook(bookid)"></<bookgrid>
</div>
</div>

<div class="panel-footer">
<a href="" ng-click="ctrl1.addBook()">Click on this link to add new books. Total books added are {{ctrl1.books.length}}</a>
</div>



</div>

<script src="/singlepage/script/bookservice.js" ></script>
<script src="/singlepage/script/bookgriddirective.js" ></script>
<script src="/singlepage/script/book.js" ></script>
<script src="/singlepage/script/bookadd.js" ></script>
<script src="/singlepage/script/bookupdate.js" ></script>



</body>
</html>