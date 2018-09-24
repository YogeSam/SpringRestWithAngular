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
 <div class="panel panel-default">
 
   
<div class="panel-heading">List Books</div>
<div class="panel-body" >
 <div class="search-container">
      <input ng-focus="ctrl1.selectSearch($event)"   ng-change="ctrl1.searchBook()" ng-model="ctrl1.searchbookname" type="text" placeholder="Search Book or Author.." id="search" name="search">
      <a href="" ng-click="ctrl1.searchBook()" ><span class="glyphicon glyphicon-search" data-toggle="tooltip" title="Search"></span></a>
      
  </div>
<div class="slide-animate-container">
    <div class="slide-animate" ng-include="ctrl1.url"></div>
</div>

	
	<table class="table table-striped table-hover">
	<thead>
	    <tr>
	      <th scope="col"></th>
	      <th scope="col">Name</th>
	      <th scope="col">Author</th>
	      <th scope="col"></th>
	    </tr>
	</thead>	
	<tr ng-repeat="book in ctrl1.books">
	    <td ng-click="ctrl1.updateBook(book.bookId);"><a href=""  ><span class="glyphicon glyphicon-envelope" data-toggle="tooltip" title="Update"></span></a></td>
    	<td>{{ book.bookName }}</td>
    	<td>{{ book.authorName }}</td>
    	<td ng-click="ctrl1.deleteBook(book.bookId);"><a href=""><span class="glyphicon glyphicon-remove-circle" data-toggle="tooltip" title="Update"></span></a></td>
  	</tr>
	</table>

</div>
</div>

<div class="panel-footer">
<a href="" ng-click="ctrl1.addBook()">Click on this link to add new books. Total books added are {{ctrl1.books.length}}</a>
</div>

</div>

<script src="/singlepage/script/bookservice.js" ></script>
<script src="/singlepage/script/book.js" ></script>
<script src="/singlepage/script/bookadd.js" ></script>
<script src="/singlepage/script/bookupdate.js" ></script>



</body>
</html>