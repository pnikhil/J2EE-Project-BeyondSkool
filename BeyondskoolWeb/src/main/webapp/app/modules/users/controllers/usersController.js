/**
 * @ngdoc controller
 * @name areasController
 * 
 * @description 
 */

var users = angular.module('beyondSkool');

users.controller('usersController',function($scope,$timeout, $window,$location, $rootScope,usersService,toaster,$state,filterFilter) {
	$scope.userState = "display";
	$scope.activity_list = [];
	
	$scope.userDetails = usersService.retrieveUserDetails().then(function(data){	
		$scope.userData  = data;	
		$scope.paginate($scope.userData.length);
	});
	
	$scope.view = function(data){
		$scope.userState = "view";
		$scope.userInfo = data;
	}
	
	$scope.goBack = function(){
		$scope.userState = "display";
	}
	
	$scope.paginate = function(size){		
		$scope.totalItems = size,
		$scope.entryLimit = 20, // items per page
		$scope.noOfPages = Math.ceil($scope.totalItems / $scope.entryLimit), $scope.maxSize = 25;
		// $watch search to update pagination
		$scope.$watch('search', function(newVal, oldVal) {		
			$scope.filtered = filterFilter($scope.userData,newVal);			
			$scope.entryLimit = 20;
			$scope.totalItems = $scope.filtered.length;
			$scope.noOfPages = Math.ceil($scope.totalItems / $scope.entryLimit);
			$scope.currentPage = 1;
		}, true);
	};
	
	$scope.refreshUserDetails = function(){
		$scope.userDetails = usersService.retrieveUserDetails().then(function(data){
			$scope.userData  = data;
		});
	}
	
	$scope.deleteUser = function(userId,beyondskoolId){
		usersService.deleteUser(userId,beyondskoolId).then(function(data){
			if(data === true){						
				toaster.pop('success', "User Deleted Successfully", '', 40000, 'trustedHtml');
				$scope.refreshUserDetails();
			}
	});
	}

	$scope.createUserButton = function(){
		$scope.userState = "create";		
	}
	
	$scope.createUser = function(){			
		var userObject = new Object();
		userObject.beyondskoolid = $scope.beyondskoolid;
		userObject.parentName = $scope.parentName;
		userObject.childName = $scope.childName;
		userObject.age = $scope.age;
		userObject.gender = $scope.gender;
		userObject.email = $scope.email;
		userObject.password = $scope.password;
		userObject.fatherMobile = $scope.fatherMobile;
		userObject.motherMobile = $scope.motherMobile;
		userObject.address = $scope.address;
		userObject.standard = $scope.standard;
		userObject.location = $scope.location;
		userObject.school = $scope.school;
		userObject.preference = $scope.preference;
		userObject.activity_list = $scope.activity_list.toString();
		userObject.timings = $scope.timings;
		console.log(userObject);
		
		usersService.saveUser(userObject).then(function(data){
			console.log(data);
			if(data === true){						
				toaster.pop('success', "User Added Successfully", '', 40000, 'trustedHtml');
				$scope.refreshUserDetails();
				$scope.userState = "display";
			}
		});
	}
	
	$scope.activityNames = function(){
		$scope.activityDetails = usersService.getActivityNames().then(function(data){
			$scope.activityData  = data;			
		});
	}
	
	
	$scope.addActivityToList = function (activity) {
		console.log(activity.activityName);
		var index = $scope.activity_list.indexOf(activity.activityName);
		if(index == -1 && activity.selected){
			$scope.activity_list.push(activity.activityName);
		} else if (!activity.selected && index != -1){
			$scope.activity_list.splice(index, 1);
		}		
	};
	
	$scope.cityNames = citiesService.retrieveCityNames().then(function(data){
		$scope.cityData  = data;
	});
	
	$scope.getAreaNames = function(cityId){
		if(cityId.length>0){
			$scope.areaNames = areasService.retrieveAreaNamesForCity(cityId).then(function(data){
				$scope.areaData  = data;		
			});
		}
		else 
			console.log("no city selected");
	}
	

	/*
	
	$scope.addArea = function(){		
		areasService.addArea($scope.cityId,$scope.newArea).then(function(data){				
			if(data === true){						
				toaster.pop('success', "Successfully added a new area", '', 40000, 'trustedHtml');
				$scope.refreshAreaNames();
			}
		});
		$scope.areaState = "display";
	}
	
	$scope.view = function(data){
		$scope.areaState = "view";
		$scope.areaInfo = data;
	}
	
	
	
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
*/
});

users.directive("userFrame", function() {	
	return {
		restrict : 'AE',
		templateUrl: function(ele, attrs) {
	          return attrs.templatePath;
	      },
		transclude: true		
	};
});

users.filter('range', function() {
	  return function(input, min, max) {
		    min = parseInt(min); //Make string input int
		    max = parseInt(max);
		    for (var i=min; i<max; i++)
		      input.push(i);
		    return input;
		  };
		});