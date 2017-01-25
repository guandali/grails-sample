
angular.module('CustomerCtrl', []).controller('CustomerController', function($scope) {

    $scope.tagline = 'Nothing beats a pocket protector!';
    $scope.customerList = Customer.get();
    console.log("----------");
    console.log(JSON.stringify(Customer.get();));



});