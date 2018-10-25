/**
 * 
 */
var BookDirective = angular.module('BookDirective', []);
BookDirective.directive('bookgrid', function() {
	  return {
		  restrict: 'E',
		  scope: {
		      books: '=',
		      'update': '&onupdate',
		      'delete': '&ondelete',
		    },
		  templateUrl: '/singlepage/bookgrid.html'
			  
		  };
		});
