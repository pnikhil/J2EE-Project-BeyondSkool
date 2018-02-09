/**
 * @function areas Service
 * @memberOf angular.module('beyondSkool')
 * @description 
 */

var centresServ = angular.module('beyondSkool');

centresServ.service('centresService',centresService);

centresServ.factory('Model', function($resource) {
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
function centresService($http) {
	
	var centreData = new Array();
	var activityData = new Array();
	
	this.getCentreDetails = function(){
		centreData = $http({
			method : 'GET',
			url : '../rest/centres/centreDataActive'
		}).then(function(response) {
			return response.data;
		});
		return centreData;
	}
	
	this.getFullCentreDetails = function(centreEmail){
		return $http({
			method : 'POST',
			url : '../rest/centres/completeCentreProfile',
			data : jQuery.param({
				'centreEmail' : centreEmail
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
			url : '../rest/signup/checkCentreAccountExists',
			data : emailId,
			headers : {
				'Content-Type' : 'application/json'
			}	
		}).then(function(response) {
			return response.data;
		});
	};
	
		this.updateActivity = function(centreActivityId, activityId, inTime, outTime, day, fromAge, toAge, totalSlots, duration, classDescription, amount, startDate,endDate) {
		return $http({
			method : 'POST',
			url : '../rest/activities/updateActivity',
			data : jQuery.param({
				'centreActivityId' : centreActivityId,
				'activityId' : activityId,
				'inTime' : inTime,
				'outTime' : outTime,
				'day' : day,
				'fromAge' : fromAge,
				'toAge' : toAge,
				'totalSlots' : totalSlots,
				'duration' : duration,
				'classDescription' : classDescription,
				'amount' : amount,
				'startDate' : startDate,
				'endDate' : endDate
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});
	}
		
	this.deleteImage = function(centreName,imageName,centreId,imageId){		
			return $http({
				method : 'POST',
				url : '../rest/centres/deleteImage',
				data : jQuery.param({
					'centreName' : centreName,
					'imageName' : imageName,
					'centreId' : centreId,
					'imageId': imageId
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
			url : '../rest/activities/activityDataForUserCentre'
		}).then(function(response) {
			return response.data;
		});
		return activityData;
	};
	
	this.deleteActivityFromCentre = function(centreActivityId){		
		return $http({
			method : 'POST',
			url : '../rest/centres/deleteCentreActivity',
			data : centreActivityId,
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function(response) {
			return response.data;
		});
	}
	
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
	
	
	this.retrieveAreaNamesForCity = function(cityId) {
		return $http({
			method : 'POST',
			url : '../rest/areas/getAreasforCity',
			data : $.param({				
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
	}
	
	this.getActivityDetails = function(centreId,centreName){
		return $http({
			method : 'POST',
			url : '../rest/centres/activityData',
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
	
	this.deleteCentre = function(centreId){		
		return $http({
			method : 'POST',
			url : '../rest/centres/deleteCentre',
			data : centreId,
			headers : {
				'Content-Type' : 'application/json'
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