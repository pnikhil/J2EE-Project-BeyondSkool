/**
 * @ngdoc controller
 * @name areasController
 * 
 * @description 
 */

var users = angular.module('beyondSkool');

users.controller('usersController',function($scope,$timeout, $window,$location, $rootScope,usersService,toaster,$state,filterFilter,citiesService,areasService) {
	$scope.userState = "display";
	$scope.activity_list = [];
	$scope.activity_know_list = [];
	$scope.email_exists = false;
	
	$scope.checkEmailExist = function(){
		if($scope.email === "" || $scope.email === undefined){
			$scope.email_exists = false;
		}
		usersService.checkParentAccountExists($scope.email).then(function(data){
			if(data === true){						
				$scope.email_exists = true;
			}else{
				$scope.email_exists = false;
			}
		});
	}
	
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
	
	$scope.deleteUser = function(userId){
		usersService.deleteUser(userId).then(function(data){
			if(data === true){						
				toaster.pop('success', "User Deleted Successfully", '', 40000, 'trustedHtml');
				$scope.refreshUserDetails();
			}
	});
	}

	$scope.createUserButton = function(){
		$scope.userState = "create";
		$scope.parentName = "";
		$scope.childName = "";
		$scope.age = "";
		$scope.gender = "";
		$scope.email = "";
		$scope.password = "";
		$scope.fatherMobile = "";
		$scope.motherMobile = "";
		$scope.address = "";
		$scope.standard = "";
		$scope.school = "";
		$scope.city = "";
		$scope.area = "";
		$scope.preference = "";
		$scope.activity_list = [];
		$scope.timings = "";
	}
	
	$scope.createUser = function(){			
		var userObject = new Object();
		/*userObject.beyondskoolid = $scope.beyondskoolid;*/
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
		userObject.school = $scope.school;
		userObject.city = $scope.city;
		userObject.area = $scope.area;
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
	
	$scope.knownsActivityNames = function(){
		$scope.activityDetails = usersService.getActivityNames().then(function(data){
			$scope.knownActivityData  = data;			
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
	
	$scope.getAreaNames = function(cityName){
		if(cityName.length>0){
			$scope.areaNames = areasService.retrieveAreaNamesForCityName(cityName).then(function(data){
				$scope.areaData  = data;		
			});
		}
		else 
			console.log("no city selected");
	}	

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