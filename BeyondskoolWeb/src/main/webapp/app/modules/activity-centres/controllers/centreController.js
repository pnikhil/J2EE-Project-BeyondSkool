/**
 * @ngdoc controller
 * @name activitiesController
 * 
 * @description 
 */

var viewCentre = angular.module('beyondSkool');
viewCentre.controller('viewCentre',function($scope,$window,$location, $rootScope,centreService,toaster,$state,Upload,$timeout,filterFilter,$stateParams,$localStorage,AuthenticationService,$cookieStore) {
	$window.scrollTo(0, 0);
	
	$scope.getUrlParam = $stateParams.centreName;
	console.log($scope.getUrlParam);
	console.log($stateParams.centreId);
	if($localStorage.currentUser !== undefined){
		$scope.userEmail = $localStorage.currentUser.username;
	}
	$scope.reviewBox = false;
	
	  $scope.max = 5;
	  $scope.isReadonly = true;

	  $scope.ratingStates = [
	    {stateOn: 'glyphicon-ok-sign', stateOff: 'glyphicon-ok-circle'},
	    {stateOn: 'glyphicon-star', stateOff: 'glyphicon-star-empty'},
	    {stateOn: 'glyphicon-heart', stateOff: 'glyphicon-ban-circle'},
	    {stateOn: 'glyphicon-heart'},
	    {stateOff: 'glyphicon-off'}
	  ];
	
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
					/*$state.go('packages');*/
					/*$window.scrollTo(0, 0);*/
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
	
	$scope.centreName = $scope.getUrlParam.replace(/-/g, ' ');
	$scope.imageArray = [];
	$scope.bookClick = false;
	$scope.isOwner = false;
	if($scope.centreName !== ""){
		$scope.centreDetails = centreService.getFullCentreDetailsFrontEnd($scope.centreName,$stateParams.centreId).then(function(data){
			$scope.centreData  = data;
			console.log($scope.centreData);
			$scope.imageArray = $scope.centreData.imagePath.split(",");
			console.log($scope.imageArray);
			$cookieStore.put('centreId',$scope.centreData.id);
		});
	}else{
		$state.go('activityCentres');
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
	
	function getCurrentTime() {
	    var date = new Date(),
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
	
	$scope.getCentreReviews = function(){
		console.log($scope.centreName);
		centreService.getCentreReviews($scope.centreName).then(function(data){
		$scope.reviewData  = data;
		console.log($scope.reviewData);
		});
	}
	
	$scope.addReviewButton = function(){
		$scope.reviewBox = true;
	}
	
	$scope.submitReview = function(){
		if($rootScope.isParentLoggedIn){
			$scope.userEmail = $localStorage.currentUser.username;
			var currentTime = getCurrentTime();
			console.log(currentTime);
			console.log($scope.userReview);
			console.log($scope.userRating);
			//console.log($cookieStore.get('parentId'));
			console.log($cookieStore.get('centreId'));
			centreService.submitReview($scope.userEmail,$cookieStore.get('centreId'),$scope.userReview,$scope.userRating,currentTime).then(function(isSuccess){
				console.log(isSuccess);
				if(isSuccess == 1){
					toaster.pop('success', "Review Added Successfully", '', 4000, 'trustedHtml');
				}
				else if(isSuccess == 2){
					toaster.pop('error', "You have already added a review for this centre", '', 4000, 'trustedHtml');
				}
				$scope.getCentreReviews();
			});
			
		}
	}
	
});

var centre = angular.module('beyondSkool');

centre.factory('Model', function($resource) {
	return $resource('centre');
});

centre.controller('centreController',function($scope,$window,$location, $rootScope,centreService,toaster,$state,Upload,$timeout,filterFilter,$stateParams) {
	$window.scrollTo(0, 0);
	$scope.removeFilter = false;
	$scope.dataLoaded = false;
	$scope.centreDetails = centreService.getSummaryCentreDetailsActive().then(function(data){
		//$scope.centreData  = data;
		$scope.centreData  = data;
		console.log(data);
		for(var i=0;i<$scope.centreData.length;i++){
			if($scope.centreData[i].imagePath == null){
				$scope.centreData[i].imagePath = "";
				$scope.centreData[i].imagePath = $scope.centreData[i].imagePath.split(",");
			}
			else{
				$scope.centreData[i].imagePath = $scope.centreData[i].imagePath.split(",");
			}
		}
		$scope.paginate($scope.centreData.length);
		$scope.dataLoaded = true;
	});
	
	/*$scope.getMoreCentreData = function () {
		if($scope.centreDataStore != undefined){		
	    $scope.centreData = $scope.centreDataStore.slice(0, $scope.centreData.length + 6);		
		}
	}*/
	
	$scope.removeActivityFilter = function(){
		$scope.activity.id = "";
		centreService.getSummaryCentreDetailsActive().then(function(data){
			$scope.centreData  = data;
			for(var i=0;i<$scope.centreData.length;i++){
				$scope.centreData[i].imagePath = $scope.centreData[i].imagePath.split(",");			
			}			
		});
		$scope.removeFilter = false;
	}
	
		$scope.paginate = function(size){		
			$scope.totalItems = size,
			$scope.entryLimit = 20, // items per page
			$scope.noOfPages = Math.ceil($scope.totalItems / $scope.entryLimit), $scope.maxSize = 25;
			// $watch search to update pagination
			$scope.$watch('search', function(newVal, oldVal) {		
			$scope.filtered = filterFilter($scope.centreData,newVal);			
			$scope.entryLimit = 20;
			$scope.totalItems = $scope.filtered.length;
			$scope.noOfPages = Math.ceil($scope.totalItems / $scope.entryLimit);
			$scope.currentPage = 1;
			}, 	true);
		};
	

	$scope.filterByActivity = function(activityId){
		centreService.activityFilteredCentre(activityId).then(function(data){
			$scope.centreData  = data;
			for(var i=0;i<$scope.centreData.length;i++){
				$scope.centreData[i].imagePath = $scope.centreData[i].imagePath.split(",");	
			}
			$scope.paginate($scope.centreData.length);
			toaster.pop('success', "Filtered results with Activity Name", '', 4000, 'trustedHtml');
			$window.scrollTo(0, 0);
			$scope.removeFilter = true;
		});
	}
	
	$scope.filterByAge = function(age){
		centreService.filterByAge(age).then(function(data){
			$scope.centreData  = data;
			for(var i=0;i<$scope.centreData.length;i++){
				$scope.centreData[i].imagePath = $scope.centreData[i].imagePath.split(",");	
			}
			$scope.paginate($scope.centreData.length);
			$window.scrollTo(0, 0);
			toaster.pop('success', "Filtered results with Age", '', 4000, 'trustedHtml');
			$scope.removeFilter = true;
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
});

centre.directive("centreFrame", function() {	
	return {
		restrict : 'AE',
		templateUrl: function(ele, attrs) {
	          return attrs.templatePath;
	      },
		transclude: true		
	};
});

/*centre.filter('removeSpacesThenLowercase', function () {
    return function (text) {
		var str = text.replace(/\s+/g, '-');
		return str.toLowerCase();
    };
});*/