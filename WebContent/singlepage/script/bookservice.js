/**
 * 
 */
var BookService = angular.module('BookService', [])
BookService.factory('BookServiceOp', ['$http', function ($http) {

    var urlBase = 'http://localhost:8042/rest';
    var BookServiceOp = {};

    BookServiceOp.getBooks = function () {
    	return $http.get(urlBase + '/Book');
    };

    BookServiceOp.addBook = function (bookname, authorname) {
        return $http.post(urlBase + '/Book/' + bookname + "/" + authorname);
    };
    
    BookServiceOp.updateBook = function (bookid, bookname, authorname) {
        return $http.put(urlBase + '/Book/' + bookid + "/" + bookname + "/" + authorname);
    };
    
    
    BookServiceOp.deleteBook = function (bookid) {
        return $http.delete(urlBase + '/Book/' + bookid);
    };

    
    return BookServiceOp;

}]);
