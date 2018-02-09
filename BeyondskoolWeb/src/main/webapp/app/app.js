var beyondSkool = angular.module('beyondSkool', ['ui.router', 'ngRoute','ui.bootstrap','ngFileUpload','ngResource', 'toaster', 'ngStorage','ngCookies','infinite-scroll']);

//added the user roles and unauthorized events as constants
beyondSkool.constant('USER_ROLES', {
	ALL : '*',
	PARENT : 'PARENT',
	CENTRE : 'CENTRE'
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
function config($httpProvider, $stateProvider, $urlRouterProvider,AUTH_EVENTS,USER_ROLES,$stateProvider) {
	$urlRouterProvider.otherwise("/");
	$stateProvider
	.state('home', {
		url:'/',
		templateUrl : 'app/modules/home/views/home.html',
		controller : 'homeController',
			data:{
				pageTitle : 'BeyondSkool - Home',
				requiresLogin: false
			}
	}).state('contact', {
	    url: '/contact',
		templateUrl : 'app/modules/contact/views/contact.html',
		controller : 'contactController',
		data:{
			pageTitle : 'BeyondSkool - Contact Us',
			requiresLogin: false			
		}
	}).state('login', {
	    url: '/login',
		templateUrl : 'app/modules/login/views/login.html',
		controller : 'loginController',
		params: {
		     obj: null
		 },
		data:{
			pageTitle : 'BeyondSkool Login',
			requiresLogin: false
		}
	}).state('signup', {
	    url: '/signUp',
		templateUrl : 'app/modules/signup/views/signup.html',
		controller : 'signupController',		
		data:{
			pageTitle : 'BeyondSkool - Sign Up',
			requiresLogin: false
		}
	}).state('myCentre', {
	    url: '/my_centre',
		templateUrl : 'app/modules/my-centre/views/my-centre.html',
		controller : 'myCentreController',
		data:{
			pageTitle : 'BeyondSkool - Manage Your Centre',
			requiresLogin: true,
			authorizedRoles: USER_ROLES.CENTRE
		}
	})/*.state('myCentre.addActivity', {
	    url: '/add_activity',
		templateUrl : 'app/modules/my-centre/views/add-my-centre-activity.html',
		controller : 'myCentreController',
		data:{
			pageTitle : 'BeyondSkool - Manage Your Centre',
			requiresLogin: true,
			authorizedRoles: USER_ROLES.CENTRE
		}
	})*/.state('activityCentres', {
	    url: '/activity_centres',
		templateUrl : 'app/modules/activity-centres/views/centres.html',
		controller : 'centreController',		
		data:{
			pageTitle : 'BeyondSkool - Activity Centres',
			requiresLogin: false
		}
	}).state('viewCentre', {
	    url: '/centre/:centreId/:centreName',
		templateUrl : 'app/modules/activity-centres/views/view-centre.html',
		controller: function($scope, $stateParams) {
		     $scope.centreName = $stateParams.centreName;
		     $scope.centreId = $stateParams.centreId;
		  },	
		data:{
			pageTitle : 'BeyondSkool - View Activity Centre',
			requiresLogin: false
		}
	}).state('parentDashboard', {
	    url: '/parent_dashboard',
		templateUrl : 'app/modules/parent-dashboard/views/parent-dashboard.html',
		controller : 'parentDashboardController',		
		data:{
			pageTitle : 'BeyondSkool - Parent Dashboard',
			requiresLogin: true,
			authorizedRoles: USER_ROLES.PARENT
		}
	}).state('packages', {
	    url: '/pricing',
		templateUrl : 'app/modules/packages/views/packages.html',
		controller : 'packageController',		
		data:{
			pageTitle : 'BeyondSkool - Packages',
			requiresLogin: false
		}
	}).state('refundPolicy', {
	    url: '/refund_policy',
		templateUrl : 'app/modules/commons/views/refund-policy.html',
		controller : 'homeController',		
		data:{
			pageTitle : 'BeyondSkool - Refund Policy',
			requiresLogin: false
		}
	}).state('privacyPolicy', {
	    url: '/privacy_policy',
		templateUrl : 'app/modules/commons/views/privacy-policy.html',
		controller : 'homeController',		
		data:{
			pageTitle : 'BeyondSkool - Privacy Policy',
			requiresLogin: false
		}
	}).state('termsConditions', {
	    url: '/terms_conditions',
		templateUrl : 'app/modules/commons/views/terms-conditions.html',
		controller : 'homeController',		
		data:{
			pageTitle : 'BeyondSkool - Terms and Conditions',
			requiresLogin: false
		}
	}).state('upcomingClasses', {
	    url: '/upcoming_classes',
		templateUrl : 'app/modules/upcoming-classes/views/upcoming-classes.html',
		controller : 'upcomingController',		
		data:{
			pageTitle : 'BeyondSkool - Upcoming Courses',
			requiresLogin: false
		}
	});
	
	$httpProvider.interceptors.push(['$injector', function($injector) {
		return $injector.get('TokenAuthInterceptor');
	}]);
	
}

function run($rootScope, $http, $location, $localStorage, AuthenticationService, AUTH_EVENTS,$state) {
	$rootScope.$state = $state;
    // keep user logged in after page refresh
    if ($localStorage.currentUser) {
    	if($localStorage.currentUser.role === 'PARENT'){
    		$rootScope.isParentLoggedIn = true;
    		$rootScope.isLoggedIn = true;
    	}
    	else if($localStorage.currentUser.role === 'CENTRE'){
    		$rootScope.isCentreLoggedIn = true;
    		$rootScope.isLoggedIn = true;
    	}    	
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.currentUser.token;            
    }
    // redirect to login page if not logged in and trying to access a restricted page
    $rootScope.$on('$locationChangeStart', function (event, next, current) {
        var publicPages = ['/','/contact','/signUp','/login','/activity_centres','/view_centre','/upcoming_classes','/pricing','/terms_conditions','/privacy_policy','/refund_policy'];
        var restrictedPage = publicPages.indexOf($location.path()) === -1;
        if($location.path().indexOf('view_centre') > -1){
        	
        } else if (restrictedPage && !$localStorage.currentUser) {
        	$state.go('login');
        }         
    });
    
  //before each state change, check if the user is logged in
	//and authorized to move onto the next state
	$rootScope.$on('$stateChangeStart', function(event, next) {		
		if(next.data.requiresLogin === true){
		if (next.data !== undefined) {
			var authorizedRoles = next.data.authorizedRoles;			
			if (!AuthenticationService.isAuthorized(authorizedRoles)) {
				event.preventDefault();
			}
		}
	}
	});
}