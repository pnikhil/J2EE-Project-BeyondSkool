/**
 * @function areas Service
 * @memberOf angular.module('beyondSkool')
 * @description 
 */

var upcomingServ = angular.module('beyondSkool');

upcomingServ.service('upcomingService',upcomingService);
/*
upcomingServ.factory('Model', function($resource) {
	return $resource('api/upload');
});*/


/**
 * @ngdoc function
 * @name centresService
 * @description This method is used to get names of activities from restservice.
 * @param Http String that represents Resource URI.
 * 
 * @returns Area data .
 */
function upcomingService($http) {
	
	var upcomingClassData = new Array();
	
	this.getUpcomingClassData = function(){
		return $http({
			method : 'GET',
			url : 'rest/activities/upcomingClassData'
		}).then(function(response) {
			return response.data;
		});		
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
	
	/*this.filterByAge = function(age) {
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
	};*/
	
	/*this.getFullCentreDetails = function(centreName){
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
	}*/
	
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
	
	this.filterUpcomingByAge = function(age) {
		return $http({
			method : 'POST',
			url : 'rest/activities/filterUpcomingByAge',
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
	
	this.filterActivityByHomeFilter = function(activity,age,area){
		
		return $http({
			method : 'POST',
			url : 'rest/activities/filterActivityByHomeFilter',
			data : jQuery.param({				
				'activity' : activity,
				'age' : age,
				'area' : area
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});	
	}
	
	this.filterByActivity = function(activityId) {
		return $http({
			method : 'POST',
			url : 'rest/activities/filterUpcomingByActivity',
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
	
	this.filterByActivityAndAge = function(activityId,age) {
		return $http({
			method : 'POST',
			url : 'rest/activities/filterUpcomingByActivityAndAge',
			data : jQuery.param({				
				'activityId' : activityId,
				'age' : age
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});		
	};
};