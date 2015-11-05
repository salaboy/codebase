'use strict';

/**
 * @ngdoc function
 * @name codebaseFrontendApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the codebaseFrontendApp
 */
angular.module('nav.controller', [])
        .controller('navController', ['$scope', '$rootScope', '$location', function ($scope, $rootScope,  $location) {

                $scope.status = "Home";
                
                $scope.go = function (path, data) {
                    $scope.status = path;
                    $location.path(path).search(data);
                };
                


            }]);