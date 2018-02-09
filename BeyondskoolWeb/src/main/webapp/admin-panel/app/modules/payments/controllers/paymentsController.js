/**
 * @ngdoc controller
 * @name paymentsController
 * 
 * @description 
 */

var payments = angular.module('beyondSkool');

payments.controller('paymentsController',function($scope,$timeout, $window,$location, $rootScope,paymentsService,toaster,$state,filterFilter) {
	
	
	$scope.payments = paymentsService.retrievePayments().then(function(data){
		$scope.paymentData  = data;
		console.log($scope.paymentData);
		$scope.paginate($scope.paymentData.length);
	});	
	
	$scope.paginate = function(size){		
		$scope.totalItems = size,
		$scope.entryLimit = 20, // items per page
		$scope.noOfPages = Math.ceil($scope.totalItems / $scope.entryLimit), $scope.maxSize = 25;
		// $watch search to update pagination
		$scope.$watch('search', function(newVal, oldVal) {		
			$scope.filtered = filterFilter($scope.paymentData,newVal);			
			$scope.entryLimit = 20;
			$scope.totalItems = $scope.filtered.length;
			$scope.noOfPages = Math.ceil($scope.totalItems / $scope.entryLimit);
			$scope.currentPage = 1;
		}, true);
	};

});