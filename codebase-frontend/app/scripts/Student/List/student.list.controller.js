'use strict';

/**
 * @ngdoc function
 * @name codebaseFrontendApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the codebaseFrontendApp
 */
angular.module('student.list.controller', [])
    .controller('studentListController', ['$scope', '$rootScope', 'studentFactory', '$location',  function ($scope, $rootScope, studentFactory, $location) {
        
        $scope.go = function ( path ) {
          $location.path( path );
        };
}]);