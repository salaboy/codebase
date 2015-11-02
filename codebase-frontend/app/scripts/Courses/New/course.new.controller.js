'use strict';

/**
 * @ngdoc function
 * @name codebaseFrontendApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the codebaseFrontendApp
 */
angular.module('course.new.controller', [])
        .controller('newCourseController', ['$scope', '$rootScope', 'courseFactory', '$location', function ($scope, $rootScope, courseFactory, $location) {

                $scope.course = {};

                $scope.newCourse = function (course) {
                    console.log(course);
                    courseFactory.newCourse(course).success(function (data) {
                        $location.path("/courses");
                    }).error(function (error) {
                        console.log("Error: " + error);
                    });

                };


            }]);