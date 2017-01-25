// public/js/services/NerdService.js
angular.module('CustomerService', []).factory('Customer', ['$http', function($http) {
	var baseUrl = "http://localhost:8080/customer_manager";

        // call to get all nerds
    return {
        get : function() {
            return $http.get(baseUrl + '/api/customers');
        },


                // these will work when more API routes are defined on the Node side of things
        // call to POST and create a new nerd
        create : function(customerData) {

            return $http.post('/api/customers', customerData);
        },

    }       

}]);