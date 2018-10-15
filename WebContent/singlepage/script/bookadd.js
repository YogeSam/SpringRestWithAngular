/**
 * 
 */
 angular.module('myApp')
 	.controller('AddBookController', function($scope, $filter, BookServiceOp) {
          var self = this;
          self.parentscope  = $scope.ctrl1;
          self.book=
        	  {
        		    "bookId": 0,
        		    "bookName": "",
        		    "authorName": "",
        		    "publisheddate": ""	
        	  };
          self.addBook = function() {
        	  if(self.book.bookName !== "" && self.book.authorName !== "" ){
        		  self.book.publisheddate =  $filter('date')(self.book.publisheddate, "yyyy-MM-dd"); 
        		  BookServiceOp.addBook(self.book).then(function(response) {
        			  bootbox.alert("Success!!!");
        			  self.parentscope.closemodal();
        			  //self.parentscope.resetUrl();
        			  self.parentscope.loadBooks();
                  }, function(error){
                	  bootbox.alert(error.statusText + ". " + error.data.message);
                  });
        	  }
          };
          self.close = function() {
        	  self.parentscope.closemodal();
          };
      });
 
 