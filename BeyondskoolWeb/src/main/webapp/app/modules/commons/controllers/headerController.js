var headerBannerModule = angular.module('beyondSkool');
headerBannerModule.controller('headerController', function ($scope, AuthenticationService){	
	
	$scope.logout = function() {	            
        AuthenticationService.Logout();
    };
});