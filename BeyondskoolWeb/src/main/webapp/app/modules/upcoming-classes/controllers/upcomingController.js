/**
 * @ngdoc controller
 * @name upcomingController
 * 
 * @description 
 */

var upcoming = angular.module('beyondSkool');
upcoming.controller('upcomingController',function($scope,$window,$location,$cookieStore, $rootScope,upcomingService,centreService,toaster,$state,$timeout,filterFilter,$stateParams,$localStorage,AuthenticationService,sharedProperties) {
	$window.scrollTo(0, 0);
	$scope.searchActive = false;
	$scope.searchDetail = sharedProperties.getSearchDetails();
	$scope.noResult = false;
	var length = 0;
	for(index in $scope.searchDetail){
	   if($scope.searchDetail.hasOwnProperty(index)) {length++;}
	}	
	if(length === 3){
		upcomingService.filterActivityByHomeFilter($scope.searchDetail.activity,$scope.searchDetail.age,$scope.searchDetail.area).then(function(data){
			$scope.upcomingData  = data;			
			if($scope.upcomingData.length>0){
				for(var i=0;i<$scope.upcomingData.length;i++){
					if($scope.upcomingData[i].imagePath == null){
						$scope.upcomingData[i].imagePath = "";
						$scope.upcomingData[i].imagePath = $scope.upcomingData[i].imagePath.split(",");
					}
					else{
						$scope.upcomingData[i].centreImagePath = $scope.upcomingData[i].centreImagePath.split(",");	
					}
					$scope.upcomingData[i].day = $scope.upcomingData[i].day.split(",");
					$scope.upcomingData[i].dayData = "";
					console.log($scope.upcomingData[i].day);
					for(var j = 0;j<$scope.upcomingData[i].day.length;j++){
						if($scope.upcomingData[i].day.length != 1){
							$scope.upcomingData[i].dayData += $scope.upcomingData[i].day[j].substring(0,3);
						}
						else{
							$scope.upcomingData[i].dayData += $scope.upcomingData[i].day[j];
						}
						
						if(j != $scope.upcomingData[i].day.length - 1){
							$scope.upcomingData[i].dayData += ",";
						}					
					}
				}
				$scope.dataLoaded = true;
				$window.scrollTo(0, 0);
				//$scope.age = $scope.searchDetail.age;
				//$scope.activity.id = $scope.searchDetail.activity;
				//$scope.removeFilter = true;
				//$scope.paginate($scope.upcomingData.length);
			}
			else{
				toaster.pop('success', "No activities found for your search. Loading all activities for Age " + $scope.searchDetail.age, '', 8000, 'trustedHtml');	
				$scope.filterByAge($scope.searchDetail.age);
				sharedProperties.addSearchDetails(null);
			}			
		});
	}
	else{
		upcomingClassDetails();
	}
	console.log($scope.searchDetail);
	$scope.bookClick = false;
	$scope.removeFilter = false;
	$scope.isOwner = false;
	$scope.dataLoaded = false;
	//$scope.performingSearch = false;

	if($localStorage.currentUser !== undefined){
		$scope.userEmail = $localStorage.currentUser.username;
	}
	
	$scope.reloadData = function(){			
			$scope.upcomingData  = $scope.upcomingDataStore;
			$scope.removeFilter = true;
	}
	
	$scope.parentLogin = function(username,password) {
			$scope.userLoginResult = "noData";
			$scope.selectedUserRole = "PARENT"
			AuthenticationService.parentLogin(username, password, $scope.selectedUserRole, function(result){								
					if(result === 1){
					$rootScope.jwtToken = $localStorage.currentUser.token;
					$rootScope.isLoggedIn = true;
					$rootScope.isParentLoggedIn = true;
					$rootScope.userRole = $localStorage.currentUser.role;
					if($localStorage.currentUser.role === "PARENT")
						$state.go('packages');
						$window.scrollTo(0, 0);
						$scope.userEmail = $localStorage.currentUser.username;
						toaster.pop('success', "Your have successfully logged in", '', 4000, 'trustedHtml');
					}					
				else if(result === -1){
					$rootScope.isLoggedIn = false;
					$scope.incorrectLogin = true;				
					$scope.loginWarning = false;
				}
			});	
		}
	
	$scope.activityDetails = centreService.getActivityNames().then(function(data){
		$scope.activityData2  = data;
		$scope.activityData = [];
		for(var i = 0; i < $scope.activityData2.length; i++){
			$scope.activityData[i] = [];
			$scope.activityData[i]['activityName'] = $scope.activityData2[i].activityName;
			$scope.activityData[i]['id'] = $scope.activityData2[i].id;			
		}
	});
	
	$scope.cityNames = centreService.retrieveCityNames().then(function(data){
		$scope.cityData  = data;		
	});
	
	$scope.getAreaNames = function(cityName){
		if(cityName.length>0){
			$scope.areaNames = centreService.retrieveAreaNamesForCityName(cityName).then(function(data){
				$scope.areaData  = data;		
			});
		}
	}
	
	$scope.filterByAge = function(age){
		$scope.currentPage = 1;
		$scope.age = age;
		upcomingService.filterUpcomingByAge(age).then(function(data){
			$scope.upcomingData = data;			
			if($scope.upcomingData.length>0){
				for(var i=0;i<$scope.upcomingData.length;i++){
					if($scope.upcomingData[i].imagePath == null){
						$scope.upcomingData[i].imagePath = "";
						$scope.upcomingData[i].imagePath = $scope.upcomingData[i].imagePath.split(",");
					}
					else{
						$scope.upcomingData[i].centreImagePath = $scope.upcomingData[i].centreImagePath.split(",");	
					}
					$scope.upcomingData[i].day = $scope.upcomingData[i].day.split(",");
					$scope.upcomingData[i].dayData = "";
					console.log($scope.upcomingData[i].day);
					for(var j = 0;j<$scope.upcomingData[i].day.length;j++){
						if($scope.upcomingData[i].day.length != 1){
							$scope.upcomingData[i].dayData += $scope.upcomingData[i].day[j].substring(0,3);
						}
						else{
							$scope.upcomingData[i].dayData += $scope.upcomingData[i].day[j];
						}
						
						if(j != $scope.upcomingData[i].day.length - 1){
							$scope.upcomingData[i].dayData += ",";
						}					
					}
				}
				$scope.searchActive = true;
			}
			$scope.dataLoaded = true;
			toaster.pop('success', "Filtered results with Age " + age, '', 4000, 'trustedHtml');			
			$scope.removeFilter = true;
			$scope.searchActive = true;
		});
	}
	
	$scope.filterByActivity = function(activityId){
		$scope.currentPage = 1;
		console.log($scope.age);
		if($scope.age === undefined){
				upcomingService.filterByActivity(activityId).then(function(data){
					$scope.upcomingData = data;					
					if($scope.upcomingData.length>0){
						for(var i=0;i<$scope.upcomingData.length;i++){
							if($scope.upcomingData[i].imagePath == null){
								$scope.upcomingData[i].imagePath = "";
								$scope.upcomingData[i].imagePath = $scope.upcomingData[i].imagePath.split(",");
							}
							else{
								$scope.upcomingData[i].centreImagePath = $scope.upcomingData[i].centreImagePath.split(",");	
							}
							$scope.upcomingData[i].day = $scope.upcomingData[i].day.split(",");
							$scope.upcomingData[i].dayData = "";
							console.log($scope.upcomingData[i].day);
							for(var j = 0;j<$scope.upcomingData[i].day.length;j++){
								if($scope.upcomingData[i].day.length != 1){
									$scope.upcomingData[i].dayData += $scope.upcomingData[i].day[j].substring(0,3);
								}
								else{
									$scope.upcomingData[i].dayData += $scope.upcomingData[i].day[j];
								}
								
								if(j != $scope.upcomingData[i].day.length - 1){
									$scope.upcomingData[i].dayData += ",";
								}					
							}
						}
					}
				$window.scrollTo(0, 0);
				toaster.pop('success', "Filtered results with Activity", '', 4000, 'trustedHtml');
				$scope.removeFilter = true;
				$scope.searchActive = true;
			});
		}
		else{
			upcomingService.filterByActivityAndAge(activityId,$scope.age).then(function(data){
				$scope.upcomingData = data;
				if($scope.upcomingData.length>0){
					for(var i=0;i<$scope.upcomingData.length;i++){
						if($scope.upcomingData[i].imagePath == null){
							$scope.upcomingData[i].imagePath = "";
							$scope.upcomingData[i].imagePath = $scope.upcomingData[i].imagePath.split(",");
						}
						else{
							$scope.upcomingData[i].centreImagePath = $scope.upcomingData[i].centreImagePath.split(",");	
						}
						$scope.upcomingData[i].day = $scope.upcomingData[i].day.split(",");
						$scope.upcomingData[i].dayData = "";
						console.log($scope.upcomingData[i].day);
						for(var j = 0;j<$scope.upcomingData[i].day.length;j++){
							if($scope.upcomingData[i].day.length != 1){
								$scope.upcomingData[i].dayData += $scope.upcomingData[i].day[j].substring(0,3);
							}
							else{
								$scope.upcomingData[i].dayData += $scope.upcomingData[i].day[j];
							}
							
							if(j != $scope.upcomingData[i].day.length - 1){
								$scope.upcomingData[i].dayData += ",";
							}					
						}
					}
				//$scope.upcomingData = $scope.upcomingDataStore.slice(0, 6);	
				$window.scrollTo(0, 0);				
				$scope.dataLoaded = true;
				toaster.pop('success', "Filtered results with Activity and Age", '', 4000, 'trustedHtml');
				$scope.removeFilter = true;
				$scope.searchActive = true;
				}
				else{
					$scope.upcomingData = [];
					$scope.noResult = true;
					$window.scrollTo(0, 0);				
					$scope.dataLoaded = true;
					toaster.pop('success', "No Results Found", '', 4000, 'trustedHtml');
					$scope.removeFilter = true;
				}				
			});			
		}
	}	

	function upcomingClassDetails(){
		upcomingService.getUpcomingClassData().then(function(data){
			console.log($scope.upcomingStoredData);
		$scope.upcomingStoredData  = data;
		$scope.upcomingDataStore = data;
		for(var i=0;i<$scope.upcomingDataStore.length;i++){
			if($scope.upcomingDataStore[i].imagePath == null){
				$scope.upcomingDataStore[i].imagePath = "";
				$scope.upcomingDataStore[i].imagePath = $scope.upcomingDataStore[i].imagePath.split(",");
			}
			else{
				$scope.upcomingDataStore[i].centreImagePath = $scope.upcomingDataStore[i].centreImagePath.split(",");	
			}
			$scope.upcomingDataStore[i].day = $scope.upcomingDataStore[i].day.split(",");
			$scope.upcomingDataStore[i].dayData = "";
			console.log($scope.upcomingDataStore[i].day);
			for(var j = 0;j<$scope.upcomingDataStore[i].day.length;j++){
				if($scope.upcomingDataStore[i].day.length != 1){
					$scope.upcomingDataStore[i].dayData += $scope.upcomingDataStore[i].day[j].substring(0,3);
				}
				else{
					$scope.upcomingDataStore[i].dayData += $scope.upcomingDataStore[i].day[j];
				}
				
				if(j != $scope.upcomingDataStore[i].day.length - 1){
					$scope.upcomingDataStore[i].dayData += ",";
				}					
			}
		}
		$scope.upcomingData = $scope.upcomingDataStore.slice(0, 4);		
		$scope.dataLoaded = true;
	});
}
	$scope.getMoreData = function () {
		if($scope.searchActive == false){
		if($scope.upcomingDataStore != undefined){
	    $scope.upcomingData = $scope.upcomingDataStore.slice(0, $scope.upcomingData.length + 4);		
		}
		}
	}
	
	$scope.paginate = function(size){		
		$scope.totalItems = size,
		$scope.entryLimit = 20, // items per page
		$scope.noOfPages = Math.ceil($scope.totalItems / $scope.entryLimit), $scope.maxSize = 25;
		// $watch search to update pagination
		$scope.$watch('search', function(newVal, oldVal) {		
			$scope.filtered = filterFilter($scope.upcomingData,newVal);			
			$scope.entryLimit = 20;
			$scope.totalItems = $scope.filtered.length;
			$scope.noOfPages = Math.ceil($scope.totalItems / $scope.entryLimit);
			$scope.currentPage = 1;
		}, true);
	};
	
	$scope.removeActivityFilter = function(){
		$scope.searchActive = false;
		if($scope.activity!=undefined){
			$scope.activity.id = "";
		}
		if($scope.search!=undefined){
			if($scope.search.cityName!=undefined){
				$scope.search.cityName = "";
			}
			if($scope.search.areaName!=undefined){
				$scope.search.areaName = "";
			}
		}
		
		if($scope.age!=undefined){
			$scope.age = "";
		}
		upcomingClassDetails();
		$scope.removeFilter = false;
	}
	
	$scope.bookSlotButton = function(){
		$scope.bookClick = true;
	}
	$scope.reset = function(){
		$scope.bookClick = false;
	}
	
	function convertDateTime(str) {
	    var date = new Date(str),
	    mnth = ("0" + (date.getMonth()+1)).slice(-2),
	    day  = ("0" + date.getDate()).slice(-2);
		var hours = date.getHours();
	 	var minutes = date.getMinutes(); 
	 	var ampm = hours >= 12 ? 'pm' : 'am';
	 	hours = hours % 12;
	 	hours = hours ? hours : 12; // the hour '0' should be '12'
	 	minutes = minutes < 10 ? '0'+minutes : minutes;
	 	var strTime = hours + ':' + minutes + ' ' + ampm;
	    return ([day, mnth, date.getFullYear()].join("-") + "  " + strTime);
	}
	
	$scope.bookSlot = function(centreActivityId, bookingSlot,cost){
		
		var formattedBookingSlot = convertDateTime(bookingSlot);
		console.log(cost);
		toaster.pop('warning', "Booking in Progress, please wait for status...", '', 5000, 'trustedHtml');
		
		centreService.bookAClass(centreActivityId, formattedBookingSlot, cost, $scope.userEmail).then(function(data){
			if(data === 1){
				toaster.pop('success', "Booking successful. Check your dashboard for the booking details", '', 4000, 'trustedHtml');
			}else if(data === -1){
				toaster.pop('error', "You do not have sufficient balance in your wallet to book a slot", '', 4000, 'trustedHtml');
			}
			else{
				toaster.pop('error', "Something went wrong", '', 4000, 'trustedHtml');
			}
		});
		$scope.bookClick = false;
	}
	
});