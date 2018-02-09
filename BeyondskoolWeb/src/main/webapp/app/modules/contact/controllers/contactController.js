var contactModule = angular.module('beyondSkool');
contactModule.controller('contactController', function($scope,$timeout, $window,$location, $rootScope,toaster,$state,contactService) {
	$scope.emptyName = false;
	$scope.emptyEmail = false;
	$scope.emptySubject = false;
	$scope.emptyMessage = false;
	
	$scope.submitForm = function(name,email,subject,message){
		$scope.emptyName = false;
		$scope.emptyEmail = false;
		$scope.emptySubject = false;
		$scope.emptyMessage = false;
		
		if(name === undefined || name.length < 3){
			$scope.emptyName = true;
		}
		else if(email === undefined  || email.length === 0){
			$scope.emptyEmail = true;
		}
		else if(subject === undefined  || subject.length === 0){
			$scope.emptySubject = true;
		}
		else if(message === undefined || message.length === 0){
			$scope.emptyMessage = true;
		}
		else {
			contactService.sendMessage(name,email,subject,message).then(function(data){
				if(data === true){						
					toaster.pop('success', "Successfully sent message", '', 40000, 'trustedHtml');
					$scope.name = "";
					$scope.subject = "";
					$scope.email = "";
					$scope.message = "";
				}
			});
		}
	}
});	