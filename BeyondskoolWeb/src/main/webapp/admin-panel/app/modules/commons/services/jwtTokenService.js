var jwtTokenServiceVariable = angular.module('beyondSkool');

jwtTokenServiceVariable.service('jwtTokenService',jwtTokenService)

function jwtTokenService() {
	
	var jwtToken = "";
	var Refresh_jwtToken = "";
	 this.getJwtToken = function() {
		 //Please write server side code for jwtTocken generation here
		 jwtToken = "this is the token generated";
	 return jwtToken;		
	 }
	 
	 
	 
};


