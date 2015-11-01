'use strict';

/**
 * @ngdoc function
 * @name codebaseFrontendApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the codebaseFrontendApp
 */
angular.module('school.edit.controller', [])
        .controller('editSchoolController', ['$scope', '$rootScope', 'schoolFactory', '$location', function ($scope, $rootScope, schoolFactory, $location) {

                $scope.school = {};

                var queryString = $location.search();
                $scope.getSchool = function (id) {
                    schoolFactory.getSchool(id).success(function (school) {
                        $scope.school = school;
                    }).error(function (error) {
                        console.log("Error: " + error);
                    });

                };

                $scope.updateSchool = function (school) {
                    console.log(school);
                    schoolFactory.updateSchool(school).success(function (data) {
                        $location.path("/schools");
                    }).error(function (error) {
                        console.log("Error: " + error);
                    });

                };
                
                $scope.getSchool(queryString.id);

            }]);