'use strict';

/**
 * @ngdoc function
 * @name codebaseFrontendApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the codebaseFrontendApp
 */
angular.module('codebaseFrontendApp')
  .controller('AboutCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
