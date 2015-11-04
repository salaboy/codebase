'use strict';

/**
 * @ngdoc function
 * @name codebaseFrontendApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the codebaseFrontendApp
 */
angular.module('teacher.details.controller', [])
        .controller('teacherDetailsController', ['$scope', '$rootScope', 'teacherFactory', '$location', function ($scope, $rootScope, teacherFactory, $location) {
                $scope.teacher = {};
        
                $scope.go = function (path, data) {
                    $location.path(path).search(data);
                };
                
                var queryString = $location.search();
                $scope.getTeacher = function (id) {
                    teacherFactory.getTeacher(id).success(function (teacher) {
                        $scope.teacher = teacher;
                    }).error(function (error) {
                        console.log("Error: " + error);
                    });

                };

                $scope.getTeacher(queryString.id);

            }]);