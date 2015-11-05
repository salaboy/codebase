'use strict';

/**
 * @ngdoc function
 * @name codebaseFrontendApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the codebaseFrontendApp
 */
angular.module('nav.controller', [])
        .controller('navController', ['$scope', '$rootScope', '$location', 'schoolFactory', function ($scope, $rootScope,  $location, schoolFactory) {

                $scope.status = "Home";
                $scope.sidebarOpen = false;
                $scope.mainNavTopOpen = false;
                
                
                $scope.schools = [];
                $scope.activeSchool = [];
            
                $scope.loadSchools = function () {

                    schoolFactory.getAllSchools().success(function (schools) {
                        console.log(" Start - Schools Get All : ");
                        for(var i = 0; i < schools.length; i++){
                            console.log(schools[i]);
                            
                        }
                        console.log(" End - Schools Get All : ");
                        $scope.schools = schools;
                        $scope.activeSchool = schools[0];
                    }).error(function (error) {
                        console.log("Error: " + error);
                    });
                };

                $scope.loadSchools();
            
                
                $scope.changeActiveSchool = function(id){
                   
                    schoolFactory.getSchool(id).success(function (school) {
                        $scope.activeSchool = school;
                    }).error(function (error) {
                        console.log("Error: " + error);
                    });
 
                };
            
                $scope.isActive = function (route) {
                    return route === $location.path();
                };
                
                $scope.openSidebar = function(){
                    $scope.sidebarOpen = !$scope.sidebarOpen;
                    
                }
                $scope.openMainNavTop = function(){
                    $scope.mainNavTopOpen = !$scope.mainNavTopOpen; 
                }
                $scope.closeMainNavTop = function(){
                    $scope.mainNavTopOpen = false; 
                }
            
                $scope.go = function (path, data) {
                    $scope.status = path;
                    $location.path(path).search(data);
                    $scope.sidebarOpen = false;
                    $scope.mainNavTopOpen = false;
                };
                
            
                $( window ).resize(function() {
                    if($( window ).width() > 992) {
                         $scope.sidebarOpen = false;
                        $scope.$apply();
                    }
                });


            }]);