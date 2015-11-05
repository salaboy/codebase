'use strict';

/**
 * @ngdoc function
 * @name codebaseFrontendApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the codebaseFrontendApp
 */
angular.module('student.new.controller', [])
        .controller('newStudentController', ['$scope', '$rootScope', 'studentFactory', '$location', function ($scope, $rootScope, studentFactory, $location) {

                $scope.student = {};

                $scope.newStudent = function (student) {
                    console.log(student);
                    student.password = "password";
                    studentFactory.newStudent(student).success(function (data) {
                        $location.path("/students");
                    }).error(function (error) {
                        console.log("Error: " + error);
                    });

                };


            }]);