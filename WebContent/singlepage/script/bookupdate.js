/**
 * 
 */
 angular.module('myApp')
 	.controller('UpdateBookController', function($scope,  $filter, BookServiceOp) {
          var self = this;
          self.parentscope  = $scope.ctrl1;
          self.book=
        	  {
        		    "bookId": 0,
        		    "bookName": "",
        		    "authorName": "",
        		    "publisheddate": ""	
        	  };
          $scope.init = function(bookid, bookname, authorname, publisheddate ){
        	self.book.bookId = bookid;
        	self.book.bookName = bookname;
        	self.book.authorName = authorname;
        	self.book.publisheddate = new Date(publisheddate.replace(/-/g, '/'));
          };
          self.updateBook = function() {
        	  self.book.publisheddate =  $filter('date')(self.book.publisheddate, "yyyy-MM-dd"); 
    		  BookServiceOp.updateBook(self.book).then(function(response) {
    			  bootbox.alert("Success!!!");
    			  self.parentscope.closemodal();
    			  self.parentscope.loadBooks();
              }, function(error){
            	  bootbox.alert(error.statusText + ". " + error.data.message);
              });        	  
          };
          self.close = function() {
        	  self.parentscope.closemodal();
          };
      });
 
 /**
 * 
 */