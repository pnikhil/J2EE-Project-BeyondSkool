/**
 * @ngdoc controller
 * @name activitiesController
 * 
 * @description 
 */

var pendingCentres = angular.module('beyondSkool');

pendingCentres.factory('Model', function($resource) {
	return $resource('pendingCentres');
});

pendingCentres.controller('pendingCentresController',function($scope,$window,$location, $rootScope,pendingCentresService,usersService,citiesService,areasService,toaster,$state,Upload,$timeout,filterFilter) {
	$scope.fileNames = [];
	$scope.fileArray = [];
	$scope.centreState = "display";
	$scope.user_email_exists = false;

	$scope.centreDetails = pendingCentresService.getPendingCentreDetails().then(function(data){
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
			console.log($scope.centreData[i].imagePath);
		}		
		$scope.paginate($scope.centreData.length);
	});
	
		$scope.checkEmailExist = function(){
		if($scope.emailId === "" || $scope.emailId === undefined){
			$scope.email_exists = false;
		}
		pendingCentresService.checkCentreAccountExists($scope.emailId).then(function(data){
			if(data === true){						
				$scope.email_exists = true;
			}else{
				$scope.email_exists = false;
			}
		});
	}
	
	$scope.approveCentre = function(centreId,centreEmail){
		pendingCentresService.approveCentre(centreId,centreEmail).then(function(data){	
			if(data === true){						
				toaster.pop('success', "Centre Approved Successfully", '', 40000, 'trustedHtml');
				$scope.refreshCentreDetails();
			}
	});
	}
	
	$scope.refreshCentreDetails = function(){
		$scope.centreDetails = pendingCentresService.getPendingCentreDetails().then(function(data){			
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
	
	$scope.view = function(centre){
		console.log(centre);
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
		
		$scope.cityNames = pendingCentresService.retrieveCityNames().then(function(data){
			$scope.cityData  = data;
			console.log($scope.cityData);
			$scope.areaNames = pendingCentresService.retrieveAreaNamesForCity($scope.city).then(function(data){
				$scope.areaData  = data;		
			});
		});
		
		$scope.getAreaNames = function(cityId){
			console.log(cityId);
				$scope.areaNames = pendingCentresService.retrieveAreaNamesForCity(cityId).then(function(data){
					$scope.areaData  = data;		
				});
		}
	}
		$scope.checkEmailExist = function(){
			if($scope.emailId === "" || $scope.emailId === undefined){
				$scope.email_exists = false;
			}
			pendingCentresService.checkCentreAccountExists($scope.emailId).then(function(data){
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
		
			
			pendingCentresService.updateCentreDetailsByAdmin($scope.centreInfo.id,$scope.ownerName,$scope.contactNo,$scope.description,$scope.address,$scope.area,$scope.city,$scope.emailId).then(function(isSuccess){
				if(isSuccess === true){
						toaster.pop('success', "Centre Updated successfully", '', 5000, 'trustedHtml');
						$scope.refreshCentreDetails();
						$scope.centreState = "display";
						$window.scrollTo(0, 0);
				}
			});	
		}
	
	
	$scope.getActivityDetails = function(centre){		
		pendingCentresService.getActivityDetails(centre.id,centre.centreName).then(function(data){				
			$scope.centreActivities = data;
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
	
	$scope.cityNames = citiesService.retrieveCityNames().then(function(data){
		$scope.cityData  = data;
		console.log($scope.cityData);
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
	   
	   $scope.storeFileNames = function(fileNames){
		  var tempFileNameArray = fileNames.split(",");
		  for(var i = 0; i < tempFileNameArray.length;i++){
			  if($scope.fileNames.indexOf(tempFileNameArray[i]) === -1){
				  $scope.fileNames.push(tempFileNameArray[i]);
				}
		  }		  
		  console.log($scope.fileNames);
	   }	   

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

		   console.log($scope.activities);
		   pendingCentresService.createCentre(centreObject).then(function(centreId){
				if(centreId !== 0){
					for(var i = 0 ; i < $scope.activities.length; i++){
						$scope.activities[i].centreId = centreId;
					}
					pendingCentresService.saveCentreActivities($scope.activities).then(function(isSuccess){
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
	   
	   $scope.uploadFiles = function(files, errFiles) {		 
			$scope.fileNames = new Array();
			$scope.files = files;
			$scope.errFiles = errFiles;

			angular.forEach(files, function(file) {
				$scope.fileArray.push($scope.centreName + "~" + file.name);
				file = Upload.rename(file,$scope.centreName + "~" + file.name);
				console.log($scope.fileArray);
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

pendingCentres.directive("pendingCentreFrame", function() {	
	return {
		restrict : 'AE',
		templateUrl: function(ele, attrs) {
	          return attrs.templatePath;
	      },
		transclude: true		
	};
});
