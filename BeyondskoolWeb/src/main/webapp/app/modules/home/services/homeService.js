/**
 * @function areas Service
 * @memberOf angular.module('beyondSkool')
 * @description 
 */

var homeServ = angular.module('beyondSkool');

homeServ.service('homeService',homeService);



/**
 * @ngdoc function
 * @name centresService
 * @description This method is used to get names of activities from restservice.
 * @param Http String that represents Resource URI.
 * 
 * @returns Area data .
 */
function homeService($http) {
	
	var activityData = new Array();
	
	var areaRecords = new Array();	
	
	this.getActivityNames = function(){
		activityData = $http({
			method : 'GET',
			url : 'rest/activities/activityDataForUserCentre'
		}).then(function(response) {
			return response.data;
		});
		return activityData;
	}
	
	
	this.retrieveAreaNames = function() {
		areaRecords = $http({
			method : 'GET',
			url : 'rest/areas/areasList'
		}).then(function(response) {
			return response.data;
		});
		return areaRecords;
	};
	
};

homeServ.factory('sharedProperties', function () {					
	var searchDetail = [];

	  var addSearchDetails = function(searchObj) {
		  searchDetail = searchObj;
	  };

	  var getSearchDetails = function(){
	      return searchDetail;
	  };

	  return {
		addSearchDetails: addSearchDetails,
	    getSearchDetails: getSearchDetails
	  };
	  
    });