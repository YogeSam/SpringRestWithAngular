/**
 * 
 */

 angular.module('myApp', ['BookService'])
 	.controller('BookController', function(BookServiceOp) {
          var self = this;
          self.url = "";
          self.books= []; 
          
          self.loadBooks = function(){
        	  BookServiceOp.getBooks().then(function(response) {
                  self.books = response.data;
              }, function(error){
            	  bootbox.alert(error.statusText);
              });
          };          
          
          self.addBook = function() {
        		  self.url = "./bookAdd.jsp";
          };

          self.updateBook = function(bookid) {
        	  var selbook = null;
        	  for(i in self.books){
        		  if(self.books[i].bookId === bookid){
        			  selbook = self.books[i];
        			  break;
        		  }
        	  }
        	  if(selbook != null){
        		  self.url = "./bookUpdate.jsp?bookid=" + selbook.bookId + "&bookname=" + selbook.bookName + "&authorname=" + selbook.authorName;
        	  }
          };
          
          self.deleteBook = function(bookid) {
        	  if(bookid != null){
        		  BookServiceOp.deleteBook(bookid).then(function(response) {
        			  bootbox.alert("Success!!!");
        			  self.loadBooks();
                  }, function(error){
                	  bootbox.alert(error.statusText);
                  });
        	  }
          };

          
          self.resetUrl = function() {
        	  self.url = "";
          };
          
          self.loadBooks();
          
          
      });
 