'use strict';

/**
 * @ngdoc function
 * @name codebaseFrontendApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the codebaseFrontendApp
 */
angular.module('school.details.controller', [])
        .controller('schoolDetailsController', ['$scope', '$rootScope', 'schoolFactory', '$location', function ($scope, $rootScope, schoolFactory, $location) {
                $scope.school = {};
        
                $scope.go = function (path, data) {
                    $location.path(path).search(data);
                };
                
                var queryString = $location.search();
                $scope.getSchool = function (id) {
                    schoolFactory.getSchool(id).success(function (school) {
                        $scope.school = school;
                    }).error(function (error) {
                        console.log("Error: " + error);
                    });

                };

                $scope.getSchool(queryString.id);

            }]);