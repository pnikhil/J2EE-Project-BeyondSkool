/**
 * @ngdoc controller
 * @name activitiesController
 * 
 * @description 
 */

var parentDashboard = angular.module('beyondSkool');

parentDashboard.filter('range', function() {
	  return function(input, min, max) {
		    min = parseInt(min); //Make string input int
		    max = parseInt(max);
		    for (var i=min; i<max; i++)
		      input.push(i);
		    return input;
		  };
		});

parentDashboard.controller('parentDashboardController',function($scope,$window,$location, $rootScope,parentDashboardService,toaster,$state,$timeout,filterFilter,signupService,$localStorage,Upload,$cookieStore) {
	$window.scrollTo(0, 0);
	$scope.parentEmail = $localStorage.currentUser.username;
	$scope.parentState = "display";
	$scope.activity_list = [];
	$scope.standard_empty = false;
	$scope.gender_empty = false;
	$scope.form_incomplete = false;
	$scope.mother_mobile_empty = false;
	$scope.timings_empty = false;
	$scope.preference_empty = false;
	$scope.area_empty = false;
	$scope.city_empty = false;
	$scope.activities_empty = false;
	$scope.showDashboard = false;
	
	$scope.parentDetails = parentDashboardService.getFullParentDetails($scope.parentEmail).then(function(data){
		$scope.parentData  = data;	
		$scope.showDashboard = true;
		console.log($scope.parentData);
		$cookieStore.put('parentId',$scope.parentData.userId);
	});
	
	$scope.paymentButton = function(){
		$scope.parentState = "payment";
	}
	
	$scope.bookingsButton = function(){
		$scope.parentState = "bookings";
	}
	
	$scope.payments = parentDashboardService.retrievePayments($localStorage.currentUser.username).then(function(data){
		$scope.paymentData  = data;
		console.log($scope.paymentData);		
	});
	
	$scope.wallet = parentDashboardService.retrieveWalletInfo($localStorage.currentUser.username).then(function(data){
		$scope.walletData  = data;
		console.log($scope.walletData);		
	});
	
	$scope.bookings = parentDashboardService.retrievebookings($localStorage.currentUser.username).then(function(data){
		$scope.bookingData  = data;
		console.log($scope.bookingData);		
	});	
	
	$scope.refreshParentDetails = function(){
		$scope.parentDetails = parentDashboardService.getFullParentDetails($scope.parentEmail).then(function(data){
			$scope.parentData  = data;			
		});
	}
	
	$scope.updateButton = function(){
		$scope.parentState = 'update';	
		$scope.userId = $scope.parentData.userId;
		$scope.parentName = $scope.parentData.parentName;
		$scope.childName = $scope.parentData.childName;
		$scope.age = $scope.parentData.age;
		$scope.gender = $scope.parentData.gender;
		$scope.email = $scope.parentData.email;
		$scope.fatherMobile = $scope.parentData.fatherMobileNo;
		$scope.motherMobile = $scope.parentData.motherMobileNo;
		$scope.address = $scope.parentData.address;
		$scope.standard = $scope.parentData.standard;
		$scope.school = $scope.parentData.school;
		$scope.timings = $scope.parentData.applicableTimings;
		$scope.preference = $scope.parentData.preference;
		
	}
	
	$scope.goBack = function(){
		$scope.parentState = "display";
	}
	
	$scope.activityNames = function(){
		$scope.activityDetails = parentDashboardService.getActivityNames().then(function(data){
			$scope.activityData  = data;
			console.log($scope.activityData);
		});
	}


	$scope.addActivityToList = function (activity) {
		console.log(activity);
		var index = $scope.activity_list.indexOf(activity.activityName);
		if(index == -1 && activity.selected){
			$scope.activity_list.push(activity.activityName);
		} else if (!activity.selected && index != -1){
			$scope.activity_list.splice(index, 1);
		}
		console.log($scope.activity_list);
	};
	
	
	$scope.updateProfile = function(){
		$scope.standard_empty = false;
		$scope.gender_empty = false;
		$scope.form_incomplete = false;
		$scope.timings_empty = false;
		$scope.preference_empty = false;
		$scope.area_empty = false;
		$scope.city_empty = false;
		$scope.mother_mobile_empty = false;
		$scope.timings_empty = false;
		$scope.preference_empty = false;
		$scope.activities_empty = false;
		var userObject = new Object();
		userObject.userId = $scope.userId;
		userObject.parentName = $scope.parentName;
		userObject.childName = $scope.childName;
		userObject.age = $scope.age;
		userObject.gender = $scope.gender;
		userObject.email = $scope.email;
		userObject.fatherMobile = $scope.fatherMobile;
		userObject.motherMobile = $scope.motherMobile;
		userObject.address = $scope.address;
		userObject.standard = parseInt($scope.standard);
		userObject.school = $scope.school;
		userObject.city = $scope.city;
		userObject.area = $scope.area;
		userObject.timings = $scope.timings;
		userObject.preference = $scope.preference;
		userObject.activity_list = $scope.activity_list.toString();
		//console.log(userObject);
		console.log(userObject.standard);
		if($scope.standard === 0 || $scope.standard.length == 0){
			$scope.standard_empty = true;
			$scope.form_incomplete = true;
		}
		else if($scope.gender === null || $scope.gender === undefined || $scope.gender.length == 0){
			$scope.gender_empty = true;
			$scope.form_incomplete = true;
		}
		else if($scope.motherMobile === null || $scope.motherMobile === undefined || $scope.motherMobile.length == 0){
			$scope.mother_mobile_empty = true;
			$scope.form_incomplete = true;
		}
		else if($scope.address === null || $scope.address === undefined || $scope.address.length == 0){
			$scope.address_empty = true;
			$scope.form_incomplete = true;
		}
		else if($scope.city === null || $scope.city === undefined || $scope.city.length == 0){
			$scope.city_empty = true;
			$scope.form_incomplete = true;
		}
		else if($scope.area === null || $scope.area === undefined || $scope.area.length == 0){
			$scope.area_empty = true;
			$scope.form_incomplete = true;
		}
		else if($scope.preference === null || $scope.preference === undefined || $scope.preference.length == 0){
			$scope.preference_empty = true;
			$scope.form_incomplete = true;
		}
		else if($scope.timings === null || $scope.timings === undefined || $scope.timings.length == 0){
			$scope.timings_empty = true;
			$scope.form_incomplete = true;
		}
		else if($scope.activity_list.toString() === null || $scope.activity_list.toString() === undefined || $scope.activity_list.toString().length == 0){
			$scope.activities_empty = true;
			$scope.form_incomplete = true;
		}
		else{
		parentDashboardService.updateParent(userObject).then(function(data){
			console.log(data);
			if(data === true){						
				toaster.pop('success', "Yours details have been updated successfully", '', 4000, 'trustedHtml');
				$scope.refreshParentDetails();
				$scope.parentState = "display";
				$window.scrollTo(0, 0);
			}
		});
		}
	}
	
	$scope.cityNames = parentDashboardService.retrieveCityNames().then(function(data){
		$scope.cityData  = data;
	});
	
	$scope.getAreaNames = function(cityName){
		console.log(cityName);
		if(cityName.length>0){
			$scope.areaNames = parentDashboardService.retrieveAreaNamesForCityName(cityName).then(function(data){
				$scope.areaData  = data;		
			});
		}
		else 
			console.log("no city selected");
	}
	
	/*$scope.editUploadNew = function(choice){
		if(choice == 'yes'){
			$scope.uploadBoxonEdit = true;
			$scope.wannaUpload = true;
		}
		else{
			$scope.wannaUpload = true;
		}
	}
	$scope.createButton = function(){
		$scope.myCentreState = 'create';
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
	
	$scope.addActivityButton = function(){
		$scope.myCentreState = 'addActivity';
		$window.scrollTo(0, 0);
		$scope.activities = [{id: 'activityBlock1'}];
	}*/
	
	/*$scope.deleteActivityFromCentre = function(centreActivityId){		
		myCentreService.deleteActivityFromCentre(centreActivityId).then(function(data){	
			if(data === true){						
				toaster.pop('success', "Activity Class Deleted Successfully", '', 4000, 'trustedHtml');
				$scope.refreshMyCentreDetails();
				$window.scrollTo(0, 0);
				$scope.myCentreState = 'activities';
				$scope.refreshMyCentreDetails();
			}
	});
		
	}*/
	
	/*$scope.editCentreButton = function(){
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
		
	}*/
	
	/*$scope.updateCentreDetails = function(){
		console.log($scope.centreData.id);
		console.log($scope.ownerName);
		console.log($scope.contactNo);
		console.log($scope.description);
		console.log($scope.address);
		if($scope.uploadBoxonEdit === false)
			$scope.imageFiles = $scope.centreData.imagePath;
		else
			$scope.imageFiles = $scope.fileNames.toString();
		console.log($scope.imageFiles);
		
		myCentreService.updateCentreDetails($scope.centreData.id,$scope.ownerName,$scope.contactNo,$scope.description,$scope.address,$scope.imageFiles).then(function(isSuccess){
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
		});		
	}
	
	$scope.addActivity = function(){		
		console.log($scope.activities);
		for(var i = 0 ; i < $scope.activities.length; i++){
			$scope.activities[i].centreId = $scope.centreData.id;
		}
		myCentreService.saveCentreActivities($scope.activities).then(function(isSuccess){
			if(isSuccess === true){
					toaster.pop('success', "Activities added successfully", '', 40000, 'trustedHtml');
					$scope.refreshMyCentreDetails();
					$scope.myCentreState = "display";
					$window.scrollTo(0, 0);
			}
		});
	}
	
	$scope.goBack = function(){
		$scope.myCentreState = "display";
	}
	
	$scope.cityNames = myCentreService.retrieveCityNames().then(function(data){
		$scope.cityData  = data;
		console.log($scope.cityData);
	});
	
	$scope.activityDetails = myCentreService.getActivityNames().then(function(data){
		$scope.activityData  = data;			
	});
	
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

		   console.log($scope.activities);
		   myCentreService.createCentreForCurrentUser(centreObject).then(function(centreId){
				if(centreId !== 0){
					for(var i = 0 ; i < $scope.activities.length; i++){
						$scope.activities[i].centreId = centreId;
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
		   //$scope.activities;
		  }
	
	$scope.getAreaNames = function(cityId){
		if(cityId.length>0){
			$scope.areaNames = myCentreService.retrieveAreaNamesForCity(cityId).then(function(data){
				$scope.areaData  = data;		
			});
		}
		else 
			console.log("no city selected");
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
				$scope.fileArray.push($scope.centreName + "~" + file.name);
				file = Upload.rename(file,$scope.centreName + "~" + file.name);
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
	});*/
	
});

parentDashboard.directive("parentFrame", function() {	
	return {
		restrict : 'AE',
		templateUrl: function(ele, attrs) {
	          return attrs.templatePath;
	      },
		transclude: true		
	};
});

