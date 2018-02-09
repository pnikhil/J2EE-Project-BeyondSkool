/**
 * @ngdoc controller
 * @name activitiesController
 * 
 * @description 
 */

var myCentre = angular.module('beyondSkool');

myCentre.factory('Model', function($resource) {
	return $resource('myCentre');
});

myCentre.controller('myCentreController',function($scope,$window,$location, $rootScope,myCentreService,toaster,$state,$timeout,filterFilter,$localStorage,Upload) {
	$scope.centreEmail = $localStorage.currentUser.username;
	
	$scope.myCentreState = "display";
	$window.scrollTo(0, 0);
	$scope.fileNames = [];
	$scope.fileArray = [];
	$scope.uploadBoxonEdit = false;
	$scope.showCentre = false;
	$scope.hideThumbnails = false;
	$scope.inTimeInvalid = false;
	$scope.outTimeInvalid = false;
	$scope.startDateUndefined= false;
	$scope.endDateUndefined = false;
	/*$scope.$on('$locationChangeStart', function(event, next, current){            
	    $scope.myCentreState = "display";		
        event.preventDefault();
        $state.go("myCentre");
	});*/
	
	$scope.$on('$routeChangeStart', function (scope, next, current) {
        console.log(next.$$route.controller);
    });
	
	$scope.editUploadNew = function(choice){
		if(choice == 'yes'){
			$scope.uploadBoxonEdit = true;
			$scope.wannaUpload = true;
			$scope.imageId = $scope.centreData.imageDirId;
		}
		else{
			$scope.wannaUpload = true;
		}
	}
	$scope.createButton = function(){
		$window.scrollTo(0, 0);
		$scope.myCentreState = 'create';
		$scope.centreName = "";
		$scope.contactNo = "";
		$scope.fileNames = "";
		$scope.city = "";
		$scope.area = "";
		$scope.address = "";
		$scope.status = "";
		$scope.description = "";
		$scope.imageId = $scope.centreData.imageDirId;
		
		centreObject = {};
		$scope.activities = [{id: 'activityBlock1'}];
		$scope.cityNames = myCentreService.retrieveCityNames().then(function(data){
			$scope.cityData  = data;
			console.log($scope.cityData);
		});
		
		$scope.getAreaNames = function(cityId){
			if(cityId.length>0){
				$scope.areaNames = myCentreService.retrieveAreaNamesForCity(cityId).then(function(data){
					$scope.areaData  = data;		
				});
			}
			else 
				console.log("no city selected");
		}
		
		$scope.activityDetails = myCentreService.getActivityNames().then(function(data){
			$scope.activityData  = data;
			console.log($scope.activityData);
		});
		
	}
	
	$scope.centreImagesButton = function(){
		$window.scrollTo(0, 0);
		$scope.myCentreState = "images";
		$scope.centreName = $scope.centreData.centreName;
	}
	
	$scope.bookingsButton = function(){
		$window.scrollTo(0, 0);
		$scope.myCentreState = "bookings";
		$scope.bookings = myCentreService.retrievebookings($localStorage.currentUser.username).then(function(data){
			$scope.bookingData  = data;
			console.log($scope.bookingData);		
		});
	}
	
	$scope.updateActivityButton = function(activity){
		console.log(activity);
		$scope.centreActivityId = activity.centreActivityId;
		$scope.getActivity = activity.activityId;
		console.log(activity.inTime);
		$scope.inTime = new Date(activity.inTime);
		$scope.outTime = new Date(activity.outTime);
		//$scope.day = activity.day;
		$scope.fromAge = activity.fromAge.toString();
		$scope.toAge = activity.toAge.toString();
		$scope.totalSlots = activity.totalSlots;
		$scope.duration = activity.duration;
		$scope.classDescription = activity.classDescription;
		$scope.amount = activity.amount;
		
		$scope.activityDetails = myCentreService.getActivityNames().then(function(data){
			$scope.activityData  = data;
		});
	}
	
	$scope.updateActivity = function(getActivity,inTime,outTime,day,fromAge,toAge,totalSlots,duration,classDescription,amount,startDate,endDate){
		console.log(inTime);
		console.log(outTime);
		if(inTime=='Invalid Date'){
			$scope.inTimeInvalid = true;
		}
		else{
			$scope.inTimeInvalid = false;
		}
		if(outTime == 'Invalid Date'){
			
			$scope.outTimeInvalid = true;
		}
		else{
			$scope.outTimeInvalid = false;
		}
		if(startDate == undefined){
			
			$scope.startDateUndefined = true;
		}
		else{
			$scope.startDateUndefined = false;
		}
		if(endDate == undefined){			
			$scope.endDateUndefined = true;
		}

		else{
			$scope.inTimeInvalid = false;
			$scope.outTimeInvalid = false;
			$scope.startDateUndefined= false;
			$scope.endDateUndefined = false;
			
		startDateString = startDate.toDateString();
		endDateString = endDate.toDateString();
		newDay = day.toString();
		myCentreService.updateActivity($scope.centreActivityId,getActivity,inTime.toLocaleTimeString(),outTime.toLocaleTimeString(),newDay,fromAge,toAge,totalSlots,duration,classDescription,amount,startDateString,endDateString).then(function(data){
			$window.scrollTo(0, 0);
			$scope.getActivityDetails();
			$scope.myCentreState = 'activities';
			toaster.pop('success', "Activity Updated Successfully", '', 4000, 'trustedHtml');
		});
		}
	}
	
	$scope.changeStatus = function(choice,bookingId){
		console.log(choice);
		console.log(bookingId);		
		myCentreService.changeBookingStatus(choice,bookingId).then(function(data){	
			if(data === true){						
				toaster.pop('success', "Booking Status changed successfully", '', 4000, 'trustedHtml');
				$scope.bookings = myCentreService.retrievebookings($localStorage.currentUser.username).then(function(data){
					$scope.bookingData  = data;
					console.log($scope.bookingData);		
				});
			}
	});
	}
	
	$scope.addActivityButton = function(){
		$scope.myCentreState = 'addActivity';
		$window.scrollTo(0, 0);
		$scope.activities = [{id: 'activityBlock1'}];
		$scope.activityDetails = myCentreService.getActivityNames().then(function(data){
			$scope.activityData  = data;
			console.log($scope.activityData);
		});
	}
	
	$scope.deleteActivityFromCentre = function(centreActivityId){
		myCentreService.deleteActivityFromCentre(centreActivityId).then(function(data){	
			if(data === true){
				toaster.pop('success', "Activity Class Deleted Successfully", '', 4000, 'trustedHtml');
				$scope.getActivityDetails();
				$window.scrollTo(0, 0);
				$scope.myCentreState = 'activities';				
			}
	});		
	}
	
	$scope.editCentreButton = function(){
		$window.scrollTo(0, 0);
		$scope.myCentreState = 'editCentre';
		$scope.wannaUpload = false;
		$scope.uploadBoxonEdit = false;
		$scope.centreName = $scope.centreData.centreName;
		$scope.ownerName = $scope.centreData.ownerName;
		$scope.emailId = $scope.centreData.mailId;
		$scope.contactNo = $scope.centreData.contactNo;
		$scope.description = $scope.centreData.description;
		$scope.city = $scope.centreData.cityName;
		$scope.area = $scope.centreData.areaName;
		$scope.address = $scope.centreData.address;
		
		$scope.cityNames = myCentreService.retrieveCityNames().then(function(data){
			$scope.cityData  = data;
			console.log($scope.cityData);
		});
		
		$scope.getAreaNames = function(cityId){
			if(cityId.length>0){
				$scope.areaNames = myCentreService.retrieveAreaNamesForCity(cityId).then(function(data){
					$scope.areaData  = data;		
				});
			}
			else 
				console.log("no city selected");
		}
		
		$scope.activityDetails = myCentreService.getActivityNames().then(function(data){
			$scope.activityData  = data;
			console.log(activityData);
		});
		
	}
	
	$scope.updateCentreDetails = function(){
		console.log($scope.centreData.id);
		console.log($scope.ownerName);
		console.log($scope.contactNo);
		console.log($scope.description);
		console.log($scope.address);
		/*if($scope.uploadBoxonEdit === false)
			$scope.imageFiles = $scope.centreData.imagePath;
		else
			$scope.imageFiles = $scope.fileNames.toString();
		console.log($scope.imageFiles);*/
		
		myCentreService.updateCentreDetails($scope.centreData.id,$scope.ownerName,$scope.contactNo,$scope.description,$scope.address/*,$scope.imageFiles*/).then(function(isSuccess){
			if(isSuccess === true){
					toaster.pop('success', "Centre Updated successfully", '', 5000, 'trustedHtml');
					$scope.refreshMyCentreDetails();
					$scope.myCentreState = "display";
					$window.scrollTo(0, 0);
			}
		});		
	}
	
	$scope.getActivityDetails = function(){
		$scope.myCentreState = "activities";
		myCentreService.getActivityDetails($scope.centreData.id,$scope.centreData.centreName).then(function(data){				
			$scope.centreActivities = data;	
			console.log($scope.centreActivities);
			for(var i = 0;i<$scope.centreActivities.length;i++){
				$scope.centreActivities[i].startDate = $scope.centreActivities[i].startDate.substring(4);
				$scope.centreActivities[i].endDate = $scope.centreActivities[i].endDate.substring(4);				
				$scope.centreActivities[i].day = $scope.centreActivities[i].day.split(",");
				$scope.centreActivities[i].dayData = "";
				for(var j = 0;j<$scope.centreActivities[i].day.length;j++){
					if($scope.centreActivities[i].day.length != 1){
						$scope.centreActivities[i].dayData += $scope.centreActivities[i].day[j].substring(0,3);
					}
					else{
						$scope.centreActivities[i].dayData += $scope.centreActivities[i].day[j];
					}
					
					if(j != $scope.centreActivities[i].day.length - 1){
						$scope.centreActivities[i].dayData += ",";
					}
				}
			}
		});	
	}
	
	$scope.updateCentreImages = function(centreId){
		$scope.imageFiles = $scope.fileNames.toString();		
		console.log(centreId);
		console.log($scope.imageFiles);
		myCentreService.updateCentreImages(centreId, $scope.imageFiles).then(function(isSuccess){
			if(isSuccess === true){
					toaster.pop('success', "Centre Images updated Successfully", '', 3000, 'trustedHtml');
					$scope.refreshMyCentreDetails();
					$scope.myCentreState = "images";					
			}
		});
		$scope.hideThumbnails = true;
	}
	
	$scope.addActivity = function(){		
		console.log($scope.activities);
		for(var i = 0 ; i < $scope.activities.length; i++){
			$scope.activities[i].centreId = $scope.centreData.id;
			 $scope.activities[i].startDateString = $scope.activities[i].startDate.toDateString();
			 $scope.activities[i].endDateString = $scope.activities[i].endDate.toDateString();
			 $scope.activities[i].day = $scope.activities[i].day.toString();
		}	 
		
		myCentreService.saveCentreActivities($scope.activities).then(function(isSuccess){
			if(isSuccess === true){
					toaster.pop('success', "Activities added successfully", '', 3000, 'trustedHtml');
					$scope.refreshMyCentreDetails();
					$scope.myCentreState = "display";
					$window.scrollTo(0, 0);
			}
		});
	}
	
	$scope.goBack = function(){
		$scope.myCentreState = "display";
	}
	
	$scope.createCentre = function(){
		   var centreObject = new Object();
		   centreObject.centreEmail = $scope.centreEmail;
		   centreObject.centreName = $scope.centreName;
		   centreObject.description = $scope.description;
		   centreObject.contactNo = $scope.contactNo;
		   centreObject.fileNames = $scope.fileNames.toString();
		   centreObject.city = parseInt($scope.city);
		   centreObject.area = parseInt($scope.area);
		   centreObject.address = $scope.address;
		   centreObject.imageId = $scope.imageId;
		   
		   console.log($scope.activities);
		   if($scope.userForm.$valid){
		   myCentreService.createCentreForCurrentUser(centreObject).then(function(centreId){
				if(centreId !== 0){
					for(var i = 0 ; i < $scope.activities.length; i++){
						$scope.activities[i].centreId = centreId;
						$scope.activities[i].startDateString = $scope.activities[i].startDate.toDateString();
						$scope.activities[i].endDateString = $scope.activities[i].endDate.toDateString();
						$scope.activities[i].day = $scope.activities[i].day.toString();
					}
					myCentreService.saveCentreActivities($scope.activities).then(function(isSuccess){
						if(isSuccess === true){
								toaster.pop('success', "Centre Added successfully with Activity Details", '', 40000, 'trustedHtml');
								$scope.refreshMyCentreDetails();
								$scope.myCentreState = "display";
								$window.scrollTo(0, 0);
						}
					});
				}
			});
		}
		   //$scope.activities;
		  }
	
	
	
	$scope.activities = [{id: 'activityBlock1'}];
	   
	   $scope.addNewActivity = function() {
	     var newActivityNo = $scope.activities.length+1;
	     $scope.activities.push({'id' : 'activityBlock' + newActivityNo});
	   };
	   
	   $scope.removeNewActivity = function() {
	     var newActivityNo = $scope.activities.length-1;
	     if (newActivityNo !== 0 ) {
	      $scope.activities.pop();
	     }
	   };
	   
	   $scope.showAddChoice = function(activity) {
	     return activity.id === $scope.activities[$scope.activities.length-1].id;
	   };
	   
	   $scope.storeFileNames = function(fileNames){
			  var tempFileNameArray = fileNames.split(",");
			  for(var i = 0; i < tempFileNameArray.length;i++){
				  if($scope.fileNames.indexOf(tempFileNameArray[i]) === -1){
					  $scope.fileNames.push(tempFileNameArray[i]);
					}
			  }		  
			  console.log($scope.fileNames);
		   }
	   $scope.uploadFiles = function(files, errFiles) {		 
			$scope.fileNames = new Array();
			$scope.files = files;
			$scope.errFiles = errFiles;			
			angular.forEach(files, function(file) {
				$scope.fileArray.push($scope.centreName + "_" + $scope.imageId + "~" + file.name);
				file = Upload.rename(file,$scope.centreName + "_" + $scope.imageId + "~" + file.name);
				console.log($scope.fileArray);
				file.upload = Upload.upload({
					url : 'rest/centres/file',
					data : {
						file : file						
					}
				});
				file.upload.then(function(response) {
						console.log(response);
						$scope.storeFileNames(response.data);
						file.result = response;
				}, function(response) {
					console.log(response);					
					if (response.status > 0)
						$scope.errorMsg = response.status + ': ' + response.data;
				}, function(evt) {
					file.progress = Math.min(100, parseInt(100.0 * evt.loaded
							/ evt.total));
				});
			});
		}
	   $scope.refreshMyCentreDetails = function(){
		   $scope.centreDetails = myCentreService.getFullCentreDetails($scope.centreEmail).then(function(data){
				$scope.centreData  = data;
				if($scope.centreData.imagePath !== undefined && $scope.centreData.imagePath !== null){
					$scope.imageArray = $scope.centreData.imagePath.split(",")			
					console.log($scope.imageArray);
				}	
				console.log($scope.centreData);
			});
			}
	
	$scope.centreDetails = myCentreService.getFullCentreDetails($scope.centreEmail).then(function(data){
		$scope.centreData  = data;
		if($scope.centreData.imagePath !== undefined && $scope.centreData.imagePath !== null){
			$scope.imageArray = $scope.centreData.imagePath.split(",")			
			console.log($scope.imageArray);
		}	
		console.log($scope.centreData);
		$scope.showCentre = true;
	});
	
	$scope.deleteImage = function(centreName,imageName,centreId){		
		myCentreService.deleteImage(centreName,imageName,centreId,$scope.centreData.imageDirId).then(function(isSuccess){
			if(isSuccess === true){
				toaster.pop('success', "Image Deleted Successfully", '', 3000, 'trustedHtml');
				$scope.refreshMyCentreDetails();
				$scope.myCentreState = "images";
		}
		});

	}
});

myCentre.directive("myCentreFrame", function() {	
	return {
		restrict : 'AE',
		templateUrl: function(ele, attrs) {
	          return attrs.templatePath;
	      },
		transclude: true		
	};
});