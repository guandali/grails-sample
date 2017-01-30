var app = angular.module('customerManager', ['ui.router', "xeditable"]);


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
	})
	.state('profile', {
		url : '/profile/:id',
		templateUrl : 'templates/profile.html',
		controller : 'EditableFormCtrl', 
		resolve : { 
			customer:['$stateParams', 'customers',
			function($stateParams, customers) {
				console.log('profile state:' + $stateParams.id);
				return customers.get($stateParams.id);
			}]

		}

	})
	.state('create', {
		url : '/create',
		templateUrl : 'templates/create.html',
		controller : 'CustomerCtrl' 
	});

	$urlRouterProvider.otherwise('home');
}]);


app.run(function(editableOptions) {
  editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2', 'default'
});


//Create a customized directive for checking email
app.directive('isUniqueEmail', [
'customers',
function(customers){
	console.log('app.directive');
	 return {
        restrict: 'A',
        require: 'ngModel',
        link: function (scope, elm, attrs, ctrl) {
            if (!ctrl) return;
            // Binded with 'blur' action 
            elm.bind('blur', function () {
                scope.$apply(checkEmail);
            });
            var checkEmail = function () {
                var userEmail = elm.val();
                console.log("userEmail IS :"  );
                console.log(userEmail);
                if (userEmail !== ''){

                	customers.isUniqueEmail(userEmail).then(function (result) {
                	console.log(result);
                    if (result === 'true') {
                    	console.log('result === true');
                        ctrl.$setValidity('isUniqueEmail', true);
                    }
                    else {
              
                    	ctrl.$setValidity('isUniqueEmail', false);
                    }
                });
                return userEmail;

                }

            };
        }
    };


}]);

// customers Services

app.factory('customers', [
'$http',
'$location',
function($http, $location) {
	var o = {
		customers : [],
		customer :[],
	};
	o.get = function(id){
	  return $http.get('/customer_manager/api/customers/' + id).then(function(res) {
	  	    console.log(JSON.stringify(res.data));
		    angular.copy(res.data, o.customer);
		});

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

	};
    o.update = function(customer){
		console.log(JSON.stringify(customer));
		return $http.post('/customer_manager/api/customers/'+customer.id, customer)
		.success(function(data){
			console.log(JSON.stringify(data));
			return $location.path("home");
		});             

	};
	o.isUniqueEmail = function(unchecked_email){
		console.log('isUniqueEmail');
		// A endpoint for checking unique email is implemented, we could also 
		//Implement it in Angular itself rather than set a API endpoint
		return $http.get('/customer_manager/api/customers/isuniquemail/' + unchecked_email).then(function(res) {
			console.log(JSON.stringify('res.data' + res.data));
			return res.data;
		});

	};
    console.log(JSON.stringify(o));
	return o;
}]);



app.controller('MainCtrl', [
'$scope',
'customers',
function($scope, customers) {
	//Extracting just email_aadress for validation.
	$scope.customers = customers.customers;
}]);

// app.controller('EditCtrl', [
// '$scope',
// 'customers',
// '$http'
// function($scope, customers) {
// 	$scope.customer = customers.customer;
// 	  $scope.saveUser = function() {
//     // $scope.user already updated!
//     return $http.post('/customer_manager/api/customers/'+$scope.customer.id, $scope.customer).error(function(err) {
//       if(err.field && err.msg) {
//         // err like {field: "name", msg: "Server-side error for this username!"} 
//         $scope.editableForm.$setError(err.field, err.msg);
//       } else { 
//         // unknown error
//         $scope.editableForm.$setError('name', 'Unknown error!');
//       }
//     });
//   };

// }]);

app.controller('EditableFormCtrl', function($scope, customers, $http) {
  console.log(customers.customer);
  console.log(typeof customers.customer);
  //$scope.customer = customers.customer;
  $scope.customer = {
   customer_first_name: customers.customer.customer_first_name,
   customer_last_name:  customers.customer.customer_last_name,
   id:                  customers.customer.id,
   school_name:         customers.customer.school_name,
   email_address:       customers.customer.email_address,
   address:             customers.customer.address


  };
  $scope.saveUser = function() {
    // $scope.user already updated!
    console.log($scope.customer.customer_first_name);
    console.log('customer.school_name is ' + $scope.customer.school_name);
    console.log('$scope.customer is' + JSON.stringify($scope.customer));
    return $http.post('/customer_manager/api/customers/'+ $scope.customer.id, $scope.customer.customer_last_name).error(function(err) {
      if(err.field && err.msg) {
        // err like {field: "name", msg: "Server-side error for this username!"} 
        $scope.editableForm.$setError(err.field, err.msg);
      } else { 
        // unknown error
        $scope.editableForm.$setError('name', 'Unknown error!');
      }
    });
  };
});



app.controller('CustomerCtrl', [
'$scope', 
'customers', 
function($scope, customers){
	
	
	//Submit form on /create page will invoke following 
	$scope.createCustomer = function(signupForm){
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
