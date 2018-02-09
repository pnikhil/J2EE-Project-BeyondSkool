var fullScreen = angular.module('optimaWriteIntake');
fullScreen.controller('fullScreenController', ['$scope', function ($scope) {	
	
    $scope.keyup = function(keyEvent) { 	
		
			if(keyEvent.keyCode == 37 && keyEvent.ctrlKey){
				
				$scope.$broadcast ('arrowSelection','leftArrow');
			}
			else if(keyEvent.keyCode == 37){
				$scope.$broadcast ('TablearrowSelection','leftArrow');
				
			}
			if(keyEvent.keyCode == 39 && keyEvent.ctrlKey){
				
				$scope.$broadcast ('arrowSelection','rightArrow');
			}
			else if(keyEvent.keyCode == 39){
				$scope.$broadcast ('TablearrowSelection','rightArrow');
			}
			if(keyEvent.keyCode == 38 && keyEvent.ctrlKey){
				
				$scope.$broadcast ('arrowSelection','upArrow');
			}
			else if(keyEvent.keyCode == 38){
				$scope.$broadcast ('TablearrowSelection','upArrow');
			}
			if(keyEvent.keyCode == 40 && keyEvent.ctrlKey){
		
				$scope.$broadcast ('arrowSelection','downArrow');
			}
			else if(keyEvent.keyCode == 40){
				$scope.$broadcast ('TablearrowSelection','downArrow');
			}
			
		
    };
			  
}]);