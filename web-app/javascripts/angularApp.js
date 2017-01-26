var app = angular.module('customerManager', ['ui.router']);


app.config(['$stateProvider', '$urlRouterProvider',
function($stateProvider, $urlRouterProvider) {

	$stateProvider
	.state('home', {
		url : '/home',
		templateUrl : 'templates/home.html',
		controller : 'MainCtrl',
		resolve : {
			postPromise : ['customers',
			function(customers) {
				return customers.getAll();
			}]

		}
	}).state('create', {
		url : '/create',
		templateUrl : 'templates/create.html',
		controller : 'CustomerCtrl' 
	});

	$urlRouterProvider.otherwise('home');
}]);



// customers Services

app.factory('customers', [
'$http',
'$location',
function($http, $location) {
	var o = {
		customers : []
	};

	o.getAll = function() {
		return $http.get('/customer_manager/api/customers').success(function(data) {
			angular.copy(data, o.customers);
		});
	};
	//POST /customer_manager/api/customers
	o.post = function(customer){
		console.log(JSON.stringify(customer));
		return $http.post('/customer_manager/api/customers', customer)
		.success(function(data){
			console.log(JSON.stringify(data));
			return $location.path("home");
		});

                       

	}
    console.log(JSON.stringify(o));
	return o;
}]);



app.controller('MainCtrl', [
'$scope',
'customers',
function($scope, customers) {
	$scope.customers = customers.customers;
}]);


app.controller('CustomerCtrl', [
'$scope', 
'customers', 
function($scope, customers){
	//Submit form on /create page will invoke following 
	$scope.createCustomer = function(){
	    console.log('clicked');
		// Need some validation 
		//validCustomer is inputs that is validated 
		customers.post({
            		address                : $scope.address,
				    customer_first_name    : $scope.firstname,
				    customer_last_name     : $scope.lastname,
				    email_address          : $scope.email,
				    school_name            : $scope.school

		});



	}


}]);

//Currently we don't need anything to be initialized
app.controller('NavCtrl',[
'$scope', 
function($scope){


}]);
