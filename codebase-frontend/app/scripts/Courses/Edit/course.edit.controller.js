'use strict';

/**
 * @ngdoc function
 * @name codebaseFrontendApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the codebaseFrontendApp
 */
angular.module('course.edit.controller', [])
        .controller('editCourseController', ['$scope', '$rootScope', 'courseFactory', '$location', function ($scope, $rootScope, courseFactory, $location) {

                $scope.course = {
                    school:{}
                };
                $scope.schoolId = $location.search().schoolId;
                $scope.course.school.id = $scope.schoolId;
                var queryString = $location.search();
                $scope.course.school.id = queryString.id;
                $scope.getCourse = function (id) {
                    courseFactory.getCourse(id).success(function (course) {
                        $scope.course = course;
                    }).error(function (error) {
                        console.log("Error: " + error);
                    });

                };

                 $scope.deleteCourse = function (id) {
                    courseFactory.deleteCourse(id).success(function (data) {
                        $location.path("/courses").search({schoolId: $scope.schoolId});
                    }).error(function (error) {
                        console.log("Error: " + error);
                    });

                };
                $scope.updateCourse = function (course) {
                    console.log(course);
                    courseFactory.updateCourse(course).success(function (data) {
                        $location.path("/courses");
                    }).error(function (error) {
                        console.log("Error: " + error);
                    });

                };
                
                $scope.getCourse(queryString.id);

            }]);