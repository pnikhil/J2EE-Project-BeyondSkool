/**
 * @ngdoc controller
 * @name homeController
 * 
 * @description 
 */

var packages = angular.module('beyondSkool');

packages.controller('packageController',function($scope,$timeout, $window,$location, $rootScope,toaster,$state,$localStorage,packageService,AuthenticationService) {
	$window.scrollTo(0, 0);
	if($localStorage.currentUser !== undefined){
		$scope.userEmail = $localStorage.currentUser.username;
	}
	
	$scope.parentLogin = function() {
       	/*if(($scope.userName === undefined && $scope.password === undefined) || ($scope.userName === "" && $scope.password === "")){
       		$scope.loginWarning = true;
       		$scope.UserPassWarning = "Username or password cannot be empty.";            		
       	}else
       	if($scope.userName === undefined || $scope.userName === ""){
       		$scope.loginWarning = true;
       		$scope.UserPassWarning = "Username cannot be empty.";            		
       	}else 
       	if($scope.password === undefined || $scope.password === ""){
       		$scope.loginWarning = true;
       		$scope.UserPassWarning = "Password cannot be empty.";            		
       	} 
       	else{*/
			$scope.userLoginResult = "noData";
			$scope.selectedUserRole = "PARENT"
			AuthenticationService.parentLogin($scope.parent_userName, $scope.parent_password, $scope.selectedUserRole, function(result){								
					if(result === 1){
					$rootScope.jwtToken = $localStorage.currentUser.token;
					$rootScope.isLoggedIn = true;
					$rootScope.isParentLoggedIn = true;
					$rootScope.userRole = $localStorage.currentUser.role;
					if($localStorage.currentUser.role === "PARENT")
						/*$state.go('packages');*/
						/*$window.scrollTo(0, 0);*/
						$scope.userEmail = $localStorage.currentUser.username;
						toaster.pop('success', "Your have successfully logged in", '', 4000, 'trustedHtml');
					}					
				else if(result === -1){
					$rootScope.isLoggedIn = false;
					$scope.incorrectLogin = true;				
					$scope.loginWarning = false;
				}
			});
       	/*}*/	
		}
	
	
	$scope.processing = false;
	 var loadExternalScript = function(path) {
		    var result = jQuery.Deferred(), script = document.createElement("script");
		    script.async = "async";
		    script.type = "text/javascript";
		    script.src = path;
		    script.onload = script.onreadystatechange = function(_, isAbort) {
		      if (!script.readyState || /loaded|complete/.test(script.readyState)) {
		        if (isAbort)
		          result.reject();
		        else
		          result.resolve();
		      }
		    };
		    script.onerror = function() {
		      result.reject();
		    };

		    jQuery("head")[0].appendChild(script);

		    return result.promise();
		  };
	
	// Use loadScript to load the Razorpay checkout.js
	// -----------------------------------------------
	var callRazorPayScript = function(amount) {
	  var img = "app/angular_resources/images/logo-top2.png",
	      name = "BeyondSkool",
	      description = "Elite Pack Subscription",
	      amount = amount;
	  
	  loadExternalScript('https://checkout.razorpay.com/v1/checkout.js').then(function() { 
	    var options = {
	      key: 'rzp_live_DYa0m9Ae74bNSY',
	      protocol: 'https',
	      hostname: 'api.razorpay.com',
	      amount: amount,
	      name: "BeyondSkool",
	      description: "Elite Pack Subscription",
	      image: img,
	      prefill: {
	        email: $scope.userEmail
	      },
	      notes: {
		        "address": "Primrose Rd, Craig Park Layout, Ashok Nagar, Next to Garuda Mall, Bengaluru, Karnataka 560025"
		   },
	      theme: {
	    	  color: "#F37254"
	      },
	      handler: function (transaction, response){
	    	  $scope.processing = true;
	    	  packageService.capturePayment(transaction.razorpay_payment_id).then(function(data){	        	
	        	if(data == true){
	        		toaster.pop('success', "Payment Successful. Your wallet has been updated", '', 4000, 'trustedHtml');
	        		$scope.processing = false;
	        	}
	        	else
	        		toaster.pop('success', "Payment failed. Try Again", '', 4000, 'trustedHtml');
			});
	      }
	    };

	    window.rzpay = new Razorpay(options);	    
	    rzpay.open();
	  });
	}

	// Trigger call to action buttons depending on the bundle packs
	// -----------------------------------------------
	var $payBundle = jQuery('.js-pay-bundle');

	$payBundle.on('click', function() {
		var itemId = jQuery(this).data('itemid'),
		amount = jQuery(this).data('amount');
		callRazorPayScript(amount);		
	});	
});
