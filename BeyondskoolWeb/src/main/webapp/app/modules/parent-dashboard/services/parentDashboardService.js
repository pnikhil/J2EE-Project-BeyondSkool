/**
 * @function areas Service
 * @memberOf angular.module('beyondSkool')
 * @description 
 */

var parentDashboardServ = angular.module('beyondSkool');

parentDashboardServ.service('parentDashboardService',parentDashboardService);

/**
 * @ngdoc function
 * @name centresService
 * @description This method is used to get names of activities from restservice.
 * @param Http String that represents Resource URI.
 * 
 * @returns Area data .
 */
function parentDashboardService($http) {
	var paymentRecords = new Array();
	var bookingRecords = new Array();
	
	this.getFullParentDetails = function(parentEmail){
		return $http({
			method : 'POST',
			url : 'rest/users/completeParentProfile',
			data : jQuery.param({
				'parentEmail' : parentEmail
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
			url : 'rest/signup/activityData'
		}).then(function(response) {
			return response.data;
		});
		return activityData;
	}
	
	this.retrievePayments = function(parentEmail) {
		paymentRecords = $http({
			method : 'POST',
			url : 'rest/users/paymentsListForParent',
			data : jQuery.param({
				'parentEmail' : parentEmail
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});
		return paymentRecords;
	};
	
	
	this.retrievebookings = function(parentEmail) {
		bookingRecords = $http({
			method : 'POST',
			url : 'rest/users/bookingsListForParent',
			data : jQuery.param({
				'parentEmail' : parentEmail
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});
		return bookingRecords;
	};
	
	this.retrieveWalletInfo = function(parentEmail) {
		bookingRecords = $http({
			method : 'POST',
			url : 'rest/users/retrieveWalletInfoForParent',
			data : jQuery.param({
				'parentEmail' : parentEmail
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});
		return bookingRecords;
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
	
	
	this.updateCentreDetails = function(centreId,ownerName,contactNo,description,address,imageFiles){
		return $http({
			method : 'POST',
			url : 'rest/centres/updateCentreDetails',
			data : jQuery.param({	
				'centreId': centreId,
				'ownerName' : ownerName,
				'contactNo' : contactNo,
				'description' : description,
				'address' : address,
				'imageFiles' : imageFiles
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});	
	};
	
	this.updateParent = function(userData){
		return $http({
			method : 'POST',
			url: 'rest/users/updateParent',
			data: userData,
			dataType: 'json',
			headers: {
                'Content-Type' : 'application/json'
            }		
		}).then(function(response){			
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
			url : 'rest/signup/activityData'
		}).then(function(response) {
			return response.data;
		});
		return activityData;
	}
	
};