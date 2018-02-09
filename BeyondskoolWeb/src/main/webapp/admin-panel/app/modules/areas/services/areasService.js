/**
 * @function areas Service
 * @memberOf angular.module('beyondSkool')
 * @description 
 */

var areasServ = angular.module('beyondSkool');

areasServ.service('areasService',areasService);

/**
 * @ngdoc function
 * @name citiesService
 * @description This method is used to get names of Areas from restservice.
 * @param Http String that represents Resource URI.
 * 
 * @returns Area data .
 */
function areasService($http) {
	var areaRecords = new Array();	
	this.retrieveAreaNames = function() {
		areaRecords = $http({
			method : 'GET',
			url : '../rest/areas/areasList'
		}).then(function(response) {
			return response.data;
		});
		return areaRecords;
	};
	
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
	
	this.retrieveAreaNamesForCityName = function(cityName) {
		return $http({
			method : 'POST',
			url : '../rest/areas/getAreasforCityName',
			data : $.param({				
				'cityName' : cityName
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});		
	};
	
	
	
	this.addArea = function(cityId,areaName){
		return $http({
			method : 'POST',
			url : '../rest/areas/addArea',
			data : $.param({
				'cityId' : cityId,
				'areaName' : areaName
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});
	}
	
	this.deleteArea = function(areaId){		
		return $http({
			method : 'POST',
			url : '../rest/areas/deleteArea',
			data : areaId,
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function(response) {
			return response.data;
		});
	}	
	
	this.updateArea = function(cityId,areaId,areaName){		
		return $http({
			method : 'POST',
			url : '../rest/areas/updateArea',
			data : $.param({
				'cityId' : cityId,
				'areaId' : areaId,
				'areaName' : areaName
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});
	}
	
	
	/*
	
	*/
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