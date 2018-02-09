/**
 * @ngdoc controller
 * @name activitiesController
 * 
 * @description 
 */

var activities = angular.module('beyondSkool');

activities.factory('Model', function($resource) {
	return $resource('activities');
});


activities.controller('activitiesController',function($scope,$timeout, $window,$location, $rootScope,activitiesService,toaster,$state,filterFilter,Upload) {
	$scope.activityState = "display";
	$scope.hideOrig = false;
	$scope.activityDetails = activitiesService.getActivityDetails().then(function(data){
		$scope.activityData  = data;		
		console.log($scope.activityData);
		$scope.paginate($scope.activityData.length);
	});
	
	$scope.refreshActivityDetails = function(){
		$scope.activityDetails = activitiesService.getActivityDetails().then(function(data){			
			$scope.activityData  = data;				
			$scope.paginate($scope.activityData.length);
		});
	}
	
	$scope.createActivityButton = function(){
		$scope.activityState = "create";
		$scope.activityName = "";
		/*$scope.description = "";*/
		$scope.picFile="";
	}
	
	$scope.view = function(data){
		$scope.activityState = "view";
		$scope.activityInfo = data;
		console.log($scope.activityInfo.imagePath);
		$scope.fileName = "";
		
	}
	
	$scope.updateButton = function(activity){
		$scope.activityState = "update";
		$scope.hideOrig = false;
		$scope.fileName = "";
		$scope.updateName = activity.activityName;
		$scope.currentImagePath = activity.imagePath;
		$scope.updateId = activity.id;
	}
	
	$scope.updateActivity = function(){
		console.log($scope.updateName);
		console.log($scope.currentImagePath);
		console.log($scope.updateId);
		activitiesService.updateActivity($scope.updateName,$scope.currentImagePath,$scope.updateId).then(function(data){
			if(data===true){
				toaster.pop('success', "Successfully Updated Activity", '', 40000, 'trustedHtml');
				$scope.refreshActivityDetails();
			}
		});
		$scope.activityState = "display";
	}
	
	$scope.createActivity = function(){		
		activitiesService.addActivity($scope.activityName/*,$scope.description*/,$scope.fileName).then(function(data){				
			if(data === true){						
				toaster.pop('success', "Successfully added a new activity", '', 40000, 'trustedHtml');
				$scope.refreshActivityDetails();
			}
		});
		$scope.activityState = "display";
	}
	
	$scope.paginate = function(size){		
		$scope.totalItems = size,
		$scope.entryLimit = 20, // items per page
		$scope.noOfPages = Math.ceil($scope.totalItems / $scope.entryLimit), $scope.maxSize = 25;
		// $watch search to update pagination
		$scope.$watch('search', function(newVal, oldVal) {		
			$scope.filtered = filterFilter($scope.activityData,newVal);			
			$scope.entryLimit = 20;
			$scope.totalItems = $scope.filtered.length;
			$scope.noOfPages = Math.ceil($scope.totalItems / $scope.entryLimit);
			$scope.currentPage = 1;
		}, true);
	};
	
	$scope.deleteActivity = function(activityId){
		console.log(activityId);
		activitiesService.deleteActivity(activityId).then(function(data){	
			if(data === true){						
				toaster.pop('success', "Activity Deleted Successfully", '', 40000, 'trustedHtml');
				$scope.refreshActivityDetails();
			}
	});
	}
	
	$scope.goBack = function(){
		$scope.activityState = "display";
	}
	
	$scope.uploadPic = function(file) {
		file = Upload.rename(file, "activity_" + file.name);
		$scope.fileName = file.ngfName;
		$scope.currentImagePath = $scope.fileName;
		$scope.hideOrig = true;
		  file.upload = Upload.upload({
		    url: '../rest/activities/fileUpload',
		    data: {file: file },
		  });
		  file.upload.then(function (response) {			
		    $timeout(function () {
		    file.result = response.data;
		    });
		  }, function (response) {			 
		    if (response.status > 0)
		    $scope.errorMsg = "Server Error! ("+response.data+")";
		  }, function (evt) {
		    // Math.min is to fix IE which reports 200% sometimes
		    file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
		  });
		  }

});