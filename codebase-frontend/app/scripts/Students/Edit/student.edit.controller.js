'use strict';

/**
 * @ngdoc function
 * @name codebaseFrontendApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the codebaseFrontendApp
 */
angular.module('student.edit.controller', [])
        .controller('editStudentController', ['$scope', '$rootScope', 'studentFactory', '$location', function ($scope, $rootScope, studentFactory, $location) {

                $scope.student = {};

                var queryString = $location.search();
                $scope.getStudent = function (id) {
                    studentFactory.getStudent(id).success(function (student) {
                        student.birthday = new Date(student.birthday);
                        $scope.student = student;
                    }).error(function (error) {
                        console.log("Error: " + error);
                    });

                };

                 $scope.deleteStudent = function (id) {
                    studentFactory.deleteStudent(id).success(function (data) {
                        $location.path("/students");
                    }).error(function (error) {
                        console.log("Error: " + error);
                    });

                };
                $scope.updateStudent = function (student) {
                    console.log(student);
                    studentFactory.updateStudent(student).success(function (data) {
                        $location.path("/students");
                    }).error(function (error) {
                        console.log("Error: " + error);
                    });

                };
                
                $scope.getStudent(queryString.id);

            }]);