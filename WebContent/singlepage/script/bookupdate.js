/**
 * 
 */
 angular.module('myApp')
 	.controller('UpdateBookController', function($scope, BookServiceOp) {
          var self = this;
          self.parentscope  = $scope.ctrl1;
          self.book=
        	  {
        		    "bookId": 0,
        		    "bookName": "",
        		    "authorName": ""
        	  };
          $scope.init = function(bookid, bookname, authorname){
        	self.book.bookId = bookid;
        	self.book.bookName = bookname;
        	self.book.authorName = authorname;
          };
          self.updateBook = function() {
    		  BookServiceOp.updateBook(self.book.bookId, self.book.bookName, self.book.authorName).then(function(response) {
    			  bootbox.alert("Success!!!");
    			  self.parentscope.closemodal();
    			  self.parentscope.loadBooks();
              }, function(error){
            	  bootbox.alert(error.statusText);
              });        	  
          };
          self.close = function() {
        	  self.parentscope.closemodal();
          };
      });
 
 /**
 * 
 */