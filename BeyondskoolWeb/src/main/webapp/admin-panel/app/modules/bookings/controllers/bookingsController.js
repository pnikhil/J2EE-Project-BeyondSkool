/**
 * @ngdoc controller
 * @name bookingsController
 * 
 * @description 
 */

var bookings = angular.module('beyondSkool');

bookings.controller('bookingsController',function($scope,$timeout, $window,$location, $rootScope,bookingsService,toaster,$state,filterFilter) {
	
	
	$scope.bookings = bookingsService.retrievebookings().then(function(data){
		$scope.bookingData  = data;
		console.log($scope.bookingData);
		$scope.paginate($scope.bookingData.length);
	});	
	
	$scope.paginate = function(size){		
		$scope.totalItems = size,
		$scope.entryLimit = 20, // items per page
		$scope.noOfPages = Math.ceil($scope.totalItems / $scope.entryLimit), $scope.maxSize = 25;
		// $watch search to update pagination
		$scope.$watch('search', function(newVal, oldVal) {		
			$scope.filtered = filterFilter($scope.bookingData,newVal);			
			$scope.entryLimit = 20;
			$scope.totalItems = $scope.filtered.length;
			$scope.noOfPages = Math.ceil($scope.totalItems / $scope.entryLimit);
			$scope.currentPage = 1;
		}, true);
	};

});