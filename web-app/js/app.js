'use strict';

// Define the `App` module
var customerApp = angular.module('customerApp', []);

// Define the `PhoneListController` controller on the `phonecatApp` module
customerApp.controller('CustomerListController', function CustomerListController($scope, $http) {
	   console.log("customerApp.controller");
	    

	   var url = 'http://localhost:8080/customer_manager/customers';
       $http.get(url).then(function(response) {
    	$scope.customerList = response.data;
      });
});