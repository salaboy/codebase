'use strict';

/**
 * @ngdoc function
 * @name codebaseFrontendApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the codebaseFrontendApp
 */
angular.module('school.list.controller', [])
        .controller('schoolListController', ['$scope', '$rootScope', 'schoolFactory', '$location', '$http', function ($scope, $rootScope, schoolFactory, $location, $http) {
                $scope.schools = [];

                $scope.go = function (path, data) {
                    $location.path(path).search(data);
                };


                $scope.loadSchools = function () {

                    schoolFactory.getAllSchools().success(function (schools) {
                        console.log(" Start - Schools Get All : ");
                        for(var i = 0; i < schools.length; i++){
                            console.log(schools[i]);
                            
                        }
                        console.log(" End - Schools Get All : ");
                        $scope.schools = schools;
                    }).error(function (error) {
                        console.log("Error: " + error);
                    });
                };

                $scope.loadSchools();


            }]);