var loginModule = angular.module('beyondSkool');
loginModule.controller('loginController', function($scope, $state, $rootScope, $window, $location,
		$localStorage, AuthenticationService) {
			$scope.incorrectLogin = false;
			$scope.forgetPasswordSuccess = false;
			$scope.forgetPasswordFailure = false;
			$scope.unlockAccountSuccess = false;
			$scope.unlockAccountFailure = false;
			$scope.accountLockedStatus = false;
			$scope.disableUpdateButton = false;
			$scope.hideForgetPasswordForm = false;
			//$scope.passwordStrength = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;
			$scope.isEmpty = false;
	        $scope.login = login;
	        initController();
	        function initController() {	            
	            AuthenticationService.Logout();
	        };
			// For fetching roles
			/*$scope.getRoles = function() {
				$scope.roles = null;
				adminService.getRoles().then(function(data) {					
					$scope.roles = data;
					$scope.roleArray = [];
					for(var i=0;i<$scope.roles.length;i++){
						$scope.roleArray.push($scope.roles[i].roleName);
					}
					$scope.selectedUserRole = $scope.roles[0].roleName;
				});
			};*/

			// To Set the top of the div in center of the screen start
			/*$scope.windowSettings = function() {
				$scope.pageHeight = {
					"height" : ($window.innerHeight) + "px"
				}; // To set the height throttling list section
				$scope.overLay = false;
				$scope.popUpWrapper = false;

				// To Set the top of the div in center of the screen start
				var element = angular.element(document
						.querySelector('.loginScreenWrapper'));
				var height = element[0].offsetHeight;
				var topPosition = ($window.innerHeight) / 2 - height / 2;
				$scope.loginTop = {
					"top" : (topPosition) + "px"
				}; // To set the height throttling list section

				var popBox = angular.element(document
						.querySelector('.myPopupWrapper'));
				var popBoxHeight = element[0].offsetHeight;
				var popBoxPosition = ($window.innerHeight) / 2 - popBoxHeight
						/ 2;
				$scope.popBoxStyle = {
					"top" : (popBoxPosition) + "px"
				};
			};*/

			// Forget Password/ Unlock Password / Close functionalities starts
			/*$scope.dialog = function(userChoice) {
				if ("forgotPassword" == userChoice) {
					$scope.overLay = true;
					$scope.popUpWrapper = true;
					$scope.secretQuestion = "";
					$scope.user_name = "";
					$scope.secretAnswer = "";
					$scope.hideForgetPasswordForm = false;
					$scope.IsMatch=false;
					$scope.forgetPasswordSuccess = false;
					$scope.forgetPasswordFailure = false;
					$scope.resetPassword = false;
					$scope.forgotPassword = true;
					$scope.noEmpty = "";
					$scope.IsPassEmpty = false;
				} else if ("unlockPassword" == userChoice) {
					$scope.overLay = true;
					$scope.unlockAccount = true;
					$scope.secretQuestion = "";
					$scope.user_name = "";
					$scope.secretAnswer = "";
					$scope.unlockAccountSuccess = false;
					$scope.unlockAccountFailure = false;
					$scope.noEmpty = "";
				} else if ("close" == userChoice) {
					$scope.overLay = false;
					$scope.popUpWrapper = false;
				} else if ("unlockClose" == userChoice) {
					$scope.overLay = false;
					$scope.unlockAccount = false;
				}
			}*/
			// close button functionalities over

			// Cancel functionality
			/*$scope.unlockButton = function(button) {
				if ("cancelButtonForForget" == button) {
					$scope.secretQuestion = "";
					$scope.user_name = "";
					$scope.secretAnswer = "";	
					$scope.newPassword = "";
					$scope.confirmNewPassword = "";
					$scope.popUpWrapper = false;
					$scope.overLay = false;
					$scope.IsMatch = false;
				}else if ("cancelButtonForUnlock" == button) {
					$scope.secretQuestion = "";
					$scope.user_name = "";
					$scope.secretAnswer = "";
					$scope.unlockAccount = false;
					$scope.overLay = false;
				} else if ("submitButton==button") {
					$location.path();
				}
			}
			$scope.role=null;
			$scope.changeevt = function () {
				$scope.role=$scope.userRole;
            };
            */
          /*  $scope.forgetPassOrUnlockPassChangeEvent = function(){
            	 if($scope.user_name !== "" && $scope.secretAnswer !== "" && $scope.secretQuestion !== ""){
            		 $scope.isEmpty = false;
	              	$scope.unlockAccountFailure = false;
            	 }
            	 else{
            		 $scope.isEmpty = true;
	              		$scope.unlockAccountFailure = false;
	              		$scope.noEmpty = "";
            	 }
            }     */       
           
            // Forgot password functionality to validate the user against
			// security questions - Forgot password screen 1
            /*$scope.forgetPassword = function(){
				  if($scope.user_name === "" || $scope.secretAnswer === "" ||  $scope.secretQuestion === ""){ 
					  $scope.isEmpty = true;
					  $scope.forgetPasswordFailure = false;
	            	if(($scope.user_name !== "" && $scope.secretAnswer !== "" && $scope.secretQuestion !== "") ){
	            		$scope.isEmpty = false;
	             		$scope.forgetPasswordFailure = false;
	             	}
	            	else if($scope.user_name === undefined || $scope.user_name === "" ){
	            		$scope.isEmpty = true;
	             		$scope.forgetPasswordFailure = false;
	             		$scope.noEmpty = "Username cannot be empty.";    
	             	}else if($scope.secretQuestion === undefined || $scope.secretQuestion === "" ){
	            		$scope.isEmpty = true;
	             		$scope.forgetPasswordFailure = false;
	             		$scope.noEmpty = "Please select your secret question.";    
	             	}else if($scope.secretAnswer === undefined || $scope.secretAnswer === "" ){
	            		$scope.isEmpty = true;
	             		$scope.forgetPasswordFailure = false;
	             		$scope.noEmpty = "Please enter your security answer.";    
	             	}
				  }
                 else{
            	AuthenticationService.ForgotPassword($scope.user_name,$scope.secretQuestion,$scope.secretAnswer,function(result){
            		if(result === true){
            			$scope.forgotPassword = false;
            			$scope.resetPassword=true;
            			$scope.isEmpty = false;
            			$scope.forgetPasswordFailure = false;
            			$scope.forgetPasswordSuccess = true;
            			$scope.forgetPasswordSuccessMsg = "Please enter your new password to continue.";
                	}else{
                		$scope.forgotPassword = true;
            			$scope.resetPassword=false;
            			$scope.isEmpty = false;
            			$scope.forgetPasswordFailure = true;
            			$scope.forgetPasswordSuccess = false;
            			$scope.forgetPasswordFailureMsg = "Invalid Username/Answer. Please try again.";
                	}          	
                });
                 }
            }	*/
            // Resetting password after user user authentication against
			// security questions - Forgot Password --> reset Password (screen
			// 2);
           /* $scope.resetPasswordMethod = function(){
            	if(($scope.confirmNewPassword === "" || $scope.confirmNewPassword === undefined) || ($scope.newPassword === "" || $scope.newPassword === undefined)){
            		$scope.IsPassEmpty = true;
            	}
            	else if($scope.newPassword !== $scope.confirmNewPassword){
            		$scope.loginWarning = true;
            		$scope.isMatch = true;
            	}
            	else{
            	AuthenticationService.ResetPassword($scope.user_name,$scope.newPassword,function(result){         	
            	if(result == true){
            			$scope.forgotPassword = false;
            			$scope.resetPassword=true;
            			$scope.forgetPasswordFailure = false;
            			$scope.forgetPasswordSuccessMsg = "New password updated successfully";
            			$scope.disableUpdateButton = true;
						$scope.hideForgetPasswordForm = true;
            	} else {
            			$scope.forgotPassword = false;
            			$scope.resetPassword=true;
            			$scope.forgetPasswordFailure = true;
            			$scope.forgetPasswordFailureMsg = "Sorry. Password not changed. Try again.";
            			
            	}
            });
            }
            }*/
           
            
            /*$scope.unlockUserAccount = function(){
            	
				  if($scope.user_name === "" || $scope.secretAnswer === "" || $scope.secretQuestion === "")
				  { 
					  $scope.isEmpty = true;
					  $scope.forgetPasswordFailure = false; 
					  $scope.noEmpty = "The form fields cannot be empty"; 
				  }				 
            
            	 if($scope.user_name === "" || $scope.secretAnswer === "" || $scope.secretQuestion === ""){
            		 $scope.isEmpty = true;
            		 $scope.forgetPasswordFailure = false;
	             	 if($scope.user_name === undefined || $scope.user_name === "" ){
	             		$scope.isEmpty = true;
	              		$scope.forgetPasswordFailure = false;
	              		$scope.unlockAccountFailure = false;
	              		$scope.noEmpty = "Username cannot be empty.";    
	              	}else if($scope.secretQuestion === undefined || $scope.secretQuestion === "" ){
	             		$scope.isEmpty = true;
	              		$scope.forgetPasswordFailure = false;
	              		$scope.unlockAccountFailure = false;
	              		$scope.noEmpty = "Please select your secret question.";    
	              	}else if($scope.secretAnswer === undefined || $scope.secretAnswer === "" ){
	             		$scope.isEmpty = true;
	              		$scope.forgetPasswordFailure = false;
	              		$scope.unlockAccountFailure = false;
	              		$scope.noEmpty = "Please enter your security answer.";    
	              	}
            	 }
                 else{
            	AuthenticationService.unlockAccount($scope.user_name,$scope.secretQuestion,$scope.secretAnswer, function(result){
                	if(result === true){
                		$scope.unlockAccountSuccess = true;
                		$scope.unlockAccountFailure = false;
                	}else{
                		$scope.unlockAccountFailure = true;
                		$scope.unlockAccountSuccess = false;
                	}          	
                }); 
              }
            }*/
           $scope.checkLoginFieldEmpty = function(){
            	if($scope.userName !== "" && $scope.password !==""){
            		$scope.loginWarning = false;
            		$scope.incorrectLogin = false;
            	}
            	else if(($scope.userName === undefined && $scope.password !== "") || ($scope.userName === "" && $scope.password !== "")){
            		$scope.loginWarning = true;
            		$scope.incorrectLogin = false;
            		$scope.UserPassWarning = "Username cannot be empty.";    
            	}
            	else if(($scope.userName !== "" && $scope.password === undefined) || ($scope.userName !== "" && $scope.password === "") ){
            		$scope.loginWarning = true;
            		$scope.incorrectLogin = false;
            		$scope.UserPassWarning = "Password cannot be empty.";  
            	}else if(($scope.userName === "" && $scope.password === "") )
            	{
            		$scope.loginWarning = true;
            		$scope.incorrectLogin = false;
            		$scope.UserPassWarning = "Username or password cannot be empty."; 
            	}
            	
            }
			// Account login
            function login() {
            	if(($scope.userName === undefined && $scope.password === undefined) || ($scope.userName === "" && $scope.password === "")){
            		$scope.loginWarning = true;
            		$scope.UserPassWarning = "Username or password cannot be empty.";            		
            	}else
            	if($scope.userName === undefined || $scope.userName === ""){
            		$scope.loginWarning = true;
            		$scope.UserPassWarning = "Username cannot be empty.";            		
            	}else 
            	if($scope.password === undefined || $scope.password === ""){
            		$scope.loginWarning = true;
            		$scope.UserPassWarning = "Password cannot be empty.";            		
            	} 
            	else{
				$scope.userLoginResult = "noData";
				$scope.selectedUserRole = "ADMIN";
				AuthenticationService.Login($scope.userName, $scope.password, $scope.selectedUserRole, function(result){								
						if(result === 1){
						$rootScope.jwtToken = $localStorage.currentUser.token;
						$rootScope.isLoggedIn = true;
						$rootScope.userRole = $localStorage.currentUser.role;
						if($localStorage.currentUser.role === "ADMIN")
							$state.go('pendingCentres');						
					}
					else if(result === 0){
						$scope.accountLockedStatus = true;
						$rootScope.isLoggedIn = false;
						$scope.incorrectLogin = false;
						$scope.loginWarning = false;
					}
					else if(result === -1){
						$rootScope.isLoggedIn = false;
						$scope.incorrectLogin = true;
						$scope.accountLockedStatus = false;
						$scope.loginWarning = false;
					}
				});
            	}				
    		}    		
    		/*$scope.pass = function() {
    			$scope.IsPassEmpty = false;
    			$scope.IsMatch = false;
    			if($scope.confirmNewPassword !== undefined && $scope.confirmNewPassword !== ""){
    			  if ($scope.newPassword != $scope.confirmNewPassword) {
    			    $scope.IsMatch=true;
    			    $scope.IsPassEmpty = false;
    			    return false;
    			  }
    			  $scope.IsMatch=false;
    			}
    		}*/
		});	