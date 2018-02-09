/**
 * @ngdoc controller
 * @name fileUploadModule.controller:fileUploadController
 * 
 * @description The fileUploadController is a angular js controller for handling
 *              create new case and upload files.
 */

'use strict';
var fileUploadModule = angular.module('optimaWriteIntake');

fileUploadModule
		.controller(
				'fileUploadController',
				function($scope, FileUploader, submissionService, toaster,$localStorage) {

					$scope.uploadingInProgress = false;

					var uploader = $scope.uploader = new FileUploader({
						headers: {
					        Authorization: 'Bearer ' + $localStorage.currentUser.token //for storing the token when jwtInterceptor is not invoked
					    },
						alias : 'file',
					});

					/**
					 * @ngdoc function
					 * @name createCaseAndUploadFiles
					 * @description This method is invoked when user clicks
					 *              'create' button in create new case pop up or
					 *              'upload' button in add files to submission
					 *              pop up
					 */
					$scope.createCaseAndUploadFiles = function() {

						if ($scope.uploader.queue.length > 0) {

							$scope.uploadingInProgress = true;
							if ($scope.$parent.isNewCase) {
								submissionService
										.createSubmission()
										.then(
												function(data) {
													$scope.submissionId = data;
													var url = 'rest/submissions/'
															+ $scope.submissionId
															+ '/files';
													for (var i = 0; i < $scope.uploader.queue.length; i++) {// adding
														// submission
														// id
														// to
														// all
														// file
														// objects
														uploader.queue[i].url = url;
													}

													uploader.uploadAll();
												});
							} else {
								$scope.submissionId = $scope.$parent.selectedSubmissionId;

								var url = 'rest/submissions/'
										+ $scope.submissionId + '/files';
								for (var i = 0; i < $scope.uploader.queue.length; i++) {// adding
									// submission
									// id
									// to
									// all
									// file
									// objects
									uploader.queue[i].url = url;
								}

								uploader.uploadAll();

							}
						} else {
							alert("Please select files first");
						}
					};

					/**
					 * @ngdoc function
					 * @name uploader.filters.push
					 * @description This method is an async filter.
					 */
					uploader.filters.push({
						name : 'asyncFilter',
						fn : function(item, options, deferred) {

							setTimeout(deferred.resolve, 1e3);
						}
					});

					/**
					 * @ngdoc function
					 * @name onWhenAddingFileFailed
					 * @description This gets invoked on failure of file
					 *              addition.
					 */

					uploader.onWhenAddingFileFailed = function(item, filter,
							options) {

					};

					/**
					 * @ngdoc function
					 * @name onAfterAddingFile
					 * @description This method gets invoked after adding file
					 *              ,setting the corresponding file icons
					 * @param fileItem
					 *            Object of selected file.
					 */

                    uploader.onAfterAddingFile = function(fileItem) {
                        var extension = fileItem.file.name
                                      .substr(fileItem.file.name.lastIndexOf('.') + 1);
                        if (extension.toUpperCase() === 'PDF') {
                               fileItem.file.icon = 'app/angular_resources/images/acrobat_icon_large.png';
                        } else if (extension.toUpperCase()  === 'XLS' || extension.toUpperCase()  === 'XLSX') {
                               fileItem.file.icon = 'app/angular_resources/images/excel_icon_large.png';
                        } else if (extension.toUpperCase() === 'DOC' || extension.toUpperCase()  === 'DOCX') {
                               fileItem.file.icon = 'app/angular_resources/images/word_icon_large.png';
                        } else if (extension.toUpperCase()  === 'JPEG' || extension.toUpperCase()  === 'JPG'
                                      || extension.toUpperCase()  === 'PNG' || extension.toUpperCase()  === 'TIFF'
                                      || extension.toUpperCase()  === 'TIF') {
                               fileItem.file.icon = 'app/angular_resources/images/image_icon_large.png';
                        } else {
                               fileItem.file.icon = 'app/angular_resources/images/unknown_icon_large.png';
                        }
    };

					uploader.onAfterAddingAll = function(addedFileItems) {

					};
					uploader.onBeforeUploadItem = function(item) {

					};
					uploader.onProgressItem = function(fileItem, progress) {

					};
					uploader.onProgressAll = function(progress) {

					};
					uploader.onSuccessItem = function(fileItem, response,
							status, headers) {

					};
					uploader.onErrorItem = function(fileItem, response, status,
							headers) {

					};
					uploader.onCancelItem = function(fileItem, response,
							status, headers) {

					};
					uploader.onCompleteItem = function(fileItem, response,
							status, headers) {

					};
					
					/**
					 * @ngdoc function
					 * @name onCompleteAll
					 * @description This method gets invoked on completion of entire files uploaded for case.
					 */

					uploader.onCompleteAll = function() {
						uploader.clearQueue();
						$scope.uploadingInProgress = false;
						$scope.$parent.refreshTaskList();
						$scope.$parent.overLay = false;
						$scope.$parent.popUpWrapper = false;
						if ($scope.$parent.isNewCase) {
							toaster.pop('success', "", 'New submission created :'
									+ $scope.submissionId, 4000, 'trustedHtml');

						} else {
							toaster.pop('success', "",
									'File upload completed ', 4000,
									'trustedHtml');
						}

					};

				});
