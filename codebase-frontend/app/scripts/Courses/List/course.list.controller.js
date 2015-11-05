'use strict';

/**
 * @ngdoc function
 * @name codebaseFrontendApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the codebaseFrontendApp
 */
angular.module('course.list.controller', [])
        .controller('coursesListController', ['$scope', '$rootScope', 'courseFactory', '$location', '$http', function ($scope, $rootScope, courseFactory, $location, $http) {
                $scope.courses = [];
                $scope.schoolId = $location.search().schoolId;
                $scope.go = function (path, data) {
                    $location.path(path).search(data);
                };


                $scope.loadCourses = function (id) {

                    courseFactory.getAllCoursesBySchool(id).success(function (courses) {
                        console.log(" Start - courses Get All : ");
                        for(var i = 0; i < courses.length; i++){
                            console.log(courses[i]);
                            
                        }
                        console.log(" End - courses Get All : ");
                        $scope.courses = courses;
                    }).error(function (error) {
                        console.log("Error: " + error);
                    });
                };

                $scope.loadCourses($scope.schoolId);


            }]);