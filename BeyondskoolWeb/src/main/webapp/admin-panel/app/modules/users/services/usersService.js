/**
 * @function areas Service
 * @memberOf angular.module('beyondSkool')
 * @description 
 */

var usersServ = angular.module('beyondSkool');

usersServ.service('usersService',usersService);

/**
 * @ngdoc function
 * @name citiesService
 * @description This method is used to get names of Areas from restservice.
 * @param Http String that represents Resource URI.
 * 
 * @returns Area data .
 */
function usersService($http) {
	var userRecords = new Array();	
	var activityData = new Array();
	
	this.retrieveUserDetails = function() {
		userRecords = $http({
			method : 'GET',
			url : '../rest/users/userDetailList'
		}).then(function(response) {
			return response.data;
		});
		return userRecords;
	};
	
	this.getActivityNames = function(){
		activityData = $http({
			method : 'GET',
			url : '../rest/activities/activityDataForUserCentre'
		}).then(function(response) {
			return response.data;
		});
		return activityData;
	}
	
	this.deleteUser = function(userId){
		return $http({
			method : 'POST',
			url : '../rest/users/deleteUser',
			data : $.param({
				'userId' : userId/*,				
				'beyondskoolId' : beyondskoolId*/
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});
	}
	
	this.saveUser = function(userData){
		return $http({
			method : 'POST',
			url: '../rest/users/saveUser',
			data: userData,
			dataType: 'json',
			headers: {
                'Content-Type' : 'application/json'
            }		
		}).then(function(response){			
			return response.data;
		});
	}
	
	this.checkParentAccountExists = function(emailId){
		return $http({
			method : 'POST',
			url : '../rest/signup/checkParentAccountExists',
			data : emailId,
			headers : {
				'Content-Type' : 'application/json'
			}	
		}).then(function(response) {
			return response.data;
		});
	}
};