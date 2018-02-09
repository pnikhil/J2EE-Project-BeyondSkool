/**
 * @ngdoc controller
 * @name areasController
 * 
 * @description 
 */

var areas = angular.module('beyondSkool');

areas.controller('areasController',function($scope,$timeout, $window,$location, $rootScope,areasService,citiesService,toaster,$state,filterFilter) {
	$scope.areaState = "display";

	$scope.createAreaButton = function(){
		$scope.areaState = "create";		
	}
	
	$scope.areaNames = areasService.retrieveAreaNames().then(function(data){
		$scope.areaData  = data;
		$scope.paginate($scope.areaData.length);
	});
	
	$scope.refreshAreaNames = function(){
		$scope.areaNames = areasService.retrieveAreaNames().then(function(data){
			$scope.areaData  = data;
			$scope.paginate($scope.areaData.length);
		});
	}
	
	$scope.addArea = function(){		
		areasService.addArea($scope.cityId,$scope.newArea).then(function(data){				
			if(data === true){						
				toaster.pop('success', "Successfully added a new area", '', 40000, 'trustedHtml');
				$scope.refreshAreaNames();
			}
		});
		$scope.areaState = "display";
	}
	
	$scope.goBack = function(){
		$scope.areaState = "display";
	}
	
	$scope.paginate = function(size){		
		$scope.totalItems = size,
		$scope.entryLimit = 20, // items per page
		$scope.noOfPages = Math.ceil($scope.totalItems / $scope.entryLimit), $scope.maxSize = 25;
		// $watch search to update pagination
		$scope.$watch('search', function(newVal, oldVal) {		
			$scope.filtered = filterFilter($scope.areaData,newVal);			
			$scope.entryLimit = 20;
			$scope.totalItems = $scope.filtered.length;
			$scope.noOfPages = Math.ceil($scope.totalItems / $scope.entryLimit);
			$scope.currentPage = 1;
		}, true);
	};
	
	$scope.view = function(data){
		$scope.areaState = "view";
		$scope.areaInfo = data;
	}
	
	$scope.cityNames = citiesService.retrieveCityNames().then(function(data){
		$scope.cityData  = data;
	});
	
	$scope.deleteArea = function(areaId){
		areasService.deleteArea(areaId).then(function(data){	
			if(data === true){						
				toaster.pop('success', "Area Deleted Successfully", '', 40000, 'trustedHtml');
				$scope.refreshAreaNames();
			}
	});
	}
	
	$scope.updateArea = function(areaId,areaName){
		$scope.areaState = "update";
		$scope.editArea = areaName;
		$scope.editId = areaId;
	}
	
	$scope.updateAreaName = function() {
		areasService.updateArea($scope.cityId, $scope.editId, $scope.editArea).then(
				function(data) {
					if (data === true) {
						toaster.pop('success', "Area Updated Successfully", '',
								40000, 'trustedHtml');
						$scope.refreshAreaNames();
					}
				});
		$scope.areaState = "display";
	}
});