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
                
                $scope.students = [];
                var queryString = $location.search();
                $scope.go = function (path, data) {
                    $location.path(path).search(data);
                };


                $scope.loadTeachers = function () {

                    teacherFactory.getAllTeachers().success(function (teachers) {
                        console.log(" Start - teachers Get All : ");
                        for(var i = 0; i < teachers.length; i++){
                            console.log(teachers[i]);
                            
                        }
                        console.log(" End - teachers Get All : ");
                        $scope.teachers = teachers;
                    }).error(function (error) {
                        console.log("Error: " + error);
                    });
                };

                $scope.loadTeachers();
                
               


            }]);