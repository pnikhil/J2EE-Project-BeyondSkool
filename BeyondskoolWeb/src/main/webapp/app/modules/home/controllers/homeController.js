/**
 * @ngdoc controller
 * @name homeController
 * 
 * @description 
 */

var home = angular.module('beyondSkool');

home.controller('homeController',function($scope,$timeout, $window,$location, $rootScope,toaster,$state,homeService,sharedProperties) {
	$window.scrollTo(0, 0);
	searchData = [];
	
	$scope.areaNames = homeService.retrieveAreaNames().then(function(data){
		$scope.areaData  = data;		
	});
	
	$scope.searchActivities = function(){
		console.log($scope.age);
		console.log($scope.area);
		console.log($scope.activity);
		searchData.age = $scope.age;
		searchData.area = $scope.area;
		searchData.activity = $scope.activity;
		console.log(searchData);
		sharedProperties.addSearchDetails(searchData);
		$state.go('upcomingClasses');
	}
	
	$scope.activityDetails = homeService.getActivityNames().then(function(data){
		$scope.activityData  = data;		
	});
});

home.directive("homeFrame", function() {	
	return {
		restrict : 'AE',
		templateUrl: function(ele, attrs) {
	          return attrs.templatePath;
	      },
		transclude: true		
	};
});
