/**
 * @ngdoc controller
 * @name signUpController
 * 
 * @description 
 */

var signup = angular.module('beyondSkool');

signup.controller('signupController',function($scope,$window,$location, $rootScope,signupService,toaster,$state,$timeout,filterFilter,AuthenticationService,$localStorage) {
	$scope.owner_name_empty = false;
	$scope.registering = false;
	$scope.owner_email_empty = false;
	$scope.owner_password_empty = false;
	$scope.owner_contact_empty = false;
	$scope.password_match = false;
	$scope.owner_confirm_password_empty = false;
	$scope.reg_complete = false;
	$scope.email_exists = false;
	$scope.user_email_exists = false;
	$scope.user_name_empty = false;
	$scope.user_email_empty = false;
	$scope.user_password_empty = false;
	$scope.user_contact_empty = false;
	$scope.password_match = false;
	$scope.user_confirm_password_empty = false;
	$scope.user_child_name_empty = false;
	$scope.user_child_age_empty = false;
	$scope.user_child_school_empty = false;
	
	$scope.checkParentEmailExist = function(){
		if($scope.user_email === "" || $scope.user_email === undefined){
			$scope.user_email_exists = false;
		}
		signupService.checkParentAccountExists($scope.user_email).then(function(data){
			if(data === true){						
				$scope.user_email_exists = true;
			}
			else{
				$scope.user_email_exists = false;
			}
		});
	}
	
	$scope.checkEmailExist = function(){
		if($scope.owner_email === "" || $scope.owner_email === undefined){
			$scope.email_exists = false;
		}
		signupService.checkCentreAccountExists($scope.owner_email).then(function(data){
			if(data === true){						
				$scope.email_exists = true;
			}else{
				$scope.email_exists = false;
			}
		});
	}
	
	$scope.userRegister = function(name,email,password,confirmPassword,contact,childName,age,school){
		$scope.user_name_empty = false;
		$scope.user_email_empty = false;
		$scope.user_password_empty = false;
		$scope.user_contact_empty = false;
		$scope.password_match = false;
		$scope.user_confirm_password_empty = false;
		$scope.user_child_name_empty = false;
		$scope.user_child_age_empty = false;
		$scope.user_child_school_empty = false;
		
		if(name === undefined || name.length < 3){
			$scope.user_name_empty = true;
		}
		else if(email === undefined  || email.length === 0){
			$scope.user_email_empty = true;
		}
		else if(password === undefined  || password.length === 0){
			$scope.user_password_empty = true;
		}		
		else if(confirmPassword === undefined  || confirmPassword.length === 0){
			$scope.user_confirm_password_empty = true;
		}
		else if(password !== confirmPassword){
			$scope.password_match = true;
		}
		else if(contact === undefined || contact.length === 0){
			$scope.user_contact_empty = true;
		}
		else if(childName === undefined || childName.length === 0){
			$scope.user_child_name_empty = true;
		}
		else if(age === undefined || age.length === 0){
			$scope.user_child_age_empty = true;
		}
		else if(school === undefined || school.length === 0){
			$scope.user_child_school = true;
		}
		else if($scope.email_exists === false){
		$scope.registering = true;
		signupService.userRegister($scope.user_name,$scope.user_email,$scope.user_password,$scope.user_contact,$scope.user_child_name,$scope.user_child_age,$scope.user_child_school).then(function(data){
			if(data === true){						
				toaster.pop('success', "Hi " + $scope.user_name + ", Your account sign up is successful. Please check your email for details.", '', 700, 'trustedHtml');
				$scope.user_name = "";
				$scope.user_email = "";
				$scope.user_password = "";
				$scope.user_contact = "";
				$scope.user_child_name = "";
				$scope.user_child_school = "";
				$scope.user_confirm_password = "";
				$scope.user_child_age = "";
				$scope.selectedUserRole = "PARENT";
				AuthenticationService.parentLogin(email, password, $scope.selectedUserRole, function(result){								
					if(result === 1){
					$rootScope.jwtToken = $localStorage.currentUser.token;
					$rootScope.isLoggedIn = true;
					$rootScope.isParentLoggedIn = true;
					$rootScope.userRole = $localStorage.currentUser.role;
					$scope.registering = false;
					if($localStorage.currentUser.role === "PARENT"){
						$state.go('upcomingClasses');
						$window.scrollTo(0, 0);
						toaster.pop('success', "Your have been logged in into your account", '', 4000, 'trustedHtml');
					}}
					else if(result === -1){
						$rootScope.isLoggedIn = false;
						$scope.incorrectLogin = true;
						$scope.accountLockedStatus = false;
						$scope.loginWarning = false;
					}
				});
					
				}else {
					toaster.pop('error', "Something went Wrong", '', 40000, 'trustedHtml');				
				}
			});
			}
		}
		
		
	$scope.centreRegister = function(name,email,password,confirmPassword,contact){
		$scope.owner_name_empty = false;
		$scope.owner_email_empty = false;
		$scope.owner_password_empty = false;
		$scope.owner_contact_empty = false;
		$scope.password_match = false;
		$scope.owner_confirm_password_empty = false;
		
		if(name === undefined || name.length < 3){
			$scope.owner_name_empty = true;
		}
		else if(email === undefined  || email.length === 0){
			$scope.owner_email_empty = true;
		}
		else if(password === undefined  || password.length === 0){
			$scope.owner_password_empty = true;
		}		
		else if(confirmPassword === undefined  || confirmPassword.length === 0){
			$scope.owner_confirm_password_empty = true;
		}else if(password !== confirmPassword){
			$scope.password_match = true;
		}
		else if(contact === undefined || contact.length === 0){
			$scope.owner_contact_empty = true;
		}		
		else if($scope.email_exists === false){
			$scope.registering = true;
			$scope.imgDirId = Math.round((Math.random() * 1000) * 10);
		signupService.centreRegister(name,email,password,contact,$scope.imgDirId).then(function(data){
			if(data === true){
				toaster.pop('success', "Hi " + name + " Your account sign up is successful", '', 750, 'trustedHtml');
				$scope.owner_name = "";
				$scope.owner_email = "";
				$scope.owner_password = "";
				$scope.owner_contact = "";
				$scope.owner_confirm_password = "";
				$scope.registering = false;
				$scope.selectedUserRole = "CENTRE";				
				AuthenticationService.centreLogin(email, password, $scope.selectedUserRole, function(result){						
					if(result === 1){
					$rootScope.jwtToken = $localStorage.currentUser.token;
					$rootScope.isLoggedIn = true;
					$rootScope.isCentreLoggedIn = true;
					$rootScope.userRole = $localStorage.currentUser.role;
					if($localStorage.currentUser.role === "CENTRE"){
						$state.go('myCentre');
						$window.scrollTo(0, 0);
						toaster.pop('success', "Your have been logged in into your account", '', 4000, 'trustedHtml');
					}}					
				else if(result === -1){
					$rootScope.isLoggedIn = false;
					$scope.incorrectLogin = true;
					$scope.accountLockedStatus = false;
					$scope.loginWarning = false;
				}
			});
				
			}else {
				toaster.pop('error', "Something went Wrong", '', 40000, 'trustedHtml');				
			}
		});
		}
	}
	
});