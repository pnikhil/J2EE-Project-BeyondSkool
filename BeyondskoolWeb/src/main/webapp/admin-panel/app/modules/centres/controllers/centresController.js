/**
 * @ngdoc controller
 * @name activitiesController
 * 
 * @description 
 */

var centres = angular.module('beyondSkool');

centres.factory('Model', function($resource) {
	return $resource('centres');
});

centres.controller('centresController',function($scope,$window,$location, $rootScope,centresService,usersService,citiesService,areasService,toaster,$state,Upload,$timeout,filterFilter) {
	$scope.fileNames = [];
	$scope.fileArray = [];
	$scope.centreState = "display";
	$scope.user_email_exists = false;
	$scope.showAreas = false;
	$scope.centreDetails = centresService.getCentreDetails().then(function(data){
		$scope.centreData  = data;
		console.log($scope.centreData);
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
		$scope.getCityNames();
	});
	
	$scope.checkEmailExist = function(){
		if($scope.emailId === "" || $scope.emailId === undefined){
			$scope.email_exists = false;
		}
		centresService.checkCentreAccountExists($scope.emailId).then(function(data){
			if(data === true){						
				$scope.email_exists = true;
			}else{
				$scope.email_exists = false;
			}
		});
	}
	
	$scope.refreshCentreDetails = function(){
		$scope.centreDetails = centresService.getCentreDetails().then(function(data){			
				$scope.centreData  = data;
				for(var i=0;i<$scope.centreData.length;i++){
					if($scope.centreData[i].imagePath == null){
						$scope.centreData[i].imagePath = "";
						$scope.centreData[i].imagePath = $scope.centreData[i].imagePath.split(",");
					}
					else{
						$scope.centreData[i].imagePath = $scope.centreData[i].imagePath.split(",");
					}
					console.log($scope.centreData[i].imagePath);
				}
				$scope.paginate($scope.centreData.length);
			});
	}

	$scope.getCityNames = function(){
			centresService.retrieveCityNames().then(function(data){
			$scope.cityNames  = data;
			console.log($scope.cityNames);
			});
	}
	
	$scope.view = function(centre){
		$window.scrollTo(0, 0);
		$scope.centreId = centre.id;
		$scope.centreEmail = centre.mailId;
		$scope.centreName = centre.centreName;
		$scope.centreState = "view";
		$scope.centreInfo =  centre;
		$scope.getActivityDetails(centre);
	}
	
	$scope.updateCentreButton = function(centre){
		$scope.centreState = "update";
		$scope.centreInfo =  centre;
		
		$window.scrollTo(0, 0);
		$scope.myCentreState = 'editCentre';
		$scope.centreName = $scope.centreInfo.centreName;
		$scope.ownerName = $scope.centreInfo.ownerName;
		$scope.emailId = $scope.centreInfo.mailId;
		$scope.contactNo = $scope.centreInfo.contactNo;
		$scope.description = $scope.centreInfo.description;
		$scope.city = $scope.centreInfo.cityId;
		$scope.area = $scope.centreInfo.areaId;
		$scope.address = $scope.centreInfo.address;
		
		$scope.cityNames = centresService.retrieveCityNames().then(function(data){
			$scope.cityData  = data;
			
			$scope.areaNames = centresService.retrieveAreaNamesForCity($scope.city).then(function(data){
				$scope.areaData  = data;
			});
		});
		
	}
	
	$scope.getAreaNames = function(cityId){
		console.log(cityId);
			$scope.areaNames = centresService.retrieveAreaNamesForCity(cityId).then(function(data){
				$scope.areaData  = data;	
				console.log($scope.areaData);
				$scope.showAreas = true;
			});
	}
		$scope.checkEmailExist = function(){
			if($scope.emailId === "" || $scope.emailId === undefined){
				$scope.email_exists = false;
			}
			centresService.checkCentreAccountExists($scope.emailId).then(function(data){
				if(data === true){						
					$scope.email_exists = true;
				}else{
					$scope.email_exists = false;
				}
			});
		}		
		$scope.updateCentreDetails = function(){
			console.log($scope.centreInfo.id);
			console.log($scope.ownerName);
			console.log($scope.contactNo);
			console.log($scope.description);
			console.log($scope.area);
			console.log($scope.city);
			console.log($scope.emailId);
			console.log($scope.address);
			centresService.updateCentreDetailsByAdmin($scope.centreInfo.id,$scope.ownerName,$scope.contactNo,$scope.description,$scope.address,$scope.area,$scope.city,$scope.emailId).then(function(isSuccess){
				if(isSuccess === true){
						toaster.pop('success', "Centre Updated successfully", '', 5000, 'trustedHtml');
						$scope.refreshCentreDetails();
						$scope.centreState = "view";
						$window.scrollTo(0, 0);
				}
			});	
		}
	
	
	$scope.getActivityDetails = function(centre){		
		console.log($scope.centreId);
		centresService.getActivityDetails($scope.centreId,centre.centreName).then(function(data){
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
	
	$scope.getActivityDetailsAfterDelete = function(){		
		centresService.getActivityDetails($scope.centreId,$scope.centreName).then(function(data){				
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
	
	$scope.deleteActivityFromCentre = function(centreActivityId){
		console.log($scope.centreId);
	centresService.deleteActivityFromCentre(centreActivityId).then(function(data){
			if(data === true){						
				toaster.pop('success', "Activity Class Deleted Successfully", '', 4000, 'trustedHtml');
				$scope.getActivityDetailsAfterDelete();
				$window.scrollTo(0, 0);
				$scope.centreState = "view";
			}
	});
	}
	
	 $scope.refreshCentreAfterImageDelete = function(){
		   $scope.centreDetails = centresService.getFullCentreDetails($scope.centreEmail).then(function(data){
				$scope.centreInfo  = data;
				if($scope.centreInfo.imagePath !== undefined && $scope.centreInfo.imagePath !== null){
					$scope.centreInfo.imagePath = $scope.centreInfo.imagePath.split(",");					
				}
			});
		}
	
	$scope.updateActivityButton = function(activity){
		console.log(activity);
		$scope.centreActivityId = activity.centreActivityId;
		$scope.getActivity = activity.activityId;
		console.log(activity.inTime);
		$scope.inTime = new Date(activity.inTime);
		$scope.outTime = new Date(activity.outTime);
		$scope.day = activity.day;
		$scope.fromAge = activity.fromAge.toString();
		$scope.toAge = activity.toAge.toString();
		$scope.totalSlots = activity.totalSlots;
		$scope.duration = activity.duration;
		$scope.classDescription = activity.classDescription;
		$scope.amount = activity.amount;
		
		$scope.activityDetails = centresService.getActivityNames().then(function(data){
			$scope.activityData  = data;
		});
	}
	
	$scope.updateActivity = function(getActivity,inTime,outTime,day,fromAge,toAge,totalSlots,duration,classDescription,amount,startDate,endDate){
		
		 startDateString = startDate.toDateString();
		// startDateString = startDateString.substring(0,15);
		 
		 endDateString = endDate.toDateString();
		// endDateString = endDateString.substring(0,15);
		
		centresService.updateActivity($scope.centreActivityId,getActivity,inTime.toLocaleTimeString(),outTime.toLocaleTimeString(),day.toString(),fromAge,toAge,totalSlots,duration,classDescription,amount,startDateString,endDateString).then(function(data){
			$scope.getActivityDetailsAfterDelete();
			$window.scrollTo(0, 0);
			$scope.centreState = "view";
			toaster.pop('success', "Activity Updated Successfully", '', 4000, 'trustedHtml');
		});
	}
	
	/**
	 * @ngdoc function
	 * @name paginate
	 * @description This method is used for invoking pagination
	 *              section on tab change.
	 */
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
		}, true);
	};
	
	$scope.deleteCentre = function(centreId){
		centresService.deleteCentre(centreId).then(function(data){	
			if(data === true){						
				toaster.pop('success', "Centre Deleted Successfully", '', 40000, 'trustedHtml');
				$scope.refreshCentreDetails();
			}
	});
	}
	
	
	
	$scope.activityDetails = usersService.getActivityNames().then(function(data){
			$scope.activityData  = data;
	});	
	
	$scope.goBack = function(){
		$scope.centreState = "display";
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
	   
	   $scope.createCentreButton = function(){
			$scope.centreState = "create";
			$scope.ownerName = "";
			$scope.emailId = "";
			$scope.password = "";
			$scope.centreName = "";
			$scope.contactNo = "";
			$scope.fileNames = "";
			$scope.city = "";
			$scope.area = "";
			$scope.address = "";
			$scope.status = "";
			$scope.description = "";
			centreObject = {};
			$scope.activities = [{id: 'activityBlock1'}];
			$scope.imageId = Math.round((Math.random() * 1000) * 10);
		}
		
		$scope.deleteImage = function(centreName,imageName,centreId,imageId){
			console.log(imageId);
			centresService.deleteImage(centreName,imageName,centreId,imageId).then(function(isSuccess){
				if(isSuccess === true){
					toaster.pop('success', "Image Deleted Successfully", '', 3000, 'trustedHtml');
					$scope.refreshCentreAfterImageDelete();
					$scope.centreState = "view";
			}
			});

		}
	   
	   $scope.createCentre = function(){
		   var centreObject = new Object();
		   centreObject.ownerName = $scope.ownerName;
		   centreObject.emailId = $scope.emailId;
		   centreObject.password = $scope.password;
		   centreObject.centreName = $scope.centreName;
		   centreObject.description = $scope.description;
		   centreObject.contactNo = $scope.contactNo;
		   centreObject.fileNames = $scope.fileNames.toString();
		   centreObject.city = parseInt($scope.city);
		   centreObject.area = parseInt($scope.area);
		   centreObject.address = $scope.address;
		   centreObject.status = $scope.status;
		   centreObject.imageId = $scope.imageId;
		   for(var i = 0; i < $scope.activities.length;i++){
			   $scope.activities[i].startDateString = $scope.activities[i].startDate.toDateString();
			   /*$scope.activities[i].startDateString = $scope.activities[i].startDateString.substring(0,15);*/
			   $scope.activities[i].endDateString = $scope.activities[i].endDate.toDateString();
			   $scope.activities[i].day = $scope.activities[i].day.toString();
			   /*$scope.activities[i].endDateString = $scope.activities[i].endDateString.substring(0,15);*/
		   }	  
		   console.log($scope.activities);
		   centresService.createCentre(centreObject).then(function(centreId){
				if(centreId !== 0){
					for(var i = 0 ; i < $scope.activities.length; i++){
						$scope.activities[i].centreId = centreId;
					}
					centresService.saveCentreActivities($scope.activities).then(function(isSuccess){
						if(isSuccess === true){
								toaster.pop('success', "Centre Added Successfully with Activity Details", '', 40000, 'trustedHtml');
								$scope.refreshCentreDetails();
								$scope.centreState = "display";
						}
					});
				}
			});
		   //$scope.activities;
		  }
	   
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
				$scope.fileArray.push($scope.centreName+"_"+$scope.imageId + "~" + file.name);
				file = Upload.rename(file,$scope.centreName+"_"+$scope.imageId + "~" + file.name);
				file.upload = Upload.upload({
					url : '../rest/centres/file',
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
	
});

centres.directive("centreFrame", function() {	
	return {
		restrict : 'AE',
		templateUrl: function(ele, attrs) {
	          return attrs.templatePath;
	      },
		transclude: true		
	};
});
