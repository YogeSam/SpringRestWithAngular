/**
 * 
 */

 angular.module('myApp', ['ui.bootstrap','BookService'])
 	.controller('BookController', function($scope, $uibModal, BookServiceOp) {
          var self = this;
          self.url = "";
          self.searchbookname = "";
          self.books= []; 
          
          
          self.selectSearch = function($event){
        	  $event.target.select();
          }
          
          self.searchBook = function(){
        	  if(self.searchbookname !== "" && self.searchbookname.length % 3 === 0){
        		  BookServiceOp.searchBooks(self.searchbookname).then(function(response) {
        			  self.books = response.data;
                  }, function(error){
                	  bootbox.alert(error.statusText);
                  });
        	  }else if(self.searchbookname.length === 0){
        		  self.loadBooks();
        	  }        	  
        	  
          }
          
          self.loadBooks = function(){
        	  BookServiceOp.getBooks().then(function(response) {
                  self.books = response.data;
              }, function(error){
            	  bootbox.alert(error.statusText + ". " + error.data.message);
              });
          };          
          
          self.addBook = function() {
        	  self.modalInstance = $uibModal.open({
        		  templateUrl: '/view/Book/Add',
        		  scope: $scope,
        		  });
        	  
        	  //self.url = "/view/Book/Add";
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
        		  self.modalInstance = $uibModal.open({
            		  templateUrl: '/view/Book/Update?bookid=' + selbook.bookId + '&bookname=' + selbook.bookName + '&authorname=' + selbook.authorName,
            		  controller: 'UpdateBookController',
            		  controllerAs: 'ctrl',
            		  scope: $scope,
            		  });
        		  
        		  //self.url = "/view/Book/Update?bookid=" + selbook.bookId + "&bookname=" + selbook.bookName + "&authorname=" + selbook.authorName; 
        	  }
          };
          
          self.deleteBook = function(bookid) {
        	  if(bookid != null){
        		  BookServiceOp.deleteBook(bookid).then(function(response) {
        			  bootbox.alert("Success!!!");
        			  self.loadBooks();
                  }, function(error){
                	  bootbox.alert(error.statusText + ". " + error.data.message);
                  });
        	  }
          };

          
          self.resetUrl = function() {
        	  self.url = "";
          };
          
          self.closemodal = function(){
        	  self.modalInstance.dismiss('cancel');
          }
          
          self.loadBooks();
          
          
      });
 