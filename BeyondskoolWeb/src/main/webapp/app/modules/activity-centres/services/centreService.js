/**
 * @function areas Service
 * @memberOf angular.module('beyondSkool')
 * @description 
 */

var centreServ = angular.module('beyondSkool');

centreServ.service('centreService',centreService);

centreServ.factory('Model', function($resource) {
	return $resource('api/upload');
});


/**
 * @ngdoc function
 * @name centresService
 * @description This method is used to get names of activities from restservice.
 * @param Http String that represents Resource URI.
 * 
 * @returns Area data .
 */
function centreService($http) {
	
	var centreData = new Array();
	var activityData = new Array();
	
	this.getSummaryCentreDetailsActive = function(){
		centreData = $http({
			method : 'GET',
			url : 'rest/centres/summaryCentreDetailsActive'
		}).then(function(response) {
			return response.data;
		});
		return centreData;
	}
	
	this.activityFilteredCentre = function(activityId) {
		return $http({
			method : 'POST',
			url : 'rest/centres/activityFilteredCentre',
			data : jQuery.param({				
				'activityId' : activityId
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});		
	};
	
	this.filterByAge = function(age) {
		return $http({
			method : 'POST',
			url : 'rest/centres/filterCentreByAge',
			data : jQuery.param({				
				'age' : age
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});		
	};
	
	this.retrieveAreaNamesForCityName = function(cityName) {
		return $http({
			method : 'POST',
			url : 'rest/areas/getAreasforCityName',
			data : jQuery.param({				
				'cityName' : cityName
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});		
	};
	
	this.getFullCentreDetails = function(centreName){
		return $http({
			method : 'POST',
			url : 'rest/centres/completeCentreData',
			data : jQuery.param({
				'centreName' : centreName
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});
	}
	
	this.getFullCentreDetailsFrontEnd = function(centreName,centreId){
		return $http({
			method : 'POST',
			url : 'rest/centres/completeCentreDataFrontEnd',
			data : jQuery.param({
				'centreName' : centreName,
				'centreId': centreId
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});
	}
	
	this.bookAClass = function(centreActivityId, bookingSlot, cost, userEmail){
		return $http({
			method : 'POST',
			url : 'rest/bookings/bookAClass',
			data : jQuery.param({
				'centreActivityId' : centreActivityId,
				'bookingSlot' : bookingSlot,
				'cost' : cost,
				'userEmail' : userEmail				
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});
	}
	
	this.checkCentreAccountExists = function(emailId){
		return $http({
			method : 'POST',
			url : 'rest/signup/checkCentreAccountExists',
			data : emailId,
			headers : {
				'Content-Type' : 'application/json'
			}	
		}).then(function(response) {
			return response.data;
		});
	}
	
	this.getActivityDetails = function(centreId,centreName){
		return $http({
			method : 'POST',
			url : 'rest/centres/activityData',
			data : $.param({
				'centreId' : centreId,
				'centreName' : centreName
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});
	}
	
	this.getActivityNames = function(){
		activityData = $http({
			method : 'GET',
			url : 'rest/activities/activityDataForUserCentre'
		}).then(function(response) {
			return response.data;
		});
		return activityData;
	}
	
	this.retrieveAreaNamesForCity = function(cityId) {
		return $http({
			method : 'POST',
			url : 'rest/areas/getAreasforCity',
			data : jQuery.param({				
				'cityId' : cityId
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});		
	};
	
	this.submitReview = function(parentEmail,centreId,review,rating,currentTime) {
		return $http({
			method : 'POST',
			url : 'rest/reviews/addReview',
			data : jQuery.param({
				'parentEmail' : parentEmail,
				'centreId' : centreId,
				'review' : review,
				'rating' : rating,
				'currentTime' : currentTime
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});		
	};
	
	this.retrieveCityNames = function() {
		cityRecords = $http({
			method : 'GET',
			url : 'rest/cities/citiesList'
		}).then(function(response) {
			return response.data;
		});
		return cityRecords;
	};
	
	this.getCentreReviews = function(centreName) {
		return $http({
			method : 'POST',
			url : 'rest/reviews/centreReviews',
			data : jQuery.param({				
				'centreName' : centreName
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});
	};
};