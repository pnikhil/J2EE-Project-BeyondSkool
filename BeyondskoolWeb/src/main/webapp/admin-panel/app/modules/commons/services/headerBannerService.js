var headerServiceVariable = angular.module('optimaWriteIntake');

headerServiceVariable.service('headerService',headerService)

function headerService($location) {
	
	var menuClickfunctionOut = "default";
	 this.menuClickfunction = function(userChoice) {	 
		alert("clicked in=="+userChoice);
		
		//ajax call start				
				var jsonText = '{"data": {"userChoice": '+userChoice+'}}';
				/*
				var jsonDataObj = JSON.parse(jsonText);			
				 $http({
						method : "GET",
						url : "to your url",
						params: jsonDataObj,
						headers: { 'Content-Type': 'application/json' },
				 }).then(function mySucces(response) {					
						//sucess response
						menuClickfunctionOut = "sucess";
						
				 }, function myError(response) {
						//fail case
						menuClickfunctionOut = "failed";
				});*/
			   //ajax call end
			   return menuClickfunctionOut;
		
	 }
};


