/**
 * @function areas Service
 * @memberOf angular.module('beyondSkool')
 * @description 
 */

var pendingCentresServ = angular.module('beyondSkool');

pendingCentresServ.service('pendingCentresService',pendingCentresService);

pendingCentresServ.factory('Model', function($resource) {
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
function pendingCentresService($http) {
	
	var centreData = new Array();
	var activityData = new Array();
	
	this.getPendingCentreDetails = function(){
		centreData = $http({
			method : 'GET',
			url : '../rest/centres/centreDataPending'
		}).then(function(response) {
			return response.data;
		});
		return centreData;
	}
	
	this.retrieveAreaNamesForCity = function(cityId) {
		return $http({
			method : 'POST',
			url : '../rest/areas/getAreasforCity',
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
	
	
	
	this.retrieveCityNames = function() {
		cityRecords = $http({
			method : 'GET',
			url : '../rest/cities/citiesList'
		}).then(function(response) {
			return response.data;
		});
		return cityRecords;
	};
	
	this.checkCentreAccountExists = function(emailId){
		return $http({
			method : 'POST',
			url : '../rest/signup/checkCentreAccountExists',
			data : emailId,
			headers : {
				'Content-Type' : 'application/json'
			}	
		}).then(function(response) {
			return response.data;
		});
	};
	
	this.updateCentreDetailsByAdmin = function(centreId,ownerName,contactNo,description,address,area,city,emailId){
		return $http({
			method : 'POST',
			url : '../rest/centres/updateCentreDetailsByAdmin',
			data : jQuery.param({	
				'centreId': centreId,
				'ownerName' : ownerName,
				'contactNo' : contactNo,
				'description' : description,
				'address' : address,
				'area': area,
				'city': city,
				'emailId' : emailId				
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});	
	}
	
	this.getActivityDetails = function(centreId,centreName){
		return $http({
			method : 'POST',
			url : '../rest/centres/activityData',
			data : jQuery.param({
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
	
	this.approveCentre = function(centreId,centreEmail){		
		return $http({
			method : 'POST',
			url : '../rest/centres/approveCentre',
			data : jQuery.param({
				'centreId' : centreId,
				'centreEmail' : centreEmail
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});
	}
	
	this.createCentre = function(centreData){
		return $http({
			method : 'POST',
			url: '../rest/centres/createCentre',
			data: centreData,
			dataType: 'json',
			headers: {
                'Content-Type' : 'application/json'
            }		
		}).then(function(response){			
			return response.data;
		});
	}
	
	this.saveCentreActivities = function(centreActivities) {
		return $http({
			method : 'POST',
			url : '../rest/centres/addActivities',
			data :centreActivities,
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function(response) {
			return response.data;
		});
	};	
};