angular.module('beyondSkool').factory('AuthenticationService', AuthenticationService);

function AuthenticationService($location,$http,$localStorage,$rootScope,$window,$state,AUTH_EVENTS) {
	
	var service = {};
	var token = '';
    service.centreLogin = centreLogin;
    service.parentLogin = parentLogin;
    service.Logout = Logout;
    service.isAuthorized = isAuthorized;
/*  service.unlockAccount = unlockAccount;
    service.ForgotPassword = ForgotPassword;
    service.ResetPassword = ResetPassword;*/
    return service;
    
	var userLoginout = "default";
	function centreLogin(username, password, userRole, callback) {		
		$http({
			method : "POST",
			url : "rest/login/centreAuthenticate",
			dataType: 'json',
			data : jQuery.param({
				'username' : username,
				'password' : password,
				'role' : userRole
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).success(function(response) {
			if (response != "") {				
				jQuery.each(response, function(token, value) {				
					$localStorage.currentUser = {
						"username" : username,
						"token" : token,
						"role" : userRole
					}					
				});
				$rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
				callback(1);				
			}
			else{
				$rootScope.$broadcast(AUTH_EVENTS.loginFailed);
				callback(-1);
			}
		}).error(function(response) {
			
		});
	}
	
	function parentLogin(username, password, userRole, callback) {		
		$http({
			method : "POST",
			url : "rest/login/parentAuthenticate",
			dataType: 'json',
			data : jQuery.param({
				'username' : username,
				'password' : password,
				'role' : userRole
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).success(function(response) {
			if (response != "") {				
				jQuery.each(response, function(token, value) {				
					$localStorage.currentUser = {
						"username" : username,
						"token" : token,
						"role" : userRole
					}					
				});
				$rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
				callback(1);				
			}
			else{
				$rootScope.$broadcast(AUTH_EVENTS.loginFailed);
				callback(-1);
			}
		}).error(function(response) {
			
		});
	}
	
	//to check if the current user role allows him to access a particular page
	function isAuthorized(authorizedRoles){		
		if (!angular.isArray(authorizedRoles)) {
		      authorizedRoles = [authorizedRoles];
		    }
		    return (authorizedRoles.indexOf($localStorage.currentUser.role) !== -1);		
		};
		
		/*function unlockAccount(username, secretQuestion, secretAnswer, callback){
		$http({
			method : "POST",
			url : "rest/login/unlockAccount",
			dataType: 'json',
			data : $.param({
				'username' : username,
				'secretQuestion' : secretQuestion,
				'secretAnswer' : secretAnswer
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).success(function(response) {			
			response === true ? callback(true) : callback(false);				
		}).error(function(response) {

		});
		
	}
	
	function ForgotPassword(username, secretQuestion, secretAnswer, callback){
		$http({
			method : "POST",
			url : "rest/login/forgetPassword",
			dataType: 'json',
			data : $.param({
				'username' : username,
				'secretQuestion' : secretQuestion,
				'secretAnswer' : secretAnswer
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).success(function(response) {			
			response === true ? callback(true) : callback(false);				
		}).error(function(response) {

		});
		
	}
	
	function ResetPassword(username, newPassword,callback){
		$http({
			method: "POST",
			url: "rest/login/resetPassword",
			dataType: 'json',
			data : $.param({
				'username' : username,
				'password' : newPassword				
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).success(function(response) {			
			response === true ? callback(true) : callback(false);				
		}).error(function(response) {

		});
	}	*/

	function Logout() {       
        delete $localStorage.currentUser;
        $rootScope.isLoggedIn = false;
        $http.defaults.headers.common.Authorization = '';          
		$rootScope.$broadcast(AUTH_EVENTS.logoutSuccess);
		$state.go('login');
		$window.scrollTo(0, 0);
    }
};