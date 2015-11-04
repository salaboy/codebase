'use strict';

/**
 * @ngdoc function
 * @name codebaseFrontendApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the codebaseFrontendApp
 */
angular.module('teacher.list.controller', [])
        .controller('teachersListController', ['$scope', '$rootScope', 'teacherFactory', '$location', '$http', function ($scope, $rootScope, teacherFactory, $location, $http) {
                $scope.teachers = [];
                var queryString = $location.search();
                $scope.go = function (path, data) {
                    $location.path(path).search(data);
                };


               


            }]);