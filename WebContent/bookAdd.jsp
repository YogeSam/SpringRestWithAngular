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
  <h2>Enter New Book Info</h2>
  <form class="form-horizontal" action="/Book/Save">
    <div class="form-group">
      <label class="control-label col-sm-2" for="bookname">Book name</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" id="bookname" placeholder="Book name" name="bookname">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="authorname">Author name</label>
      <div class="col-sm-10">          
        <input type="text" class="form-control" id="authorname" placeholder="Author name" name="authorname">
      </div>
    </div>
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-default btn-primary">Submit</button>
        <a href="/Book" class="btn btn-info" role="button">Cancel</a>
      </div>
    </div>
  </form>
</div>

</body>
</html>