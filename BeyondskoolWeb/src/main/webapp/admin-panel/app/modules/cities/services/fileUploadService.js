/**
 * The fileUploadService is a angular js service for handling  file upload data from REST service.
 *
 * @author 526601
 * @version 1.0
 * @since 2017-01-23
 */


var fileUploadServ = angular.module('optimaWriteIntake');  

fileUploadServ.service('fileUploadService',fileUploadService);
function fileUploadService($http){

	 var fd = new FormData();
     fd.append('username', 'John.Larsson');//hard coded username .to do  change
	 //Service for create a new submission in db
	 this.createSubmission = function() {
			var submissionId = $http({ 
					method : 'POST',
					headers: {
						"Content-Type": undefined
					},
					data : fd,
					url : 'rest/submissions'
			}).then(function(response) {
				return response.data;  
			});
			return submissionId;
		}
};