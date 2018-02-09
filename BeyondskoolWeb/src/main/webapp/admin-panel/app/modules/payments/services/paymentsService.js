/**
 * @function areas Service
 * @memberOf angular.module('beyondSkool')
 * @description 
 */

var paymentsServ = angular.module('beyondSkool');

paymentsServ.service('paymentsService',paymentsService);

/**
 * @ngdoc function
 * @name paymentsService
 * @description This method is used to get names of Areas from restservice.
 * @param Http String that represents Resource URI.
 * 
 * @returns Payments data .
 */
function paymentsService($http) {
	var paymentRecords = new Array();	
	this.retrievePayments = function() {
		paymentRecords = $http({
			method : 'GET',
			url : '../rest/payments/paymentsList'
		}).then(function(response) {
			return response.data;
		});
		return paymentRecords;
	};

};