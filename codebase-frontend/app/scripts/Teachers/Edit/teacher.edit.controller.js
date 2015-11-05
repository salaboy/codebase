'use strict';

/**
 * @ngdoc function
 * @name codebaseFrontendApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the codebaseFrontendApp
 */
angular.module('teacher.edit.controller', [])
        .controller('editTeacherController', ['$scope', '$rootScope', 'teacherFactory', '$location', function ($scope, $rootScope, teacherFactory, $location) {

                $scope.teacher = {};

                var queryString = $location.search();
                $scope.getTeacher = function (id) {
                    teacherFactory.getTeacher(id).success(function (teacher) {
                        teacher.birthday = new Date(teacher.birthday);
                        $scope.teacher = teacher;
                    }).error(function (error) {
                        console.log("Error: " + error);
                    });

                };

                 $scope.deleteTeacher = function (id) {
                    teacherFactory.deleteTeacher(id).success(function (data) {
                        $location.path("/teachers");
                    }).error(function (error) {
                        console.log("Error: " + error);
                    });

                };
                $scope.updateTeacher = function (teacher) {
                    console.log(teacher);
                    teacherFactory.updateTeacher(teacher).success(function (data) {
                        $location.path("/teachers");
                    }).error(function (error) {
                        console.log("Error: " + error);
                    });

                };
                
                $scope.getTeacher(queryString.id);

            }]);