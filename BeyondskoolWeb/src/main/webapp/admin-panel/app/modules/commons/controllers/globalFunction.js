angular.module('optimaWriteIntake').run(function($rootScope, $state, Auth, AUTH_EVENTS) {
	
	//before each state change, check if the user is logged in
	//and authorized to move onto the next state
	$rootScope.$on('$stateChangeStart', function (event, next,AUTH_EVENTS) {
		console.log(AUTH_EVENTS);
	    var authorizedRoles = next.data.authorizedRoles;
	    if (!Auth.isAuthorized(authorizedRoles)) {
	      event.preventDefault();
	      if (Auth.isAuthenticated()) {	       
	        $rootScope.$broadcast(AUTH_EVENTS.notAuthorized);
	      } else {	       
	        $rootScope.$broadcast(AUTH_EVENTS.notAuthenticated);
	      }
	    }
	  });
	
	/* To show current active state on menu */
	$rootScope.getClass = function(path) {
		if ($state.current.name == path){
			return "active";
		} else {
			return "";
		}
	}
});