/**
 * @ngdoc controller
 * @name reviewsController
 * 
 * @description 
 */

var reviews = angular.module('beyondSkool');

reviews.controller('reviewsController',function($scope,$timeout, $window,$location, $rootScope, reviewsService,$state,filterFilter,toaster) {
	
	  $scope.max = 5;
	  $scope.isReadonly = true;

	  $scope.ratingStates = [
	    {stateOn: 'glyphicon-ok-sign', stateOff: 'glyphicon-ok-circle'},
	    {stateOn: 'glyphicon-star', stateOff: 'glyphicon-star-empty'},
	    {stateOn: 'glyphicon-heart', stateOff: 'glyphicon-ban-circle'},
	    {stateOn: 'glyphicon-heart'},
	    {stateOff: 'glyphicon-off'}
	  ];
	
	$scope.reviews = reviewsService.retrieveReviews().then(function(data){
		$scope.reviewData  = data;		
		$scope.paginate($scope.reviewData.length);
	});
	
	$scope.deleteReview = function(reviewId){
		console.log(reviewId);
		reviewsService.deleteReview(reviewId).then(function(data){	
			if(data === true){						
				toaster.pop('success', "Review Deleted Successfully", '', 3000, 'trustedHtml');
				$scope.refreshReviewDetails();
			}
	});
	}
	
	$scope.refreshReviewDetails = function(){
		$scope.reviews = reviewsService.retrieveReviews().then(function(data){
			$scope.reviewData  = data;			
			$scope.paginate($scope.reviewData.length);
		});
	}
	
	$scope.paginate = function(size){		
		$scope.totalItems = size,
		$scope.entryLimit = 20, // items per page
		$scope.noOfPages = Math.ceil($scope.totalItems / $scope.entryLimit), $scope.maxSize = 25;
		// $watch search to update pagination
		$scope.$watch('search', function(newVal, oldVal) {		
			$scope.filtered = filterFilter($scope.reviewData,newVal);			
			$scope.entryLimit = 20;
			$scope.totalItems = $scope.filtered.length;
			$scope.noOfPages = Math.ceil($scope.totalItems / $scope.entryLimit);
			$scope.currentPage = 1;
		}, true);
	};
});