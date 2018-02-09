var beyondSkool = angular.module('beyondSkool').factory('TokenAuthInterceptor', ['$localStorage', '$rootScope','$q','$location','$window','AUTH_EVENTS', 
                  function($localStorage,$rootScope,$q,$location,$window,AUTH_EVENTS,AuthenticationService) {
					return {
						request : function(config) {
							if ($localStorage !== undefined) {								
								if ($localStorage.currentUser !== undefined) {									
									if ($localStorage.currentUser.token !== undefined) {										
										var authToken = $localStorage.currentUser.token;										
										if (authToken) {
											//$window.sessionStorage["userInfo"];
											config.headers.Authorization = "Bearer " + $localStorage.currentUser.token;
											//console.log(config.headers.Authorization);
											}
										}
									}
								}
							return config;
						},
						response : function(response) {
							var refreshToken = response.headers('jwt');
							if (refreshToken) {
								$rootScope.jwtToken = refreshToken;								
								$localStorage.currentUser.token = refreshToken;
								authToken = $rootScope.jwtToken;
							}
							return response;
						},
						responseError : function(error) {
							console.log(error);
							//var stateService = $injector.get('$state');
							//console.log(stateService);
							$rootScope.$broadcast({
							500 : AUTH_EVENTS.serverError,
							401 : AUTH_EVENTS.notAuthenticated,
							403 : AUTH_EVENTS.notAuthorized,
							419 : AUTH_EVENTS.sessionTimeout,
							440 : AUTH_EVENTS.sessionTimeout
							}[error.status], error);
							$rootScope.jwtToken = "";
							AuthenticationService.Logout();
							return $q.reject(error);
						}
					};
				}]);


beyondSkool.config(function($httpProvider) {
	$httpProvider.interceptors.push('TokenAuthInterceptor');	
});