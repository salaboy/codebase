'use strict';

/**
 * @ngdoc function
 * @name codebaseFrontendApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the codebaseFrontendApp
 */
angular.module('course.details.controller', [])
        .controller('courseDetailsController', ['$scope', '$rootScope', 'courseFactory', '$location', function ($scope, $rootScope, courseFactory, $location) {
                $scope.course = {};
        
                $scope.go = function (path, data) {
                    $location.path(path).search(data);
                };
                
                var queryString = $location.search();
                $scope.getCourse = function (id) {
                    courseFactory.getCourse(id).success(function (course) {
                        $scope.course = course;
                    }).error(function (error) {
                        console.log("Error: " + error);
                    });

                };

                $scope.getCourse(queryString.id);

            }]);