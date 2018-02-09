/**
 * @function Reviews Service
 * @memberOf angular.module('beyondSkool')
 * @description 
 */

var reviewsServ = angular.module('beyondSkool');

reviewsServ.service('reviewsService',reviewsService);

/**
 * @ngdoc function
 * @name reviewsService
 * @description This method is used to get names of Areas from restservice.
 * @param Http String that represents Resource URI.
 * 
 * @returns Payments data .
 */
function reviewsService($http) {
	var reviewRecords = new Array();	
	this.retrieveReviews = function() {
		reviewRecords = $http({
			method : 'GET',
			url : '../rest/reviews/reviewsList'
		}).then(function(response) {
			return response.data;
		});
		return reviewRecords;
	};
	
	this.deleteReview = function(reviewId){		
		return $http({
			method : 'POST',
			url : '../rest/reviews/deleteReview',
			data : $.param({				
				'reviewId' : reviewId
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});
	}

};