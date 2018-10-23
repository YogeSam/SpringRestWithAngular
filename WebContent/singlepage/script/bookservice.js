/**
 * 
 */
var BookService = angular.module('BookService', [])
BookService.factory('BookServiceOp', ['$http', function ($http) {

    var urlBase = '/rest';
    var BookServiceOp = {};

    BookServiceOp.getBooks = function () {
    	return $http.get(urlBase + '/Book');
    };

    BookServiceOp.addBook = function (bookname, authorname) {
        return $http.post(urlBase + '/Book/' + bookname + "/" + authorname);
    };

    BookServiceOp.addBook = function (book) {
        return $http.post(urlBase + '/Book', book);
    };

    
    BookServiceOp.updateBook = function (bookid, bookname, authorname) {
        return $http.put(urlBase + '/Book/' + bookid + "/" + bookname + "/" + authorname);
    };
    
    BookServiceOp.updateBook = function (book) {
        return $http.put(urlBase + '/Book', book);
    };
    
    
    BookServiceOp.deleteBook = function (bookid) {
        return $http.delete(urlBase + '/Book/' + bookid);
    };

    BookServiceOp.searchBooks = function (bookname) {
    	return $http.get(urlBase + '/Book/Search/BookName/' + bookname);
    };
    
    return BookServiceOp;

}]);

