'use strict';

/**
 * @ngdoc function
 * @name codebaseFrontendApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the codebaseFrontendApp
 */
angular.module('codebaseFrontendApp')
  .controller('MainCtrl', ['$scope', 'rest', 'register', function($scope, rests, registers) {
    $scope.testPath = rests.testall();
    $scope.testreg = registers.testall();
}]);