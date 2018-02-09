/**
 * @function taskSubmissionService
 * @memberOf angular.module('optimaWriteIntake')
 * @description The taskSubmissionService is a angular js service for handling
 *              task submission data from REST service.
 */

var citiesServ = angular.module('beyondSkool');

citiesServ.service('citiesService',citiesService);

/**
 * @ngdoc function
 * @name citiesService
 * @description This method is used to get names of cities from restservice.
 * @param Http
 *            String that represents Resource URI.
 * 
 * @returns City data .
 */
function citiesService($http) {
	var cityRecords = new Array();
	
	this.retrieveCityNames = function() {
		cityRecords = $http({
			method : 'GET',
			url : '../rest/cities/citiesList'
		}).then(function(response) {
			return response.data;
		});
		return cityRecords;
	};
	
	this.addCity = function(cityName){
		console.log(cityName);
		return $http({
			method : 'POST',
			url : '../rest/cities/createCity',
			data : JSON.stringify(cityName),
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function(response) {
			return response.data;
		});
	}
	
	this.deleteCity = function(cityId){
		return $http({
			method : 'POST',
			url : '../rest/cities/deleteCity',
			data : $.param({
				'cityId' : cityId
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});
	}
	
	this.updateCity = function(cityId,cityName){
		console.log(cityId);
		return $http({
			method : 'POST',
			url : '../rest/cities/updateCity',
			data : $.param({
				'cityName' : cityName,
				'cityId' : cityId
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});
	}
/*
	*//**
	 * @ngdoc function
	 * @name retrieveDocumentTypes
	 * @description This method is used to get docClassification from
	 *              restservice.
	 * 
	 * @returns docTypes.
	 *//*
	this.retrieveDocumentTypes = function() {

		docTypes = $http({
			method : 'GET',
			url : 'rest/submissions/docClassification'
		}).then(function(response) {
			return response.data;
		});

		return docTypes;

	};

	*//**
	 * @ngdoc function
	 * @name retrieveGetWorkList
	 * @description This method is used to retrieve work item from restservice.
	 * 
	 * @returns workList.
	 *//*

	this.retrieveGetWorkList = function() {
		workList = $http({
			method : 'GET',
			url : 'rest/submissions/workitems'
		}).then(function(response) {
			return response.data;
		});

		return workList;
	};

	*//**
	 * @ngdoc function
	 * @name moveSubmissionList
	 * @description This method is used to move the selected submissions from
	 *              task pane.
	 * 
	 * @returns workList.
	 *//*
	this.moveSubmissionList = function(selectedSubList) {
		return $http({
			method : 'POST',
			url : 'rest/submissions/moveSubmissions',
			data : JSON.stringify(selectedSubList),
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function(response) {
			return response.data;
		});
	};

	*//**
	 * @ngdoc function
	 * @name updateStatus
	 * @description This method is used to update status on click of
	 *              submissions.
	 * @param submissionId
	 *            String that represents corresponding submissionId.
	 *//*
	this.updateStatus = function(submissionId) {

		return $http({
			method : 'POST',
			url : 'rest/submissions/updateStatus',
			data : JSON.stringify(submissionId),
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function(response) {
			return response.data;
		});

	};

	*//**
	 * @ngdoc function
	 * @name extractSubmission
	 * @description This method is used to extract the submissions.
	 * @param obj
	 *            object that represents corresponding extraction required
	 *            datas.
	 *//*
	this.extractSubmission = function(obj) {
		var caseId = $http({
			method : 'POST',
			url : 'rest/submissions/extract',
			data :obj,
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function(response) {
			return response.data;
		});

		return caseId;
	};

	*//**
	 * @ngdoc function
	 * @name createSubmission
	 * @description This method is used to create the submissions.
	 * @returns submissionId String that represents corresponding submissionId.
	 *//*
	this.createSubmission = function() {
		var fd = new FormData();
		fd.append('username', 'John.Larsson');// hard coded username .to do
												// change
		// Service for create a new submission in db
		var submissionId = $http({
			method : 'POST',
			headers : {
				"Content-Type" : undefined
			},
			data : fd,
			url : 'rest/submissions'
		}).then(function(response) {
			return response.data;
		});
		return submissionId;
	};
	
	this.retrieveCaseCount = function() {
		caseCount = $http({
			method : 'GET',
			url : 'rest/cases/caseCount'
		}).then(function(response) {
			return response.data;
		});
		return caseCount;
	}
*/
};