'use strict';

/**
 * @ngdoc function
 * @name codebaseFrontendApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the codebaseFrontendApp
 */
angular.module('school.new.controller', [])
        .controller('newSchoolController', ['$scope', '$rootScope', 'schoolFactory', '$location', function ($scope, $rootScope, schoolFactory, $location) {

                $scope.school = {};

                $scope.newSchool = function (school) {
                    console.log(school);
                    schoolFactory.newSchool(school).success(function (data) {
                        $location.path("/schools");
                    }).error(function (error) {
                        console.log("Error: " + error);
                    });

                };


            }]);