'use strict';

/**
 * @ngdoc function
 * @name codebaseFrontendApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the codebaseFrontendApp
 */
angular.module('student.list.controller', [])
        .controller('studentsListController', ['$scope', '$rootScope', 'studentFactory', '$location', '$http', function ($scope, $rootScope, studentFactory, $location, $http) {
                $scope.students = [];
                var queryString = $location.search();
                $scope.go = function (path, data) {
                    $location.path(path).search(data);
                };


                $scope.loadStudents = function () {

                    studentFactory.getAllStudents().success(function (students) {
                        console.log(" Start - Schools Get All : ");
                        for(var i = 0; i < students.length; i++){
                            console.log(students[i]);
                            
                        }
                        console.log(" End - Schools Get All : ");
                        $scope.students = students;
                    }).error(function (error) {
                        console.log("Error: " + error);
                    });
                };

                $scope.loadStudents();


            }]);