/**
 * @function areas Service
 * @memberOf angular.module('beyondSkool')
 * @description 
 */

var activitiesServ = angular.module('beyondSkool');

activitiesServ.service('activitiesService',activitiesService);

/**
 * @ngdoc function
 * @name activitiesService
 * @description This method is used to get names of activities from restservice.
 * @param Http String that represents Resource URI.
 * 
 * @returns Area data .
 */
function activitiesService($http) {
	
	var activityData = new Array();
	
	this.getActivityDetails = function(){
		activityData = $http({
			method : 'GET',
			url : '../rest/activities/activityData'
		}).then(function(response) {
			return response.data;
		});
		return activityData;
	}
	
	this.addActivity = function(activityName/*,description*/,fileName){
		return $http({
			method : 'POST',
			url : '../rest/activities/addActivity',
			data : $.param({
				'activityName' : activityName,
				/*'description' : description,*/
				'fileName' : fileName
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});
	}
	
	this.deleteActivity = function(activityId){		
		return $http({
			method : 'POST',
			url : '../rest/activities/deleteActivity',
			data : $.param({
				'activityId' : activityId
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});
	}
	
	this.updateActivity = function(activityName,imagePath,activityId){
		return $http({
			method : 'POST',
			url : '../rest/activities/updateActivityData',
			data : $.param({
				'activityName' : activityName,
				'imagePath' : imagePath,
				'activityId' : activityId
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});
	}
	
};