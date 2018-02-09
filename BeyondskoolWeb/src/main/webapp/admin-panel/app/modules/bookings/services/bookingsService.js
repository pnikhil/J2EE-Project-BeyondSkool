/**
 * @function areas Service
 * @memberOf angular.module('beyondSkool')
 * @description 
 */

var bookingsServ = angular.module('beyondSkool');

bookingsServ.service('bookingsService',bookingsService);

/**
 * @ngdoc function
 * @name bookingsService
 * @description This method is used to get names of Areas from restservice.
 * @param Http String that represents Resource URI.
 * 
 * @returns bookings data .
 */
function bookingsService($http) {
	var bookingRecords = new Array();	
	this.retrievebookings = function() {
		bookingRecords = $http({
			method : 'GET',
			url : '../rest/bookings/bookingsList'
		}).then(function(response) {
			return response.data;
		});
		return bookingRecords;
	};

};