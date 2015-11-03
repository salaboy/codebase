'use strict';

/**
 * @ngdoc function
 * @name codebaseFrontendApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the codebaseFrontendApp
 */
angular.module('student.details.controller', [])
        .controller('studentDetailsController', ['$scope', '$rootScope', 'studentFactory', '$location', function ($scope, $rootScope, studentFactory, $location) {
                $scope.student = {};
        
                $scope.go = function (path, data) {
                    $location.path(path).search(data);
                };
                
                var queryString = $location.search();
                $scope.getStudent = function (id) {
                    studentFactory.getStudent(id).success(function (student) {
                        $scope.student = student;
                    }).error(function (error) {
                        console.log("Error: " + error);
                    });

                };

                $scope.getStudent(queryString.id);

            }]);