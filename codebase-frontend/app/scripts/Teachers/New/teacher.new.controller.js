'use strict';

/**
 * @ngdoc function
 * @name codebaseFrontendApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the codebaseFrontendApp
 */
angular.module('teacher.new.controller', [])
        .controller('newTeacherController', ['$scope', '$rootScope', 'teacherFactory', '$location', function ($scope, $rootScope, teacherFactory, $location) {

                $scope.teacher = {};

                $scope.newTeacher = function (teacher) {
                    console.log(teacher);
                    teacherFactory.newTeacher(teacher).success(function (data) {
                        $location.path("/teachers");
                    }).error(function (error) {
                        console.log("Error: " + error);
                    });

                };


            }]);