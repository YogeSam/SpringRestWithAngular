/**
 * 
 */
 angular.module('myApp')
 	.controller('AddBookController', function($scope, BookServiceOp) {
          var self = this;
          self.parentscope  = $scope.ctrl1;
          self.book=
        	  {
        		    "bookId": 0,
        		    "bookName": "",
        		    "authorName": ""
        	  };
          self.addBook = function() {
        	  if(self.book.bookName !== "" && self.book.authorName !== "" ){
        		  BookServiceOp.addBook(self.book).then(function(response) {
        			  bootbox.alert("Success!!!");
        			  self.parentscope.resetUrl();
        			  self.parentscope.loadBooks();
                  }, function(error){
                	  bootbox.alert(error.statusText);
                  });
        	  }
          };
          self.close = function() {
        	  self.parentscope.resetUrl();
          };
      });
 
 