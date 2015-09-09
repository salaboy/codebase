"use strict";
(function () {
    

    var groupsController = function ($scope, $rootScope, $location, $routeParams, $teachers, $students, $groups, $util)  {
        $scope.userType = $rootScope.usertype;
        $scope.urlparams = $routeParams;
        $scope.groups= $groups.getAllGroups();
        
        $scope.teachers = $teachers.getAllTeachers();
        
        $scope.students = $students.getAllStudents();
        
        
        //ADD TEACHER FORM
        $scope.addSubmitted = false;
        $scope.addGroup = function (isValid) {

            $scope.addSubmitted = true;

            if (isValid) {
                $location.path("/groups");
            }
        };
            
        //CHECK URL PARAMETERS
        if (!$util.isEmpty($scope.urlparams)) {
            $scope.currentGroup = $scope.groups[$scope.urlparams.id];
            $scope.currentGroup.students = $students.getStudentsInGroup($scope.urlparams.id);
        }
        
        //UPDATE TEACHER
        $scope.updateTGroup = function (isValid) {

            $scope.updateSubmitted = true;

            if (isValid) {
                $location.path("/groups");
            }
        };
    };
    
//    groupsController.$inject = ['$scope', '$rootScope', '$location', '$routeParams', '$teachers', '$students', '$groups', '$util'];
//    angular.module( "codebase" ).controller("groupsController", groupsController);
        module.exports = groupsController;
}());