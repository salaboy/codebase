"use strict";
(function () {
    

    var teachersController = function ($scope, $rootScope, $location, $routeParams, $teachers, $students, $groups, $util) {
        $scope.userType = $rootScope.usertype;
        $scope.urlparams = $routeParams;
        $scope.teachers = $teachers.getAllTeachers();
        $scope.groups= $groups.getAllGroups(); 
        
        //ADD TEACHER FORM
        $scope.addSubmitted = false;
        $scope.addTeacher = function (isValid) {

            $scope.addSubmitted = true;

            if (isValid) {
                $location.path("/teachers");
            }
        };
            
        //CHECK URL PARAMETERS
        if (!$util.isEmpty($scope.urlparams)) {
            $scope.currentUser = $scope.teachers[$scope.urlparams.id];
        }
        
        //UPDATE TEACHER
        $scope.updateTeacher = function (isValid) {

            $scope.updateSubmitted = true;

            if (isValid) {
                $location.path("/teachers");
            }
        };
    };
       module.exports = teachersController;
//    teachersController.$inject = ['$scope', '$rootScope', '$location', '$routeParams', '$teachers', '$students', '$groups', '$util'];
//    angular.module( "codebase" ).controller("teachersController", teachersController);
    
}());