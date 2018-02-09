var contactServ = angular.module('beyondSkool');

contactServ.service('contactService',contactService);

function contactService($http) {
	
	this.sendMessage = function(name,email,subject,message){
		return $http({
			method : 'POST',
			url : 'rest/contact/contactForm',
			data : jQuery.param({
				'name' : name,
				'email' : email,
				'subject': subject,
				'message' : message
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});
	}
	
};