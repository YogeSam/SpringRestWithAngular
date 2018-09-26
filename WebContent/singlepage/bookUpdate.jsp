<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
<head>
<%@ include file="/header.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="container"  ng-controller='UpdateBookController as ctrl'  ng-init="init('<c:out value="${param.bookid}" />','<c:out value="${param.bookname}" />','<c:out value="${param.authorname}" />')">
  <h2>Enter New Book Info</h2>
  <form class="form-horizontal">
    <div class="form-group">
      <label class="control-label col-xs-2" for="bookname">Book name</label>
      <div class="col-xs-4">
        <input type="text" class="form-control" id="bookname" placeholder="Book name" name="bookname"  ng-model="ctrl.book.bookName">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-xs-2" for="authorname">Author name</label>
      <div class="col-xs-4">          
        <input type="text" class="form-control" id="authorname" placeholder="Author name" name="authorname" ng-model="ctrl.book.authorName">
      </div>
    </div>
    <div class="form-group">        
      <div class="col-sm-offset-2 col-xs-4">
        <button class="btn btn-default btn-primary" ng-click="ctrl.updateBook()">Submit</button>
        <button class="btn btn-default btn-primary" ng-click="ctrl.close()">Cancel</button>
      </div>
    </div>
  </form>
</div>

</body>
</html>