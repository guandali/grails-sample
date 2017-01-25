var customerApp = angular.module('customerApp', []);

customerApp.controller('RegistrationController', ['$scope', function($scope) {
  console.log($scope.email );

  $scope.register = function() {
	console.log(JSON.stringify($scope.user));
    $scope.message = 'Welcome ' + $scope.user.firstname;
  };

}]);