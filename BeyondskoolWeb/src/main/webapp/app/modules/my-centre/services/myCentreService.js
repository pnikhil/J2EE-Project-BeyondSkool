/**
 * @function areas Service
 * @memberOf angular.module('beyondSkool')
 * @description 
 */

var myCentreServ = angular.module('beyondSkool');

myCentreServ.service('myCentreService',myCentreService);

/**
 * @ngdoc function
 * @name centresService
 * @description This method is used to get names of activities from restservice.
 * @param Http String that represents Resource URI.
 * 
 * @returns Area data .
 */
function myCentreService($http) {
	
	this.getFullCentreDetails = function(centreEmail){
		return $http({
			method : 'POST',
			url : 'rest/centres/completeCentreProfile',
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
	
	this.retrievebookings = function(centreEmail) {
		bookingRecords = $http({
			method : 'POST',
			url : 'rest/centres/bookingsListForCentre',
			data : jQuery.param({
				'centreEmail' : centreEmail
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});
		return bookingRecords;
	};
	
	this.deleteImage = function(centreName,imageName,centreId,imageId){		
		return $http({
			method : 'POST',
			url : 'rest/centres/deleteImage',
			data : jQuery.param({
				'centreName' : centreName,
				'imageName' : imageName,
				'centreId' : centreId,
				'imageId' : imageId
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});		
	}
	
	this.updateCentreImages = function(centreId,imageNames){		
		return $http({
			method : 'POST',
			url : 'rest/centres/updateImages',
			data : jQuery.param({
				'centreId' : centreId,
				'imageNames' : imageNames
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});		
	}
	
	this.changeBookingStatus = function(choice,bookingId) {
		return $http({
			method : 'POST',
			url : 'rest/bookings/changeBookingStatus',
			data : jQuery.param({
				'choice' : choice,
				'bookingId' : bookingId
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});		
	};
	
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
	
	this.getActivityDetails = function(centreId,centreName){
		return $http({
			method : 'POST',
			url : 'rest/centres/activityData',
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
	
	this.updateActivity = function(centreActivityId,activityId,inTime,outTime,day,
									fromAge,toAge,totalSlots,duration,classDescription,amount,startDate,endDate){
		return $http({
			method : 'POST',
			url : 'rest/activities/updateActivity',
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
	
	this.deleteActivityFromCentre = function(centreActivityId){		
		return $http({
			method : 'POST',
			url : 'rest/centres/deleteCentreActivity',
			data : centreActivityId,
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function(response) {
			return response.data;
		});
	}
	
	this.updateCentreDetails = function(centreId,ownerName,contactNo,description,address/*,imageFiles*/){
		return $http({
			method : 'POST',
			url : 'rest/centres/updateCentreDetails',
			data : jQuery.param({	
				'centreId': centreId,
				'ownerName' : ownerName,
				'contactNo' : contactNo,
				'description' : description,
				'address' : address
				/*'imageFiles' : imageFiles*/
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});	
	}
	
	this.retrieveCityNames = function() {
		cityRecords = $http({
			method : 'GET',
			url : 'rest/cities/citiesList'
		}).then(function(response) {
			return response.data;
		});
		return cityRecords;
	};
	
	this.getActivityNames = function(){
		activityData = $http({
			method : 'GET',
			url : 'rest/activities/activityDataForUserCentre'
		}).then(function(response) {
			return response.data;
		});
		return activityData;
	}
	
	this.createCentreForCurrentUser = function(centreData){
		return $http({
			method : 'POST',
			url: 'rest/centres/createCentreForCurrentUser',
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
			url : 'rest/centres/addActivities',
			data : centreActivities,
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function(response) {
			return response.data;
		});
	};	
};