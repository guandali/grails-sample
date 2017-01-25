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
		controller : 'MainCtrl' 
	});

	$urlRouterProvider.otherwise('home');
}]);



// customers Services

app.factory('customers', ['$http',
function($http) {
	var o = {
		customers : []
	};

	o.getAll = function() {
		return $http.get('/customer_manager/api/customers').success(function(data) {
			angular.copy(data, o.customers);
		});
	};
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
	//Submit form on /create page will invoke foolowing 
	$scope.createCustomer = function(){
		// Need some validation 


	}


}]);

//Currently we don't need anything to be initialized
app.controller('NavCtrl',[
'$scope', 
function($scope){


}]);
