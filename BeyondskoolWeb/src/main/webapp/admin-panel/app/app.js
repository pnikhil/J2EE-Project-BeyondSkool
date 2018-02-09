var beyondSkool = angular.module('beyondSkool', ['ui.router', 'ngRoute','ui.bootstrap', /*'ngSanitize',*/'ngFileUpload','ngResource', 'toaster', 'ngStorage','ngCookies']);

//added the user roles and unauthorized events as constants
beyondSkool.constant('USER_ROLES', {
	all : '*',
	ADMIN : 'ADMIN' 
}).constant('AUTH_EVENTS', {
	loginSuccess : 'auth-login-success',
	loginFailed : 'auth-login-failed',
	logoutSuccess : 'auth-logout-success',
	sessionTimeout : 'auth-session-timeout',
	notAuthenticated : 'auth-not-authenticated',
	notAuthorized : 'auth-not-authorized',
	serverError : 'server-error'
});

beyondSkool.config(config);
beyondSkool.run(run);

beyondSkool.controller('appController', function($scope, $location,$rootScope, $controller, $uibModal, $state) {
	
});

beyondSkool.directive('updateTitle', ['$rootScope', '$timeout', 
                               function($rootScope, $timeout) {
                                return {
                                  link: function(scope, element) {
                                    var listener = function(event, toState) {
                                      var title = 'BeyondSkool';
                                      if (toState.data && toState.data.pageTitle) title = toState.data.pageTitle;
                                      $timeout(function() {
                                        element.text(title);
                                      }, 0, false);
                                    };
                                    $rootScope.$on('$stateChangeSuccess', listener);
                                  }
                                };
                              }]);

// added state based change event using ui-router
function config($httpProvider, $stateProvider, $urlRouterProvider,AUTH_EVENTS,USER_ROLES) {
	$urlRouterProvider.otherwise("/login");
	$stateProvider.state('login', {
	    url: '/login',
		templateUrl : 'app/modules/login/views/login.html',
		controller : 'loginController',
		data:{
			pageTitle : 'BeyondSkool Login'
		}
	})
	.state('home', {
		url:'/',
		templateUrl : 'app/modules/login/views/login.html',
		controller : 'loginController',
			data:{
				pageTitle : 'BeyondSkool Login'
			}
	})
	.state('cities', {
		url: '/cities',
		templateUrl : 'app/modules/cities/views/cities.html',
		controller : 'citiesController',
		 data: {
           requiresLogin: true,
           authorizedRoles: USER_ROLES.ADMIN,
           pageTitle: 'Cities - BeyondSkool'
       }
	})
	.state('areas', {
		url: '/areas',
		templateUrl : 'app/modules/areas/views/areas.html',
		controller : 'areasController',
		 data: {
           requiresLogin: true,
           authorizedRoles: USER_ROLES.ADMIN,
           pageTitle: 'Areas - BeyondSkool'
       }
	})
	.state('users', {
		url: '/users',
		templateUrl : 'app/modules/users/views/users.html',
		controller : 'usersController',
		 data: {
           requiresLogin: true,
           authorizedRoles: USER_ROLES.ADMIN,
           pageTitle: 'Users - BeyondSkool'
       }
	})
	.state('activities', {
		url: '/activities',
		templateUrl : 'app/modules/activities/views/activities.html',
		controller : 'activitiesController',
		 data: {
           requiresLogin: true,
           authorizedRoles: USER_ROLES.ADMIN,
           pageTitle: 'Activities - BeyondSkool'
       }
	})
	.state('centres', {
		url: '/centres',
		templateUrl : 'app/modules/centres/views/centres.html',
		controller : 'centresController',
		 data: {
           requiresLogin: true,
           authorizedRoles: USER_ROLES.ADMIN,
           pageTitle: 'Centres - BeyondSkool'
       }
	}).state('payments', {
		url: '/payments',
		templateUrl : 'app/modules/payments/views/payments.html',
		controller : 'paymentsController',
		 data: {
           requiresLogin: true,
           authorizedRoles: USER_ROLES.ADMIN,
           pageTitle: 'Payments - BeyondSkool'
       }
	}).state('bookings', {
		url: '/bookings',
		templateUrl : 'app/modules/bookings/views/bookings.html',
		controller : 'bookingsController',
		 data: {
           requiresLogin: true,
           authorizedRoles: USER_ROLES.ADMIN,
           pageTitle: 'Bookings - BeyondSkool'
       }
	}).state('pendingCentres', {
		url: '/pending_centres',
		templateUrl : 'app/modules/pending-centres/views/pending-centres.html',
		controller : 'pendingCentresController',
		 data: {
           requiresLogin: true,
           authorizedRoles: USER_ROLES.ADMIN,
           pageTitle: 'Pending Activity Centres - BeyondSkool'
       }
	}).state('userReviews', {
		url: '/user_reviews',
		templateUrl : 'app/modules/reviews/views/reviews.html',
		controller : 'reviewsController',
		 data: {
           requiresLogin: true,
           authorizedRoles: USER_ROLES.ADMIN,
           pageTitle: 'User Reviews - BeyondSkool'
       }
	});
	
	$httpProvider.interceptors.push(['$injector', function($injector) {
		return $injector.get('TokenAuthInterceptor');
	}]);
	
}

function run($rootScope, $http, $location, $localStorage, AuthenticationService, AUTH_EVENTS,$state) {
    // keep user logged in after page refresh
    if ($localStorage.currentUser) {
    	$rootScope.isLoggedIn = true;
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.currentUser.token;            
    }
    // redirect to login page if not logged in and trying to access a restricted page
    $rootScope.$on('$locationChangeStart', function (event, next, current) {
        var publicPages = ['/login'];
        var restrictedPage = publicPages.indexOf($location.path()) === -1;
        if (restrictedPage && !$localStorage.currentUser) {
        	$state.go('login');
        }
    });
    
  //before each state change, check if the user is logged in
	//and authorized to move onto the next state
	$rootScope.$on('$stateChangeStart', function(event, next) {		
		if (next.data !== undefined) {
			var authorizedRoles = next.data.authorizedRoles;
			if($state.current.name !== ""){
			if (!AuthenticationService.isAuthorized(authorizedRoles)) {
				event.preventDefault();
			}}
		}
	});
	
	/*$rootScope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams) {
			    $state.current = toState;
			    console.log($state.current);
			    console.log($state.current.name);
			  
				 // Idle.watch();
			}
	});	*/
}