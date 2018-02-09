/**
 * @function areas Service
 * @memberOf angular.module('beyondSkool')
 * @description 
 */

var packageServ = angular.module('beyondSkool');

packageServ.service('packageService',packageService);

/**
 * @ngdoc function
 * @name citiesService
 * @description This method is used to get names of Areas from restservice.
 * @param Http String that represents Resource URI.
 * 
 * @returns Area data .
 */
function packageService($http) {
	
	this.capturePayment = function(paymentId) {
		return $http({
			method : 'POST',
			url : 'rest/payments/capturePayment',
			data : jQuery.param({				
				'paymentId' : paymentId
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			return response.data;
		});		
	};
};