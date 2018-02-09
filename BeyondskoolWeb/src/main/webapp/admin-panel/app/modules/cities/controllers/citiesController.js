/**
 * @ngdoc controller
 * @name citiesController
 * 
 * @description 
 */

var cities = angular.module('beyondSkool');

cities.controller('citiesController',function($scope,$timeout, $window,$location, $rootScope,citiesService,toaster,$state) {
	$scope.cityState = "display";

	$scope.createCityButton = function(){
		$scope.cityState = "create";
	}
	
	$scope.cityNames = citiesService.retrieveCityNames().then(function(data){
		$scope.cityData  = data;
	});
	
	$scope.refreshCityNames = function(){
		$scope.cityNames = citiesService.retrieveCityNames().then(function(data){
			$scope.cityData  = data;
		});
	}
	
	$scope.view = function(data){
		$scope.cityState = "view";
		$scope.cityInfo = data;
	}
	
	$scope.createCity = function(){		
		citiesService.addCity($scope.cityName).then(function(data){				
			if(data === true){						
				toaster.pop('success', "Successfully added a new city", '', 40000, 'trustedHtml');
				$scope.refreshCityNames();
			}
		});
		$scope.cityState = "display";
	}
	
	$scope.deleteCity = function(cityId){
		citiesService.deleteCity(cityId).then(function(data){	
			if(data === true){						
				toaster.pop('success', "City Deleted Successfully", '', 40000, 'trustedHtml');
				$scope.refreshCityNames();
			}
	});
	}
	
	$scope.updateCity = function(cityId,cityName){
		$scope.cityState = "update";
		$scope.editCity = cityName;
		$scope.editId = cityId;
	}
		
	$scope.updateCityName = function() {
		citiesService.updateCity($scope.editId, $scope.editCity).then(
				function(data) {
					if (data === true) {
						toaster.pop('success', "City Updated Successfully", '',
								40000, 'trustedHtml');
						$scope.refreshCityNames();
					}
				});
		$scope.cityState = "display";
	}	

});