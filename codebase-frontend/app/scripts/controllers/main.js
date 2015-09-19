'use strict';

/**
 * @ngdoc function
 * @name codebaseFrontendApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the codebaseFrontendApp
 */
angular.module('codebaseFrontendApp')
  .controller('MainCtrl', ['$scope', 'rest', 'register', function($scope, rest, register) {
    $scope.greeting = rest.testall();
    $scope.reg = register.testall();

 
}]);