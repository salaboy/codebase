'use strict';

/**
 * @ngdoc function
 * @name codebaseFrontendApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the codebaseFrontendApp
 */
angular.module('codebaseFrontendApp')
  .controller('MainCtrl', ['$scope', '$rootScope',  'register', function($scope, $rootScope, register) {
    register.login('facebook');
}]);