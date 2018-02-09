/**
 * @function areas Service
 * @memberOf angular.module('beyondSkool')
 * @description 
 */

var signupServ = angular.module('beyondSkool');

signupServ.service('signupService',signupService);

/**
 * @ngdoc function
 * @name centresService
 * @description This method is used to get names of activities from restservice.
 * @param Http String that represents Resource URI.
 * 
 * @returns Area data .
 */
function signupService($http) {
	
	this.centreRegister = function(name,email,password,contact,imageDirId){
		return $http({
			method : 'POST',
			url : 'rest/signup/centreRegister',
			data : jQuery.param({
				'name' : name,
				'email' : email,
				'password' : password,
				'contact' : contact,
				'imageDirId' : imageDirId
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});
	}
	
	this.userRegister = function(name,email,password,contact,childName,childAge,childSchool){
		return $http({
			method : 'POST',
			url : 'rest/signup/userRegister',
			data : jQuery.param({
				'name' : name,
				'email' : email,
				'password' : password,
				'contact' : contact,
				'childName' : childName,
				'childAge' : childAge,
				'childSchool' : childSchool
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
	
	this.checkParentAccountExists = function(emailId){
		return $http({
			method : 'POST',
			url : 'rest/signup/checkParentAccountExists',
			data : emailId,
			headers : {
				'Content-Type' : 'application/json'
			}	
		}).then(function(response) {
			return response.data;
		});
	}
	
};